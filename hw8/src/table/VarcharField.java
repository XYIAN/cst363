package table;

import java.nio.ByteBuffer;

public class VarcharField extends Field {
	
	private String s;
	
	public VarcharField(String s, VarcharType st) {
		if (s.length() > st.maxChars()) {
			throw new IllegalArgumentException("string exceeds legal max length of "+st.maxChars());
		}
		this.s = s;
		this.type = st;
	}
	
	// a convenient constructor
	public VarcharField(String s) {
		this(s, new VarcharType(s.length()));
	}
	
	/**
	 * Return the value of this field.
	 * @return
	 */
	public String getValue() { return s; }
	
	@Override
	public void serialize(ByteBuffer buf, int index) {
		StringUtils.serializeString(s, buf, index);
	}

	@Override
	public void deserialize(ByteBuffer buf, int index) {
		s = StringUtils.deserializeString(buf, index);
	}

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
