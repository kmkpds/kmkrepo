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
public class TestOptimisation extends TestCase {

	ServletRunner sr = new ServletRunner();
	public TestOptimisation(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testDoGetHttpServletRequestHttpServletResponse() throws IOException, SAXException {
		// test form 1 
		
		WebConversation     conversation = new WebConversation();
		HttpUnitOptions.setExceptionsThrownOnScriptError(false);
		WebRequest  request = new GetMethodWebRequest( "http://localhost:8080/RailSimulator/optimisation?action=optimisationParc");
		
		WebResponse  response = conversation.getResponse(request);

		WebForm forms[] = response.getForms();
		
		//System.out.println( " param " + formulaire.getParameterNames().length);
		assertEquals( 2, forms.length);
		// 200 OK 
	    assertEquals(200, response.getResponseCode());
		assertNotNull( "No response received", response );
		
		

	}
	
	public void testAfficherDoGetHttpServletRequestHttpServletResponse() throws IOException, SAXException {

		//test recuperation données 
		
		WebConversation     conversation = new WebConversation();
		HttpUnitOptions.setExceptionsThrownOnScriptError(false);
		WebRequest  request = new GetMethodWebRequest( "http://localhost:8080/RailSimulator/optimisation?action=optimisationParc");
		
		WebResponse  response = conversation.getResponse(request);

        WebForm formAfficher = response.getForms()[0];
		//verification si bien form afficher 
		assertEquals("formAfficher", formAfficher.getName());
        
        //on récupère httpRequest
		request = formAfficher.getRequest();
		//on envoie dans la requete le parametre frequence
		request.setParameter( "frequence", "frequenceSamedi" );
			
        //on envoie la requete avec les parametres du formulaire et on récupère la reponse
		response = conversation.getResponse(request);
		
		
		assertEquals(200, response.getResponseCode());

		

	}

	



}
