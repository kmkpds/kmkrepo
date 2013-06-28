package servlets;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Date;
import java.util.Random;
import java.util.Timer;

import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

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
import org.xml.sax.SAXException;

import communicationJMS.Consommateur;
import communicationJMS.Listener;
import communicationJMS.Producteur;
import communicationJMS.XmlTransformer;
import dao.FactHoraireDAO;
import dao.HibernateUtils;


public class FactHoraireControler extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	static Producteur producteur = new Producteur();
	static XmlTransformer transforme = new XmlTransformer();
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
