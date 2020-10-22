package table;

import java.util.ArrayList;
import java.util.List;

/**
 * A relational schema, which can be thought of as the type of a record.
 * @author Glenn
 *
 */
public class Schema extends Type {
	
	private List<String> fnames;	  // invariant: elements must be unique
	private List<FieldType> ftypes;   // invariant: must be same length as fnames
	
	private static final int maxFieldNameLength = 24;

	// create a new record type with one field
	public Schema() {
		fnames = new ArrayList<String>();
		ftypes = new ArrayList<FieldType>();
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
	 * @return
	 */
	public String getName(int i) {
		if (i < 0 || i >= ftypes.size()) {
			throw new IllegalArgumentException("No field i in schema: "+this);
		}
		return fnames.get(i);
	}
	
	/**
	 * return a list of the field names of this schema
	 * @return
	 */
	public List<String> getAttrs() {
		return new ArrayList<String>(fnames);
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
	 * return the number of fields in the record type
	 * @return
	 */
	public int size() { return fnames.size(); }
	
	@Override
	public String toString() {
		String s = "["+String.join(", ",  fnames)+"]";
		return s;
	}

}
