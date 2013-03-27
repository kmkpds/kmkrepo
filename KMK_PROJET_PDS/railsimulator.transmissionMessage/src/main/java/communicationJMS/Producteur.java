package communicationJMS;

import javax.jms.*;

import javax.naming.NamingException;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.BasicConfigurator;

/**
 * 
 * @author faycallemseffer
 *
 */

public class Producteur {
		
	// URL du serveur JMS
	 private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	//Nom de la file de production
	 private static String subject = "queueTransmissionMSGTerrain";
	 
	 public Producteur() {
		 
	 }
 
	 public void ecrireMessage(String messageXML) throws JMSException, NamingException {

	 BasicConfigurator.configure();
	 // Obtention de connexion JMS ˆ partir du serveur
	  ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
	  Connection connection = connectionFactory.createConnection();
	  connection.start();
	  try { 
	   // Creation de session (Messages JMS sont envoyŽs et re�us en utilisant une session)
	   Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	  // Destination de la file
	   Destination destination = session.createQueue(subject);
	   // MessageProducer est utilisŽ pour l'envoi de messages
	   MessageProducer producer = session.createProducer(destination);
	   // le message ˆ transferer
	   TextMessage message = session.createTextMessage(messageXML);
	   // Transfert de message vers la file (ˆ L'ESB)
	   producer.send(message);
	   System.out.println("message envoyŽ :  " + message.getText() + "");
	  } finally {
	   connection.close();
	  }
 	}

 /*public static void main(String[] args) throws JMSException {
  try {
	  String messageXML = "premier message que j'envoie";
   BasicConfigurator.configure();
   new Producteur().ecrireMessage(messageXML);
  } catch (NamingException e) {
   e.printStackTrace();
  }

 }	*/
}