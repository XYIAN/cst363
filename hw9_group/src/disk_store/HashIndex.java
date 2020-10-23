package disk_store;


import java.util.List;

/**
 * A hash index.  
 * 
 */

public class HashIndex implements DBIndex {

	/**
	 * Create an new index.
	 */
	public HashIndex() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public List<Integer> lookup(int key) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void insert(int key, int blockNum) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(int key, int blockNum) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public String toString() {
		throw new UnsupportedOperationException();
	}
}
