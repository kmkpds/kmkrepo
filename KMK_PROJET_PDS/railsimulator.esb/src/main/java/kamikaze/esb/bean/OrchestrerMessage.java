package kamikaze.esb.bean;
import java.util.ArrayList;    
import java.util.List;
import kamikaze.esb.jms.TransformationMessage;
import kamikaze.esb.model.*;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.camel.InOnly;
//import org.apache.camel.component.jms.JmsConfiguration;


public class OrchestrerMessage{
	private String model="kamikaze.esb.model"; 
	private String sch="src/main/resources/MockISIDIS.xsd"; 
	private TransformationMessage reader ;
	
	
	public OrchestrerMessage(){
		reader = new TransformationMessage(model,sch);
	}
	
	@Handler
	public void process(Exchange exchange) {

		String body = (String)exchange.getIn().getBody();
		Object obj = reader.xmlToObject(body);
		
		if (obj instanceof ActionAckMessage){
			ActionAckMessage mess= (ActionAckMessage)obj;
			mess.isSetLocation();
			body = reader.objectToXML(mess);	
			System.out.println("body" +body.toString());
		}
		if (obj instanceof ActionMessage){
			ActionMessage mess= (ActionMessage)obj;
			mess.isSetLocation();
			body = reader.objectToXML(mess);	
			System.out.println("body" +body.toString());
		}
		if (obj instanceof EventMessage){
			EventMessage mess= (EventMessage)obj;
			mess.isSetLocation();
			body = reader.objectToXML(mess);	
			System.out.println("body" +body.toString());
		}
		if (obj instanceof IncidentMessage){
			IncidentMessage mess= (IncidentMessage)obj;
			mess.isSetLocation();
			body = reader.objectToXML(mess);	
			System.out.println("body" +body.toString());
		}
		if (obj instanceof SendInfrastructure){
			SendInfrastructure mess= (SendInfrastructure)obj;
			Infrastructure infra=mess.getObjet();
			infra.isSetLines();
			infra.isSetStations();
			infra.isSetTrains();
			mess.setObjet(infra);
			body = reader.objectToXML(mess);	
			System.out.println("body" +body.toString());
		}
			
		 exchange.getIn().setBody(body);
		
		
	}//fin fonction process


}//fin classe OrchestrerMessage
