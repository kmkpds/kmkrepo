package dao.BI;

import java.util.List;

import org.hibernate.Session;

import dao.HibernateUtils;

import beans.BI.TypeAbonnementBI;

public class TypeAbonnementBiDAO {
	
	private Session se = null;
	private List<TypeAbonnementBI> listeTypeAbo;
	
	public List<TypeAbonnementBI> listerTypeAbo(){
		se = HibernateUtilsBiAuto.getSession();
    	se.beginTransaction();  	 	
    	listeTypeAbo = se.createQuery("from TypeAbonnementBI").list();
    	
        return listeTypeAbo;
	}

}
