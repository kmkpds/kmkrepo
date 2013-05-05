package railsimulator.bi;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BiFrequentationLigne;
import beans.BiLigne;
import beans.BiRecette;

import dao.BiFrequentationLigneDAO;
import dao.BiIncidentDAO;
import dao.BiLigneDAO;
import dao.BiRecetteDAO;


public class BiControler extends HttpServlet{


	private BiLigneDAO ligne_dao = new BiLigneDAO();
	private BiFrequentationLigneDAO frequentation_dao = new BiFrequentationLigneDAO();
	private BiRecetteDAO recette_dao = new BiRecetteDAO();
	private BiIncidentDAO incident_dao = new BiIncidentDAO();

	private BiLigne biLigne = new BiLigne();
	private List<BiLigne> listeLigne;
	private List<String> listeAnne;
	private List<BiFrequentationLigne> freqListe;
	private List<BiRecette> recetteListe;


	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{


		String action = request.getParameter("action");


		// Section Frequence
		if(action.equals("freqligne")){
			listeLigne = ligne_dao.listerLigne();
			request.logout();
			request.setAttribute("listeLigne",listeLigne);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/selectFrequentationLigne.jsp").forward( request, response );
		}

		if(action.equals("freqUsager")){

			listeLigne = ligne_dao.listerLigne();
			listeAnne = frequentation_dao.getFrequentationAnnee();
			request.logout();
			request.setAttribute("listeLigne",listeLigne);
			request.setAttribute("listeAnne",listeAnne);

			this.getServletContext().getRequestDispatcher( "/WEB-INF/selectFrequentationUsager.jsp").forward( request, response );
		}

		if(action.equals("freqUsager2")){

			listeLigne = ligne_dao.listerLigne();
			listeAnne = frequentation_dao.getFrequentationAnnee();
			request.logout();
			request.setAttribute("listeLigne",listeLigne);
			request.setAttribute("listeAnne",listeAnne);

			this.getServletContext().getRequestDispatcher( "/WEB-INF/selectFrequentationUsager2.jsp").forward( request, response );
		}
		// Fin Section Frequence


		// Section CA
		if(action.equals("evoCA")){
			listeLigne = ligne_dao.listerLigne();
			request.logout();
			request.setAttribute("listeLigne",listeLigne);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/selectCaLigne.jsp").forward( request, response );
		}

		if(action.equals("repartitionCout")){
			listeLigne = ligne_dao.listerLigne();
			listeAnne = recette_dao.getCaAnnee();
			request.logout();
			request.setAttribute("listeLigne",listeLigne);
			request.setAttribute("listeAnne",listeAnne);

			this.getServletContext().getRequestDispatcher( "/WEB-INF/selectRepartitionCout.jsp").forward( request, response );
		}
		// Fin Section CA

		// Section Incident

		if(action.equals("nbIncident")){
			listeLigne = ligne_dao.listerLigne();
			listeAnne = incident_dao.getIncidentAnnee();
			request.logout();
			request.setAttribute("listeLigne",listeLigne);
			request.setAttribute("listeAnne",listeAnne);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/selectNombreIncident.jsp").forward( request, response );
		}

		if(action.equals("tempsIncident")){

			listeLigne = ligne_dao.listerLigne();
			listeAnne = incident_dao.getIncidentAnnee();
			request.logout();
			request.setAttribute("listeLigne",listeLigne);
			request.setAttribute("listeAnne",listeAnne);			
			this.getServletContext().getRequestDispatcher( "/WEB-INF/selectTempsIncident.jsp").forward( request, response );

		}
		// Fin Section Incident

	}










	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");





		// Thème Fréquence


		// Graphique nombre d'usager par ligne
		if(action.equals("Valider Ligne")){

			int  id = Integer.parseInt(request.getParameter("idLigne"));	
			freqListe = (List<BiFrequentationLigne>) frequentation_dao.getFrequentationByLigne(id);		
			request.logout();
			request.setAttribute("freqListe",freqListe);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/BIfrequentationLigne.jsp").forward( request, response );

		}
		// FIN Graphique nombre d'usager par ligne



