package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import dao.HibernateUtils;
import beans.Ligne;
import beans.Reseau;
import beans.Station;
import dao.StationDAO;
import junit.framework.TestCase;

public class StationDAOTest extends TestCase {
	
	/*
	 * Test Unitaire de station DAO
	 */


		private Session se = null;
		
	    Station station = new Station();
		Station station2 = new Station();
		private Ligne ligne = new Ligne();
		private Reseau reseau = new Reseau();
		
		private List<Station> listeStation;
		
		private StationDAO station_dao = new StationDAO();
		
		private boolean test;
		
		
		
		/*
		 * Test de la méthode createStation
		 */

		public void testCreateStation(){

			se = HibernateUtils.getSession();
			Transaction t = se.beginTransaction(); 
			
			//Purge de la table ligne & reseau & station base de donnée de test
			Query delete=se.createQuery("delete from Station");
			Query delete2=se.createQuery("delete from Ligne");	
			Query delete3=se.createQuery("delete from Reseau");	
			delete.executeUpdate();
			delete2.executeUpdate();
			delete3.executeUpdate();
			t.commit();
			
			//création d'un reseau test
			Transaction t2 = se.beginTransaction();
			reseau.setNomReseau("reseau 1");
			se.save(reseau);		
		    t2.commit();
		    se.flush();
		    
			//création d'une ligne test
			Transaction t3 = se.beginTransaction();
			ligne.setNomLigne("Ligne 1");
			ligne.setCommentaire("Ligne 1");
			ligne.setReseau(reseau);
			se.save(ligne);		
		    t3.commit();
		    se.flush();

			//Création d'une station par la methode createStation
			station_dao.createStation("station Alpha", "station Alpha",55.5,55.5,ligne);
	 
			//Test visant a vérifier la bonne insertion
			Query requete = se.createQuery("select count(*) from Station where nomStation='station Alpha' and commentaireStation='station Alpha' and latitude=55.5 and longitude=55.5");
	 
			//le count doit retourner 1
			assertEquals("1",requete.uniqueResult().toString()); 

			//nouvelle purge de la table Ligne & reseau & station
			Transaction t4 = se.beginTransaction();
			Query delete4=se.createQuery("delete from Station");
			Query delete5=se.createQuery("delete from Ligne");	
			Query delete6=se.createQuery("delete from Reseau");	
			delete4.executeUpdate();
			delete5.executeUpdate();
			delete6.executeUpdate();
			t4.commit();
			se.close();

		}
		
		/*
		 * Test de la méthode supprimerStation
		 */
		public void testSupprimerStation(){


			
			se = HibernateUtils.getSession();
			Transaction t = se.beginTransaction(); 
			
			//Purge de la table ligne & reseau & station base de donnée de test
			Query delete=se.createQuery("delete from Station");
			Query delete2=se.createQuery("delete from Ligne");	
			Query delete3=se.createQuery("delete from Reseau");	
			delete.executeUpdate();
			delete2.executeUpdate();
			delete3.executeUpdate();
			t.commit();
			
			//création d'un reseau test
			Transaction t2 = se.beginTransaction();
			reseau.setNomReseau("reseau 1");
			se.save(reseau);		
		    t2.commit();
		    se.flush();
		    
			//création d'une ligne test
			Transaction t3 = se.beginTransaction();
			ligne.setNomLigne("Ligne 1");
			ligne.setCommentaire("Ligne 1");
			ligne.setReseau(reseau);
			se.save(ligne);		
		    t3.commit();
		    se.flush();
		    
			//création d'une Station test
			Transaction t4 = se.beginTransaction();
		     station.setNomStation("Station Alpha");
		     station.setCommentaireStation("Station Alpha");
		     station.setLatitude(55.5);
		     station.setLongitude(55.5);
		     station.setLigne(ligne);
			se.save(station);		
		    t4.commit();
		    se.flush();

			
			//Récupération de l'identifiant nouvellement créer
			se.flush();		
			int id=station.getIdStation();

			//Suppression de la ligne créer par la methode supprimer Ligne
			station_dao.supprimerStation(id);

			//Vérification de la suppression
			Query requete = se.createQuery("select count(*) from Station where idStation="+id);
	        
			//Le count doit retourner 0
			assertEquals("0",requete.uniqueResult().toString());
			
			
			
			//nouvelle purge de la table Ligne & reseau & station
			Transaction t5 = se.beginTransaction();
			Query delete4=se.createQuery("delete from Station");
			Query delete5=se.createQuery("delete from Ligne");	
			Query delete6=se.createQuery("delete from Reseau");	
			delete4.executeUpdate();
			delete5.executeUpdate();
			delete6.executeUpdate();
			t5.commit();
			se.close();
			


		}
		
		
		/*
		 * Test de la méthode listerStation
		 */
		 
