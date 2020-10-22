package table;

public class IntField extends Field {
	
	private int i;
	
	public IntField(int i) {
		this.i = i;
		this.type = IntType.getInstance();
	}
	
	public int getValue() { return i; }
	
	public void setValue(int i) { this.i = i; }

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof IntField)) {
			return false;
		}
		return i == ((IntField)obj).i;
	}

	@Override
	public String toString() {
		return Integer.toString(i);
	}
}
