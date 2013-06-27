package railsimulator.gestionperso; 

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Tache;
import dao.TacheDAO;

public class TacheControler extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Tache> listeTache;
	private TacheDAO tache_dao = new TacheDAO();
	private Tache tache = new Tache();
	String duree, libelletache;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");		
		// Form valider		
		if(action.equals("valider")){
			System.out.println("jjjjj");
		
            boolean erreur[] = new boolean[2];
            boolean erreurGenerale = false;
			         
	         libelletache = request.getParameter("libelletache");
	         
	     
	        if ( libelletache.trim().isEmpty()) {
	        	 erreurGenerale=true;
	        	 erreur[2]=true;
	        }
	     
	        if (erreurGenerale == false) {
	        
	        	Tache tache = new Tache(libelletache);
	        	tache_dao.createTache(tache);  	        
				listeTache= tache_dao.listerTache();        
				//request.logout();
				request.setAttribute("listeTache",listeTache);
				this.getServletContext().getRequestDispatcher( "/WEB-INF/accueilRefTache.jsp").forward( request, response );	        	
	        }
	        else{
	          
	        	//request.logout();
	        	request.setAttribute( "erreur", erreur );
	        	request.setAttribute( "erreurGénérale", erreurGenerale );
	        	this.getServletContext().getRequestDispatcher( "/WEB-INF/formRefTache.jsp").forward( request, response );
	        	
	        }
		}
		
		// Form Modifier		
				if(action.equals("update")){
					
				
		            boolean erreur[] = new boolean[3];
		            boolean erreurGenerale = false;
		            int idtache = Integer.parseInt(request.getParameter("idtache"));
		            
			       
			        libelletache = request.getParameter("libelletache");
			        
			         
			       
			        
			     
			        if ( libelletache.trim().isEmpty()) {
			        	 erreurGenerale=true;
			        	 erreur[2]=true;
			        }
		        
			        
			        if (erreurGenerale == false) {
			        	
			        	
			         	tache.setIdtache(idtache);
			        	
			        	tache.setLibelletache(libelletache);
			        
			        	
						
			        	tache_dao.modifierTache(tache); 
			        	listeTache= tache_dao.listerTache();        
						request.logout();
						request.setAttribute("listeTache",listeTache);
						this.getServletContext().getRequestDispatcher( "/WEB-INF/accueilRefTache.jsp").forward( request, response );	        	
			        }
			        else{
			        	tache.setIdtache(idtache);
			        	
			        	
			        	tache.setLibelletache(libelletache);
			        	
						
						
			        	request.logout();
			        	request.setAttribute( "erreur", erreur );
			        	request.setAttribute( "erreurGénérale", erreurGenerale );
			        	request.setAttribute( "tache", tache );
			            this.getServletContext().getRequestDispatcher( "/WEB-INF/formModifRefTache.jsp" ).forward( request, response );
			        	
			        }
				}
	
	}



	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

		String action = request.getParameter("action");

		// affichage référentiel liste tache
		if(action.equals("refTache")){
			listeTache= tache_dao.listerTache();
	
			//request.logout();
			request.setAttribute("listeTache",listeTache);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/accueilRefTache.jsp").forward( request, response );
		}


		// affichage Form ajouter		
		if(action.equals("formAjouter")){
			
			listeTache= tache_dao.listerTache();
			request.setAttribute("listeTache",listeTache);
			
			this.getServletContext().getRequestDispatcher( "/WEB-INF/formRefTache.jsp").forward( request, response );
		}
        
	
		//  affichage Form modifier
		if(action.equals("modif")){
			
			int  id = Integer.parseInt(request.getParameter("id"));
	
			tache=tache_dao.getTacheByID(id);
			
			request.logout();
			request.setAttribute("tache",tache);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/formModifRefTache.jsp").forward( request, response );
		}
	

		// suppression et affichage de la nouvelle liste		
		if(action.equals("supp")){

			int  id = Integer.parseInt(request.getParameter("id"));
			tache_dao.supprimerTache(id);
			listeTache= tache_dao.listerTache();        
			request.logout();
			request.setAttribute("listeTache",listeTache);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/accueilRefTache.jsp").forward( request, response );
		}
		
		
	}
}