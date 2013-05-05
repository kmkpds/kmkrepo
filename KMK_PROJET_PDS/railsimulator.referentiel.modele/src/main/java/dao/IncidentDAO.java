package dao;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;


import beans.Incident;
public class IncidentDAO {
	
	private Session se = null;
	private List<Incident> listeIncident;
	
	
	public List<Incident> listerIncident() {
    	se = HibernateUtils.getSession();
    	se.beginTransaction();  	 	
    	listeIncident = se.createQuery("from Incident").list();  
    	
        return listeIncident;
    }
	
	public BigInteger getNbActionTotal(int id) {
		
		se = HibernateUtils.getSession();
		Query query = se.createSQLQuery(
				"select count(*) from incident,procedureintervention,actionintervention where idincident = ? AND incident.procedureintervention_idprocedure=idprocedure AND actionintervention.procedureintervention_idprocedure=idprocedure")
				.setParameter(0, id);	        
		BigInteger result =  (BigInteger) query.uniqueResult();		

	
			return(result);
		
	}
	
	public BigInteger getNbActionFini(int id) {
		
		se = HibernateUtils.getSession();
		Query query = se.createSQLQuery(
				"select count(*) from incident,procedureintervention,actionintervention where idincident = ? AND incident.procedureintervention_idprocedure=idprocedure AND actionintervention.procedureintervention_idprocedure=idprocedure AND actionintervention.datefin is not NULL")
				.setParameter(0, id);	        
		BigInteger result =  (BigInteger) query.uniqueResult();		

	
			return(result);
		
	}
	

}