		// Histogramme Répartition  d'usager par ligne  Mensuelle ou Trimestrielle
		if(action.equals("Valider Ligne Usager")){

			int  id = Integer.parseInt(request.getParameter("idLigne"));	
			int  annee = Integer.parseInt(request.getParameter("date"));
			String affichage = request.getParameter("affichage");
			List<BigDecimal> listeFreqEtudiant = new ArrayList<BigDecimal>();
			List<BigDecimal> listeFreqSalarie = new ArrayList<BigDecimal>();
			List<BigDecimal> listeFreqRetraite = new ArrayList<BigDecimal>();
			List<BigDecimal> listeFreqAutre = new ArrayList<BigDecimal>();

			if(affichage.equals("mensuelle")){
				for (int i=1; i<13; i++) {



					listeFreqEtudiant.add(frequentation_dao.getFrequentationEtudiant(id,annee,i));
					listeFreqSalarie.add(frequentation_dao.getFrequentationSalarie(id,annee,i));	
					listeFreqRetraite.add(frequentation_dao.getFrequentationRetraite(id,annee,i));	
					listeFreqAutre.add(frequentation_dao.getFrequentationAutre(id,annee,i));	



				}
				request.logout();

				request.setAttribute("freqEtudiant",listeFreqEtudiant);
				request.setAttribute("freqSalarie",listeFreqSalarie);
				request.setAttribute("freqRetraite",listeFreqRetraite);
				request.setAttribute("freqAutre",listeFreqAutre);
				this.getServletContext().getRequestDispatcher( "/WEB-INF/BIfrequentationUsagerMensuelle.jsp").forward( request, response );
			}

			else if(affichage.equals("trimestrielle")){
				for (int i=1; i<5; i++) {



					listeFreqEtudiant.add(frequentation_dao.getFrequentationEtudiantTrimestrielle(id,annee,i));
					listeFreqSalarie.add(frequentation_dao.getFrequentationSalarieTrimestrielle(id,annee,i));	
					listeFreqRetraite.add(frequentation_dao.getFrequentationRetraiteTrimestrielle(id,annee,i));	
					listeFreqAutre.add(frequentation_dao.getFrequentationAutreTrimestrielle(id,annee,i));	



				}
				request.logout();

				request.setAttribute("freqEtudiant",listeFreqEtudiant);
				request.setAttribute("freqSalarie",listeFreqSalarie);
				request.setAttribute("freqRetraite",listeFreqRetraite);
				request.setAttribute("freqAutre",listeFreqAutre);
				this.getServletContext().getRequestDispatcher( "/WEB-INF/BIfrequentationUsagerTrimestrielle.jsp").forward( request, response );

			}
			else{

				for (int i=1; i<3; i++) {



					listeFreqEtudiant.add(frequentation_dao.getFrequentationEtudiantSemestrielle(id,annee,i));
					listeFreqSalarie.add(frequentation_dao.getFrequentationSalarieSemestrielle(id,annee,i));	
					listeFreqRetraite.add(frequentation_dao.getFrequentationRetraiteSemestrielle(id,annee,i));	
					listeFreqAutre.add(frequentation_dao.getFrequentationAutreSemestrielle(id,annee,i));	



				}
				request.logout();

				request.setAttribute("freqEtudiant",listeFreqEtudiant);
				request.setAttribute("freqSalarie",listeFreqSalarie);
				request.setAttribute("freqRetraite",listeFreqRetraite);
				request.setAttribute("freqAutre",listeFreqAutre);
				this.getServletContext().getRequestDispatcher( "/WEB-INF/BIfrequentationUsagerSemestrielle.jsp").forward( request, response );

			}
		}
		// FIN Histogramme Répartition d'usager par ligne  Mensuelle ou Trimestrielle

		// Cammembert Taux d'usager par ligne et par type Mensuelle 
		if(action.equals("Valider Ligne Usager 2")){

			int  id = Integer.parseInt(request.getParameter("idLigne"));	
			int  annee = Integer.parseInt(request.getParameter("date"));
			int  mois = Integer.parseInt(request.getParameter("mois"));



			float tauxEtudiant = frequentation_dao.getTauxEtudiant(id,annee,mois).floatValue()*100;
			float tauxSalarie = frequentation_dao.getTauxSalarie(id,annee,mois).floatValue()*100;
			float tauxRetraite = frequentation_dao.getTauxRetraite(id,annee,mois).floatValue()*100;
			float tauxAutre = frequentation_dao.getTauxAutre(id,annee,mois).floatValue()*100;

			request.logout();
			request.setAttribute("tauxEtudiant",tauxEtudiant);
			request.setAttribute("tauxSalarie",tauxSalarie);
			request.setAttribute("tauxRetraite",tauxRetraite);
			request.setAttribute("tauxAutre",tauxAutre);


			this.getServletContext().getRequestDispatcher( "/WEB-INF/BIfrequentationUsager2.jsp").forward( request, response );
		}
		// FIN Cammembert Taux d'usager par ligne et par type Mensuelle 


		// Fin Thème Fréquence


		//  Thème Recette

		// Graphique Ca && Cout par ligne
		if(action.equals("Valider Ca Ligne")){

			int  id = Integer.parseInt(request.getParameter("idLigne"));

			recetteListe = (List<BiRecette>) recette_dao.getCaByLigne(id);
			System.out.println(recetteListe.get(0).getCa());
			request.logout();
			request.setAttribute("recetteListe",recetteListe);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/BIcaLigne.jsp").forward( request, response );
		}
		// FIN Graphique Ca && Cout par ligne


