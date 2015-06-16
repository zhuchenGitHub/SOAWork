package Assignment2;

public class Course {
	private String courseId;
	private String courseName;
	private String teacherName;
	private String classRoom;
	private Score score;

	public Course(String courseId, String courseName, String teacherName,
			String classRoom, Score score) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.teacherName = teacherName;
		this.classRoom = classRoom;
		this.score = score;
	}

	public String getCourId() {
		return courseId;
	}

	public String getCourName() {
		return courseName;
	}

	public String getTeacName() {
		return teacherName;
	}

	public String getClassroom() {
		return classRoom;
	}
	public Score getScore(){
		return score;
	}
}
