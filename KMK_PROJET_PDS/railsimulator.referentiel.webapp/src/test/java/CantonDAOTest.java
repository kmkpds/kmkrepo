

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Canton;
import beans.Ligne;
import beans.Reseau;
import beans.Station;
import dao.CantonDAO;
import dao.HibernateUtils;
import dao.StationDAO;
import junit.framework.TestCase;

public class CantonDAOTest extends TestCase {
	
	private Session se = null;
	
    Station station = new Station();
	Station station2 = new Station();
	private Ligne ligne = new Ligne();
	private Reseau reseau = new Reseau();
	
	private List<Canton> listeCanton=new ArrayList<Canton>();
	private int[][] listeStation;
	Canton canton=new Canton();
	Canton canton2=new Canton();
	private CantonDAO canton_dao= new CantonDAO();
	
	private boolean test;

	
	// Test de la m�thode createCantonParamStation ok
	

	public void testcreateCantonParamStation(){

		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction(); 
		
		//Purge des tables base de donnEe de test
		Query delete1=se.createSQLQuery("delete from canton");
		Query delete2=se.createSQLQuery("delete from station_has_station");
		Query delete3=se.createSQLQuery("delete from station");	
		Query delete4=se.createSQLQuery("delete from parametrehoraire");	
		Query delete5=se.createSQLQuery("delete from ligne");
		Query delete12=se.createSQLQuery("delete from reseau");	
		delete1.executeUpdate();
		delete2.executeUpdate();
		delete3.executeUpdate();
		delete4.executeUpdate();
		delete5.executeUpdate();
		delete12.executeUpdate();
		t.commit();
		
		//cr�ation d'un reseau test
		Transaction t2 = se.beginTransaction();
		reseau.setNomReseau("reseau 1");
		se.save(reseau);		
	    t2.commit();
	    se.flush();
	    
		//cr�ation d'une ligne test
		Transaction t3 = se.beginTransaction();
		ligne.setNomLigne("Ligne 1");
		ligne.setCommentaire("Ligne 1");
		ligne.setReseau(reseau);
		se.save(ligne);		
	    t3.commit();
	    se.flush();
	    
		
		//creation d'une station test
		Transaction t4 = se.beginTransaction();
		Station station1 = new Station();
		station1.setIdStation(1);
		station1.setLatitude(10);
		station1.setLongitude(25);
		station1.setNomStation("Test Station 1");
		station1.setLigne(ligne);
		se.save(station1);		
	    t4.commit();
	    se.flush();
	    
	    //creation d'une deuxieme station test
	    Transaction t5 = se.beginTransaction();
		station2.setIdStation(2);
		station2.setLatitude(50);
		station2.setLongitude(75);
		station2.setNomStation("Test Station 2");
		station2.setLigne(ligne);
		se.save(station2);
		t5.commit();
		se.flush();

		double distance=6359631.622653402;		
		double nbcanton = 31798.15811326701; //car distance/200
		int modulo =2;
		
		//Cr�ation d'une station par la methode createStation
		int idcanton=canton_dao.createCantonParamStation(200+modulo,station1,station2);
 
		//Test visant a v�rifier la bonne insertion
		Query requete = se.createQuery("select count(*) from Canton where idCanton="+idcanton);
 
		//le count doit retourner 1
		assertEquals("1",requete.uniqueResult().toString()); 

		//nouvelle purge de la table Ligne & reseau & station
		Transaction t6 = se.beginTransaction();
		Query delete6=se.createSQLQuery("delete from canton");
		Query delete7=se.createSQLQuery("delete from station_has_station");
		Query delete8=se.createSQLQuery("delete from station");	
		Query delete9=se.createSQLQuery("delete from parametrehoraire");	
		Query delete10=se.createSQLQuery("delete from ligne");	
		Query delete11=se.createSQLQuery("delete from reseau");	
		delete6.executeUpdate();
		delete7.executeUpdate();
		delete8.executeUpdate();
		delete9.executeUpdate();
		delete10.executeUpdate();
		delete11.executeUpdate();
		t6.commit();
		se.close();

	}
	