		// Histogramme repartiton Cout fixe / Cout variable
		if(action.equals("Valider Repartition Cout")){

			int  id = Integer.parseInt(request.getParameter("idLigne"));	
			int  annee = Integer.parseInt(request.getParameter("date"));
			String affichage = request.getParameter("affichage");

			List<Double> listeCoutFixe = new ArrayList<Double>();
			List<Double> listeCoutVariable = new ArrayList<Double>();

			if(affichage.equals("mensuelle")){
				for (int i=1; i<13; i++) {

					listeCoutFixe.add(recette_dao.getCoutFixe(id,annee,i));
					listeCoutVariable.add(recette_dao.getCoutVariable(id,annee,i));	           

				}
				request.logout();

				request.setAttribute("coutFixe",listeCoutFixe);
				request.setAttribute("coutVariable",listeCoutVariable);

				this.getServletContext().getRequestDispatcher( "/WEB-INF/BIrepartitionCoutMensuelle.jsp").forward( request, response );
			}
			else if(affichage.equals("trimestrielle")){
				for (int i=1; i<5; i++) {

					listeCoutFixe.add(recette_dao.getCoutFixeTrimestrielle(id,annee,i));
					listeCoutVariable.add(recette_dao.getCoutVariableTrimestrielle(id,annee,i));	           

				}
				request.logout();

				request.setAttribute("coutFixe",listeCoutFixe);
				request.setAttribute("coutVariable",listeCoutVariable);

				this.getServletContext().getRequestDispatcher( "/WEB-INF/BIrepartitionCoutTrimestrielle.jsp").forward( request, response );

			}
			else{
				for (int i=1; i<3; i++) {

					listeCoutFixe.add(recette_dao.getCoutFixeSemestrielle(id,annee,i));
					listeCoutVariable.add(recette_dao.getCoutVariableSemestrielle(id,annee,i));	           

				}
				request.logout();

				request.setAttribute("coutFixe",listeCoutFixe);
				request.setAttribute("coutVariable",listeCoutVariable);

				this.getServletContext().getRequestDispatcher( "/WEB-INF/BIrepartitionCoutSemestrielle.jsp").forward( request, response );

			}
		}

		// Fin Histogramme repartiton Cout fixe / Cout variable
		// FIN Thème Recette


		//Thème incident


		if(action.equals("Valider nombre incident")){

			int  idligne = Integer.parseInt(request.getParameter("idLigne"));
			int  annee = Integer.parseInt(request.getParameter("date"));
			String affichage = request.getParameter("affichage");
			List<Integer> listeNbIncident = new ArrayList<Integer>();


			for (int i=1; i<25; i++) {

				listeNbIncident.add(incident_dao.getNbIncidentParLigne(i,annee,idligne,affichage));

			}

			request.logout();
			request.setAttribute("listeNbIncident",listeNbIncident);
			request.setAttribute("annee",annee);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/BInbIncidentLigneAnnuelle.jsp").forward( request, response );

		}

		if(action.equals("Valider temps incident")){

			int  idligne = Integer.parseInt(request.getParameter("idLigne"));
			int  annee = Integer.parseInt(request.getParameter("date"));
			String affichage = request.getParameter("affichage");
			List<String> listeTempsMoyenIncident = new ArrayList<String>();
			List<String> listeTempsMaxIncident = new ArrayList<String>();
			List<String> listeTempsMinIncident = new ArrayList<String>();
			

			for (int i=1; i<25; i++) {

				listeTempsMoyenIncident.add(incident_dao.getTempsMoyenIncident(i,annee,idligne,affichage).toString());	
				System.out.println("moyen:");
				System.out.println(listeTempsMoyenIncident.get(i-1));
                listeTempsMaxIncident.add(incident_dao.getTempsMoyenIncident(i,annee,idligne,affichage).toString());	
                System.out.println("max:");
                System.out.println(listeTempsMaxIncident.get(i-1));
                listeTempsMinIncident.add(incident_dao.getTempsMoyenIncident(i,annee,idligne,affichage).toString());	
                System.out.println("min:");
                System.out.println(listeTempsMinIncident.get(i-1));
			}

			request.logout();
			request.setAttribute("listeTempsMoyenIncident",listeTempsMoyenIncident);
			request.setAttribute("listeTempsMaxIncident",listeTempsMaxIncident);
			request.setAttribute("listeTempsMinIncident",listeTempsMinIncident);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/BITempsIncident.jsp").forward( request, response );

		}
		//FIN Thème incident
	}




}





