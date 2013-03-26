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
		
		if(action.equals("main")){
			
			 String reseauMatrice[][] = { {"1","2","3","4","5","6","7"},
					{"0","652","300","1739","2499","0","0"},
					
					{"652","0","719","1088","1855","0","0"},
					
					{"300","719","0","1579","2438","1974","3388"}, 
					
					{"1739","1088","1579","0","868","856","2280"},
					
					{"2499","1855","2438","868","0","1181","2130"},
					
					{"0","0","1974","856","1181","0","1485"},
					
					{"0","0","3388","2280","2130","1485","0"},

	        };			
			
			railsimulator.tools.Algo algo = new Algo();
		     
			int a = algo.kruskal(reseauMatrice);
			

			
		}
		
	}

}
