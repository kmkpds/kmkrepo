package railsimulator.visualisation.messagesTerrain;

import java.io.StringReader;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import modeleMessages.MockInfoTraffic;

public class ListenerTrafic implements MessageListener {

	@Override
	public void onMessage(Message msg) {
		
		//Capture de l'état du trafic
		try {
				if (msg instanceof TextMessage) {
					TextMessage text = (TextMessage) msg;
					StringReader inputString = new StringReader(text.getText());
					JAXBContext context = JAXBContext.newInstance("modeleMessages");
					// Décodage du message
					Unmarshaller decodeur = context.createUnmarshaller();
					Object result = decodeur.unmarshal(inputString);
					//Acquittement du message
					inputString.close();
					rafraichissementReseau(result);
					// MockInfoTrafic 
				}
					else {
						System.err.println("Erreur de format de message !!");
					}
		}	catch (JMSException e1) {
				e1.printStackTrace();
			}
			catch (JAXBException e2) {
				e2.printStackTrace();
			}
		
	}
	
	private void rafraichissementReseau(Object obj){
		
		if (obj instanceof MockInfoTraffic){
			
		
		}
		
	}

}
