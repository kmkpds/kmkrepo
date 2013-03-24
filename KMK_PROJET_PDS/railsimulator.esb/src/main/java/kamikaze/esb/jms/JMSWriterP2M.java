package kamikaze.esb.jms;

import java.util.ResourceBundle;      

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;



public class JMSWriterP2M {
	//ResourceBundle bundle  = ResourceBundle.getBundle("jndi");
 	
		public void SendFile(Object obj) {
 		
			GenerateXml  xml = new GenerateXml();
 		
  
 		String brokerURL = "tcp://localhost:61616";
 
		ConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);

		try {
			
			Connection connex = factory.createConnection();
			Session mqSession = connex.createSession(false, Session.AUTO_ACKNOWLEDGE);
			//queue BDF2ServiceMix
			Destination messageQueue = mqSession.createQueue("BDF2ServiceMix"); //QueueSend
			MessageProducer mp = mqSession.createProducer(messageQueue);
			
			String aut = xml.genererFile(obj);
			
			Message message	= mqSession.createTextMessage(aut);
			mp.send(message);

			
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}
		//Message est complextype
		public void send(Message us) {
			// TODO Auto-generated method stub
			GenerateXml  xml = new GenerateXml();
	 		
			  
	 		String brokerURL = "tcp://localhost:61616";
	 
			ConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);

			try {
				
				Connection connex = factory.createConnection();
				Session mqSession = connex.createSession(false, Session.AUTO_ACKNOWLEDGE);
				Destination messageQueue = mqSession.createQueue("BDF2ServiceMix");
				MessageProducer producer = mqSession.createProducer(messageQueue);
				String aut = xml.genererFile(us);
				
				Message message	= mqSession.createTextMessage(aut);
				//message.setJMSCorrelationID(string)
				producer.send(message);
			} catch (JMSException e) {
				e.printStackTrace();
			}

		}//fin void send(message us)
		
}//fin classe


