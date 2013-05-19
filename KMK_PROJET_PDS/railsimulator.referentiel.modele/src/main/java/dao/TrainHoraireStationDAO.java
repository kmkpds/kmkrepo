package dao;

import java.util.List;

import javax.persistence.Column;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Geolocalisation;
import beans.ParametreHoraire;
import beans.Train;
import beans.Station;
import beans.TrainHoraireStation;

public class TrainHoraireStationDAO {
	private Session se = null;
	private TrainHoraireStation trainHoraireStation;
	private List<TrainHoraireStation> listetrainHoraireStation;
	private ParametreHoraire parametreHoraire;
	public TrainHoraireStationDAO() {
		// TODO Auto-generated constructor stub
	}

	public  void createTrainHoraire( String heureJO, String heureSamedi, String heureDimancheJF, Station station, Train train) {

		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction();
		TrainHoraireStation trainHoraireStation = new TrainHoraireStation();
		if(heureJO!=""){
			trainHoraireStation.setHeureJO(heureJO);
		}
		if(heureDimancheJF!=""){
			trainHoraireStation.setHeureDimancheJF(heureDimancheJF);
		}
		if(heureSamedi!=""){
			trainHoraireStation.setHeureSamedi(heureSamedi);
		}
		trainHoraireStation.setStation(station);
		trainHoraireStation.setTrain(train);
		se.save(trainHoraireStation);

		t.commit();
		se.close();
	}
	
	public  int createTrainHoraireReturnId( String heureJO, String heureSamedi, String heureDimancheJF, Station station, Train train) {

		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction();
		TrainHoraireStation trainHoraireStation = new TrainHoraireStation();
		if(heureJO!=""){
			trainHoraireStation.setHeureJO(heureJO);
		}
		if(heureDimancheJF!="null"){
			trainHoraireStation.setHeureDimancheJF(heureDimancheJF);
		}
		if(heureSamedi!=""){
			trainHoraireStation.setHeureSamedi(heureSamedi);
		}
		trainHoraireStation.setStation(station);
		trainHoraireStation.setTrain(train);
		se.save(trainHoraireStation);
		int idTrainHoraireStation = (Integer) se.save(trainHoraireStation);
		t.commit();
		se.close();
		return idTrainHoraireStation;
	}
	
	public TrainHoraireStation getTrainHoraireStationById(int id) {
		se = HibernateUtils.getSession();
		se.beginTransaction(); 

		trainHoraireStation= (TrainHoraireStation) se.createQuery("from TrainHoraireStation where id="+id).uniqueResult();
		se.close();

		return trainHoraireStation;
	}
		
	public List<TrainHoraireStation> listTrainHoraireStationById() {
		se = HibernateUtils.getSession();
		se.beginTransaction();  	 	
		listetrainHoraireStation = se.createQuery("from TrainHoraireStation").list();
		return listetrainHoraireStation;
	}
	
	public List<TrainHoraireStation> listTrainHoraireStationByListTrain(String trainList) {
		se = HibernateUtils.getSession();
		se.beginTransaction();  
		listetrainHoraireStation = se.createQuery("from TrainHoraireStation where train_idtrain in"+ trainList).list();
		return listetrainHoraireStation;
	}
	
	public int deleteAllTrainHoraireStation(){
		Session se = null;
		se = HibernateUtils.getSession();
		Transaction t9 = se.beginTransaction();
		Query create9=se.createSQLQuery("delete from trainhorairestation");
		create9.executeUpdate();
		t9.commit();
		return 1;
			
	}
}