package communication.jms.dwh;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.xml.sax.SAXException;

public class XmlTransformer {

	private Marshaller encodeur;
	private Unmarshaller decodeur ;
	public XmlTransformer() {
	}
	//converti l'objet en String (CML)
    public String transformeXML(Object message) throws JAXBException, SAXException{
        try {
        	StringWriter outputString = new StringWriter();
        	JAXBContext jc = JAXBContext.newInstance(message.getClass());
            encodeur = jc.createMarshaller(); 
			encodeur.marshal(message, outputString);
			outputString.close();
			
			return outputString.getBuffer().toString();
           
        } catch( JAXBException jbe ){
           System.out.println("erreur jaxb");		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    }
    //converti String (xml) en object 
	public Object xmlToObject(String xml) {
		StringReader reader = new StringReader(xml);
		Object result = null;
		try {		
			result = decodeur.unmarshal(reader);
		} catch (JAXBException e) {
			System.out.println("JAXBException dans la methode xmlToObject() du Decodeur : " + e.toString());
			e.printStackTrace();
		}
		return result;
	}


}