		public void testListerStation(){


			se = HibernateUtils.getSession();
			Transaction t = se.beginTransaction(); 
			
			//Purge de la table ligne & reseau & station base de donnée de test
			Query delete=se.createQuery("delete from Station");
			Query delete2=se.createQuery("delete from Ligne");	
			Query delete3=se.createQuery("delete from Reseau");	
			delete.executeUpdate();
			delete2.executeUpdate();
			delete3.executeUpdate();
			t.commit();
			
			//création d'un reseau test
			Transaction t2 = se.beginTransaction();
			reseau.setNomReseau("reseau 1");
			se.save(reseau);		
		    t2.commit();
		    se.flush();
		    
			//création d'une ligne test
			Transaction t3 = se.beginTransaction();
			ligne.setNomLigne("Ligne 1");
			ligne.setCommentaire("Ligne 1");
			ligne.setReseau(reseau);
			se.save(ligne);		
		    t3.commit();
		    se.flush();
		    
			//création d'une Station test
			Transaction t4 = se.beginTransaction();
		    station.setNomStation("Station Alpha");
		    station.setCommentaireStation("Station Alpha");
		    station.setLatitude(55.5);
		    station.setLongitude(55.5);
		    station.setLigne(ligne);
			se.save(station);
			
			station2.setNomStation("Station Beta");
			station2.setCommentaireStation("Station Beta");
			station2.setLatitude(55.5);
			station2.setLongitude(55.5);
			station2.setLigne(ligne);
			se.save(station2);
		    t4.commit();
		    se.flush();
	        
			
			//Récupération de la liste de ligne par la méthode listerLigne
			listeStation= station_dao.listerStation();

			//Vérification du nombre de résultat (2)
			if (listeStation.size()==2){
				//Vérification des données récupérées
				if(listeStation.get(0).getNomStation().equals("Station Alpha") && listeStation.get(0).getCommentaireStation().equals("Station Alpha")
						&& listeStation.get(0).getLatitude() == 55.5 && listeStation.get(0).getLongitude() == 55.5
						&& listeStation.get(1).getNomStation().equals("Station Beta") && listeStation.get(1).getCommentaireStation().equals("Station Beta")
						&& listeStation.get(1).getLatitude() == 55.5 && listeStation.get(1).getLongitude() == 55.5){
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

			
			//nouvelle purge de la table Ligne & reseau & station
			Transaction t5 = se.beginTransaction();
			Query delete4=se.createQuery("delete from Station");
			Query delete5=se.createQuery("delete from Ligne");	
			Query delete6=se.createQuery("delete from Reseau");	
			delete4.executeUpdate();
			delete5.executeUpdate();
			delete6.executeUpdate();
			t5.commit();
			se.close();

		}
		
		/*
		 * Test de la méthode getStationByID
		 */
		
		public void testGetStationByID(){

			se = HibernateUtils.getSession();
			Transaction t = se.beginTransaction(); 
			
			//Purge de la table ligne & reseau & station base de donnée de test
			Query delete=se.createQuery("delete from Station");
			Query delete2=se.createQuery("delete from Ligne");	
			Query delete3=se.createQuery("delete from Reseau");	
			delete.executeUpdate();
			delete2.executeUpdate();
			delete3.executeUpdate();
			t.commit();
			
			//création d'un reseau test
			Transaction t2 = se.beginTransaction();
			reseau.setNomReseau("reseau 1");
			se.save(reseau);		
		    t2.commit();
		    se.flush();
		    
			//création d'une ligne test
			Transaction t3 = se.beginTransaction();
			ligne.setNomLigne("Ligne 1");
			ligne.setCommentaire("Ligne 1");
			ligne.setReseau(reseau);
			se.save(ligne);		
		    t3.commit();
		    se.flush();
		    
			//création d'une Station test
			Transaction t4 = se.beginTransaction();
		     station.setNomStation("Station Alpha");
		     station.setCommentaireStation("Station Alpha");
		     station.setLatitude(55.5);
		     station.setLongitude(55.5);
		     station.setLigne(ligne);
			se.save(station);		
		    t4.commit();
		    se.flush();
			
			//Récupération de l'identifiant nouvellement créer
			int id=station.getIdStation();

			//Récupération d'un objet Ligne par la méthode getLigneByID
			station = station_dao.getStationByID(id); 
			
			//Vérification des données récupérées
			if(station.getNomStation().equals("Station Alpha") && station.getCommentaireStation().equals("Station Alpha") 
					&& station.getLatitude()==55.5 &&  station.getLongitude()==55.5){
				test=true; 
			}
			else{
				test=false;
			}

			assertTrue(test); 

			
			//nouvelle purge de la table Ligne & reseau & station
			Transaction t5 = se.beginTransaction();
			Query delete4=se.createQuery("delete from Station");
			Query delete5=se.createQuery("delete from Ligne");	
			Query delete6=se.createQuery("delete from Reseau");	
			delete4.executeUpdate();
			delete5.executeUpdate();
			delete6.executeUpdate();
			t5.commit();
			se.close();
		}
		
		/*
		 * Test de la méthode ModifierStation
		 */
		public void testModifierStation(){

			se = HibernateUtils.getSession();
			Transaction t = se.beginTransaction(); 
			
			//Purge de la table ligne & reseau & station base de donnée de test
			Query delete=se.createQuery("delete from Station");
			Query delete2=se.createQuery("delete from Ligne");	
			Query delete3=se.createQuery("delete from Reseau");	
			delete.executeUpdate();
			delete2.executeUpdate();
			delete3.executeUpdate();
			t.commit();
			
			//création d'un reseau test
			Transaction t2 = se.beginTransaction();
			reseau.setNomReseau("reseau 1");
			se.save(reseau);		
		    t2.commit();
		    se.flush();
		    
			//création d'une ligne test
			Transaction t3 = se.beginTransaction();
			ligne.setNomLigne("Ligne 1");
			ligne.setCommentaire("Ligne 1");
			ligne.setReseau(reseau);
			se.save(ligne);		
		    t3.commit();
		    se.flush();
		    
			//création d'une Station test
			Transaction t4 = se.beginTransaction();
		     station.setNomStation("Station Alpha");
		     station.setCommentaireStation("Station Alpha");
		     station.setLatitude(55.5);
		     station.setLongitude(55.5);
		     station.setLigne(ligne);
			se.save(station);		
		    t4.commit();
		    se.flush();
			
			//Récupération de l'identifiant nouvellement créer
			int id=station.getIdStation();

			//Modification de l'objet Ligne par la méthode modifierLigne
			station.setIdStation(id);
		    station.setNomStation("Station Beta");
		    station.setCommentaireStation("Station Beta");
		    station.setLatitude(66.6);
		    station.setLongitude(66.6);
			station_dao.modifierStation(station);



			//Récuperation de la ligne modifiée
			station = (Station) se.createQuery("from Station where idStation="+id).uniqueResult();
			
			

			//Vérification des modifications
			if(station.getNomStation().equals("Station Beta") && station.getCommentaireStation().equals("Station Beta") 
					&& station.getLatitude()==66.6 &&  station.getLongitude()==66.6){
				test=true; 
			}
			else{
				test=false;
			}
			
			assertTrue(test);
			
			//nouvelle purge de la table Ligne & reseau & station
			Transaction t5 = se.beginTransaction();
			Query delete4=se.createQuery("delete from Station");
			Query delete5=se.createQuery("delete from Ligne");	
			Query delete6=se.createQuery("delete from Reseau");	
			delete4.executeUpdate();
			delete5.executeUpdate();
			delete6.executeUpdate();
			t5.commit();
			se.close();

		}
		
		/*
		 * Test de la méthode ModifierStation
		 */
		public void testCreateStationToStation(){
			
			se = HibernateUtils.getSession();
			Transaction t = se.beginTransaction(); 
			
			//Purge de la table ligne & reseau & station base de donnée de test
			Query delete=se.createQuery("delete from Station");
			Query delete2=se.createQuery("delete from Ligne");	
			Query delete3=se.createQuery("delete from Reseau");	
			delete.executeUpdate();
			delete2.executeUpdate();
			delete3.executeUpdate();
			t.commit();
			
			//création d'un reseau test
			Transaction t2 = se.beginTransaction();
			reseau.setNomReseau("reseau 1");
			se.save(reseau);		
		    t2.commit();
		    se.flush();
		    
			//création d'une ligne test
			Transaction t3 = se.beginTransaction();
			ligne.setNomLigne("Ligne 1");
			ligne.setCommentaire("Ligne 1");
			ligne.setReseau(reseau);
			se.save(ligne);		
		    t3.commit();
		    se.flush();
		    


		  
		    
			//Création d'une station par la methode createStation
			station_dao.createStation("station Alpha", "station Alpha",55.5,55.5,ligne);
			//Création d'une station par la methode createStation
			station_dao.createStation("station Beta", "station Beta",55.5,55.5,ligne);
			
		
			
			station = (Station) se.createQuery("from Station where nomStation='station Alpha' AND commentaireStation='station Alpha'").uniqueResult();
			station2 = (Station) se.createQuery("from Station where nomStation='station Beta' AND commentaireStation='station Beta'").uniqueResult();
		    
			
			station_dao.createStationToStation(station,station2);
		    
		    
		    int idStation =station.getIdStation();
		    int idStation2 =station2.getIdStation();
		    
		   System.out.println(idStation);
		   System.out.println(idStation2);
		   
		    List<Station> listeStationAller1 = station.getStationAller() ;
		    List<Station> listeStationAller2 = station2.getStationAller() ;
		    
		    System.out.println(listeStationAller1.get(0).getIdStation());
		    System.out.println(listeStationAller2.get(0).getIdStation());
		    
		    
		    
		    if(listeStationAller1.get(0).getIdStation()==idStation2
		    		&& listeStationAller2.get(0).getIdStation()==idStation ){
				test=true; 
			}
			else{
				test=false;
			}
		    
			assertTrue(test);
			Transaction t5 = se.beginTransaction();
			Query delete7=se.createSQLQuery("delete from station_has_station");
			delete7.executeUpdate();
			t5.commit();
			Transaction t6 = se.beginTransaction();
			Query delete4=se.createQuery("delete from Station");
			Query delete5=se.createQuery("delete from Ligne");	
			Query delete6=se.createQuery("delete from Reseau");	
			delete7.executeUpdate();
			delete4.executeUpdate();
			delete5.executeUpdate();
			delete6.executeUpdate();
			t6.commit();
			se.close();

		}
		


}
