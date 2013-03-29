package dao;

import java.util.*;

import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Canton;
import beans.Geolocalisation;
import beans.Reseau;
import beans.Zone;
import beans.Lieu;


public class ZoneDAO {
	
	private Session se = null;
	private List<Zone> listeZone;
    private Zone zone;
	
	

	public ZoneDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public  void createZone(Integer nombreHabitantsParZone , Integer nombreMaxDeStationParZone ,Float surfaceZone , Reseau reseau ) {
	      
		 
		 se = HibernateUtils.getSession();
	     Transaction t = se.beginTransaction();
	     Zone zone = new Zone();
	     zone.setNombreHabitantsParZone(nombreHabitantsParZone);
	     zone.setNombreMaxDeStationParZone(nombreMaxDeStationParZone);
	     zone.setSurfaceZone(surfaceZone);
	     zone.setReseau(reseau);
	     
		  se.save(zone);
		
	     t.commit();
	     se.close();
	}
	
	public  int  createZonereturn(Integer nombreHabitantsParZone , Integer nombreMaxDeStationParZone ,Float surfaceZone , Reseau reseau ) {
	      
		 
		 se = HibernateUtils.getSession();
	     Transaction t = se.beginTransaction();
	     Zone zone = new Zone();
	     zone.setNombreHabitantsParZone(nombreHabitantsParZone);
	     zone.setNombreMaxDeStationParZone(nombreMaxDeStationParZone);
	     zone.setSurfaceZone(surfaceZone);
	     zone.setReseau(reseau);
	     
		 int idZone = (Integer) se.save(zone);
		
	     t.commit();
	     se.close();

		return idZone;
		
	}
	
	public List<Zone> listerZone() {
    	se = HibernateUtils.getSession();
    	se.beginTransaction();  	 	
    	listeZone = se.createQuery("from Zone").list();
        return listeZone;
    }
	
	public Zone getZoneByID(int id) {
    	se = HibernateUtils.getSession();
    	se.beginTransaction(); 
    	
    	zone = (Zone) se.createQuery("from Zone where idZone="+id).uniqueResult();
    	 se.close();
    	
        return zone;
    }
	

}
