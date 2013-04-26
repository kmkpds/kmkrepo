package railsimulator.horaire.controleur;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import railsimulator.tools.Algo;
import railsimulator.tools.AlgoCreationReseau;
import dao.HibernateUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ParametreHoraireDAO;
import dao.LigneDAO;

import beans.ParametreHoraire;
import beans.Ligne;


public class HoraireControler extends HttpServlet {
	private ParametreHoraire parametreHoraire = new ParametreHoraire();
	private ParametreHoraireDAO parametreHoraireDAO = new ParametreHoraireDAO();
	private LigneDAO ligneDAO = new LigneDAO();
	private Ligne ligne = new Ligne();
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

		String action = request.getParameter("action");
		if(action.equals("creerHoraire")){
			List<Ligne> ligneList = ligneDAO.listerLigne();
			request.logout();
			request.setAttribute("listeLigne",ligneList);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/creationHoraire.jsp").forward( request, response );
		}
		if(action.equals("visualiserHoraire")){
			//List<Ligne> ligneList = ligneDAO.listerLigne();
			request.logout();
			//request.setAttribute("listeLigne",ligneList);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/visualisationHoraire.jsp").forward( request, response );
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		if(action.equals("creerHoraire")){
			List<Ligne> ligneList = ligneDAO.listerLigne();
			request.logout();
			request.setAttribute("listeLigne",ligneList);
			this.getServletContext().getRequestDispatcher("/WEB-INF/creationHoraire.jsp").forward( request, response );
	
		}
		if(action.equals("genererGrilleHoraire")){
			String[] heuresPointeJO = request.getParameterValues("heuresPointeJO");
			String[] heuresPointeSamedi = request.getParameterValues("heuresPointeSamedi");
			String[] heuresPointeDimancheJF = request.getParameterValues("heuresPointeDimancheJF");	
			boolean checkSubmit=true;
			//Récupération Heure du premier train
			int heurePTJO = Integer.parseInt(request.getParameter("heurePTJO"));
			int minutePTJO = Integer.parseInt(request.getParameter("minutePTJO"));
			int heurePTSamedi = Integer.parseInt(request.getParameter("heurePTSamedi"));
			int minutePTSamedi = Integer.parseInt(request.getParameter("minutePTSamedi"));
			int heurePTDimancheJF = Integer.parseInt(request.getParameter("heurePTDimancheJF"));
			int minutePTDimancheJF = Integer.parseInt(request.getParameter("minutePTDimancheJF"));
			int heureDTJO = Integer.parseInt(request.getParameter("heureDTJO"));
			int minuteDTJO = Integer.parseInt(request.getParameter("minuteDTJO"));
			int heureDTSamedi = Integer.parseInt(request.getParameter("heureDTSamedi"));
			int minuteDTSamedi = Integer.parseInt(request.getParameter("minuteDTSamedi"));
			int heureDTDimancheJF = Integer.parseInt(request.getParameter("heureDTDimancheJF"));
			int minuteDTDimancheJF = Integer.parseInt(request.getParameter("minuteDTDimancheJF"));
			int vitesseMoyenne=  Integer.parseInt(request.getParameter("vitesseMoyenne"));
			if(heurePTJO>heureDTJO | heurePTSamedi>heureDTSamedi | heurePTDimancheJF>heureDTDimancheJF){
				checkSubmit=false;
			}
			if(heurePTJO==heureDTJO &&  minutePTJO>=minuteDTJO){
				checkSubmit=false;
			}
			if(heurePTSamedi==heureDTSamedi &&  minutePTSamedi>=minuteDTSamedi){
				checkSubmit=false;
			}
			if(heurePTDimancheJF==heureDTDimancheJF &&  minutePTDimancheJF>=minuteDTDimancheJF){
				checkSubmit=false;
			}
			if (heuresPointeDimancheJF != null && heuresPointeJO !=null && heuresPointeSamedi != null && vitesseMoyenne!=0 && checkSubmit!=false){
				//Récupération idLigne
				int idLgn = Integer.parseInt(request.getParameter("idLigne"));
				//ligne = ligneDAO.getLigneByID(idLigne);
				ligne = ligneDAO.getLigneByID(idLgn);
				parametreHoraire.setLigne(ligne);
				
				//Récupération vitesseMoyenne
				parametreHoraire.setVitesseMoyenne(vitesseMoyenne);
				
				String heurePTJOStr;
				if(heurePTJO<10){
					heurePTJOStr="0"+heurePTJO;
				}
				else{
					heurePTJOStr= String.valueOf(heurePTJO);
				}
				String minutePTJOStr;
				if(minutePTJO<10){
					minutePTJOStr="0"+minutePTJO;
				}
				else{
					minutePTJOStr= String.valueOf(minutePTJO);
				}
				String HeurePTJO = heurePTJOStr+ ":" + minutePTJOStr +":00";
				parametreHoraire.setHeurePremierTrainJO(HeurePTJO);
				
				String heurePTSamediStr;
				if(heurePTSamedi<10){
					heurePTSamediStr="0"+heurePTSamedi;
				}
				else{
					heurePTSamediStr= String.valueOf(heurePTSamedi);
				}
				String minutePTSamediStr;
				if(minutePTSamedi<10){
					minutePTSamediStr="0"+minutePTSamedi;
				}
				else{
					minutePTSamediStr= String.valueOf(minutePTSamedi);
				}
				String HeurePTSamedi = heurePTSamediStr+ ":" + minutePTSamediStr +":00";
				parametreHoraire.setHeurePremierTrainSamedi(HeurePTSamedi);
				
				String heurePTDimancheJFStr;
				if(heurePTDimancheJF<10){
					heurePTDimancheJFStr="0"+heurePTDimancheJF;
				}
				else{
					heurePTDimancheJFStr= String.valueOf(heurePTDimancheJF);
				}
				String minutePTDimancheJFStr;
				if(minutePTDimancheJF<10){
					minutePTDimancheJFStr="0"+minutePTDimancheJF;
				}
				else{
					minutePTDimancheJFStr= String.valueOf(minutePTDimancheJF);
				}
				String HeurePTDimancheJF = heurePTDimancheJFStr+ ":" + minutePTDimancheJFStr +":00";
				parametreHoraire.setHeurePremierTrainDimancheJF(HeurePTDimancheJF);
				String heureDTJOStr;
				if(heureDTJO<10){
					heureDTJOStr="0"+heureDTJO;
				}
				else{
					heureDTJOStr= String.valueOf(heureDTJO);
				}
				String minuteDTJOStr;
				if(minuteDTJO<10){
					minuteDTJOStr="0"+minuteDTJO;
				}
				else{
					minuteDTJOStr= String.valueOf(minuteDTJO);
				}
				String HeureDTJO = heureDTJOStr+ ":" + minuteDTJOStr +":00";
				parametreHoraire.setHeureDernierTrainJO(HeureDTJO);
				String heureDTSamediStr;
				if(heureDTSamedi<10){
					heureDTSamediStr="0"+heureDTSamedi;
				}
				else{
					heureDTSamediStr= String.valueOf(heureDTSamedi);
				}
				String minuteDTSamediStr;
				if(minuteDTSamedi<10){
					minuteDTSamediStr="0"+minuteDTSamedi;
				}
				else{
					minuteDTSamediStr= String.valueOf(minuteDTSamedi);
				}
				String HeureDTSamedi = heureDTSamediStr+ ":" + minuteDTSamediStr +":00";
				parametreHoraire.setHeureDernierTrainSamedi(HeureDTSamedi);
				String heureDTDimancheJFStr;
				if(heureDTDimancheJF<10){
					heureDTDimancheJFStr="0"+heureDTDimancheJF;
				}
				else{
					heureDTDimancheJFStr= String.valueOf(heureDTDimancheJF);
				}
				String minuteDTDimancheJFStr;
				if(minuteDTDimancheJF<10){
					minuteDTDimancheJFStr="0"+minuteDTDimancheJF;
				}
				else{
					minuteDTDimancheJFStr= String.valueOf(minuteDTDimancheJF);
				}
				String HeureDTDimancheJF = heureDTDimancheJFStr+ ":" + minuteDTDimancheJFStr +":00";
				parametreHoraire.setHeureDernierTrainDimancheJF(HeureDTDimancheJF);
				
				//Récupération cadencement
				int cadencementJOMin = Integer.parseInt(request.getParameter("cadencementJOMin"));
				String cadencementJOMinStr;
				if(cadencementJOMin<10){
					cadencementJOMinStr="0"+cadencementJOMin;
				}
				else{
					cadencementJOMinStr= String.valueOf(cadencementJOMin);
				}
				int cadencementJOSec = Integer.parseInt(request.getParameter("cadencementJOSec"));
				String cadencementJOSecStr;
				if(cadencementJOSec<10){
					cadencementJOSecStr="0"+cadencementJOSec;
				}
				else{
					cadencementJOSecStr= String.valueOf(cadencementJOSec);
				}
				String cadencementJO =  "00:" +cadencementJOMinStr+ ":" + cadencementJOSecStr;
				parametreHoraire.setCadencementJO(cadencementJO);
				
				int cadencementSamediMin = Integer.parseInt(request.getParameter("cadencementSamediMin"));
				String cadencementSamediMinStr;
				if(cadencementSamediMin<10){
					cadencementSamediMinStr="0"+cadencementSamediMin;
				}
				else{
					cadencementSamediMinStr= String.valueOf(cadencementSamediMin);
				}
				int cadencementSamediSec = Integer.parseInt(request.getParameter("cadencementSamediSec"));
				String cadencementSamediSecStr;
				if(cadencementSamediSec<10){
					cadencementSamediSecStr="0"+cadencementSamediSec;
				}
				else{
					cadencementSamediSecStr= String.valueOf(cadencementSamediSec);
				}
				String cadencementSamedi = "00:" +cadencementSamediMinStr+ ":" + cadencementSamediSecStr;
				parametreHoraire.setCadencementSamedi(cadencementSamedi);
				
				int cadencementDimancheJFMin = Integer.parseInt(request.getParameter("cadencementDimancheJFMin"));
				String cadencementDimancheJFMinStr;
				if(cadencementDimancheJFMin<10){
					cadencementDimancheJFMinStr="0"+cadencementDimancheJFMin;
				}
				else{
					cadencementDimancheJFMinStr= String.valueOf(cadencementDimancheJFMin);
				}
				int cadencementDimancheJFSec = Integer.parseInt(request.getParameter("cadencementDimancheJFSec"));
				String cadencementDimancheJFSecStr;
				if(cadencementDimancheJFSec<10){
					cadencementDimancheJFSecStr="0"+cadencementDimancheJFSec;
				}
				else{
					cadencementDimancheJFSecStr= String.valueOf(cadencementDimancheJFSec);
				}
				String cadencementDimancheJF = "00:" +cadencementDimancheJFMinStr+ ":" + cadencementDimancheJFSecStr;
				parametreHoraire.setCadencementDimancheJF(cadencementDimancheJF);
				
				//Récupération temps de stationnement
				int tempsStationnementJOMin = Integer.parseInt(request.getParameter("tempsStationnementJOMin"));
				String tempsStationnementJOMinStr;
				if(tempsStationnementJOMin<10){
					tempsStationnementJOMinStr="0"+tempsStationnementJOMin;
				}
				else{
					tempsStationnementJOMinStr= String.valueOf(tempsStationnementJOMin);
				}
				int tempsStationnementJOSec = Integer.parseInt(request.getParameter("tempsStationnementJOSec"));
				String tempsStationnementJOSecStr;
				if(tempsStationnementJOSec<10){
					tempsStationnementJOSecStr="0"+tempsStationnementJOSec;
				}
				else{
					tempsStationnementJOSecStr= String.valueOf(tempsStationnementJOSec);
				}
				String tempsStationnementJO = "00:" +tempsStationnementJOMinStr+ ":" + tempsStationnementJOSecStr;
				parametreHoraire.setTempsStationnementJO(tempsStationnementJO);
				int tempsStationnementSamediMin = Integer.parseInt(request.getParameter("tempsStationnementSamediMin"));
				String tempsStationnementSamediMinStr;
				if(tempsStationnementSamediMin<10){
					tempsStationnementSamediMinStr="0"+tempsStationnementSamediMin;
				}
				else{
					tempsStationnementSamediMinStr= String.valueOf(tempsStationnementSamediMin);
				}
				int tempsStationnementSamediSec = Integer.parseInt(request.getParameter("tempsStationnementSamediSec"));
				String tempsStationnementSamediSecStr;
				if(tempsStationnementSamediSec<10){
					tempsStationnementSamediSecStr="0"+tempsStationnementSamediSec;
				}
				else{
					tempsStationnementSamediSecStr= String.valueOf(tempsStationnementSamediSec);
				}
				String tempsStationnementSamedi = "00:" +tempsStationnementSamediMinStr+ ":" + tempsStationnementSamediSecStr;
				parametreHoraire.setTempsStationnementSamedi(tempsStationnementSamedi);
				int tempsStationnementDimancheJFMin = Integer.parseInt(request.getParameter("tempsStationnementDimancheJFMin"));
				String tempsStationnementDimancheJFMinStr;
				if(tempsStationnementDimancheJFMin<10){
					tempsStationnementDimancheJFMinStr="0"+tempsStationnementDimancheJFMin;
				}
				else{
					tempsStationnementDimancheJFMinStr= String.valueOf(tempsStationnementDimancheJFMin);
				}
				int tempsStationnementDimancheJFSec = Integer.parseInt(request.getParameter("tempsStationnementDimancheJFSec"));
				String tempsStationnementDimancheJFSecStr;
				if(tempsStationnementDimancheJFSec<10){
					tempsStationnementDimancheJFSecStr="0"+tempsStationnementDimancheJFSec;
				}
				else{
					tempsStationnementDimancheJFSecStr= String.valueOf(tempsStationnementDimancheJFSec);
				}
				String tempsStationnementDimancheJF = "00:" +tempsStationnementDimancheJFMinStr+ ":" + tempsStationnementDimancheJFSecStr;
				parametreHoraire.setTempsStationnementDimancheJF(tempsStationnementDimancheJF);
				
				//Récupération heures de pointe
				String heuresPointeJOStr ="";
				if (heuresPointeJO != null){
					for(int i=0; i<heuresPointeJO.length; i++){
						heuresPointeJOStr = heuresPointeJOStr +heuresPointeJO[i] +" | ";
						//System.out.println(heuresPointeJOStr);
					}
				}
				heuresPointeJOStr = heuresPointeJOStr.substring(0, heuresPointeJOStr.length()-2); 
				parametreHoraire.setHeuresPointeJO(heuresPointeJOStr);
				String heuresPointeSamediStr ="";
				if (heuresPointeSamedi != null){
					for(int i=0; i<heuresPointeSamedi.length; i++){
						heuresPointeSamediStr = heuresPointeSamediStr+heuresPointeSamedi[i] +" | ";
						//System.out.println(heuresPointeSamediStr);
					}
				}
				heuresPointeSamediStr = heuresPointeSamediStr.substring(0, heuresPointeSamediStr.length()-2); 
				parametreHoraire.setHeuresPointeSamedi(heuresPointeSamediStr);
	
				String heuresPointeDimancheJFStr ="";
				if (heuresPointeDimancheJF != null){
					for(int i=0; i<heuresPointeDimancheJF.length; i++){
						heuresPointeDimancheJFStr = heuresPointeDimancheJFStr +heuresPointeDimancheJF[i]+" | ";
						//System.out.println(heuresPointeDimancheJFStr);
					}
				}
				heuresPointeDimancheJFStr = heuresPointeDimancheJFStr.substring(0, heuresPointeDimancheJFStr.length()-2); 
				parametreHoraire.setHeuresPointeDimancheJF(heuresPointeDimancheJFStr);
				int idParametreHoraire =parametreHoraireDAO.createParametreHoraireReturnId(ligne, HeurePTJO, HeurePTSamedi, HeurePTDimancheJF,  HeureDTJO, HeureDTSamedi, HeureDTDimancheJF, cadencementJO, cadencementSamedi, cadencementDimancheJF, heuresPointeJOStr, heuresPointeSamediStr, heuresPointeDimancheJFStr, tempsStationnementJO, tempsStationnementSamedi, tempsStationnementDimancheJF, vitesseMoyenne);
				request.logout();
				request.setAttribute("Parametres", parametreHoraire);
				//request.setAttribute("listeLigne",ligneList);
				this.getServletContext().getRequestDispatcher( "/WEB-INF/visualisationHoraire.jsp").forward( request, response );		
			}
			else{
				List<Ligne> ligneList = ligneDAO.listerLigne();
				request.setAttribute("listeLigne",ligneList);
				this.getServletContext().getRequestDispatcher( "/WEB-INF/creationHoraire.jsp").forward( request, response );	
			}
		}		
	}
}
