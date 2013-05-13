package railsimulator.workflow.mock;

import java.util.TimerTask;

import beans.Action;
import beans.Incident;
import dao.ActionDAO;

public class Mock1Action3Termine extends TimerTask{

	Action action = new Action();	
	Incident incident = new Incident();
	private ActionDAO action_dao = new ActionDAO();
	
	public void run(){
		
		try{
			
		    action = action_dao.getActionByID(3);
		    action.setDateFin("2013-05-06 17:23:04");
		    action.setStatut("Terminé");
		    action_dao.modifierAction(action);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
