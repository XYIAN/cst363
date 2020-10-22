package table;

/**
 * A database query.  Note that a query on a table gives a table.
 * So a query can be thought of as a function that maps a table
 * to a table.
 * 
 * @author Glenn
 *
 */
public abstract class Query {
	
	/**
	 * Evaluate this query on the given table.  If all attributes
	 * in the query exist in the table, then return a table
	 * containing the result of the table.  If the query contains
	 * any attribute not in the table, return null.
	 * @param table
	 * @return
	 */
	public abstract Table eval(Table table);
}
