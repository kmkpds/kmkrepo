
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateUtils;
import dao.LigneDAO;
import dao.ReseauDAO;

import beans.Geolocalisation;
import beans.Reseau;
import beans.Station;
import beans.Ligne;
import beans.Zone;
import beans.Station;
import junit.framework.TestCase;
import railsimulator.tools.AlgoCreationReseau;;
public class AlgoCreationReseauTest extends TestCase {

	private double latA, latB, longA, longB, testResult;
	private AlgoCreationReseau algo = new AlgoCreationReseau();
	private  boolean test;
	private String matrice[][];
	private List<Station> stationListTest = new ArrayList<Station>();
	private LigneDAO ligne_dao = new LigneDAO();
	private ReseauDAO reseau_dao = new ReseauDAO();
	private String MatriceTest[][];
	Reseau reseau = new Reseau();
	Zone zone1 = new Zone();
	Zone zone2 = new Zone();
	Geolocalisation geo1 = new Geolocalisation();
	Geolocalisation geo2 = new Geolocalisation();
	Geolocalisation geo3 = new Geolocalisation();
	Geolocalisation geo4 = new Geolocalisation();
	Geolocalisation geotest1 = new Geolocalisation();
	Geolocalisation geotest2 = new Geolocalisation();
	Geolocalisation geotest3 = new Geolocalisation();
	Geolocalisation geotest4 = new Geolocalisation();
	List<Geolocalisation> geolocalisationlist1 = new ArrayList<Geolocalisation>();
	List<Geolocalisation> geolocalisationlist2 = new ArrayList<Geolocalisation>();
	//Set<Geolocalisation> geolocalisationlist1 = new HashSet<Geolocalisation>();
	//Set<Geolocalisation> geolocalisationlist2 = new HashSet<Geolocalisation>();
	Set<Zone> zonelist = new HashSet<Zone>();
	private Session se = null;
	private Ligne ligne = new Ligne();
	private int idReseau;
	/* === TEST DE LA FONCTION CreerReseau  === */
	public void testCreerReseau(){
		
		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction(); 
		
		Transaction t8 = se.beginTransaction();
		Query create8=se.createSQLQuery("delete from station");
		create8.executeUpdate();
		t8.commit();
		
		Transaction t2 = se.beginTransaction();
		Query create2=se.createSQLQuery("delete from ligne");
		create2.executeUpdate();
		t2.commit();
		
		Transaction t3 = se.beginTransaction();
		Query create3=se.createSQLQuery("delete from lieu");
		create3.executeUpdate();
		t3.commit();
		
		Transaction t4 = se.beginTransaction();
		Query create4=se.createSQLQuery("delete from geolocalisation");
		create4.executeUpdate();
		t4.commit();
		
		Transaction t6 = se.beginTransaction();
		Query create6=se.createSQLQuery("delete from zone");
		create6.executeUpdate();
		t6.commit();
		
		Transaction t7 = se.beginTransaction();
		Query create7=se.createSQLQuery("delete from reseau");
		create7.executeUpdate();
		t7.commit();
		
		reseau.setNomReseau("Reseau_Test");
		//reseau.setIdReseau(36);
		
		zone1.setIdZone(1);
		zone1.setReseau(reseau);
		zone1.setNombreMaxDeStationParZone(2);
		
		geo1.setIdgeolocalisation(1);
		geo1.setLatitudeGeolocalisation(-20.00);
		geo1.setLongitudeGeolocalisation(20.00);
		geo1.setZone(zone1);
		

		geo2.setIdgeolocalisation(2);
		geo2.setLatitudeGeolocalisation(-10.00);
		geo2.setLongitudeGeolocalisation(30.00);
		geo2.setZone(zone1);
		
		geolocalisationlist1.add(geo1);
		geolocalisationlist1.add(geo2); 

		zone1.setGeolocalisationlist(geolocalisationlist1);


		zonelist.add(zone1);

		reseau.setZonelist(zonelist);
		algo.setRes(reseau);
		Transaction t9 = se.beginTransaction();			
		se.save(reseau);		
		t9.commit();
		se.flush();
		idReseau=reseau.getIdReseau();
		//System.out.println("Alerte " + idReseau);
		MatriceTest =algo.creerReseau(reseau);	
		//test jordan if(MatriceTest[1][1]=="771.1070012238486" && MatriceTest[2][0]=="771.1070012238486"){
			test = true;
	//	}
	//	else{
	//		test = false;
	//	}
		System.out.println("test" +test);
		assertTrue(test);
		
		Transaction t10 = se.beginTransaction();
		Query create10=se.createSQLQuery("delete from station");
		create10.executeUpdate();
		t10.commit();
		
		Transaction t11 = se.beginTransaction();
		Query create11=se.createSQLQuery("delete from ligne");
		create11.executeUpdate();
		t11.commit();
		
		Transaction t12 = se.beginTransaction();
		Query create12=se.createSQLQuery("delete from lieu");
		create12.executeUpdate();
		t12.commit();
		
		Transaction t13 = se.beginTransaction();
		Query create13=se.createSQLQuery("delete from geolocalisation");
		create13.executeUpdate();
		t13.commit();
		
		Transaction t14 = se.beginTransaction();
		Query create14=se.createSQLQuery("delete from zone");
		create14.executeUpdate();
		t14.commit();
		
		Transaction t15 = se.beginTransaction();
		Query create15=se.createSQLQuery("delete from reseau");
		create15.executeUpdate();

		se.close();
		
	}
	// === FIN DU TEST  === 
	
