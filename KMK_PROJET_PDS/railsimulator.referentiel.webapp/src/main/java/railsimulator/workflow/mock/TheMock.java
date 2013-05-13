package railsimulator.workflow.mock;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Timer;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateUtils;

public class TheMock extends HttpServlet  {
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

		String action = request.getParameter("action");
		Session se = null;
		Timer moteurMessage;
	
		if(action.equals("incident")){
			
			se = HibernateUtils.getSession();
			Transaction t = se.beginTransaction();
			
			//Purge des tables:
			//Procedure, Action, Incident
			//de la base de donnée de TU
			Query delete1=se.createQuery("delete from Action");
			Query delete2=se.createQuery("delete from Incident");
			Query delete3=se.createQuery("delete from Procedure");
			delete1.executeUpdate();
			delete2.executeUpdate();
			delete3.executeUpdate();
			t.commit();
		
			//Exécution des tâches
			moteurMessage = new Timer();
			
			moteurMessage.schedule(new MockIncident1(), 2000);
			
			moteurMessage.schedule(new Mock1Action1Termine(), 5000);
			moteurMessage.schedule(new Mock1Action2EnCours(), 7000);
			moteurMessage.schedule(new Mock1Action3EnCours(), 9000);
			moteurMessage.schedule(new Mock1Action2Termine(), 10000);
			moteurMessage.schedule(new Mock1Action4EnCours(), 12000);
			
			moteurMessage.schedule(new MockIncident2(), 14000);
			moteurMessage.schedule(new Mock2Action1Termine(), 19000);
			
			moteurMessage.schedule(new Mock1Action3Termine(), 21000);
			moteurMessage.schedule(new Mock1Action4Termine(), 23000);
			moteurMessage.schedule(new Mock1Action5EnCours(), 25000);
			moteurMessage.schedule(new Mock1Action6EnCours(), 25000);
			
			moteurMessage.schedule(new Mock2Action2EnCours(), 25000);
			moteurMessage.schedule(new Mock2Action2Termine(), 27000);
			moteurMessage.schedule(new Mock2Action3EnCours(), 29000);
			
			moteurMessage.schedule(new Mock1Action5Termine(), 29000);
			
			moteurMessage.schedule(new Mock2Action3Termine(), 31000);
			
			moteurMessage.schedule(new Mock1Action7EnCours(), 33000);
			moteurMessage.schedule(new Mock1Action6Termine(), 33000);
			
			moteurMessage.schedule(new Mock2Action4EnCours(), 33000);
			moteurMessage.schedule(new Mock2Action4Termine(), 35000);
			
			moteurMessage.schedule(new Mock1Action8EnCours(), 35000);
			moteurMessage.schedule(new Mock1Action7Termine(), 37000);
			moteurMessage.schedule(new Mock1Action8Termine(), 39000);
			
			
			
		}

		
		
	}


}
