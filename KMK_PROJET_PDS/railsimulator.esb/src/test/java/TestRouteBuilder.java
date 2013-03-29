import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Component;


public class TestRouteBuilder extends CamelTestSupport {
	
	@EndpointInject(uri = "mock:mockSIInfoTerrain")
    protected MockEndpoint mockEndpoint;	
	
	@EndpointInject(uri = "mock:beanOrchestration")
    protected MockEndpoint mockBean;
	
	@EndpointInject(uri = "mock:QUEUESIEvent")
    protected MockEndpoint queueSIEvent;
	
	//kamikaze.esb.bean.OrchestrerMessage mockBean = mock(kamikaze.esb.bean.OrchestrerMessage.class);
    @Produce(uri = "direct:start")
    protected ProducerTemplate template;
    @Produce(uri = "direct:start2")
    protected ProducerTemplate template2;
    
    
   // protected Component comp ;
    
    @Test
    public void testSendMatchingMockInfortrafic() throws Exception {
        String expectedBody = "MockInfoTrafic";

        mockEndpoint.expectedBodiesReceived(expectedBody);

        template.sendBody(expectedBody);
       
        mockEndpoint.assertIsSatisfied();
        
      
       // mockBean.assertExchangeReceived(0);
        System.out.println(mockEndpoint.getEndpointUri());
    }
    
    @Test
    public void testSendMatchingEventMessage() throws Exception {
    String expectedBody = "EnvoiEventMessage";

    queueSIEvent.expectedBodiesReceived(expectedBody);

        template2.sendBody(expectedBody);
       
        queueSIEvent.assertIsSatisfied();
    }
    
   
    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            public void configure() {
                from("direct:start").filter(body().contains("MockInfoTrafic")).bean(mockBean).to("mock:mockSIInfoTerrain");
                from("direct:start2").filter(body().contains("EnvoiEventMessage")).bean(mockBean).to("mock:QUEUESIEvent");
                
            }
        };
    }
    
    @After
    public void tearDown() throws Exception {
        log.info("Testing done: " + this);
        //System.out.println("teardown"+     consumer.getCamelContext());
   
        log.debug("tearDown test");
        if (consumer != null) {
            consumer.stop();
        }
        if (template != null) {
            template.stop();
        }
        stopCamelContext();
    }

	
	

}
