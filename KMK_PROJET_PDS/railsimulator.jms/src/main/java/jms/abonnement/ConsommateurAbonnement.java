package jms.abonnement;

import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ConsommateurAbonnement {


	//File local
	//private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	//file VM-BDD
	//private static String url = "tcp://178.33.40.163:61617";
	//File VM-ESB
	//private static String url = "tcp://178.33.40.163:61616";
	private static String url = "tcp://178.33.40.163:61616";
	
	//Nom de la file de production
	
	private QueueConnectionFactory connectionQueueFactory;
	private QueueConnection queueConnection;
	private QueueSession queueSession;
	private Queue queue;
	private QueueReceiver messageReceive;

	//Consommateur de message
	public ConsommateurAbonnement(String nomQueue) {
			
		try {
		
				connectionQueueFactory = (QueueConnectionFactory) new ActiveMQConnectionFactory("tcp://178.33.40.163:61616");
				queueConnection = connectionQueueFactory.createQueueConnection();
				queueConnection.start();
				queueSession = queueConnection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
				queue = queueSession.createQueue(nomQueue);
				messageReceive = queueSession.createReceiver(queue);
		
		}catch (JMSException e1){
			System.out.println(("JMSException dans le constructeur du MessageConsumer du jar jmsPerso : ") + e1.toString());
			e1.printStackTrace();			
			}
		
	}
	//methode lancer le listener
	public 	void lancer(MessageListener ml) {
		
		try {
			messageReceive.receiveNoWait();
			messageReceive.setMessageListener(ml);
		
		
		}	catch (JMSException e){
			System.out.println(("JMSException dans la methode lancer() du MessageConsumer du jar jmsPerso : ") + e.toString());
			e.printStackTrace();
		} catch (NullPointerException e2){
			System.out.println(("NullPointerException dans la methode lancer() du MessageConsumer du jar jmsPerso : ") + e2.toString());
			e2.printStackTrace();		
		}
	
	}
	//arreter la consommation
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