package dao;
    
import org.hibernate.Transaction;   
import org.hibernate.Session;
import org.hibernate.Query;

import beans.Tourniquet;
public class TourniquetDAO {
	private Session se = null;
	private Tourniquet tourniquet ;
	
	public Tourniquet createTourniquetReturnTourniquet(Tourniquet tourn)
	 {
	se = HibernateUtils.getSession();
	Transaction t = se.beginTransaction();
	se.save(tourn);
	tourniquet = tourn;
	t.commit();
	se.close();

	return tourniquet;
}
	
	public Tourniquet getTourniquetById(int id) {
	
    	se = HibernateUtils.getSession();
    	
    	se.beginTransaction(); 
    	
    	tourniquet = (Tourniquet) se.createQuery("from Tourniquet where idtourniquet="+id).uniqueResult();
    	se.close();
    	
        return tourniquet;
    }


}
