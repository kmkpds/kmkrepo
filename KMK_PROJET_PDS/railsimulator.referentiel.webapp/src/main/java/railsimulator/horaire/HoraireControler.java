package railsimulator.horaire;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import railsimulator.tools.AlgoCreationHoraire;
import dao.HibernateUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ParametreHoraireDAO;
import dao.LigneDAO;
import dao.ReseauDAO;
import dao.StationDAO;
import dao.TrainDAO;
import dao.TrainHoraireStationDAO;
import dao.StationDAO;

import beans.ParametreHoraire;
import beans.Ligne;
import beans.Reseau;
import beans.Train;
import beans.TrainHoraireStation;
import beans.Station;


public class HoraireControler extends HttpServlet {
	private ParametreHoraire parametreHoraire = new ParametreHoraire();
	private ParametreHoraireDAO parametreHoraireDAO = new ParametreHoraireDAO();
	private LigneDAO ligneDAO = new LigneDAO();
	private ReseauDAO reseauDAO = new ReseauDAO();
	private TrainDAO trainDAO = new TrainDAO();
	private TrainHoraireStationDAO trainHoraireStationDAO = new TrainHoraireStationDAO();
	private StationDAO stationDAO = new StationDAO();
	private Station station1 = new Station();
	private Station station2 = new Station();
	private Station station3 = new Station();
	private Station station4 = new Station();
	private Station station5 = new Station();
	private Reseau reseau = new Reseau();
	private Ligne ligne = new Ligne();
	private Train train1 = new Train();
	private Train train2 = new Train();
	private Train train3 = new Train();
	private TrainHoraireStation trainHoraireStation1 = new TrainHoraireStation();
	private TrainHoraireStation trainHoraireStation2 = new TrainHoraireStation();
	private TrainHoraireStation trainHoraireStation3 = new TrainHoraireStation();
	private TrainHoraireStation trainHoraireStation4 = new TrainHoraireStation();
	private TrainHoraireStation trainHoraireStation5 = new TrainHoraireStation();
	private TrainHoraireStation trainHoraireStation6 = new TrainHoraireStation();
	private TrainHoraireStation trainHoraireStation7 = new TrainHoraireStation();
	private TrainHoraireStation trainHoraireStation8 = new TrainHoraireStation();
	private TrainHoraireStation trainHoraireStation9 = new TrainHoraireStation();
	private TrainHoraireStation trainHoraireStation10 = new TrainHoraireStation();
	private TrainHoraireStation trainHoraireStation11 = new TrainHoraireStation();
	private TrainHoraireStation trainHoraireStation12 = new TrainHoraireStation();
	private TrainHoraireStation trainHoraireStation13 = new TrainHoraireStation();
	private TrainHoraireStation trainHoraireStation14 = new TrainHoraireStation();
	private TrainHoraireStation trainHoraireStation15 = new TrainHoraireStation();
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

