package railsimulator.transmissionMSG.transmissionTerrain;

import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.xml.bind.JAXBException;
import org.apache.log4j.BasicConfigurator;
import org.xml.sax.SAXException;
import serialisationXML.XmlTransformer;
import beans.AnnonceVoyageur;
import communicationJMS.Producteur;


public class EnvoiMSGTerrain {

	
	public EnvoiMSGTerrain() {
		
	}
	
	public void envoyerMessageAnnonceToESB(AnnonceVoyageur annonce) throws JMSException, JAXBException, SAXException{
		  try {
			 //creation d'un objet message de type annonceVoyageur
			//  AnnonceVoyageur messageVoyageur = new AnnonceVoyageur();
		
			//Marshaller le message de type annonceVoyageur en XML
			  XmlTransformer transforme = new XmlTransformer();
			  String messageXML = transforme.transformeXML(annonce);
			  BasicConfigurator.configure();
		   //ecrire le message ˆ la file
		   new Producteur().ecrireMessage(messageXML);
		  } catch (NamingException e) {
		   e.printStackTrace();
		  }		 
	}

	public void envoyerMessage(Object object) throws JMSException, JAXBException, SAXException{
		  try {
			 //creation d'un objet message de type InstructionInfrastructure
			
			//Marshaller le message de type annonceVoyageur en XML
			  XmlTransformer transforme = new XmlTransformer();
			  String messageXML = transforme.transformeXML(object);
			  BasicConfigurator.configure();
		   //ecrire le message ˆ la file
		   new Producteur().ecrireMessage(messageXML);
		  } catch (NamingException e) {
		   e.printStackTrace();
		   
		  }		 
	}
}
