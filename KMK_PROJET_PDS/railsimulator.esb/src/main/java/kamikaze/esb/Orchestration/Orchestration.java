package kamikaze.esb.Orchestration;

import java.util.List; 
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

        from("activemq://BDF2ServiceMix")
        .resequence(header("JMSPriority")).batch().timeout(3000).allowDuplicates().reverse()       
        .choice()
        .when(body().contains("MockInfoTrafic"))
        .bean(OrchestrerMessage.class).to(bundle.getString("SIInfoTrafic"))
        .when(body().contains("EventMessage"))
        .bean(OrchestrerMessage.class).to(bundle.getString("SIEventMessage"))
        .when(body().contains("SimpleMessage"))
        .bean(OrchestrerMessage.class).to(bundle.getString("SIIncSimpleMessage"))
        .when(body().contains("CritiqueMessage"))
        .bean(OrchestrerMessage.class).to(bundle.getString("SIIncCritiqueMessage"))
        .otherwise().bean(OrchestrerMessage.class).to(bundle.getString("Autrequeue")); 

	}//fin configure
}
