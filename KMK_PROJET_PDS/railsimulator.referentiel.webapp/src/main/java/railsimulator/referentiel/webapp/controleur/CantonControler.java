package railsimulator.referentiel.webapp.controleur;

import java.io.IOException;
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


import javax.servlet.http.HttpServlet;

public class CantonControler extends HttpServlet {
	
	private List<Canton> listeCanton;
	private List<Station> listeStation;
	private CantonDAO canton_dao = new CantonDAO();
	private StationDAO station_dao = new StationDAO();
	private List<Ligne> listeLigne;
	private LigneDAO ligne_dao = new LigneDAO();
	private Canton canton = new Canton();
	private Station station = new Station();
	private Ligne ligne = new Ligne();

	
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		
		
		if(action.equals("Annuler")){



			listeCanton = canton_dao.listerCanton();
			request.logout();
			request.setAttribute("listeCanton",listeCanton);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/accueilRefCanton.jsp").forward( request, response );
		}
		
		
		
		// Form valider		
		if(action.equals("Valider")){
			
			
            boolean erreur[] = new boolean[2];
            boolean erreurGenerale = false;
         
			String nomCanton = request.getParameter("nomCanton");
	        String commentaireCanton = request.getParameter("commentaireCanton");
	     
	        int  id = Integer.parseInt(request.getParameter("idLigne"));
	        int  idStation = Integer.parseInt(request.getParameter("idStation"));
	       
	        
	     
	        
	        if ( nomCanton.trim().isEmpty()) {
	        	 erreurGenerale=true;
	        	 erreur[0]=true;
	            
	        }
	        if ( commentaireCanton.trim().isEmpty()) {
	        	 erreurGenerale=true;
	        	 erreur[1]=true;
	        }
	           
	     
	        if (erreurGenerale == false) {
	        	
	        	
	        	ligne=ligne_dao.getLigneByID(id);
	        	station=station_dao.getStationByID(idStation);
	        	 
	        	canton_dao.createCanton(nomCanton, commentaireCanton, ligne, station);  
	        
	        	
				listeCanton= canton_dao.listerCanton();        
				request.logout();
				request.setAttribute("listeCanton",listeCanton);
				this.getServletContext().getRequestDispatcher( "/WEB-INF/accueilRefCanton.jsp").forward( request, response );	        	
	        }
	        else{
	        	listeLigne= ligne_dao.listerLigne();
	        	listeStation= station_dao.listerStation();
	    		
				
				
	        	request.logout();
	        	request.setAttribute("listeStation",listeStation);
	        	request.setAttribute("listeLigne",listeLigne);
	        	request.setAttribute( "erreur", erreur );
	        	request.setAttribute( "erreurGénérale", erreurGenerale );
	            this.getServletContext().getRequestDispatcher( "/WEB-INF/formRefCanton.jsp" ).forward( request, response );
	        	
	        }
		}
		
		
		// Form Modifier		
		if(action.equals("Update")){
			
		
            boolean erreur[] = new boolean[3];
            boolean erreurGenerale = false;
            int idCanton = Integer.parseInt(request.getParameter("idCanton"));
			String nomCanton = request.getParameter("nomCanton");
	        String commentaireCanton = request.getParameter("commentaireCanton");
	        int idLigne = Integer.parseInt(request.getParameter("idLigne"));
	        System.out.println(request.getParameter("idStation"));
	        int idStation = Integer.parseInt(request.getParameter("idStation"));
	        
	        
	        if ( nomCanton.trim().isEmpty()) {
	        	 erreurGenerale=true;
	        	 erreur[0]=true;
	        }
	        if ( commentaireCanton.trim().isEmpty()) {
	        	 erreurGenerale=true;
	        	 erreur[1]=true;
	        }
	     
	        if (erreurGenerale == false) {
	        	
	        	
				canton.setIdCanton(idCanton);
				canton.setNomCanton(nomCanton);
				canton.setCommentaireCanton(commentaireCanton);
				canton.setLigne(ligne_dao.getLigneByID(idLigne));
				canton.setStation(station_dao.getStationByID(idStation));
				
				canton_dao.modifierCanton(canton); 
	        	listeCanton= canton_dao.listerCanton();        
				request.logout();
				request.setAttribute("listeCanton",listeCanton);
				this.getServletContext().getRequestDispatcher( "/WEB-INF/accueilRefCanton.jsp").forward( request, response );	        	
	        }
	        else{
	        	canton.setIdCanton(idCanton);
				canton.setNomCanton(nomCanton);
				canton.setCommentaireCanton(commentaireCanton);
				canton.setLigne(ligne_dao.getLigneByID(idLigne));
				
				listeLigne= ligne_dao.listerLigne();
				
				
				
				
	        	request.logout();
	        	request.setAttribute( "erreur", erreur );
	        	request.setAttribute( "erreurGénérale", erreurGenerale );
	        	request.setAttribute( "canton", canton );
	        	request.setAttribute("listeLigne",listeLigne);
	            this.getServletContext().getRequestDispatcher( "/WEB-INF/formModifRefCanton.jsp" ).forward( request, response );
	        	
	        }
		}
	}
	
	
	
	
	
	
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

		String action = request.getParameter("action");
		
		if(action.equals("refCanton")){



			listeCanton = canton_dao.listerCanton();
			request.logout();
			request.setAttribute("listeCanton",listeCanton);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/accueilRefCanton.jsp").forward( request, response );
		}
		

		
		// affichage Form ajouter		
		if(action.equals("formAjouter")){

			listeLigne= ligne_dao.listerLigne();
			listeStation= station_dao.listerStation();
			
			
		
			request.logout();
			request.setAttribute("listeLigne",listeLigne);
			request.setAttribute("listeStation",listeStation);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/formRefCanton.jsp").forward( request, response );
		}
		
		//  affichage Form modifier
		if(action.equals("modif")){
			
			int  id = Integer.parseInt(request.getParameter("id"));
			canton=canton_dao.getCantonByID(id);
			listeLigne= ligne_dao.listerLigne();
			listeStation= station_dao.listerStation();
			
			request.logout();
			request.setAttribute("canton",canton);
			request.setAttribute("listeLigne",listeLigne);
			request.setAttribute("listeStation",listeStation);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/formModifRefCanton.jsp").forward( request, response );
		}
		
		// suppression et affichage de la nouvelle liste		
		if(action.equals("supp")){


			int  id = Integer.parseInt(request.getParameter("id"));
			canton_dao.supprimerCanton(id);
			listeCanton= canton_dao.listerCanton();        
			request.logout();
			request.setAttribute("listeCanton",listeCanton);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/accueilRefCanton.jsp").forward( request, response );
		}
	}

}
