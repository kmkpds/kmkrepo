package dao;

import java.util.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import beans.Ligne;
import beans.Reseau;

public class LigneDAO {	
	
	private Session se = null;
	private List<Ligne> listeLigne;
    private Ligne ligne;

    
    
	public  void createLigne(String nomLigne , String commentaireLigne, Reseau reseau ) {
	      
		 
		se = HibernateUtils.getSession();
	     Transaction t = se.beginTransaction();
	     Ligne ligne = new Ligne();
	     ligne.setNomLigne(nomLigne);
	     ligne.setCommentaire(commentaireLigne); 
	     ligne.setReseau(reseau);
	     se.save(ligne);
		
	     t.commit();
	     se.close();
	}
	
	public int createLigneReturnId(String nomLigne , String commentaireLigne, Reseau reseau){
		se = HibernateUtils.getSession();
	    Transaction t = se.beginTransaction();
	    Ligne ligne = new Ligne();
	    ligne.setNomLigne(nomLigne);
	    ligne.setCommentaire(commentaireLigne); 
	    ligne.setReseau(reseau);
	    int idLigne = (Integer) se.save(ligne);
			
	     t.commit();
	     se.close();

		return idLigne;
	}
	public List<Ligne> listerLigne() {
    	se = HibernateUtils.getSession();
    	se.beginTransaction();  	 	
    	listeLigne = se.createQuery("from Ligne").list();
    	
    	
    	
        return listeLigne;
    }
	
	public Ligne getLigneByID(int id) {
    	se = HibernateUtils.getSession();
    	se.beginTransaction(); 
    	
    	ligne = (Ligne) se.createQuery("from Ligne where idLigne="+id).uniqueResult();
    	 se.close();
    	
        return ligne;
    }
 
   
    public void supprimerLigne(int id) {
    	
    	se = HibernateUtils.getSession();
    	se.beginTransaction();
        ligne = (Ligne) se.load(Ligne.class, id);
        se.delete(ligne);
        se.beginTransaction().commit();
        se.close();
    }
    
    public void modifierLigne(Ligne ligne) {
    	  
    	se = HibernateUtils.getSession();       
    	Transaction tr=se.beginTransaction();
    	se.update(ligne);
    	tr.commit();
    	se.close();
    	 
    }
	
	
}