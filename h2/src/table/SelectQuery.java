package table;

import java.util.Iterator;
import java.util.List;

/*
 * @AUTHOR Kyle Dilbeck
 * @cst363 
 * @v1.2.0
 * @date: 932020
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
		InstTable result = new InstTable();
		for(InstTuple row : table) {
			Boolean temp = cond.eval(row);
			if (temp == null) {
				return null;
			}
			if(temp) {
				result.insert(row);
			}
		}
		
		return result;
	}

	@Override
	public String toString() {
		return "select * where "+cond;
	}

}
