package dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Frein;
import beans.Porte;
import beans.Train;
import beans.Wagon;

public class WagonDAO {
	private Session se = null;
	private List<Wagon> listeWagon;
   
   
    public WagonDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
    public void createWagon(Set<Porte> listePortes, Set<Frein> listeFreins, Train train) {

		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction();
		Wagon wagon = new Wagon();
		wagon.setListePortes(listePortes);
		wagon.setListeFreins(listeFreins);
		wagon.setTrain(train);
		se.save(wagon);

		t.commit();
		se.close();
	}
	public  int  createWagonReturnId(Integer temperatureWagon,Integer frequentation,Set<Porte> listePortes, Set<Frein> listeFreins, Train train)  {
		 
		 se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction();
		Wagon wagon = new Wagon();
		wagon.setTemperatureWagon(temperatureWagon);
		wagon.setFrequentation(frequentation);
		wagon.setListePortes(listePortes);
		wagon.setListeFreins(listeFreins);
		wagon.setTrain(train);
		//se.save(wagon);

		int idWagon = (Integer) se.save(wagon);

		t.commit();
		se.close();

		return idWagon;
		
	}
	public Wagon getWagonById(int id) {
		se = HibernateUtils.getSession();
		se.beginTransaction(); 

		Wagon wagon= (Wagon) se.createQuery("from Wagon where id="+id).uniqueResult();
		se.close();

		return wagon;
	}
	
	public List<Wagon> listerWagon() {
    	se = HibernateUtils.getSession();
    	se.beginTransaction();  	 	
    	listeWagon = se.createQuery("from Wagon").list();
        return listeWagon;
    }
	public int listerWagonCount(Train train) {
		int result=0;
		se = HibernateUtils.getSession();
		Query query = se.createSQLQuery(
				"SELECT count(*) FROM wagon WHERE train_idtrain= ?")
				.setParameter(0, train.getIdTrain());	        
		 result =   ((Number)query.uniqueResult()).intValue();
        return result;
        
        
    }
	public List<Wagon> listerWagonByIdTrain(int idTrain) {
		se = HibernateUtils.getSession();
    	se.beginTransaction();  	 	
    	listeWagon = se.createQuery("from Wagon where train_idtrain="+idTrain).list();
        return listeWagon;
    }
}
