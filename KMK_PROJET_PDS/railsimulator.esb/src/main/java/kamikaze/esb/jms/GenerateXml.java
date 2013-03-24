package kamikaze.esb.jms;


import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;     

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;


import org.xml.sax.SAXException;



public class GenerateXml {
	//ResourceBundle bundle  = ResourceBundle.getBundle("jndi");
	public String genererFile(Object obj){
		
		try {
			//Sauvegarder la serialisation d'un fichier XML
			StringWriter sw = new StringWriter();
			JAXBContext context = JAXBContext.newInstance("kamikaze.esb.model"); //Package classe jms
			Marshaller marshall = context.createMarshaller();
			SchemaFactory factory = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);
			File schemaFile = new File("/Users/ketsia_55/Documents/workspace/testOrchestration/src/main/resources/MockTrainV3.xsd"); // cible fichier xsd
			Schema schema = factory.newSchema(schemaFile);
			marshall.setSchema(schema); // verification schema
			marshall.marshal(obj, sw);   //conversion objet en stringwiter
			sw.close();
			return sw.getBuffer().toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		

	}
}

