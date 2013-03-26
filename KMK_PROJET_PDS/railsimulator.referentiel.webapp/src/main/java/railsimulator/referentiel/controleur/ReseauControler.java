package railsimulator.referentiel.controleur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import dao.StationDAO;
import dao.ReseauDao;
import dao.ZoneDao;
import dao.GeolocalisationDao;
import dao.LieuDao;

import beans.Station;
import beans.Reseau;
import beans.Zone;
import beans.Geolocalisation;
import beans.Lieu;

public class ReseauControler extends HttpServlet {

	private List<Station> listeStation;
	private List<Reseau> listeReseau;
	private List<Zone> listeZone;
	private StationDAO station_dao = new StationDAO();
	private ReseauDao reseau_dao = new ReseauDao(); 
	private ZoneDao zone_dao = new ZoneDao();
	private GeolocalisationDao geolocalisation_dao = new GeolocalisationDao();
	private LieuDao lieu_dao = new LieuDao ();
	private Reseau reseau = new Reseau ();
	private Zone zone = new Zone();
	private Geolocalisation geolocalisation = new Geolocalisation() ;
	private Lieu lieu = new Lieu();





	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{


		String action = request.getParameter("action");


		if(action.equals("visualisationReseau")){

			listeStation= station_dao.listerStation();

			request.logout();
			request.setAttribute("listeStation",listeStation);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/visualisationReseau.jsp").forward( request, response );
		}



		/*	if(action.equals("creerReseau")){
			request.logout();
			this.getServletContext().getRequestDispatcher( "/WEB-INF/CreationReseau.jsp").forward( request, response );
		}
		 */


		if(action.equals("creerReseau")){
			listeReseau= reseau_dao.listerReseau();
			listeZone=zone_dao.listerZone();
			request.logout();
			request.setAttribute("listeReseau",listeReseau);
			request.setAttribute("listeZone",listeZone);
			
			this.getServletContext().getRequestDispatcher( "/WEB-INF/creeationReseau.jsp").forward( request, response );
		}

		if(action.equals("definirReseau")){
			request.logout();
			this.getServletContext().getRequestDispatcher( "/WEB-INF/geolocaliser.jsp").forward( request, response );
		}

		if(action.equals("test")){
			request.logout();
			this.getServletContext().getRequestDispatcher( "/WEB-INF/testGeo.jsp").forward( request, response );
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String action = request.getParameter("action");

		if(action.equals("AjoutZone")){


			// String latitude = request.getParameter("latitudeZone");


			int nbrha = Integer.parseInt(request.getParameter("NbrHabt"));
		
			int nbr = Integer.parseInt(request.getParameter("NbrMaxStation"));
		
			float surfac = Float.parseFloat(request.getParameter("Surface"));
			
			int  idreseau = Integer.parseInt(request.getParameter("idReseau"));
			
			
			reseau=reseau_dao.getReseauByID(idreseau);
			zone_dao.createZone(nbrha, nbr, surfac ,reseau);

			this.getServletContext().getRequestDispatcher("/WEB-INF/creeationReseau.jsp").forward( request, response );



		}
		if(action.equals("AjoutGeolocalisation")){
			

			Double latitude = Double.parseDouble(request.getParameter("latitudeZone"));
			Double longitude = Double.parseDouble(request.getParameter("longitudeZone"));
			Double latitudeb = Double.parseDouble(request.getParameter("latitudeZoneB"));
			Double longitudeb = Double.parseDouble(request.getParameter("longitudeZoneB"));
			
			int  idzone = Integer.parseInt(request.getParameter("idZone"));
			zone=zone_dao.getZoneByID(idzone);
			
			geolocalisation_dao.createGeolocalisation(latitude, longitude , zone);
			geolocalisation_dao.createGeolocalisation(latitudeb, longitudeb, zone);
			   

		}
				
		
		if(action.equals("AjoutLieu")){
			Double latitudelieu = Double.parseDouble(request.getParameter("latitudeLieu"));
			Double longitudelieu = Double.parseDouble(request.getParameter("longitudeLieu"));	
			String nomLieu = request.getParameter("nomLieu");
	     	String typeLieu=request.getParameter("typeLieu");
	     	int  idzone = Integer.parseInt(request.getParameter("idZone"));
			zone=zone_dao.getZoneByID(idzone);
		
           lieu_dao.createlieu(latitudelieu, longitudelieu, nomLieu, typeLieu, zone);
		}

		if(action.equals("CreerReseau")){
			String nomReseau = request.getParameter("reseau");	
			reseau_dao.createReseau(nomReseau);
			request.logout();
			this.getServletContext().getRequestDispatcher("/WEB-INF/creeationReseau.jsp").forward( request, response );

		}
	}

}
