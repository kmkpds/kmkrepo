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

public class MockIncident1 extends TimerTask{

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
			//Transaction t = se.beginTransaction();
				
			//Cr�ation de la proc�dure pour ce typeincident
			int idProcedure;
			idProcedure = procedure_dao.createProcedureReturnId("Proc�dure A");
			procedure = procedure_dao.getProcedureByID(idProcedure);
		    se.flush();
		    
		    //Cr�ation des actions pour cette proc�dure
		    action_dao.createActionReturnId(1, "Arr�ter tous les trains", "2013-05-06 17:00:10", 5, 2013, null, "En cours", procedure);
		    action_dao.createActionReturnId(2, "D�clencher alarme du train", null, 0, 0, null, "En attente", procedure);
		    action_dao.createActionReturnId(3, "D�clencher alarme de station", null, 0, 0, null, "En attente", procedure);
		    action_dao.createActionReturnId(4, "Lancer un appel d'urgence", null, 0, 0, null, "En attente", procedure);
		    action_dao.createActionReturnId(5, "Informer su la perturbation du traffic", null, 0, 0, null, "En attente", procedure);
		    action_dao.createActionReturnId(6, "Message panneau 'informer perturbation'", null, 0, 0, null, "En attente", procedure);
		    action_dao.createActionReturnId(7, "Lancer un message sonore 'Incendie'", null, 0, 0, null, "En attente", procedure);
		    action_dao.createActionReturnId(8, "Evacuer les passagers du train", null, 0, 0, null, "En attente", procedure);
		    se.flush();
		    
	        //Cr�ation du 1er typeincident
		    int idtype = 20;//Accident voyageur
			typeIncident = typeincident_dao.getTypeIncidentByID(idtype);
		    se.flush();
		    
		    //Cr�ation de l'incident 1
		    incident_dao.createIncidentReturnId(1, "Incident d�clar� dans le train A", "2013-05-06 17:00:00", 5, 2013, null, "Moyen", procedure, typeIncident, null);
			se.flush();
			
		}//fin try
		catch (Exception e) {
			e.printStackTrace();		
		}//fin catch
	}//fin run

}//fin classe mock
