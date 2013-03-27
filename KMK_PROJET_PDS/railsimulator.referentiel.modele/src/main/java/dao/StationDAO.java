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

    
    public  void createStation(String nomStation , String commentaireStation, double latitude, double longitude, Ligne ligne) {
	      
		 
		 se = HibernateUtils.getSession();
	     Transaction t = se.beginTransaction();
	    
	     
	     station.setNomStation(nomStation);
	     station.setCommentaireStation(commentaireStation);
	     station.setLatitude(latitude);
	     station.setLongitude(longitude);
	     station.setLigne(ligne);
    
		 se.save(station);		
	     t.commit();
	     se.close();
	}
    
    public  void createStationToStation(Station station1, Station station2) {
	      
     
	  
	 	 se = HibernateUtils.getSession();       
    	 Transaction tr=se.beginTransaction(); 
    	 
		 List<Station> listStation = station1.getStationAller();
		 listStation.add(station2);		
		 List<Station> listStation2 = station2.getStationAller();
		 listStation2.add(station1);
	
         station1.setStationAller(listStation);
    	 station2.setStationAller(listStation2);
    

	     se.update(station1);
	     se.update(station2);

    	 tr.commit();
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
