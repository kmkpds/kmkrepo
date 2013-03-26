package railsimulator.visualisation.controleur;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StationDAO;

import beans.Station;


public class VisualisationControler extends HttpServlet {
	
	private List<Station> listeStation;
	private StationDAO stationDao;
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		String action = request.getParameter("action");
		

		if(action.equals("visualiserTrafic")){
			
			listeStation= stationDao.listerStation();
			request.logout();
			request.setAttribute("listeStation",listeStation);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/visualisationTrafic.jsp");
			
		}
		
	}	
	
	private void visualiserTrafic( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		
		listeStation= stationDao.listerStation();
		request.logout();
		request.setAttribute("listeStation",listeStation);
		this.getServletContext().getRequestDispatcher( "/WEB-INF/visualisationTrafic.jsp");
	}
	
	

}
