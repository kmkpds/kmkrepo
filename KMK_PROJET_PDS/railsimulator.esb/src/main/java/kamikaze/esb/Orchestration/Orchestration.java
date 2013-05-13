package kamikaze.esb.Orchestration;


import java.util.Map;
import java.util.ResourceBundle;

import kamikaze.esb.bean.OrchestrerMessage;
import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Expression;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsConfiguration;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.apache.camel.spi.Synchronization;
import org.apache.camel.spi.UnitOfWork;

public class Orchestration extends RouteBuilder {

	public ResourceBundle bundle  = ResourceBundle.getBundle("jndi");

	@Override
	public void configure() throws Exception { 
		from(bundle.getString("BDF2ServiceMix"))
		.to(bundle.getString("QueueReceive"))
		.log("RTDG TO RTDRS recu");
		
        from(bundle.getString("QueueReceive"))
        //.resequence(header("JMSPriority")).batch().timeout(3000).allowDuplicates().reverse()       
        .choice()
        .when(body().contains("ActionAckMessage"))
        .bean(OrchestrerMessage.class).to(bundle.getString("SIActionAckMessage"))
        .when(body().contains("EventMessage"))
        .bean(OrchestrerMessage.class).to(bundle.getString("SIEventMessage"))
        .when(body().contains("IncidentMessage"))
        .bean(OrchestrerMessage.class).to(bundle.getString("SIIncidentMessage"))
        .when(body().contains("ActionMessage"))
        .bean(OrchestrerMessage.class).to(bundle.getString("SIActionMessage")) //mettre ServiceMix2B2F pr voir qu'on ecrit ds la file alors qu'ici elle consomme les messages en ecoute
        .otherwise().bean(OrchestrerMessage.class).to(bundle.getString("Autrequeue")); 
       
 
	}//fin configure
}
