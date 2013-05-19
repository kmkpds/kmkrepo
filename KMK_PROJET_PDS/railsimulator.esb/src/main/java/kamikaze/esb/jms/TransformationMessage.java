package kamikaze.esb.jms;

import javax.jms.JMSException;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ResourceBundle;


public class TransformationMessage {

	private JAXBContext context;
	private Marshaller march;
	private Unmarshaller unmarch;
	private SchemaFactory factory;
	private File file;
	private Schema schema;
	private String pathModel=null;
	private String pathSchema=null;

	ResourceBundle bundle  = ResourceBundle.getBundle("jndi");
	
	
	public TransformationMessage(){
		
	}
	
	public TransformationMessage(String pathModel, String pathSchema){
		try {
			this.pathModel = pathModel;
			this.pathSchema = pathSchema;
			init();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void init() throws JMSException{
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
			System.out.println("Erreur JAXB ds methode objetToXML() de TransformationMessage : " + e.toString());
			e.printStackTrace();
		}
		
		return null;
	}

	public Object xmlToObject(String xml) {
		StringReader reader = new StringReader(xml);
		Object result = null;
		try {
			if(unmarch == null){
				System.out.println("unmarch est nulle");	
			}
			else{
				System.out.println("unmarch non null");
				System.out.println("reader ==>"+xml);
				
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				dbf.setNamespaceAware(true);
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc = db.parse(new InputSource(reader));
				//Node n = (Node) doc.getDocumentElement().getFirstChild();
		
				
				result = unmarch.unmarshal(doc.getDocumentElement().getFirstChild().getNextSibling());
			}

			if(result == null){
				System.out.println("result est nulle");	
			}
		} catch (JAXBException e) {
			System.out.println("Erreur ds methode xmlToObject() de TransformationMessage : " + e.toString());
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public int random_priority(int min, int max){
		   int range = (max - min) + 1;     
		   return (int)(Math.random() * range) + min;
		
	}//fin random_priority
	
}// fin transformationmessage
