package dw;

import java.sql.*;
import java.text.SimpleDateFormat;

import org.h2.api.Trigger;

public class AuditGradeTrigger implements Trigger {
	
	/* 
	   uses courses-dll.sql and courses-small.sql to 
	   create tables for university database.
	   
	   create table grade_audit (
	   id varchar(5), 
	   course_id varchar(8),
	   sec_id varchar(8),
	   semester varchar(6),
	   year numeric(4,0),
	   grade varchar(2),
	   time_of varchar(10));
	   
	   create trigger demotrigger 
	       before insert on takes for each row call "dw.AuditGradeTrigger"; 
	   
	   create trigger demotrigger_u 
	       before update on takes for each row call "dw.AuditGradeTrigger";
	 */

	// takes (ID , course_id, sec_id, semester, year, grade)
	// when grade is inserted or deleted from the takes table,
	// or updated,  then if the grade is failing, then 
	// a row is inserted into the grade_audit table.
	
	// if a failing grade is later updated to passing, 
	// the row in grade_audit is deleted.
	// if a passing grade is later updated to failing, 
	// the row in grade_audit is inserted.
	
	// grade_audit table is list of Ds and Fs
  
  // array index starting at 0 for columns in Object[] for method 'fire'
	private static final int ID = 0;
	private static final int COURSE_ID = 1;
	private static final int SEC_ID = 2;
	private static final int SEMESTER = 3;
	private static final int YEAR = 4;
	private static final int GRADE = 5;
  
  private static final String insert_sql = 
         "insert into grade_audit (id, course_id, sec_id, semester, year, grade, time_of) "+
         " values(?, ?, ?, ?, ?, ?, ? )";
         
  private static final String delete_sql = "delete from grade_audit where id=? and course_id=?";
	
  private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

	// grade_audit (ID , course_id, sec_id, semester, year, grade, time_of varchar(25))

	@Override
	public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {
		System.out.println("AuditGradeTrigger fire called.");
		if (oldRow == null) {
			System.out.println("oldRow null");
		} else {
			System.out.println("oldRow size is "+oldRow.length);
		}
		if (newRow == null) {
			System.out.println("newRow null");
		} else {
			System.out.println("newRow size is "+newRow.length);
		}
		try {
			if ("F".equals(newRow[GRADE])) {
				// student fails course, insert row to audit table
				String currentDate = dateFormatter.format(new java.util.Date());
				
				PreparedStatement insert_stmt = conn.prepareStatement(insert_sql);
        // sql column index starts at 1, not 0
				insert_stmt.setObject(1, newRow[ID]);
				insert_stmt.setObject(2, newRow[COURSE_ID]);
				insert_stmt.setObject(3, newRow[SEC_ID]);
				insert_stmt.setObject(4, newRow[SEMESTER]);
				insert_stmt.setObject(5, newRow[YEAR]);
				insert_stmt.setObject(6, newRow[GRADE]);
				insert_stmt.setString(7, currentDate);
				long num_rows = insert_stmt.executeUpdate();
				System.out.println("AuditGradeTrigger insert num_rows=" + num_rows);  // for debug 

			} else {
				// when student passes course, remove any audit rows for student, course
				
				PreparedStatement delete_stmt = conn.prepareStatement(delete_sql);
				delete_stmt.setObject(1, newRow[ID]);
				delete_stmt.setObject(2, newRow[COURSE_ID]);
				int num_rows = delete_stmt.executeUpdate();
				System.out.println("AuditGradeTrigger delete num_rows=" + num_rows);  // for debug
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

  /*
   * called when the trigger id created 
  */
	@Override
	public void init(Connection conn, String schema, String trigger, String table, boolean before, int type) throws SQLException {
		/*
		 * type 1=INSERT, 2=UPDATE, 4=DELETE, 8=SELECT
		 */
     // for debug  
		System.out.println("AuditGradeTrigger init called.");
		System.out.println("schema=" + schema + ", trigger=" + trigger + ", table=" + table + ", before=" + before
				+ ", type=" + type);
	}
  
  /*
  * database is being shutdown
  */
  @Override
	public void close() throws SQLException  {
		System.out.println("AuditGradeTrigger close.");  // debug 
	}

  /* 
   * called when trigger is deleted
   */
	@Override
	public void remove() throws SQLException {
		System.out.println("AuditGradeTrigger deleted.");
	}
}