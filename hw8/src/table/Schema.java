package table;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A relational schema, with support for a multi-attribute primary key.
 * @author Glenn
 *
 */
public class Schema extends Type {
	
	// note that there is no way to remove field names or types from a schema
	private List<String> fnames;	  // invariant: elements must be unique
	private List<FieldType> ftypes;   // invariant: must be same length as fnames
	private List<String> keyFields;	  // invariant: must be null or a subset of fnames
	
	private static final int maxFieldNameLength = 24;

	// create a new record type with one field
	public Schema() {
		fnames = new ArrayList<String>();
		ftypes = new ArrayList<FieldType>();
		keyFields = null;
	}
	
	/**
	 * add a field
	 * @param fname
	 * @param ftype
	 */
	public void add(String fname, FieldType ftype) {
		if (fname.length() > maxFieldNameLength) {
			throw new IllegalArgumentException("field name "+fname+" is more than "+maxFieldNameLength+" in length");
		}
		fnames.add(fname);
		ftypes.add(ftype);
	}
	
	/**
	 * set the primary key of the schema
	 * @param keyFields These must be non-empty and a subset of the schema fields.
	 */
	public void setKey(List<String> keyFields) {
		if (this.keyFields != null) {
			throw new IllegalArgumentException("primary key of a schema cannot be changed");
		}
		if (!fnames.containsAll(keyFields)) {
			throw new IllegalArgumentException("primary key fields must all be schema fields");
		}
		// the key is copied so it cannot be changed later
		this.keyFields = new ArrayList<String>(keyFields);
	}
	
	/**
	 * return the index of the given field name in this schema, or -1 if
	 * no such field name in this schema
	 * @param fname
	 * @return
	 */
	public int getFieldIndex(String fname) {
		return fnames.indexOf(fname);
	}
	
	/**
	 * return the type of the ith field
	 * @param i
	 * @return
	 */
	public FieldType getType(int i) {
		if (i < 0 || i >= ftypes.size()) {
			throw new IllegalArgumentException("No field i in schema: "+this);
		}
		return ftypes.get(i);
	}
	
	/**
	 * return the name of the ith field
	 * @param i
	 * @return name of the ith field
	 */
	public String getName(int i) {
		if (i < 0 || i >= ftypes.size()) {
			throw new IllegalArgumentException("No field i in schema: "+this);
		}
		return fnames.get(i);
	}
	
	/**
	 * return a list of the field names of this schema
	 * @return a list of field names
	 */
	public List<String> getFieldNames() {
		return new ArrayList<String>(fnames);
	}
	
	/**
	 * return a list of the field names of the primary key,
	 * or null if no primary key is defined for this schema
	 * @return a list of key field names
	 */
	public List<String> getPrimaryKey() {
		return keyFields;
	}
	
	/**
	 * return the number of fields in the record type
	 * @return
	 */
	public int size() { return fnames.size(); }
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Schema)) {
			return false;
		}
		Schema s = (Schema)obj;
		return (fnames.equals(s.fnames) && ftypes.equals(s.ftypes) && keyFields.equals(s.keyFields));
	}
	
	@Override
	public int getLen() {
		int totLen = 0;
		for (FieldType ft : ftypes) {
			totLen += ft.getLen();
		}
		return totLen;
	}

	/**
	 * Return a tuple of this schema with default field values
	 * @return
	 */
	public Tuple defaultTuple() {
		List<Field> fields = new ArrayList<Field>();
		for (FieldType ft : ftypes) {
			fields.add(ft.defaultField());
		}
		Tuple tup = new Tuple(fields, this);
		return tup;
	}

	/**
	 * return the result of "projecting" this schema onto the given attributes;
	 * the result can be an empty schema
	 * 
	 * @param attrs
	 * @return
	 */
	public Schema project(List<String> attrs) {
		// This is not efficient, but schemas are usually small.
		// Maybe better to return this if all of the attrs are specified.
		Schema result = new Schema();
		for (String attr: attrs) {
			int index = getFieldIndex(attr);
			if (index >= 0) {
				result.add(attr,  getType(index));
			}
		}
		return result;
	}
	
	/**
	 * Return the schema of the natural join of a table with this schema
	 * and a table with the given schema.  Return null if the schema
	 * share no field names, or there exists a shared field name but
	 * with different types in the two tables.  The resulting schema
	 * will have no primary key.
	 * @param schema1
	 * @return
	 */
	public static Schema naturalJoin(Schema schema1, Schema schema2) {
		Set<String> sharedFields = schema1.sharedFields(schema2);
		if (sharedFields.size() == 0) {
			return null;
		}
		
		Schema result = new Schema();
		// add fields from schema1
		for (int i = 0; i < schema1.fnames.size(); i++) {
			result.add(schema1.fnames.get(i), schema1.ftypes.get(i));
		}
		// add non-shared fields from schema2
		for (int i = 0; i < schema2.fnames.size(); i++) {
			String fname = schema2.fnames.get(i);
			FieldType type2 = schema2.ftypes.get(i);
			if (schema1.fnames.contains(fname)) {
				// this field name is shared; types must be the same
				FieldType type1 = schema1.ftypes.get(schema1.getFieldIndex(fname));
				if (!type1.equals(type2)) {
					return null; 
				}
			} else {
				// this field name not share; add it to result
				result.add(fname, type2);
			}
		}
		
		return result;
	}
	
	/**
	 * return the set of the field names shared by this and the given schema
	 * @param schema2
	 * @return
	 */
	public Set<String> sharedFields(Schema schema2) {
		Set<String> shared = new HashSet<>(fnames);
		shared.retainAll(schema2.fnames);
		return shared;
	}
	
	@Override
	public String toString() {
		String s = "["+String.join(", ",  fnames)+"]";
		return s;
	}

}
