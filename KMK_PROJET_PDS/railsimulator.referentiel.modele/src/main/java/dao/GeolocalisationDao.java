package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import beans.Ligne;
import beans.Zone;
import beans.Geolocalisation;


public class GeolocalisationDao {

	private Session se = null;
	private List<Geolocalisation> listeGeolocalisation;
	private Geolocalisation geolocalisation;


	public GeolocalisationDao() {
		// TODO Auto-generated constructor stub
	}


	public  void createGeolocalisation( Double latitudeGeolocalisation , Double longitudeGeolocalisation , Zone zone ) {


		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction();
		Geolocalisation geolocalisation = new Geolocalisation();
		
        geolocalisation.setLatitudeGeolocalisation(latitudeGeolocalisation);
        geolocalisation.setLongitudeGeolocalisation(longitudeGeolocalisation);
        geolocalisation.setZone(zone);
       
		se.save(geolocalisation);

		t.commit();
		se.close();

	}

	public List<Geolocalisation> listerGeolocalisation() {
		se = HibernateUtils.getSession();
		se.beginTransaction();  	 	
		listeGeolocalisation = se.createQuery("from Geolocalisation").list();

		return listeGeolocalisation;
	}

	public Geolocalisation getGeolocalisationByID(int id) {
		se = HibernateUtils.getSession();
		se.beginTransaction(); 

		geolocalisation= (Geolocalisation) se.createQuery("from Geolocalisation where idCanton="+id).uniqueResult();
		se.close();

		return geolocalisation;
	}

}
