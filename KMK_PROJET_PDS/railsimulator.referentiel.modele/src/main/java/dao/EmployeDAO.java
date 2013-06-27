package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Employe;

public class EmployeDAO {
	
		private Session se = null;	
		private Employe employe;
		private List<Employe> listeEmploye;
		
		public void createEmploye(Employe empl){

			se = HibernateUtils.getSession();
			Transaction t = se.beginTransaction();
			se.save(empl);   
	        t.commit();
	        se.close();
	    }


		public Employe getEmployeByID(int id) {

	    	se = HibernateUtils.getSession();
	    	se.beginTransaction();  	
	    	employe = (Employe) se.createQuery("from Employe where idemp="+id).uniqueResult();
	    	
	    	 se.close();

	    	  return employe;
	    }
		
		@SuppressWarnings("unchecked")
		public List<Employe> listerEmploye() {
	    	se = HibernateUtils.getSession();
	    	se.beginTransaction();  	 	
	    	listeEmploye = se.createQuery("from Employe").list();  
	    	
	        return listeEmploye;
	    }
		
		
		
		 public void supprimerEmploye(int idemp) {
		    	
		    	se = HibernateUtils.getSession();
		    	se.beginTransaction();
		    	employe = (Employe) se.load(Employe.class, idemp);
		        se.delete(employe);
		        se.beginTransaction().commit();
		        se.close();
		    }
		    
		    public void modifierEmploye(Employe employe) {
		    	  
		    	se = HibernateUtils.getSession();       
		    	Transaction tr=se.beginTransaction();
		    	se.update(employe);
		    	tr.commit();
		    	se.close();
		    	 
		    }	
		    
			@SuppressWarnings("unchecked")
			public List<Employe> getEmpInSite( int idsite) {
		    	se = HibernateUtils.getSession();
		    	se.beginTransaction(); 
		    	
		    	listeEmploye = se.createQuery("from Employe where site_idsite="+idsite).list();
		    	 se.close();
		    	
		        return listeEmploye;
		    }
			
		    
	

	}