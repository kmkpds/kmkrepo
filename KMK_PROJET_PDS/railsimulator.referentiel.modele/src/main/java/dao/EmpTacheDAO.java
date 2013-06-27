package dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import beans.EmpTache;

public class EmpTacheDAO {

	private Session se = null;	
	private EmpTache empTache;
	private List<EmpTache> listeEmpTache;
	
	
	public void createEmpToTache(EmpTache empTache){
    	se=HibernateUtils.getSession();
    	Transaction tr = se.beginTransaction();
    	
    	se.save(empTache);   
        tr.commit();
        se.close();
    	

    	
    }
	
	public EmpTache getEmptacheByID(int id) {

    	se = HibernateUtils.getSession();
    	se.beginTransaction();  	
    	empTache = (EmpTache) se.createQuery("from EmpTache where idemptache="+id).uniqueResult();
    	
    	 se.close();

    	  return empTache;
    }
	
	
	@SuppressWarnings("unchecked")
	public List<EmpTache> getEmpHasTache(int idemp) {

    	se = HibernateUtils.getSession();
    	se.beginTransaction();  	
    	listeEmpTache =  se.createQuery("from EmpTache where idemp="+idemp).list();
    
    	  return listeEmpTache;
    }
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<EmpTache> listerEmpTache() {
    	se = HibernateUtils.getSession();
    	se.beginTransaction();  	 	
    	listeEmpTache = se.createQuery("from EmpTache").list();  
    	
        return listeEmpTache;
    }
	

	
}

	
	
	
	

