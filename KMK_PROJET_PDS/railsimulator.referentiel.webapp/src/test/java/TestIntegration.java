import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import railsimulator.tools.Algo;
import railsimulator.tools.AlgoCreationReseau;
import railsimulator.tools.AlgoDivTroncCanton;

import dao.CantonDAO;
import dao.GeolocalisationDAO;
import dao.HibernateUtils;
import dao.ReseauDAO;
import dao.StationDAO;
import dao.ZoneDAO;

import beans.Canton;
import beans.Ligne;
import beans.Reseau;
import beans.Station;
import beans.Zone;
import junit.framework.TestCase;

public class TestIntegration extends TestCase {

	Reseau reseau =new Reseau();
	ReseauDAO reseau_dao= new ReseauDAO();
	ZoneDAO zone_dao=new ZoneDAO();
	Set<Zone> zonelist=new HashSet<Zone>();
	Set<Ligne> lignelist=new HashSet<Ligne>();
	StationDAO station_dao=new StationDAO();
	CantonDAO canton_dao=new CantonDAO();
    Station station1 = new Station();
	Station station2 = new Station();
	Ligne ligne = new Ligne();
	Canton canton=new Canton();
	boolean test=false;

	public TestIntegration (){
	}
	
	public void testinter() {
		System.out.println("dans testintegration");
		Session se = null;
		se = HibernateUtils.getSession();

		Transaction t10 = se.beginTransaction();
		Query create10=se.createSQLQuery("delete from canton");
		create10.executeUpdate();
		t10.commit();
		
		Transaction t = se.beginTransaction();
		Query create=se.createSQLQuery("delete from station_has_station");
		create.executeUpdate();
		t.commit();

		
		Transaction t8 = se.beginTransaction();
		Query create8=se.createSQLQuery("delete from station");
		create8.executeUpdate();
		t8.commit();
		
		Transaction t9 = se.beginTransaction();
		Query create9=se.createSQLQuery("delete from parametrehoraire");
		create9.executeUpdate();
		t9.commit();
		

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
		
		Transaction t5 = se.beginTransaction();
		Query create5=se.createSQLQuery("delete from geolocalisation");
		create5.executeUpdate();
		t5.commit();
		
		Transaction t6 = se.beginTransaction();
		Query create6=se.createSQLQuery("delete from zone");
		create6.executeUpdate();
		t6.commit();
		
		Transaction t7 = se.beginTransaction();
		Query create7=se.createSQLQuery("delete from reseau");
		create7.executeUpdate();
		t7.commit();
		
		Transaction t21 = se.beginTransaction();
		Query create21 = se
				.createSQLQuery("insert into reseau (idreseau, nomreseau) values(1, 'reseau 1')");
		create.executeUpdate();
		t21.commit();
		
		Transaction t22 = se.beginTransaction();
		Query create22 = se
				.createSQLQuery("insert into zone (idzone,nombrehabitants,nombrestations,surface,reseau_idreseau) values(1,14567,3,11068700,1)");
		create.executeUpdate();
		t22.commit();
		
		se.flush();
		

		System.out.println("fin sup de la base purger");
		
	

		//ajout zone
		int nbrha =1000;
		int nbr = 3;
		float surfac=(float) 8247384.6139979595;
		//creer reseau
		//int  idreseau =reseau_dao.createReseauReturnId("reseau 1");
		List<Reseau> listeReseau = reseau_dao.listerReseau();
		//System.out.println("idreseau" +idreseau);
		Double latitude = 47.92278444035086;
		Double longitude =1.9217491149902344;
		Double latitudeb = 47.903109595807585;
		Double longitudeb = 1.8712806701660156;

		List<Zone>  listeZone = zone_dao.listerZone();
		reseau=reseau_dao.getReseauByID(1);
		//int idzone = zone_dao.createZonereturn(nbrha, nbr, surfac ,reseau);
		//System.out.println("idzone" +idzone);
		Zone zone = zone_dao.getZoneByID(1);
	
		GeolocalisationDAO geolocalisation_dao=new GeolocalisationDAO();
		geolocalisation_dao.createGeolocalisation(latitude, longitude , zone);
		geolocalisation_dao.createGeolocalisation(latitudeb, longitudeb, zone);
		listeZone=zone_dao.listerZone();
		listeReseau= reseau_dao.listerReseau();
		
		
		AlgoCreationReseau algo = new AlgoCreationReseau();
		Algo kruskal = new Algo();
		String[][] matriceStation = algo.creerReseau(reseau);
		kruskal.stationToStation(matriceStation);
		kruskal.dijkstra(matriceStation,reseau);
		
		int[] stationList =kruskal.getMatriceNomStation(matriceStation);
		System.out.println("taille int[] stationList " +stationList.length);

		int[][]  listeStationAffichage;
		try {
			listeStationAffichage = station_dao.listerStationHasStationByListStation(stationList);
	
			Transaction t24 = se.beginTransaction();
			ligne.setNomLigne("Ligne 1");
			ligne.setCommentaire("Ligne 1");
			ligne.setReseau(reseau);
			se.save(ligne);		
		    t24.commit();
		    se.flush();
		    
			//création d'une Station test
			Transaction t23 = se.beginTransaction();
		    station1.setNomStation("Station Alpha");
		    station1.setCommentaireStation("Station Alpha");
		    station1.setLatitude(55.5);
		    station1.setLongitude(55.5);
		     station1.setLigne(ligne);
			se.save(station1);
			
			station2.setNomStation("Station Beta");
			station2.setCommentaireStation("Station Beta");
			station2.setLatitude(55.5);
			station2.setLongitude(55.5);
		     station2.setLigne(ligne);
			se.save(station2);
		    t23.commit();
		    se.flush();
		    
			Transaction t25 = se.beginTransaction();
		     canton.setDistance(200);
		     canton.setStation1(station1);
		     canton.setStation2(station2);	     
			se.save(canton);	
		    t25.commit();
		    se.flush();
		    
		List<Station> listeStation = new ArrayList<Station>();
		    listeStation.add(station1);
		    listeStation.add(station2);
		listeStation = station_dao.listerStation();

				
		AlgoDivTroncCanton algodiv=new AlgoDivTroncCanton();

		algodiv.decoupage(listeStationAffichage,stationList);

		List<Canton> listeCanton = canton_dao.listerCantonParam(listeStationAffichage);
		
		int idreseau=1;
		int idzone=1;
		String Nomstation=listeStation.get(0).getNomStation();
		double distanceCanton=listeCanton.get(0).getDistance();
	
		
//		listeReseau.get(idreseau);
//		listeZone.get(idzone);
//		listeStation.get(idstation);
//		listeCanton.get(idcanton);
		
		if((listeReseau!=null) && (listeZone!=null) && (listeCanton!=null) && (listeStation!=null)){
			if((idreseau==1) && (idzone==1) &&(Nomstation.equals("Station Alpha")) && (distanceCanton==200)){
				test=true;
			}
			else {
				test=false;
			}
		}//fin if !=null
		else{
			test=false;
		}
		
		System.out.println("boolean => " +test);
		assertTrue(test);
	

		Transaction t11 = se.beginTransaction();
		Query create11=se.createSQLQuery("delete from canton");
		create11.executeUpdate();
		t11.commit();
		
		Transaction t12 = se.beginTransaction();
		Query create12=se.createSQLQuery("delete from station_has_station");
		create12.executeUpdate();
		t12.commit();

		
		Transaction t13 = se.beginTransaction();
		Query create13=se.createSQLQuery("delete from station");
		create13.executeUpdate();
		t13.commit();
		
		Transaction t14 = se.beginTransaction();
		Query create14=se.createSQLQuery("delete from parametrehoraire");
		create14.executeUpdate();
		t14.commit();
		

		Transaction t15 = se.beginTransaction();
		Query create15=se.createSQLQuery("delete from ligne");
		create15.executeUpdate();
		t15.commit();
		
		Transaction t16 = se.beginTransaction();
		Query create16=se.createSQLQuery("delete from lieu");
		create16.executeUpdate();
		t16.commit();
		
		Transaction t17 = se.beginTransaction();
		Query create17=se.createSQLQuery("delete from geolocalisation");
		create17.executeUpdate();
		t17.commit();
		
		Transaction t18 = se.beginTransaction();
		Query create18=se.createSQLQuery("delete from geolocalisation");
		create18.executeUpdate();
		t18.commit();
		
		Transaction t19 = se.beginTransaction();
		Query create19=se.createSQLQuery("delete from zone");
		create19.executeUpdate();
		t19.commit();
		
		Transaction t20 = se.beginTransaction();
		Query create20=se.createSQLQuery("delete from reseau");
		create20.executeUpdate();
		t20.commit();
		
		se.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
