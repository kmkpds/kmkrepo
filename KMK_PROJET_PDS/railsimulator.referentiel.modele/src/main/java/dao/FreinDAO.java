package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Frein;
import beans.Wagon;

public class FreinDAO {
	private Session se = null;
	private List<Frein> listeFrein;
	private Frein frein=new Frein();
	
	public FreinDAO() {
	}
	
	
    public void createFrein(Float temperature,Wagon wagon) {
 	   
		 se = HibernateUtils.getSession();
	     Transaction t = se.beginTransaction();
	     frein.setTemperature(temperature);
	     frein.setWagon(wagon);
	    

		 se.save(frein);		
	     t.commit();
	     se.close();
	}

    
	public List<Frein> listerFrein() {
    	se = HibernateUtils.getSession();
    	se.beginTransaction();  	 	
    	listeFrein = se.createQuery("from Frein").list();
    	
    	
    	
        return listeFrein;
    }
}
