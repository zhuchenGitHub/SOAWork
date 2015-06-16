package Assignment2;

public class PersonInfo {
	private int studentId;
	private String name;
	private String sex;
	private int age;
	private Department department;

	public PersonInfo(int studentId, String name, String sex, int age,
			Department department) {
		this.studentId = studentId;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.department = department;
	}

	public int getStuId() {
		return studentId;
	}

	public String getStuName() {
		return name;
	}

	public String getSex() {
		return sex;
	}

	public int getAge() {
		return age;
	}

	public Department getDep() {
		return department;
	}
}
