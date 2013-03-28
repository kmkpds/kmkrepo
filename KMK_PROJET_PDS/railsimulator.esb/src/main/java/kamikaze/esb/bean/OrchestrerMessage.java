package kamikaze.esb.bean;
import java.util.ArrayList;    
import java.util.List;
import kamikaze.esb.jms.TransformationMessage;
import kamikaze.esb.model.Capteur;
import kamikaze.esb.model.EnvoiEventMessage;
import kamikaze.esb.model.EnvoiEventMessageToSI;
import kamikaze.esb.model.EnvoiIncCritiqueMessage;
import kamikaze.esb.model.EnvoiIncCritiqueMessageToSI;
import kamikaze.esb.model.EnvoiIncSimpleMessage;
import kamikaze.esb.model.EnvoiIncSimpleMessageToSI;
import kamikaze.esb.model.EnvoiInfoTraficToSI;
import kamikaze.esb.model.EventMessage;
import kamikaze.esb.model.EventType;
import kamikaze.esb.model.IncCritiqueMessage;
import kamikaze.esb.model.IncSimpleMessage;
import kamikaze.esb.model.Ligne;
import kamikaze.esb.model.MockInfoTrafic;
import kamikaze.esb.model.Train;
import kamikaze.esb.model.Wagon;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.camel.InOnly;


public class OrchestrerMessage{
	private String model="kamikaze.esb.model"; //kamikaze-bundle.model";
	private String sch="./deploy/xsd/MockISIDIS.xsd"; //MockTrainV3
	private TransformationMessage reader ;
	
	
	public OrchestrerMessage(){
		reader = new TransformationMessage(model,sch);
	}
	
	@Handler
	public void process(Exchange exchange) {

		String body = (String)exchange.getIn().getBody();
		Object obj = reader.xmlToObject(body);

		if (obj instanceof MockInfoTrafic){
			MockInfoTrafic mock= (MockInfoTrafic)obj;
			List<Ligne> ligne = new ArrayList<Ligne>();
			
			ligne = mock.getLigne();
			EnvoiInfoTraficToSI envInfo2si=new EnvoiInfoTraficToSI();
			
			for(Ligne i:ligne){
				System.out.println("ligne =>" +i.getNom());	
				envInfo2si.getListeLignes().add(i);
			}
			
			body = reader.objectToXML(envInfo2si);	
			System.out.println("body" +body.toString());
		}
		if (obj instanceof EnvoiEventMessage){
			EnvoiEventMessage event= (EnvoiEventMessage)obj;
			List<EventMessage> listevent = new ArrayList<EventMessage>();
			
			listevent = event.getEventmess();
			EnvoiEventMessageToSI envEvent2si=new EnvoiEventMessageToSI();
			
			for(EventMessage i:listevent){
				System.out.println("eventType =>" +i.getType());	
				envEvent2si.getEventmessage().add(i);
			}
			
			body = reader.objectToXML(envEvent2si);	
			System.out.println("body" +body.toString());
		}
		if (obj instanceof EnvoiIncCritiqueMessage){
			EnvoiIncCritiqueMessage critique= (EnvoiIncCritiqueMessage)obj;
			List<IncCritiqueMessage> listeCritiqueMessage = new ArrayList<IncCritiqueMessage>();
			
			listeCritiqueMessage = critique.getCritiquemessage();
			EnvoiIncCritiqueMessageToSI envIncCritique2si=new EnvoiIncCritiqueMessageToSI();
			
			for(IncCritiqueMessage i:listeCritiqueMessage){
				System.out.println("incidentCritique =>" +i.getType());	
				envIncCritique2si.getCritiquemessage().add(i);
			}
			
			body = reader.objectToXML(envIncCritique2si);	
			System.out.println("body" +body.toString());
		}
		if (obj instanceof EnvoiIncSimpleMessage){
			EnvoiIncSimpleMessage simple= (EnvoiIncSimpleMessage)obj;
			List<IncSimpleMessage> listeIncSimple = new ArrayList<IncSimpleMessage>();
			
			listeIncSimple = simple.getSimpleMessage();
			EnvoiIncSimpleMessageToSI envSimple2si=new EnvoiIncSimpleMessageToSI();
			
			for(IncSimpleMessage i:listeIncSimple){
				System.out.println("incidentSimple =>" +i.getType());	
				envSimple2si.getSimplemessage().add(i);
			}
			
			body = reader.objectToXML(envSimple2si);	
			System.out.println("body" +body.toString());
		}
		
		 exchange.getIn().setBody(body);//exchange.getOut().setBody(body);//exchange.getIn().setBody(body);
		//System.out.println("apres echange");
		
		
	}//fin fonction process
	


}//fin classe OrchestrerMessage
