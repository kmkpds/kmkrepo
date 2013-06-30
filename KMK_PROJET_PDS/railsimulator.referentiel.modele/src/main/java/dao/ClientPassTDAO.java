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
import beans.ClientPassTourniquet;


public class ClientPassTDAO {
	private Session se = null;
	private ClientPassTourniquet c;
	private List<ClientPassTourniquet> listC;
	
	
	public ClientPassTourniquet getClientPassById(int id) {
    	se = HibernateUtils.getSession();
    	se.beginTransaction(); 
    	
    	c = (ClientPassTourniquet) se.createQuery("from ClientPassTourniquet where idtransaction="+id).uniqueResult();
    	se.close();
    	
        return c;
    }
	
	public List<ClientPassTourniquet> listerClientPass() {
    	se = HibernateUtils.getSession();
    	se.beginTransaction();  	 	
    	listC = se.createSQLQuery("SELECT * FROM client_pass_tourniquet").list();
        return listC;
    }

	
}
