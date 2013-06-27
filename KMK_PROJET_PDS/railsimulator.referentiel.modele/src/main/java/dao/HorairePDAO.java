package dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;


import beans.HoraireP;



public class HorairePDAO {
	
	private Session se = null;
	private List<HoraireP> listeHoraireP;
	private HoraireP horaireP;

	
	public List<HoraireP> listerHoraireP() {
    	se = HibernateUtils.getSession();
    	se.beginTransaction();  	 	
    	listeHoraireP = se.createQuery("from HoraireP").list();  
    	
        return listeHoraireP;
    }

	

	public HoraireP getHorairePByID(int id) {
    	se = HibernateUtils.getSession();
    	se.beginTransaction(); 
    	
    	horaireP = (HoraireP) se.createQuery("from HoraireP where idHoraireP="+id).uniqueResult();
    	 se.close();
    	
        return horaireP;
    }

}


	

