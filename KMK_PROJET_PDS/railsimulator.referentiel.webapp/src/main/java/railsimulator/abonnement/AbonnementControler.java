package railsimulator.abonnement;

import java.io.IOException;         
import java.security.SecureRandom;
import java.sql.Date;
import java.util.Random;
import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import beans.Tourniquet;
import dao.TourniquetDAO;
import jms.abonnement.ConsommateurAbonnement;

import beans.AbonnementMany;
import dao.AbonnementManyDAO;

import beans.Client;

import dao.ClientDAO;
import dao.ZoneAboDAO;
import beans.ZoneAbo;


import railsimulator.abonnement.ListenerAbonnement;



import org.xml.sax.SAXException;    
 


public class AbonnementControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private ListenerAbonnement listener = new ListenerAbonnement();
	private AbonnementManyDAO daoabo = new AbonnementManyDAO();
	private AbonnementMany abo = new AbonnementMany();
	private ClientDAO daoC = new ClientDAO();
	private Client client = new Client();
	private TourniquetDAO daotour = new TourniquetDAO();
	private Tourniquet tour = new Tourniquet();
   private ZoneAbo zone = new ZoneAbo();
   private ZoneAboDAO daoz = new ZoneAboDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
    	
//    		System.out.println("listener post");
//			String nomQueue = "AbonnementAller" ;
//			ConsommateurAbonnement consomme = new ConsommateurAbonnement("localhost", nomQueue);
//			MessageListener listener = new ListenerAbonnement();
//			consomme.lancer(listener);// lancer la lecture des messages		
		
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		
	}
	public void init() throws ServletException
    {
		super.init();
		String nomQueue = "AbonnementAller" ;
		ConsommateurAbonnement consomme = new ConsommateurAbonnement(nomQueue);
		MessageListener listener = new ListenerAbonnement();
		consomme.lancer(listener);// lancer la lecture des messages		
		System.out.println("listner get init ");
		
		
    }
    }



