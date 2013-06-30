package dao;
import java.sql.ResultSet;   
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.AbonnementMany;
import beans.Canton;
import beans.Ligne;
import beans.ZoneAbo;
import beans.TypeAbonnement;

import com.mysql.jdbc.PreparedStatement;

public class AbonnementManyDAO {
	private Session se = null;
	private AbonnementMany abo;
	private List<AbonnementMany> aboM=new ArrayList<AbonnementMany>();
	
	
	public void createAboMany(AbonnementMany abo)
			 {

		try {
			se = HibernateUtils.getSession();
			Transaction t = se.beginTransaction();
			
			se.save(abo);
			t.commit();
			se.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	
	public int createAboManyReturnId(AbonnementMany abo)
	 {


	se = HibernateUtils.getSession();
	Transaction t = se.beginTransaction();
	
	se.save(abo);
	int idAboMany = (Integer) se.save(abo);
	t.commit();
	se.close();

return idAboMany;
}
	
	public AbonnementMany getAbonnementManyById(int id) {
    	se = HibernateUtils.getSession();
    	se.beginTransaction(); 
    	
    	abo = (AbonnementMany) se.createQuery("from AbonnementMany where id="+id).uniqueResult();
    	se.close();
    	
        return abo;
    }
	
	public AbonnementMany getAbonnementManyByIdClient(int id) {
    	se = HibernateUtils.getSession();
    	se.beginTransaction(); 
    	
    	abo = (AbonnementMany) se.createQuery("from AbonnementMany where idclient="+id).uniqueResult();
    	se.close();
    	
        return abo;
    }
	
	public List<AbonnementMany> listerAboByClient(int idClient) {
    	se = HibernateUtils.getSession();
    	se.beginTransaction();  	 	
    	aboM = (List<AbonnementMany>) se.createQuery("from AbonnementMany where idclient="+idClient).list();
        return aboM;
    }
	
	

}
