package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;


import beans.FactHoraire;

public class FactHoraireDAO {
	
	private Session se = null;	
	private FactHoraire factHoraire;
	private List<FactHoraire> listeFactHoraire;

	public void createFactHoraire(FactHoraire facth){

		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction();
		se.save(facth);   
        t.commit();
        se.close();
    }
	        
        public FactHoraire getFactHoraireByID(int id) {
        	
	    	se = HibernateUtils.getSession();
	    	se.beginTransaction(); 	    	
	    	factHoraire = (FactHoraire) se.createQuery("from FactHoraire where id="+id).uniqueResult();	    	
	    	 se.close();
	    	 
	        return factHoraire;

	    }
  public FactHoraire getUserDailyStatus(int idemp,String date) {
        	
	    	se = HibernateUtils.getSession();
	    	se.beginTransaction(); 	    	
	    	
	    	factHoraire = (FactHoraire) se.createQuery("from FactHoraire where idemp="+idemp+" AND date='"+date+"'").uniqueResult();	    	
	    	
	    	se.close();
	    	 
	        return factHoraire;

	    }
		
		public List<FactHoraire> listerFactHoraire() {
	    	se = HibernateUtils.getSession();
	    	se.beginTransaction();  	 	
	    	listeFactHoraire = se.createQuery("from FactHoraire").list();  
	    	
	        return listeFactHoraire;
	    }
		
		
		@SuppressWarnings("unchecked")
		public List<FactHoraire> getEmpHasStatus(int idemp) {

	    	se = HibernateUtils.getSession();
	    	se.beginTransaction();  	
	    	listeFactHoraire =  se.createQuery("from FactHoraire where idemp="+idemp).list();
	    	
	    	// se.close();

	    	  return listeFactHoraire;
	    }
		
		

		

	}
	

