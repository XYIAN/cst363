package table;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A database record (aka tuple)
 * 
 * @author Glenn
 *
 */
public class Tuple {
	
	List<Field> fields;
	Schema schema;   // invariant: size and types of schema must match fields
	
	public Tuple(List<Field> fields, Schema schema) {
		this.fields = fields;
		this.schema = schema;
		if (fields.size() != schema.size()) {
			throw new IllegalArgumentException("Error: Number of fields does not match size of schema.");
		}
	}
	
	/**
	 * @return number of fields in the record
	 */
	public int size() {
		return fields.size();
	}
	
	/**
	 * get the schema of this record
	 * @return
	 */
	public Schema getSchema() {
		return schema;
	}
	
	/**
	 * get the ith field of the record, or null if no ith field
	 * @param i index of the attribute (first attribute has index 0)
	 */
	public Field get(int i) {	
		if (i < 0 || i >= fields.size()) {
			return null;
		}
		return fields.get(i);
	}
	
	/**
	 * get a field of this tuple by name
	 * 
	 * @param name
	 * @return
	 */
	public Field get(String name) {
		int i = schema.getFieldIndex(name);
		if (i < 0) {
			return null;
		}
		return fields.get(i);
	}
	
	public void set(int i, Field field) {
		// field must have the right type
		if (!(field.getType().equals(schema.getType(i)))) {
			throw new IllegalArgumentException("Field does not match type of ith field in schema");
		}
		fields.set(i, field);
	}
	
	/**
	 * write the fields of this record to the given byte array, at the given position
	 * @param buf
	 * @param index
	 */
	public void serialize(byte[] buffer, int i) {
		// write the record into the byte buffer
		int len = schema.getLen();
		ByteBuffer bbuf = ByteBuffer.allocate(len);
		serialize(bbuf, 0);
		
		// write the byte buffer into buffer at position i
		bbuf.rewind();
		bbuf.get(buffer, i, len);
	}

	/**
	 * set the fields of this record using values in the given byte array, at the given position
	 * @param buf
	 * @param index
	 */
	public void deserialize(byte[] buffer, int i) {
		// put the buffer values into a byte buffer
		int len = schema.getLen();
		ByteBuffer bbuf = ByteBuffer.allocate(len);
		bbuf.put(buffer, i, len);
		
		// set the record value using the byte buffer 
		bbuf.rewind();
		deserialize(bbuf, 0);
	}
	
	/**
	 * write the fields of this record to the given buffer, at the given position
	 * @param buf
	 * @param index
	 */
	public void serialize(ByteBuffer buf, int index) {
		for (Field field: fields) {
			field.serialize(buf, index);
			index += field.getType().getLen();
		}
	}

	/**
	 * set the fields of this record using values in the given buffer, at the given position
	 * @param buf
	 * @param index
	 */
	public void deserialize(ByteBuffer buf, int index) {
		for (Field field : fields) {
			field.deserialize(buf, index);
			index += field.getType().getLen();
		}
	}
	
	/**
	 * return the fields obtained by projecting this tuple onto the given attributes
	 * 
	 * @param attrs
	 * @return
	 */
	public List<Field> project(List<String> attrs) {
		List<Field> projFields = new ArrayList<>();
		// if the name of the ith field of the tuple 
		// appears in attrs, add that field to the result
		for (int i = 0; i < fields.size(); i++) {
			String fname = schema.getName(i);
			int index = attrs.indexOf(fname);
			if (index >= 0) {
				projFields.add(fields.get(i));
			}
		}
		
		return projFields;
	}

	/**
	 * return the fields of this plus the fields of the given tuple
	 * @param tuple1
	 * @return
	 */
	public List<Field> concatenate(Tuple tuple1) {
		List<Field> result = new ArrayList<Field>(fields);
		result.addAll(tuple1.fields);
		return result;
	}
	
	/**
	 * return true if this has has same number of records as as obj,
	 * and the records have the same values
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Tuple)) {
			return false;
		}
		
		// same number of records
		Tuple tup = (Tuple)obj;
		if (tup.size() != size()) {
			return false;
		}
		
		// fields are equal
		for (int i = 0; i < size(); i++) {
			if (!(this.get(i).equals(tup.get(i)))) {
				return false;
			}
		}
		
		return true;
	}
	
	public String toString() {
		// Strings.join would be simpler, but it doesn't work on arbitrary lists
		String s = fields.stream().map(Object::toString).collect(Collectors.joining(", "));
		return s;
	}
}
