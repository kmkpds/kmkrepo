package railsimulator.transmissionMSG.serialisationXML;
import java.io.IOException;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.xml.sax.SAXException;
import beans.Message;

public class XmlTransformer {

	public XmlTransformer() {
		// TODO Auto-generated constructor stub
	}
	
	//convertir un event en XML gr‰ce ˆ JAXB
    public String transformeXML(Object message) throws JAXBException, SAXException{
        try {
        	StringWriter outputString = new StringWriter();
        	JAXBContext jc = JAXBContext.newInstance(message.getClass());
            Marshaller encodeur = jc.createMarshaller();
            //encodeur.marshal( event , System.out );                       
			// encodage de l'event
			encodeur.marshal(message, outputString);
			outputString.close();
			
			return outputString.getBuffer().toString();
           
        } catch( JAXBException jbe ){
           System.out.println("erreurr jaxb");		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    }


}
