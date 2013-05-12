package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import beans.FrequentationLigneDWH;

public class FrequentationLigneDWHDAO {
	private Session se = null;
	
	public FrequentationLigneDWHDAO() {
	}
// inserer un objet frequentation dans DWH   
    public void insertFrequentation(FrequentationLigneDWH frequentation) {	       
    	se = HibernateUtilsBI.getSessionBI();
    	Transaction t = se.beginTransaction();  	  
	    se.save(frequentation);
	    t.commit();
	    se.close();
	}
}
