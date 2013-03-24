package kamikaze.esb.jms;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.Marshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

public class TransformationMessage {

	private JAXBContext context;
	private Marshaller march;
	private Unmarshaller unmarch;
	private SchemaFactory factory;
	private File file;
	private Schema schema;
	private String pathModel=null;
	private String pathSchema=null;
	
	public TransformationMessage(){
		
	}
	
	public TransformationMessage(String pathModel, String pathSchema){
		this.pathModel = pathModel;
		this.pathSchema = pathSchema;
		init();
	}


	public void init(){
		try {

			context = JAXBContext.newInstance(pathModel,this.getClass().getClassLoader());	
				if (pathSchema == null){
					march = context.createMarshaller();
					unmarch = context.createUnmarshaller();
				}
				else {
				factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
				file = new File(pathSchema);
				schema = factory.newSchema(file);
				march = context.createMarshaller();
				march.setSchema(schema);
				unmarch = context.createUnmarshaller();
				unmarch.setSchema(schema);		
				}
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//fin init
	
	

	public String objectToXML(Object o) {
		try {
			StringWriter sw = new StringWriter();
			march.marshal(o, sw);
			return sw.getBuffer().toString();
		} catch (JAXBException e) {
			System.out.println("Erreur ds methode objetToXML() de TransformationMessage : " + e.toString());
			e.printStackTrace();
		}
		
		return null;
	}

	public Object xmlToObject(String xml) {
		StringReader reader = new StringReader(xml);
		Object result = null;
		try {
			result = unmarch.unmarshal(reader);
		} catch (JAXBException e) {
			System.out.println("Erreur ds methode xmlToObject() de TransformationMessage : " + e.toString());
			e.printStackTrace();
		}
		return result;
	}
	
}// fin transformationmessage
