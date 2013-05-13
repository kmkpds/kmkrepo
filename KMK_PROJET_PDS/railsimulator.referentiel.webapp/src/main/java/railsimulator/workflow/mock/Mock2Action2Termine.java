package railsimulator.workflow.mock;

import java.util.TimerTask;

import beans.Action;
import beans.Incident;
import dao.ActionDAO;
import dao.IncidentDAO;

public class Mock2Action2Termine extends TimerTask{

	Action action = new Action();	
	Incident incident = new Incident();
	private ActionDAO action_dao = new ActionDAO();
	private IncidentDAO incident_dao = new IncidentDAO();
	
	public void run(){
		
		try{
			
		    action = action_dao.getActionByID(5);
		    action.setDateFin("2013-05-06 18:10:02");
		    action.setStatut("Terminé");
		    action_dao.modifierAction(action);
			
		    incident = incident_dao.getIncidentByID(2);
		    incident.setDateFin("2013-05-06 18:10:02");
		    incident_dao.modifierIncident(incident);
		    
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
