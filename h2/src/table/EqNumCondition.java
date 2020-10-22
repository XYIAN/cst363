package table;

/*
 * @AUTHOR Kyle Dilbeck
 * @cst363 
 * @v1.2.0
 * @date: 932020
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
		//what to check int(s) for 
		Integer i = tuple.getIntAttr(attr);
		if (i == null) {
			return null;
		}
		return i == value;
	}
	
	@Override
	public String toString() {
		return attr+" = "+value;
	}
}
