
import junit.framework.TestCase;
import railsimulator.tools.Algo;

public class AlgoTest extends TestCase {

	private Algo algo = new Algo();
	private String reseauMatrice[][] = { {"1","2"},
			                             {"0","652"},
			                             {"652","0"},};
	
	private int[] tabDistance = {0,652,0,652};
	
	private int index;
	
	
	private  boolean test;

	public void testGetMatriceIncidence(){

		
		int [][] matriceIncidence = algo.getMatriceIncidence(reseauMatrice);

        if(matriceIncidence[0][0]==0 && matriceIncidence[0][1]==652 && matriceIncidence[1][0]==652 && matriceIncidence[1][1 ]==0   ){
        	
        	test = true;
		}
		else{
			test=false;

		}
		

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
       
    }

}
