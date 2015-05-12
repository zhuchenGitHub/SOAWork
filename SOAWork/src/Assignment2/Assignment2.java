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

		// schema验证StudentList.xml是否符合StudentList.xsd要求
		try {
			dom.schemaVerify(
					"C:/Users/zhuchen/Desktop/文档/SOA作业/13-01/StudentList.xml",
					"C:/Users/zhuchen/Desktop/文档/SOA作业/13-01/StudentList.xsd");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// XSLT将StudentList.xml转化为符合ScoreList.xsd的XML文档
		File file = new File(
				"C:/Users/zhuchen/Desktop/文档/SOA作业/13-01/assignment 2/scorelist/ScoreList.xml");
		if (!(file.exists())) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String sourceFilePath = "C:/Users/zhuchen/Desktop/文档/SOA作业/13-01/StudentList.xml";
		String resultFilePath = "C:/Users/zhuchen/Desktop/文档/SOA作业/13-01/assignment 2/scorelist/ScoreList.xml";
		String xsltFilePath = "C:/Users/zhuchen/Desktop/文档/SOA作业/13-01/assignment 2/ScoreList.xsl";
		XSLT xslt = new XSLT();
		xslt.xsltTransForm(sourceFilePath, resultFilePath, xsltFilePath);
		try {
			dom.schemaVerify(
					"C:/Users/zhuchen/Desktop/文档/SOA作业/13-01/assignment 2/scorelist/ScoreList.xml",
					"C:/Users/zhuchen/Desktop/文档/SOA作业/13-01/assignment 2/scorelist/ScoreList.xsd");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// SAX解析XML文档
		SAXParserFactory sax = SAXParserFactory.newInstance();
		sax.setNamespaceAware(true);
		SAXParser saxparser = sax.newSAXParser();
		File f = new File(
				"C:/Users/zhuchen/Desktop/文档/SOA作业/13-01/assignment 2/scorelist/ScoreList.xml");
		MySAXHardler hardler = new MySAXHardler();
		saxparser.parse(f, hardler);
		List<CourseScore> result = hardler.courseScoreList;

		// SAX生成XML
		String notpassPath = "C:/Users/zhuchen/Desktop/文档/SOA作业/13-01/assignment 2/scorelist/NotPassList.xml";
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
					"C:/Users/zhuchen/Desktop/文档/SOA作业/13-01/assignment 2/scorelist/NotPassList.xml",
					"C:/Users/zhuchen/Desktop/文档/SOA作业/13-01/assignment 2/scorelist/ScoreList.xsd");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
