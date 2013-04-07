package railsimulator.transmissionMSG.communicationJMS;


import javax.jms.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.BasicConfigurator;

public class Consommateur{
	
// URL du serveur JMS
 private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

	
 	//private static String url = "tcp://178.33.40.163:61616";

 // Nom de la file de reception
 private static String subject = "queueTransmissionMSGTerrain";
 
 public Consommateur() {
	
 }
 public void lireMessage() throws JMSException {
	  BasicConfigurator.configure();
	  // Obtention de connexion JMS ˆ partir du serveur
	  ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
	  Connection connection = connectionFactory.createConnection();
	  connection.start();

	  // Creation de session (Messages JMS sont envoyŽs et reus en utilisant une session)
	  Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

	  // Destination de la file
	  Destination destination = session.createQueue(subject);

	  // MessageConsumer est utilisŽ pour recevoir de messages
	  MessageConsumer consumer = session.createConsumer(destination);

	  // Reception de Message
	  Message message = consumer.receive();

	  // L'obtention d'acces au message
	  if (message instanceof TextMessage) {
	   TextMessage textMessage = (TextMessage) message;
	   System.out.println("message Reu :" + textMessage.getText()
	     + "");
	  }
	  else {
		  //Si la file est vide ==> afficher "File vide"
		System.out.println("file vide");
	}
	  
	  connection.close();
 }
 
 
 public static void main(String[] args)  {

	 Consommateur cons = new Consommateur();
	 	try {
			cons.lireMessage();
		} catch (JMSException e) {
			e.printStackTrace();
		}
 }
 	
}