package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
	
	private Table instTable, deptTable;
	private Schema instSchema, deptSchema;
	private Tuple instTuple, deptTuple;
	
	private Tuple newInstTuple(String id, String name, String dept_name, int salary, Schema schema) {
		Field field1 = new VarcharField(id,        (VarcharType)schema.getType(0));
		Field field2 = new VarcharField(name,      (VarcharType)schema.getType(1));
		Field field3 = new VarcharField(dept_name, (VarcharType)schema.getType(2));
		Field field4 = new IntField(salary);
		List<Field> fields = Arrays.asList(field1, field2, field3, field4);
		return new Tuple(fields, schema);
	}
	
	private Tuple newDeptTuple(String deptName, String building, int budget, Schema schema) {
		Field field1 = new VarcharField(deptName,  (VarcharType)schema.getType(0));
		Field field2 = new VarcharField(building,  (VarcharType)schema.getType(1));
		Field field3 = new IntField(budget);
		List<Field> fields = Arrays.asList(field1, field2, field3);
		return new Tuple(fields, schema);
	}
	
	@BeforeEach
	void init() {
		// instructor table
		VarcharType id_type = new VarcharType(5);
		VarcharType name_type = new VarcharType(24);
		VarcharType dept_name_type = new VarcharType(16);
		IntType salary_type = IntType.getInstance();
		
		instSchema = new Schema();
		instSchema.add("ID",  id_type);
		instSchema.add("name", name_type);
		instSchema.add("dept_name", dept_name_type);
		instSchema.add("salary",  salary_type);
				
		instTable = new Table(instSchema);
		
		String ids[]   = {"22222",     "12121",    "32343",   "45565",      "98345",      "76766",   "10101"};
		String names[] = {"Einstein",  "Wu",       "El Said", "Katz",       "Kim",        "Crick",   "Srinivasan"};
		String depts[] = {"Physics",   "Finance",  "History", "Comp. Sci.", "Elec. Eng.", "Biology", "Comp. Sci."};
		int salaries[] = {95000,       90000,      60000,     75000,        80000,        72000,     65000};

		for (int i = 0; i < ids.length; i++) {
			instTable.insert(newInstTuple(ids[i], names[i], depts[i], salaries[i], instSchema));
		}
		
		instTuple = newInstTuple("11111", "Molina",   "Music",   70000, instSchema);
		
		// department table
		VarcharType building_type = new VarcharType(15);
		IntType budget_type = IntType.getInstance();
		
		deptSchema = new Schema();
		deptSchema.add("dept_name",  dept_name_type);
		deptSchema.add("building", building_type);
		deptSchema.add("budget", budget_type);
		deptSchema.setKey(Arrays.asList("dept_name"));
		
		deptTable = new Table(deptSchema);
		
		String deptNames[] = {"Biology", "Comp. Sci.", "Elec. Eng.", "Finance", "History", "Music",   "Physics"};
		String buildings[] = {"Watson",  "Taylor",     "Taylor",     "Painter", "Painter", "Packard", "Watson"};
		int    budgets[]   = {90000,     100000,       85000,        120000,    50000,     80000,     70000};

		for (int i = 0; i < ids.length; i++) {
			deptTable.insert(newDeptTuple(deptNames[i], buildings[i], budgets[i], deptSchema));
		}
		
		deptTuple = newDeptTuple("Psychology", "Packard", 75000, deptSchema);
	}

	@Test
	void insertOneTuple() {
		// insert should succeed if the key value is not already in the table
		boolean insertSucceeded = instTable.insert(instTuple);
		assertTrue(insertSucceeded);
	}
	
	@Test
	void simpleStrQuery() {
		// test a simple string equality query
		Condition cond = new EqCondition("dept_name", "Comp. Sci.");
		Query q = new SelectQuery(cond);
		Table result = q.eval(instTable);
		assertTrue(result.size() == 2);
	}
	
	@Test
	void projectStrQuery() {
		// test a string equality query with projection
		Condition cond = new EqCondition("dept_name", "Comp. Sci.");
		Query q = new SelectQuery(Arrays.asList("name", "dept_name"), cond);
		Table result = q.eval(instTable);
		assertTrue(result.size() == 2 && result.numCols() == 2);
	}
	
	@Test
	void simpleNumQuery() {
		// test a simple numeric equality query
		Condition cond = new EqCondition("salary", 72000);
		Query q = new SelectQuery(cond);
		Table result = q.eval(instTable);
		assertTrue(result.size() == 1);
	}
	
	@Test
	void emptyQuery() {
		// test a query that should return an empty table
		Condition cond = new EqCondition("salary", 12000);
		Query q = new SelectQuery(cond);
		Table result = q.eval(instTable);
		assertTrue(result.size() == 0);
	}
	
	@Test
	void andQuery() {
		Condition cond = new AndCondition(new EqCondition("salary", 72000), new EqCondition("name", "Crick")); 
		Query q = new SelectQuery(cond);
		Table tbl = q.eval(instTable);
		assertTrue(tbl.size() == 1);
	}

	@Test
	void orQuery() {
		Condition cond = new OrCondition(new EqCondition("salary", 60000), new EqCondition("dept_name", "Biology")); 
		Query q = new SelectQuery(cond);
		Table tbl = q.eval(instTable);
		assertTrue(tbl.size() == 2);
	}
	
	@Test
	void badAttrQuery() {
		Condition cond = new EqCondition("salarie", 72000);
		Query q = new SelectQuery(cond);
		Table result = q.eval(instTable);
		assertTrue(result == null);
	}
	
	@Test
	void badAttrOrQuery() {
		Condition cond = new OrCondition(new EqCondition("salarie", 60000), new EqCondition("dept_name", "Biology")); 
		Query q = new SelectQuery(instSchema.getFieldNames(), cond);
		Table result = q.eval(instTable);
		assertTrue(result == null);
	}
	
	@Test
	void setPrimaryKey() {
		List<String> keys1 = Arrays.asList("ID");
		instSchema.setKey(keys1);
		List<String> keys2 = instSchema.getPrimaryKey();
		assertTrue(keys1.containsAll(keys2) && keys2.containsAll(keys1));
	}
	
	@Test
	void singleFieldPrimaryKey() {
		// set primary key
		instSchema.setKey(Arrays.asList("ID"));
		
		// tuple with same ID as Einstein
		Tuple dupTuple = newInstTuple("22222", "Royce", "Physics", 85000, instSchema);
		int n = instTable.size();
		boolean result = instTable.insert(dupTuple);
		assertTrue(!result && instTable.size() == n);
	}
	
	@Test
	void multiFieldPrimaryKey() {
		// set primary key
		instSchema.setKey(Arrays.asList("name", "dept_name"));
		
		// tuple with same name as Einstein, but different dept. name
		Tuple tup1 = newInstTuple("77665", "Einstein", "Biology", 85000, instSchema);
		// tuple with same name and dept. name as Einstein
		Tuple tup2 = newInstTuple("87432", "Einstein", "Biology", 75000, instSchema);
		
		int n = instTable.size();
		boolean result1 = instTable.insert(tup1);
		boolean result2 = instTable.insert(tup2);
		
		assertTrue(result1 && !result2 && instTable.size() == (n+1));
	}
	
	@Disabled
	@Test
	void schemaJoin() {
		Schema joined = Schema.naturalJoin(instSchema, deptSchema);
		assertTrue(joined != null && joined.size() == 6);
	}
	
	@Disabled
	@Test
	void tableJoin() {
		Table joined = Table.naturalJoin(instTable, deptTable);
		System.out.println(joined);
		assertTrue(joined != null && joined.size() == instTable.size());
	}
	

	
}
