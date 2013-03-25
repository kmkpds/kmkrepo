package transmissionTerrain;

import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.xml.bind.JAXBException;

import model.Event;

import org.apache.log4j.BasicConfigurator;
import org.xml.sax.SAXException;
import serialisationXML.XmlTransformer;
import beans.AnnonceVoyageur;
import beans.InstructionInfrastructure;
import beans.Message;
import communicationJMS.Producteur;

/**
 * Cette classe offre un service aux UC :
 * 			Transmettre Annonce Voyageur 
 * 			Transmettre Instruction Infrastructure
 * 
 *
	 * @author faycallemseffer
	 * @param args
	 * @throws SAXException 
	 * @throws JAXBException 
	 * @throws JMSException 
	 */

public class EnvoiMSGTerrain {

	
	public EnvoiMSGTerrain() {
		
	}
	//l'envoi de message de type Annonce Voyageur
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
	//l'envoie de message de type II
	public void envoyerMessageIIToESB(InstructionInfrastructure infra) throws JMSException, JAXBException, SAXException{
		  try {
			 //creation d'un objet message de type InstructionInfrastructure
			//InstructionInfrastructure messageInstructionInfrastructure = new InstructionInfrastructure();
			//Marshaller le message de type annonceVoyageur en XML
			  XmlTransformer transforme = new XmlTransformer();
			  String messageXML = transforme.transformeXML(infra);
			  BasicConfigurator.configure();
		   //ecrire le message ˆ la file
		   new Producteur().ecrireMessage(messageXML);
		  } catch (NamingException e) {
		   e.printStackTrace();
		  }		 
	}
	//l'envoie de message de tt type 
	public void envoyerMessage(Object object) throws JMSException, JAXBException, SAXException{
		  try {
			 //creation d'un objet message de type Objet
			
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
