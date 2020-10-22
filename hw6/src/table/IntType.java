package table;

// All IntType objects are the same, so IntType is a singleton

public class IntType extends FieldType {
	
	private IntType() {}
	
	private static final IntType instance = new IntType();
	
	public static IntType getInstance() {
		return instance;
	}

	@Override
	public String toString() {
		return "int";
	}

}
