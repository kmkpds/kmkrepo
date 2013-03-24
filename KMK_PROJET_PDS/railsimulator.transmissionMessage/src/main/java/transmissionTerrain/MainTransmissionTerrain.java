package transmissionTerrain;

import javax.jms.JMSException;
import javax.xml.bind.JAXBException;
import org.xml.sax.SAXException;
import beans.AnnonceVoyageur;
import beans.InstructionInfrastructure;

public class MainTransmissionTerrain {
	
	public static void main(String[] args) throws JMSException, JAXBException, SAXException {

		// Mocker deux instances ==> Message 
		AnnonceVoyageur annonce = new AnnonceVoyageur(4, "malaise voyageur", "suite ˆ un malaise voyageur...");
		InstructionInfrastructure infra = new InstructionInfrastructure(1,"ˆ definir les attribut avec khady");
		//Envoyer les deux Messages sur une file ActiveMQ 
		new EnvoiMSGTerrain().envoyerMessageAnnonceToESB(annonce);
		new EnvoiMSGTerrain().envoyerMessageIIToESB(infra);
		
	}
}





