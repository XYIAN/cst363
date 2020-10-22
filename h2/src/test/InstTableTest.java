package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import table.AndCondition;
import table.Condition;
import table.EqNumCondition;
import table.EqStringCondition;
import table.InstTable;
import table.InstTuple;
import table.OrCondition;
import table.Query;
import table.SelectQuery;

class InstTableTest {
	
	private InstTable table;
	private InstTuple oldTup, newTup;
	
	@BeforeEach
	void init() {
		String ids[]   = {"22222",     "12121",    "32343",   "45565",      "98345",      "76766",   "10101"};
		String names[] = {"Einstein",  "Wu",       "El Said", "Katz",       "Kim",        "Crick",   "Srinivasan"};
		String depts[] = {"Physics",   "Finance",  "History", "Comp. Sci.", "Elec. Eng.", "Biology", "Comp. Sci."};
		int salaries[] = {95000,       90000,      60000,     75000,        80000,        72000,     65000};

		table = new InstTable();
		for (int i = 0; i < ids.length; i++) {
			table.insert(new InstTuple(ids[i], names[i], depts[i], salaries[i]));
		}
		
		oldTup = new InstTuple("22222", "Einstein", "Physics", 95000);
		newTup = new InstTuple("11111", "Molina",   "Music",   70000);
	}

	@Test
	void insertOneTuple() {
		// insert should succeed if the key value is not already in the table
		boolean insertSucceeded = table.insert(newTup);
		assertTrue(insertSucceeded);
	}
	
	@Test
	void insertDuplicateTuple() {
		// insert should fail if the key value *is* already in the table
		boolean insertSucceeded = table.insert(oldTup);
		assertTrue(!insertSucceeded);
	}
	
	@Test
	void lookupExistingTuple() {
		// lookup by ID should succeed if the ID is in the table
		InstTuple tup = table.lookup(oldTup.getID());
		assertTrue(tup.getID() == oldTup.getID());
	}
	
	@Test
	void lookupMissingTuple() {
		// lookup by ID should fail if the ID is not in the table
		InstTuple tup = table.lookup(newTup.getID());
		assertTrue(tup == null);
	}
	
	@Test
	void simpleStrQuery() {
		// test a simple string equality query
		Condition cond = new EqStringCondition("dept_name", "Comp. Sci.");
		Query q = new SelectQuery(cond);
		InstTable result = q.eval(table);
		assertTrue(result.size() == 2);
	}
	
	@Test
	void simpleNumQuery() {
		// test a simple numeric equality query
		Condition cond = new EqNumCondition("salary", 72000);
		Query q = new SelectQuery(cond);
		InstTable result = q.eval(table);
		assertTrue(result.size() == 1);
	}
	
	@Test
	void emptyQuery() {
		// test a query that should return an empty table
		Condition cond = new EqNumCondition("salary", 12000);
		Query q = new SelectQuery(cond);
		InstTable result = q.eval(table);
		assertTrue(result.size() == 0);
	}
	
	@Test
	void andQuery() {
		Condition cond = new AndCondition(new EqNumCondition("salary", 72000), new EqStringCondition("name", "Crick")); 
		Query q = new SelectQuery(cond);
		InstTable tbl = q.eval(table);
		assertTrue(tbl.size() == 1);
	}

	@Test
	void orQuery() {
		Condition cond = new OrCondition(new EqNumCondition("salary", 60000), new EqStringCondition("dept_name", "Biology")); 
		Query q = new SelectQuery(cond);
		InstTable tbl = q.eval(table);
		assertTrue(tbl.size() == 2);
	}
	
	@Test
	void badAttrQuery() {
		Condition cond = new EqNumCondition("salarie", 72000);
		Query q = new SelectQuery(cond);
		InstTable result = q.eval(table);
		assertTrue(result == null);
	}
	
	@Test
	void badAttrOrQuery() {
		Condition cond = new OrCondition(new EqNumCondition("salarie", 60000), new EqStringCondition("dept_name", "Biology")); 
		Query q = new SelectQuery(cond);
		InstTable result = q.eval(table);
		assertTrue(result == null);
	}
	
}
