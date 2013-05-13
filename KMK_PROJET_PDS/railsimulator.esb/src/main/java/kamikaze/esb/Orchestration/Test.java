package kamikaze.esb.Orchestration;

import java.util.concurrent.TimeUnit;

import org.apache.camel.CamelContext;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
		
		      BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/camelContext.xml");
	
		      CamelContext camel = SpringCamelContext.springCamelContext((ApplicationContext) beanFactory);
	
		      camel.start();
		    } catch (Exception e) {
		    }
	}

}
