package table;

/**
 * A type, which can be a field type or a compound type
 * @author Glenn
 *
 */
public abstract class Type {
	// return the number of bytes requires to serialize an object of this type
	public abstract int getLen();
	
    public abstract String toString();
}
