package jms.abonnement;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;



public class GenerateXml {
	private Unmarshaller decodeur;
	private Marshaller marshall;
	private StringWriter sw; 
	private JAXBContext context;
	
	public GenerateXml(){
		
	}
	
protected JAXBContext getJaxbContext() throws JAXBException {
		
	
		if (context == null ) {
			
			Thread.currentThread().setContextClassLoader(
					this.getClass().getClassLoader()); //THIS FIXED IT
			context = JAXBContext.newInstance(xsd.model.ObjectFactory.class);
			System.out.println("context "+context.getClass().toString());
		} 
		return context;
	}

	
	
	public String genererFile(Object obj){
		
		try {
			//Sauvegarder la serialisation d'un fichier XML
			StringWriter sw = new StringWriter();
			 context = getJaxbContext(); //Package classe jms
			marshall = context.createMarshaller();
		//	SchemaFactory factory = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);
			//File schemaFile = new File("/Users/MacSim/Documents/workspace/MockIsidis/AbonnementXSD.xsd"); // cible fichier xsd
			//Schema schema = factory.newSchema(schemaFile);
			//marshall.setSchema(schema); // verification schema
			marshall.marshal(obj, sw);   //conversion objet en stringwiter
			sw.close();
			return sw.getBuffer().toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		return sw.getBuffer().toString();
		

	}
	
	 //converti String (xml) en object 
		public Object xmlToObject(String xml) {
			StringReader reader = new StringReader(xml);
			Object result = null;
			try {	
				 context = getJaxbContext();
				decodeur = context.createUnmarshaller();
				result = decodeur.unmarshal(reader);
			} catch (JAXBException e) {
				System.out.println("JAXBException dans la methode xmlToObject() du Decodeur : " + e.toString());
				e.printStackTrace();
			}
			return result;
		}

}

