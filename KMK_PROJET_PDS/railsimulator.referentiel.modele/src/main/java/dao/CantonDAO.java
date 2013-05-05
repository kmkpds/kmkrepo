package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Canton;
import beans.Lieu;
import beans.Ligne;
import beans.Station;

public class CantonDAO {
	private Session se = null;
	private List<Canton> listeCanton;
	private Canton canton=new Canton();

	public CantonDAO() {
		// TODO Auto-generated constructor stub
	}
	
    public int createCantonParamStation(double distance,Station station1,Station station2) {
	   
		 se = HibernateUtils.getSession();
	     Transaction t = se.beginTransaction();
	     canton.setDistance(distance);
	     canton.setStation1(station1);
	     canton.setStation2(station2);

		 int idCanton = (Integer)se.save(canton);		
	     t.commit();
	     se.close();
	     return idCanton;
	}
    public  int createCantonReturnId(String nomCanton, String commentaireCanton,Station station1,Station station2) {
	      
		 
		 se = HibernateUtils.getSession();
	     Transaction t = se.beginTransaction();
	    
	     canton.setStation1(station1);
	     canton.setStation2(station2);
	     int idCanton = (Integer) se.save(canton);		
	     t.commit();
	     se.close();
	     return idCanton;
	}
    
    
    
    public  void createCanton(Station station1,Station station2) {
	      
		 
		 se = HibernateUtils.getSession();
	     Transaction t = se.beginTransaction();
	   
	     canton.setStation1(station1);
	     canton.setStation2(station2);
		 se.save(canton);		
	     t.commit();
	     se.close();
	}


    
    public void supprimerCanton(int id) {
    	
    	se = HibernateUtils.getSession();
    	se.beginTransaction();
        canton = (Canton) se.load(Canton.class, id);
        se.delete(canton);
        se.beginTransaction().commit();
        se.close();
    }
    
	public Canton getCantonByID(int id) {
    	se = HibernateUtils.getSession();
    	se.beginTransaction(); 
    	
    	canton = (Canton) se.createQuery("from Canton where idCanton="+id).uniqueResult();
    	se.close();
    	
        return canton;
    }
	
    public void modifierCanton(Canton canton) {
  	  
    	se = HibernateUtils.getSession();       
    	Transaction tr=se.beginTransaction();
    	se.update(canton);
    	tr.commit();
    	se.close();
    	 
    }
    
	public List<Canton> listerCanton() {
    	se = HibernateUtils.getSession();
    	se.beginTransaction();  	 	
    	listeCanton = se.createQuery("from Canton").list();
    	    	
        return listeCanton;
    }
	public List<Canton> listerCantonParam(List<Station> listeStation) {
    	se = HibernateUtils.getSession();
    	se.beginTransaction();  	 	
		String listeStationStr="where ";
		for(int i=0; i<listeStation.size();i++){
			//listeStationStr = listeStationStr + listeStation.get(i).getIdStation() +",";
			listeStationStr = listeStationStr + " station_station_idstation1="+listeStation.get(i).getIdStation() +" or station_station_idstation2= "+listeStation.get(i).getIdStation()+" or";
		}
		listeStationStr = listeStationStr.substring(0, listeStationStr.length()-2);
		//listeStationStr = listeStationStr.substring(0, listeStationStr.length()-1);
		//listeStationStr = listeStationStr + ")";

		
    	//listeCanton = se.createQuery("from Canton where station_has_station_station_idstation1="+listeStationStr+ " or station_has_station_station_idstation2= "+listeStationStr).list();
		listeCanton = se.createQuery("from Canton "+listeStationStr).list();
   	  	
        return listeCanton;
    }
	
}