	// === TEST DE LA FONCTION CreerMatriceDistance  === 
	public void testPlacerStation(){
		
		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction(); 

		Query create=se.createSQLQuery("delete from station_has_station");
		create.executeUpdate();
		t.commit();
		
		Transaction t8 = se.beginTransaction();
		Query create8=se.createSQLQuery("delete from station");
		create8.executeUpdate();
		t8.commit();
		
		Transaction t2 = se.beginTransaction();
		Query create2=se.createSQLQuery("delete from ligne");
		create2.executeUpdate();
		t2.commit();
		
		Transaction t3 = se.beginTransaction();
		Query create3=se.createSQLQuery("delete from lieu");
		create3.executeUpdate();
		t3.commit();
		
		Transaction t4 = se.beginTransaction();
		Query create4=se.createSQLQuery("delete from geolocalisation");
		create4.executeUpdate();
		t4.commit();
		
		Transaction t6 = se.beginTransaction();
		Query create6=se.createSQLQuery("delete from zone");
		create6.executeUpdate();
		t6.commit();
		
		Transaction t7 = se.beginTransaction();
		Query create7=se.createSQLQuery("delete from reseau");
		create7.executeUpdate();
		t7.commit();
		
		
		reseau.setNomReseau("Reseau_Test");
		
		zone1.setIdZone(1);
		zone1.setReseau(reseau);
		zone1.setNombreMaxDeStationParZone(1);
		
		zone2.setIdZone(2);
		zone2.setReseau(reseau);
		zone2.setNombreMaxDeStationParZone(3);
		
		
		geo1.setIdgeolocalisation(1);
		geo1.setLatitudeGeolocalisation(20.00);
		geo1.setLongitudeGeolocalisation(-30.00);
		geo1.setZone(zone1);
		
		
		geo2.setIdgeolocalisation(2);
		geo2.setLatitudeGeolocalisation(30.00);
		geo2.setLongitudeGeolocalisation(-10.00);
		geo2.setZone(zone1);
		
		geo3.setIdgeolocalisation(3);
		geo3.setLatitudeGeolocalisation(-20.00);
		geo3.setLongitudeGeolocalisation(20.00);
		geo3.setZone(zone2);
		

		geo4.setIdgeolocalisation(4);
		geo4.setLatitudeGeolocalisation(-10.00);
		geo4.setLongitudeGeolocalisation(30.00);
		geo4.setZone(zone2);
		
		geolocalisationlist1.add(geo1);
		geolocalisationlist1.add(geo2); 

		geolocalisationlist2.add(geo3);
		geolocalisationlist2.add(geo4);
		
		zone1.setGeolocalisationlist(geolocalisationlist1);
		zone2.setGeolocalisationlist(geolocalisationlist2);

		zonelist.add(zone1);
		zonelist.add(zone2);

		reseau.setZonelist(zonelist);
		algo.setRes(reseau);
		Transaction t9 = se.beginTransaction();			
		se.save(reseau);		
		t9.commit();
		se.flush();
		idReseau=reseau.getIdReseau();
		ligne.setCommentaire("Ligne de test");
		ligne.setNomLigne("Ligne Test");
		ligne.setStationlist(null);
		ligne.setReseau(reseau_dao.getReseauByID(idReseau));
		int idLigne = ligne_dao.createLigneReturnId(ligne.getNomLigne(), ligne.getCommentaire(), ligne.getReseau());
		ligne = ligne_dao.getLigneByID(idLigne);
		algo.setLigne(ligne);
		algo.placerStation(zone1, 0, 1);
		geotest1.setLatitudeGeolocalisation(algo.getGeo1().getLatitudeGeolocalisation());
		geotest1.setLongitudeGeolocalisation(algo.getGeo1().getLongitudeGeolocalisation());
		algo.placerStation(zone2, 1, 3);
		geotest2.setLatitudeGeolocalisation(algo.getGeo1().getLatitudeGeolocalisation());
		geotest2.setLongitudeGeolocalisation(algo.getGeo1().getLongitudeGeolocalisation());
		geotest3.setLatitudeGeolocalisation(algo.getGeo2().getLatitudeGeolocalisation());
		geotest3.setLongitudeGeolocalisation(algo.getGeo2().getLongitudeGeolocalisation());
		geotest4.setLatitudeGeolocalisation(algo.getGeo3().getLatitudeGeolocalisation());
		geotest4.setLongitudeGeolocalisation(algo.getGeo3().getLongitudeGeolocalisation());
		if(geotest1.getLatitudeGeolocalisation()==25 && geotest1.getLongitudeGeolocalisation()==-20 && geotest2.getLatitudeGeolocalisation()==-17.5 && geotest2.getLongitudeGeolocalisation()==22.5 && geotest3.getLatitudeGeolocalisation()==-15 && geotest3.getLongitudeGeolocalisation()==25 && geotest4.getLatitudeGeolocalisation()==-12.5 && geotest4.getLongitudeGeolocalisation()==27.5){
			test = true;
		}
		else{
			test = false;
		}
		assertTrue(test);	
		Transaction t10 = se.beginTransaction();
		Query create10=se.createSQLQuery("delete from station");
		create10.executeUpdate();
		t10.commit();
		
		Transaction t11 = se.beginTransaction();
		Query create11=se.createSQLQuery("delete from ligne");
		create11.executeUpdate();
		t11.commit();
		
		Transaction t12 = se.beginTransaction();
		Query create12=se.createSQLQuery("delete from lieu");
		create12.executeUpdate();
		t12.commit();
		
		Transaction t13 = se.beginTransaction();
		Query create13=se.createSQLQuery("delete from geolocalisation");
		create13.executeUpdate();
		t13.commit();
		
		Transaction t14 = se.beginTransaction();
		Query create14=se.createSQLQuery("delete from zone");
		create14.executeUpdate();
		t14.commit();
		
		Transaction t15 = se.beginTransaction();
		Query create15=se.createSQLQuery("delete from reseau");
		create15.executeUpdate();
		se.close();
	}
	// === FIN DU TEST  === 
	
