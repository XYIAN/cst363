package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import table.AndCondition;
import table.Condition;
import table.EqCondition;
import table.Field;
import table.FieldType;
import table.IntField;
import table.IntType;
import table.Table;
import table.Tuple;
import table.VarcharField;
import table.OrCondition;
import table.Query;
import table.Schema;
import table.SelectQuery;
import table.VarcharType;

class TableTest {
	
	private Table table;
	private Schema schema;
	private Tuple newTup;
	
	private Tuple newInstTuple(String id, String name, String dept_name, int salary, Schema schema) {
		Field field1 = new VarcharField(id,        (VarcharType)schema.getType(0));
		Field field2 = new VarcharField(name,      (VarcharType)schema.getType(1));
		Field field3 = new VarcharField(dept_name, (VarcharType)schema.getType(2));
		Field field4 = new IntField(salary);
		List<Field> fields = Arrays.asList(field1, field2, field3, field4);
		return new Tuple(fields, schema);
	}
	
	@BeforeEach
	void init() {
		VarcharType id_type = new VarcharType(5);
		VarcharType name_type = new VarcharType(24);
		VarcharType dept_name_type = new VarcharType(16);
		IntType salary_type = IntType.getInstance();
		
		schema = new Schema();
		schema.add("ID",  id_type);
		schema.add("name", name_type);
		schema.add("dept_name", dept_name_type);
		schema.add("salary",  salary_type);
		
		table = new Table(schema);
		
		String ids[]   = {"22222",     "12121",    "32343",   "45565",      "98345",      "76766",   "10101"};
		String names[] = {"Einstein",  "Wu",       "El Said", "Katz",       "Kim",        "Crick",   "Srinivasan"};
		String depts[] = {"Physics",   "Finance",  "History", "Comp. Sci.", "Elec. Eng.", "Biology", "Comp. Sci."};
		int salaries[] = {95000,       90000,      60000,     75000,        80000,        72000,     65000};

		for (int i = 0; i < ids.length; i++) {
			table.insert(newInstTuple(ids[i], names[i], depts[i], salaries[i], schema));
		}
		
		newInstTuple("22222", "Einstein", "Physics", 95000, schema);
		newTup = newInstTuple("11111", "Molina",   "Music",   70000, schema);
	}

	@Test
	void insertOneTuple() {
		// insert should succeed if the key value is not already in the table
		boolean insertSucceeded = table.insert(newTup);
		assertTrue(insertSucceeded);
	}
	
	@Test
	void simpleStrQuery() {
		// test a simple string equality query
		Condition cond = new EqCondition("dept_name", "Comp. Sci.");
		Query q = new SelectQuery(cond);
		Table result = q.eval(table);
		assertTrue(result.size() == 2);
	}
	
	@Test
	void projectStrQuery() {
		// test a string equality query with projection
		Condition cond = new EqCondition("dept_name", "Comp. Sci.");
		Query q = new SelectQuery(Arrays.asList("name", "dept_name"), cond);
		Table result = q.eval(table);
		assertTrue(result.size() == 2 && result.numCols() == 2);
	}
	
	@Test
	void simpleNumQuery() {
		// test a simple numeric equality query
		Condition cond = new EqCondition("salary", 72000);
		Query q = new SelectQuery(cond);
		Table result = q.eval(table);
		assertTrue(result.size() == 1);
	}
	
	@Test
	void emptyQuery() {
		// test a query that should return an empty table
		Condition cond = new EqCondition("salary", 12000);
		Query q = new SelectQuery(cond);
		Table result = q.eval(table);
		assertTrue(result.size() == 0);
	}
	
	@Test
	void andQuery() {
		Condition cond = new AndCondition(new EqCondition("salary", 72000), new EqCondition("name", "Crick")); 
		Query q = new SelectQuery(cond);
		Table tbl = q.eval(table);
		assertTrue(tbl.size() == 1);
	}

	@Test
	void orQuery() {
		Condition cond = new OrCondition(new EqCondition("salary", 60000), new EqCondition("dept_name", "Biology")); 
		Query q = new SelectQuery(cond);
		Table tbl = q.eval(table);
		assertTrue(tbl.size() == 2);
	}
	
	@Test
	void badAttrQuery() {
		Condition cond = new EqCondition("salarie", 72000);
		Query q = new SelectQuery(cond);
		Table result = q.eval(table);
		assertTrue(result == null);
	}
	
	@Test
	void badAttrOrQuery() {
		Condition cond = new OrCondition(new EqCondition("salarie", 60000), new EqCondition("dept_name", "Biology")); 
		Query q = new SelectQuery(schema.getAttrs(), cond);
		Table result = q.eval(table);
		assertTrue(result == null);
	}
	
}
