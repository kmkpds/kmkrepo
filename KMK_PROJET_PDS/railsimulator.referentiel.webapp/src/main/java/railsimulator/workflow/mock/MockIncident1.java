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
				
			//Création de la procédure pour ce typeincident
			int idProcedure;
			idProcedure = procedure_dao.createProcedureReturnId("Procédure A");
			procedure = procedure_dao.getProcedureByID(idProcedure);
		    se.flush();
		    
		    //Création des actions pour cette procédure
		    action_dao.createActionReturnId(1, "Arrêter tous les trains", "2013-05-06 17:00:10", 5, 2013, null, "En cours", procedure);
		    action_dao.createActionReturnId(2, "Déclencher alarme du train", null, 0, 0, null, "En attente", procedure);
		    action_dao.createActionReturnId(3, "Déclencher alarme de station", null, 0, 0, null, "En attente", procedure);
		    action_dao.createActionReturnId(4, "Lancer un appel d'urgence", null, 0, 0, null, "En attente", procedure);
		    action_dao.createActionReturnId(5, "Informer su la perturbation du traffic", null, 0, 0, null, "En attente", procedure);
		    action_dao.createActionReturnId(6, "Message panneau 'informer perturbation'", null, 0, 0, null, "En attente", procedure);
		    action_dao.createActionReturnId(7, "Lancer un message sonore 'Incendie'", null, 0, 0, null, "En attente", procedure);
		    action_dao.createActionReturnId(8, "Evacuer les passagers du train", null, 0, 0, null, "En attente", procedure);
		    se.flush();
		    
	        //Création du 1er typeincident
		    int idtype = 20;//Accident voyageur
			typeIncident = typeincident_dao.getTypeIncidentByID(idtype);
		    se.flush();
		    
		    //Création de l'incident 1
		    incident_dao.createIncidentReturnId(1, "Incident déclaré dans le train A", "2013-05-06 17:00:00", 5, 2013, null, "Moyen", procedure, typeIncident, null);
			se.flush();
			
		}//fin try
		catch (Exception e) {
			e.printStackTrace();		
		}//fin catch
	}//fin run

}//fin classe mock
