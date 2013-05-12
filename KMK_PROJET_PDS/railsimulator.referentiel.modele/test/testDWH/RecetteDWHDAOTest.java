package testDWH;

import javax.xml.bind.JAXBException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.xml.sax.SAXException;
import servlets.DWHControler;
import beans.RecetteDWH;
import dao.HibernateUtilsBI;
import dao.RecetteDWHDAO;
import junit.framework.TestCase;

public class RecetteDWHDAOTest extends TestCase {
	private Session se = null;
	RecetteDWHDAO dao = new RecetteDWHDAO();

	//test d'insertion d'objet Recette
	public void testInsertRecette() throws JAXBException, SAXException {
		se = HibernateUtilsBI.getSessionBI();
		//Je recupere le nb existant dans la table recette
    	int nb1 = (Integer) se.createCriteria(RecetteDWH.class).setProjection(Projections.rowCount()).uniqueResult();
    	//J'insere un nouveau objet
    	dao.insertRecette(DWHControler.getObjectRecette());
    	//Je recupere le nombre de ligne apres l'insertion
    	int nb2 = (Integer) se.createCriteria(RecetteDWH.class).setProjection(Projections.rowCount()).uniqueResult();
    	//affectation
    	int nb3 = nb1 +1 ;
    	//comparaison
    	assertEquals(nb3,nb2);
	}

}