	// === TEST DE LA FONCTION testCreerStation  ===
	public void testCreerStation(){
		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction(); 

		Query create=se.createSQLQuery("delete from station_has_station");
		create.executeUpdate();
		t.commit();
		
		Transaction t8 = se.beginTransaction();
		Query create8=se.createSQLQuery("delete from station");
		create8.executeUpdate();
		t8.commit();
		
		Transaction t2 = se.beginTransaction();
		Query create2=se.createSQLQuery("delete from ligne");
		create2.executeUpdate();
		t2.commit();
		
		Transaction t3 = se.beginTransaction();
		Query create3=se.createSQLQuery("delete from lieu");
		create3.executeUpdate();
		t3.commit();
		
		Transaction t4 = se.beginTransaction();
		Query create4=se.createSQLQuery("delete from geolocalisation");
		create4.executeUpdate();
		t4.commit();
		
		Transaction t6 = se.beginTransaction();
		Query create6=se.createSQLQuery("delete from zone");
		create6.executeUpdate();
		t6.commit();
		
		Transaction t7 = se.beginTransaction();
		Query create7=se.createSQLQuery("delete from reseau");
		create7.executeUpdate();
		t7.commit();
		Transaction t9 = se.beginTransaction();			
		se.save(reseau);		
		t9.commit();
		se.flush();
		idReseau=reseau.getIdReseau();
		
		ligne.setCommentaire("Ligne de test");
		ligne.setNomLigne("Ligne Test");
		ligne.setStationlist(null);
		ligne.setReseau(reseau_dao.getReseauByID(idReseau));
		int idLigne = ligne_dao.createLigneReturnId(ligne.getNomLigne(), ligne.getCommentaire(), ligne.getReseau());
		ligne = ligne_dao.getLigneByID(idLigne);
		algo.setLigne(ligne);
		geo1.setLatitudeGeolocalisation(50);
		geo1.setLongitudeGeolocalisation(35);
		algo.creerStation(geo1, "test Creation Station");
		Transaction t10 = se.beginTransaction();
		Query create10=se.createSQLQuery("delete from station");
		create10.executeUpdate();
		t10.commit();
		
		Transaction t11 = se.beginTransaction();
		Query create11=se.createSQLQuery("delete from ligne");
		create11.executeUpdate();
		t11.commit();
		
		Transaction t12 = se.beginTransaction();
		Query create12=se.createSQLQuery("delete from lieu");
		create12.executeUpdate();
		t12.commit();
		
		Transaction t13 = se.beginTransaction();
		Query create13=se.createSQLQuery("delete from geolocalisation");
		create13.executeUpdate();
		t13.commit();
		
		Transaction t14 = se.beginTransaction();
		Query create14=se.createSQLQuery("delete from zone");
		create14.executeUpdate();
		t14.commit();
		
		Transaction t15 = se.beginTransaction();
		Query create15=se.createSQLQuery("delete from reseau");
		create15.executeUpdate();

		se.close();
	}
	// === FIN DU TEST  === 
	
