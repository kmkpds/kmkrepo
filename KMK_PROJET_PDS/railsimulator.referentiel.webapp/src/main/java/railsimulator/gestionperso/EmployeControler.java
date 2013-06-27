package railsimulator.gestionperso; 

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Employe;
import beans.HoraireP;
import beans.Site;
import dao.EmployeDAO;
import dao.HorairePDAO;
import dao.SiteDAO;

public class EmployeControler extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Employe> listeEmploye;
	private List<HoraireP> listeHoraireP;
	private EmployeDAO employe_dao = new EmployeDAO();
	private SiteDAO site_dao = new SiteDAO();
	private HorairePDAO horaireP_dao = new HorairePDAO();
	private List<Site> listeSite;
	private Employe employe = new Employe();
	String nom, prenom, fonction;
	int horairep_id, site_idsite; 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");		
		// Form valider		
		if(action.equals("valider")){
			System.out.println("jjjjj");
		
            boolean erreur[] = new boolean[2];
            boolean erreurGenerale = false;
			 nom = request.getParameter("nom");
	         prenom = request.getParameter("prenom");	 
	         fonction = request.getParameter("fonction");
	         String horaire = request.getParameter("horairep_id");
	         String idsite =request.getParameter("site_idsite");
	         horairep_id = Integer.parseInt(horaire);
	         site_idsite = Integer.parseInt(idsite);
	        
 
	        if ( nom.trim().isEmpty()) {
	        	 erreurGenerale=true;
	        	 erreur[0]=true;
	        }
	        if ( prenom.trim().isEmpty()) {
	        	 erreurGenerale=true;
	        	 erreur[1]=true;
	        }
	     
	        if ( fonction.trim().isEmpty()) {
	        	 erreurGenerale=true;
	        	 erreur[2]=true;
	        }
	     
	        if (erreurGenerale == false) {
	        
	        	Employe empl = new Employe(nom, prenom, fonction, horairep_id, site_idsite);
	        	employe_dao.createEmploye(empl);  	        
				listeEmploye= employe_dao.listerEmploye();        
				
				request.setAttribute("listeEmploye",listeEmploye);
				this.getServletContext().getRequestDispatcher( "/WEB-INF/accueilRefEmploye.jsp").forward( request, response );	        	
	        }
	        else{
	          
	        	
	        	request.setAttribute( "erreur", erreur );
	        	request.setAttribute( "erreurGénérale", erreurGenerale );
	        	this.getServletContext().getRequestDispatcher( "/WEB-INF/formRefEmploye.jsp").forward( request, response );
	        	
	        }
		}
		
		// Form Modifier		
				if(action.equals("update")){
					
				
		            boolean erreur[] = new boolean[3];
		            boolean erreurGenerale = false;
		            int idemp = Integer.parseInt(request.getParameter("idemp"));
		            nom = request.getParameter("nom");
			        prenom = request.getParameter("prenom");	 
			        fonction = request.getParameter("fonction");
			        horairep_id = Integer.parseInt(request.getParameter("horairep_id"));
			        site_idsite = Integer.parseInt(request.getParameter("site_idsite"));
			         
			        if ( nom.trim().isEmpty()) {
			        	 erreurGenerale=true;
			        	 erreur[0]=true;
			        }
			        if ( prenom.trim().isEmpty()) {
			        	 erreurGenerale=true;
			        	 erreur[1]=true;
			        }
			     
			        if ( fonction.trim().isEmpty()) {
			        	 erreurGenerale=true;
			        	 erreur[2]=true;
			        }
		        
			        
			        if (erreurGenerale == false) {
			        	
			        	
			         	employe.setIdemp(idemp);
			        	employe.setNom(nom);
			        	employe.setPrenom(prenom);
			        	employe.setFonction(fonction);
			        	employe.setHorairep_id(horairep_id);
			        	employe.setSite_idsite(site_idsite);
						
			        	employe_dao.modifierEmploye(employe); 
			        	listeEmploye= employe_dao.listerEmploye();        
						request.logout();
						request.setAttribute("listeEmploye",listeEmploye);
						this.getServletContext().getRequestDispatcher( "/WEB-INF/accueilRefEmploye.jsp").forward( request, response );	        	
			        }
			        else{
			        	employe.setIdemp(idemp);
			        	employe.setNom(nom);
			        	employe.setPrenom(prenom);
			        	employe.setFonction(fonction);
			        	employe.setHorairep_id(horairep_id);
			        	employe.setSite_idsite(site_idsite);
						
						
			        	request.logout();
			        	request.setAttribute( "erreur", erreur );
			        	request.setAttribute( "erreurGénérale", erreurGenerale );
			        	request.setAttribute( "employe", employe );
			            this.getServletContext().getRequestDispatcher( "/WEB-INF/formModifRefEmploye.jsp" ).forward( request, response );
			        	
			        }
				}
	
	}



	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

		String action = request.getParameter("action");

		// affichage référentiel liste ligne
		if(action.equals("refEmploye")){
			listeEmploye= employe_dao.listerEmploye();
	
			//request.logout();
			request.setAttribute("listeEmploye",listeEmploye);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/accueilRefEmploye.jsp").forward( request, response );
		}


		// affichage Form ajouter		
		if(action.equals("formAjouter")){
			
			listeSite= site_dao.listerSite();
			listeHoraireP= horaireP_dao.listerHoraireP();
			
			//request.logout();
			request.setAttribute("listeSite",listeSite);
			request.setAttribute("listeHoraireP",listeHoraireP);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/formRefEmploye.jsp").forward( request, response );
		}
        
	
		//  affichage Form modifier
		if(action.equals("modif")){
			
			int  id = Integer.parseInt(request.getParameter("id"));
	
			employe=employe_dao.getEmployeByID(id);
			
			request.logout();
			request.setAttribute("employe",employe);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/formModifRefEmploye.jsp").forward( request, response );
		}
	

		// suppression et affichage de la nouvelle liste		
		if(action.equals("supp")){

			int  id = Integer.parseInt(request.getParameter("id"));
			employe_dao.supprimerEmploye(id);
			listeEmploye= employe_dao.listerEmploye();        
			request.logout();
			request.setAttribute("listeEmploye",listeEmploye);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/accueilRefEmploye.jsp").forward( request, response );
		}
		
		
	}
}