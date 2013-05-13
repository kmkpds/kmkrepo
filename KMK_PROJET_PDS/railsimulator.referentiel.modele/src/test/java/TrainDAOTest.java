package test;

import java.util.List;

import junit.framework.TestCase;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import beans.Reseau;
import beans.Zone;
import beans.Ligne;
import beans.Train;

import dao.HibernateUtils;
import dao.ZoneDAO;
import dao.TrainDAO;

public class TrainDAOTest  extends TestCase {

	private Session se = null;


	private Zone zone = new Zone();
	private Zone zone2 = new Zone();
	private Reseau reseau = new Reseau();
	private List<Zone> listeZone;
	private ZoneDAO zone_dao = new ZoneDAO();

	
	private Train train = new Train();
	private Train train2 = new Train();
	private Ligne ligne = new Ligne();
	private List<Train> listeTrain ;
	private TrainDAO train_dao = new TrainDAO();
	
	boolean test;


	public void testCreateTrain() {

		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction(); 
		
		Query delete5=se.createQuery("delete from Train");
		Query delete=se.createQuery("delete from Ligne");
		Query delete2=se.createQuery("delete from Reseau");	
		
		delete5.executeUpdate();
		delete.executeUpdate();
		t.commit();

		//création d'un reseau test
		Transaction t1 = se.beginTransaction();
		reseau.setNomReseau("reseau 1");
		se.save(reseau);		
		t1.commit();
		se.flush();
		
		//création d'une ligne test
		Transaction t2 = se.beginTransaction();
		ligne.setReseau(reseau);
		ligne.setNomLigne("ligne 14");
		ligne.setCommentaire("test");
		ligne.setLongueur(45);
		ligne.setNombredestation(15);
		ligne.setDureemoyennetrajet(30);
		ligne.setNombredetrain(15);
		ligne.setPrixdeplace(0.12);
		ligne.setNombrepassagers(50000);
		se.save(ligne);		
		t2.commit();
		se.flush();
		

		//Création d'un Train par la methode createTrain
		zone_dao.createZone(50,50,(float)50.500,reseau);
		train_dao.createTrain("test", 50.50, 34.6, 5, ligne);
		

		//Test visant a vérifier la bonne insertion
		Query requete = se.createQuery("select count(*) from Train where nomtrain=test and latitudetrain=50.50 and longitudetrain=34.6 and etat =5 ");

		//le count doit retourner 1
		assertEquals("1",requete.uniqueResult().toString()); 

		//nouvelle purge de la table Train & Ligne & reseau
		Transaction t3 = se.beginTransaction();		
		
		Query delete6=se.createQuery("delete from Train");		
		delete6.executeUpdate();
		Query delete3=se.createQuery("delete from  Ligne ");		
		delete3.executeUpdate();
		Query delete4=se.createQuery("delete from Reseau");		
		delete4.executeUpdate();
		t3.commit();
		se.close();

	}
	
	public void testListerTrain() {
		

		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction();
		
		//Purge de la table  reseau base de donnée de test
		Query delete=se.createQuery("delete from Train");		
		delete.executeUpdate();
		t.commit();
		
		//création d'un reseau test
		Transaction t3 = se.beginTransaction();
		reseau.setNomReseau("reseau 1");
		se.save(reseau);		
		t3.commit();
		se.flush();
		
		//création d'une ligne test
		Transaction t4 = se.beginTransaction();
		ligne.setReseau(reseau);
		ligne.setNomLigne("ligne 14");
		ligne.setCommentaire("test");
		ligne.setLongueur(45);
		ligne.setNombredestation(15);
		ligne.setDureemoyennetrajet(30);
		ligne.setNombredetrain(15);
		ligne.setPrixdeplace(0.12);
		ligne.setNombrepassagers(50000);
		se.save(ligne);		
		t4.commit();
		se.flush();


        //Insertion de 2 nouvelles zones
		Transaction t5 = se.beginTransaction();
		train.setNomTrain("test");
		train.setLatitudeTrain(50.5);
		train.setLongitudeTrain(44);
		train.setEtat(5);
		se.save(train);	

		train2.setNomTrain("test2");
		train2.setLatitudeTrain(66);
		train2.setLongitudeTrain(66);
		train2.setEtat(6);
		se.save(train2);	
		t5.commit();
        
		
		//Récupération de la liste de zone par la méthode listerZone
		listeTrain=train_dao.listerTrain();

		//Vérification du nombre de résultat (2)
		if (listeTrain.size()==2){
			test= true ;
		}
		else{
			test= false;
		}


		assertTrue(test); 

		
		//nouvelle purge de la table  Train & Ligne & reseau
		Transaction t6 = se.beginTransaction();
		Query delete3=se.createQuery("delete from Train");
		Query delete4=se.createQuery("delete from Ligne");
		Query delete5=se.createQuery("delete from Reseau");		
		delete3.executeUpdate();
		delete4.executeUpdate();
		delete5.executeUpdate();
		//delete4.executeUpdate();		
		t6.commit();
		se.close();

   
    }
	
	public void testGetTrainByID(){

		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction(); 
		
		Query delete5=se.createQuery("delete from Train");
		Query delete=se.createQuery("delete from Ligne");
		Query delete2=se.createQuery("delete from Reseau");	
		
		delete5.executeUpdate();
		delete.executeUpdate();
		delete2.executeUpdate();
		t.commit();
		
		//création d'un reseau test
		Transaction t1 = se.beginTransaction();
		reseau.setNomReseau("reseau 1");
		se.save(reseau);		
		t1.commit();
		se.flush();
		
		//création d'une ligne test
		Transaction t2 = se.beginTransaction();
		ligne.setReseau(reseau);
		ligne.setNomLigne("ligne 14");
		ligne.setCommentaire("test");
		ligne.setLongueur(45);
		ligne.setNombredestation(15);
		ligne.setDureemoyennetrajet(30);
		ligne.setNombredetrain(15);
		ligne.setPrixdeplace(0.12);
		ligne.setNombrepassagers(50000);
		se.save(ligne);		
		t2.commit();
		se.flush();
		
		//Création d'un train
		Transaction t3 = se.beginTransaction();
		train.setNomTrain("test");
		train.setLatitudeTrain(50.5);
		train.setLongitudeTrain(44);
		train.setEtat(5);
		se.save(train);	
		t3.commit();
		
		//Récupération de l'identifiant nouvellement créer
		se.flush();
		int id=train.getIdTrain();

		//Récupération d'un objet Zone par la méthode getZoneByID
		train= train_dao.getTrainByID(id);
		
		//Vérification des données récupérées
		if(train.getNomTrain()=="test" && train.getLatitudeTrain()==50.5 && train.getLongitudeTrain()==44 && train.getEtat()==5 ){
			test=true; 
		}
		else{
			test=false;
		}

		assertTrue(test); 

		
		//nouvelle purge de la table zone & reseau
		Transaction t6 = se.beginTransaction();
		Query delete1=se.createQuery("delete from Train");
		Query delete4=se.createQuery("delete from Ligne");
		Query delete3=se.createQuery("delete from Reseau");	
		
		delete1.executeUpdate();
		delete4.executeUpdate();
		delete3.executeUpdate();
		t6.commit();
		se.close();
	}
	



}
