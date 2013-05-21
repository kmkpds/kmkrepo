import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateUtils;
import dao.OptimisationCheminDAO;
import beans.OptimisationChemin;
import beans.Reseau;
import junit.framework.TestCase;
import railsimulator.tools.Algo;

public class AlgoTest extends TestCase {

	private Algo algo = new Algo();
	private String reseauMatrice[][] = { {"1","2"},
			                             {"0","652"},
			                             {"652","0"},};
	private Session se = null;
	private double[] tabDistance = {0,652,0,652};
	private OptimisationCheminDAO optimisation_dao=new OptimisationCheminDAO();
	private int index;
	private Reseau reseau=new Reseau();	
	private  boolean test;

	public void testGetMatriceIncidence(){

		
		double [][] matriceIncidence = algo.getMatriceIncidence(reseauMatrice);

        if(matriceIncidence[0][0]==0 && matriceIncidence[0][1]==652 && matriceIncidence[1][0]==652 && matriceIncidence[1][1 ]==0   ){
        	
        	test = true;
		}
		else{
			test=false;

		}
        assertTrue(test);

	}
	
	public void testGetMatriceNomStation(){
		
		int nomStation[] =  algo.getMatriceNomStation(reseauMatrice);

        if(nomStation[0]==1 && nomStation[1]==2 ){
        	
        	test = true;
		}
		else{
			test=false;

		}



		assertTrue(test);

	}


    public void testGetIndexMinPoids() {
        index=algo.getIndexMinPoids(tabDistance); 
    	
        if(index==1 ){
        	
        	test = true;
		}
		else{
			test=false;

		}
        assertTrue(test);
       
    }
    
    public void testStationTostation(){ 	

    	
    }
    
    public void testdijkstra(){
    	List<OptimisationChemin> liste;
		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction(); 
		Query delete=se.createSQLQuery("delete from optimisationchemin");
		Query delete2=se.createSQLQuery("delete from station_has_station");
		Query delete3=se.createSQLQuery("delete from station");	
		Query delete4=se.createSQLQuery("delete from parametrehoraire");	
		Query delete5=se.createSQLQuery("delete from ligne");
		Query delete12=se.createSQLQuery("delete from reseau");	
		
		delete.executeUpdate();
		delete2.executeUpdate();
		delete3.executeUpdate();
		delete4.executeUpdate();
		delete5.executeUpdate();
		delete12.executeUpdate();
		t.commit();
		
		//création d'un reseau test
		Transaction t2 = se.beginTransaction();
		reseau.setNomReseau("reseau 1");
		se.save(reseau);		
	    t2.commit();
	    se.flush();
	    
    	algo.dijkstra(reseauMatrice,reseau);

    	liste=optimisation_dao.listerOptimisation();
    	
    	//if (reseauMatrice[1][0]=="0.0" && reseauMatrice[1][1]=="6373.886" && reseauMatrice[2][0]=="6373.886" && reseauMatrice[2][1]=="0.0"){
    	if(liste.isEmpty()){
    		test=false;
    	}
    	else{
    		test=true;
    	}
    	assertTrue(test);
    	
    	Transaction t5 = se.beginTransaction();
    	Query delete13=se.createSQLQuery("delete from optimisationchemin");
		Query delete7=se.createSQLQuery("delete from station_has_station");
		Query delete8=se.createSQLQuery("delete from station");	
		Query delete9=se.createSQLQuery("delete from parametrehoraire");	
		Query delete10=se.createSQLQuery("delete from ligne");	
		Query delete11=se.createSQLQuery("delete from reseau");	
		
		delete13.executeUpdate();
		delete7.executeUpdate();
		delete8.executeUpdate();
		delete9.executeUpdate();
		delete10.executeUpdate();
		delete11.executeUpdate();
		t5.commit();
		se.close();

    }

}
