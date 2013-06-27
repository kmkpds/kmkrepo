/**
 * 
 */
/**
 * @author Fatizara
 *
 */
package railsimulator.gestionperso;


import java.io.IOException; 
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;







import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeDAO;
import dao.FactHoraireDAO;
import dao.HorairePDAO;
import dao.PosteParSiteDAO;
import dao.SiteDAO;
import beans.Employe;
import beans.FactHoraire;
import beans.HoraireP;
import beans.PosteParSite;
import beans.Site;




public class SiteControler extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private List<Employe> listeEmploye;
	private EmployeDAO employe_dao = new EmployeDAO();
	private List<Site> listeSite;
	private List<PosteParSite> listePosteParSite;
	private PosteParSiteDAO posteParSite_dao = new PosteParSiteDAO();
	private SiteDAO site_dao = new SiteDAO();
	private HorairePDAO horairep_dao=new HorairePDAO();
	private FactHoraireDAO facthoraire_dao= new FactHoraireDAO();
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		System.out.println("avant");
		String action = request.getParameter("action");
		
			
		if (action.equals("empParSite")){
			listePosteParSite = posteParSite_dao.listerPosteParSite();
			
			afficherPoste(request, response);
				
		}
	
		if (action.equals("tache")){

			afficherRefTacheEmp(request, response);	
			System.out.println("Fin action");
		}
		
		
	}	
	
	


public void afficherPoste( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	
	listePosteParSite= posteParSite_dao.listerPosteParSite();
	listeSite= site_dao.listerSite();
	
	request.setAttribute("listePosteParSite",listePosteParSite);
	request.setAttribute("listeSite",listeSite);
	this.getServletContext().getRequestDispatcher( "/WEB-INF/empParSite.jsp").forward( request, response );
			
}


public void afficherRefTacheEmp( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	
	int idsite = Integer.parseInt(request.getParameter("id"));	
	listeEmploye = employe_dao.getEmpInSite(idsite);
	List<HoraireP> listHoraireP=horairep_dao.listerHoraireP();
	Map <Integer,HoraireP> mapHoraireP=new HashMap<Integer,HoraireP>(0);
	for(int i=0;i<listHoraireP.size();i++)
		mapHoraireP.put(listHoraireP.get(i).getIdhorairep(),listHoraireP.get(i));
	
	Map <Integer,FactHoraire> listStatus=new HashMap<Integer,FactHoraire>(0) ;
	DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	Date date=new Date();
	String str_date=dateFormat.format(date).toString();
	
	for(int i=0;i<listeEmploye.size();i++)
	{
		FactHoraire f=facthoraire_dao.getUserDailyStatus(listeEmploye.get(i).getIdemp(), str_date);
		listStatus.put(listeEmploye.get(i).getIdemp(), f);
	}
	request.setAttribute("listeEmploye",listeEmploye);	
	//System.out.println(idsite);
	//System.out.println(listeEmploye.get(0).getNom());
	request.setAttribute("idsite",idsite);
	request.setAttribute("listStatus",listStatus);
	request.setAttribute("mapHoraireP",mapHoraireP);
	this.getServletContext().getRequestDispatcher( "/WEB-INF/empsiteX.jsp").forward( request, response );
			
}



public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	doGet(request,response);
	

	 }
}
