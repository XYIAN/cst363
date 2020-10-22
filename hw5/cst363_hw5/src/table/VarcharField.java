package table;

public class VarcharField extends Field {
	
	private String s;
	
	public VarcharField(String s, VarcharType st) {
		if (s.length() > st.maxChars()) {
			throw new IllegalArgumentException("string exceeds legal max length of "+st.maxChars());
		}
		this.s = s;
		this.type = st;
	}
	
	public String getValue() { return s; }

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof VarcharField)) {
			return false;
		}
		return s.equals(((VarcharField)obj).s);
	}

	@Override
	public String toString() {
		return s;
	}


}
