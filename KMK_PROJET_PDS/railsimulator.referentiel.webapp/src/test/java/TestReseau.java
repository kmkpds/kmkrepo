
import java.io.IOException;

import org.xml.sax.SAXException;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.HttpUnitOptions;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.httpunit.WebTable;
import com.meterware.servletunit.ServletRunner;

import junit.framework.TestCase;


public class TestReseau extends TestCase {
	ServletRunner sr = new ServletRunner();
	public TestReseau(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testDoGetHttpServletRequestHttpServletResponse() throws IOException, SAXException {
		
		
		
		WebConversation     conversation = new WebConversation();
		HttpUnitOptions.setExceptionsThrownOnScriptError(false);
		WebRequest  request = new GetMethodWebRequest( "http://localhost:8080/RailSimulator/reseau?action=creerReseau");
		
		WebResponse  response = conversation.getResponse(request);

		WebForm forms[] = response.getForms();
		
		//System.out.println( " param " + formulaire.getParameterNames().length);
		assertEquals( 6, forms.length);
		// 200 OK 
	    assertEquals(200, response.getResponseCode());
		assertNotNull( "No response received", response );

	}

	public void testDoPostHttpServletRequestHttpServletResponse() throws IOException, SAXException {
		
		WebConversation     conversation = new WebConversation();
		HttpUnitOptions.setExceptionsThrownOnScriptError(false);
		WebRequest  request = new GetMethodWebRequest( "http://localhost:8080/RailSimulator/reseau?action=creerReseau");
		WebResponse  response = conversation.getResponse(request);

		WebForm formAjouterZone = response.getForms()[3];
		
		request = formAjouterZone.getRequest();
		request.setParameter( "NbrHabt", "50");
		request.setParameter( "NbrMaxStation", "5");
		request.setParameter( "Surface", "2361623.8820757456");		
		//request.setParameter("idReseau", new String[]{"17"});
		request.setParameter( "latitudeZone", "47.92497007243085" );
		request.setParameter( "longitudeZone", "1.9275856018066406" );
		request.setParameter( "latitudeZoneB", "47.915766786845914" );
		request.setParameter( "longitudeZoneB", "1.8966865539550781" );

		response = conversation.getResponse(request);

		assertEquals(200, response.getResponseCode());


		
	}

}
