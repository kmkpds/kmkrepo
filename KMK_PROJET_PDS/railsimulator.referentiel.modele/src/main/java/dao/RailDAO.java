package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Canton;
import beans.Porte;
import beans.Rail;
import beans.Wagon;

public class RailDAO {
	private Session se = null;
	private List<Rail> listeRail;
	private Rail rail=new Rail();

public RailDAO() {
}


public int createRail(String direction) {
	   
	 se = HibernateUtils.getSession();
     Transaction t = se.beginTransaction();
     rail.setDirection(direction);
    

     int idRail = (Integer) se.save(rail);		
     t.commit();
     se.close();
	return idRail;
}


public List<Rail> listerRail() {
	se = HibernateUtils.getSession();
	se.beginTransaction();  	 	
	listeRail = se.createQuery("from Rail").list();	
	
    return listeRail;
}


public Rail getRailByID(int id) {
	se = HibernateUtils.getSession();
	se.beginTransaction(); 
	
	rail = (Rail) se.createQuery("from Rail where idRail="+id).uniqueResult();
	se.close();
	
    return rail;
}

}
