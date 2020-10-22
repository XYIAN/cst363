package table;

/**
 * A row of an Instructor table.
 * 
 * @author Glenn
 *
 */

public class InstTuple {
	
	// fields of the tuple; ID is the primary key
	private String ID;
	private String name;
	private String dept_name;
	private int salary;
	
	// constructor
	public InstTuple(String ID, String name, String dept_name, int salary) {
		this.ID = ID;
		this.name = name;
		this.dept_name = dept_name;
		this.salary = salary;
	}

	public String getID() { return ID; }
	public void   setID(String ID) { this.ID = ID; }

	/**
	 * Return the value of a string attribute of this tuple.
	 * Return null if no such attribute.
	 * @param attr
	 * @return
	 */
	public String getStringAttr(String attr) {
		switch(attr) {
		case "ID":			return ID;
		case "name":		return name;
		case "dept_name":	return dept_name;
		default:			return null;
		}
	}
	
	/**
	 * Return the value of an int attribute of this tuple.
	 * Return null if no such attribute.
	 * @param attr
	 * @return
	 */
	public Integer getIntAttr(String attr) {
		switch(attr) {
		case "salary":		return salary;
		default:			return null;
		}
	}
	
	public String toString() {
		return "(ID: "+ID+"; name: "+name+"; dept name: "+dept_name+"; salary: "+salary+")";
	}
}
