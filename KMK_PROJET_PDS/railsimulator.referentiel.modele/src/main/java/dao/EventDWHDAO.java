package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import beans.EventDWH;


public class EventDWHDAO {
	private Session se = null;
	
	
	public EventDWHDAO() {
		// TODO Auto-generated constructor stub
	}
	
	// inserer un objet Event dans DWH   
    public void insertEvent(EventDWH event) {	       
    	se = HibernateUtilsBI.getSessionBI();
    	Transaction t = se.beginTransaction();  	  
	    se.save(event);
	    t.commit();
	    se.close();
	}

}
