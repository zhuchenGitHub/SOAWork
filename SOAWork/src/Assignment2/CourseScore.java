package Assignment2;

public class CourseScore {
	private String courseID;
	private final String scoreClass = "×ÜÆÀ³É¼¨";
	private String studentID;
	private int score;

	public CourseScore() {

	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getScoreClass() {
		return scoreClass;
	}
}
