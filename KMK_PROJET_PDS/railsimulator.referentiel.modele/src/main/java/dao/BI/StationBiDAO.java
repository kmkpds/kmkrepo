package dao.BI;

import java.util.List;

import org.hibernate.Session;

import beans.BI.StationBI;

public class StationBiDAO {
	
	private Session se = null;
	private List<StationBI> listeStation;
	
	public List<StationBI> listerStation(){
		se = HibernateUtilsBiAuto.getSession();
    	se.beginTransaction();  	 	
    	listeStation = se.createQuery("from StationBI").list();
    	return listeStation;
	}

}
