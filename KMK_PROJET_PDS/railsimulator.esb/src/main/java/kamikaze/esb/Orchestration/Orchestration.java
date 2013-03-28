package kamikaze.esb.Orchestration;

import java.util.List; 
import java.util.Map;

import kamikaze.esb.bean.OrchestrerMessage;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.Synchronization;
import org.apache.camel.spi.UnitOfWork;

public class Orchestration extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
            from("activemq://BDF2ServiceMix").choice().when(body().contains("MockInfoTrafic"))
            .bean(OrchestrerMessage.class).to("activemq://SIInfoTrafic")
            .when(body().contains("EventMessage"))
            .bean(OrchestrerMessage.class).to("activemq://SIEventMessage")
            .when(body().contains("SimpleMessage"))
            .bean(OrchestrerMessage.class).to("activemq://SIIncSimpleMessage")
            .when(body().contains("CritiqueMessage"))
            .bean(OrchestrerMessage.class).to("activemq://SIIncCritiqueMessage")
            .otherwise().bean(OrchestrerMessage.class).to("activemq://Autrequeue"); //

	}//fin configure
}
