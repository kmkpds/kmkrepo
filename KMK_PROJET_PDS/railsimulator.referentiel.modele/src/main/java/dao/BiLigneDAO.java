package dao;

import java.util.List;

import beans.BiLigne;
import org.hibernate.Session;


public class BiLigneDAO {
	
	private Session se = null;
	private List<BiLigne> listeLigne;
    
    
    
	public List<BiLigne> listerLigne() {
    	se = HibernateUtils.getSession();
    	se.beginTransaction(); 
    	
    	listeLigne = se.createQuery("from BiLigne").list();
    	
        return listeLigne;
    }
	


}
