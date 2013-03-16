package railsimulator.referentiel.webapp.controleur;

import java.io.IOException; 
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Ligne;

import dao.LigneDAO;






public class LigneControler extends HttpServlet {

	private List<Ligne> listeLigne;
	private LigneDAO ligne_dao = new LigneDAO();
	private Ligne ligne = new Ligne();





	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		
	
		if(action.equals("Annuler")){

			afficherRefLigne(request, response);

		}
		
		
		// Form valider		
		if(action.equals("Valider")){
			
			creationLigne(request, response);
			
           
		}
		
			
		if(action.equals("Update")){
				
			modificationLigne(request, response);
				
		}
					
				
		          
	
	}


	
	
	


	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

		String action = request.getParameter("action");


		if(action.equals("refLigne")){
			
			afficherRefLigne(request, response);
						
		}
				
		if(action.equals("formAjouter")){
  
			formCreationLigne(request, response);
			
		}
        
	
		if(action.equals("modif")){
		 
			formModificationLigne(request, response);
			
		}
		
		
		if(action.equals("supp")){

			suppressionLigne(request, response);
			
		}



	}
	
	
	 
	
	public void afficherRefLigne( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		listeLigne = ligne_dao.listerLigne();
		request.logout();
		request.setAttribute("listeLigne",listeLigne);
		this.getServletContext().getRequestDispatcher( "/WEB-INF/accueilRefLigne.jsp").forward( request, response );
				
	}
	
	public void formCreationLigne( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		request.logout();
		this.getServletContext().getRequestDispatcher( "/WEB-INF/formRefLigne.jsp").forward( request, response );
				
	}
	
   public void formModificationLigne( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
	   int  id = Integer.parseInt(request.getParameter("id"));		
		ligne=ligne_dao.getLigneByID(id);		
		request.logout();
		request.setAttribute("ligne",ligne);
		this.getServletContext().getRequestDispatcher( "/WEB-INF/formModifRefLigne.jsp").forward( request, response );
				
	}
   
   public void suppressionLigne( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
	   int  id = Integer.parseInt(request.getParameter("id"));
		ligne_dao.supprimerLigne(id);
		listeLigne= ligne_dao.listerLigne();        
		request.logout();
		request.setAttribute("listeLigne",listeLigne);
		this.getServletContext().getRequestDispatcher( "/WEB-INF/accueilRefLigne.jsp").forward( request, response );
				
	}
   
   
	public void creationLigne( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		 boolean erreur[] = new boolean[2];
         boolean erreurGenerale = false;
			String nomLigne = request.getParameter("nomLigne");
	        String commentaireLigne = request.getParameter("commentaireLigne");	    
	        
	        if ( nomLigne.trim().isEmpty()) {
	        	 erreurGenerale=true;
	        	 erreur[0]=true;
	        }
	        if ( commentaireLigne.trim().isEmpty()) {
	        	 erreurGenerale=true;
	        	 erreur[1]=true;
	        }
	     
	        if (erreurGenerale == false) {
	        	
	        	ligne_dao.createLigne(nomLigne, commentaireLigne);  	        
				listeLigne= ligne_dao.listerLigne();        
				request.logout();
				request.setAttribute("listeLigne",listeLigne);
				this.getServletContext().getRequestDispatcher( "/WEB-INF/accueilRefLigne.jsp").forward( request, response );	        	
	        }
	        else{
	          
	        	request.logout();
	        	request.setAttribute( "erreur", erreur );
	        	request.setAttribute( "erreurGénérale", erreurGenerale );
	            this.getServletContext().getRequestDispatcher( "/WEB-INF/formRefLigne.jsp" ).forward( request, response );
	        	
	        }
	}
	
	   public void modificationLigne( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
			
		   boolean erreur[] = new boolean[3];
           boolean erreurGenerale = false;
           int idLigne = Integer.parseInt(request.getParameter("idLigne"));
			String nomLigne = request.getParameter("nomLigne");
	        String commentaireLigne = request.getParameter("commentaireLigne");	    
	        
	        
	        if ( nomLigne.trim().isEmpty()) {
	        	 erreurGenerale=true;
	        	 erreur[0]=true;
	        }
	        if ( commentaireLigne.trim().isEmpty()) {
	        	 erreurGenerale=true;
	        	 erreur[1]=true;
	        }
	     
	        if (erreurGenerale == false) {
	        	
	        	
				ligne.setIdLigne(idLigne);
				ligne.setNomLigne(nomLigne);
				ligne.setCommentaire(commentaireLigne);
				
				ligne_dao.modifierLigne(ligne); 
	        	listeLigne= ligne_dao.listerLigne();        
				request.logout();
				request.setAttribute("listeLigne",listeLigne);
				this.getServletContext().getRequestDispatcher( "/WEB-INF/accueilRefLigne.jsp").forward( request, response );	        	
	        }
	        else{
	        	ligne.setIdLigne(idLigne);
				ligne.setNomLigne(nomLigne);
				ligne.setCommentaire(commentaireLigne);
				
				
	        	request.logout();
	        	request.setAttribute( "erreur", erreur );
	        	request.setAttribute( "erreurGénérale", erreurGenerale );
	        	request.setAttribute( "ligne", ligne );
	            this.getServletContext().getRequestDispatcher( "/WEB-INF/formModifRefLigne.jsp" ).forward( request, response );
	        	
	        }
		
		 
		}
	
	
}