		String action = request.getParameter("action");
		if(action.equals("creerHoraire")){
			List<Ligne> ligneList = ligneDAO.listerLigne();
//			if (ligneList.size()==0){
//				int idReseau = reseauDAO.createReseauReturnId("Test reseau");
//				reseau = reseauDAO.getReseauByID(idReseau);
//				ligneDAO.createLigne("Ligne Test", "test", reseau);
//				ligneList = ligneDAO.listerLigne();
//			}
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
		if(action.equals("testCreationHoraire")){
			List<Ligne> ligneList = ligneDAO.listerLigne();
			if (ligneList.size()==0){
				int idReseau = reseauDAO.createReseauReturnId("Test reseau");
				reseau = reseauDAO.getReseauByID(idReseau);
				ligneDAO.createLigne("Ligne Test", "test", reseau);
				ligneList = ligneDAO.listerLigne();
			}
			request.logout();
			request.setAttribute("listeLigne",ligneList);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/testCreationHoraire.jsp").forward( request, response );
		}
		if(action.equals("Mock")){
			//Création réseau
			int idReseau = reseauDAO.createReseauReturnId("Test reseau");
			reseau = reseauDAO.getReseauByID(idReseau);
			//Création Ligne
			int idLigne = ligneDAO.createLigneReturnId("Ligne Test", "test", reseau);
			ligne = ligneDAO.getLigneByID(idLigne);
			//Création Train
			int idTrain = trainDAO.createTrainReturnId("Train test 1", 200.23333333, 344.55533322, 1, ligne);
			train1 = trainDAO.getTrainByID(idTrain);
			idTrain = trainDAO.createTrainReturnId("Train test 2", 300.23333333, 222.55533322, 1, ligne);
			train2 = trainDAO.getTrainByID(idTrain);
			idTrain = trainDAO.createTrainReturnId("Train test 3", 50.23333333, 150.55533322, 1, ligne);
			train3 = trainDAO.getTrainByID(idTrain);
			//Création Station
			int idStation = stationDAO.createStationReturnId("Station test 1", "Commentaire test", 50.00, 10.00, ligne);
			station1 = stationDAO.getStationByID(idStation);
			idStation = stationDAO.createStationReturnId("Station test 2", "Commentaire test", 100.00, 20.00, ligne);
			station2 = stationDAO.getStationByID(idStation);
			idStation = stationDAO.createStationReturnId("Station test 3", "Commentaire test", 150.00, 15.00, ligne);
			station3 = stationDAO.getStationByID(idStation);
			idStation = stationDAO.createStationReturnId("Station test 4", "Commentaire test", 200.00, 35.00, ligne);
			station4 = stationDAO.getStationByID(idStation);
			idStation = stationDAO.createStationReturnId("Station test 5", "Commentaire test", 75.00, 20.00, ligne);
			station5 = stationDAO.getStationByID(idStation);
			//Création TrainHoraireStation
			int idTrainHoraireStation = trainHoraireStationDAO.createTrainHoraireReturnId("8:00", "9:00", "10:00", station1, train1);
			trainHoraireStation1 = trainHoraireStationDAO.getTrainHoraireStationById(idTrainHoraireStation);
			idTrainHoraireStation = trainHoraireStationDAO.createTrainHoraireReturnId("8:05", "9:07", "10:10", station1, train2);
			trainHoraireStation2 = trainHoraireStationDAO.getTrainHoraireStationById(idTrainHoraireStation);
			idTrainHoraireStation = trainHoraireStationDAO.createTrainHoraireReturnId("8:10", "9:14", "10:20", station1, train3);
			trainHoraireStation3 = trainHoraireStationDAO.getTrainHoraireStationById(idTrainHoraireStation);
			idTrainHoraireStation = trainHoraireStationDAO.createTrainHoraireReturnId("8:07", "9:05", "10:10", station2, train1);
			trainHoraireStation4 = trainHoraireStationDAO.getTrainHoraireStationById(idTrainHoraireStation);
			idTrainHoraireStation = trainHoraireStationDAO.createTrainHoraireReturnId("8:12", "9:12", "10:20", station2, train2);
			trainHoraireStation5 = trainHoraireStationDAO.getTrainHoraireStationById(idTrainHoraireStation);
			idTrainHoraireStation = trainHoraireStationDAO.createTrainHoraireReturnId("8:17", "9:19", "10:30", station2, train3);
			trainHoraireStation6 = trainHoraireStationDAO.getTrainHoraireStationById(idTrainHoraireStation);
			idTrainHoraireStation = trainHoraireStationDAO.createTrainHoraireReturnId("8:25", "9:25", "10:20", station3, train1);
			trainHoraireStation7 = trainHoraireStationDAO.getTrainHoraireStationById(idTrainHoraireStation);
			idTrainHoraireStation = trainHoraireStationDAO.createTrainHoraireReturnId("8:30", "9:30", "10:30", station3, train2);
			trainHoraireStation8 = trainHoraireStationDAO.getTrainHoraireStationById(idTrainHoraireStation);
			idTrainHoraireStation = trainHoraireStationDAO.createTrainHoraireReturnId("8:35", "9:35", "10:40", station3, train3);
			trainHoraireStation9 = trainHoraireStationDAO.getTrainHoraireStationById(idTrainHoraireStation);
			idTrainHoraireStation = trainHoraireStationDAO.createTrainHoraireReturnId("8:37", "9:40", "10:30", station4, train1);
			trainHoraireStation10 = trainHoraireStationDAO.getTrainHoraireStationById(idTrainHoraireStation);
			idTrainHoraireStation = trainHoraireStationDAO.createTrainHoraireReturnId("8:42", "9:47", "10:40", station4, train2);
			trainHoraireStation11 = trainHoraireStationDAO.getTrainHoraireStationById(idTrainHoraireStation);
			idTrainHoraireStation = trainHoraireStationDAO.createTrainHoraireReturnId("8:47", "9:54", "10:50", station4, train3);
			trainHoraireStation12 = trainHoraireStationDAO.getTrainHoraireStationById(idTrainHoraireStation);
			idTrainHoraireStation = trainHoraireStationDAO.createTrainHoraireReturnId("8:50", "10:00", "10:40", station5, train1);
			trainHoraireStation13 = trainHoraireStationDAO.getTrainHoraireStationById(idTrainHoraireStation);
			idTrainHoraireStation = trainHoraireStationDAO.createTrainHoraireReturnId("8:55", "10:07", "10:50", station5, train2);
			trainHoraireStation14 = trainHoraireStationDAO.getTrainHoraireStationById(idTrainHoraireStation);
			idTrainHoraireStation = trainHoraireStationDAO.createTrainHoraireReturnId("9:00", "10:15", "11:00", station5, train3);
			trainHoraireStation15 = trainHoraireStationDAO.getTrainHoraireStationById(idTrainHoraireStation);
			
			
		}
		if(action.equals("Purger")){
			Session se = null;
			se = HibernateUtils.getSession();
			
			Transaction t9 = se.beginTransaction();
			Query create9=se.createSQLQuery("delete from parametrehoraire");
			create9.executeUpdate();
			t9.commit();
			
			Transaction t5 = se.beginTransaction();
			Query create5=se.createSQLQuery("delete from trainhorairestation");
			create5.executeUpdate();
			t5.commit();
			
			Transaction t10 = se.beginTransaction();
			Query create10=se.createSQLQuery("delete from train");
			create10.executeUpdate();
			t10.commit();
			
			Transaction t = se.beginTransaction();
			Query create=se.createSQLQuery("delete from station_has_station");
			create.executeUpdate();
			t.commit();
			
			Transaction t8 = se.beginTransaction();
			Query create8=se.createSQLQuery("delete from station");
			create8.executeUpdate();
			t8.commit();
			
			Transaction t2 = se.beginTransaction();
			Query create2=se.createSQLQuery("delete from ligne");
			create2.executeUpdate();
			t2.commit();
			
			Transaction t3 = se.beginTransaction();
			Query create3=se.createSQLQuery("delete from lieu");
			create3.executeUpdate();
			t3.commit();
			
			Transaction t4 = se.beginTransaction();
			Query create4=se.createSQLQuery("delete from geolocalisation");
			create4.executeUpdate();
			t4.commit();
			
			Transaction t6 = se.beginTransaction();
			Query create6=se.createSQLQuery("delete from zone");
			create6.executeUpdate();
			t6.commit();
			
			Transaction t7 = se.beginTransaction();
			Query create7=se.createSQLQuery("delete from reseau");
			create7.executeUpdate();
			t7.commit();
			
			se.close();	
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
		if(action.equals("testCreationHoraire")){
			String checkJS = request.getParameter("msgCheckJS");
			List<Ligne> ligneList = ligneDAO.listerLigne();
			if (ligneList.size()==0){
				int idReseau = reseauDAO.createReseauReturnId("Test reseau");
				reseau = reseauDAO.getReseauByID(idReseau);
				ligneDAO.createLigne("Ligne Test", "test", reseau);
				ligneList = ligneDAO.listerLigne();
			}
			request.logout();
			request.setAttribute("msgCheckJS", checkJS);
			request.setAttribute("listeLigne",ligneList);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/testCreationHoraire.jsp").forward( request, response );
		
		}
		if(action.equals("genererGrilleHoraire")){
			int idParametreHoraire;
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
				//int idParametreHoraire =parametreHoraireDAO.createParametreHoraireReturnId(ligne, HeurePTJO, HeurePTSamedi, HeurePTDimancheJF,  HeureDTJO, HeureDTSamedi, HeureDTDimancheJF, cadencementJO, cadencementSamedi, cadencementDimancheJF, heuresPointeJOStr, heuresPointeSamediStr, heuresPointeDimancheJFStr, tempsStationnementJO, tempsStationnementSamedi, tempsStationnementDimancheJF, vitesseMoyenne);
				idParametreHoraire =parametreHoraireDAO.createParametreHoraireReturnId(ligne, HeurePTJO, HeurePTSamedi, HeurePTDimancheJF,  HeureDTJO, HeureDTSamedi, HeureDTDimancheJF, "00:00:00", "01:01:01", "02:02:00", heuresPointeJOStr, heuresPointeSamediStr, heuresPointeDimancheJFStr, tempsStationnementJO, tempsStationnementSamedi, tempsStationnementDimancheJF, vitesseMoyenne);
				AlgoCreationHoraire algoHoraire = new AlgoCreationHoraire();
				algoHoraire.calculGrilleHoraire(idParametreHoraire);
				TrainHoraireStationDAO trainHoraireStationDAO = new TrainHoraireStationDAO();
				TrainDAO trainDAO = new TrainDAO();
				List<Train> trainList = trainDAO.listerTrainByLigne(idLgn);
				String trainListStr="(";
				for (int j=0;j<trainList.size()-1; j++){
					trainListStr=trainListStr+trainList.get(j).getIdTrain()+",";
				}
				trainListStr=trainListStr.substring(0, trainListStr.length()-1);
				trainListStr=trainListStr +")";
				List<TrainHoraireStation> trainHoraireStationList = trainHoraireStationDAO.listTrainHoraireStationByListTrain(trainListStr);
				String tableau[][] = new String[trainHoraireStationList.size()][6] ; 
				for(int i=0; i<trainHoraireStationList.size()-1;i++){
					tableau[i][0] = String.valueOf(idLgn);
					tableau[i][1] = trainHoraireStationList.get(i).getTrain().getIdTrain() + "-"+trainHoraireStationList.get(i).getTrain().getNomTrain();
					tableau[i][2] = trainHoraireStationList.get(i).getStation().getIdStation()+"-"+trainHoraireStationList.get(i).getStation().getNomStation();
					tableau[i][3] = trainHoraireStationList.get(i).getHeureJO();
					tableau[i][4] = trainHoraireStationList.get(i).getHeureSamedi();
					tableau[i][5] = trainHoraireStationList.get(i).getHeureDimancheJF();
//					System.out.println("id train" +trainHoraireStationList.get(i).getTrain().getIdTrain());
//					System.out.println("Nom train" +trainHoraireStationList.get(i).getTrain().getNomTrain());
//					System.out.println("Nom station "+ trainHoraireStationList.get(i).getStation().getNomStation());
//					System.out.println("Heure Dimanche JF" +trainHoraireStationList.get(i).getHeureDimancheJF());
//					System.out.println("Heure JO" +trainHoraireStationList.get(i).getHeureJO());
//					System.out.println("Heure Samedi" +trainHoraireStationList.get(i).getHeureSamedi());
				}
//				for(int i=0; i<trainHoraireStationList.size()-1;i++){
//					for(int j=0; j<6;j++){
//						System.out.print(tableau[i][j] + " | ");
//					}
//					System.out.println(" ");
//				}
				request.logout();
//				request.setAttribute("Parametres", parametreHoraire);
				request.setAttribute("ListHoraire", tableau);
				//request.setAttribute("listeLigne",ligneList);
				
				
				this.getServletContext().getRequestDispatcher( "/WEB-INF/visualisationHoraireCreation.jsp").forward( request, response );		
			}
			else{
				List<Ligne> ligneList = ligneDAO.listerLigne();
				request.setAttribute("listeLigne",ligneList);
				this.getServletContext().getRequestDispatcher( "/WEB-INF/creationHoraire.jsp").forward( request, response );	
			}
		}		
	}
}
