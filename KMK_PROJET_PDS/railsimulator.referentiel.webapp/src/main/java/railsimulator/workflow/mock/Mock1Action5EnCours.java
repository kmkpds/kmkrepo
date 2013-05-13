package railsimulator.workflow.mock;

import java.util.TimerTask;

import beans.Action;
import dao.ActionDAO;

public class Mock1Action5EnCours extends TimerTask{

	Action action = new Action();	
	private ActionDAO action_dao = new ActionDAO();
	
	public void run(){
		
		try{
			
		    action = action_dao.getActionByID(5);
		    
		    action.setDateDebut("2013-05-06 17:25:13");
		    action.setStatut("En cours");
			
		    action_dao.modifierAction(action);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
