package railsimulator.workflow.mock;

import java.util.TimerTask;

import beans.Action;
import dao.ActionDAO;

public class Mock1Action4Termine extends TimerTask{

	Action action = new Action();	
	private ActionDAO action_dao = new ActionDAO();
	
	public void run(){
		
		try{
			
		    action = action_dao.getActionByID(4);
		    
		    action.setDateFin("2013-05-06 17:24:51");
		    action.setStatut("Terminé");
			
		    action_dao.modifierAction(action);
		    
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
