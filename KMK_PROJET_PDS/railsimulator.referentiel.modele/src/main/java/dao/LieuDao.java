package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import beans.Lieu;
import beans.Zone;



public class LieuDao {

	private Session se = null;
	private List<Lieu> listeLieu;
	private Lieu lieu;

	public LieuDao() {
		// TODO Auto-generated constructor stub
	}

	public  void createlieu(Double latitudeLieu , Double longitudeLieu , String nomLieu , String typeLieu , Zone zone ) {


		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction();
		Lieu lieu = new Lieu();

		lieu.setLatitudeLieu(latitudeLieu);
		lieu.setLongitudeLieu(longitudeLieu);
		lieu.setNomLieu(nomLieu);
		lieu.setTypeLieu(typeLieu);
		lieu.setZone(zone);

		se.save(lieu);

		t.commit();
		se.close();
	}

	public List<Lieu> listerLieu() {
    	se = HibernateUtils.getSession();
    	se.beginTransaction();  	 	
    	listeLieu = se.createQuery("from Lieu").list();
    	
        return listeLieu;
    }

	public Lieu getLieuByID(int id) {
    	se = HibernateUtils.getSession();
    	se.beginTransaction(); 
    	
    	lieu = (Lieu) se.createQuery("from Lieu where idLieu="+id).uniqueResult();
    	 se.close();
    	
        return lieu;
    }


}
