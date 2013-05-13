package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Action;
import beans.Incident;
import beans.Procedure;

public class ProcedureDAO {
	
	private Session se = null;
	private List<Action> listeAction;
	private Procedure procedure;
	
	public List<Action> listerAction() {
    	se = HibernateUtils.getSession();
    	se.beginTransaction();  	 	
    	listeAction = se.createQuery("from Action").list();  
    	
        return listeAction;
    }
	
	public int createProcedureReturnId(String libelle){
		
		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction();
		Procedure procedure = new Procedure();
		procedure.setLibelleProcedure(libelle);
		procedure.setListeAction(listeAction);
		int idProcedure = (Integer) se.save(procedure);
        
        t.commit();
        se.close();

        return idProcedure;
		
	}
	
	public Procedure getProcedureByID(int id) {
    	se = HibernateUtils.getSession();
    	se.beginTransaction(); 
    	
    	procedure = (Procedure) se.createQuery("from Procedure where idProcedure="+id).uniqueResult();
    	 se.close();
    	
        return procedure;
    }
	
}
