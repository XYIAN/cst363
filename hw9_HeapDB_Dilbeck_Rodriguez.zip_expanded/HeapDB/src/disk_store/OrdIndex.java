package disk_store;

import java.util.ArrayList;
import java.util.List;

/**
 * An ordered index.  Duplicate search key values are allowed,
 * but not duplicate index table entries.  In DB terminology, a
 * search key is not a superkey.
 * 
 * A limitation of this class is that only single integer search
 * keys are supported.
 *
 */


public class OrdIndex implements DBIndex {
	
	private class Entry {
		int key;
		ArrayList<BlockCount> blocks;//list of block nums 
	}
	
	private class BlockCount {
		int blockNo;
		int count;
	}
	
	ArrayList<Entry> entries;//index for blocks 
	int size = 0;//size of index
	
	/**
	 * Create an new ordered index.
	 */
	public OrdIndex() {
		entries = new ArrayList<>();//creating new index cons 
	}
	
	@Override
	public List<Integer> lookup(int key) {//list<int> block numbers that contain row index
		// binary search of entries arraylist
		// return list of block numbers (no duplicates). 
		// if key not found, return empty list
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void insert(int key, int blockNum) {
		throw new UnsupportedOperationException();
		//create new entry into array list 
	}

	@Override
	public void delete(int key, int blockNum) {
		// lookup key 
		//  if key not found, should not occur.  Ignore it.
		//  decrement count for blockNum.
		//  if count is now 0, remove the blockNum.
		//  if there are no block number for this key, remove the key entry.
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Return the number of entries in the index
	 * @return
	 */
	public int size() {
		return size;
		// you may find it useful to implement this
		
	}
	
	@Override
	public String toString() {
		throw new UnsupportedOperationException();
	}
}