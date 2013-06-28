/**
 * 
 */
/**
 * @author Fatizara
 *
 */
package servlets;


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

import dao.EmpTacheDAO;
import dao.EmployeDAO;
import dao.FactHoraireDAO;
import dao.HorairePDAO;
import dao.TacheDAO;
import beans.EmpTache;
import beans.Employe;
import beans.FactHoraire;
import beans.HoraireP;
import beans.Tache;




public class AfEmployeControler extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private List<Employe> listeEmploye;
	private List<Tache> listeTache;
	private List<EmpTache> listeEmpTache2;

	
	
	private EmployeDAO employe_dao = new EmployeDAO();
	private TacheDAO tache_dao = new TacheDAO();
	private EmpTacheDAO emptache_dao = new EmpTacheDAO();
	private FactHoraireDAO facthoraire_dao= new FactHoraireDAO();
	
	private HorairePDAO horairep_dao=new HorairePDAO();
		
	private Employe employe = new Employe();

	
	String date, heuredtache, duree, commentaire;
	
	

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		System.out.println("avant");
		String action = request.getParameter("action");
		
		if (action.equals("Afemp")){
			System.out.println("debut action v");
			
			afficherRefEmp(request, response);	
			System.out.println("Fin action");
		}
		
	
		
		if (action.equals("tache")){

			afficherRefTacheEmp(request, response);	
			System.out.println("Fin action");
		}
		

		
		if(action.equals("formAffecter")){
			
			int  idemp = Integer.parseInt(request.getParameter("idemp"));
			
			employe=employe_dao.getEmployeByID(idemp);
			listeTache = tache_dao.listerTache();
	
			request.logout();
			request.setAttribute("employe",employe);
			request.setAttribute("listeTache",listeTache);
			
			this.getServletContext().getRequestDispatcher( "/WEB-INF/formAffecter.jsp").forward( request, response );
		}
		
		
		
	}	
	
	
	//affichage des emp dans afficher emp 
public void afficherRefEmp( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	
	listeEmploye= employe_dao.listerEmploye();
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
	
	request.setAttribute("listStatus",listStatus);
	request.setAttribute("mapHoraireP",mapHoraireP);
	this.getServletContext().getRequestDispatcher( "/WEB-INF/afficherEmploye.jsp").forward( request, response );
			
}

public void afficherRefTacheEmp( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	
	int idemp = Integer.parseInt(request.getParameter("id"));
	listeEmpTache2 = emptache_dao.getEmpHasTache(idemp);
	request.setAttribute("listeEmpTache",listeEmpTache2);
	request.setAttribute("idemp",idemp);
	this.getServletContext().getRequestDispatcher( "/WEB-INF/afficherTacheEmp.jsp").forward( request, response );
			
}




public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	
	String action = request.getParameter("action");
		
	if(action.equals("valider")){
		

		int idtache =Integer.parseInt(request.getParameter("idtache"));
	   
		int idemp = Integer.parseInt(request.getParameter("idemp")); 
		 
        date = request.getParameter("selectedate");
        System.out.println("date e ee  "+date);
        heuredtache= request.getParameter("heuredtache");
        duree=request.getParameter("duree");
        commentaire = request.getParameter("commentaire");
       
        
        EmpTache emptache = new EmpTache(idemp, idtache,date, heuredtache, duree, commentaire);
        emptache_dao.createEmpToTache(emptache);      
        listeEmpTache2= emptache_dao.getEmpHasTache(idemp);       

		request.setAttribute("listeEmpTache",listeEmpTache2);
		request.setAttribute("idemp",idemp);
		this.getServletContext().getRequestDispatcher( "/WEB-INF/afficherTacheEmp.jsp").forward( request, response );	 
		
	}
}

}
