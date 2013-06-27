package dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import beans.PosteParSite;;


public class PosteParSiteDAO {
	
	private Session se = null;
	private List<PosteParSite> listeposteParSite;
	private PosteParSite posteParSite ;
	
	
	public void createPosteParSite(PosteParSite pps){

		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction();
		se.save(pps);   
        t.commit();
        se.close();
    }


	public List<PosteParSite> listerPosteParSite() {
    	se = HibernateUtils.getSession();
    	se.beginTransaction();  	 	
    	listeposteParSite =  se.createQuery("from PosteParSite").list();  
    	
        return listeposteParSite;
    }

	
	
	public PosteParSite getPosteParSiteByID(int id) {

    	se = HibernateUtils.getSession();
    	se.beginTransaction();  	
    	posteParSite = (PosteParSite) se.createQuery("from PosteParSite where id="+id).uniqueResult();
    	
    	 se.close();

    	  return posteParSite;
    }


}
