package table;

/**
 * A condition of the form cond1 and cond2, where cond1 and cond2
 * are conditions.
 * 
 * @author Glenn
 *
 */

public class AndCondition extends Condition {

	private Condition cond1, cond2;
	
	public AndCondition(Condition cond1, Condition cond2) {
		this.cond1 = cond1;
		this.cond2 = cond2;
	}
	
	@Override
	public Boolean eval(InstTuple tuple) {
		Boolean b1 = cond1.eval(tuple);
		Boolean b2 = cond2.eval(tuple);
		if (b1 == null || b2 == null) {
			return null;
		}
		return b1 && b2;
	}
	
	@Override
	public String toString() {
		return cond1+" and "+cond2;
	}
}
