package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Canton;
import beans.Ligne;
import beans.Station;

public class StationDAO {

	private Session se = null;
	private List<Station> listeStation;
	private Station station = new Station();

    
    public  void createStation(String nomStation , String commentaireStation) {
	      
		 
		 se = HibernateUtils.getSession();
	     Transaction t = se.beginTransaction();
	    
	     
	     station.setNomStation(nomStation);
	     station.setCommentaireStation(commentaireStation);

	   
	     
	    
		 se.save(station);
		
	     t.commit();
	     se.close();
	}
    
    public void supprimerStation(int id) {
    	
    	se = HibernateUtils.getSession();
    	se.beginTransaction();
        station = (Station) se.load(Station.class, id);
        se.delete(station);
        se.beginTransaction().commit();
        se.close();
    }
    
	public Station getStationByID(int id) {
    	se = HibernateUtils.getSession();
    	se.beginTransaction(); 
    	
    	station = (Station) se.createQuery("from Station where idStation="+id).uniqueResult();
    	se.close();
    	
        return station;
    }
	
    public void modifierStation(Station station) {
  	  
    	se = HibernateUtils.getSession();       
    	Transaction tr=se.beginTransaction();
    	se.update(station);
    	tr.commit();
    	se.close();
    	 
    }
    
	public List<Station> listerStation() {
    	se = HibernateUtils.getSession();
    	se.beginTransaction();  	 	
    	listeStation = se.createQuery("from Station").list();
    	
    	
    	
        return listeStation;
    }
    
    
}
