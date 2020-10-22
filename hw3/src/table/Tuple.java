package table;

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
	 * return the fields obtained by projecting this tuple onto the given attributes
	 * 
	 * @param attrs
	 * @return
	 */
	public List<Field> project(List<String> attrs) {
		List<Field> projFields = new ArrayList<>();
		for (int i = 0; i < fields.size(); i++) {
			String fname = schema.getName(i);
			int index = attrs.indexOf(fname);
			if (index >= 0) {
				projFields.add(fields.get(index));
			}
		}
		
		return projFields;
	}
	
	public String toString() {
		// Strings.join would be simpler, but it doesn't work on arbitrary lists
		String s = fields.stream().map(Object::toString).collect(Collectors.joining(", "));
		return s;
	}

}
