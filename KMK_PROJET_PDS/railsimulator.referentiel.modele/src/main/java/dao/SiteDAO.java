package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Employe;
import beans.Site;



public class SiteDAO {
	
	private Session se = null;
	private List<Site> listeSite;
	private List<Employe> listeEmploye;
	private Site site;

	

	@SuppressWarnings("unchecked")
	public List<Site> listerSite() {
    	se = HibernateUtils.getSession();
    	se.beginTransaction();  	 	
    	listeSite = se.createQuery("from Site").list();  
    	
        return listeSite;
    }


	

}


	

