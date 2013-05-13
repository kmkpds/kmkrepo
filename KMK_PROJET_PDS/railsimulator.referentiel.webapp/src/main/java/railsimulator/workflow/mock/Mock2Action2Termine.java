package railsimulator.workflow.mock;

import java.util.TimerTask;

import beans.Action;
import beans.Incident;
import dao.ActionDAO;

public class Mock2Action2Termine extends TimerTask{

	Action action = new Action();	
	Incident incident = new Incident();
	private ActionDAO action_dao = new ActionDAO();
	
	public void run(){
		
		try{
			
		    action = action_dao.getActionByID(10);
		    action.setDateFin("2013-05-06 17:26:50");
		    action.setStatut("Termin�");
		    action_dao.modifierAction(action);
		    
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
