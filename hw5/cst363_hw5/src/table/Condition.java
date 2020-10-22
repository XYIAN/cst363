package table;

public abstract class Condition {
	
	/**
	 * Evaluate this condition on a tuple.  Return null if
	 * the condition is not defined on the tuple, otherwise
	 * return true if the condition holds of the tuple, and
	 * false if it doesn't.
	 * @param tuple
	 * @return
	 */
	public abstract Boolean eval(Tuple tuple);

}
