package table;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * An instructor table, implemented in-memory as a simple list of rows.
 * 
 * @author Glenn
 *
 */

public class InstTable implements Iterable<InstTuple> {
	
	private List<InstTuple> tuples;
	
	public InstTable() {
		tuples = new ArrayList<>();
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
		for (InstTuple tuple: tuples) {
			if (tuple.getID().equals(ID)) {
				return tuple;
			}
		}
		return null;
	}
	
	/**
	 * Iterate over all the tuples in this table.
	 */
	public Iterator<InstTuple> iterator() {
		return tuples.iterator();
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
