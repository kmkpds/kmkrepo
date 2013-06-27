package dao.BI;

import java.util.Calendar;
import java.util.List;

import net.sourceforge.jtds.jdbc.DateTime;

import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Ligne;
import beans.Reseau;
import beans.BI.FrequentationBI;
import beans.BI.LigneBI;
import beans.BI.StationBI;
import beans.BI.TypeAbonnementBI;

public class FrequentationBiDAO {
	
	private Session se = null;
	private List<FrequentationBiDAO> listeFreq;
    private FrequentationBI freq;
   
    public  void createFreq(Calendar dateFreq, StationBI stationFreq, LigneBI ligneFreq, TypeAbonnementBI typeFreq) {
	      
		 
		se = HibernateUtilsBiAuto.getSession();
	     Transaction t = se.beginTransaction();
	     freq = new FrequentationBI(dateFreq, stationFreq, ligneFreq, typeFreq);
	     se.save(freq);
		
	     t.commit();
	     se.close();
	}

}
