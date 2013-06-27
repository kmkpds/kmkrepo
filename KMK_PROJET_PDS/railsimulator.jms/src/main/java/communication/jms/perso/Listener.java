package communication.jms.perso;

import javax.jms.MessageListener;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.JMSException;
public class Listener implements MessageListener {

 
    public void onMessage(Message message) {
 
        try {
            if (! (message instanceof TextMessage) ) {
            	System.out.println(message.getClass().getName());
            	
                return;
            }
            process((TextMessage)message);
         
        }
        catch (Throwable t) {
            System.err.println((t instanceof JMSException) ? "JMSException" : "Throwable" + " Caught in onMessage(): " + t.getMessage());
        }
       
    }

    private void process(TextMessage message) throws JMSException {    	
       message.acknowledge();
      
    
	
    }

}