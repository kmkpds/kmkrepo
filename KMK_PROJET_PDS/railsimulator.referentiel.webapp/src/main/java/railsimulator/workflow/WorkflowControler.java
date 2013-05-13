package railsimulator.workflow;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IncidentDAO;
import beans.Incident;






public class WorkflowControler extends HttpServlet{



	private List<Incident> listeIncident;
	private IncidentDAO incident_dao = new IncidentDAO();
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
    
		String action = request.getParameter("action");
		
		if(action.equals("visuWorkflow")){
			
			listeIncident = incident_dao.listerIncident();
			request.logout();
			request.setAttribute("listeIncident",listeIncident);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/majWorkflow.jsp").forward( request, response );
		}
	
	}



}
