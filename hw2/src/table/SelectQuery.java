package table;

import java.util.Iterator;
import java.util.List;

/**
 * A simple select query of the form:
 *    select * from table on condition
 * 
 * @author Glenn
 *
 */

public class SelectQuery extends Query {
	
	private Condition cond;
	
	public SelectQuery(Condition cond) {
		this.cond = cond;
	}
	
	@Override
	public InstTable eval(InstTable table) {
		// YOUR CODE HERE
		// replace the following line with your code
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@Override
	public String toString() {
		return "select * where "+cond;
	}

}