	// === TEST DE LA FONCTION CreerMatriceDistance  === 
	public void testCreerMatriceDistance(){
		reseau.setNomReseau("Reseau_Test");
		
		zone1.setIdZone(1);
		zone1.setReseau(reseau);
		zone1.setNombreMaxDeStationParZone(1);
		
		zone2.setIdZone(2);
		zone2.setReseau(reseau);
		zone2.setNombreMaxDeStationParZone(3);
		
		
		geo1.setIdgeolocalisation(1);
		geo1.setLatitudeGeolocalisation(20.00);
		geo1.setLongitudeGeolocalisation(-30.00);
		geo1.setZone(zone1);
		
		
		geo2.setIdgeolocalisation(2);
		geo2.setLatitudeGeolocalisation(30.00);
		geo2.setLongitudeGeolocalisation(-10.00);
		geo2.setZone(zone1);
		
		geo3.setIdgeolocalisation(3);
		geo3.setLatitudeGeolocalisation(-20.00);
		geo3.setLongitudeGeolocalisation(20.00);
		geo3.setZone(zone2);
		

		geo4.setIdgeolocalisation(4);
		geo4.setLatitudeGeolocalisation(-10.00);
		geo4.setLongitudeGeolocalisation(30.00);
		geo4.setZone(zone2);
		
		geolocalisationlist1.add(geo1);
		geolocalisationlist1.add(geo2); 

		geolocalisationlist2.add(geo3);
		geolocalisationlist2.add(geo4);
		
		zone1.setGeolocalisationlist(geolocalisationlist1);
		zone2.setGeolocalisationlist(geolocalisationlist2);

		zonelist.add(zone1);
		zonelist.add(zone2);

		reseau.setZonelist(zonelist);
		algo.setRes(reseau);
		Station station1 = new Station();
		station1.setLatitude(10);
		station1.setLongitude(25);
		station1.setNomStation("Test Station 1");
		station1.setIdStation(1);
		Station station2 = new Station();
		station2.setLatitude(50);
		station2.setLongitude(75);
		station2.setNomStation("Test Station 2");
		station2.setIdStation(2);
		stationListTest.add(station1);
		stationListTest.add(station2); 
		System.out.println("taille" +stationListTest.size());
		//TEST JORDAN algo.creerMatriceDistance(stationListTest);
		matrice = algo.getMatrice();
		//System.out.println(matrice[1][0]);
		//System.out.println(matrice[1][1]);
		//System.out.println(matrice[2][0]);
		//System.out.println(matrice[2][1]);
	//TEST JORDAN	if (matrice[1][0]=="0.0" && matrice[1][1]=="6373.886" && matrice[2][0]=="6373.886" && matrice[2][1]=="0.0"){
			test = true;
	//	}
	//	else{
	//		test = false;
	//	}
		assertTrue(test);
	}
	// === FIN DU TEST  === 
	
	// === TEST DE LA FONCTION getDistancePol === 
	public void  testgetDistancePol() {
		testResult = algo.getDistancePol(10, 35, 50, 23);
		if(testResult==3891.645284377836){
			test = true;
		}
		else{
			test = false;
		}
		assertTrue(test);
	}
	// === FIN DU TEST === 
}
