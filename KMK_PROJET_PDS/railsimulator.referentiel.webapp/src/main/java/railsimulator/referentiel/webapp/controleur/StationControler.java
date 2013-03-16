package railsimulator.referentiel.webapp.controleur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Canton;
import beans.Ligne;
import beans.Station;
import dao.CantonDAO;
import dao.LigneDAO;
import dao.StationDAO;

public class StationControler extends HttpServlet {

	private List<Canton> listeCanton;
	private CantonDAO canton_dao = new CantonDAO();
	private StationDAO station_dao = new StationDAO();
	private List<Ligne> listeLigne;
	private LigneDAO ligne_dao = new LigneDAO();
	private Canton canton = new Canton();
	private Ligne ligne = new Ligne();
	private Station station = new Station();



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");

		if(action.equals("Annuler")){



			listeLigne = ligne_dao.listerLigne();
			request.logout();
			request.setAttribute("listeLigne",listeLigne);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/accueilRefStation.jsp").forward( request, response );
		}
		
		// Form valider		
		if(action.equals("Valider")){


			boolean erreur[] = new boolean[2];
			boolean erreurGenerale = false;


			String nomStation = request.getParameter("nomStation");
			String commentaireStation = request.getParameter("commentaireStation");

			String idCanton[] = request.getParameterValues("canton"); 






			if ( nomStation.trim().isEmpty()) {

				erreurGenerale=true;
				erreur[0]=true;

			}
			if ( commentaireStation.trim().isEmpty()) {

				erreurGenerale=true;
				erreur[1]=true;

			}


			if (erreurGenerale == false) {




				station_dao.createStation(nomStation, commentaireStation); 
				listeLigne= ligne_dao.listerLigne();

				request.logout();
				request.setAttribute("listeLigne",listeLigne);
				this.getServletContext().getRequestDispatcher( "/WEB-INF/accueilRefStation.jsp").forward( request, response );	        	
			}
			else{



				listeLigne= ligne_dao.listerLigne();

				request.logout();
				request.setAttribute("listeLigne",listeLigne);
				request.setAttribute( "erreurGénérale", erreurGenerale );
				request.setAttribute( "erreur", erreur);
				this.getServletContext().getRequestDispatcher( "/WEB-INF/formRefStation.jsp").forward( request, response );

			}
		}
		
		// Form Modifier		
		if(action.equals("Update")){
			
		
            boolean erreur[] = new boolean[3];
            boolean erreurGenerale = false;
            int idStation = Integer.parseInt(request.getParameter("idStation"));
			String nomStation = request.getParameter("nomStation");
	        String commentaireStation = request.getParameter("commentaireStation");	    
	        
	        
	        if ( nomStation.trim().isEmpty()) {
	        	 erreurGenerale=true;
	        	 erreur[0]=true;
	        }
	        if ( commentaireStation.trim().isEmpty()) {
	        	 erreurGenerale=true;
	        	 erreur[1]=true;
	        }
	     
	        if (erreurGenerale == false) {
	        	
	        	
				station.setIdStation(idStation);
				station.setNomStation(nomStation);
				station.setCommentaireStation(commentaireStation);
				
				station_dao.modifierStation(station); 
	        	listeLigne= ligne_dao.listerLigne();        
				request.logout();
				request.setAttribute("listeLigne",listeLigne);
				this.getServletContext().getRequestDispatcher( "/WEB-INF/accueilRefStation.jsp").forward( request, response );	        	
	        }
	        else{
	        	station.setIdStation(idStation);
				station.setNomStation(nomStation);
				station.setCommentaireStation(commentaireStation);
				
				
	        	request.logout();
	        	request.setAttribute( "erreur", erreur );
	        	request.setAttribute( "erreurGénérale", erreurGenerale );
	        	request.setAttribute( "station", station );
	            this.getServletContext().getRequestDispatcher( "/WEB-INF/formModifRefStation.jsp" ).forward( request, response );
	        	
	        }
		}
		
		
		
	}





	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

		String action = request.getParameter("action");
		System.out.println(action);

		if(action.equals("refStation")){



			listeLigne = ligne_dao.listerLigne();
			request.logout();
			request.setAttribute("listeLigne",listeLigne);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/accueilRefStation.jsp").forward( request, response );
		}
		


		// affichage Form ajouter		
		if(action.equals("formAjouter")){

			

			request.logout();
			this.getServletContext().getRequestDispatcher( "/WEB-INF/formRefStation.jsp").forward( request, response );
		}

		if(action.equals("supp")){
          
			int  id = Integer.parseInt(request.getParameter("id"));
			
			station_dao.supprimerStation(id);
			
			listeLigne = ligne_dao.listerLigne();
			
			request.logout();
			request.setAttribute("listeLigne",listeLigne);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/accueilRefStation.jsp").forward( request, response );
		}
		
		//  affichage Form modifier
		if(action.equals("modif")){
			
			int  id = Integer.parseInt(request.getParameter("id"));
	
			station=station_dao.getStationByID(id);
			
			request.logout();
			request.setAttribute("station",station);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/formModifRefStation.jsp").forward( request, response );
		}
	}
}
