 

import junit.framework.TestCase;  
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import railsimulator.tools.PassTourniquet;
import xsd.model.*;

import jms.abonnement.GenerateXml;
import dao.AbonnementManyDAO;
import dao.TourniquetDAO;

public class PassTourniquetTest extends TestCase {


	public PassTourniquetTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	@Test
	public void testVerifCheckAboRetour(){
		
		CheckAboRetour ch= new CheckAboRetour();
		String s = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><CheckAboRetour return=\"false\" idtransaction=\"1\" xmlns=\"http://www.example.org/AbonnementXSD\"><tourniquet idTourniquet=\"189\" zone=\"2\"/></CheckAboRetour>";
		
		ch.setReturn(false);
		ch.setIdtransaction(1);
		TourniquetJms tour = new TourniquetJms();
		
		tour.setIdTourniquet(189);
		tour.setZone(2);
		ch.setTourniquet(tour);
		
		GenerateXml xml = new GenerateXml();
		
		String s1 = xml.genererFile(ch);
		
		System.out.println(s1);
		Assert.assertEquals(xml.genererFile(ch),s);
		Assert.assertNotNull(s1);
		
	}
	
	@Test
	public void testBeanJaxb(){
		
		CheckAboRetour ch= new CheckAboRetour();
		String s = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><CheckAboRetour return=\"false\" idtransaction=\"1\" xmlns=\"http://www.example.org/AbonnementXSD\"><tourniquet zone=\"2\" idTourniquet=\"189\"/></CheckAboRetour>";
		ch.setReturn(false);
		ch.setIdtransaction(1);
		
		TourniquetJms tour = new TourniquetJms();
		tour.setIdTourniquet(189);
		tour.setZone(2);
		ch.setTourniquet(tour);
		
		GenerateXml xml = new GenerateXml();
		
		 String s1 = xml.genererFile(ch);
		 
		 CheckAboRetour ch2 = new CheckAboRetour();
		 ch2 = (CheckAboRetour) xml.xmlToObject(s1);
		 System.out.println(ch2.getIdtransaction());
		
		System.out.println(s1);
		Assert.assertEquals((int)ch2.getIdtransaction(),(int)1);
		Assert.assertNotNull(s1);
		
	}
	
	
	@Test
	public void testRetourPassTourniquet(){
		AbonnementManyDAO daoabo = new AbonnementManyDAO();
		PassTourniquet passok = new PassTourniquet();
		PassTourniquet passko = new PassTourniquet();
		
		TourniquetDAO daotour = new TourniquetDAO();
		
		Boolean checkfalse = passok.PassTourniquet(daotour.getTourniquetById(188), daoabo.getAbonnementManyById(141), 9990);
		Boolean checktrue = passko.PassTourniquet(daotour.getTourniquetById(189), daoabo.getAbonnementManyById(143), 9991);
		
		
		
		Assert.assertFalse(checkfalse);
		Assert.assertTrue(checktrue);
		
		
	}
	

}
