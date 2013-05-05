package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import beans.TypeIncident;


public class TypeIncidentDAO {	
	private Session se = null;	
	private List<TypeIncident> listeTypeIncident;
	@SuppressWarnings("unused")
	private TypeIncident typeIncident = new TypeIncident();

	
	
	public int getTypeIncidentByID(Integer id) {
	 	se = HibernateUtils.getSession();
    	se.beginTransaction(); 	
    	typeIncident = (TypeIncident) se.createQuery("from TypeIncident where id="+id).uniqueResult();
    
    	se.close();
        return id;
    }
	public List getAllTypeIncident() {
	 	se = HibernateUtils.getSession();
    	se.beginTransaction(); 	
    	List<TypeIncident> listtypeIncident =  se.createQuery("from TypeIncident").list();
    
    	se.close();
        return listtypeIncident;
    }
	
	

	@SuppressWarnings("unchecked")
	public List<TypeIncident> listerTypeIncident() {
	 	se = HibernateUtils.getSession();
    	se.beginTransaction(); 
    	listeTypeIncident = se.createQuery("from TypeIncident").list();  	
        return listeTypeIncident;
    }


}
