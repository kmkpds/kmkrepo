package kamikaze.esb.jms;


import java.io.StringReader;         

import java.util.Locale;
import java.util.ResourceBundle;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


import org.apache.activemq.ActiveMQConnectionFactory;

import com.sun.org.apache.xml.internal.security.Init;


public class JMSReaderM2P implements MessageListener{

     
	//ResourceBundle bundle  = ResourceBundle.getBundle("jndi");

	//protected MessageConsumer bf2Smx = null;
	//protected ConnectionFactory factory  = null;

	
	
	public  JMSReaderM2P() {
	
		

	}



	public void onMessage(Message msg) {
		// TODO Auto-generated method stub
		try {
			// TODO Auto-generated method stub
			System.out.println("ds on message");
				
				System.out.println("entre reader");

				if(msg instanceof TextMessage){
					TextMessage tmsg = (TextMessage) msg;
					String contenu = tmsg.getText();
					System.out.println("Traitement TextMessage :"+ contenu.toString());
				

					StringReader inputString  = new StringReader(contenu);

					System.out.println(inputString);
					
					//ajout test
					System.out.println("contenu==>" +inputString.toString());

					JAXBContext  context = JAXBContext.newInstance("kamikaze.esb.model");
					Unmarshaller decodeur = context.createUnmarshaller();
					Object result = decodeur.unmarshal(inputString);				
					inputString.close();

				}// if msg instanceof textmessage
				else{
					System.out.println("mauvaise instance of");
				}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}


}
	








