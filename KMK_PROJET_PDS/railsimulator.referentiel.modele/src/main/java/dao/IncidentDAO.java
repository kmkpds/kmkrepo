package dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import beans.Action;
import beans.BiLigne;
import beans.Incident;
import beans.Procedure;
import beans.TypeIncident;

public class IncidentDAO {
	
	private Session se = null;
	private List<Incident> listeIncident;
	private Incident incident;
	
	public List<Incident> listerIncident() {
    	se = HibernateUtils.getSession();
    	se.beginTransaction();  	 	
    	listeIncident = se.createQuery("from Incident").list();  
    	se.close();
    	
        return listeIncident;
    }
	
	public int createIncidentReturnId(int id, String description, String datedebut, int mois, int annee, String datefin, String criticite, Procedure procedure, TypeIncident typeincident, BiLigne biligne){
		
		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction();
		Incident incident = new Incident();
		incident.setIdIncident(id);
		incident.setDescription(description);
		incident.setDateDebut(datedebut); 
		incident.setDateFin(datefin);
		incident.setMois(mois);
		incident.setAnnee(annee);
		incident.setCriticite(criticite);
		incident.setProcedure(procedure);
		incident.setTypeIncident(typeincident);
		incident.setBiLigne(biligne);
		int idIncident = (Integer) se.save(incident);
        
        t.commit();
        se.close();

        return idIncident;
		
	}
	
	public void modifierIncident(Incident incident){
    	se = HibernateUtils.getSession();
    	Transaction t = se.beginTransaction();
    	se.update(incident);
    	t.commit();
    	se.close();
    	
    }
	
	public Incident getIncidentByID(int id) {
    	se = HibernateUtils.getSession();
    	se.beginTransaction(); 
    	
    	incident = (Incident) se.createQuery("from Incident where idIncident="+id).uniqueResult();
    	 se.close();
    	
        return incident;
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
