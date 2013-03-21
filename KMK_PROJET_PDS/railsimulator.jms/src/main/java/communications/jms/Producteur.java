package communications.jms;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.StringTokenizer;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Producteur {

	//Variables de fonctionnement
	private QueueConnectionFactory  connectionQueueFactory;
	private QueueConnection queueConnection;
	private QueueSession queueSession;
	private Queue destinationQueue;
	private QueueSender producer;
	
	private Decodeur monDecodeur;

	public Producteur(String ipEnvoi, String nomQueue, String pathSchema, String pathModel) {

		try {
			connectionQueueFactory = new ActiveMQConnectionFactory(("tcp://") + ipEnvoi + ":61616");
			queueConnection = connectionQueueFactory.createQueueConnection();
			queueConnection.start();
			queueSession = queueConnection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
			destinationQueue = queueSession.createQueue(nomQueue);
			producer = queueSession.createSender(destinationQueue);
			monDecodeur = new Decodeur(pathSchema, pathModel);

		} catch (JMSException e) {
			System.out.println(("JMSException dans le constructeur du MessageProducer du jar jmsPerso :") + e.toString());
			e.printStackTrace();
		}
	}

	public TextMessage creerTextMessage() throws JMSException{
		return queueSession.createTextMessage();
	}

	//Envoi du TextMessage
	public void envoyer(Object o) {
		try {		
			
			TextMessage tm = queueSession.createTextMessage();
			tm.setText(monDecodeur.objetToXML(o));
			tm.setJMSCorrelationID(myIP());
			producer.send(tm);
		} catch (JMSException e) {
			System.out.println(("JMSException dans le méthode envoyer() du MessageProducer du jar jmsPerso :") + e.toString());
			e.printStackTrace();
		}
	}

	public void envoyer(TextMessage tmm) {
		try {		
			tmm.setJMSCorrelationID(myIP());
			producer.send(tmm);
		} catch (JMSException e) {
			System.out.println(("JMSException dans le méthode envoyer() du MessageProducer du jar jmsPerso :") + e.toString());
			e.printStackTrace();
		}
	}
	

	public void arreter(){

		try {

			producer.close();
			queueSession.close();
			queueConnection.close();

		} catch (JMSException e) {
			System.out.println(("JMSException dans le méthode arreter() du MessageProducer du jar jmsPerso :") + e.toString());
			e.printStackTrace();
		}
	}

	
	/*
	 * 
	 * 
	 * Méthodes permettant de récuperer des paramètres interessants.
	 * 
	 * 
	 */


	//Permet de recuperer son IP
	public String myIP(){

		String monIP = ("");
		String s;

		try {
			s = (InetAddress.getLocalHost()).toString();
			StringTokenizer st = new StringTokenizer(s, ("/") );

			while(st.hasMoreTokens()){
				monIP = st.nextToken(); 
			}
		} catch (UnknownHostException e) {
			System.out.println(("UnknownHostException dans la methode myIP() du MessageProducer du jar jmsPerso : ") + e.toString());
			e.printStackTrace();
		} 
		return monIP;
	}
}
