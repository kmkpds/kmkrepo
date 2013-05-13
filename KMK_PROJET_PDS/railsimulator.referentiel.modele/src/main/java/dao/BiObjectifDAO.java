package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import beans.BiObjectif;

public class BiObjectifDAO {
	private Session se = null;
	private BiObjectif objectif = new BiObjectif();
	
	public List getObjectifAnnee() {
		se = HibernateUtils.getSession();
		Query query = se.createSQLQuery(
				"select DISTINCT annee from biobjectif");
				List result = query.list();	
				
		return(result);
    }
	
	public BiObjectif getObjectif(int annee) {
    	se = HibernateUtils.getSession();
    	se.beginTransaction(); 
    	
    	objectif = (BiObjectif) se.createQuery("from BiObjectif where annee="+annee).uniqueResult();
    	se.close();
    	
        return objectif;
    }

}
