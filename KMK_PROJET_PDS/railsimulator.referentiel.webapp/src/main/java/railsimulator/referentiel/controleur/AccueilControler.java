package railsimulator.referentiel.controleur;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import railsimulator.tools.Algo;

public class AccueilControler extends HttpServlet {

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{


		String action = request.getParameter("action");
	
		if(action.equals("creationReseau")){
			request.logout();
			this.getServletContext().getRequestDispatcher( "/WEB-INF/accueilCreationReseau.jsp").forward( request, response );
		}
		
		if(action.equals("ref")){
			//purger l'objet request
			request.logout();
			this.getServletContext().getRequestDispatcher("/WEB-INF/accueilRef.jsp").forward( request, response );
		}
        


		if(action.equals("index")){
			request.logout();
			this.getServletContext().getRequestDispatcher( "/WEB-INF/accueil.jsp").forward( request, response );
		}
		
	}

}
