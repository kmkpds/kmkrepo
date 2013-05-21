

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.GeolocalisationDAO;
import dao.HibernateUtils;
import dao.LigneDAO;
import beans.Geolocalisation;
import beans.Ligne;
import beans.Reseau;
import beans.Zone;


import junit.framework.TestCase;

public class GeolocalisationDAOTest extends TestCase {
   private Session se = null;
	
	
	private Geolocalisation geolocalisation = new Geolocalisation();
	private Geolocalisation geolocalisation1 = new Geolocalisation();
	private Zone zone = new Zone();
	private Reseau reseau = new Reseau();
	
	private List<Geolocalisation> listeGeolocalisation;
	
	private GeolocalisationDAO geolocalisation_dao = new GeolocalisationDAO();

	
	boolean test;

	public GeolocalisationDAOTest(String name) {
		super(name);
	}
	
	
	/*
	 * Test de la méthode createGeolocalisation
	 */

	public void testCreateGeolocalisation(){

		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction(); 
		

		//Purge de la table reseau & zone & geolocalisation base de donnée de test
		Query delete=se.createQuery("delete from Geolocalisation");
		Query delete2=se.createQuery("delete from Zone");
		Query delete3=se.createQuery("delete from Reseau");		
		delete.executeUpdate();
		delete2.executeUpdate();
		delete3.executeUpdate();
		t.commit();
		
		
		//création d'un reseau test
		Transaction t2 = se.beginTransaction();
		reseau.setNomReseau("reseau test");
		se.save(reseau);		
		t2.commit();
		se.flush();
		
		//création d'une zone test
		Transaction t3 = se.beginTransaction();
		zone.setReseau(reseau);
		zone.setNombreHabitantsParZone(4455522);
		zone.setNombreMaxDeStationParZone(4);
		zone.setSurfaceZone((float)1145.22);
		se.save(zone);		
		t3.commit();
		se.flush();
		
		//Création d'une Geolocalisation
		Transaction t4 = se.beginTransaction();
		geolocalisation.setZone(zone);
		geolocalisation.setLatitudeGeolocalisation((float)43.26);
		geolocalisation.setLongitudeGeolocalisation((float)0.99);		
		se.save(geolocalisation);		
		t4.commit();
		
		
	}
	
	
	public void testListerGeolocalisaion(){


		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction();
		
		//Purge de la table geolocalisation & zone & reseau  base de donnée de test
		Query delete=se.createQuery("delete from Geolocalisation");
		Query delete2=se.createQuery("delete from Zone");
		Query delete3=se.createQuery("delete from Reseau");		
		delete.executeUpdate();
		delete2.executeUpdate();
		delete3.executeUpdate();
		t.commit();
		
		//création d'un reseau test
		Transaction t1 = se.beginTransaction();
		reseau.setNomReseau("reseau 3");
		se.save(reseau);		
		t1.commit();
		se.flush();
		
		
		//création d'une zone test
		
		Transaction t2 = se.beginTransaction();
		zone.setReseau(reseau);
		zone.setNombreHabitantsParZone(5555666);
		zone.setNombreMaxDeStationParZone(5);
		zone.setSurfaceZone((float)12224.33);
		
		se.save(zone);		
		t2.commit();
		se.flush();


        //Insertion de 2 nouvelles geolocalisation
		Transaction t3 = se.beginTransaction();
		
		geolocalisation.setZone(zone);
		geolocalisation.setLatitudeGeolocalisation((double)112.3);
		geolocalisation.setLongitudeGeolocalisation((double)1125.36);
		se.save(geolocalisation);	

		
		
		
		
		geolocalisation1.setZone(zone);
		geolocalisation1.setLatitudeGeolocalisation((double)12.3);
		geolocalisation1.setLongitudeGeolocalisation((double)0.36);
	
		se.save(geolocalisation1);	
		t3.commit();
        
		
		//Récupération de la liste de geolocalisation par la méthode listerLigne
		
		listeGeolocalisation=geolocalisation_dao.listerGeolocalisation();
		
	

		//Vérification du nombre de résultat (2)
		if (listeGeolocalisation.size()==2){
			//Vérification des données récupérées
			if(listeGeolocalisation.get(0).getLatitudeGeolocalisation()==112.3
					&& listeGeolocalisation.get(0).getLongitudeGeolocalisation()==1125.36
					&& listeGeolocalisation.get(1).getLatitudeGeolocalisation()==12.3
					&& listeGeolocalisation.get(1).getLongitudeGeolocalisation()==0.36){
				test=true; 
			}
			else{
				test=false;
			}
		}
		else{
			test= false;
		}


		assertTrue(test); 

		
		//nouvelle purge de la table geolocalisation & Zone & reseau
		Transaction t4 = se.beginTransaction();
		Query delete4=se.createQuery("delete from Geolocalisation");		
		Query delete5=se.createQuery("delete from Zone");
		Query delete6=se.createQuery("delete from Reseau");
		delete4.executeUpdate();
		delete5.executeUpdate();
		delete6.executeUpdate();
		t4.commit();
		se.close();

	}


	public void testGetGeolocalisationByID(){

		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction();
		
		//Purge de la table geolocalisation & zone & reseau base de donnée de test
		Query delete=se.createQuery("delete from Geolocalisation");
		Query delete2=se.createQuery("delete from Zone");
		Query delete3=se.createQuery("delete from Reseau");
		delete.executeUpdate();
		delete2.executeUpdate();
		delete3.executeUpdate();
		t.commit();
		
		//création d'un reseau test
		Transaction t2 = se.beginTransaction();
		reseau.setNomReseau("reseau 6");
		se.save(reseau);		
		t2.commit();
		se.flush();
		
		//création d'une zone test
		Transaction t3 = se.beginTransaction();
		zone.setNombreHabitantsParZone(5454542);
		zone.setNombreMaxDeStationParZone(5);
		zone.setSurfaceZone((float)112.366);
		zone.setReseau(reseau);
		se.save(zone);		
		t3.commit();
		se.flush();
		
		//Création d'une Geolocalisation
		Transaction t4 = se.beginTransaction();
		geolocalisation.setLatitudeGeolocalisation(1122.336);
		geolocalisation.setLongitudeGeolocalisation(4452.33);
		geolocalisation.setZone(zone);	
		se.save(geolocalisation);
		t4.commit();
		
		//Récupération de l'identifiant nouvellement créer
		se.flush();
		int id=geolocalisation.getIdgeolocalisation();

		//Récupération d'un objet Ligne par la méthode getLigneByID
		geolocalisation=geolocalisation_dao.getGeolocalisationByID(id);
		
		//Vérification des données récupérées
		if(geolocalisation.getLatitudeGeolocalisation()==1122.336 && geolocalisation.getLongitudeGeolocalisation()==4452.33 )
	        		
		{
			test=true; 
		}
		else{
			test=false;
		}

		assertTrue(test); 

		
		//nouvelle purge de la table Ligne & reseau
		Transaction t5 = se.beginTransaction();
		Query delete4=se.createQuery("delete from Geolocalisation");		
		Query delete5=se.createQuery("delete from Zone");
		Query delete6=se.createQuery("delete from Reseau");
		delete4.executeUpdate();
		delete5.executeUpdate();
		delete6.executeUpdate();
		t5.commit();
		se.close();
	}
	
	
	
	protected void setUp() throws Exception {
		super.setUp();
	}

}
