
import java.util.List;

import junit.framework.TestCase;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import beans.Reseau;
import beans.Zone;
import dao.HibernateUtils;
import dao.ZoneDAO;

public class ZoneDAOTest  extends TestCase {

	private Session se = null;


	private Zone zone = new Zone();
	private Zone zone2 = new Zone();
	private Reseau reseau = new Reseau();

	private List<Zone> listeZone;

	private ZoneDAO zone_dao = new ZoneDAO();

	boolean test;


	public void testCreateZone() {

		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction(); 


		Query delete=se.createQuery("delete from Zone");
		Query delete2=se.createQuery("delete from Reseau");		
		delete.executeUpdate();
		delete2.executeUpdate();
		t.commit();

		//création d'un reseau test
		Transaction t2 = se.beginTransaction();
		reseau.setNomReseau("reseau 1");
		se.save(reseau);		
		t2.commit();
		se.flush();

		//Création d'une ligne par la methode createZone
		zone_dao.createZone(50,50,(float)50.500,reseau);

		//Test visant a vérifier la bonne insertion
		Query requete = se.createQuery("select count(*) from Zone where nombreHabitantsParZone=50 and nombreMaxDeStationParZone=50  ");

		//le count doit retourner 1
		assertEquals("1",requete.uniqueResult().toString()); 

		//nouvelle purge de la table Ligne & reseau
		Transaction t3 = se.beginTransaction();
		Query delete3=se.createQuery("delete from Zone");		
		delete3.executeUpdate();
		Query delete4=se.createQuery("delete from Reseau");		
		delete4.executeUpdate();
		t3.commit();
		se.close();

	}
	
	public void testListerZone() {
		

		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction();
		
		//Purge de la table  reseau base de donnée de test
		Query delete=se.createQuery("delete from Zone");
		Query delete2=se.createQuery("delete from Reseau");		
		delete.executeUpdate();
		delete2.executeUpdate();
		t.commit();
		
		//création d'un reseau test
		Transaction t2 = se.beginTransaction();
		reseau.setNomReseau("reseau 1");
		se.save(reseau);		
		t2.commit();
		se.flush();


        //Insertion de 2 nouvelles zones
		Transaction t3 = se.beginTransaction();
		zone.setNombreHabitantsParZone(50);
		zone.setNombreMaxDeStationParZone(50);
		zone.setSurfaceZone((float)50.50);
		zone.setReseau(reseau);
		
		se.save(zone);	

		zone2.setNombreHabitantsParZone(60);
		zone2.setNombreMaxDeStationParZone(60);
		zone2.setSurfaceZone((float)60.60);
		zone2.setReseau(reseau);
		se.save(zone2);	
		t3.commit();
        
		
		//Récupération de la liste de zone par la méthode listerZone
		listeZone= zone_dao.listerZone();

		//Vérification du nombre de résultat (2)
		if (listeZone.size()==2){
			//Vérification des données récupérées
			if(listeZone.get(0).getNombreHabitantsParZone()==50 && listeZone.get(0).getNombreMaxDeStationParZone()==50 && listeZone.get(1).getNombreHabitantsParZone()==60 && listeZone.get(1).getNombreMaxDeStationParZone()==60){
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

		
		//nouvelle purge de la table Ligne & reseau
		Transaction t4 = se.beginTransaction();
		Query delete3=se.createQuery("delete from Zone");		
		Query delete4=se.createQuery("delete from Reseau");		
		delete3.executeUpdate();
		delete4.executeUpdate();		
		t4.commit();
		se.close();

   
    }
	
	public void testGetZoneByID(){

		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction();
		
		//Purge de la table zone & reseau base de donnée de test
		Query delete=se.createQuery("delete from Zone");
		Query delete2=se.createQuery("delete from Reseau");		
		delete.executeUpdate();
		delete2.executeUpdate();
		t.commit();
		
		//création d'un reseau test
		Transaction t2 = se.beginTransaction();
		reseau.setNomReseau("reseau 1");
		se.save(reseau);		
		t2.commit();
		se.flush();
		
		//Création d'une zone
		Transaction t3 = se.beginTransaction();
		zone.setNombreHabitantsParZone(50);
		zone.setNombreMaxDeStationParZone(50);
		zone.setSurfaceZone((float)50.50);
		zone.setReseau(reseau);	
		se.save(zone);
		t3.commit();
		
		//Récupération de l'identifiant nouvellement créer
		se.flush();
		int id=zone.getIdZone();

		//Récupération d'un objet Zone par la méthode getZoneByID
		zone = zone_dao.getZoneByID(id); 
		
		//Vérification des données récupérées
		if(zone.getNombreHabitantsParZone()==50 && zone.getNombreMaxDeStationParZone()==50 ){
			test=true; 
		}
		else{
			test=false;
		}

		assertTrue(test); 

		
		//nouvelle purge de la table zone & reseau
		Transaction t4 = se.beginTransaction();
		Query delete3=se.createQuery("delete from Zone");		
		Query delete4=se.createQuery("delete from Reseau");		
		delete3.executeUpdate();
		delete4.executeUpdate();		
		t4.commit();
		se.close();
	}
	


}
