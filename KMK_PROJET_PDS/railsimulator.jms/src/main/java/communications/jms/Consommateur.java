package communications.jms;
import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Consommateur {


	//Variables de fonctionnement
	private String brokerURL;
	private QueueConnectionFactory connectionQueueFactory;
	private QueueConnection queueConnection;
	private QueueSession queueSession;
	private Queue queue;
	private QueueReceiver messageReceive;

	public Consommateur(String ip, String nomQueue){

		try {

			//Identification du provider JMS
			brokerURL = "tcp://" + ip + ":61616";
			//Factory de QueueConnections
			connectionQueueFactory = (QueueConnectionFactory) new ActiveMQConnectionFactory(brokerURL);
			//Création d'un objet Queueconnections
			queueConnection = connectionQueueFactory.createQueueConnection();
			//Connexion au provider
			queueConnection.start();
			queueSession = queueConnection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
			//Nom de la file réceptionnée
			queue = queueSession.createQueue(nomQueue);
			//Récupération du message
			messageReceive = queueSession.createReceiver(queue);

		}
		catch (JMSException e1){
			System.out.println(("JMSException dans le constructeur du MessageConsumer du jar jmsPerso : ") + e1.toString());
			e1.printStackTrace();			
		}		
	}

	public void lancer(MessageListener ml) {

		try {
			//Demarrage des consommateurs pour recevoir.
			messageReceive.receiveNoWait();

			//Attribution d'un Thread à ces consommateurs pour traiter les messsages de manières asynchrones.
			messageReceive.setMessageListener(ml);

		}
		catch (JMSException e){
			System.out.println(("JMSException dans la methode lancer() du MessageConsumer du jar jmsPerso : ") + e.toString());
			e.printStackTrace();
		} 
		catch (NullPointerException e2){
			System.out.println(("NullPointerException dans la methode lancer() du MessageConsumer du jar jmsPerso : ") + e2.toString());
			e2.printStackTrace();		
		}
	}


	public void arreter(){

		try {

			messageReceive.close();
			queueSession.close();
			queueConnection.close();

		} catch (JMSException e) {
			System.out.println(("JMSException dans la methode arreter() du MessageConsumer du jar jmsPerso : ") + e.toString());
			e.printStackTrace();
		}
	}	
}