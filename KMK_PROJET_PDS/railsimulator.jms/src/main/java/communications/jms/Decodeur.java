package communications.jms;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;

public class Decodeur {

	private JAXBContext context;
	private Marshaller changeurObjetsEnXML;
	private Unmarshaller changeurXMLEnObjet;
	private SchemaFactory sf;
	private StreamSource source;
	private Schema unSchema;

	private String pathToSchema = null; 
	private String pathToModel = null;

	public Decodeur(String pathSchema, String pathModel) {
		this.pathToSchema = pathSchema;
		this.pathToModel = pathModel;
		init();
	}
	
	public Decodeur(String pathModel) {
		this.pathToModel = pathModel;
		init();
	}
	

	public String objetToXML(Object o) {
		try {
			StringWriter sw = new StringWriter();
			changeurObjetsEnXML.marshal(o, sw);
			return sw.getBuffer().toString();
		} catch (JAXBException e) {
			System.out.println("JAXBException dans la methode objetToXML() du Decodeur du jar jmsPerso : " + e.toString());
			e.printStackTrace();
		}
		
		return null;
	}

	public Object xmlToObject(String xml) {
		StringReader reader = new StringReader(xml);
		Object result = null;
		try {
			result = changeurXMLEnObjet.unmarshal(reader);
		} catch (JAXBException e) {
			System.out.println("JAXBException dans la methode xmlToObject() du Decodeur du jar jmsPerso : " + e.toString());
			e.printStackTrace();
		}
		return result;
	}


	private void init() {
		try {
			context = JAXBContext.newInstance(pathToModel);

			if (pathToSchema == null){
				changeurObjetsEnXML = context.createMarshaller();
				changeurXMLEnObjet = context.createUnmarshaller();
			}
			else {
			sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			source = new StreamSource(pathToSchema);
			unSchema = sf.newSchema(source);
			changeurObjetsEnXML = context.createMarshaller();
			changeurObjetsEnXML.setSchema(unSchema);
			changeurXMLEnObjet = context.createUnmarshaller();
			changeurXMLEnObjet.setSchema(unSchema);
			}

		} catch (JAXBException e1) {
			System.out.println("JAXBException dans la methode init() du Decodeur du jar jmsPerso : " + e1.toString());
			e1.printStackTrace();
		} catch (SAXException e2) {
			System.out.println("SAXException dans la methode init() du Decodeur du jar jmsPerso : " + e2.toString());
			e2.printStackTrace();
		}
	}
}

