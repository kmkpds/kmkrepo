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
	private QueueConnectionFactory connectionQueueFactory;
	private QueueConnection queueConnection;
	private QueueSession queueSession;
	private Queue queue;
	private QueueReceiver consommateur;

	public Consommateur(String ip, String nomQueue){

		try {

			connectionQueueFactory = new ActiveMQConnectionFactory(("tcp://") + ip + ":61616"); 

			queueConnection = connectionQueueFactory.createQueueConnection();
			queueConnection.start();
			queueSession = queueConnection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
			queue = queueSession.createQueue(nomQueue);

			//Creation du consommateur sur la queue
			consommateur = queueSession.createReceiver(queue);

		}
		catch (JMSException e1){
			System.out.println(("JMSException dans le constructeur du MessageConsumer du jar jmsPerso : ") + e1.toString());
			e1.printStackTrace();			
		}		
	}

	public void lancer(MessageListener ml) {

		try {
			//Demarrage des consommateurs pour recevoir.
			consommateur.receiveNoWait();

			//Attribution d'un Thread à ces consommateurs pour traiter les messsages de manières asynchrones.
			consommateur.setMessageListener(ml);

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

			consommateur.close();
			queueSession.close();
			queueConnection.close();

		} catch (JMSException e) {
			System.out.println(("JMSException dans la methode arreter() du MessageConsumer du jar jmsPerso : ") + e.toString());
			e.printStackTrace();
		}
	}	
}