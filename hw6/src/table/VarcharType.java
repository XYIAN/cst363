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
	public String toString() {
		return "varchar("+maxChars+")";
	}
}
