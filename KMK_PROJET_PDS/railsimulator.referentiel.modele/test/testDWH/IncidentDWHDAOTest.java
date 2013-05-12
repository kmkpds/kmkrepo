package testDWH;

import javax.xml.bind.JAXBException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.xml.sax.SAXException;
import servlets.DWHControler;
import beans.IncidentDWH;
import dao.HibernateUtilsBI;
import dao.IncidentDWHDAO;
import junit.framework.TestCase;

public class IncidentDWHDAOTest extends TestCase {
	private Session se = null;
	IncidentDWHDAO dao = new IncidentDWHDAO();
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	//test d'insertion d'object Incident
	public void testInsertIncident() throws JAXBException, SAXException {
		se = HibernateUtilsBI.getSessionBI();
		//Je recupere le nb existant dans la table incident
    	int nb1 = (Integer) se.createCriteria(IncidentDWH.class).setProjection(Projections.rowCount()).uniqueResult();
    	//J'insere un nouveau objet
    	dao.insertIncident(DWHControler.getObjectIncident());
    	//Je recupere le nombre de ligne apres l'insertion
    	int nb2 = (Integer) se.createCriteria(IncidentDWH.class).setProjection(Projections.rowCount()).uniqueResult();
    	//affectation
    	int nb3 = nb1 +1 ;
    	//comparaison
    	assertEquals(nb3,nb2);
	}

}
