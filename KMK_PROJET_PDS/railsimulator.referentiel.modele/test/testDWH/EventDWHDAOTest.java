package testDWH;

import javax.xml.bind.JAXBException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.xml.sax.SAXException;
import servlets.DWHControler;
import beans.EventDWH;
import dao.EventDWHDAO;
import dao.HibernateUtilsBI;
import junit.framework.TestCase;

public class EventDWHDAOTest extends TestCase {
	private Session se = null;
	EventDWHDAO dao = new EventDWHDAO();

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	//test d'insertion d'objetevent
	public void testInsertEvent() throws JAXBException, SAXException {
		se = HibernateUtilsBI.getSessionBI();
		//Je recupere le nb existant dans la table Event
    	int nb1 = (Integer) se.createCriteria(EventDWH.class).setProjection(Projections.rowCount()).uniqueResult();
    	//J'insere un nouveau objet
    	dao.insertEvent(DWHControler.getObjectEvent());
    	//Je recupere le nombre de ligne apres l'insertion
    	int nb2 = (Integer) se.createCriteria(EventDWH.class).setProjection(Projections.rowCount()).uniqueResult();
    	//affectation
    	int nb3 = nb1 +1 ;
    	//comparaison
    	assertEquals(nb3,nb2);
	}

}
