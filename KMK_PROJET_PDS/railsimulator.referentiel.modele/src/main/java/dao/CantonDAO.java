package dao;

import java.util.List;

import org.hibernate.Query;
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
	
    public int createCantonParamStation(int distance,Station station1,Station station2) {
	   
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
	public List<Canton> listerCantonParam(int[][] listeStationAffichage) {
    	se = HibernateUtils.getSession();
    	se.beginTransaction();  	 	
		String listeStationStr="where ";
		for(int i=0; i<listeStationAffichage.length-1;i++){
			listeStationStr = listeStationStr + " station_station_idstation1="+listeStationAffichage[i][0] +" or station_station_idstation2= "+listeStationAffichage[i][1]+" or";
		}
		listeStationStr = listeStationStr.substring(0, listeStationStr.length()-2);
System.out.println("requete cantondao" +listeStationStr);
		listeCanton = se.createQuery("from Canton "+listeStationStr).list();
   	  	
        return listeCanton;
    }
	public int listerCantonCount(Canton canton) {
		int result=0;
		se = HibernateUtils.getSession();
		Query query = se.createSQLQuery(
				"SELECT count(*) FROM train WHERE canton_idcanton= ?")
				.setParameter(0, canton.getIdCanton());	        
		 result =   ((Number)query.uniqueResult()).intValue();
        return result;
        
        
    }
}
