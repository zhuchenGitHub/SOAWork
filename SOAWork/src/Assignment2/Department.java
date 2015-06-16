package Assignment2;

enum DepartmentType {
	A("党组织"), B("学生会"), C("行政部门"), D("物业部门"), E("直属部门"), F("院系");
	private String typeName;

	private DepartmentType(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeName() {
		return typeName;
	}
}

public class Department {
	private int departmentId;
	private String departmentName;
	private DepartmentType departmentType;
	private String departmentDescription;

	public Department(int departmentId, String departmentName,
			DepartmentType departmentType, String departmentDescription) {
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.departmentType = departmentType;
		this.departmentDescription = departmentDescription;
	}

	public int getDepId() {
		return departmentId;
	}

	public String getDepName() {
		return departmentName;
	}

	public String getDepType() {
		return departmentType.getTypeName();
	}

	public String getDepDescription() {
		return departmentDescription;
	}
}
