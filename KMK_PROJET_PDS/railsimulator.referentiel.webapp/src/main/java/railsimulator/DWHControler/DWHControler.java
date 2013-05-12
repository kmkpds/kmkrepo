package railsimulator.DWHControler;

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
import org.xml.sax.SAXException;
import beans.EventDWH;
import beans.FrequentationLigneDWH;
import beans.IncidentDWH;
import beans.RecetteDWH;
import communication.jms.dwh.ConsommateurDWH;
import communication.jms.dwh.ListenerDWH;
import communication.jms.dwh.ProducteurDWH;
import dao.EventDWHDAO;
import dao.FrequentationLigneDWHDAO;
import dao.IncidentDWHDAO;
import dao.RecetteDWHDAO;
import communication.jms.dwh.XmlTransformer;


public class DWHControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static FrequentationLigneDWH frequentation = new FrequentationLigneDWH();
	static FrequentationLigneDWHDAO dao_frequentation = new FrequentationLigneDWHDAO() ; 
	private static IncidentDWH incident = new IncidentDWH();
	static IncidentDWHDAO dao_incident = new IncidentDWHDAO() ;
	private static EventDWH event = new EventDWH();
	static EventDWHDAO dao_event = new EventDWHDAO();
	private static RecetteDWH recette = new RecetteDWH();
	static RecetteDWHDAO dao_recette = new RecetteDWHDAO(); 
	static ProducteurDWH producteur = new ProducteurDWH();
	static XmlTransformer transforme = new XmlTransformer();
	private 
	String confirmation ="";
	int cpt;
   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
    	
    	String action = request.getParameter("action");
    	//Lancement de Demo avec le bouton lancer
    	if(action.equals("lancer")){
    		//recupere le nombre de seconde choisi
			int  id = Integer.parseInt(request.getParameter("seconde"));
			//Recupere l'objet souhaite
			String object =request.getParameter("object");
			//nom de la file BI DWH
			String nomQueue = "BI_DWH" ;
			ConsommateurDWH consomme = new ConsommateurDWH("localhost", nomQueue);
			//lancer le listener
			MessageListener listener = new ListenerDWH();
			consomme.lancer(listener);// lancer la lecture des messages		
			
			try {
				//lancer la production des messages
				if(object.equals("frequentation") || object.equals("incident")|| object.equals("recette")
						|| object.equals("event")){
					//compteur de message creee 
					 cpt = envoyerPlusieursMessage(id,object);	
					 confirmation = +cpt+" messages envoyes : " ;
				}
				else 
					//message d'erreur
				confirmation = "frequentation ou incident ou event ou recette: "+cpt+" (message envoye)";
			} catch (JMSException e) {e.printStackTrace();} 
				catch (JAXBException e) {e.printStackTrace();}
				catch (SAXException e) {e.printStackTrace();}
			
			request.setAttribute("confirmation", confirmation);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/prodconsomDemo.jsp").forward( request, response );             	

		}   	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		// affichage de la page demo
		if(action.equals("demo")){
			this.getServletContext().getRequestDispatcher( "/WEB-INF/prodconsomDemo.jsp").forward( request, response );		
		}
	}
	//Envoyer les messages par timer
	public static int envoyerPlusieursMessage(int seconde,String object) throws JMSException, JAXBException, SAXException{
		int cpt =0;
		String messageXML ;
				try {
					long start = System.currentTimeMillis();
					// timer
					while( System.currentTimeMillis() < ( start + (1000 * +seconde))) {
						//si c est frequentation
						if(object.equals("frequentation")){		
							//genere l objet en  random
						  frequentation = getObjectFrequentation();						 	
						  	dao_frequentation.insertFrequentation(frequentation);						 	
						 	messageXML = transforme.transformeXML(frequentation);
						 	producteur.ecrireMessage(messageXML);		
						   cpt ++ ;
						}	
						//si c est incident
						if(object.equals("incident")){
							//genere l objet en  random
							incident = getObjectIncident();
							dao_incident.insertIncident(incident);
							messageXML = transforme.transformeXML(incident);
						 	producteur.ecrireMessage(messageXML);			
						   cpt ++ ;
						}	
						//si c esr event
						if(object.equals("event")){
							//genere l objet en  random
							event = getObjectEvent();
							dao_event.insertEvent(event);
							messageXML = transforme.transformeXML(event);
						 	producteur.ecrireMessage(messageXML);			
						   cpt ++ ;
						}
						//si c est recette
						if(object.equals("recette")){
							//genere l objet en  random
							recette = getObjectRecette();
							dao_recette.insertRecette(recette);
							messageXML = transforme.transformeXML(recette);
						 	producteur.ecrireMessage(messageXML);			
						   cpt ++ ;
						}
					}			
			 
			  } catch (NamingException e) {
			   e.printStackTrace();
			  }
		return cpt;		 
	}	
	// generer une chaine de caractere de longueur 10
	public static String generateString() {
		     Random rd = new SecureRandom();		     
		     String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789";
		     String pw = "";
	     for (int i=0; i<10; i++)
	     {
	         int index = (int)(rd.nextDouble()*letters.length());
	         pw += letters.substring(index, index+1);
	     }
	     return pw;
	 }
	// generer un entier de longueur 6
	public static Integer generateInt() {
		     Random rd = new SecureRandom();  
		     String letters = "0123456789";
		     String pw = "";
	     for (int i=0; i<6; i++)
	     {
	         int index = (int)(rd.nextDouble()*letters.length());
	         pw += letters.substring(index, index+1);
	     }
	     int u =Integer.parseInt(pw);
	     return u;
	 }
	// generer un entier de longueur 6
	public static int generateID() {
		     Random rd = new SecureRandom();  
		     String letters = "0123456789";
		     String pw = "";
	     for (int i=0; i<6; i++)
	     {
	         int index = (int)(rd.nextDouble()*letters.length());
	         pw += letters.substring(index, index+1);
	     }
	     int u =Integer.parseInt(pw);
	     return u;
	 }
	// generer un entier de longueur 1
	public static Integer generateJour() {
		     Random rd = new SecureRandom();	    
		     String letters = "123456789";
		     String pw = "";
	    for (int i=0; i<1; i++)
	    {
	        int index = (int)(rd.nextDouble()*letters.length());
	        pw += letters.substring(index, index+1);
	    }
	    int u =Integer.parseInt(pw);
	    return u;
	}
	// generer une date
	public static String generateDate() {
			Random r =new Random();
			long unixtime=(long) (1293861599+r.nextDouble()*60*60*24*365);
			Date d = new Date(unixtime);
			return d.toString();
	}
	//generer une criticite (Urgent ou Critique ou Basse)
	public static String genereCriticite(){
		String crt="";
		Random r = new Random();
		  String letters = "123";
		    String pw = "";
		    for (int i=0; i<1; i++)
		    {
		        int index = (int)(r.nextDouble()*letters.length());
		        pw += letters.substring(index, index+1);
		    }
		    if(pw.equals("1"))  crt ="Urgent" ; 
		    if(pw.equals("2"))  crt ="Criticque" ; 
		    if(pw.equals("3")) crt= "Basse" ; 
		  return crt;
	}
	// genere un objet de type frequentation aleatoire
	public static FrequentationLigneDWH getObjectFrequentation() throws JAXBException, SAXException{
			Integer frequentation, frequentationEtudiant,frequentationSalarie,frequentationRetraite
			,frequentationAutre;
			int idFrequentationLigne, jour,mois, annee,idligne;
			String date ;
			
				FrequentationLigneDWH freq ;
				idFrequentationLigne=generateID();
				frequentation =generateInt();
				frequentationEtudiant=generateInt();
				frequentationSalarie=generateInt();
				frequentationRetraite=generateInt();
				frequentationAutre=generateInt();
				mois=generateJour();
				annee=2012;
				jour =generateJour();
				date=jour+"-0"+mois+"-"+annee;
				idligne=generateJour();	
			
			 freq=new FrequentationLigneDWH(idFrequentationLigne, frequentation, frequentationEtudiant, frequentationSalarie, 
					 						frequentationRetraite, frequentationAutre, 
					 						date, mois, annee, idligne);			
		 return freq;
		}
	// genere un objet de type incident aleatoire
		public static IncidentDWH getObjectIncident() throws JAXBException, SAXException{
			int idIncident, jour, mois,  annee,  id_procedureintervention,  id_type;
			String description, dateDebut, dateFin, criticite;		
			IncidentDWH inc ;
			idIncident =generateID();
			description =generateString();
			mois=generateJour();
			annee=2012;
			jour =generateJour();
			dateDebut=jour+"-0"+mois+"-"+annee;
			dateFin=jour+1+"-0"+mois+"-"+annee;
			id_procedureintervention=generateJour();
			id_type=generateJour();
			criticite =genereCriticite();
			inc= new IncidentDWH(idIncident,description, dateDebut,  mois,  annee,
					 dateFin,  criticite,  id_procedureintervention,  id_type);

		  return inc;
		}
		// genere un objet de type Event aleatoire
		public static EventDWH getObjectEvent() throws JAXBException, SAXException{
			int idEvent, type_idtype,jour,mois,annee;
			String  date, criticite;
			EventDWH ev;
			idEvent =generateID();	
			mois= generateJour();
			annee= 2012;
			jour =generateJour();
			date=jour+"-0"+mois+"-"+annee;
			criticite =genereCriticite();
			type_idtype =generateJour();
			ev = new EventDWH(idEvent, date, criticite, type_idtype);

		  return ev;
		}
		// genere un objet de type Recette aleatoire
		public static RecetteDWH getObjectRecette() throws JAXBException, SAXException{
			int idRecette, id_ligne,jour, mois,  annee;
			float ca ,cout, coutFixe, coutVariable;
			String date;			
			RecetteDWH rec;
			idRecette =generateID();	
			mois= generateJour();
			annee= 2012;
			jour =generateJour();
			date=jour+"-0"+mois+"-"+annee;
			ca=generateInt();
			coutFixe=generateInt();
			coutVariable=generateInt();
			cout =coutFixe + coutVariable;
			id_ligne =generateJour();
			rec = new RecetteDWH(idRecette,ca,cout, coutFixe, coutVariable,date, mois, annee,id_ligne);

		  return rec;
		}

}
