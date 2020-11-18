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
		List<Integer> result = new ArrayList<>();
		int l = 0, r = entries.size() - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            // Check if key is present at mid
            if (entries.get(m).key == key) {
            	for(BlockCount x : entries.get(m).blocks) {            		
            		result.add(x.blockNo);
            	}
            }
            // If key greater, ignore left half
            if (entries.get(m).key < key)
                l = m + 1;
            // If key is smaller, ignore right half
            else
                r = m - 1;
        }
        // if we reach here, then element was
        // not present
        return result; //returns empty arrayList of integers
		//throw new UnsupportedOperationException();
	}
	
	@Override
	public void insert(int key, int blockNum) {
		//throw new UnsupportedOperationException();
		List<Integer> found = lookup(key);
		boolean check = true; // to check if key doesnt exist
		int index = -1;
//		if(found.isEmpty()) {
//			return;
//		}
		int l = 0, r = entries.size() - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            // Check if key is present at mid
            if (entries.get(m).key == key) {
            	for(BlockCount x : entries.get(m).blocks) {
            		if(x.blockNo == blockNum) {
            			x.count++;
            			return;
            		}
            		BlockCount temp = new BlockCount();
            		temp.count = 1;
            		temp.blockNo = blockNum;
            		entries.get(m).blocks.add(temp);
            		return;
            	}
            }
            // If key greater, ignore left half
            if (entries.get(m).key < key)
                l = m + 1;
            // If key is smaller, ignore right half
            else
                r = m - 1;
        }
        //This is the new binary search to find the position where the new entry goes---------------------------------------NEW BINARY SEARCH FOR NEW ENTRY
//        int left = 0, right = entries.size() - 1;
//        while (left <= right) {
//            int middle = left + (right - left) / 2;
//           // check if key doesnt exist
//            if(entries.get(middle).key != key) {
//            	//find out where to put it
//            	index = middle;
//            	check = false;
//            }
//            // If key greater, ignore left half
//            if (entries.get(middle).key < key)
//                left = middle + 1;
//            // If key is smaller, ignore right half
//            else
//                right = middle - 1;
//        }
        //create new entrie
        //if(!check) {
        	Entry tempEntry = new Entry();
    		tempEntry.key = key;
    		tempEntry.blocks = new ArrayList<>();
        	BlockCount temp2 = new BlockCount();
        	temp2.count = 1;
        	temp2.blockNo = blockNum;
        	tempEntry.blocks.add(temp2);
        	entries.add(l,tempEntry);
        //}
	}
	@Override
	public void delete(int key, int blockNum) {
		// lookup key
		List<Integer> found = lookup(key);
		if(found.isEmpty()) {
			return;
		}
		int l = 0, r = entries.size() - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            // Check if key is present at mid
            if (entries.get(m).key == key) {
            	for(BlockCount x : entries.get(m).blocks) {            		
            		if(x.blockNo == blockNum) {
            			x.count--;
            			size--; 
            			if(x.count == 0 ) {
            				//size--;
            				entries.get(m).blocks.remove(x); 
            				
            			}
            			if(entries.get(m).blocks.size() == 0) {
            				entries.remove(entries.get(m));
            			}
            			return; 
            		}
            	}
            }
            // If key greater, ignore left half
            if (entries.get(m).key < key)
                l = m + 1;
            // If key is smaller, ignore right half
            else
                r = m - 1;
        }
		
		//  if key not found, should not occur.  Ignore it.
		//  decrement count for blockNum.
		//  if count is now 0, remove the blockNum.
		//  if there are no block number for this key, remove the key entry.
		//throw new UnsupportedOperationException();
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
		//return "["+blockNum+","+count+"]";

	}
}