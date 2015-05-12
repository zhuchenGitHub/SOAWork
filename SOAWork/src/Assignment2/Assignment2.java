package Assignment2;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerConfigurationException;

import org.xml.sax.SAXException;

public class Assignment2 {

	public static void main(String[] args) throws SAXException, IOException,
			ParserConfigurationException, TransformerConfigurationException {
		DOM dom = new DOM();
		try {
			dom.domCreateXML();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		// schema��֤StudentList.xml�Ƿ����StudentList.xsdҪ��
		try {
			dom.schemaVerify(
					"C:/Users/zhuchen/Desktop/�ĵ�/SOA��ҵ/13-01/StudentList.xml",
					"C:/Users/zhuchen/Desktop/�ĵ�/SOA��ҵ/13-01/StudentList.xsd");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// XSLT��StudentList.xmlת��Ϊ����ScoreList.xsd��XML�ĵ�
		File file = new File(
				"C:/Users/zhuchen/Desktop/�ĵ�/SOA��ҵ/13-01/assignment 2/scorelist/ScoreList.xml");
		if (!(file.exists())) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String sourceFilePath = "C:/Users/zhuchen/Desktop/�ĵ�/SOA��ҵ/13-01/StudentList.xml";
		String resultFilePath = "C:/Users/zhuchen/Desktop/�ĵ�/SOA��ҵ/13-01/assignment 2/scorelist/ScoreList.xml";
		String xsltFilePath = "C:/Users/zhuchen/Desktop/�ĵ�/SOA��ҵ/13-01/assignment 2/ScoreList.xsl";
		XSLT xslt = new XSLT();
		xslt.xsltTransForm(sourceFilePath, resultFilePath, xsltFilePath);
		try {
			dom.schemaVerify(
					"C:/Users/zhuchen/Desktop/�ĵ�/SOA��ҵ/13-01/assignment 2/scorelist/ScoreList.xml",
					"C:/Users/zhuchen/Desktop/�ĵ�/SOA��ҵ/13-01/assignment 2/scorelist/ScoreList.xsd");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// SAX����XML�ĵ�
		SAXParserFactory sax = SAXParserFactory.newInstance();
		sax.setNamespaceAware(true);
		SAXParser saxparser = sax.newSAXParser();
		File f = new File(
				"C:/Users/zhuchen/Desktop/�ĵ�/SOA��ҵ/13-01/assignment 2/scorelist/ScoreList.xml");
		MySAXHardler hardler = new MySAXHardler();
		saxparser.parse(f, hardler);
		List<CourseScore> result = hardler.courseScoreList;

		// SAX����XML
		String notpassPath = "C:/Users/zhuchen/Desktop/�ĵ�/SOA��ҵ/13-01/assignment 2/scorelist/NotPassList.xml";
		File file1 = new File(notpassPath);
		if (!(file1.exists())) {
			try {
				file1.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		SAXToXML ss = new SAXToXML();
		ss.createSAXXML(notpassPath, result);
		try {
			dom.schemaVerify(
					"C:/Users/zhuchen/Desktop/�ĵ�/SOA��ҵ/13-01/assignment 2/scorelist/NotPassList.xml",
					"C:/Users/zhuchen/Desktop/�ĵ�/SOA��ҵ/13-01/assignment 2/scorelist/ScoreList.xsd");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
