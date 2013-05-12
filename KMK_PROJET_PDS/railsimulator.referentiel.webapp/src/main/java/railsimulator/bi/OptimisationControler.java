package railsimulator.bi;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import beans.CoutFonctionnement;
import beans.Ligne;
import beans.ParametreHoraire;
import beans.Train;

import dao.LigneDAO;


public class OptimisationControler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OptimisationControler() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();

		if(action.equals("optimisationParc")){
			List<Ligne> listeLigne= new LigneDAO().listerLigne();


			request.setAttribute("listeLigne", listeLigne);

			request.logout();
			this.getServletContext().getRequestDispatcher( "/WEB-INF/affichageinformationligne.jsp").forward( request, response );
		}

		if(action.equals("ModifierLaFrequence")){

			request.logout();
			//this.getServletContext().getRequestDispatcher( "/WEB-INF/modificationfrequenceligne.jsp").forward( request, response );
			this.getServletContext().getRequestDispatcher( "/WEB-INF/modificationfrequence1.jsp").forward( request, response );

		}


		if(action.equals("Afficher")){

			LigneDAO ligneDAO = new LigneDAO();

			String idLigne = (String) request.getParameter("idLigne");
			String frequence = (String) request.getParameter("frequence");

			int id = new Integer(idLigne);
			Ligne ligne = ligneDAO.getLigneByID(id);
			ParametreHoraire parametreHoraire = ligne.getParametreHoraireList().iterator().next();
			Train train = ligne.getTrainlist().iterator().next();
			String freq = "";

			if(frequence.equals("frequenceDimancheetJF")){
				freq = parametreHoraire.getCadencementDimancheJF();
			}else if(frequence.equals("frequenceJO")){
				freq = parametreHoraire.getCadencementJO();
			}if(frequence.equals("frequenceSamedi")){
				freq = parametreHoraire.getCadencementSamedi();
			}

			request.setAttribute("freq", freq);
			request.setAttribute("train", train);
			request.setAttribute("ligne", ligne);
			request.setAttribute("parametreHoraire", parametreHoraire);
			request.setAttribute("total", train.getNombredeplacea()+train.getNombredeplaced());
			
			CoutFonctionnement coutFonctionnement = ligne.getCoutFonctionnementlist().iterator().next();
			//1
			session.setAttribute("nbVoyageurAncien", ligne.getNombrepassagers());
			session.setAttribute("nbrTrain", ligne.getNombredetrain());
			session.setAttribute("longueurLigne", ligne.getLongueur());
			session.setAttribute("vitesseTrain", train.getVitesse());
			session.setAttribute("prixtrain", train.getPrixtrain());
			session.setAttribute("tarificationdedeplacement", coutFonctionnement.getTarificationdedeplacement());
			session.setAttribute("Consommationenergie", coutFonctionnement.getConsommationenergie());
			session.setAttribute("freq", freq);
			session.setAttribute("coutMaintenance", coutFonctionnement.getEntretienmaterielroulant());
			session.setAttribute("prixplace", ligne.getPrixdeplace());
			List<Ligne> listeLigne= ligneDAO.listerLigne();
			request.setAttribute("listeLigne", listeLigne);
			request.logout();
			this.getServletContext().getRequestDispatcher( "/WEB-INF/affichageinformationligne.jsp").forward( request, response );

		}


		if(action.equals("OK")){
		
			//2
			double frequence = Double.parseDouble(request.getParameter("NouvelleFrequence"));
			double longueurLigne= Double.parseDouble(session.getAttribute("longueurLigne").toString());
			double vitesseTrain= Double.parseDouble(session.getAttribute("vitesseTrain").toString());
			int   prixtrain =Integer.parseInt(session.getAttribute("prixtrain").toString());
			int   nbtrain =Integer.parseInt(session.getAttribute("nbrTrain").toString());
			double tarificationdedeplacement =Double.parseDouble(session.getAttribute("tarificationdedeplacement").toString());
			double Consommationenergie = Double.parseDouble(session.getAttribute("Consommationenergie").toString());
			int  freq = Integer.parseInt(session.getAttribute("freq").toString());
			int nbVoyagAncien = Integer.parseInt(session.getAttribute("nbVoyageurAncien").toString());
			double coutMaintenance = Double.parseDouble(session.getAttribute("coutMaintenance").toString());
			double prixplace = Double.parseDouble(session.getAttribute("prixplace").toString());
			
			DecimalFormat df = new DecimalFormat ( ) ; 
			df.setMaximumFractionDigits(2); 
			df.setMinimumFractionDigits(2);
			df.setGroupingUsed(false);
			
			
			//calcul des coûts 
			
			//3
			int nbTrainArajouter = (int) (longueurLigne / (frequence * (vitesseTrain/60)));
			int nbPlaceProposee = 360 * nbTrainArajouter;
			String TauxSatisfaction = "" ;
			if(frequence >= 1 && frequence <= 3){
				TauxSatisfaction = "90 %";
			}else if(frequence >= 4 && frequence <=6 ){
				TauxSatisfaction ="45 %";
			}else if(frequence >6){
				TauxSatisfaction ="15 %";
			}
			//double coutfreqmateriel = prixtrain* nbtrain;
			double coutfreqprev=prixtrain* nbTrainArajouter ;
			double InvestissmentTrain=prixtrain* nbTrainArajouter;
		    //double coutTarifNormal = nbVoyagAncien * longueurLigne * tarificationdedeplacement;
		    double coutTarifNormal = ((nbVoyagAncien * tarificationdedeplacement) /longueurLigne );
			//double coutTarifPrev =  (nbPlaceProposee + nbVoyagAncien) * longueurLigne * tarificationdedeplacement;
			double coutTarifPrev =  (((nbPlaceProposee + nbVoyagAncien)* tarificationdedeplacement) /longueurLigne) ;
			double coutMaintNormal = nbtrain * coutMaintenance;
			double coutMaintPrev = (nbtrain + nbTrainArajouter)*coutMaintenance;
			double consEnergNormal = nbtrain * longueurLigne * Consommationenergie;
			double consEnergPrev = (nbtrain + nbTrainArajouter) * longueurLigne * Consommationenergie;
			double revenuvoynormal=nbVoyagAncien*prixplace;
			double revenuvoyPrev=(nbVoyagAncien + nbPlaceProposee) * prixplace;
			double coutIncidentnormal= 10510*nbtrain;
			double coutIncidentPrev=10510*nbTrainArajouter;
			int rsi = (int)(InvestissmentTrain/(revenuvoyPrev+coutMaintPrev)) ;
			double coutMaterielRoulantPrev =(Double)(InvestissmentTrain/(rsi*365));
			
			//3
			request.setAttribute("InvestissmentTrain",df.format(InvestissmentTrain));
			request.setAttribute("rsi",rsi);
			request.setAttribute("coutIncidentnormal",df.format(coutIncidentnormal));
			request.setAttribute("coutIncidentPrev",df.format(coutIncidentPrev));
			request.setAttribute("coutMaterielRoulantPrev",df.format(coutMaterielRoulantPrev));
			request.setAttribute("TauxSatisfaction", TauxSatisfaction);
			request.setAttribute("nbTrainArajouter", nbTrainArajouter);
			request.setAttribute("nbPlaceProposee", nbPlaceProposee);
			request.setAttribute("frequence", (int)frequence);
			request.setAttribute("prixtrain",df.format (prixtrain));
			//request.setAttribute("coutfreqmateriel",df.format(coutfreqmateriel));
			request.setAttribute("coutfreqprev",df.format ( coutfreqprev ));
			request.setAttribute("tarificationdedeplacement",tarificationdedeplacement);
			request.setAttribute("coutTarifNormal",df.format(coutTarifNormal));
			request.setAttribute("coutTarifPrev",df.format(coutTarifPrev));	
			request.setAttribute("coutMaintenance",coutMaintenance);	
			request.setAttribute("coutMaintNormal",df.format(coutMaintNormal));	
			request.setAttribute("coutMaintPrev",df.format(coutMaintPrev));	
			request.setAttribute("coutEnerg",Consommationenergie);	
			request.setAttribute("consEnergNormal",df.format(consEnergNormal));	
			request.setAttribute("consEnergPrev",df.format(consEnergPrev));	
			request.setAttribute("totalcout", df.format(coutTarifNormal +coutMaintNormal + consEnergNormal+coutIncidentnormal));
			request.setAttribute("totalcoutPrev", df.format(coutMaterielRoulantPrev + coutTarifPrev +coutMaintPrev + consEnergPrev+coutIncidentPrev));
			request.setAttribute("resultatNormal",df.format(revenuvoynormal-(coutTarifNormal +coutMaintNormal + consEnergNormal+coutIncidentnormal)) );
			request.setAttribute("resultatPrev",df.format(revenuvoyPrev-(coutMaterielRoulantPrev + coutTarifPrev +coutMaintPrev + consEnergPrev+coutIncidentPrev)) );
			request.setAttribute("prixplace",prixplace);
			request.setAttribute("revenuvoynormal",df.format(revenuvoynormal));
			request.setAttribute("revenuvoyPrev",df.format(revenuvoyPrev));
			
			request.logout();
			
			
			
			
			//this.getServletContext().getRequestDispatcher( "/WEB-INF/modificationfrequenceligne.jsp").forward( request, response );
			this.getServletContext().getRequestDispatcher( "/WEB-INF/modificationfrequence1.jsp").forward( request, response );

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
