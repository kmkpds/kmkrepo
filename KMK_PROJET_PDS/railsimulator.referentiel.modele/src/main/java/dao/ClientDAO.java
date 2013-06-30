package dao;

import java.util.List;  

import javax.persistence.Column;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import beans.Client;
import beans.Tourniquet;

public class ClientDAO {
	private Session se = null;
	private Client client;
	public Client createClientReturnClient(Client client){
	se = HibernateUtils.getSession();
	Transaction t = se.beginTransaction();
	se.save(client);
	t.commit();
	se.close();
	return client;
	}
	public Client getClientById(int id) {
    	se = HibernateUtils.getSession();
    	se.beginTransaction(); 
    	
    	client = (Client) se.createQuery("from Client where id="+id).uniqueResult();
    	se.close();
    	
        return client;
    }
	
}
