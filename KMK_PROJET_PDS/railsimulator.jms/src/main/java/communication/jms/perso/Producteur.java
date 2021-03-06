package communication.jms.perso;

import javax.jms.*;
import javax.naming.NamingException;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;



public class Producteur {
		
	//File local
	//private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	//file VM-BDD
	//private static String url = "tcp://178.33.40.163:61617";
	//File VM-ESB
	private static String url = "tcp://178.33.40.163:61616";
	// accees via internet explorer 
		//http://178.33.40.163:8777/activemqweb/queues.jsp
	
	//Nom de la file de production
	 private static String subject = "Fact_Horaire";
	 
	 public Producteur() {
		 
	 }
	 //envoyer un messsage à l'ESB
	 public void ecrireMessage(String messageXML) throws JMSException, NamingException {


	 // Obtention de connexion JMS e partir du serveur
	  ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
	  Connection connection = connectionFactory.createConnection();
	  connection.start();
	  try { 
	   // Creation de session (Messages JMS sont envoyes et recus en utilisant une session)
	   Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	  // Destination de la file
	   Destination destination = session.createQueue(subject);
	   // MessageProducer est utilise pour l'envoi de messages
	   MessageProducer producer = session.createProducer(destination);
	   // le message  transferer
	   TextMessage message = session.createTextMessage(messageXML);
	   // Transfert de message vers la file ( L'ESB)
	  producer.send(message);   
	  // System.out.println("message envoye :  " + message.getText() + "");
	  } finally {
	   connection.close();
	  }
 	}
}