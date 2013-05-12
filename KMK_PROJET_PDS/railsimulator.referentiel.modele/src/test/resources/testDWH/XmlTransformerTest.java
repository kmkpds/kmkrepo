package testDWH;

import javax.xml.bind.JAXBException;
import org.xml.sax.SAXException;
import communication.jms.dwh.XmlTransformer;

import beans.Message;
import junit.framework.TestCase;

public class XmlTransformerTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	//test transforme
	public void testXmlTransformer() throws JAXBException, SAXException {
		//create object message
		Message message = new Message();
		//setter message
		message.setCriticite(2);
		message.setIdMessage(2);
		message.setLibelle("libelle");
		//affecter 
		Object object = message ;
		XmlTransformer xml = new XmlTransformer();
		String resultat = xml.transformeXML(object);
		
	
		assertSame(resultat,object);
		
	}


}
