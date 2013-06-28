package railsimulator.gestionperso;

import java.io.IOException;

import java.util.Timer;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import beans.FactHoraire;
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


import dao.FactHoraireDAO;
import dao.HibernateUtils;


public class FactHoraireControler extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private static FactHoraire facthoraire ;
	private static FactHoraireDAO fact_dao = new FactHoraireDAO();
	String confirmation ="";
	int cpt;
   
 
    	
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
    		
    		this.getServletContext().getRequestDispatcher( "/WEB-INF/empprodconsomDemo.jsp").forward( request, response );		

    	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");

		if(action.equals("demo")){
			this.getServletContext().getRequestDispatcher( "/WEB-INF/empprodconsomDemo.jsp").forward( request, response );		
		}
	}


	}
