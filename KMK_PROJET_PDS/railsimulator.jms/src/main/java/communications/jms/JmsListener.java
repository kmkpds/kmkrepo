package communications.jms;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import beans.Incident;
import beans.Procedure;
import beans.TypeIncident;

import communications.jms.trt.Utils;

public class JmsListener implements MessageListener {

	public void onMessage(Message message) {
		try {
			if (message instanceof TextMessage) {
				process((TextMessage) message);
			}
			if (message instanceof ObjectMessage) {
				process((ObjectMessage) message);
			}
		} catch (Throwable t) {
			System.err
					.println((t instanceof JMSException) ? "JMSException"
							: "Throwable" + " Caught in onMessage(): "
									+ t.getMessage());
		}
	}

	private void process(ObjectMessage message) throws Exception {

		System.out.println(message.getObject());
		Utils.exporterMessageInBase(message.getObject());
		// testinsert();
		message.acknowledge();
		System.out.println("ObjectMessage() received and processed: "
				+ message.getObject().toString());
	}

	private void process(TextMessage message) throws JMSException {
		System.out.println(message.getText());
//		testinsert();
		message.acknowledge();
		System.out.println("onMessage() received and processed: "
				+ message.getText());
	}

	private void testinsert() {
		Incident incident = new Incident();
		incident.setAnnee(2014);
		incident.setMois(12);
		incident.setCriticite("criticite");
		incident.setDateDebut("12/12/2012");
		incident.setDescription("description");
		Procedure p = new Procedure();
		p.setIdProcedure(32);
		incident.setProcedure(p);

		TypeIncident typeIncident = new TypeIncident();
		typeIncident.setIdType(17);
		incident.setTypeIncident(typeIncident);
		try {
			Utils.exporterMessageInBase((Serializable) incident);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
