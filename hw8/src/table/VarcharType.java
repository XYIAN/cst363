package table;

public class VarcharType extends FieldType {
	
	// maximum allowed string length
	public static final int maxCharLimit = 64;
	public static final int defaultCharLimit = 16;
	private int maxChars;
	
	public VarcharType(int maxChars) {
		if (maxChars == 0) {
			throw new IllegalArgumentException("a VarcharType must allow for more than 0 characters");
		}
		if (maxChars > maxCharLimit) {
			throw new IllegalArgumentException("max Varchar size is "+maxCharLimit+" characters");
		}
		this.maxChars = maxChars;
	}
	
	public VarcharType() {
		this(defaultCharLimit);
	}
	
	public int maxChars() { return maxChars; }
	
	@Override
	public int getLen() {
		// on-disk representation requires maxChar bytes, plus 4 bytes to store string length as int
		return maxChars + Integer.BYTES;
	}
	
	@Override
	public boolean equals(Object obj) {
		if ((obj instanceof VarcharType)) {
			return(maxChars == ((VarcharType)obj).maxChars);
		}
		return false;
	}

	@Override
	public Field defaultField() {
		return new VarcharField("", this);
	}

	@Override
	public String toString() {
		return "varchar("+maxChars+")";
	}

}
