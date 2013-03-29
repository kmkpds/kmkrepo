package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;


import beans.Reseau;
import beans.Station;


public class ReseauDAO {	
	private Session se = null;
	private List<Reseau> listeReseau;
	private Reseau reseau = new Reseau() ;
	
	public ReseauDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public void createReseau (String nomReseau){
		
		 se = HibernateUtils.getSession();
	     Transaction t = se.beginTransaction();
	     reseau.setNomReseau(nomReseau);
	     se.save(reseau);
	     t.commit();
	     se.close();
	
	  
	    
	}
	
	
	public Reseau getReseauByID(int id) {
    	se = HibernateUtils.getSession();
    	se.beginTransaction(); 
    	reseau = (Reseau) se.createQuery("from Reseau where idReseau="+id).uniqueResult();
    	se.close();
    	return reseau;
    }
	public int getReseauByName(String name) {
    	se = HibernateUtils.getSession();
    	se.beginTransaction(); 
    	reseau = (Reseau) se.createQuery("from Reseau where nomReseau="+name).uniqueResult();
    	se.close();
    	return reseau.getIdReseau();
    }
	
	
	
	
	public List<Reseau> listerReseau() {
    	se = HibernateUtils.getSession();
    	se.beginTransaction();  	 	
    	listeReseau = se.createQuery("from Reseau").list();
    	
    	
        return listeReseau;
    }

}
