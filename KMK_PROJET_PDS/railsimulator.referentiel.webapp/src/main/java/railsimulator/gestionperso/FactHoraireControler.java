package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mock.Mock1Emp2Absent;
import mock.Mock1Emp3AHeure;
import mock.Mock1Emp13AHeure;
import mock.Mock1Emp3SortieHeure;
import mock.Mock2Emp18SortieAvantHeure;
import mock.Mock2Emp4ARetard;
import mock.Mock2Emp4SortieAHeure;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Employe;
import beans.FactHoraire;
import beans.HoraireP;
import dao.EmployeDAO;
import dao.FactHoraireDAO;
import dao.HibernateUtils;
import dao.HorairePDAO;


public class FactHoraireControler extends HttpServlet {

	private List<Employe> listeEmploye;
	private EmployeDAO employe_dao = new EmployeDAO();
	private HorairePDAO horairep_dao=new HorairePDAO();
	private FactHoraireDAO facthoraire_dao= new FactHoraireDAO();
    	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{


    		String action = request.getParameter("action");
    		Session se = null;
    		Timer moteurMessage;



    		if(action.equals("lancer")){

    			se = HibernateUtils.getSession();
    			Transaction t = se.beginTransaction();

    			//Purge de la table fact horaire de la bdd
    			
    			
    			Query delete1=se.createQuery("delete from FactHoraire");
    			delete1.executeUpdate();
    			t.commit();

    			//Exécution des tâches
    			moteurMessage = new Timer();		

    			moteurMessage.schedule(new Mock1Emp13AHeure(), 5000);
    			moteurMessage.schedule(new Mock1Emp3AHeure(), 7000);
    			moteurMessage.schedule(new Mock1Emp2Absent(), 9000);
    			moteurMessage.schedule(new Mock2Emp4ARetard(), 10000);
    			
    			moteurMessage.schedule(new Mock2Emp4SortieAHeure(), 20000);
    			moteurMessage.schedule(new Mock1Emp3SortieHeure(), 30000);
    			moteurMessage.schedule(new Mock2Emp18SortieAvantHeure(), 14000);
    		
    		}
    		
    		//this.getServletContext().getRequestDispatcher( "/WEB-INF/empprodconsomDemo.jsp").forward( request, response );		

    	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String ret="";
		String str_state="";
		if(action.equals("facthoraireAjax"))
		{
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
				Employe emp=listeEmploye.get(i);
				FactHoraire f=facthoraire_dao.getUserDailyStatus(emp.getIdemp(), str_date);

				
				HoraireP hp=mapHoraireP.get(emp.getHorairep_id());
				
				 if(f==null)
					str_state="Absent";
				else
				{
					String heuref=f.getHeuref();
					
					if(heuref.equals("00:00:00"))
					{
						String horaired=f.getHeured();
						String horairedPrev= hp.getHeured();
						
						
						Timestamp timestampd=Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd")
						.format(new Date()).
						concat(" "+ horaired));
						
						Timestamp timestampdPrev=Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd")
						.format(new Date ()).
						concat(" "+ horairedPrev));
						if(timestampdPrev.equals(timestampd))
							str_state="En poste arrivee a l'heure";
						else if(timestampd.after(timestampdPrev))
							str_state="En poste arrivee en retard";
						else if(timestampd.before(timestampdPrev))
							str_state="En poste arrivee en avance";
						
					}
					else
					{
						
						String horairef=f.getHeuref();
						String horairefPrev= hp.getHeuref();
						
						Timestamp timestampf=Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new Date()).concat(" "+ horairef));
						
						Timestamp timestampfPrev=Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd")
						.format(new Date ()).
						concat(" "+ horairefPrev)); 
						
						if(timestampfPrev.equals(timestampf))
							str_state="Absent sortie a l heure"; 
						
						else if(timestampf.before(timestampfPrev))
							str_state="Absent  sortie avant l heure";
					}
				}
				
				ret+=listeEmploye.get(i).getIdemp()+"="+ str_state+"&";
			}
		//	System.out.println("ret "+ret);
			PrintWriter out = response.getWriter();
			out.print(ret);
			return ;
		}
	}
		

}
