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
	private Reseau reseau = new Reseau();
	private Ligne ligne = new Ligne();
	
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
		if(action.equals("creerHoraireTest")){
			List<Ligne> ligneList = ligneDAO.listerLigne();
//			if (ligneList.size()==0){
//				int idReseau = reseauDAO.createReseauReturnId("Test reseau");
//				reseau = reseauDAO.getReseauByID(idReseau);
//				ligneDAO.createLigne("Ligne Test", "test", reseau);
//				ligneList = ligneDAO.listerLigne();
//			}
			request.logout();
			request.setAttribute("listeLigne",ligneList);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/creationHoraireTest.jsp").forward( request, response );
		}
		if(action.equals("visualiserHoraire")){
			int idLgn = ligneDAO.listerLigne().get(0).getIdLigne();
			TrainHoraireStationDAO trainHoraireStationDAO = new TrainHoraireStationDAO();
			TrainDAO trainDAO = new TrainDAO();
			List<Train> trainList = trainDAO.listerTrainByLigne(idLgn);
			String trainListStr="(";
			for (int j=0;j<trainList.size(); j++){
				trainListStr=trainListStr+trainList.get(j).getIdTrain()+",";
			}
			trainListStr=trainListStr.substring(0, trainListStr.length()-1);
			trainListStr=trainListStr +")";
			List<TrainHoraireStation> trainHoraireStationList = trainHoraireStationDAO.listTrainHoraireStationByListTrain(trainListStr);
			String tableau[][] = new String[trainHoraireStationList.size()][6] ; 
			for(int i=0; i<trainHoraireStationList.size();i++){
				tableau[i][0] = String.valueOf(idLgn);
				tableau[i][1] = trainHoraireStationList.get(i).getTrain().getIdTrain() + "-"+trainHoraireStationList.get(i).getTrain().getNomTrain();
				tableau[i][2] = trainHoraireStationList.get(i).getStation().getIdStation()+"-"+trainHoraireStationList.get(i).getStation().getNomStation();
				tableau[i][3] = trainHoraireStationList.get(i).getHeureJO();
				tableau[i][4] = trainHoraireStationList.get(i).getHeureSamedi();
				tableau[i][5] = trainHoraireStationList.get(i).getHeureDimancheJF();
			}
			ArrayList<String> nomStationList = new ArrayList<String>(); 
			String nomStation;
			boolean check;
			for(int i=0; i<trainHoraireStationList.size();i++){
				check = false;
				nomStation = String.valueOf(trainHoraireStationList.get(i).getStation().getIdStation())+" - "+String.valueOf(trainHoraireStationList.get(i).getStation().getNomStation());
				for(int j=0; j<nomStationList.size();j++){
					if(nomStation.equals(nomStationList.get(j))){
						check=true;
					}
				}
				if (check==false){
					nomStationList.add(String.valueOf(trainHoraireStationList.get(i).getStation().getIdStation())+" - "+String.valueOf(trainHoraireStationList.get(i).getStation().getNomStation())); 
				}
			}
			ArrayList<String> nomTrainList = new ArrayList<String>(); 
			String nomTrain;
			for(int i=0; i<trainHoraireStationList.size();i++){
				check = false;
				nomTrain = String.valueOf(trainHoraireStationList.get(i).getTrain().getIdTrain())+" - "+String.valueOf(trainHoraireStationList.get(i).getTrain().getNomTrain());
				for(int j=0; j<nomTrainList.size();j++){
					if(nomTrain.equals(nomTrainList.get(j))){
						check=true;
					}
				}
				if (check==false){
					nomTrainList.add(String.valueOf(trainHoraireStationList.get(i).getTrain().getIdTrain())+" - "+String.valueOf(trainHoraireStationList.get(i).getTrain().getNomTrain()));
				}
			}
			String[][] tableauHoraireJO = new String[nomStationList.size()+1][nomTrainList.size()+1];
			String[][] tableauHoraireSamedi = new String[nomStationList.size()+1][nomTrainList.size()+1];
			String[][] tableauHoraireDimancheJF = new String[nomStationList.size()+1][nomTrainList.size()+1];
			tableauHoraireJO[0][0] =" ";
			tableauHoraireSamedi[0][0] =" ";
			tableauHoraireDimancheJF[0][0] =" ";
			for(int i=0; i<nomStationList.size(); i++){
				//System.out.println(nomStationList.get(i));
				tableauHoraireJO[i+1][0]=nomStationList.get(i);
				tableauHoraireSamedi[i+1][0]=nomStationList.get(i);
				tableauHoraireDimancheJF[i+1][0]=nomStationList.get(i);
			}	
			for(int i=0; i<nomTrainList.size(); i++){
				//System.out.println(nomTrainList.get(i));
				tableauHoraireJO[0][i+1]=nomTrainList.get(i);
				tableauHoraireSamedi[0][i+1]=nomTrainList.get(i);
				tableauHoraireDimancheJF[0][i+1]=nomTrainList.get(i);
			}
			for(int i=0; i<trainHoraireStationList.size(); i++){
				nomTrain = String.valueOf(trainHoraireStationList.get(i).getTrain().getIdTrain())+" - "+String.valueOf(trainHoraireStationList.get(i).getTrain().getNomTrain());
				nomStation = String.valueOf(trainHoraireStationList.get(i).getStation().getIdStation())+" - "+String.valueOf(trainHoraireStationList.get(i).getStation().getNomStation());			
				for(int j=0; j<nomStationList.size(); j++){
					if(tableauHoraireJO[j+1][0].equals(nomStation)){
						for(int n=0; n<nomTrainList.size(); n++){
							if(tableauHoraireJO[0][n+1].equals(nomTrain)){
								tableauHoraireJO[j+1][n+1]=trainHoraireStationList.get(i).getHeureJO();
								tableauHoraireSamedi[j+1][n+1]=trainHoraireStationList.get(i).getHeureSamedi();
								tableauHoraireDimancheJF[j+1][n+1]=trainHoraireStationList.get(i).getHeureDimancheJF();
							}
						}
					}		
				}
			}
			
			String tableauJOStr ="";
			String tableauSamediStr ="";
			String tableauDimancheJFStr ="";
			String GraphJO ="";
			String GraphSamedi ="";
			String GraphDimancheJF ="";
			for(int j=0; j<nomStationList.size()+1; j++){
				for(int i=0; i<nomTrainList.size()+1; i++){
//					System.out.print(tableauHoraireJO[j][i]);
					tableauJOStr = tableauJOStr +tableauHoraireJO[j][i]+"|";
					tableauSamediStr = tableauSamediStr +tableauHoraireSamedi[j][i]+"|";
					tableauDimancheJFStr = tableauDimancheJFStr +tableauHoraireDimancheJF[j][i]+"|";
				}
//				tableauJOStr = tableauJOStr + j;
//				tableauSamediStr = tableauSamediStr + j;
//				tableauDimancheJFStr = tableauDimancheJFStr + j;
				tableauJOStr = tableauJOStr.substring(0, tableauJOStr.length()-1);
				tableauSamediStr = tableauSamediStr.substring(0, tableauSamediStr.length()-1);
				tableauDimancheJFStr = tableauDimancheJFStr.substring(0, tableauDimancheJFStr.length()-1);
				tableauJOStr = tableauJOStr +"//";
				tableauSamediStr = tableauSamediStr +"//";
				tableauDimancheJFStr = tableauDimancheJFStr +"//";
			}
			tableauJOStr = tableauJOStr.substring(0, tableauJOStr.length()-2);
			tableauSamediStr = tableauSamediStr.substring(0, tableauSamediStr.length()-2);
			tableauDimancheJFStr = tableauDimancheJFStr.substring(0, tableauDimancheJFStr.length()-2);
			String[] timeJO;
			String[] timeSamedi;
			String[] timeDimancheJF;
			String idTrains;
			int compteur=0;
			//List des trains récupérée ==> trainList
			for (int i=0; i<trainList.size();i++){
				idTrains = "("+trainList.get(i).getIdTrain()+")";
				trainHoraireStationList = trainHoraireStationDAO.listTrainHoraireStationByListTrain(idTrains);
				GraphJO = GraphJO + trainList.get(i).getIdTrain() +" - " +trainList.get(i).getNomTrain() +"|";
				GraphSamedi = GraphSamedi + trainList.get(i).getIdTrain() +" - " +trainList.get(i).getNomTrain() +"|";
				GraphDimancheJF = GraphDimancheJF + trainList.get(i).getIdTrain() +" - " +trainList.get(i).getNomTrain() +"|";
				for(int j=0; j<trainHoraireStationList.size(); j++){
					compteur = 0;
					for(int n=0; n<nomStationList.size(); n++){
						nomStation = trainHoraireStationList.get(j).getStation().getIdStation()+ " - "+trainHoraireStationList.get(j).getStation().getNomStation();
						if(nomStationList.get(n).equals(nomStation)){
							compteur = n;
						}
					}
					if(trainHoraireStationList.get(j).getHeureJO()!=null){
						timeJO = trainHoraireStationList.get(j).getHeureJO().split(":");
						GraphJO = GraphJO + timeJO[0]+" - "+timeJO[1]+" - "+ compteur+"//";
					}
//					else{
//						GraphJO = GraphJO + "0"+" - "+"0"+" - "+ compteur+"//";
//					}
					if(trainHoraireStationList.get(j).getHeureSamedi()!=null){
						timeSamedi = trainHoraireStationList.get(j).getHeureSamedi().split(":");
						GraphSamedi = GraphSamedi + timeSamedi[0]+" - "+timeSamedi[1]+" - "+ compteur+"//";
					}
//					else{
//						GraphSamedi = GraphSamedi + "0"+" - "+"0"+" - "+ compteur+"//";
//					}
					if(trainHoraireStationList.get(j).getHeureDimancheJF()!=null){
						timeDimancheJF = trainHoraireStationList.get(j).getHeureDimancheJF().split(":");
						GraphDimancheJF = GraphDimancheJF + timeDimancheJF[0]+" - "+timeDimancheJF[1]+" - "+ compteur+"//";
					}
//					else{
//						GraphDimancheJF = GraphDimancheJF + "0"+" - "+"0"+" - "+ compteur+"//";
//					}	
				}
				GraphJO = GraphJO.substring(0, GraphJO.length()-2);
				GraphJO = GraphJO + "##";
				GraphSamedi = GraphSamedi.substring(0, GraphSamedi.length()-2);
				GraphSamedi = GraphSamedi + "##";
				GraphDimancheJF = GraphDimancheJF.substring(0, GraphDimancheJF.length()-2);
				GraphDimancheJF = GraphDimancheJF + "##";
			}
			GraphJO = GraphJO.substring(0, GraphJO.length()-2);
			GraphSamedi = GraphSamedi.substring(0, GraphSamedi.length()-2);
			GraphDimancheJF = GraphDimancheJF.substring(0, GraphDimancheJF.length()-2);
//			System.out.println(GraphJO);
			Ligne ligne = ligneDAO.getLigneByID(idLgn);
			List<Ligne> ligneList = ligneDAO.listerLigne();
			request.logout();
			request.setAttribute("listeLigne", ligneList);
			request.setAttribute("GraphJO", GraphJO);
			request.setAttribute("GraphSamedi", GraphSamedi);
			request.setAttribute("GraphDimancheJF", GraphDimancheJF);
			request.setAttribute("NomLigne", ligne.getNomLigne());
			request.setAttribute("ListHoraireJOStr", tableauJOStr);
			request.setAttribute("ListHoraireSamediStr", tableauSamediStr);
			request.setAttribute("ListHoraireDimancheJFStr", tableauDimancheJFStr);
			request.setAttribute("StationList", nomStationList);
			
//			request.setAttribute("Parametres", parametreHoraire);
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
			Train train1 = new Train();
			Train train2 = new Train();
			Train train3 = new Train();
			Train train4 = new Train();
			Train train5 = new Train();
			Train train6 = new Train();
			Train train7 = new Train();
			Train train8 = new Train();
			Train train9 = new Train(); 
			Station station1 = new Station();
			Station station2 = new Station();
			Station station3 = new Station();
			Station station4 = new Station();
			Station station5 = new Station();
			Station station6 = new Station();
			Station station7 = new Station();
			Station station8 = new Station();
			Station station9 = new Station();
			Station station10 = new Station();
			Station station11 = new Station();
			Station station12 = new Station();
			Station station13 = new Station();
			Station station14 = new Station();
			Station station15 = new Station();
			Station station16 = new Station();
			Station station17 = new Station();
			Station station18 = new Station();
			Station station19 = new Station();
			Station station20 = new Station();
			
			
			//Création réseau
			int idReseau = reseauDAO.createReseauReturnId("Test reseau");
			reseau = reseauDAO.getReseauByID(idReseau);
			//Création Ligne
			int idLigne = ligneDAO.createLigneReturnId("Ligne Test 1", "test", reseau);
			ligne = ligneDAO.getLigneByID(idLigne);
			//Création Train
			int idTrain = trainDAO.createTrainReturnId("Train test 1", 200.23333333, 344.55533322, 1, ligne);
			train1 = trainDAO.getTrainByID(idTrain);
			idTrain = trainDAO.createTrainReturnId("Train test 2", 300.23333333, 222.55533322, 1, ligne);
			train2 = trainDAO.getTrainByID(idTrain);
			idTrain = trainDAO.createTrainReturnId("Train test 3", 50.23333333, 150.55533322, 1, ligne);
			train3 = trainDAO.getTrainByID(idTrain);
			//Création Station
			int idStation1 = stationDAO.createStationReturnId("Station test 1", "Commentaire test", 50.00, 10.00, ligne);
			station1 = stationDAO.getStationByID(idStation1);
				trainHoraireStationDAO.createTrainHoraire("8:00", "9:00", "10:00", station1, train1);
				trainHoraireStationDAO.createTrainHoraire("8:05", "9:07", "10:10", station1, train2);
				trainHoraireStationDAO.createTrainHoraire("8:10", "9:14", "10:20", station1, train3);
			int idStation2 = stationDAO.createStationReturnId("Station test 2", "Commentaire test", 100.00, 20.00, ligne);
			station2 = stationDAO.getStationByID(idStation2);
				trainHoraireStationDAO.createTrainHoraire("8:07", "9:05", "10:10", station2, train1);
				trainHoraireStationDAO.createTrainHoraire("8:12", "9:12", "10:20", station2, train2);
				trainHoraireStationDAO.createTrainHoraire("8:17", "9:19", "10:30", station2, train3);
			int idStation3 = stationDAO.createStationReturnId("Station test 3", "Commentaire test", 150.00, 15.00, ligne);
			station3 = stationDAO.getStationByID(idStation3);
				trainHoraireStationDAO.createTrainHoraire("8:25", "9:25", "10:20", station3, train1);
				trainHoraireStationDAO.createTrainHoraire("8:30", "9:30", "10:30", station3, train2);
				trainHoraireStationDAO.createTrainHoraire("8:35", "9:35", "10:40", station3, train3);
			int idStation4 = stationDAO.createStationReturnId("Station test 4", "Commentaire test", 200.00, 35.00, ligne);
			station4 = stationDAO.getStationByID(idStation4);
				trainHoraireStationDAO.createTrainHoraire("8:37", "9:40", "10:30", station4, train1);
				trainHoraireStationDAO.createTrainHoraire("8:42", "9:47", "10:40", station4, train2);
				trainHoraireStationDAO.createTrainHoraire("8:47", "9:54", "10:50", station4, train3);
			int	idStation5 = stationDAO.createStationReturnId("Station test 5", "Commentaire test", 75.00, 20.00, ligne);
			station5 = stationDAO.getStationByID(idStation5);
				trainHoraireStationDAO.createTrainHoraire("8:50", "10:00", "10:40", station5, train1);
				trainHoraireStationDAO.createTrainHoraire("8:55", "10:07", "10:50", station5, train2);
				trainHoraireStationDAO.createTrainHoraire("9:00", "10:15", "11:00", station5, train3);
			
			//Création Ligne
			Ligne ligne2 = new Ligne();
			int idLigne2 = ligneDAO.createLigneReturnId("Ligne Test 2", "test", reseau);
			ligne2 = ligneDAO.getLigneByID(idLigne2);
			//Création Train
			int idTrain4 = trainDAO.createTrainReturnId("Train test 4", 200.23333333, 344.55533322, 1, ligne2);
			train4 = trainDAO.getTrainByID(idTrain4);
			int idTrain5 = trainDAO.createTrainReturnId("Train test 5", 300.23333333, 222.55533322, 1, ligne2);
			train5 = trainDAO.getTrainByID(idTrain5);
			int idTrain6 = trainDAO.createTrainReturnId("Train test 6", 50.23333333, 150.55533322, 1, ligne2);
			train6 = trainDAO.getTrainByID(idTrain6);
			int idTrain7 = trainDAO.createTrainReturnId("Train test 7", 50.23333333, 150.55533322, 1, ligne2);
			train7 = trainDAO.getTrainByID(idTrain7);
			int idTrain8 = trainDAO.createTrainReturnId("Train test 8", 50.23333333, 150.55533322, 1, ligne2);
			train8 = trainDAO.getTrainByID(idTrain8);
			int idTrain9 = trainDAO.createTrainReturnId("Train test 9", 50.23333333, 150.55533322, 1, ligne2);
			train9 = trainDAO.getTrainByID(idTrain9);
			int	idStation6 = stationDAO.createStationReturnId("Station test 6", "Commentaire test", 75.00, 20.00, ligne2);
			station6 = stationDAO.getStationByID(idStation6);
				trainHoraireStationDAO.createTrainHoraire("06:00", "07:30", "08:00", station6, train4);
				trainHoraireStationDAO.createTrainHoraire("06:03:30", "07:35:00", "08:10", station6, train5);
				trainHoraireStationDAO.createTrainHoraire("22:00:00", "22:50:00", "22:33:00", station6, train6);				
				trainHoraireStationDAO.createTrainHoraire("06:46:12", "08:23:12", "09:07:12", station6, train7);
				trainHoraireStationDAO.createTrainHoraire("06:49:42", "08:21:12", "08:56:12", station6, train8);
			int	idStation7 = stationDAO.createStationReturnId("Station test 7", "Commentaire test", 75.00, 20.00, ligne2);
			station7 = stationDAO.getStationByID(idStation7);	
				trainHoraireStationDAO.createTrainHoraire("06:02:56", "07:33:26", "08:04:26", station7, train4);
				trainHoraireStationDAO.createTrainHoraire("06:06:26", "07:37:56", "08:12:56", station7, train5);
				trainHoraireStationDAO.createTrainHoraire("22:02:56", "22:53:26", "22:37:26", station7, train6);				
				trainHoraireStationDAO.createTrainHoraire("06:43:16", "08:19:46", "09:02:46", station7, train7);
				trainHoraireStationDAO.createTrainHoraire("06:46:46", "08:18:16", "08:53:16", station7, train8);
			int	idStation8 = stationDAO.createStationReturnId("Station test 8", "Commentaire test", 75.00, 20.00, ligne2);
			station8 = stationDAO.getStationByID(idStation8);
				trainHoraireStationDAO.createTrainHoraire("06:06:50", "07:37:50", "08:09:50", station8, train4);
				trainHoraireStationDAO.createTrainHoraire("06:10:20", "07:41:50", "08:16:50", station8, train5);
				trainHoraireStationDAO.createTrainHoraire("22:06:50", "22:57:50", "22:42:50", station8, train6);				
				trainHoraireStationDAO.createTrainHoraire("06:39:22", "08:15:22", "08:57:22", station8, train7);
				trainHoraireStationDAO.createTrainHoraire("06:42:52", "08:14:22", "08:49:22", station8, train8);
			int	idStation9 = stationDAO.createStationReturnId("Station test 9", "Commentaire test", 75.00, 20.00, ligne2);
			station9 = stationDAO.getStationByID(idStation9);								
				trainHoraireStationDAO.createTrainHoraire("06:10:16", "07:41:46", "08:14:46", station9, train4);
				trainHoraireStationDAO.createTrainHoraire("06:13:46", "07:45:16", "08:20:16", station9, train5);
				trainHoraireStationDAO.createTrainHoraire("22:10:16", "23:01:46", "22:47:46", station9, train6);				
				trainHoraireStationDAO.createTrainHoraire("06:35:56", "08:11:26", "08:52:26", station9, train7);
				trainHoraireStationDAO.createTrainHoraire("06:39:26", "08:10:56", "08:45:56", station9, train8);
			int	idStation10 = stationDAO.createStationReturnId("Station test 10", "Commentaire test", 75.00, 20.00, ligne2);
			station10 = stationDAO.getStationByID(idStation10);		
				trainHoraireStationDAO.createTrainHoraire("06:13:55", "07:45:55", "08:19:55", station10, train4);
				trainHoraireStationDAO.createTrainHoraire("06:17:25", "07:48:55", "08:23:55", station10, train5);
				trainHoraireStationDAO.createTrainHoraire("22:13:55", "23:05:55", "22:52:55", station10, train6);
				trainHoraireStationDAO.createTrainHoraire("06:32:17", "08:07:17", "08:47:17", station10, train7);
				trainHoraireStationDAO.createTrainHoraire("06:35:47", "08:07:17", "08:42:17", station10, train8);
				//trainHoraireStationDAO.createTrainHoraire("", "23:32:17", "", station10, train9);
			int	idStation11 = stationDAO.createStationReturnId("Station test 11", "Commentaire test", 75.00, 20.00, ligne2);
			station11 = stationDAO.getStationByID(idStation11);					
				trainHoraireStationDAO.createTrainHoraire("06:18:04", "07:50:34", "08:25:34", station11, train4);
				trainHoraireStationDAO.createTrainHoraire("06:21:34", "07:53:04", "08:28:04", station11, train5);
				trainHoraireStationDAO.createTrainHoraire("22:18:04", "23:10:34", "22:58:34", station11, train6);
				trainHoraireStationDAO.createTrainHoraire("06:28:08", "08:02:38", "08:41:38", station11, train7);
				trainHoraireStationDAO.createTrainHoraire("06:31:38", "08:03:08", "08:38:08", station11, train8);
				//trainHoraireStationDAO.createTrainHoraire("", "23:28:08", "", station11, train9);
			int	idStation12 = stationDAO.createStationReturnId("Station test 12", "Commentaire test", 75.00, 20.00, ligne2);
			station12 = stationDAO.getStationByID(idStation12);	
				trainHoraireStationDAO.createTrainHoraire("06:21:00", "07:54:00", "08:30:00", station12, train4);
				trainHoraireStationDAO.createTrainHoraire("06:24:30", "07:56:00", "08:31:00", station12, train5);
				trainHoraireStationDAO.createTrainHoraire("22:21:00", "23:14:00", "23:03:00", station12, train6);
				trainHoraireStationDAO.createTrainHoraire("06:25:12", "07:59:12", "08:37:12", station12, train7);
				trainHoraireStationDAO.createTrainHoraire("06:28:42", "08:00:12", "08:35:12", station12, train8);
				trainHoraireStationDAO.createTrainHoraire("", "23:25:12", "23:00:12", station12, train9);
			int	idStation13 = stationDAO.createStationReturnId("Station test 13", "Commentaire test", 75.00, 20.00, ligne2);
			station13 = stationDAO.getStationByID(idStation13);	
				trainHoraireStationDAO.createTrainHoraire("06:23:28", "07:56:58", "08:33:58", station13, train4);
				trainHoraireStationDAO.createTrainHoraire("06:26:58", "07:58:28", "08:33:28", station13, train5);
//				trainHoraireStationDAO.createTrainHoraire("22:23:28", "23:16:58", "", station13, train6);
				trainHoraireStationDAO.createTrainHoraire("06:22:44", "07:56:14", "08:33:14", station13, train7);
				trainHoraireStationDAO.createTrainHoraire("06:26:14", "07:57:44", "08:32:44", station13, train8);
//				trainHoraireStationDAO.createTrainHoraire("", "23:22:44", "22:57:44", station13, train9);
			int	idStation14 = stationDAO.createStationReturnId("Station test 14", "Commentaire test", 75.00, 20.00, ligne2);
			station14 = stationDAO.getStationByID(idStation14);	
				trainHoraireStationDAO.createTrainHoraire("06:26:24", "08:00:24", "08:38:24", station14, train4);
				trainHoraireStationDAO.createTrainHoraire("06:29:54", "08:01:24", "08:36:24", station14, train5);
//				trainHoraireStationDAO.createTrainHoraire("22:26:24", "23:20:24", "", station14, train6);
				trainHoraireStationDAO.createTrainHoraire("06:19:48", "07:52:48", "08:28:48", station14, train7);
				trainHoraireStationDAO.createTrainHoraire("06:23:18", "07:54:48", "08:29:48", station14, train8);
//				trainHoraireStationDAO.createTrainHoraire("", "23:19:48", "22:54:48", station14, train9);
			int	idStation15 = stationDAO.createStationReturnId("Station test 15", "Commentaire test", 75.00, 20.00, ligne2);
			station15 = stationDAO.getStationByID(idStation15);	
				trainHoraireStationDAO.createTrainHoraire("06:29:35", "08:04:05", "08:43:05", station15, train4);
				trainHoraireStationDAO.createTrainHoraire("06:33:05", "08:04:35", "08:39:35", station15, train5);
//				trainHoraireStationDAO.createTrainHoraire("22:29:35", "23:24:05", "", station15, train6);					
				trainHoraireStationDAO.createTrainHoraire("06:16:37", "07:49:07", "08:24:07", station15, train7);
				trainHoraireStationDAO.createTrainHoraire("06:20:07", "07:51:37", "08:26:37", station15, train8);
				trainHoraireStationDAO.createTrainHoraire("22:31:37", "23:16:37", "22:51:37", station15, train9);								
			int	idStation16 = stationDAO.createStationReturnId("Station test 16", "Commentaire test", 75.00, 20.00, ligne2);
			station16 = stationDAO.getStationByID(idStation16);	
				trainHoraireStationDAO.createTrainHoraire("06:33:58", "08:08:58", "08:43:58", station16, train4);
				trainHoraireStationDAO.createTrainHoraire("06:37:28", "08:08:58", "08:36:24", station16, train5);
//				trainHoraireStationDAO.createTrainHoraire("22:33:58", "23:28:58", "", station16, train6);	
				trainHoraireStationDAO.createTrainHoraire("06:12:14", "07:44:14", "08:22:14", station16, train7);
				trainHoraireStationDAO.createTrainHoraire("06:15:44", "07:47:14", "08:29:48", station16, train8);
				trainHoraireStationDAO.createTrainHoraire("22:27:14", "23:12:14", "22:47:14", station16, train9);
			int	idStation17 = stationDAO.createStationReturnId("Station test 17", "Commentaire test", 75.00, 20.00, ligne2);
			station17 = stationDAO.getStationByID(idStation17);
				trainHoraireStationDAO.createTrainHoraire("06:37:52", "08:13:22", "08:54:22", station17, train4);
				trainHoraireStationDAO.createTrainHoraire("06:41:22", "08:12:52", "08:47:52", station17, train5);
//				trainHoraireStationDAO.createTrainHoraire("", "23:33:22", "", station17, train6);	
				trainHoraireStationDAO.createTrainHoraire("06:08:20", "07:39:50", "08:12:50", station17, train7);
				trainHoraireStationDAO.createTrainHoraire("06:11:50", "07:43:20", "08:18:20", station17, train8);
				trainHoraireStationDAO.createTrainHoraire("22:23:20", "23:08:20", "22:43:20", station17, train9);					
			int	idStation18 = stationDAO.createStationReturnId("Station test 18", "Commentaire test", 75.00, 20.00, ligne2);
			station18 = stationDAO.getStationByID(idStation18);	
				trainHoraireStationDAO.createTrainHoraire("06:41:02", "08:17:02", "08:59:02", station18, train4);
				trainHoraireStationDAO.createTrainHoraire("06:44:32", "08:16:02", "08:51:02", station18, train5);
				//trainHoraireStationDAO.createTrainHoraire("", "", "", station14, train6);											
				trainHoraireStationDAO.createTrainHoraire("06:05:10", "07:36:10", "08:08:10", station18, train7);
				trainHoraireStationDAO.createTrainHoraire("06:08:40", "07:40:10", "08:15:10", station18, train8);
				trainHoraireStationDAO.createTrainHoraire("22:20:10", "23:05:10", "22:40:10", station18, train9);	
			int	idStation19 = stationDAO.createStationReturnId("Station test 19", "Commentaire test", 75.00, 20.00, ligne2);
			station19 = stationDAO.getStationByID(idStation19);			
				trainHoraireStationDAO.createTrainHoraire("06:43:44", "08:20:14", "09:03:14", station19, train4);
				trainHoraireStationDAO.createTrainHoraire("06:47:14", "08:18:44", "08:53:44", station19, train5);
				//trainHoraireStationDAO.createTrainHoraire("", "", "", station19, train6);						
				trainHoraireStationDAO.createTrainHoraire("06:02:28", "07:32:58", "08:03:58", station19, train7);
				trainHoraireStationDAO.createTrainHoraire("06:05:58", "07:37:28", "08:12:28", station19, train8);
				trainHoraireStationDAO.createTrainHoraire("22:17:28", "23:02:28", "22:37:28", station19, train9);
			
			int	idStation20 = stationDAO.createStationReturnId("Station test 20", "Commentaire test", 75.00, 20.00, ligne2);
			station20 = stationDAO.getStationByID(idStation20);				
				trainHoraireStationDAO.createTrainHoraire("06:46:12", "08:23:12", "09:07:12", station20, train4);
				trainHoraireStationDAO.createTrainHoraire("06:49:42", "08:21:12", "08:56:12", station20, train5);
				//trainHoraireStationDAO.createTrainHoraire("", "", "", station20, train6);
				trainHoraireStationDAO.createTrainHoraire("06:00:00", "07:30:00", "08:00:00", station20, train7);
				trainHoraireStationDAO.createTrainHoraire("06:03:30", "07:35:00", "08:10:00", station20, train8);
				trainHoraireStationDAO.createTrainHoraire("22:15:00", "23:00:00", "22:35:00", station20, train9);											
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
		if(action.equals("creerHoraireTest")){
			List<Ligne> ligneList = ligneDAO.listerLigne();
			request.logout();
			request.setAttribute("listeLigne",ligneList);
			this.getServletContext().getRequestDispatcher("/WEB-INF/creationHoraireTest.jsp").forward( request, response );
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
				for (int j=0;j<trainList.size(); j++){
					trainListStr=trainListStr+trainList.get(j).getIdTrain()+",";
				}
				trainListStr=trainListStr.substring(0, trainListStr.length()-1);
				trainListStr=trainListStr +")";
				List<TrainHoraireStation> trainHoraireStationList = trainHoraireStationDAO.listTrainHoraireStationByListTrain(trainListStr);
				String tableau[][] = new String[trainHoraireStationList.size()][6] ; 
				for(int i=0; i<trainHoraireStationList.size();i++){
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
				ArrayList<String> nomStationList = new ArrayList<String>(); 
				String nomStation;
				boolean check;
				for(int i=0; i<trainHoraireStationList.size();i++){
					check = false;
					nomStation = String.valueOf(trainHoraireStationList.get(i).getStation().getIdStation())+" - "+String.valueOf(trainHoraireStationList.get(i).getStation().getNomStation());
					for(int j=0; j<nomStationList.size();j++){
						if(nomStation.equals(nomStationList.get(j))){
							check=true;
						}
					}
					if (check==false){
						nomStationList.add(String.valueOf(trainHoraireStationList.get(i).getStation().getIdStation())+" - "+String.valueOf(trainHoraireStationList.get(i).getStation().getNomStation())); 
					}
				}
				ArrayList<String> nomTrainList = new ArrayList<String>(); 
				String nomTrain;
				for(int i=0; i<trainHoraireStationList.size();i++){
					check = false;
					nomTrain = String.valueOf(trainHoraireStationList.get(i).getTrain().getIdTrain())+" - "+String.valueOf(trainHoraireStationList.get(i).getTrain().getNomTrain());
					for(int j=0; j<nomTrainList.size();j++){
						if(nomTrain.equals(nomTrainList.get(j))){
							check=true;
						}
					}
					if (check==false){
						nomTrainList.add(String.valueOf(trainHoraireStationList.get(i).getTrain().getIdTrain())+" - "+String.valueOf(trainHoraireStationList.get(i).getTrain().getNomTrain()));
					}
				}
				String[][] tableauHoraireJO = new String[nomStationList.size()+1][nomTrainList.size()+1];
				String[][] tableauHoraireSamedi = new String[nomStationList.size()+1][nomTrainList.size()+1];
				String[][] tableauHoraireDimancheJF = new String[nomStationList.size()+1][nomTrainList.size()+1];
				tableauHoraireJO[0][0] =" ";
				tableauHoraireSamedi[0][0] =" ";
				tableauHoraireDimancheJF[0][0] =" ";
				for(int i=0; i<nomStationList.size(); i++){
					//System.out.println(nomStationList.get(i));
					tableauHoraireJO[i+1][0]=nomStationList.get(i);
					tableauHoraireSamedi[i+1][0]=nomStationList.get(i);
					tableauHoraireDimancheJF[i+1][0]=nomStationList.get(i);
				}	
				for(int i=0; i<nomTrainList.size(); i++){
					//System.out.println(nomTrainList.get(i));
					tableauHoraireJO[0][i+1]=nomTrainList.get(i);
					tableauHoraireSamedi[0][i+1]=nomTrainList.get(i);
					tableauHoraireDimancheJF[0][i+1]=nomTrainList.get(i);
				}
				for(int i=0; i<trainHoraireStationList.size(); i++){
					nomTrain = String.valueOf(trainHoraireStationList.get(i).getTrain().getIdTrain())+" - "+String.valueOf(trainHoraireStationList.get(i).getTrain().getNomTrain());
					nomStation = String.valueOf(trainHoraireStationList.get(i).getStation().getIdStation())+" - "+String.valueOf(trainHoraireStationList.get(i).getStation().getNomStation());			
					for(int j=0; j<nomStationList.size(); j++){
						if(tableauHoraireJO[j+1][0].equals(nomStation)){
							for(int n=0; n<nomTrainList.size(); n++){
								if(tableauHoraireJO[0][n+1].equals(nomTrain)){
									tableauHoraireJO[j+1][n+1]=trainHoraireStationList.get(i).getHeureJO();
									tableauHoraireSamedi[j+1][n+1]=trainHoraireStationList.get(i).getHeureSamedi();
									tableauHoraireDimancheJF[j+1][n+1]=trainHoraireStationList.get(i).getHeureDimancheJF();
								}
							}
						}		
					}
				}
				
				String tableauJOStr ="";
				String tableauSamediStr ="";
				String tableauDimancheJFStr ="";
				String GraphJO ="";
				String GraphSamedi ="";
				String GraphDimancheJF ="";
				for(int j=0; j<nomStationList.size()+1; j++){
					for(int i=0; i<nomTrainList.size()+1; i++){
//						System.out.print(tableauHoraireJO[j][i]);
						tableauJOStr = tableauJOStr +tableauHoraireJO[j][i]+"|";
						tableauSamediStr = tableauSamediStr +tableauHoraireSamedi[j][i]+"|";
						tableauDimancheJFStr = tableauDimancheJFStr +tableauHoraireDimancheJF[j][i]+"|";
					}
//					tableauJOStr = tableauJOStr + j;
//					tableauSamediStr = tableauSamediStr + j;
//					tableauDimancheJFStr = tableauDimancheJFStr + j;
					tableauJOStr = tableauJOStr.substring(0, tableauJOStr.length()-1);
					tableauSamediStr = tableauSamediStr.substring(0, tableauSamediStr.length()-1);
					tableauDimancheJFStr = tableauDimancheJFStr.substring(0, tableauDimancheJFStr.length()-1);
					tableauJOStr = tableauJOStr +"//";
					tableauSamediStr = tableauSamediStr +"//";
					tableauDimancheJFStr = tableauDimancheJFStr +"//";
				}
				tableauJOStr = tableauJOStr.substring(0, tableauJOStr.length()-2);
				tableauSamediStr = tableauSamediStr.substring(0, tableauSamediStr.length()-2);
				tableauDimancheJFStr = tableauDimancheJFStr.substring(0, tableauDimancheJFStr.length()-2);
				String[] timeJO;
				String[] timeSamedi;
				String[] timeDimancheJF;
				String idTrains;
				int compteur=0;
				//List des trains récupérée ==> trainList
				for (int i=0; i<trainList.size();i++){
					idTrains = "("+trainList.get(i).getIdTrain()+")";
					trainHoraireStationList = trainHoraireStationDAO.listTrainHoraireStationByListTrain(idTrains);
					GraphJO = GraphJO + trainList.get(i).getIdTrain() +" - " +trainList.get(i).getNomTrain() +"|";
					GraphSamedi = GraphSamedi + trainList.get(i).getIdTrain() +" - " +trainList.get(i).getNomTrain() +"|";
					GraphDimancheJF = GraphDimancheJF + trainList.get(i).getIdTrain() +" - " +trainList.get(i).getNomTrain() +"|";
					for(int j=0; j<trainHoraireStationList.size(); j++){
						compteur = 0;
						for(int n=0; n<nomStationList.size(); n++){
							nomStation = trainHoraireStationList.get(j).getStation().getIdStation()+ " - "+trainHoraireStationList.get(j).getStation().getNomStation();
							if(nomStationList.get(n).equals(nomStation)){
								compteur = n;
							}
						}
						if(trainHoraireStationList.get(j).getHeureJO()!=null){
							timeJO = trainHoraireStationList.get(j).getHeureJO().split(":");
							GraphJO = GraphJO + timeJO[0]+" - "+timeJO[1]+" - "+ compteur+"//";
						}
//						else{
//							GraphJO = GraphJO + "0"+" - "+"0"+" - "+ compteur+"//";
//						}
						if(trainHoraireStationList.get(j).getHeureSamedi()!=null){
							timeSamedi = trainHoraireStationList.get(j).getHeureSamedi().split(":");
							GraphSamedi = GraphSamedi + timeSamedi[0]+" - "+timeSamedi[1]+" - "+ compteur+"//";
						}
//						else{
//							GraphSamedi = GraphSamedi + "0"+" - "+"0"+" - "+ compteur+"//";
//						}
						if(trainHoraireStationList.get(j).getHeureDimancheJF()!=null){
							timeDimancheJF = trainHoraireStationList.get(j).getHeureDimancheJF().split(":");
							GraphDimancheJF = GraphDimancheJF + timeDimancheJF[0]+" - "+timeDimancheJF[1]+" - "+ compteur+"//";
						}
//						else{
//							GraphDimancheJF = GraphDimancheJF + "0"+" - "+"0"+" - "+ compteur+"//";
//						}	
					}
					GraphJO = GraphJO.substring(0, GraphJO.length()-2);
					GraphJO = GraphJO + "##";
					GraphSamedi = GraphSamedi.substring(0, GraphSamedi.length()-2);
					GraphSamedi = GraphSamedi + "##";
					GraphDimancheJF = GraphDimancheJF.substring(0, GraphDimancheJF.length()-2);
					GraphDimancheJF = GraphDimancheJF + "##";
				}
				GraphJO = GraphJO.substring(0, GraphJO.length()-2);
				GraphSamedi = GraphSamedi.substring(0, GraphSamedi.length()-2);
				GraphDimancheJF = GraphDimancheJF.substring(0, GraphDimancheJF.length()-2);
//				System.out.println(GraphJO);
				Ligne ligne = ligneDAO.getLigneByID(idLgn);
				List<Ligne> ligneList = ligneDAO.listerLigne();
				request.logout();
				request.setAttribute("listeLigne", ligneList);
				request.setAttribute("GraphJO", GraphJO);
				request.setAttribute("GraphSamedi", GraphSamedi);
				request.setAttribute("GraphDimancheJF", GraphDimancheJF);
				
				request.setAttribute("NomLigne", ligne.getNomLigne());
				request.setAttribute("ListHoraireJOStr", tableauJOStr);
				request.setAttribute("ListHoraireSamediStr", tableauSamediStr);
				request.setAttribute("ListHoraireDimancheJFStr", tableauDimancheJFStr);
				request.setAttribute("StationList", nomStationList);
				
//				request.setAttribute("Parametres", parametreHoraire);
				this.getServletContext().getRequestDispatcher( "/WEB-INF/visualisationHoraire.jsp").forward( request, response );		
			}
			else{
				List<Ligne> ligneList = ligneDAO.listerLigne();
				request.setAttribute("listeLigne",ligneList);
				this.getServletContext().getRequestDispatcher( "/WEB-INF/creationHoraire.jsp").forward( request, response );	
			}
		}	
		if(action.equals("visualiserHoraire")){
			int idLgn = Integer.parseInt(request.getParameter("idLigne"));
			TrainHoraireStationDAO trainHoraireStationDAO = new TrainHoraireStationDAO();
			TrainDAO trainDAO = new TrainDAO();
			List<Train> trainList = trainDAO.listerTrainByLigne(idLgn);
			String trainListStr="(";
			for (int j=0;j<trainList.size(); j++){
				trainListStr=trainListStr+trainList.get(j).getIdTrain()+",";
			}
			trainListStr=trainListStr.substring(0, trainListStr.length()-1);
			trainListStr=trainListStr +")";
			List<TrainHoraireStation> trainHoraireStationList = trainHoraireStationDAO.listTrainHoraireStationByListTrain(trainListStr);
			String tableau[][] = new String[trainHoraireStationList.size()][6] ; 
			for(int i=0; i<trainHoraireStationList.size();i++){
				tableau[i][0] = String.valueOf(idLgn);
				tableau[i][1] = trainHoraireStationList.get(i).getTrain().getIdTrain() + "-"+trainHoraireStationList.get(i).getTrain().getNomTrain();
				tableau[i][2] = trainHoraireStationList.get(i).getStation().getIdStation()+"-"+trainHoraireStationList.get(i).getStation().getNomStation();
				tableau[i][3] = trainHoraireStationList.get(i).getHeureJO();
				tableau[i][4] = trainHoraireStationList.get(i).getHeureSamedi();
				tableau[i][5] = trainHoraireStationList.get(i).getHeureDimancheJF();
			}
			ArrayList<String> nomStationList = new ArrayList<String>(); 
			String nomStation;
			boolean check;
			for(int i=0; i<trainHoraireStationList.size();i++){
				check = false;
				nomStation = String.valueOf(trainHoraireStationList.get(i).getStation().getIdStation())+" - "+String.valueOf(trainHoraireStationList.get(i).getStation().getNomStation());
				for(int j=0; j<nomStationList.size();j++){
					if(nomStation.equals(nomStationList.get(j))){
						check=true;
					}
				}
				if (check==false){
					nomStationList.add(String.valueOf(trainHoraireStationList.get(i).getStation().getIdStation())+" - "+String.valueOf(trainHoraireStationList.get(i).getStation().getNomStation())); 
				}
			}
			ArrayList<String> nomTrainList = new ArrayList<String>(); 
			String nomTrain;
			for(int i=0; i<trainHoraireStationList.size();i++){
				check = false;
				nomTrain = String.valueOf(trainHoraireStationList.get(i).getTrain().getIdTrain())+" - "+String.valueOf(trainHoraireStationList.get(i).getTrain().getNomTrain());
				for(int j=0; j<nomTrainList.size();j++){
					if(nomTrain.equals(nomTrainList.get(j))){
						check=true;
					}
				}
				if (check==false){
					nomTrainList.add(String.valueOf(trainHoraireStationList.get(i).getTrain().getIdTrain())+" - "+String.valueOf(trainHoraireStationList.get(i).getTrain().getNomTrain()));
				}
			}
			String[][] tableauHoraireJO = new String[nomStationList.size()+1][nomTrainList.size()+1];
			String[][] tableauHoraireSamedi = new String[nomStationList.size()+1][nomTrainList.size()+1];
			String[][] tableauHoraireDimancheJF = new String[nomStationList.size()+1][nomTrainList.size()+1];
			tableauHoraireJO[0][0] =" ";
			tableauHoraireSamedi[0][0] =" ";
			tableauHoraireDimancheJF[0][0] =" ";
			for(int i=0; i<nomStationList.size(); i++){
				//System.out.println(nomStationList.get(i));
				tableauHoraireJO[i+1][0]=nomStationList.get(i);
				tableauHoraireSamedi[i+1][0]=nomStationList.get(i);
				tableauHoraireDimancheJF[i+1][0]=nomStationList.get(i);
			}	
			for(int i=0; i<nomTrainList.size(); i++){
				//System.out.println(nomTrainList.get(i));
				tableauHoraireJO[0][i+1]=nomTrainList.get(i);
				tableauHoraireSamedi[0][i+1]=nomTrainList.get(i);
				tableauHoraireDimancheJF[0][i+1]=nomTrainList.get(i);
			}
			for(int i=0; i<trainHoraireStationList.size(); i++){
				nomTrain = String.valueOf(trainHoraireStationList.get(i).getTrain().getIdTrain())+" - "+String.valueOf(trainHoraireStationList.get(i).getTrain().getNomTrain());
				nomStation = String.valueOf(trainHoraireStationList.get(i).getStation().getIdStation())+" - "+String.valueOf(trainHoraireStationList.get(i).getStation().getNomStation());			
				for(int j=0; j<nomStationList.size(); j++){
					if(tableauHoraireJO[j+1][0].equals(nomStation)){
						for(int n=0; n<nomTrainList.size(); n++){
							if(tableauHoraireJO[0][n+1].equals(nomTrain)){
								tableauHoraireJO[j+1][n+1]=trainHoraireStationList.get(i).getHeureJO();
								tableauHoraireSamedi[j+1][n+1]=trainHoraireStationList.get(i).getHeureSamedi();
								tableauHoraireDimancheJF[j+1][n+1]=trainHoraireStationList.get(i).getHeureDimancheJF();
							}
						}
					}		
				}
			}
			
			String tableauJOStr ="";
			String tableauSamediStr ="";
			String tableauDimancheJFStr ="";
			String GraphJO ="";
			String GraphSamedi ="";
			String GraphDimancheJF ="";
			for(int j=0; j<nomStationList.size()+1; j++){
				for(int i=0; i<nomTrainList.size()+1; i++){
//					System.out.print(tableauHoraireJO[j][i]);
					tableauJOStr = tableauJOStr +tableauHoraireJO[j][i]+"|";
					tableauSamediStr = tableauSamediStr +tableauHoraireSamedi[j][i]+"|";
					tableauDimancheJFStr = tableauDimancheJFStr +tableauHoraireDimancheJF[j][i]+"|";
				}
//				tableauJOStr = tableauJOStr + j;
//				tableauSamediStr = tableauSamediStr + j;
//				tableauDimancheJFStr = tableauDimancheJFStr + j;
				tableauJOStr = tableauJOStr.substring(0, tableauJOStr.length()-1);
				tableauSamediStr = tableauSamediStr.substring(0, tableauSamediStr.length()-1);
				tableauDimancheJFStr = tableauDimancheJFStr.substring(0, tableauDimancheJFStr.length()-1);
				tableauJOStr = tableauJOStr +"//";
				tableauSamediStr = tableauSamediStr +"//";
				tableauDimancheJFStr = tableauDimancheJFStr +"//";
			}
			tableauJOStr = tableauJOStr.substring(0, tableauJOStr.length()-2);
			tableauSamediStr = tableauSamediStr.substring(0, tableauSamediStr.length()-2);
			tableauDimancheJFStr = tableauDimancheJFStr.substring(0, tableauDimancheJFStr.length()-2);
			String[] timeJO;
			String[] timeSamedi;
			String[] timeDimancheJF;
			String idTrains;
			int compteur=0;
			//List des trains récupérée ==> trainList
			for (int i=0; i<trainList.size();i++){
				idTrains = "("+trainList.get(i).getIdTrain()+")";
				trainHoraireStationList = trainHoraireStationDAO.listTrainHoraireStationByListTrain(idTrains);
				GraphJO = GraphJO + trainList.get(i).getIdTrain() +" - " +trainList.get(i).getNomTrain() +"|";
				GraphSamedi = GraphSamedi + trainList.get(i).getIdTrain() +" - " +trainList.get(i).getNomTrain() +"|";
				GraphDimancheJF = GraphDimancheJF + trainList.get(i).getIdTrain() +" - " +trainList.get(i).getNomTrain() +"|";
				for(int j=0; j<trainHoraireStationList.size(); j++){
					compteur = 0;
					for(int n=0; n<nomStationList.size(); n++){
						nomStation = trainHoraireStationList.get(j).getStation().getIdStation()+ " - "+trainHoraireStationList.get(j).getStation().getNomStation();
						if(nomStationList.get(n).equals(nomStation)){
							compteur = n;
						}
					}
					if(trainHoraireStationList.get(j).getHeureJO()!=null){
						timeJO = trainHoraireStationList.get(j).getHeureJO().split(":");
						GraphJO = GraphJO + timeJO[0]+" - "+timeJO[1]+" - "+ compteur+"//";
					}
//					else{
//						GraphJO = GraphJO + "0"+" - "+"0"+" - "+ compteur+"//";
//					}
					if(trainHoraireStationList.get(j).getHeureSamedi()!=null){
						timeSamedi = trainHoraireStationList.get(j).getHeureSamedi().split(":");
						GraphSamedi = GraphSamedi + timeSamedi[0]+" - "+timeSamedi[1]+" - "+ compteur+"//";
					}
//					else{
//						GraphSamedi = GraphSamedi + "0"+" - "+"0"+" - "+ compteur+"//";
//					}
					if(trainHoraireStationList.get(j).getHeureDimancheJF()!=null){
						timeDimancheJF = trainHoraireStationList.get(j).getHeureDimancheJF().split(":");
						GraphDimancheJF = GraphDimancheJF + timeDimancheJF[0]+" - "+timeDimancheJF[1]+" - "+ compteur+"//";
					}
//					else{
//						GraphDimancheJF = GraphDimancheJF + "0"+" - "+"0"+" - "+ compteur+"//";
//					}	
				}
				GraphJO = GraphJO.substring(0, GraphJO.length()-2);
				GraphJO = GraphJO + "##";
				GraphSamedi = GraphSamedi.substring(0, GraphSamedi.length()-2);
				GraphSamedi = GraphSamedi + "##";
				GraphDimancheJF = GraphDimancheJF.substring(0, GraphDimancheJF.length()-2);
				GraphDimancheJF = GraphDimancheJF + "##";
			}
			GraphJO = GraphJO.substring(0, GraphJO.length()-2);
			GraphSamedi = GraphSamedi.substring(0, GraphSamedi.length()-2);
			GraphDimancheJF = GraphDimancheJF.substring(0, GraphDimancheJF.length()-2);
//			System.out.println(GraphJO);
			
			Ligne ligne = ligneDAO.getLigneByID(idLgn);
			List<Ligne> ligneList = ligneDAO.listerLigne();
			request.logout();
			request.setAttribute("listeLigne", ligneList);
			request.setAttribute("GraphJO", GraphJO);
			request.setAttribute("GraphSamedi", GraphSamedi);
			request.setAttribute("GraphDimancheJF", GraphDimancheJF);
			request.setAttribute("NomLigne", ligne.getNomLigne());
			request.setAttribute("ListHoraireJOStr", tableauJOStr);
			request.setAttribute("ListHoraireSamediStr", tableauSamediStr);
			request.setAttribute("ListHoraireDimancheJFStr", tableauDimancheJFStr);
			request.setAttribute("StationList", nomStationList);
			
//			request.setAttribute("Parametres", parametreHoraire);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/visualisationHoraire.jsp").forward( request, response );	
		}
	}
}
