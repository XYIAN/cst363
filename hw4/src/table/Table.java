package table;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * An instructor table, implemented in-memory as a simple list of rows.
 * 
 * @author Glenn
 *
 */

public class Table implements Iterable<Tuple> {
	
	private List<Tuple> tuples;
	private Schema schema;
	
	public Table(Schema schema) {
		this.schema = schema;
		tuples = new ArrayList<>();
	}
	
	public int size() {
		return tuples.size();
	}
	
	public Schema getSchema() {
		return schema;
	}
	
	public int numCols() {
		return schema.size();
	}
	
	/**
	 * Insert the given tuple into the table.  Return false if inserting
	 * the tuple would result in an integrity constraint violation.
	 * @param tuple
	 */
	public boolean insert(Tuple tuple) {
		// get the column name(s) that make up the primary key
		List<String> primaryKey = schema.getPrimaryKey();
		if (primaryKey == null ) {
			tuples.add(tuple);
			return true;
		}
		// YOUR CODE HERE
		
		// for each tuple t in the table
		// check if the primary key values of tuple equal the 
		// primary key of t,  if so, this is duplicate key. return false
		
		String [] pkeyV = new String [primaryKey.size()];
		for(int i = 0; i < pkeyV.length; i++) {
			String columnName = primaryKey.get(i);
			VarcharField value = (VarcharField)tuple.get(columnName);
			pkeyV[i] = value.getValue();
		}
		
		for (Tuple t: this) {
			// t is one row in the table
			
			// for each colName in primaryKey
			boolean flag = false;
			for(String k : primaryKey) {
			if (!t.get(k).equals(tuple.get(k))){
				flag = true;
				break;
			}
		}
		if(!flag) {
			return false;
		}
	  }
		tuples.add(tuple);
		return true;
	}
	
    /**
     * Return the result of a project of this table on the given attributes
     * @param attrs
     * @return
     */
	public Table project(List<String> attrs) {
		Schema proj_schema = schema.project(attrs);
		Table result = new Table(proj_schema);
		for (Tuple tup: tuples) {
			result.insert(new Tuple(tup.project(attrs), proj_schema));
		}
		return result;
	}
	
	/**
	 * Return the result of the natural join of table1 and table2
	 * @param table1
	 * @param table2
	 * @return
	 */
	public static Table naturalJoin(Table table1, Table table2) {
		throw new UnsupportedOperationException("Natural join not yet implemented.");
	}

	/**
	 * Iterate over all the tuples in this table.
	 */
	public Iterator<Tuple> iterator() {
		return tuples.iterator();
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Tuple tuple: tuples) {
			sb.append(tuple+"\n");
		}
		return sb.toString();
	}

}
