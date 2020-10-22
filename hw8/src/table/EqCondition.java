package table;

/**
 * A condition of the form attr = value, where attr is a
 * numeric attribute, and value is a numeric constant.
 * 
 * @author Glenn
 *
 */

public class EqCondition extends Condition {
	
	private String attr;
	private Field value;
	
	public EqCondition(String attr, Field value) {
		this.attr = attr;
		this.value = value;
	}
	
	// convenience method for equality test with varchar
	public EqCondition(String attr, String value) {
		this(attr, new VarcharField(value, new VarcharType()));
	}
	
	// convenience method for equality test with int
	public EqCondition(String attr, int value) {
		this(attr, new IntField(value));
	}

	@Override
	public Boolean eval(Tuple tuple) {
		Field field = tuple.get(attr);
		if (field == null) {
			return null;
		}
		return field.equals(value);
	}
	
	@Override
	public String toString() {
		return attr+" = "+value;
	}
}
