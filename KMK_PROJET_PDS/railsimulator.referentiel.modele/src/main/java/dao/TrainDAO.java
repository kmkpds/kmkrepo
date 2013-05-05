package dao;

import java.util.List;

import javax.persistence.Column;

import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Reseau;
import beans.Train;
import beans.Ligne;
import beans.Zone;

public class TrainDAO {

	private Session se = null;
	private List<Train> listeTrain;
	private Train train;
	public TrainDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void createTrain(String nomTrain, Double latitudeTrain,
			Double longitudeTrain, int etat, Ligne ligne) {

		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction();
		Train train = new Train();
		train.setNomTrain(nomTrain);
		train.setLatitudeTrain(latitudeTrain);
		train.setLongitudeTrain(longitudeTrain);
		train.setEtat(etat);
		train.setLigne(ligne);
		se.save(train);

		t.commit();
		se.close();
	}
	public  int  createTrainReturnId(String nomTrain, Double latitudeTrain,
			Double longitudeTrain, int etat, Ligne ligne) {
		 
		 se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction();
		Train train = new Train();
		train.setNomTrain(nomTrain);
		train.setLatitudeTrain(latitudeTrain);
		train.setLongitudeTrain(longitudeTrain);
		train.setEtat(etat);
		train.setLigne(ligne);
		se.save(train);

		int idTrain = (Integer) se.save(train);

		t.commit();
		se.close();

		return idTrain;
		
	}
	public List<Train> listerTrain() {
    	se = HibernateUtils.getSession();
    	se.beginTransaction();  	 	
    	listeTrain = se.createQuery("from Train").list();
        return listeTrain;
    }
	
	public List<Train> listerTrainByLigne(int idLigne) {
//    	LigneDAO lignedao = new LigneDAO();
//    	Ligne ligne = new Ligne();
//    	//ligne = lignedao.getLigneByID(idLigne);
//    	ligne = lignedao.getLigneByID(idLigne);
//    	
//    	String idTrainStr = "(";
//    	Object[] trainList =ligne.getTrainlist().toArray();
//    	Train train;
//    	for(int i=0; i<trainList.length-1; i++){
//    		train = (Train) trainList[i];
//    		idTrainStr = idTrainStr+ train.getIdTrain() + ",";
//    	}
//    	idTrainStr = idTrainStr.substring(0, idTrainStr.length()-1);
//    	idTrainStr = idTrainStr+")";
		se = HibernateUtils.getSession();
    	se.beginTransaction();  	 	
    	listeTrain = se.createQuery("from Train where ligne_idligne="+idLigne).list();
        return listeTrain;
    }
	
	public Train getTrainByID(int id) {
    	se = HibernateUtils.getSession();
    	se.beginTransaction(); 
    	
    	train = (Train) se.createQuery("from Train where idTrain="+id).uniqueResult();
    	se.close();
    	
        return train;
    }
}
