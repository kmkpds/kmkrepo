package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import beans.Porte;
import beans.Wagon;

public class PorteDAO {
	private Session se = null;
	private List<Porte> listePorte;
	private Porte porte=new Porte();

public PorteDAO() {
}


public void createPorte(Integer statut,Wagon wagon) {
	   
	 se = HibernateUtils.getSession();
     Transaction t = se.beginTransaction();
     porte.setStatut(statut);
     porte.setWagon(wagon);
    

	 se.save(porte);		
     t.commit();
     se.close();
}


public List<Porte> listerPorte() {
	se = HibernateUtils.getSession();
	se.beginTransaction();  	 	
	listePorte = se.createQuery("from Porte").list();	
	
    return listePorte;
}

}
