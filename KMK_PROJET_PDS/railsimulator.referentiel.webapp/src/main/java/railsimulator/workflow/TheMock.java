package railsimulator.workflow;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Timer;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import railsimulator.workflow.mock.Mock1Action1Termine;
import railsimulator.workflow.mock.Mock1Action2EnCours;
import railsimulator.workflow.mock.Mock1Action2Termine;
import railsimulator.workflow.mock.Mock1Action3EnCours;
import railsimulator.workflow.mock.Mock1Action3Termine;
import railsimulator.workflow.mock.Mock2Action1Termine;
import railsimulator.workflow.mock.Mock2Action2EnCours;
import railsimulator.workflow.mock.Mock2Action2Termine;
import railsimulator.workflow.mock.MockIncident1;
import railsimulator.workflow.mock.MockIncident2;

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
			//TypeIncident, Procedure, Action, Incident
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
			
			moteurMessage.schedule(new MockIncident2(), 14000);
			
			moteurMessage.schedule(new Mock1Action2Termine(), 12000);
			moteurMessage.schedule(new Mock1Action3Termine(), 21000);
			
			moteurMessage.schedule(new Mock2Action1Termine(), 19000);
			moteurMessage.schedule(new Mock2Action2EnCours(), 23000);
			moteurMessage.schedule(new Mock2Action2Termine(), 26000);
			
			
		}

		
		
	}


}
