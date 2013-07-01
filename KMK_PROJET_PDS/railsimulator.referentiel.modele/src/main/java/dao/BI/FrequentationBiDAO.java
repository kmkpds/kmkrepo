package dao.BI;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.BI.FrequentationBI;
import beans.BI.LigneBI;
import beans.BI.StationBI;
import beans.BI.TypeAbonnementBI;

public class FrequentationBiDAO {
	
	private Session se = HibernateUtilsBiAuto.getSession();
    private FrequentationBI freq;
   
    public  void createFreq(Calendar dateFreq, StationBI stationFreq, LigneBI ligneFreq, TypeAbonnementBI typeFreq) {
	      
		 
		 //se = HibernateUtilsBiAuto.getSession();
	     Transaction t = se.beginTransaction();
	     freq = new FrequentationBI(dateFreq, stationFreq, ligneFreq, typeFreq);
	     se.save(freq);
		
	     t.commit();
	     
	}
    
    public void sessionClosed(){
    	se.close();
    }

}
