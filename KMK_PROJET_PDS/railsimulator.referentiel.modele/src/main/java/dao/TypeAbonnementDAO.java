package dao;
import java.util.List;    

import javax.persistence.Column;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import beans.TypeAbonnement;

public class TypeAbonnementDAO {
	private Session se = null;
	private TypeAbonnement typeAbo;
	
	public void createTypeAbonnement(String libelle){
		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction();
		typeAbo = new TypeAbonnement();
		typeAbo.setLibelle(libelle);
		se.save(typeAbo);
		t.commit();
		se.close();
		
	}
	
	
	public TypeAbonnement getTypeAbonnementById(int id){
		se = HibernateUtils.getSession();
    	se.beginTransaction(); 
    	
    	typeAbo = (TypeAbonnement) se.createQuery("from TypeAbonnement where id="+id).uniqueResult();
    	se.close();
		
		return typeAbo;
		
	}

}
