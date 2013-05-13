

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

	
	public void testModifierDoGetHttpServletRequestHttpServletResponse() throws IOException, SAXException {

		//test recuperation données 
		
		WebConversation     conversation = new WebConversation();
		HttpUnitOptions.setExceptionsThrownOnScriptError(false);
		WebRequest  request = new GetMethodWebRequest( "http://localhost:8080/RailSimulator/optimisation?idLigne=13&frequence=frequenceSamedi&action=Afficher");
		
		WebResponse  response = conversation.getResponse(request);

        WebForm formModifier = response.getForms()[1];
		//verification si bien form modifier 
		assertEquals("form1", formModifier.getName());
        
        //on récupère httpRequest
		request = formModifier.getRequest();
	
		//on envoie dans la requete les parametres
		request.setParameter( "frequenceLigne", "10" );
		request.setParameter( "idLigne", "13" );
		request.setParameter( "longueurLigne", "60" );
		request.setParameter( "nombreLigne", "10" );
		request.setParameter( "duréeTrajet", "30" );
		request.setParameter( "nombreTrain", "6" );
		request.setParameter( "nombrePassagers", "500000" );
		request.setParameter( "nombreWagon", "5" );
		request.setParameter( "vitesseTrain", "50" );
		request.setParameter( "nombrePlacesa", "400" );
		request.setParameter( "nombrePlacesd", "270" );
		request.setParameter( "nombrePlaces", "670");
	

			
        //on envoie la requete avec les parametres du formulaire et on récupère la reponse
		response = conversation.getResponse(request);

		
		assertEquals(200, response.getResponseCode());

		

	}
	
	
	public void testCalculCoutDoGetHttpServletRequestHttpServletResponse() throws IOException, SAXException {

        //test recuperation données 
		
		WebConversation conversation = new WebConversation();
		HttpUnitOptions.setExceptionsThrownOnScriptError(false);
		WebRequest  request = new GetMethodWebRequest( "http://localhost:8080/RailSimulator/optimisation?action=OK");
		
		WebResponse  response = conversation.getResponse(request);

		request.setParameter( "NouvelleFrequence", "5");
		request.setParameter( "longueurLigne", "670");
		request.setParameter( "vitesseTrain", "670");
		request.setParameter( "prixtrain", "670");
		request.setParameter( "nbrTrain", "670");
		request.setParameter( "tarificationdedeplacement", "670");
		request.setParameter( "Consommationenergie", "670");
		request.setParameter( "freq", "670");
		request.setParameter( "nbVoyageurAncien", "670");
		request.setParameter( "coutMaintenance", "670");
		request.setParameter( "prixplace", "670");
	
			
        //on envoie la requete avec les parametres du formulaire et on récupère la reponse
		response = conversation.getResponse(request);
		
			
		assertEquals(200, response.getResponseCode());
		

	}




}
