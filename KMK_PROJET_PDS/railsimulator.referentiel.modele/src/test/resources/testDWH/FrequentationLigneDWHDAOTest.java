package testDWH;

import javax.xml.bind.JAXBException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.xml.sax.SAXException;
import railsimulator.beans.DWHControler;
import dao.FrequentationLigneDWHDAO;
import dao.HibernateUtilsBI;
import beans.FrequentationLigneDWH;
import junit.framework.TestCase;

public class FrequentationLigneDWHDAOTest extends TestCase {
	private Session se = null;
	FrequentationLigneDWHDAO dao = new FrequentationLigneDWHDAO();
	

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	//test d'insertion d'object frequentation
	public void testInsertFrequentation() throws JAXBException, SAXException {
		se = HibernateUtilsBI.getSessionBI();
		//Je recupere le nb existant dans la table Frequentation
    	int nb1 = (Integer) se.createCriteria(FrequentationLigneDWH.class).setProjection(Projections.rowCount()).uniqueResult();
    	//J'insere un nouveau objet
    	dao.insertFrequentation(DWHControler.getObjectFrequentation());
    	//Je recupere le nombre de ligne apres l'insertion
    	int nb2 = (Integer) se.createCriteria(FrequentationLigneDWH.class).setProjection(Projections.rowCount()).uniqueResult();
    	//affectation
    	int nb3 = nb1 +1 ;
    	//comparaison
    	assertEquals(nb3,nb2);
    	
	}

}
