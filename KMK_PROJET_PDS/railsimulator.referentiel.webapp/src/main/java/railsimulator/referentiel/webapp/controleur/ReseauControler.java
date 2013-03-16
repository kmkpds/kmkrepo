package railsimulator.referentiel.webapp.controleur;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StationDAO;


import beans.Station;

public class ReseauControler extends HttpServlet {

	private List<Station> listeStation;
	private StationDAO station_dao = new StationDAO();
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{


		String action = request.getParameter("action");
	
		if(action.equals("visualisationReseau")){
			
			listeStation= station_dao.listerStation();

			request.logout();
			request.setAttribute("listeStation",listeStation);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/visualisationReseau.jsp").forward( request, response );
		}

		
		
		
	}

}
