package dao;

import java.util.Set;

import beans.Ligne;
import beans.Reseau;
import beans.Station;
import beans.Train;
import beans.TrainHoraireStation;

import org.hibernate.Session;
import org.hibernate.Transaction;


import dao.HibernateUtils;
import dao.LigneDAO;

public class testDAOMAX  {

	/**
	 * @param args
	 */
	
	
	private static Reseau reseau;
		@SuppressWarnings("null")
		public testDAOMAX(){
		Session se = null;
		
		 se = HibernateUtils.getSession();
		// TODO Auto-generated method stub
		Transaction t2 = se.beginTransaction();
		reseau = new Reseau();
		reseau.setNomReseau("reseau 2");
		se.save(reseau);		
	    t2.commit();
	    Ligne ligne = new Ligne();
	    ligne.setNomLigne("LigneMax3");
	    ligne.setReseau(reseau);
	    Transaction t3 = se.beginTransaction();
	    se.save(ligne);
	    t3.commit();
	    
	    Train train = new Train();
	   // train.setIdTrain(5666);
	    train.setNomTrain("coco2");
	    train.setLigne(ligne);
	    train.setLatitudeTrain(200.23333333);
	    train.setLongitudeTrain(344.55533322);
	    train.setEtat(1);
	    train.setLigne(ligne);
	    Transaction t5 = se.beginTransaction();
	    se.save(train);
	   t5.commit();
	    Station station = new Station();
	    station.setNomStation("station MAx1");
	    station.setLigne(ligne);
	   // station.setTrainHoraireStation(trainhorairestationlist);
	    Transaction t4 = se.beginTransaction();
	    se.save(station);
	    t4.commit();
	   
	    TrainHoraireStation trainhorairestation = new TrainHoraireStation();
	    trainhorairestation.setStation(station);
	    trainhorairestation.setTrain(train);
	    trainhorairestation.setHeureDimancheJF("22:00");
	    trainhorairestation.setHeureJO("15:00");
	    trainhorairestation.setHeureSamedi("8:00");
	    Transaction t6 = se.beginTransaction();
	    se.save(trainhorairestation);
	    t6.commit();
	       
	   se.flush();
	  
	}
}

