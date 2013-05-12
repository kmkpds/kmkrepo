package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import beans.RecetteDWH;

public class RecetteDWHDAO {
	
	private Session se = null;
	public RecetteDWHDAO() {
	}
	// inserer un objet Recette dans DWH   
    public void insertRecette(RecetteDWH recette) {	       
    	se = HibernateUtilsBI.getSessionBI();
    	Transaction t = se.beginTransaction();  	  
	    se.save(recette);
	    t.commit();
	    se.close();
	}
}
