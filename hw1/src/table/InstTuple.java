package table;

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
	public void setID(String iD) { ID = iD; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getDept_name() { return dept_name; }
	public void setDept_name(String dept_name) { this.dept_name = dept_name; }

	public int getSalary() { return salary;	}
	public void setSalary(int salary) {	this.salary = salary; }
	
	public String toString() {
		return "(ID: "+ID+"; name: "+name+"; dept name: "+dept_name+"; salary: "+salary+")";
	}
}
