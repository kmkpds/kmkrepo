package railsimulator.workflow.mock;

import java.util.TimerTask;

import beans.Action;
import dao.ActionDAO;

public class Mock1Action8EnCours extends TimerTask{

	Action action = new Action();	
	private ActionDAO action_dao = new ActionDAO();
	
	public void run(){
		
		try{
			
		    action = action_dao.getActionByID(8);
		    
		    action.setDateDebut("2013-05-06 17:31:04");
		    action.setStatut("En cours");
			
		    action_dao.modifierAction(action);
		    
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
