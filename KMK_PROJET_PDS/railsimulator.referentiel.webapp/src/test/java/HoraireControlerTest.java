
import java.io.IOException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.xml.sax.SAXException;

import beans.Ligne;
import beans.Reseau;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.HttpUnitOptions;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.httpunit.WebTable;
import com.meterware.servletunit.ServletRunner;

import dao.HibernateUtils;
import dao.LigneDAO;
import dao.ReseauDAO;
import junit.framework.TestCase;
public class HoraireControlerTest extends TestCase {
	
	ServletRunner sr = new ServletRunner();
	private Session se = null;
	public HoraireControlerTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}
	public void testDoGetHttpServletRequestHttpServletResponse() throws IOException, SAXException {

		//httpunit mock une servlet et servletrunner lance ce "mock"
		WebConversation     conversation = new WebConversation();
		HttpUnitOptions.setExceptionsThrownOnScriptError(false);
		WebRequest  request = new GetMethodWebRequest( "http://localhost:8080/RailSimulator/horaire?action=creerHoraire");
		WebResponse  response = conversation.getResponse(request);
		WebForm forms[] = response.getForms();
		assertEquals( 1, forms.length);
		// 200 OK 
	    assertEquals(200, response.getResponseCode());
		assertNotNull( "No response received", response );

	}

	public void testDoPostHttpServletRequestHttpServletResponse() throws IOException, SAXException {
		//constructeur de la servlet
		WebConversation     conversation = new WebConversation();
		HttpUnitOptions.setExceptionsThrownOnScriptError(false);
		WebRequest  request = new GetMethodWebRequest( "http://localhost:8080/RailSimulator/horaire?action=creerHoraire");
		WebResponse  response = conversation.getResponse(request);
		//récupère le formulaire
		WebForm formCreerHoraire = response.getForms()[0];
		
		LigneDAO ligne_dao = new LigneDAO();
		ReseauDAO reseau_dao = new ReseauDAO();
		int idReseau = reseau_dao.createReseauReturnId("Test Horaire Controller Reseau");
		Reseau res = new Reseau();
		res = reseau_dao.getReseauByID(idReseau);
		int idLigne = ligne_dao.createLigneReturnId("Test Horaire Controller", "Commentaire", res);
		Ligne ligne = new Ligne();
		ligne = ligne_dao.getLigneByID(idLigne);
		request = formCreerHoraire.getRequest();
		
		request.setParameter("heuresPointeJO",new String[]{"heuresPointeJO910"});
		request.setParameter("heuresPointeSamedi",new String[]{"heuresPointeSamedi1011"});
		request.setParameter("heuresPointeDimancheJF",new String[]{"heuresPointeDimancheJF67"});
		
		//request.setParameter("idLigne",String.valueOf(ligne.getIdLigne()));
		request.setParameter("vitesseMoyenne","80");
		request.setParameter("heurePTJO","1");
		request.setParameter("minutePTJO","1");
		request.setParameter("heurePTSamedi","2");
		request.setParameter("minutePTSamedi","2");
		request.setParameter("heurePTDimancheJF","3");
		request.setParameter("minutePTDimancheJF","3");
		request.setParameter("heureDTJO","4");
		request.setParameter("minuteDTJO","4");
		request.setParameter("heureDTSamedi","5");
		request.setParameter("minuteDTSamedi","5");
		request.setParameter("heureDTDimancheJF","6");
		request.setParameter("minuteDTDimancheJF","6");
		request.setParameter("cadencementJOMin","1");
		request.setParameter("cadencementJOSec","15");
		request.setParameter("cadencementSamediMin","8");
		request.setParameter("cadencementSamediSec","30");
		request.setParameter("cadencementDimancheJFMin","9");
		request.setParameter("cadencementDimancheJFSec","45");
		request.setParameter("tempsStationnementJOMin","10");
		request.setParameter("tempsStationnementJOSec","10");
		request.setParameter("tempsStationnementSamediMin","11");
		request.setParameter("tempsStationnementSamediSec","11");
		request.setParameter("tempsStationnementDimancheJFMin","12");
		request.setParameter("tempsStationnementDimancheJFSec","12");
		
		response = conversation.getResponse(request);
		//nouvelle purge de la table Ligne & ParametreHoraire
		se = HibernateUtils.getSession();
		Transaction t4 = se.beginTransaction();
		Query delete4=se.createQuery("delete from ParametreHoraire");		
		delete4.executeUpdate();
		Query delete3=se.createQuery("delete from Ligne");		
		delete3.executeUpdate();	
		t4.commit();
		se.close();
		assertEquals(200, response.getResponseCode());	
	}

}
