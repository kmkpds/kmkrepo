package dao;

import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Session;

import beans.Incident;
import beans.TypeIncident;

public class TypeIncidentDAO {

	private Session se = null;
	private Set<Incident> listeIncident;
	private TypeIncident typeincident;
	
	public TypeIncident getTypeIncidentByID(int id) {
    	
		se = HibernateUtils.getSession();
    	se.beginTransaction(); 
    	typeincident = (TypeIncident) se.createQuery("from TypeIncident where idType="+id).uniqueResult();
    	se.close();
    	
        return typeincident;
    }
}
