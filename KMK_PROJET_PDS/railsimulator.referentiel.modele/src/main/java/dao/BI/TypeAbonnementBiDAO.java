package dao.BI;

import java.util.List;

import org.hibernate.Session;

import dao.HibernateUtils;

import beans.BI.TypeAbonnementBI;

public class TypeAbonnementBiDAO {
	
	private Session se = null;
	private List<TypeAbonnementBI> listeTypeAbo;
	
	public List<TypeAbonnementBI> listerTypeAbo(){
		se = HibernateUtils.getSession();
    	se.beginTransaction();  	 	
    	listeTypeAbo = se.createQuery("from TypeAbonnementBi").list();
    	
        return listeTypeAbo;
	}

}
