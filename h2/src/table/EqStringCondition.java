package table;

/**
 * A condition of the form attr = value, where attr is a string attribute
 * and value is a string constant.
 * 
 * @author Glenn
 *
 */

public class EqStringCondition extends Condition {
	
	private String attr;
	private String value;
	
	public EqStringCondition(String attr, String value) {
		this.attr = attr;
		this.value = value;
	}

	@Override
	public Boolean eval(InstTuple tuple) {
		String s = tuple.getStringAttr(attr);
		if (s == null) {
			return null;
		}
		return s.equals(value);
	}
	
	@Override
	public String toString() {
		return attr+" = "+value;
	}
}
