/*
 * @AUTHOR  : Kyle DILBECK 
 * @TITLE   : hw1(instTable).java
 * @DATE    : 8/27/2020
 * @v       : 1.0.0
 * @ABSTRACT: this program will now correctly look up the given test ID or return null if none. 
 */


package table;

import java.util.ArrayList;
import java.util.List;

public class InstTable {
	
	private List<InstTuple> tuples;
	
	public InstTable() {
		tuples = new ArrayList<InstTuple>();
	}
	
	public int size() {
		return tuples.size();
	}
	
	/**
	 * Insert the given tuple into the table.  Return
	 * false if the key was already present.
	 * @param tuple
	 */
	public boolean insert(InstTuple tuple) {
		int i = keyIndex(tuple.getID());
		if (i >= 0) {
			return false;
		}
		tuples.add(tuple);
		return true;
	}
	
	/**
	 * Delete the tuple having the given ID value from the table.
	 * Return false if no such tuple in the table.
	 * @param ID
	 */
	public boolean delete(String ID) {
		int i = keyIndex(ID);
		if (i >= 0) {
			tuples.remove(i);
			return true;
		}
		return false;		
	}
	
	/**
	 * Return the tuple having the given ID value from the table.
	 * Return null if no such tuple in the table.
	 * @param ID
	 * @return
	 */
	public InstTuple lookup(String ID) {
		//throw new UnsupportedOperationException();
		int j = keyIndex(ID); 
		if(j >= 0 ){
			return tuples.get(j); 
		}
		return null; 
	}
	
	/**
	 * Return all tuples in the table that have the given department name.
	 * @param dept_name
	 * @return
	 */
	public List<InstTuple> lookupByDept(String dept_name) {
		List<InstTuple> result = new ArrayList<InstTuple>();
		for (InstTuple tuple: tuples) {
			if (tuple.getDept_name().equals(dept_name)) {
				result.add(tuple);
			}
		}
		return result;
	}
	
	// return the index of the first tuple with the given ID value;
	// return -1 if no tuple has the given ID value;
	private int keyIndex(String ID) {
		for (int i = 0; i < tuples.size(); i++) {
			if (tuples.get(i).getID().equals(ID)) {
				return i;
			}
		}
		return -1;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (InstTuple tuple: tuples) {
			sb.append(tuple+"\n");
		}
		return sb.toString();
	}
}
