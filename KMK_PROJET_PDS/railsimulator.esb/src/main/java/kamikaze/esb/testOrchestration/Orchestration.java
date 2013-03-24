package kamikaze.esb.testOrchestration;

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
		
            from("activemq://BDF2ServiceMix").bean(OrchestrerMessage.class).to("activemq://ServiceMix2SI"); //

	}//fin configure

}
