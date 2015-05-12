package Assignment2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class SAXToXML {
	public void courseScoreNode(TransformerHandler th, CourseScore courseScore)
			throws SAXException {
		AttributesImpl attr = new AttributesImpl();
		attr.clear();
		attr.addAttribute("", "课程编号", "课程编号", "", courseScore.getCourseID());
		attr.addAttribute("", "成绩性质", "成绩性质", "", courseScore.getScoreClass());
		th.startElement("http://jw.nju.edu.cn/schema", "课程成绩", "ss:课程成绩", attr);
		th.startElement("http://jw.nju.edu.cn/schema", "成绩", "ss:成绩", null);
		th.startElement("http://jw.nju.edu.cn/schema", "学号", "ss:学号", null);
		th.characters(courseScore.getStudentID().toCharArray(), 0, courseScore
				.getStudentID().length());
		th.endElement("http://jw.nju.edu.cn/schema", "学号", "ss:学号");
		th.startElement("http://jw.nju.edu.cn/schema", "得分", "ss:得分", null);
		th.characters(String.valueOf(courseScore.getScore()).toCharArray(), 0,
				String.valueOf(courseScore.getScore()).length());
		th.endElement("http://jw.nju.edu.cn/schema", "得分", "ss:得分");
		th.endElement("http://jw.nju.edu.cn/schema", "成绩", "ss:成绩");
		th.endElement("http://jw.nju.edu.cn/schema", "课程成绩", "ss:课程成绩");
	}

	public void createSAXXML(String resultPath, List<CourseScore> list)
			throws FileNotFoundException, TransformerConfigurationException,
			SAXException {
		SAXToXML s = new SAXToXML();
		Result resultXml = new StreamResult(new FileOutputStream(resultPath));
		SAXTransformerFactory sff = (SAXTransformerFactory) SAXTransformerFactory
				.newInstance();
		TransformerHandler th = sff.newTransformerHandler();
		th.setResult(resultXml);
		Transformer transformer = th.getTransformer();
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		th.startDocument();
		AttributesImpl attr = new AttributesImpl();
		attr.addAttribute("", "ss", "xmlns:ss", "",
				"http://jw.nju.edu.cn/schema");
		th.startElement("http://jw.nju.edu.cn/schema", "课程成绩列表", "ss:课程成绩列表",
				attr);
		for (CourseScore cs : list) {
			s.courseScoreNode(th, cs);
		}
		th.endElement("http://jw.nju.edu.cn/schema", "课程成绩列表", "ss:课程成绩列表");
		th.endDocument();
	}
}
