package railsimulator.visualisation.controleur;

import java.io.IOException; 
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Station;
import dao.StationDAO;


public class VisualisationControler extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private List<Station> listeStation;
	private StationDAO station_dao = new StationDAO();

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		String action = request.getParameter("action");
		
		if(action.equals("visualiserTrafic")){
			
			listeStation = station_dao.listerStation();

			request.logout();
			request.setAttribute("listeStation",listeStation);

			//this.getServletContext().getRequestDispatcher("/WEB-INF/visualisationTrafic.jsp");
			this.getServletContext().getRequestDispatcher( "/WEB-INF/testMxGraph.html");
		}
		
	}	
	
	private void visualiserTrafic( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		
	    listeStation = station_dao.listerStation();

		request.logout();
		request.setAttribute("listeStation",listeStation);

		this.getServletContext().getRequestDispatcher( "/WEB-INF/visualisationTrafic.jsp");
		//this.getServletContext().getRequestDispatcher( "/WEB-INF/testMxGraph.html");
	}
	
	

}
