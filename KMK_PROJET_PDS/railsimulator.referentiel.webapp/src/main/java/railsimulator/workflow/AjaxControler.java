package railsimulator.workflow;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Action;
import beans.Incident;
import dao.HibernateUtils;
import dao.IncidentDAO;

public class AjaxControler  extends HttpServlet {

	private Session se = null;
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		 IncidentDAO incident_dao = new IncidentDAO();
		 List<Incident> listeIncident = incident_dao.listerIncident();
		 List<Action> listeAction;
		
		response.setContentType("text/html");
		response.setContentType("text/javascript");
		response.setContentType("text/css");
		
		response.setCharacterEncoding( "UTF-8" );
		PrintWriter out = response.getWriter();

		
		//<link rel="stylesheet" type="text/css" href="css/corp.css"/>
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
		out.println(request.getContextPath());
		out.println("/css/corp.css\"/>");
		
		//<link rel="alternate stylesheet" type="text/css" href="css/print.css" media="screen" title="Version imprimable" id="stylesheet-print" />
		out.println("<link rel=\"alternate stylesheet\" type=\"text/css\" href=\"");
		out.println(request.getContextPath());
		out.println("/css/print.css\" media=\"screen\" title=\"Version imprimable\" id=\"stylesheet-print\" />");
		
		//<script type="text/javascript" src="js/main.js"></script>	
		out.println("<script type=\"text/javascript\" src=\"");
		out.println(request.getContextPath());
		out.println("/js/main.js\"/>");
		
		out.println("<div id=\"RnoMainContent\" class=\"sc\">");
		out.println("<h2 id=\"RnoPageTitle\">");
		out.println("<a></a>Statut des incidents déclarés");
		out.println("</h2>");
		out.println("<div id=\"RnoWorkspace\">");
		
		for (int i=0; i<listeIncident.size(); i++)
	      {
		listeAction = (List<Action>) listeIncident.get(i).getProcedure().getListeAction();
		
		BigInteger nbaction=incident_dao.getNbActionTotal(listeIncident.get(i).getIdIncident());
		BigInteger nbfini=incident_dao.getNbActionFini(listeIncident.get(i).getIdIncident());
		
		out.println("<div class=\"RnoSection\">");
		out.println("<h3 class=\"RnoSectionTitle\">");
		
		out.println("<span></span>"+ listeIncident.get(i).getTypeIncident().getLibelleType()); 
		out.println(" déclaré le ");
		out.println(listeIncident.get(i).getDateDebut());
		out.println(": Traitement par ");
		out.println(listeIncident.get(i).getProcedure().getLibelleProcedure());
		
		if (listeIncident.get(i).getDateFin() == null){
			out.println(" EN COURS à " + (nbfini.floatValue()/nbaction.floatValue())*100 + "%");
		
		}else if (listeIncident.get(i).getDateFin() != null){
			out.println(" TERMINE à "+ (nbfini.floatValue()/nbaction.floatValue())*100 + "%"
					+ " le "+ listeIncident.get(i).getDateFin());
		}
		out.println("</h3>");									
		out.println("<div class=\"RnoSectionContent\">");
		out.println("<div class=\"RnoDataTable\">");
		out.println("<table>");
		out.println("<tr class=\"RnoMainHeader\">");
		out.println("<th>");
		out.println("<a href=\"#\">Action incident</a>");
		out.println("</th>");
		out.println("<th>");
		out.println("<a href=\"#\">Date d'enclenchement</a>");
		out.println("</th>");
		out.println("<th>");
		out.println("<a href=\"#\">Date de fin</a>");
		out.println("</th>");
		out.println("<th>");
		out.println("<a href=\"#\">Statut</a>");
		out.println("</th>");
		out.println("</tr>");
		
		Iterator<Action> it = listeAction.iterator();
		while (it.hasNext()) {
			 Action action = (Action) it.next();
		
		out.println("<tr>");
		
		out.println("<td>" + action.getLibelleActionIntervention() +"</td>");
		out.println("<td>");
			if (action.getDateDebut() == null){
				out.println("");
			}else
				out.println(action.getDateDebut());
		out.println("</td>");
		
		out.println("<td>");
		if (action.getDateFin() == null){
			out.println("");
		}else
			out.println(action.getDateFin());
		out.println("</td>");
		
		out.println("<td>");
		if (action.getDateDebut() != null && action.getDateFin() != null){
			out.println("<img src=\"");
			out.println(request.getContextPath());
			out.println("/images/icones/iconeTermine.png\" width=\"20\" height=\"20\">");
			out.println(" TERMINE");
		}else if (action.getDateDebut() == null){
			out.println("<img src=\"");
			out.println(request.getContextPath());
			out.println("/images/icones/iconeEnAttente.png\" width=\"20\" height=\"20\">");
			out.println(" EN ATTENTE");
		}else if (action.getDateDebut() != null && action.getDateFin() == null){
			out.println("<img src=\"");
			out.println(request.getContextPath());
			out.println("/images/icones/iconeEnCours.png\" width=\"20\" height=\"20\">");
			out.println("EN COURS");
		}
		out.println("</td>");
		
		out.println("</tr>");
	      }
        out.println("</table>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
	      }
		out.println("</div>");
	    out.println("</div>");
	    
	   
	}
	
		
		
}
