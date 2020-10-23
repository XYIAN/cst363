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
//	 public static void binarySearch(ArrayList<Entry>arr[], int first, int last, int key){  
//		   int mid = (first + last)/2;  
//		   while( first <= last ){  
//		      if ( arr.get(mid).key < key ){  
//		        first = mid + 1;     
//		      }else if ( arr.indexOf(mid) == key ){  
//		        System.out.println("Element is found at index: " + mid);  
//		        break;  
//		      }else{  
//		         last = mid - 1;  
//		      }  
//		      mid = (first + last)/2;  
//		   }  
//		   if ( first > last ){  
//		      System.out.println("Element is not found!");  
//		   }  
//		 }   

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
		
//		BS
		if(lookup(key) == entries.get(key)) {
			
		}
		//ENTRIES.ADD(index,value)
//		}//END FOR 
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
            			entries.remove(blockNum);
            			if(x.count > 1) {
            				x.count--;
            				size--;
            			}
            			else {
            				
            			}
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
	}
}