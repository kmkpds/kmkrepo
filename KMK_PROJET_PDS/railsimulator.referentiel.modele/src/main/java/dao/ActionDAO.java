package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Action;
import beans.Incident;
import beans.Procedure;

public class ActionDAO {

	private Session se = null;
	private Action action;

	public  int  createActionReturnId(int id, String libelle, String datedebut, int mois, int annee, String datefin, String statut, Procedure procedure){
		
		se = HibernateUtils.getSession();
        Transaction t = se.beginTransaction();
        Action action = new Action();
        action.setIdActionIntervention(id);
        action.setLibelleActionIntervention(libelle);
		action.setDateDebut(datedebut); 
		action.setDateFin(datefin);
		action.setMois(mois);
		action.setAnnee(annee);
		action.setStatut(statut);
		action.setProcedureAction(procedure);
        
        int idAction = (Integer) se.save(action);
        
        t.commit();
        se.close();

        return idAction;
		
	}

	public Action getActionByID(int id) {
    	se = HibernateUtils.getSession();
    	se.beginTransaction(); 
    	
    	action = (Action) se.createQuery("from Action where idActionIntervention="+id).uniqueResult();
    	se.close();
    	
        return action;
    }
	
	public void modifierAction(Action action){
    	se = HibernateUtils.getSession();
    	Transaction t = se.beginTransaction();
    	se.update(action);
    	t.commit();
    	se.close();
    	
    }
	
	
}
