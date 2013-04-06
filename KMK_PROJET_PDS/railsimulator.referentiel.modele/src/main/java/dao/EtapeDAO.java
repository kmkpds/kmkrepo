package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import beans.Etape;


public class EtapeDAO {
	private Session se = null;
	private List<Etape> listeEtape;
	private Etape etape = new Etape();

	/**public void createEtape(Etape etape) {
		// TODO Auto-generated method stub
		 se = HibernateUtils.getSession();
	     Transaction t = se.beginTransaction();    
	     se.save(etape);
	     t.commit();
	     System.out.println("succé de création");
	     se.close();
	}
	*/
	public Etape getEtapeByID(int id) {
    	se = HibernateUtils.getSession();
    	se.beginTransaction();   	
      	etape = (Etape) se.createQuery("from Etape where idEtape="+id).uniqueResult();
    	System.out.println(etape.getNomEtape()+ "  "+etape.getIdEtape());
    	se.close();
        return etape;
    }

	@SuppressWarnings("unchecked")
	public List<Etape> listerEtape() {
    	se = HibernateUtils.getSession();
    	se.beginTransaction();  	 	
    	listeEtape = se.createQuery("from Etape").list();   	
    	return listeEtape;
    }

}
