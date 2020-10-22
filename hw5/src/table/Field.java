package table;

import java.nio.ByteBuffer;

public abstract class Field {
	
	protected Type type;
	
	/**
	 * return the type of the field
	 * @return
	 */
	public Type getType() { return type; }
	
	public abstract boolean equals(Object obj);
	
    public abstract String toString();
}
