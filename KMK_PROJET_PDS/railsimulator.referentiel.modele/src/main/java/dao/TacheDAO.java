package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Tache;


public class TacheDAO {
	
	
	private Session se = null;	
	private Tache tache;
	private List<Tache> listeTache;
	
	public void createTache(Tache tache){

		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction();
		se.save(tache);   
        t.commit();
        se.close();
    }


	public Tache getTacheByID(int id) {

    	se = HibernateUtils.getSession();
    	se.beginTransaction();  	
    	tache = (Tache) se.createQuery("from Tache where idtache="+id).uniqueResult();
    	
    	 se.close();

    	  return tache;
    }
	
	
	
	@SuppressWarnings("unchecked")
	public List<Tache> listerTache() {
    	se = HibernateUtils.getSession();
    	se.beginTransaction();  	 	
    	listeTache = se.createQuery("from Tache").list();  
    	
        return listeTache;
    }
	
	 public void supprimerTache(int idtache) {
	    	
	    	se = HibernateUtils.getSession();
	    	se.beginTransaction();
	    	tache = (Tache) se.load(Tache.class, idtache);
	        se.delete(tache);
	        se.beginTransaction().commit();
	        se.close();
	    }
	    
	    public void modifierTache(Tache tache) {
	    	  
	    	se = HibernateUtils.getSession();       
	    	Transaction tr=se.beginTransaction();
	    	se.update(tache);
	    	tr.commit();
	    	se.close();
	    	 
	    }	
	    	

}
