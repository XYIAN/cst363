package table;

import java.util.List;

/**
 * Kyle Dilbeck 
 * Homework 8 
 * Block.java
 *
 */

public class Block {
	
	static final int BLOCK_SIZE = 4096; // total bytes per block
	
	private Schema schema;
	private byte[] bytes;
	private Bitmap bitmap;
	private int tupleSize;	   // number of bytes per record
	private int bitmapSize;    // number of bytes in bitmap
	private int maxRecs;       // max records per block
	
	/**
	 * Create a new block.
	 * @param schema
	 */
	public Block(Schema schema) {
		this.schema = schema;
		
		// how many records can fit into a block?
		bytes = new byte[BLOCK_SIZE];
		tupleSize = schema.getLen();
		maxRecs = (int)Math.floor((BLOCK_SIZE - 1)/(tupleSize + 1.0/8.0)); 
		
		// create bitmap
		bitmapSize = (int)Math.ceil(maxRecs/8.0);
		bitmap = new Bitmap(bytes, bitmapSize);
		
		// make sure size parameters are okay
		if (tupleSize * maxRecs + bitmapSize > BLOCK_SIZE) {
			throw new RuntimeException("internal block error: bad block size");
		}
	}
	
	/**
	 * Insert a tuple into the block.
	 * @param tuple
	 */
	public void insert(Tuple tuple) {	
		
		// The following is an implementation hint.  Read it only if you want.
		//
		// rough outline of what needs to be done:
		// - using bitmap, see if there is room for another tuple; if not, throw IllegalStateException
		// - if tuple has a primary key, make sure a record with the same values
		//   for the primary key fields is not already in the block
		// - put record into position associated with first 0 in bitmap
		//   - figure out the location in bytes[] where the tuple should go
		//   - serialize the tuple into that location
		//   - make sure to set the proper bit in the bitmap afterward
		
		int index = bitmap.firstZero();
		if(tuple.getSchema().getPrimaryKey() != null) {
			if(index == -1) {
				throw new IllegalStateException();
			}
			for(int i = 0; i < bitmapSize; i++) {
				if(bitmap.getBit(i)  == true) {
					Tuple temp = schema.defaultTuple();
					temp.deserialize(bytes, bitmapSize + i * tupleSize);
					if(equalOnFields(temp,tuple,schema.getPrimaryKey())) {
						throw new IllegalArgumentException();
					}
				}
			}
		}//end primary key check
		int offset = bitmapSize + index * tupleSize;
		tuple.serialize(bytes, offset);
		bitmap.setBit(index, true);
		
	}
	
	/**
	 * Delete all tuples in the block have the given field value.
	 * @param fieldName
	 * @param field
	 */
	public void delete(String fieldName, Field field) {
		
		// The following is an implementation hint.  Read it only if you want.
		//
		// rough outline of what needs to be done:
		// - see if the name of the given field appears in the schema for this block;
		//   is not, throw an IllegalArgumentException
		// - iterate over the bit map to find positions where a bit is set
		//   - for each set bit, determine the position of the record in the block
		//     (the toString() method below may help to understand this)
		//   - deserialize the tuple at that position
		//   - see if the field value of the deserialized tuple and the value of
		//     the 'field' parameter are equal
		//   - if they are equal, the tuple should be deleted, so clear the bit map
		//     at this position
		
		
		
		if (schema.getFieldIndex(fieldName) < 0) {
			throw new IllegalArgumentException("Error: no such field in schema");
		}
		for(int i = 0; i < bitmapSize; i++) {
			//look for bits that are 1(row) 
			if(bitmap.getBit(i)) {
				Tuple temp = schema.defaultTuple(); 
				temp.deserialize(bytes, bitmapSize + i * tupleSize);
				if(temp.get(fieldName).equals(field)){
					//if match
					bitmap.setBit(i, false);
					//creates-sets bit to be free space 
				}
				//
				
				
			}
		}//end for
	}
	
	// return true if tuples t1 and t2 both have values for the given field
	// names, and the values are equal
	private static boolean equalOnFields(Tuple t1, Tuple t2, List<String> fields) {
		for (String fname: fields) {
			Field fieldVal1 = t1.get(fname);
			Field fieldVal2 = t2.get(fname);
			if (fieldVal1 == null || fieldVal2 == null || !fieldVal1.equals(fieldVal2)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Return the number of records in the block.
	 * @return
	 */
	public int size() {
		return bitmap.sum();
	}
	
	public String toString() {
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("[\n");
		
		Tuple tup = schema.defaultTuple();
		for (int i = 0; i < bitmap.size(); i++) {
			if (bitmap.getBit(i)) {
				int recPos = bitmapSize + i*tupleSize;
				tup.deserialize(bytes, recPos);
				sbuf.append(tup+"\n");
			}
		}
		
		sbuf.append("]\n");
		return sbuf.toString();
	}

}
