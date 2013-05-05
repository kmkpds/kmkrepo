package railsimulator.visualisation;


import java.io.IOException; 
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.TypeIncidentDAO;
import beans.TypeIncident;


public class VisuIncidentsControleur extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private List<TypeIncident> listeTypeIncident;
	private TypeIncidentDAO typeIncident_dao = new TypeIncidentDAO();

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		System.out.println("avant");
		String action = request.getParameter("action");
		
		if(action.equals("visualiser")){
			System.out.println("debut action v");
			listeTypeIncident = typeIncident_dao.listerTypeIncident();
			afficherRefEtape(request, response);	
			System.out.println("Fin action");
		}
		
	}	
	
	
	
	
	//affichage des etapes dans la page listeEtape.jsp
public void afficherRefEtape( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	
	listeTypeIncident = typeIncident_dao.listerTypeIncident();

	request.setAttribute("listeTypeIncident",listeTypeIncident);
	this.getServletContext().getRequestDispatcher( "/WEB-INF/visualiser.jsp").forward( request, response );
			
}
	

}
