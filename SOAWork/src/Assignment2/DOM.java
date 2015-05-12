package Assignment2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class DOM {

	public static Document getDocument() throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.newDocument();
		doc.setXmlVersion("1.0");
		return doc;
	}

	public Element createDepElement(Department department, Document doc)
			throws ParserConfigurationException {
		Element departmentNode = doc.createElementNS(
				"http://www.nju.edu.cn/schema", "Department");
		Element departmentIdNode = doc.createElementNS(
				"http://www.nju.edu.cn/schema", "id");
		departmentIdNode.setTextContent(String.valueOf(department.getDepId()));
		Element departmentNameNode = doc.createElementNS(
				"http://www.nju.edu.cn/schema", "name");
		departmentNameNode.setTextContent(department.getDepName());
		Element departmentTypeNode = doc.createElementNS(
				"http://www.nju.edu.cn/schema", "type");
		departmentTypeNode.setTextContent(department.getDepType());
		Element departmentDescNode = doc.createElementNS(
				"http://www.nju.edu.cn/schema", "description");
		departmentDescNode.setTextContent(department.getDepDescription());
		departmentNode.appendChild(departmentIdNode);
		departmentNode.appendChild(departmentNameNode);
		departmentNode.appendChild(departmentTypeNode);
		departmentNode.appendChild(departmentDescNode);
		return departmentNode;
	}

	public Element createPersonInfoElement(PersonInfo personInfo, Document doc)
			throws ParserConfigurationException {
		DOM dom = new DOM();
		Element personInfoNode = doc.createElementNS(
				"http://www.nju.edu.cn/schema", "personInfo");
		Element personInfoIdNode = doc.createElementNS(
				"http://www.nju.edu.cn/schema", "id");
		personInfoIdNode.setTextContent(String.valueOf(personInfo.getStuId()));
		Element personInfoNameNode = doc.createElementNS(
				"http://www.nju.edu.cn/schema", "name");
		personInfoNameNode.setTextContent(personInfo.getStuName());
		Element personInfoSexNode = doc.createElementNS(
				"http://www.nju.edu.cn/schema", "sex");
		personInfoSexNode.setTextContent(personInfo.getSex());
		Element personInfoAgeNode = doc.createElementNS(
				"http://www.nju.edu.cn/schema", "age");
		personInfoAgeNode.setTextContent(String.valueOf(personInfo.getAge()));
		Element departmentNode = dom.createDepElement(personInfo.getDep(), doc);
		personInfoNode.appendChild(personInfoIdNode);
		personInfoNode.appendChild(personInfoNameNode);
		personInfoNode.appendChild(personInfoSexNode);
		personInfoNode.appendChild(personInfoAgeNode);
		personInfoNode.appendChild(departmentNode);
		return personInfoNode;

	}

	public Element createScoreElement(Score score, Document doc)
			throws ParserConfigurationException {
		Element scoreNode = doc.createElementNS("http://jw.nju.edu.cn/schema",
				"MyScore");
		Element normalScoreNode = doc.createElementNS(
				"http://jw.nju.edu.cn/schema", "NormalScore");
		normalScoreNode.setTextContent(String.valueOf(score.getNormal()));
		Element testScoreNode = doc.createElementNS(
				"http://jw.nju.edu.cn/schema", "TestScore");
		testScoreNode.setTextContent(String.valueOf(score.getTest()));
		Element finalScoreNode = doc.createElementNS(
				"http://jw.nju.edu.cn/schema", "FinalScore");
		finalScoreNode.setTextContent(String.valueOf(score.getFinal()));
		scoreNode.appendChild(normalScoreNode);
		scoreNode.appendChild(testScoreNode);
		scoreNode.appendChild(finalScoreNode);
		return scoreNode;

	}

	public Element createCourseElement(Course course, Document doc)
			throws ParserConfigurationException {
		DOM dom = new DOM();
		Element courseNode = doc.createElementNS("http://jw.nju.edu.cn/schema",
				"Course");
		courseNode.setAttribute("CourseID", String.valueOf(course.getCourId()));
		Element courseNameNode = doc.createElementNS(
				"http://jw.nju.edu.cn/schema", "CourseName");
		courseNameNode.setTextContent(course.getCourName());
		Element courseTeacherNameNode = doc.createElementNS(
				"http://jw.nju.edu.cn/schema", "TeacherName");
		courseTeacherNameNode.setTextContent(course.getTeacName());
		Element courseClassRoomNode = doc.createElementNS(
				"http://jw.nju.edu.cn/schema", "ClassRoom");
		courseClassRoomNode.setTextContent(course.getClassroom());
		Element MyScore = dom.createScoreElement(course.getScore(), doc);
		courseNode.appendChild(courseNameNode);
		courseNode.appendChild(courseTeacherNameNode);
		courseNode.appendChild(courseClassRoomNode);
		courseNode.appendChild(MyScore);
		return courseNode;

	}

	public void schemaVerify(String xmlPath, String schemaPath)
			throws Exception {
		SchemaFactory schemaFactory = SchemaFactory
				.newInstance("http://www.w3.org/2001/XMLSchema");
		Schema schema = schemaFactory.newSchema(new File(schemaPath));
		Validator validator = schema.newValidator();
		validator.setErrorHandler(new ErrorHandler() {

			public void warning(SAXParseException exception)
					throws SAXException {
				System.out.println("警告：" + exception);
			}

			public void fatalError(SAXParseException exception)
					throws SAXException {
				System.out.println("致命：" + exception);
			}

			public void error(SAXParseException exception) throws SAXException {
				System.out.println("错误：" + exception);

			}
		});
		validator.validate(new StreamSource(new File(xmlPath)));
	}

	public void domCreateXML() throws ParserConfigurationException,
			TransformerConfigurationException {
		Document doc = getDocument();
		Random random = new Random();
		DOM dom = new DOM();

		Department department1 = new Department(102, "软件学院", DepartmentType.F,
				"软件学院介绍");
		PersonInfo personInfo1 = new PersonInfo(121250049, "花霞", "female", 20,
				department1);
		Course student1Course1 = new Course("000001", "商务智能", "贝佳", "教学楼101",
				new Score(random.nextInt(40), random.nextInt(60)));
		Course student1Course2 = new Course("000002", "电子商务", "王金庆", "教学楼102",
				new Score(random.nextInt(40), random.nextInt(60)));
		Course student1Course3 = new Course("000003", "软件工程", "刘钦", "教学楼103",
				new Score(random.nextInt(40), random.nextInt(60)));
		Course student1Course4 = new Course("000004", "软件测试", "陈振宇", "教学楼104",
				new Score(random.nextInt(40), random.nextInt(60)));
		Course student1Course5 = new Course("000005", "人机交互", "冯桂焕", "教学楼105",
				new Score(random.nextInt(40), random.nextInt(60)));
		Element stu1CourseListNode = doc.createElementNS(
				"http://jw.nju.edu.cn/schema", "CourseList");
		stu1CourseListNode.appendChild(dom.createCourseElement(student1Course1,
				doc));
		stu1CourseListNode.appendChild(dom.createCourseElement(student1Course2,
				doc));
		stu1CourseListNode.appendChild(dom.createCourseElement(student1Course3,
				doc));
		stu1CourseListNode.appendChild(dom.createCourseElement(student1Course4,
				doc));
		stu1CourseListNode.appendChild(dom.createCourseElement(student1Course5,
				doc));
		Element student1 = doc.createElementNS("http://jw.nju.edu.cn/schema",
				"Student");
		student1.setAttribute("StudentNO", "1");
		student1.appendChild(dom.createPersonInfoElement(personInfo1, doc));
		student1.appendChild(stu1CourseListNode);
		// ********************************************************************************student1
		Department department2 = new Department(102, "软件学院", DepartmentType.F,
				"软件学院介绍");
		PersonInfo personInfo2 = new PersonInfo(121250001, "姜晓明", "male", 20,
				department2);
		Course student2Course1 = new Course("000001", "商务智能", "贝佳", "教学楼101",
				new Score(random.nextInt(40), random.nextInt(60)));
		Course student2Course2 = new Course("000002", "电子商务", "王金庆", "教学楼102",
				new Score(random.nextInt(40), random.nextInt(60)));
		Course student2Course3 = new Course("000003", "软件工程", "刘钦", "教学楼103",
				new Score(random.nextInt(40), random.nextInt(60)));
		Course student2Course4 = new Course("000004", "软件测试", "陈振宇", "教学楼104",
				new Score(random.nextInt(40), random.nextInt(60)));
		Course student2Course5 = new Course("000005", "人机交互", "冯桂焕", "教学楼105",
				new Score(random.nextInt(40), random.nextInt(60)));
		Element stu2CourseListNode = doc.createElementNS(
				"http://jw.nju.edu.cn/schema", "CourseList");
		stu2CourseListNode.appendChild(dom.createCourseElement(student2Course1,
				doc));
		stu2CourseListNode.appendChild(dom.createCourseElement(student2Course2,
				doc));
		stu2CourseListNode.appendChild(dom.createCourseElement(student2Course3,
				doc));
		stu2CourseListNode.appendChild(dom.createCourseElement(student2Course4,
				doc));
		stu2CourseListNode.appendChild(dom.createCourseElement(student2Course5,
				doc));
		Element student2 = doc.createElementNS("http://jw.nju.edu.cn/schema",
				"Student");
		student2.setAttribute("StudentNO", "2");
		student2.appendChild(dom.createPersonInfoElement(personInfo2, doc));
		student2.appendChild(stu2CourseListNode);
		// ********************************************************************************student2
		Department department3 = new Department(102, "软件学院", DepartmentType.F,
				"软件学院介绍");
		PersonInfo personInfo3 = new PersonInfo(121250002, "郑轲阳", "male", 20,
				department3);
		Course student3Course1 = new Course("000001", "商务智能", "贝佳", "教学楼101",
				new Score(random.nextInt(40), random.nextInt(60)));
		Course student3Course2 = new Course("000002", "电子商务", "王金庆", "教学楼102",
				new Score(random.nextInt(40), random.nextInt(60)));
		Course student3Course3 = new Course("000003", "软件工程", "刘钦", "教学楼103",
				new Score(random.nextInt(40), random.nextInt(60)));
		Course student3Course4 = new Course("000004", "软件测试", "陈振宇", "教学楼104",
				new Score(random.nextInt(40), random.nextInt(60)));
		Course student3Course5 = new Course("000005", "人机交互", "冯桂焕", "教学楼105",
				new Score(random.nextInt(40), random.nextInt(60)));
		Element stu3CourseListNode = doc.createElementNS(
				"http://jw.nju.edu.cn/schema", "CourseList");
		stu3CourseListNode.appendChild(dom.createCourseElement(student3Course1,
				doc));
		stu3CourseListNode.appendChild(dom.createCourseElement(student3Course2,
				doc));
		stu3CourseListNode.appendChild(dom.createCourseElement(student3Course3,
				doc));
		stu3CourseListNode.appendChild(dom.createCourseElement(student3Course4,
				doc));
		stu3CourseListNode.appendChild(dom.createCourseElement(student3Course5,
				doc));
		Element student3 = doc.createElementNS("http://jw.nju.edu.cn/schema",
				"Student");
		student3.setAttribute("StudentNO", "3");
		student3.appendChild(dom.createPersonInfoElement(personInfo3, doc));
		student3.appendChild(stu3CourseListNode);
		// ********************************************************************************student3
		Department department4 = new Department(102, "软件学院", DepartmentType.F,
				"软件学院介绍");
		PersonInfo personInfo4 = new PersonInfo(121250230, "朱晨", "male", 20,
				department4);
		Course student4Course1 = new Course("000001", "商务智能", "贝佳", "教学楼101",
				new Score(random.nextInt(40), random.nextInt(60)));
		Course student4Course2 = new Course("000002", "电子商务", "王金庆", "教学楼102",
				new Score(random.nextInt(40), random.nextInt(60)));
		Course student4Course3 = new Course("000003", "软件工程", "刘钦", "教学楼103",
				new Score(random.nextInt(40), random.nextInt(60)));
		Course student4Course4 = new Course("000004", "软件测试", "陈振宇", "教学楼104",
				new Score(random.nextInt(40), random.nextInt(60)));
		Course student4Course5 = new Course("000005", "人机交互", "冯桂焕", "教学楼105",
				new Score(random.nextInt(40), random.nextInt(60)));
		Element stu4CourseListNode = doc.createElementNS(
				"http://jw.nju.edu.cn/schema", "CourseList");
		stu4CourseListNode.appendChild(dom.createCourseElement(student4Course1,
				doc));
		stu4CourseListNode.appendChild(dom.createCourseElement(student4Course2,
				doc));
		stu4CourseListNode.appendChild(dom.createCourseElement(student4Course3,
				doc));
		stu4CourseListNode.appendChild(dom.createCourseElement(student4Course4,
				doc));
		stu4CourseListNode.appendChild(dom.createCourseElement(student4Course5,
				doc));
		Element student4 = doc.createElementNS("http://jw.nju.edu.cn/schema",
				"Student");
		student4.setAttribute("StudentNO", "4");
		student4.appendChild(dom.createPersonInfoElement(personInfo4, doc));
		student4.appendChild(stu4CourseListNode);
		// ***********************************************************************student4
		Element root = doc.createElementNS("http://jw.nju.edu.cn/schema",
				"StudentList");
		root.appendChild(student1);
		root.appendChild(student2);
		root.appendChild(student3);
		root.appendChild(student4);
		doc.appendChild(root);
		TransformerFactory transFactory = TransformerFactory.newInstance();
		Transformer transFormer = transFactory.newTransformer();
		DOMSource domsource = new DOMSource(doc);
		File file = new File(
				"C:/Users/zhuchen/Desktop/文档/SOA作业/13-01/StudentList.xml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			FileOutputStream out = new FileOutputStream(file);
			StreamResult xmlResult = new StreamResult(out);
			try {
				transFormer.transform(domsource, xmlResult);
			} catch (TransformerException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
