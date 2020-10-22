package table;

/**
 * A bitmap, implemented as a byte array.
 * 
 * Note that java bytes are signed, so values range
 * from -128 to 127.  A byte of all 1's is -0x01.
 * 
 * Note also that Byte.SIZE gives the number of bits in a byte.
 *
 */

public class Bitmap {
	private byte[] bytes;
	private int numBytes;
	private final byte ONES = -0x01;	// 8 bits of 1s
	
	/**
	 * Create a new bitmap.  The numBytes argument is provided
	 * because some applications may not want to use the entire
	 * byte array for the bitmap.
	 * @param bytes
	 * @param numBytes
	 */
	public Bitmap(byte[] bytes, int numBytes) {
		this.bytes = bytes;
		this.numBytes = numBytes;
	}
	
	/**
	 * An alternative constructor when all bits in the byte array are to be used.
	 * @param bytes
	 */
	public Bitmap(byte[] bytes) {
		this(bytes, bytes.length);
	}
	
	/**
	 * Return the number of bits in this bitmap. 
	 * @return
	 */
	public int size() { return numBytes * Byte.SIZE; }
	
	/**
	 * Set all bits in the bitmap to 0.
	 */
	public void clear() {
		for (int i = 0; i < numBytes; i++) {
			bytes[i] = 0;
		}
	}
	
	/**
	 * Return the count of the number of 1 bits.
	 * @return
	 */
	public int sum() {
		int s = 0;
		for (int i = 0; i < size(); i++) {
			s += getBit(i) ? 1 : 0;
		}
		return s;
	}
	
	/**
	 * Return true if the ith bit is 1, else return false.
	 * @param i
	 * @return
	 */
	public boolean getBit(int i) {
		// ib is in index of the byte that contains the ith bit
		int ib = i / Byte.SIZE;
		if (ib >= numBytes) {
			throw new IllegalArgumentException("getting bit larger than bit map (byte number is "+ib+")");
		}
		
		// get the bit we need from that byte
		return getBit(bytes[ib], i - ib*Byte.SIZE);
	}
	
	/**
	 * Set the ith bit to 1 (if bit is true) or 0 (if not)
	 * @param i
	 * @param bit
	 */
	public void setBit(int i, boolean bit) {
		// ib is in index of the byte that contains the ith bit
		int ib = i / Byte.SIZE;
		if (ib >= numBytes) {
			throw new IllegalArgumentException("bit i = "+i+" is larger than "+ib);
		}
		
		// set the bit we need with that byte, and update buffer
		bytes[ib] = setBit(bytes[ib], i - ib*Byte.SIZE, bit);
	}
	
	// return true if the ith bit of b is 1, else return false
	private boolean getBit(byte b, int i) {
		return ((b >> (7 - i)) & 1) != 0;
	}
	
	// return a byte like b except with bit i set to 1 (if bit is true) or 0 (if not)
	private byte setBit(byte b, int i, boolean bit) {
		if (bit) {
			b |= (1 << (7-i));
		} else {
			b &= ~(1 << (7-i));
		}
		return b;
	}
	
	/**
	 * return the index of the first bit that is 0, else return -1 is not bit is 0
	 * @return
	 */
	public int firstZero() {
		for (int i = 0; i < numBytes; i++) {
			if (bytes[i] != ONES) {
				// some block is free; find the index of the first zero bit
				for (int j = 0; j < Byte.SIZE; j++) {
					if (!getBit(bytes[i], j)) {
						return i*Byte.SIZE + j;
					}
				}
				throw new IllegalStateException("no zero bit found");
			}
		}
		return -1;
	}
		
	/** 
	 * Return the bitmap as a string
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size(); i++) {
			if (i > 0 && i % Byte.SIZE == 0) {
				sb.append(" ");
			}
			sb.append(getBit(i) ? "1" : "0");
		}
		return sb.toString();
	}
}
