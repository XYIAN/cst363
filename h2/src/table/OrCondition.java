package table;

/*
 * @AUTHOR Kyle Dilbeck
 * @cst363 
 * @v1.2.0
 * @date: 932020
 */

public class OrCondition extends Condition {

	private Condition cond1, cond2;
	
	public OrCondition(Condition cond1, Condition cond2) {
		this.cond1 = cond1;
		this.cond2 = cond2;
	}
	
	@Override
	public Boolean eval(InstTuple tuple) {
		// YOUR CODE HERE
		// replace the following line with your code
		//comparing cond strings?(both or one)
		//return null or false?
		//
		Boolean b1 = cond1.eval(tuple);
		Boolean b2 = cond2.eval(tuple);
		if (b1 == null || b2 == null) {
			return null;
		}
		return (b1 || b2);
	}
	
	@Override
	public String toString() {
		return cond1+" and "+cond2;
	}
}
