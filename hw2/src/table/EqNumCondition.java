package table;

/**
 * A condition of the form attr = value, where attr is a
 * numeric attribute, and value is a numeric constant.
 * 
 * @author Glenn
 *
 */

public class EqNumCondition extends Condition {
	
	private String attr;
	private int value;
	
	public EqNumCondition(String attr, int value) {
		this.attr = attr;
		this.value = value;
	}

	@Override
	public Boolean eval(InstTuple tuple) {
		// YOUR CODE HERE
		// replace the following line with your code
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	@Override
	public String toString() {
		return attr+" = "+value;
	}
}
