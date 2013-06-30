package dao;
import java.util.List;    

import javax.persistence.Column;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.Session;
import org.hibernate.Transaction;
import beans.ZoneAbo;

public class ZoneAboDAO {
	
	private Session se = null;
	private ZoneAbo zone;
	public void createZoneAbo(int numero){
		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction();
		zone = new ZoneAbo();
		zone.setNumZone(numero);
		
		se.save(zone);
		t.commit();
		se.close();
	}
	
	public ZoneAbo getZoneAboById(int id) {
		se = HibernateUtils.getSession();
		se.beginTransaction(); 

		zone= (ZoneAbo) se.createQuery("from ZoneAbo where idzoneabo="+id).uniqueResult();
		se.close();

		return zone;
	}
	

}