	// Test de la m�thode supprimerCanton ok
	
	
	public void testsupprimerCanton(){


		
		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction(); 
		
		Query delete1=se.createSQLQuery("delete from canton");
		Query delete2=se.createSQLQuery("delete from station_has_station");
		Query delete3=se.createSQLQuery("delete from station");	
		Query delete4=se.createSQLQuery("delete from parametrehoraire");	
		Query delete5=se.createSQLQuery("delete from ligne");
		Query delete12=se.createSQLQuery("delete from reseau");	
		delete1.executeUpdate();
		delete2.executeUpdate();
		delete3.executeUpdate();
		delete4.executeUpdate();
		delete5.executeUpdate();
		delete12.executeUpdate();
		t.commit();
		
		//cr�ation d'un reseau test
		Transaction t2 = se.beginTransaction();
		reseau.setNomReseau("reseau 1");
		se.save(reseau);		
	    t2.commit();
	    se.flush();
	    
		//cr�ation d'une ligne test
		Transaction t3 = se.beginTransaction();
		ligne.setNomLigne("Ligne 1");
		ligne.setCommentaire("Ligne 1");
		ligne.setReseau(reseau);
		se.save(ligne);		
	    t3.commit();
	    se.flush();
	    
		//cr�ation d'une Station test
		Transaction t4 = se.beginTransaction();
	     station.setNomStation("Station Alpha");
	     station.setCommentaireStation("Station Alpha");
	     station.setLatitude(55.5);
	     station.setLongitude(55.5);
	     station.setLigne(ligne);
		se.save(station);		
	    t4.commit();
	    se.flush();
	    
	    Transaction t7 = se.beginTransaction();
		station2.setIdStation(2);
		station2.setLatitude(50);
		station2.setLongitude(75);
		station2.setNomStation("Test Station 2");
		station2.setLigne(ligne);
		se.save(station2);
		t7.commit();
		se.flush();

		Transaction t6 = se.beginTransaction();
	     canton.setDistance(200);
	     canton.setStation1(station);
	     canton.setStation2(station2);
		se.save(canton);		
	    t6.commit();
	    se.flush();
		
		//R�cup�ration de l'identifiant nouvellement cr�er
		int id=canton.getIdCanton();

		//Suppression de la ligne cr�er par la methode supprimer Ligne
		canton_dao.supprimerCanton(id);

		//V�rification de la suppression
		Query requete = se.createQuery("select count(*) from Canton where idCanton="+id);
        
		//Le count doit retourner 0
		assertEquals("0",requete.uniqueResult().toString());
		
		
		
		//nouvelle purge de la table Ligne & reseau & station
		Transaction t5 = se.beginTransaction();
		Query delete6=se.createSQLQuery("delete from canton");
		Query delete7=se.createSQLQuery("delete from station_has_station");
		Query delete8=se.createSQLQuery("delete from station");	
		Query delete9=se.createSQLQuery("delete from parametrehoraire");	
		Query delete10=se.createSQLQuery("delete from ligne");	
		Query delete11=se.createSQLQuery("delete from reseau");	
		delete6.executeUpdate();
		delete7.executeUpdate();
		delete8.executeUpdate();
		delete9.executeUpdate();
		delete10.executeUpdate();
		delete11.executeUpdate();
		t5.commit();
		se.close();
		


	}
	
	
	
