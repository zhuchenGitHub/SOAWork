package Assignment2;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MySAXHardler extends DefaultHandler {
	public List<CourseScore> courseScoreList;
	private CourseScore courseScore;
	private String courseID;
	private String studentID;
	private int score;
	private boolean isFinalScore = false;
	private boolean isScore = false;
	private boolean isStuID = false;

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		courseScoreList = new ArrayList<CourseScore>();
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}

	@Override
	public void startElement(String arg0, String arg1, String arg2,
			Attributes arg3) throws SAXException {
		if (2 == arg3.getLength()) {
			if (arg3.getValue(1).equals("总评成绩")) {
				courseID = arg3.getValue(0);
				isFinalScore = true;
			} else {
				isFinalScore = false;
			}

		}
		if (arg2.equals("ss:得分") && isFinalScore) {
			isScore = true;	
		} else {
			isScore = false;
		}
		if (arg2.equals("ss:学号") && isFinalScore) {
			isStuID = true;		
		} else {
			isStuID = false;
		}
	}

	@Override
	public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
		if (isStuID) {
			studentID = new String(arg0, arg1, arg2);
			isStuID = false;
		}
		if (isScore) {
			score = Integer.parseInt(new String(arg0, arg1, arg2));
			isScore = false;
		}
	}

	@Override
	public void endElement(String arg0, String arg1, String arg2) {
		if (isFinalScore && (score < 60)&&(arg2.equals("ss:得分"))) {
			courseScore = new CourseScore();
			courseScore.setCourseID(courseID);
			courseScore.setScore(score);
			courseScore.setStudentID(studentID);
			courseScoreList.add(courseScore);
			isFinalScore=false;
		}
	}
}
