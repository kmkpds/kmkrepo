package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.BI.HibernateUtilsBiAuto;
import beans.FrequentationLigneDWH;

public class FrequentationLigneDWHDAO {
	private Session se = null;
	
	public FrequentationLigneDWHDAO() {
	}
// inserer un objet frequentation dans DWH   
    public void insertFrequentation(FrequentationLigneDWH frequentation) {	       
    	se = HibernateUtilsBiAuto.getSession();
    	Transaction t = se.beginTransaction();  	  
	    se.save(frequentation);
	    t.commit();
	    se.close();
	}
}