	 // Test de la m�thode listerCanton ok
	 
	 
	public void testlisterCanton(){


		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction(); 
		

		//Purge des tables base de donnEe de test
		Query delete1=se.createSQLQuery("delete from canton");
		Query delete2=se.createSQLQuery("delete from station_has_station");
		Query delete3=se.createSQLQuery("delete from station");	
		Query delete4=se.createSQLQuery("delete from parametrehoraire");	
		Query delete5=se.createSQLQuery("delete from ligne");
		Query delete12=se.createSQLQuery("delete from reseau");	
		delete1.executeUpdate();
		delete2.executeUpdate();
		delete3.executeUpdate();
		delete4.executeUpdate();
		delete5.executeUpdate();
		delete12.executeUpdate();
		t.commit();
		
		//cr�ation d'un reseau test
		Transaction t2 = se.beginTransaction();
		reseau.setNomReseau("reseau 1");
		se.save(reseau);		
	    t2.commit();
	    se.flush();
	    
		//cr�ation d'une ligne test
		Transaction t3 = se.beginTransaction();
		ligne.setNomLigne("Ligne 1");
		ligne.setCommentaire("Ligne 1");
		ligne.setReseau(reseau);
		se.save(ligne);		
	    t3.commit();
	    se.flush();
	    
		
		//creation d'une station test
		Transaction t4 = se.beginTransaction();
		station.setIdStation(1);
		station.setLatitude(10);
		station.setLongitude(25);
		station.setNomStation("Station Alpha");
		station.setCommentaireStation("Station Alpha");
		station.setLigne(ligne);
		se.save(station);		
	    t4.commit();
	    se.flush();
	    
	    //creation d'une deuxieme station test
	    Transaction t5 = se.beginTransaction();
		station2.setIdStation(2);
		station2.setLatitude(50);
		station2.setLongitude(75);
		station2.setNomStation("Station Beta");
		station2.setCommentaireStation("Station Beta");
		station2.setLigne(ligne);
		se.save(station2);
		t5.commit();
		se.flush();
		
		Transaction t6 = se.beginTransaction();
	     canton.setDistance(200);
	     canton.setStation1(station);
	     canton.setStation2(station2);	     
		se.save(canton);	
	    t6.commit();
	    se.flush();
	    
		Transaction t8 = se.beginTransaction();

	     canton2.setDistance(230);
	     canton2.setStation1(station);
	     canton2.setStation2(station2);
	
		se.save(canton2);
	    t8.commit();
	    se.flush();
		
		//R�cup�ration de la liste de ligne par la m�thode listerLigne
		listeCanton= canton_dao.listerCanton();

		//V�rification du nombre de r�sultat (2)
		if (listeCanton.size()==2){
			//V�rification des donn�es r�cup�r�es
			if(listeCanton.get(0).getStation1().getNomStation().equals("Station Alpha") && listeCanton.get(0).getStation1().getCommentaireStation().equals("Station Alpha")
					&& listeCanton.get(0).getStation1().getLatitude() == 10 && listeCanton.get(0).getStation1().getLongitude() == 25
					&& listeCanton.get(1).getStation2().getNomStation().equals("Station Beta") && listeCanton.get(1).getStation2().getCommentaireStation().equals("Station Beta")
					&& listeCanton.get(1).getStation2().getLatitude() == 50 && listeCanton.get(1).getStation2().getLongitude() == 75){
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

		

		Transaction t7 = se.beginTransaction();
		Query delete6=se.createSQLQuery("delete from canton");
		Query delete7=se.createSQLQuery("delete from station_has_station");
		Query delete8=se.createSQLQuery("delete from station");	
		Query delete9=se.createSQLQuery("delete from parametrehoraire");	
		Query delete10=se.createSQLQuery("delete from ligne");	
		Query delete11=se.createSQLQuery("delete from reseau");	
		delete6.executeUpdate();
		delete7.executeUpdate();
		delete8.executeUpdate();
		delete9.executeUpdate();
		delete10.executeUpdate();
		delete11.executeUpdate();
		t7.commit();
		se.close();

	}
	
	//Test de la m�thode getStationByID ook
	 
	
	public void testgetCantonByID(){

		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction(); 
		//Purge des tables base de donnEe de test
		Query delete1=se.createSQLQuery("delete from canton");
		Query delete2=se.createSQLQuery("delete from station_has_station");
		Query delete3=se.createSQLQuery("delete from station");	
		Query delete4=se.createSQLQuery("delete from parametrehoraire");	
		Query delete5=se.createSQLQuery("delete from ligne");
		Query delete12=se.createSQLQuery("delete from reseau");	
		delete1.executeUpdate();
		delete2.executeUpdate();
		delete3.executeUpdate();
		delete4.executeUpdate();
		delete5.executeUpdate();
		delete12.executeUpdate();
		t.commit();
		
		//cr�ation d'un reseau test
		Transaction t2 = se.beginTransaction();
		reseau.setNomReseau("reseau 1");
		se.save(reseau);		
	    t2.commit();
	    se.flush();
	    
		//cr�ation d'une ligne test
		Transaction t3 = se.beginTransaction();
		ligne.setNomLigne("Ligne 1");
		ligne.setCommentaire("Ligne 1");
		ligne.setReseau(reseau);
		se.save(ligne);		
	    t3.commit();
	    se.flush();
	    
		
		//creation d'une station test
		Transaction t4 = se.beginTransaction();
		station.setIdStation(1);
		station.setLatitude(10);
		station.setLongitude(25);
		station.setNomStation("Station Alpha");
		station.setCommentaireStation("Station Alpha");
		station.setLigne(ligne);
		se.save(station);		
	    t4.commit();
	    se.flush();
	    
	    //creation d'une deuxieme station test
	    Transaction t5 = se.beginTransaction();
		station2.setIdStation(2);
		station2.setLatitude(50);
		station2.setLongitude(75);
		station2.setNomStation("Station Beta");
		station2.setCommentaireStation("Station Beta");
		station2.setLigne(ligne);
		se.save(station2);
		t5.commit();
		se.flush();
		
		Transaction t6 = se.beginTransaction();
	     canton.setDistance(200);
	     canton.setStation1(station);
	     canton.setStation2(station2);	     
		se.save(canton);	
	    t6.commit();
	    se.flush();
	    
		
		//R�cup�ration de l'identifiant nouvellement cr�er
		int id=canton.getIdCanton();
		
		//R�cup�ration d'un objet Ligne par la m�thode getLigneByID
		canton = canton_dao.getCantonByID(id); 
	
		//V�rification des donn�es r�cup�r�es 
		if(canton.getIdCanton()==id && canton.getDistance()==200){
			test=true; 
		}
		else{
			test=false;
		}

		assertTrue(test); 

		
		//nouvelle purge de la table Ligne & reseau & station
		Transaction t9 = se.beginTransaction();
		Query delete6=se.createSQLQuery("delete from canton");
		Query delete7=se.createSQLQuery("delete from station_has_station");
		Query delete8=se.createSQLQuery("delete from station");	
		Query delete9=se.createSQLQuery("delete from parametrehoraire");	
		Query delete10=se.createSQLQuery("delete from ligne");
		Query delete11=se.createSQLQuery("delete from reseau");	
		delete6.executeUpdate();
		delete7.executeUpdate();
		delete8.executeUpdate();
		delete9.executeUpdate();
		delete10.executeUpdate();
		delete11.executeUpdate();
		t9.commit();
		se.close();
	}
	
	
	 // Test de la m�thode ModifierCanton ok
	 
	public void testmodifierCanton(){

		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction(); 
		
		//Purge des tables base de donnEe de test
		Query delete1=se.createSQLQuery("delete from canton");
		Query delete2=se.createSQLQuery("delete from station_has_station");
		Query delete3=se.createSQLQuery("delete from station");	
		Query delete4=se.createSQLQuery("delete from parametrehoraire");	
		Query delete5=se.createSQLQuery("delete from ligne");
		Query delete12=se.createSQLQuery("delete from reseau");	
		delete1.executeUpdate();
		delete2.executeUpdate();
		delete3.executeUpdate();
		delete4.executeUpdate();
		delete5.executeUpdate();
		delete12.executeUpdate();
		t.commit();
		
		//cr�ation d'un reseau test
		Transaction t2 = se.beginTransaction();
		reseau.setNomReseau("reseau 1");
		se.save(reseau);		
	    t2.commit();
	    se.flush();
	    
		//cr�ation d'une ligne test
		Transaction t3 = se.beginTransaction();
		ligne.setNomLigne("Ligne 1");
		ligne.setCommentaire("Ligne 1");
		ligne.setReseau(reseau);
		se.save(ligne);		
	    t3.commit();
	    se.flush();
	    
		
		//creation d'une station test
		Transaction t4 = se.beginTransaction();
		station.setIdStation(1);
		station.setLatitude(10);
		station.setLongitude(25);
		station.setNomStation("Station Alpha");
		station.setLigne(ligne);
		se.save(station);		
	    t4.commit();
	    se.flush();
	    
	    //creation d'une deuxieme station test
	    Transaction t5 = se.beginTransaction();
		station2.setIdStation(2);
		station2.setLatitude(50);
		station2.setLongitude(75);
		station2.setNomStation("Station Beta");
		station2.setLigne(ligne);
		se.save(station2);
		t5.commit();
		se.flush();
		
		Transaction t6 = se.beginTransaction();
	     canton.setDistance(200);
	     canton.setStation1(station);
	     canton.setStation2(station2);
		se.save(canton);		
	    t6.commit();
	    se.flush();

		//R�cup�ration de l'identifiant nouvellement cr�er
		int id=canton.getIdCanton();

		//Modification de l'objet Ligne par la m�thode modifierLigne
		canton.setIdCanton(id);
		canton.setDistance(210);
		canton.setStation1(station);
		canton.setStation2(station2);
	    
	    
		canton_dao.modifierCanton(canton);



		//R�cuperation du canton modifi�e
		canton = (Canton) se.createQuery("from Canton where idCanton="+id).uniqueResult();
		

		//V�rification des modifications
		if(canton.getDistance()==210 && canton.getIdCanton()==id){
			test=true; 
		}
		else{
			test=false;
		}

		assertTrue(test);
		
		
		//nouvelle purge de la table Ligne & reseau & station
		Transaction t7 = se.beginTransaction();
		Query delete6=se.createSQLQuery("delete from canton");
		Query delete7=se.createSQLQuery("delete from station_has_station");
		Query delete8=se.createSQLQuery("delete from station");	
		Query delete9=se.createSQLQuery("delete from parametrehoraire");	
		Query delete10=se.createSQLQuery("delete from ligne");	
		Query delete11=se.createSQLQuery("delete from reseau");	
		delete6.executeUpdate();
		delete7.executeUpdate();
		delete8.executeUpdate();
		delete9.executeUpdate();
		delete10.executeUpdate();
		delete11.executeUpdate();
		t7.rollback();
		se.close();

	}

	
	 // Test de la m�thode listerStationHasStationByListStation OK
	 
	 
	public void testlisterCantonParam() throws SQLException{

		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction(); 
		
		Query delete=se.createSQLQuery("delete from canton");
		Query delete2=se.createSQLQuery("delete from station_has_station");
		Query delete3=se.createSQLQuery("delete from station");	
		Query delete4=se.createSQLQuery("delete from parametrehoraire");	
		Query delete5=se.createSQLQuery("delete from ligne");
		Query delete12=se.createSQLQuery("delete from reseau");	
		
		delete.executeUpdate();
		delete2.executeUpdate();
		delete3.executeUpdate();
		delete4.executeUpdate();
		delete5.executeUpdate();
		delete12.executeUpdate();
		t.commit();
		
		//cr�ation d'un reseau test
		Transaction t2 = se.beginTransaction();
		reseau.setNomReseau("reseau 1");
		se.save(reseau);		
	    t2.commit();
	    se.flush();
	    
		//cr�ation d'une ligne test
		Transaction t3 = se.beginTransaction();
		ligne.setNomLigne("Ligne 1");
		ligne.setCommentaire("Ligne 1");
		ligne.setReseau(reseau);
		se.save(ligne);		
	    t3.commit();
	    se.flush();
	    
		//cr�ation d'une Station test
		Transaction t4 = se.beginTransaction();
	    station.setNomStation("Station Alpha2");
	    station.setCommentaireStation("Station Alpha2");
	    station.setLatitude(55.5);
	    station.setLongitude(55.5);
	    station.setLigne(ligne);
		se.save(station);
		
		station2.setNomStation("Station Beta2");
		station2.setCommentaireStation("Station Beta2");
		station2.setLatitude(55.5);
		station2.setLongitude(55.5);
		station2.setLigne(ligne);
		se.save(station2);
	    t4.commit();
	    se.flush();
        
	    //listeStation.add(station);
	    //listeStation.add(station2);
	    
		Transaction t6 = se.beginTransaction();
	     canton.setDistance(200);
	     canton.setStation1(station);
	     canton.setStation2(station2);
	     
	     canton2.setDistance(230);
	     canton2.setStation1(station);
	     canton2.setStation2(station2);
	     
		se.save(canton);		
		se.save(canton2);
	    t6.commit();
	    se.flush();
		
		//R�cup�ration de la liste de ligne par la m�thode listerLigne
		listeCanton= canton_dao.listerCantonParam(listeStation);

		//V�rification du nombre de r�sultat (2)
		if (listeCanton.size()==2){
			//V�rification des donn�es r�cup�r�es
			if(listeCanton.get(0).getStation1().getNomStation().equals("Station Alpha2") && listeCanton.get(0).getStation1().getCommentaireStation().equals("Station Alpha2")
					&& listeCanton.get(0).getStation1().getLatitude() == 55.5 && listeCanton.get(0).getStation1().getLongitude() == 55.5
					&& listeCanton.get(1).getStation2().getNomStation().equals("Station Beta2") && listeCanton.get(1).getStation2().getCommentaireStation().equals("Station Beta2")
					&& listeCanton.get(1).getStation2().getLatitude() == 55.5 && listeCanton.get(1).getStation2().getLongitude() == 55.5){
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
		Query delete6=se.createSQLQuery("delete from canton");
		Query delete7=se.createSQLQuery("delete from station_has_station");
		Query delete8=se.createSQLQuery("delete from station");	
		Query delete9=se.createSQLQuery("delete from parametrehoraire");	
		Query delete10=se.createSQLQuery("delete from ligne");	
		Query delete11=se.createSQLQuery("delete from reseau");	
		delete6.executeUpdate();
		delete7.executeUpdate();
		delete8.executeUpdate();
		delete9.executeUpdate();
		delete10.executeUpdate();
		delete11.executeUpdate();
		t5.commit();
		se.close();

	}

	
}//fin  tucanton
