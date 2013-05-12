package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import beans.IncidentDWH;

public class IncidentDWHDAO {
	private Session se = null;
	
	public IncidentDWHDAO() {
	}
	//inserer un objet incident dans DWH
    public void insertIncident(IncidentDWH incident) {	       
    	se = HibernateUtilsBI.getSessionBI();
    	Transaction t = se.beginTransaction();  	  
	    se.save(incident);
	    t.commit();
	    se.close();
	}

}
