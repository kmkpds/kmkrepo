package kamikaze.esb.bean;
import java.util.ArrayList; 
import java.util.List;

import kamikaze.esb.jms.GenerateXml;
import kamikaze.esb.jms.TransformationMessage;
import kamikaze.esb.model.Capteur;
import kamikaze.esb.model.EnvoyerEventServiceMixToSI;
import kamikaze.esb.model.EnvoyerEventToServiceMix;
import kamikaze.esb.model.Event;
import kamikaze.esb.model.EventType;
import kamikaze.esb.model.Train;
import kamikaze.esb.model.Wagon;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.camel.InOnly;


public class OrchestrerMessage{
	private String model="kamikaze.esb.model"; //kamikaze-bundle.model";
	private String sch="./deploy/xsd/MockTrainV3.xsd"; 
	private TransformationMessage reader ;
	
	
	public OrchestrerMessage(){
		reader = new TransformationMessage(model,sch);
	}
	
	@Handler
	public void process(Exchange exchange) {
//System.out.println("dans process");
		String body = (String)exchange.getIn().getBody();
		Object obj = reader.xmlToObject(body);
//System.out.println("Object obj = reader;");
		if (obj instanceof EnvoyerEventToServiceMix){
//System.out.println("dans instanceof");
			EnvoyerEventToServiceMix event=(EnvoyerEventToServiceMix)obj;
			Event e=event.getMess();
			EnvoyerEventServiceMixToSI event2SI=new EnvoyerEventServiceMixToSI();
//System.out.println("apres instanc event2si");		
			if (e.getCriticite()==1){
				event2SI.setLigneOrWagonOrTrain(e.getCapteur().getLigneOrWagonOrTrain());
				event2SI.setMess(e);
				//System.out.println("criticité ok");
			}
			else {
				event2SI.setMess(null);
				//System.out.println("criticité not ok");
			}

			body = reader.objectToXML(event2SI);	
			System.out.println("body" +body.toString());
		}
		
		exchange.getIn().setBody(body);//exchange.getOut().setBody(body);//exchange.getIn().setBody(body);
		//System.out.println("apres echange");
		
	}//fin fonction process
	


}//fin classe OrchestrerMessage
