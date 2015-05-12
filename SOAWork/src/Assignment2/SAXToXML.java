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
		attr.addAttribute("", "�γ̱��", "�γ̱��", "", courseScore.getCourseID());
		attr.addAttribute("", "�ɼ�����", "�ɼ�����", "", courseScore.getScoreClass());
		th.startElement("http://jw.nju.edu.cn/schema", "�γ̳ɼ�", "ss:�γ̳ɼ�", attr);
		th.startElement("http://jw.nju.edu.cn/schema", "�ɼ�", "ss:�ɼ�", null);
		th.startElement("http://jw.nju.edu.cn/schema", "ѧ��", "ss:ѧ��", null);
		th.characters(courseScore.getStudentID().toCharArray(), 0, courseScore
				.getStudentID().length());
		th.endElement("http://jw.nju.edu.cn/schema", "ѧ��", "ss:ѧ��");
		th.startElement("http://jw.nju.edu.cn/schema", "�÷�", "ss:�÷�", null);
		th.characters(String.valueOf(courseScore.getScore()).toCharArray(), 0,
				String.valueOf(courseScore.getScore()).length());
		th.endElement("http://jw.nju.edu.cn/schema", "�÷�", "ss:�÷�");
		th.endElement("http://jw.nju.edu.cn/schema", "�ɼ�", "ss:�ɼ�");
		th.endElement("http://jw.nju.edu.cn/schema", "�γ̳ɼ�", "ss:�γ̳ɼ�");
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
		th.startElement("http://jw.nju.edu.cn/schema", "�γ̳ɼ��б�", "ss:�γ̳ɼ��б�",
				attr);
		for (CourseScore cs : list) {
			s.courseScoreNode(th, cs);
		}
		th.endElement("http://jw.nju.edu.cn/schema", "�γ̳ɼ��б�", "ss:�γ̳ɼ��б�");
		th.endDocument();
	}
}
