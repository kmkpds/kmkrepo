package railsimulator.transmissionMSG.controleur;

import java.io.IOException;
import java.util.List;
import javax.jms.JMSException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import org.xml.sax.SAXException;
import transmissionTerrain.EnvoiMSGTerrain;
import beans.AnnonceVoyageur;
import beans.Etape;
import dao.AnnonceVoyageurDAO;
import dao.EtapeDAO;

//Servlet implementation class TransmissionMessageTerrainControler
 //Controleur : lancement de procedure, affichage de la liste des étapes à exécuter
 // controle criticité de 1 à 5 , envoie de message à activemq, renvoie une confirmation détaillée

public class TransmissionMessageTerrainControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	List<Etape> listeEtape;
	private EtapeDAO etape_dao = new EtapeDAO();
	private Etape etape = new Etape();
	private EnvoiMSGTerrain message = new EnvoiMSGTerrain();
	private AnnonceVoyageur annonceVoyageur = new AnnonceVoyageur();
    String criticite, commentaire ,
    			train,train2,train3,train4 ,
    			ligne , ligne2, ligne3, ligne4
    			,station , station2, station3, station4 ,
    			wagon, wagon2, wagon3, wagon4;
    Integer criticiteInt ;
    String confirmation = "Le message envoyé est le suivant : ";// Je prepare le message de confirmation 
    String libelleAnnonce ;

   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
    	
    	String action = request.getParameter("action");
    	// Form valider		
		if(action.equals("Valider")){
	            
	            criticite= request.getParameter("criticite");
						//convertir la criticité de string en int 
							criticiteInt = Integer.parseInt(criticite); 
		        commentaire = request.getParameter("commentaire");	
		        train = request.getParameter("train");
		        train2 = request.getParameter("train2");
		        train3 = request.getParameter("train3");
		        train4 = request.getParameter("train4");
		    
		        ligne = request.getParameter("ligne");
		        ligne2 = request.getParameter("ligne2");
		        ligne3 = request.getParameter("ligne3");
		        ligne4 = request.getParameter("ligne4");
		        
		        station = request.getParameter("station");
		        station2 = request.getParameter("station2");
		        station3 = request.getParameter("station3");
		        station4 = request.getParameter("station4");

		        wagon = request.getParameter("wagon");
		        wagon2 = request.getParameter("wagon2");
		        wagon3 = request.getParameter("wagon3");
		        wagon4 = request.getParameter("wagon4");
		        
		   		   //si tout fonctionne bien alors le traite 
		      //  if (erreurGenerale == false) {
		        	//setter l'annonce
		        	annonceVoyageur.setTypeEvenement("malaise Voyageur");
		        	annonceVoyageur.setLibelleAnnonce("malaise voyageur");
		        	annonceVoyageur.setCriticite(criticiteInt);
		        	annonceVoyageur.setCommentaire(commentaire);
		        	annonceVoyageur.setTrain(train);
		        	annonceVoyageur.setLigne(ligne);
		        	annonceVoyageur.setStation(station);
		        	annonceVoyageur.setWagon(wagon);
		        	
		        		try {
							//l'envoi de message sur queue JMS
							message.envoyerMessageAnnonceToESB(annonceVoyageur);
							//message.envoyerMessage(bb);
							} catch (JMSException e){e.printStackTrace();}
		        				catch (JAXBException e) {e.printStackTrace();}
		        					catch (SAXException e) {e.printStackTrace();
						}
		        		
		        	request.logout();
		        	request.setAttribute("libelleAnnonce",libelleAnnonce);
		        	request.setAttribute("criticite",criticite);	   
		        	request.setAttribute("commentaire",commentaire);
		        	request.setAttribute("train",train);
		        	request.setAttribute("train2",train2);
		        	request.setAttribute("train3",train3);
		        	request.setAttribute("train4",train4);
		        	request.setAttribute("ligne",ligne);
		        	request.setAttribute("ligne2",ligne2);
		        	request.setAttribute("ligne3",ligne3);
		        	request.setAttribute("ligne4",ligne4);
		        	request.setAttribute("station",station);
		        	request.setAttribute("station2",station2);
		        	request.setAttribute("station3",station3);
		        	request.setAttribute("station4",station4);
		        	request.setAttribute("wagon",wagon);
		        	request.setAttribute("wagon2",wagon2);
		        	request.setAttribute("wagon3",wagon3);
		        	request.setAttribute("wagon4",wagon4);
		        	request.setAttribute("confirmation", confirmation);
		        	
					this.getServletContext().getRequestDispatcher( "/WEB-INF/confirmationEnvoi.jsp").forward( request, response );	        	

		}   	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");

		//lancement des étapes
		if(action.equals("etape")){
			listeEtape = etape_dao.listerEtape();
			request.logout();	
			//affichage de toutes les étapes
			afficherRefEtape(request, response);			
		}
		//lister les étapes quand l'utilisateur fait sont choix
		if(action.equals("excecute")){
			int  id = Integer.parseInt(request.getParameter("id"));
			etape = etape_dao.getEtapeByID(id);
			//request.logout();
			request.setAttribute("etape",etape);
			AnnonceVoyageurDAO dao = new AnnonceVoyageurDAO();
			libelleAnnonce = dao.getAnnonceVoyageurByID(id);
			request.logout();
			request.setAttribute("libelleAnnonce",libelleAnnonce);
			request.setAttribute("typeEvenement","malaise Voyageur");
			this.getServletContext().getRequestDispatcher( "/WEB-INF/formExecuteEtape.jsp").forward( request, response );
		}
	}

		//affichage des étapes dans la page listeEtape.jsp
	public void afficherRefEtape( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		listeEtape = etape_dao.listerEtape();
		request.logout();
		request.setAttribute("listeEtape",listeEtape);
		this.getServletContext().getRequestDispatcher( "/WEB-INF/listeEtape.jsp").forward( request, response );
				
	}
}
