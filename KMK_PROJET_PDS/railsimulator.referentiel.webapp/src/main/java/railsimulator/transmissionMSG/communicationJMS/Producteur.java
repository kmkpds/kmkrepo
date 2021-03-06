package railsimulator.transmissionMSG.communicationJMS;

import javax.jms.*;

import javax.naming.NamingException;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.BasicConfigurator;


public class Producteur {
		
	// URL du serveur JMS
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	//	private static String url = "tcp://178.33.40.163:61616";
	//Nom de la file de production
	 private static String subject = "queueTransmissionMSGTerrain";
	 
	 public Producteur() {
		 
	 }
 
	 public void ecrireMessage(String messageXML) throws JMSException, NamingException {

	 BasicConfigurator.configure();
	 // Obtention de connexion JMS � partir du serveur
	  ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
	  Connection connection = connectionFactory.createConnection();
	  connection.start();
	  try { 
	   // Creation de session (Messages JMS sont envoy�s et re�us en utilisant une session)
	   Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	  // Destination de la file
	   Destination destination = session.createQueue(subject);
	   // MessageProducer est utilis� pour l'envoi de messages
	   MessageProducer producer = session.createProducer(destination);
	   // le message � transferer
	   TextMessage message = session.createTextMessage(messageXML);
	   // Transfert de message vers la file (� L'ESB)
	   producer.send(message);
	   System.out.println("message envoy� :  " + message.getText() + "");
	  } finally {
	   connection.close();
	  }
 	}

 /**public static void main(String[] args) throws JMSException {
  try {
	  String messageXML = "premier message que j'envoie";
   BasicConfigurator.configure();
   new Producteur().ecrireMessage(messageXML);
  } catch (NamingException e) {
   e.printStackTrace();
  }

 }	**/
}