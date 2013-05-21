
import junit.framework.TestCase; 
import railsimulator.tools.AlgoCreationHoraire;

public class AlgoCreationHoraireTest extends TestCase {
	
	private AlgoCreationHoraire algo = new AlgoCreationHoraire();
	private boolean test;
	
	public void testAjoutTemps(){
		String h = algo.ajoutTemps("01:00:00", 191.56945481494304, "00:02:00");
		System.out.println(h);
	
	
	if(h.equals("01:05:12")){
		test=true;
	}
	else{
		test=false;
	}
	assertTrue(test);
	}
	
}
