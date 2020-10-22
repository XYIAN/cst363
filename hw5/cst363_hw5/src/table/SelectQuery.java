package table;

import java.util.Iterator;
import java.util.List;

/**
 * A simple select query of the form:
 *    select attrs from table on condition
 * 
 * @author Glenn
 *
 */

public class SelectQuery extends Query {
	
	private Condition cond;
	private List<String> attrs;	   // a value of null means all attributes of the table
	
	// select attrs where cond
	public SelectQuery(List<String> attrs, Condition cond) {
		this.attrs = attrs;
		this.cond = cond;
	}
	
	// select * where cond
	public SelectQuery(Condition cond) {
		this(null, cond);
	}
	
	/**
	 * Return the schema of the result of this query, given the
	 * schema of the table to which the query is applied
	 * @return
	 */
	public Schema resultSchema(Schema inSchema) {
		return inSchema;
	}
	
	@Override
	public Table eval(Table table) {
		Table result = new Table(table.getSchema());
		// if a tuple of table satisfies the condition, add it to the result table
		for (Tuple tuple: table) {
			Boolean b = cond.eval(tuple);
			if (b == null) {
				return null;
			}
			if (b) {
				result.insert(tuple);
			}
		}
		
		List<String> proj_attrs = attrs == null ? table.getSchema().getFieldNames() : attrs;
		Table projected = result.project(proj_attrs);
		
		return projected;
	}

	@Override
	public String toString() {
	    String proj_attrs = "*";
	    if (attrs != null) {
	    	proj_attrs = String.join(",", attrs);
	    }
	    return "select "+proj_attrs+" where "+cond;
	}

}
