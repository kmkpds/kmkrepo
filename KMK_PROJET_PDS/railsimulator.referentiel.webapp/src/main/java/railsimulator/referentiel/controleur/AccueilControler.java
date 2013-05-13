package railsimulator.referentiel.controleur;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AccueilControler extends HttpServlet {

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{


		String action = request.getParameter("action");
	
		if(action.equals("creationReseau")){
			request.logout();
			this.getServletContext().getRequestDispatcher( "/WEB-INF/accueilCreationReseau.jsp").forward( request, response );
		}
		
	if(action.equals("bi")){
			request.logout();
			this.getServletContext().getRequestDispatcher( "/WEB-INF/accueilBi.jsp").forward( request, response );
		}
		
	if(action.equals("workflow")){
		request.logout();
		this.getServletContext().getRequestDispatcher( "/WEB-INF/accueilWorkflow.jsp").forward( request, response );
	}
	
	if(action.equals("creationHoraire")){
			request.logout();
			this.getServletContext().getRequestDispatcher( "/WEB-INF/accueilCreationHoraire.jsp").forward( request, response );
		}
	
	if(action.equals("index")){
		request.logout();
		this.getServletContext().getRequestDispatcher( "/accueil.jsp").forward( request, response );
	}
		
	}

}
