package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import table.InstTable;
import table.InstTuple;

class InstTableTest {

	@Test
	void insertOneTuple() {
		InstTuple tup1 = new InstTuple("12121", "Wu", "Finance", 90000);
		
		InstTable tbl = new InstTable();
		boolean insertSucceeded = tbl.insert(tup1);
		assertTrue(insertSucceeded);
	}
	
	@Test
	void insertDuplicateTuple() {
		InstTuple tup1 = new InstTuple("12121", "Wu", "Finance", 90000);
		InstTuple tup2 = new InstTuple("12121", "Mozart", "Music", 40000);
		
		InstTable tbl = new InstTable();
		tbl.insert(tup1);
		boolean insertSucceeded = tbl.insert(tup2);
		assertTrue(!insertSucceeded);
	}
	
	@Test
	void lookupExistingTuple() {
		InstTuple tup1 = new InstTuple("12121", "Wu", "Finance", 90000);
		InstTuple tup2 = new InstTuple("15151", "Mozart", "Music", 40000);
		
		InstTable tbl = new InstTable();
		tbl.insert(tup1);
		tbl.insert(tup2);
		InstTuple tup = tbl.lookup(tup1.getID());
		assertTrue(tup.getID() == tup1.getID());
	}
	
	@Test
	void lookupMissingTuple() {
		InstTuple tup1 = new InstTuple("12121", "Wu", "Finance", 90000);
		InstTuple tup2 = new InstTuple("15151", "Mozart", "Music", 40000);
		
		InstTable tbl = new InstTable();
		tbl.insert(tup1);
		tbl.insert(tup2);
		InstTuple tup = tbl.lookup("12123");
		assertTrue(tup == null);
	}
	
	@Test
	void lookupDepartment() {
		InstTuple tup1 = new InstTuple("12121", "Wu", "Finance", 90000);
		InstTuple tup2 = new InstTuple("15151", "Mozart", "Music", 40000);	
		InstTuple tup3 = new InstTuple("76543", "Singh", "Finance", 80000);
		
		InstTable tbl = new InstTable();
		tbl.insert(tup1);
		tbl.insert(tup2);
		tbl.insert(tup3);
		assertTrue(tbl.size() == 3);
		
		List<InstTuple> tups = tbl.lookupByDept("Finance");
		assertTrue(tups.size() == 2);
	}
}
