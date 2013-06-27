package railsimulator.gestionperso;

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
import beans.FactHoraire;
import org.xml.sax.SAXException;
import communicationJMS.Consommateur;
import communicationJMS.Listener;
import communicationJMS.Producteur;
import communicationJMS.XmlTransformer;
import dao.FactHoraireDAO;


public class FactHoraireControler extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	static Producteur producteur = new Producteur();
	static XmlTransformer transforme = new XmlTransformer();
	private static FactHoraire facthoraire ;
	private static FactHoraireDAO fact_dao = new FactHoraireDAO();
	String confirmation ="";
	int cpt;
   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
    	
    	String action = request.getParameter("action");
    
    	if(action.equals("lancer")){
			String nomQueue = "Fact_Horaire" ;
			Consommateur consomme = new Consommateur("localhost", nomQueue);
			MessageListener listener = new Listener();
			consomme.lancer(listener);// lancer la lecture des messages		
			try {
					
			
					 cpt = envoyerPlusieursMessage();	
					 confirmation = +cpt+" messages envoyes : " ;
				}catch (JMSException e) {e.printStackTrace();} 
				catch (JAXBException e) {e.printStackTrace();}
				catch (SAXException e) {e.printStackTrace();}
			
			request.setAttribute("confirmation", confirmation);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/prodconsomDemo.jsp").forward( request, response );             	

		}   	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");

		if(action.equals("demo")){
			this.getServletContext().getRequestDispatcher( "/WEB-INF/prodconsomDemo.jsp").forward( request, response );		
		}
	}

	public static int envoyerPlusieursMessage() throws JMSException, JAXBException, SAXException{
		int cpt =0;
		String messageXML ;
		System.out.println("avant try ");
				try {
					//long start = System.currentTimeMillis();
				//	while( System.currentTimeMillis() < ( start + (1000 * +seconde))) {
					
					for(int i=2; i<6; i++){
						
						Integer idemp = null ;
						String dateh=null, heured = null, heuref=null; 
							
						if(i==5){
							
							idemp= 5;
							dateh ="2013-07-01";
							heured="04:48:00";
							heuref="12:00:00";
						}
						if(i==2){
							
							idemp= 2;
							dateh ="2013-07-01";
							heured="11:45:00";
							heuref="19:00:00";
						}
						if(i==3){
												
							idemp= 3;
							dateh ="2013-07-01";
							heured="18:47:30";
							heuref="01:30:00";
											}
						if(i==4){
							
							idemp= 4;
							dateh ="2013-07-01";
							heured="00:00:00";
							heuref="00:00:00";
						}
						System.out.println("avant object ");
											
						facthoraire =new FactHoraire(idemp,dateh,heured,heuref);
					   fact_dao.createFactHoraire(facthoraire);	
					   messageXML = transforme.transformeXML(facthoraire);
						  System.out.println(messageXML);
						  producteur.ecrireMessage(messageXML);		
						   cpt ++ ;
					
					}			
			 
			  } catch (NamingException e) {
			   e.printStackTrace();
			  }
		return cpt;		 
	}	
	
	}
