package railsimulator.referentiel.controleur;

import java.io.IOException;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import dao.HibernateUtils;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import railsimulator.tools.Algo;

import dao.LigneDAO;
import dao.ReseauDAO;
import dao.ReseauDAO;
import dao.StationDAO;


import beans.Station;

public class ReseauControler extends HttpServlet {

	private List<Station> listeStation;
	private StationDAO station_dao = new StationDAO();
	private ReseauDAO reseau_dao = new ReseauDAO();
	private LigneDAO ligne_dao = new LigneDAO();
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{


		String action = request.getParameter("action");
	
		if(action.equals("visualisationReseau")){
			
			listeStation= station_dao.listerStation();

			request.logout();
			request.setAttribute("listeStation",listeStation);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/visualisationReseau.jsp").forward( request, response );
		}

		
		if(action.equals("algo")){
			
			 String reseauMatrice[][] = { {"1","2","4","5","6","7"},
						{"0","652","1739","2499","0","0"},
						
						{"652","0","1088","1855","0","0"},
						
						{"1739","1088","0","868","856","2280"},
						
						{"2499","1855","868","0","1181","2130"},
						
						{"0","0","856","1181","0","1485"},
						
						{"0","0","2280","2130","1485","0"},

		        };
			
			Algo algo = new Algo();
		     
			algo.stationToStation(reseauMatrice);	
			
			listeStation= station_dao.listerStation();

			request.logout();
			request.setAttribute("listeStation",listeStation);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/visualisationReseau.jsp").forward( request, response );
		}
		
		if(action.equals("station")){
			
			
			Session se = null;
			se = HibernateUtils.getSession();
			Transaction t = se.beginTransaction();
			Query create=se.createSQLQuery("insert into reseau (idreseau, nomreseau) values(1, 'reseau 1')");
			create.executeUpdate();
			t.commit();
			
			Transaction t2 = se.beginTransaction();
			Query create2=se.createSQLQuery("insert into ligne (idligne, nomligne, commentaire, reseau_idreseau) values(1, 'ligne 1','ligne 1',1)");
			create2.executeUpdate();
			t2.commit();
			
			Transaction t3 = se.beginTransaction();
			Query create3=se.createSQLQuery("INSERT INTO station (idstation, nomstation, commentaire, latitude, longitude, ligne_idligne) VALUES (1, 'station 1', 'station 1', 47.9015298444342, 1.8995111649564933, 1),(2, 'station 2', 'station 2', 47.90406160754688, 1.907407588296337, 1),(4, 'station 4', 'station 4', 47.90918229530656, 1.9198530381254386, 1),(5, 'station 5', 'station 5', 47.90837682743897, 1.9314401810697746, 1),(6, 'station 6', 'station 6', 47.916776090342864, 1.921741313271923, 1),(7, 'station 7', 'station 7', 47.927474546482074, 1.9336717789701652, 1)");
			create3.executeUpdate();
			t3.commit();
			
			
			se.close();
			
			
			listeStation= station_dao.listerStation();

			request.logout();
			request.setAttribute("listeStation",listeStation);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/visualisationReseau.jsp").forward( request, response );
			 			
		}
		
		if(action.equals("purger")){
			
			Session se = null;
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
			
			
			se.close();
			
			
			
			 			
		}
		
		
	}

}
