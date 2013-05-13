package railsimulator.workflow.mock;

import java.util.TimerTask;

import org.hibernate.Session;

import beans.Procedure;
import beans.TypeIncident;
import dao.ActionDAO;
import dao.HibernateUtils;
import dao.IncidentDAO;
import dao.ProcedureDAO;
import dao.TypeIncidentDAO;

public class MockIncident2 extends TimerTask{

	private Session se = null;
	
	TypeIncident typeIncident = new TypeIncident();
	Procedure procedure = new Procedure();
	
	private IncidentDAO incident_dao = new IncidentDAO();
	private ProcedureDAO procedure_dao = new ProcedureDAO();
	private ActionDAO action_dao = new ActionDAO();
	private TypeIncidentDAO typeincident_dao = new TypeIncidentDAO();
	
	public void run(){
		
		try{
			se = HibernateUtils.getSession();
				
			//Cr�ation de la proc�dure pour ce typeincident
			int idProcedure;
			idProcedure = procedure_dao.createProcedureReturnId("Proc�dure B");
			procedure = procedure_dao.getProcedureByID(idProcedure);
		    se.flush();
		    
		    //Cr�ation des actions pour cette proc�dure
		    action_dao.createActionReturnId(4, "Arr�t du train", "2013-05-06 18:00:10", 5, 2013, null, "En cours", procedure);
		    action_dao.createActionReturnId(5, "Appeler agents", null, 0, 0, null, "En attente", procedure);
		    se.flush();
		    
	        //Cr�ation du 2�me typeincident
		    int idtype = 2;//Colis suspect
			typeIncident = typeincident_dao.getTypeIncidentByID(idtype);
		    se.flush();
		    
		    //Cr�ation de l'incident 2
		    incident_dao.createIncidentReturnId(2, "Incident d�clar� dans le train B", "2013-05-06 18:00:00", 5, 2013, null, "Majeur", procedure, typeIncident, null);
			se.flush();
			
		}//fin try
		catch (Exception e) {
			e.printStackTrace();		
		}//fin catch
	}//fin run

}//fin classe mock
