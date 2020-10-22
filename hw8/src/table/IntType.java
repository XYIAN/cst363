package table;

// All IntType objects are the same, so IntType is a singleton

public class IntType extends FieldType {
	
	private IntType() {}
	
	private static final IntType instance = new IntType();
	
	public static IntType getInstance() {
		return instance;
	}
	
	@Override
	public int getLen() {
		return Integer.BYTES;
	}
	
	@Override
	public Field defaultField() {
		return new IntField(0);
	}
	
	@Override
	public boolean equals(Object obj) {
		// IntType is a singleton
		return obj == instance;
	}

	@Override
	public String toString() {
		return "int";
	}
}
