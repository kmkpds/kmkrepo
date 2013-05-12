package communication.jms.dwh;

import javax.jms.MessageListener;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.JMSException;
public class ListenerDWH implements MessageListener {

 //demmare process
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
    //preparation process
    private void process(TextMessage message) throws JMSException {    	
       message.acknowledge();
      
    
	
    }

}