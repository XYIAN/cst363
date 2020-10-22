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
		List<String> key = schema.getPrimaryKey();
		if (key != null) {
			for (Tuple tup: tuples) {
				boolean someFieldDiffers = false;
				for (String keyField : key) {
					if (!tuple.get(keyField).equals(tup.get(keyField))) {
						someFieldDiffers = true;
						break;
					}
				}
				if (!someFieldDiffers) {
					return false;
				}
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
		
		// outline:
		// - compute schema of result
		//   - need to make sure that shared attributes have the same types in the two schema
		// - for all matching tuples tup1, tup2 of table1, table2:
		//   - create a tuple that has shared attributes plus non-shared attributes
		//     - in other words, all attributes of first tuple; non-shared attributes of second tuple
		//   - add tuple to result
				
		Schema resultSchema = Schema.naturalJoin(table1.schema, table2.schema);
		if (resultSchema == null) {
			return null;
		}
		List<String> resultFnames = resultSchema.getFieldNames();
		Table result = new Table(resultSchema);

		Set<String> sharedFields = table1.schema.sharedFields(table2.schema);
		for (Tuple tup1 : table1.tuples) {
			for (Tuple tup2 : table2.tuples) {
				boolean matchedSoFar = true;
				for (String field: sharedFields) {
					if (!tup1.get(field).equals(tup2.get(field))) {
						matchedSoFar = false;
						break;
					}
				}
				if (matchedSoFar) {
					List<Field> resultFields = new ArrayList<>();
					int schemaIndex = 0;
					for (int i = 0; i < tup1.size(); i++) {
						if (table1.schema.getName(i).equals(resultFnames.get(schemaIndex))) {
							resultFields.add(tup1.get(i));
							schemaIndex++;
						}
					}
					for (int i = 0; i < tup2.size(); i++) {
						if (table2.schema.getName(i).equals(resultFnames.get(schemaIndex))) {
							resultFields.add(tup2.get(i));
							schemaIndex++;
						}
					}
					result.insert(new Tuple(resultFields, resultSchema));
				}
			}
		}
		
		return result;
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
