package table;

import java.nio.ByteBuffer;

public abstract class Field {
	
	protected FieldType type;
	
	/**
	 * write the field as a sequence of bytes to position index in the given array
	 * @param buf a ByteBuffer
	 * @param index
	 */
    public abstract void serialize(ByteBuffer buf, int index);
    
	/**
	 * Set the field value using the value in the buffer at position index
	 * @param buf a ByteBuffer
	 * @param index location of the bytes for the field in buf
	 */
	public abstract void deserialize(ByteBuffer buf, int index);
	
	/**
	 * return the type of the field
	 * @return
	 */
	public FieldType getType() { return type; }
	
	public abstract boolean equals(Object obj);
	
    public abstract String toString();
}
