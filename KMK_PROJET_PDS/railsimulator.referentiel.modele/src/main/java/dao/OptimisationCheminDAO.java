package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Canton;
import beans.OptimisationChemin;
import beans.Station;
import beans.TrainHoraireStation;

import dao.LigneDAO;
import dao.StationDAO;

public class OptimisationCheminDAO {
	private Session se = null;
	//private List<Canton> listeCanton;
	private OptimisationChemin optimisationChemin=new OptimisationChemin();
	
	public OptimisationCheminDAO() {
	}
	
	
    public void createOptimisationChemin(Station station1,Station station2,double distance,String chemin) {
 	   
		 se = HibernateUtils.getSession();
	     Transaction t = se.beginTransaction();
	     optimisationChemin.setStation1(station1);
	     optimisationChemin.setStation2(station2);
	     optimisationChemin.setDistanceligne(distance);
	     optimisationChemin.setChemin(chemin);

		 se.save(optimisationChemin);		
	     t.commit();
	     se.close();
//	     return idCanton;
	}
    public List<OptimisationChemin> listOptimisationCheminByLigne(int idLigne) {
		se = HibernateUtils.getSession();
		se.beginTransaction(); 
		String stationListStr="";
		StationDAO stationDAO = new StationDAO();
		List<Station> stationList = stationDAO.listerStation();
		stationListStr ="(";
		for (int i=0; i<stationList.size();i++){
			//System.out.println("stationList.get(i).getLigne().getIdLigne()" +stationList.get(i).getLigne().getIdLigne());
			if(stationList.get(i).getLigne().getIdLigne()==idLigne){
				//System.out.println(stationList.get(i).getIdStation());
				stationListStr = stationListStr + stationList.get(i).getIdStation()+", ";
			}
			else{
				//System.out.println("stationList.get(i).getLigne().getIdLigne() not==idLigne" +stationList.get(i).getLigne().getIdLigne() + " "+idLigne);
				stationListStr = stationListStr + stationList.get(i).getIdStation()+" , ";
			}
		}
		stationListStr = stationListStr.substring(0, stationListStr.length()-2);
		stationListStr =stationListStr +")";

		List<OptimisationChemin> listeOptimisationChemin;
		listeOptimisationChemin = se.createQuery("from OptimisationChemin where station_idstation1 in"+ stationListStr +" or station_idstation2 in"+ stationListStr).list();

		se.close();
		return listeOptimisationChemin;
	}
    
}//fin OptimisationCheminDAO
