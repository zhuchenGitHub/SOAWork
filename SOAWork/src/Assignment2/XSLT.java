package Assignment2;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XSLT {
	/*
	 * XSLT×ª»»
	 */
	public void xsltTransForm(String sourceFilePath, String resultFilePath,
			String xsltFilePath) {
		try {
			TransformerFactory factory = TransformerFactory.newInstance();
			Templates template = factory.newTemplates(new StreamSource(
					new FileInputStream(xsltFilePath)));
			Transformer transformer = template.newTransformer();
			Source source = new StreamSource(
					new FileInputStream(sourceFilePath));
			Result result = new StreamResult(new FileOutputStream(
					resultFilePath));
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
