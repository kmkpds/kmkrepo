package railsimulator.abonnement;
 
import javax.jms.JMSException;    
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.hibernate.HibernateException;


import jms.abonnement.GenerateXml;
import jms.abonnement.Writer;
import railsimulator.tools.PassTourniquet;

import beans.AbonnementMany;
import dao.AbonnementManyDAO;
import dao.TourniquetDAO;
import beans.Tourniquet;

import xsd.model.*;











public class ListenerAbonnement implements MessageListener {


	//demmare process
	private GenerateXml decode = new GenerateXml();
	
	private	CheckAboAller retour = new CheckAboAller();
	private	AbonnementMany aboTest = new AbonnementMany();
	private	AbonnementManyDAO daoAbo = new AbonnementManyDAO();
	private	TourniquetDAO daoTour = new TourniquetDAO();
	private	Tourniquet tour = new Tourniquet();
	private	PassTourniquet verifPass= new PassTourniquet();
	private	CheckAboRetour  aboRetour = new CheckAboRetour();
	private	xsd.model.TourniquetJms xsdTour = new TourniquetJms();
	private	Writer writer = new Writer();
	private	String s;
	 public void onMessage(Message message) {
	    	
			
	        try {
	            if ((message instanceof TextMessage) ) {
	            	 TextMessage textMessage = (TextMessage)message;
	            	 String s = textMessage.getText();
	            	 retour = (CheckAboAller) decode.xmlToObject(s);
	            	System.out.println(retour.getClient().getIdClient());
	            	aboTest = daoAbo.getAbonnementManyByIdClient(retour.getClient().getIdClient());
	            	tour = daoTour.getTourniquetById(retour.getTourniquet().getIdTourniquet());
	            	System.out.println("id tour "+tour.getIdtourniquet());
	            	System.out.println("id client "+aboTest.getIdclient().getId());
	            	System.out.println("id tourniquet retour = "+(daoTour.getTourniquetById(retour.getTourniquet().getIdTourniquet())).getIdtourniquet());
	            	System.out.println("idtransac retour = "+retour.getIdtransaction());
	            	Boolean b = verifPass.PassTourniquet(daoTour.getTourniquetById(retour.getTourniquet().getIdTourniquet()), aboTest,retour.getIdtransaction());
	            	System.out.println("retour listner check abo tourniquet : "+ b);
	            	xsdTour.setIdTourniquet(tour.getIdtourniquet());
	            	xsdTour.setZone(tour.getZoneAbo().getNumZone());
	            	aboRetour.setTourniquet((TourniquetJms)xsdTour);
	            	aboRetour.setReturn(b);
	            	aboRetour.setIdtransaction(retour.getIdtransaction());
//	            	System.out.println(aboRetour.getTourniquet().getIdTourniquet());
	            	System.out.println("aboretour.bool = "+aboRetour.isReturn());
	            	writer.SendFile(aboRetour);
	        		
	                // System.out.println(retour.getClient().getIdClient());
	            	//System.out.println(" Message recu : " + msg.getText());
	            	
	                return;
	            }
	         
	         
	        }
	        catch (Throwable t) {
	            System.err.println((t instanceof JMSException) ? "JMSException" : "Throwable" + " Caught in onMessage(): " + t.getMessage());
	        }
	       
	    }
	
    //preparation process
   
}