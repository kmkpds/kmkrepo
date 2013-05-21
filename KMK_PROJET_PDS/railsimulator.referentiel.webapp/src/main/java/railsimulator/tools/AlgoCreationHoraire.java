package railsimulator.tools;

import java.sql.Date;

import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Calendar;


import java.util.List;

import beans.Ligne;


import beans.Train;

import beans.Station;

import beans.TrainHoraireStation;

import beans.ParametreHoraire;


import dao.ParametreHoraireDAO;

import dao.StationDAO;

import dao.TrainDAO;

import dao.TrainHoraireStationDAO;

import java.util.Collections;

public class AlgoCreationHoraire {

	private TrainHoraireStation trainhorairestation;

	private TrainHoraireStationDAO trainhorairestationdao;

	private List<TrainHoraireStation> listTrainhorairestation;

	private Ligne ligne;

	private List<Train> listeTrain;

	private Train unTrain;

	private TrainDAO trainDAO;

	private ParametreHoraire param;

	private ParametreHoraireDAO paramDAO;

	private List<Station> listeStationLigne;

	private List<Station> listeStationLigneReverse;

	private StationDAO stationDAO;

	private Station station1;

	private Station station2;

	private Double tempsStation1a2;

	private Double distanceS1S2;

	private int idfirstStation;

	private Date date1;

	private Date date2;

	private Date tempsCadencement;

	private Date heurePremierTrain;

	private Date heureStationPrecedente;

	private SimpleDateFormat dateFormat;

	private List<Date> listeHeureTrainToutStation;

	private List<Double> listeDistanceStation2a2;

	private List<Double> listeDistanceStation2a2Reverse;

	private Double dist;

	private Double distReverse;

	private Calendar dateCalendar;

	private Calendar dateCalendar2;
	private Calendar dateCalendar3;

	private List<String> tabHoraires;
	private List<String> tabHorairesReverse;

	private List<String> tabHorairesDimancheJF;
	private List<String> tabHorairesDimancheJFReverse;

	private List<String> tabHorairesSamedi;
	private List<String> tabHorairesSamediReverse;

	private String tempsCadencementString;

	private Calendar dateCalendarCadencement;
	private Calendar dateCalendarCadencementSamedi;
	private Calendar dateCalendarCadencementDimanche;
	private Calendar dateCalendarSamedi;

	private Calendar dateCalendarDimanche;
	private boolean checkTempsArret;

	public AlgoCreationHoraire(int idParam) {

		trainhorairestationdao = new TrainHoraireStationDAO();
		param = new ParametreHoraire();

		paramDAO = new ParametreHoraireDAO();

		param = paramDAO.getParametreHoraireByID(idParam);

		int i = trainhorairestationdao.deleteAllTrainHoraireStationByLigne(param.getLigne().getIdLigne());

		// //System.out.println(i);
		if(param.getVitesseMoyenne()<200 && checkTempsArret()==true){
		int go = horaireTrainListStation(idParam);
		}
		else{
			System.out.println("probleme temps arret");
			System.out.println("Vitesse moyenne trop ŽlŽvŽe!");
			
		}
		
		

	}

	public int horaireTrainListStation(int idParam) {

		listeTrain = new ArrayList<Train>();

		param = new ParametreHoraire();

		paramDAO = new ParametreHoraireDAO();

		param = paramDAO.getParametreHoraireByID(idParam);

		trainDAO = new TrainDAO();

		listeTrain = trainDAO.listerTrainByLigne(param.getLigne().getIdLigne());

		tabHoraires = new ArrayList<String>();
		tabHorairesReverse = new ArrayList<String>();

		tabHorairesSamedi = new ArrayList<String>();

		tabHorairesDimancheJF = new ArrayList<String>();
		tabHorairesDimancheJFReverse = new ArrayList<String>();
		tabHorairesSamediReverse  = new ArrayList<String>();

		listeStationLigne = new ArrayList<Station>();

		listeStationLigneReverse = new ArrayList<Station>();

		stationDAO = new StationDAO();

		listeDistanceStation2a2 = new ArrayList<Double>();

		listeDistanceStation2a2Reverse = new ArrayList<Double>();

		listeStationLigne = stationDAO.listerStationByLigne(param.getLigne()
				.getIdLigne());

		listeStationLigneReverse.addAll(listeStationLigne);

		Collections.reverse(listeStationLigneReverse);

		// //System.out.println("ListStationLigne Reverse Apres CollectionReverse id1= "+listeStationLigneReverse.get(0).getIdStation());

		for (int i = 0; i < listeStationLigne.size() - 1; i++) {

			//System.out.println(listeStationLigne.size());

			dist = getDistancePol(listeStationLigne.get(i).getLatitude(),
					listeStationLigne.get(i + 1).getLatitude(),
					listeStationLigne.get(i).getLongitude(), listeStationLigne
							.get(i + 1).getLongitude());

			distReverse = getDistancePol(listeStationLigneReverse.get(i)
					.getLatitude(), listeStationLigneReverse.get(i + 1)
					.getLatitude(), listeStationLigneReverse.get(i)
					.getLongitude(), listeStationLigneReverse.get(i + 1)
					.getLongitude());

			// //System.out.println("id Station= "+listeStationLigne.get(i).getIdStation());

			listeDistanceStation2a2
					.add(dist * 3600 / param.getVitesseMoyenne());

			listeDistanceStation2a2Reverse.add(calculTempsStation1a2(
					listeStationLigneReverse.get(i),
					listeStationLigneReverse.get(i + 1),
					param.getVitesseMoyenne()));


		}

		String heurePremierTrain = param.getHeurePremierTrainJO();

		tabHoraires.clear();

		tabHoraires.add(heurePremierTrain);
		tabHorairesReverse.add(heurePremierTrain);

		String heurePremierTrainSamedi = param.getHeurePremierTrainSamedi();

		tabHorairesSamedi.clear();

		tabHorairesSamedi.add(heurePremierTrainSamedi);
		tabHorairesSamediReverse.add(heurePremierTrainSamedi);
		String heurePremierTrainDimanche = param
				.getHeurePremierTrainDimancheJF();

		tabHorairesDimancheJF.clear();

		tabHorairesDimancheJF.add(heurePremierTrainDimanche);
		tabHorairesDimancheJFReverse.add(heurePremierTrainDimanche);

		for (int j = 0; j < listeStationLigne.size() - 1; j++) {

			String tempsArret = param.getTempsStationnementJO();
			String tempsArretSamedi = param.getTempsStationnementSamedi();
			String tempsArretDimanche = param.getTempsStationnementDimancheJF();

			String splitTempsArret[] = tempsArret.split(":");

			String splitHeureTrain1[] = heurePremierTrain.split(":");

			String heureStationPrecedent = null;

			int size = listeDistanceStation2a2.size();


			tabHorairesDimancheJF.add(ajoutTemps(tabHorairesDimancheJF.get(j),
					listeDistanceStation2a2.get(j), tempsArretDimanche));
			tabHorairesDimancheJFReverse.add(ajoutTemps(tabHorairesDimancheJFReverse.get(j),
					listeDistanceStation2a2Reverse.get(j), tempsArretDimanche));

			tabHoraires.add(ajoutTemps(tabHoraires.get(j),
					listeDistanceStation2a2.get(j), tempsArret));
			tabHorairesReverse.add(ajoutTemps(tabHorairesReverse.get(j),
					listeDistanceStation2a2Reverse.get(j), tempsArret));

			tabHorairesSamedi.add(ajoutTemps(tabHorairesSamedi.get(j),
					listeDistanceStation2a2.get(j), tempsArretSamedi));
			tabHorairesSamediReverse.add(ajoutTemps(tabHorairesSamediReverse.get(j),
					listeDistanceStation2a2Reverse.get(j), tempsArretSamedi));


//			 System.out.println("tabhoraire Dimanche // Reverse = "+tabHorairesDimancheJF.get(j)+" // "+tabHorairesDimancheJFReverse.get(j));
//			 System.out.println("tabhoraire JO // Reverse = "+tabHoraires.get(j)+" // "+tabHorairesReverse.get(j));
//			 System.out.println("tabhoraire Samedi // Reverse = "+tabHorairesSamedi.get(j)+" // "+tabHorairesSamediReverse.get(j));


			// //System.out.println("size "+tabHoraires.get());
			
			//System.out.println(tabHoraires.get(j)+" //"+tabHorairesSamedi.get(j)+"//"+tabHorairesDimancheJF.get(j));

		}


		tempsCadencementString = param.getCadencementJO();

		String splitCadencement1[] = tempsCadencementString.split(":");

		int heureCadencement = Integer.parseInt(splitCadencement1[0]);

		int minCadencement = Integer.parseInt(splitCadencement1[1]);

		int secCadencement = Integer.parseInt(splitCadencement1[2]);

		dateCalendarCadencement = Calendar.getInstance();

		dateCalendarCadencement.set(Calendar.HOUR_OF_DAY, heureCadencement);

		dateCalendarCadencement.set(Calendar.MINUTE, minCadencement);

		dateCalendarCadencement.set(Calendar.SECOND, secCadencement);

		dateFormat = new SimpleDateFormat("HH:mm:ss");

		String stringHeurePlusCadencement = dateFormat
				.format(dateCalendarCadencement.getTime());
		String tempsCadencementStringSamedi = param.getCadencementSamedi();

			String splitCadencementSamedi[] = tempsCadencementStringSamedi.split(":");

			int heureCadencementSamedi = Integer.parseInt(splitCadencementSamedi[0]);

			int minCadencementSamedi = Integer.parseInt(splitCadencementSamedi[1]);

			int secCadencementSamedi = Integer.parseInt(splitCadencementSamedi[2]);

			dateCalendarCadencementSamedi = Calendar.getInstance();

			dateCalendarCadencementSamedi.set(Calendar.HOUR_OF_DAY, heureCadencementSamedi);

			dateCalendarCadencementSamedi.set(Calendar.MINUTE, minCadencementSamedi);

			dateCalendarCadencementSamedi.set(Calendar.SECOND, secCadencementSamedi);

			dateFormat = new SimpleDateFormat("HH:mm:ss");

			String stringHeurePlusCadencementSamedi = dateFormat
					.format(dateCalendarCadencementSamedi.getTime());
		
		
		String tempsCadencementStringDimanche = param.getCadencementDimancheJF();

		String splitCadencementDimanche[] = tempsCadencementStringDimanche.split(":");

		int heureCadencementDimanche = Integer.parseInt(splitCadencementDimanche[0]);

		int minCadencementDimanche = Integer.parseInt(splitCadencementDimanche[1]);

		int secCadencementDimanche = Integer.parseInt(splitCadencementDimanche[2]);

		dateCalendarCadencementDimanche = Calendar.getInstance();

		dateCalendarCadencementDimanche.set(Calendar.HOUR_OF_DAY, heureCadencementDimanche);

		dateCalendarCadencementDimanche.set(Calendar.MINUTE, minCadencementDimanche);

		dateCalendarCadencementDimanche.set(Calendar.SECOND, secCadencementDimanche);

		dateFormat = new SimpleDateFormat("HH:mm:ss");

		String stringHeurePlusCadencementDimanche = dateFormat
				.format(dateCalendarCadencementDimanche.getTime());
		
		for (int i = 0; i < listeTrain.size(); i++) {
				
				//System.out.println((int) Math.round((listeTrain.size()/2)));
			
				if(i<(int) Math.round((listeTrain.size()/2))){
			for (int e = 0; e < tabHoraires.size(); e++) {

				dateCalendar = Calendar.getInstance();
				dateCalendar.set (Calendar.HOUR_OF_DAY,0);
				dateCalendar.set (Calendar.MINUTE,0);
				dateCalendar.set (Calendar.SECOND,0);
				dateCalendar.set (Calendar.MILLISECOND,0);

				String heureAvant = tabHoraires.get(e);
				//System.out.println("heure avant : "+heureAvant);

				String splitHeureTrain1[] = heureAvant.split(":");

				int heureTrain1 = Integer.parseInt(splitHeureTrain1[0]);

				int minHeureTrain1 = Integer.parseInt(splitHeureTrain1[1]);

				int secHeureTrain1 = Integer.parseInt(splitHeureTrain1[2]);

				dateCalendarSamedi = Calendar.getInstance();

				String heureAvantSamedi = tabHorairesSamedi.get(e);

				 //System.out.println("heureAvantSAmedi avant traitement "+heureAvantSamedi);

				String splitHeureTrain1Samedi[] = heureAvantSamedi.split(":");

				int heureTrain1Samedi = Integer
						.parseInt(splitHeureTrain1Samedi[0]);

				int minHeureTrain1Samedi = Integer
						.parseInt(splitHeureTrain1Samedi[1]);

				int secHeureTrain1Samedi = Integer
						.parseInt(splitHeureTrain1Samedi[2]);

				dateCalendarDimanche = Calendar.getInstance();

				String heureAvantDimanche = tabHorairesDimancheJF.get(e);

				 //System.out.println("heureAvantDimanche avant traitement "+heureAvantDimanche);

				String splitHeureTrain1Dimanche[] = heureAvantDimanche
						.split(":");

				int heureTrain1Dimanche = Integer
						.parseInt(splitHeureTrain1Dimanche[0]);

				int minTrain1Dimanche = Integer
						.parseInt(splitHeureTrain1Dimanche[1]);

				int secTrain1Dimanche = Integer
						.parseInt(splitHeureTrain1Dimanche[2]);

				trainhorairestation = new TrainHoraireStation();

				trainhorairestationdao = new TrainHoraireStationDAO();

				if (i == 0) {

					// //System.out.println("e= "+e);

					trainhorairestationdao.createTrainHoraire(
							tabHoraires.get(e), tabHorairesSamedi.get(e),
							tabHorairesDimancheJF.get(e),
							listeStationLigne.get(e), listeTrain.get(i));

				}

				if (i > 0) {
					//System.out.println("H train 1 "+heureTrain1);

					dateCalendar.set(Calendar.HOUR_OF_DAY,heureTrain1);

					dateCalendar.set(Calendar.MINUTE, minHeureTrain1);

					dateCalendar.set(Calendar.SECOND, secHeureTrain1);

					dateCalendar.add(Calendar.HOUR_OF_DAY,heureCadencement * i);
					System.out.println("heure cadencement "+heureCadencement);

					dateCalendar.add(Calendar.MINUTE, minCadencement * i);

					dateCalendar.add(Calendar.SECOND, secCadencement * i);

					 //System.out.println("h   avant calendar.Add "+dateCalendar.getTime());
					dateFormat = new SimpleDateFormat("HH:mm:ss");

					heureAvant = dateFormat.format(dateCalendar.getTime());

					 //System.out.println("h apres  calendar.Add "+heureAvant);

					dateCalendarSamedi.set(Calendar.HOUR_OF_DAY,
						 heureTrain1Samedi);

					dateCalendarSamedi.set(Calendar.MINUTE,
							minHeureTrain1Samedi);

					dateCalendarSamedi.set(Calendar.SECOND,
							secHeureTrain1Samedi);

					dateCalendarSamedi.add(Calendar.HOUR_OF_DAY,heureCadencementSamedi * i);

					dateCalendarSamedi.add(Calendar.MINUTE, minCadencementSamedi * i);

					dateCalendarSamedi.add(Calendar.SECOND, secCadencementSamedi * i);

					dateFormat = new SimpleDateFormat("HH:mm:ss");

					heureAvantSamedi = dateFormat.format(dateCalendarSamedi
							.getTime());

					dateCalendarDimanche.set(Calendar.HOUR_OF_DAY,
						 heureTrain1Dimanche);

					dateCalendarDimanche
							.set(Calendar.MINUTE, minTrain1Dimanche);

					dateCalendarDimanche
							.set(Calendar.SECOND, secTrain1Dimanche);

					dateCalendarDimanche.add(Calendar.HOUR_OF_DAY,heureCadencementDimanche
							* i);

					dateCalendarDimanche.add(Calendar.MINUTE, minCadencementDimanche
							* i);

					dateCalendarDimanche.add(Calendar.SECOND, secCadencementDimanche
							* i);

					dateFormat = new SimpleDateFormat("HH:mm:ss");

					heureAvantDimanche = dateFormat.format(dateCalendarDimanche
							.getTime());

					int idHoraire = trainhorairestationdao
							.createTrainHoraireReturnId(heureAvant,
									heureAvantSamedi, heureAvantDimanche,
									listeStationLigne.get(e), listeTrain.get(i));

				}

			}}
				 if (i>=(int) Math.round((listeTrain.size()/2)) && i<listeTrain.size()) {
					int nbTrainSur2 = (int) Math.round((listeTrain.size()/2));
//					System.out.println("nbtrain/2 "+nbTrainSur2);
//					System.out.println(listeTrain.size()-nbTrainSur2);
//					System.out.println("i ="+i);
					int cptd = i-nbTrainSur2;
				//	System.out.println("CPTD = "+cptd);
					
					//System.out.println("AJOUT REVERSE");
					

					for (int e = 0; e < tabHorairesReverse.size(); e++) {
						int cptReverse = 1;
							dateCalendar = Calendar.getInstance();
							dateCalendar.set (Calendar.HOUR_OF_DAY,0);
							dateCalendar.set (Calendar.MINUTE,0);
							dateCalendar.set (Calendar.SECOND,0);
							dateCalendar.set (Calendar.MILLISECOND,0);

							String heureAvant = tabHorairesReverse.get(e);

							String splitHeureTrain1[] = heureAvant.split(":");

							int heureTrain1 = Integer.parseInt(splitHeureTrain1[0]);

							int minHeureTrain1 = Integer.parseInt(splitHeureTrain1[1]);

							int secHeureTrain1 = Integer.parseInt(splitHeureTrain1[2]);

							dateCalendarSamedi = Calendar.getInstance();

							String heureAvantSamedi = tabHorairesSamediReverse.get(e);

							String splitHeureTrain1Samedi[] = heureAvantSamedi.split(":");

							int heureTrain1Samedi = Integer
									.parseInt(splitHeureTrain1Samedi[0]);

							int minHeureTrain1Samedi = Integer
									.parseInt(splitHeureTrain1Samedi[1]);

							int secHeureTrain1Samedi = Integer
									.parseInt(splitHeureTrain1Samedi[2]);

							dateCalendarDimanche = Calendar.getInstance();

							String heureAvantDimanche = tabHorairesDimancheJFReverse.get(e);

							String splitHeureTrain1Dimanche[] = heureAvantDimanche
									.split(":");

							int heureTrain1Dimanche = Integer
									.parseInt(splitHeureTrain1Dimanche[0]);

							int minTrain1Dimanche = Integer
									.parseInt(splitHeureTrain1Dimanche[1]);

							int secTrain1Dimanche = Integer
									.parseInt(splitHeureTrain1Dimanche[2]);

							trainhorairestation = new TrainHoraireStation();

							trainhorairestationdao = new TrainHoraireStationDAO();

							if (i == (int) Math.round((listeTrain.size()/2))) {

								trainhorairestationdao.createTrainHoraire(
										tabHorairesReverse.get(e), tabHorairesSamediReverse.get(e),
										tabHorairesDimancheJFReverse.get(e),
										listeStationLigneReverse.get(e), listeTrain.get(i));

							}

							if (i > (int) Math.round((listeTrain.size()/2)) && i<listeTrain.size() ) {

								dateCalendar.set(Calendar.HOUR_OF_DAY, heureTrain1);

								dateCalendar.set(Calendar.MINUTE, minHeureTrain1);

								dateCalendar.set(Calendar.SECOND, secHeureTrain1);

								dateCalendar.add(Calendar.HOUR_OF_DAY,heureCadencement * cptd);

								dateCalendar.add(Calendar.MINUTE, minCadencement * cptd);

								dateCalendar.add(Calendar.SECOND, secCadencement * cptd);

								dateFormat = new SimpleDateFormat("HH:mm:ss");

								heureAvant = dateFormat.format(dateCalendar.getTime());

								dateCalendarSamedi.set(Calendar.HOUR_OF_DAY,
									 heureTrain1Samedi);

								dateCalendarSamedi.set(Calendar.MINUTE,
										minHeureTrain1Samedi);

								dateCalendarSamedi.set(Calendar.SECOND,
										secHeureTrain1Samedi);

								dateCalendarSamedi.add(Calendar.HOUR_OF_DAY, heureCadencementSamedi * cptd);

								dateCalendarSamedi.add(Calendar.MINUTE, minCadencementSamedi * cptd);

								dateCalendarSamedi.add(Calendar.SECOND, secCadencementSamedi * cptd);

								dateFormat = new SimpleDateFormat("HH:mm:ss");

								heureAvantSamedi = dateFormat.format(dateCalendarSamedi
										.getTime());

								dateCalendarDimanche.set(Calendar.HOUR_OF_DAY,
									 heureTrain1Dimanche);

								dateCalendarDimanche
										.set(Calendar.MINUTE, minTrain1Dimanche);

								dateCalendarDimanche
										.set(Calendar.SECOND, secTrain1Dimanche);

								dateCalendarDimanche.add(Calendar.HOUR_OF_DAY,heureCadencementDimanche
										* cptd);

								dateCalendarDimanche.add(Calendar.MINUTE, minCadencementDimanche
										* cptd);

								dateCalendarDimanche.add(Calendar.SECOND, secCadencementDimanche
										* cptd);

								dateFormat = new SimpleDateFormat("HH:mm:ss");

								heureAvantDimanche = dateFormat.format(dateCalendarDimanche
										.getTime());

								int idHoraire = trainhorairestationdao
										.createTrainHoraireReturnId(heureAvant,
												heureAvantSamedi, heureAvantDimanche,
												listeStationLigneReverse.get(e), listeTrain.get(i));

								
							}
							cptReverse++;
							System.out.println("Compteur reverse "+cptReverse);
				}
				
					}

			

		}

		return 1;

	}

	public ParametreHoraire getParametreHoraireById(int id)
			throws ParseException {

		param = new ParametreHoraire();

		paramDAO = new ParametreHoraireDAO();

		param = paramDAO.getParametreHoraireByID(id);

		return param;

	}

	public String ajoutTemps(String heureAvant, Double tempsParcours,
			String tempsArret) {

		String s;
		
		String splitTempsArret[] = tempsArret.split(":");

		String splitHeureTrain1[] = heureAvant.split(":");

		int heureTrain1 = Integer.parseInt(splitHeureTrain1[0]);

		int minHeureTrain1 = Integer.parseInt(splitHeureTrain1[1]);

		int secHeureTrain1 = Integer.parseInt(splitHeureTrain1[2]);

		int heureTempsArret = Integer.parseInt(splitTempsArret[0]);

		int minTempsArret = Integer.parseInt(splitTempsArret[1]);

		int secTempsArret = Integer.parseInt(splitTempsArret[2]);

		int tempsParcoursrounded = (int) Math.round(tempsParcours);

		dateCalendar = Calendar.getInstance();
		dateCalendar.set (Calendar.HOUR_OF_DAY,0);
		dateCalendar.set (Calendar.MINUTE,0);
		dateCalendar.set (Calendar.SECOND,0);
		dateCalendar.set (Calendar.MILLISECOND,0);
		System.out.println("DateCalendar getInstance "+dateCalendar.getTime());

		dateCalendar.set(Calendar.HOUR_OF_DAY,heureTrain1);
		System.out.println(dateCalendar.getTime());
		dateCalendar.set(Calendar.MINUTE, minHeureTrain1);

		dateCalendar.set(Calendar.SECOND, secHeureTrain1);
		System.out.println("heure train1 int "+heureTrain1);
		

		 System.out.println("heure train1 getTime "+dateCalendar.getTime());

		Calendar heureCalcule = Calendar.getInstance();
		System.out.println("heure temps arret "+heureTempsArret);

		//dateCalendar.add(Calendar.HOUR_OF_DAY,heureTempsArret);

		dateCalendar.add(Calendar.MINUTE, minTempsArret);

		dateCalendar.add(Calendar.SECOND, secTempsArret + tempsParcoursrounded);

		heureCalcule = dateCalendar;
		//System.out.println("heure calculee.getTime "+heureCalcule.getTime());

		dateFormat = new SimpleDateFormat("HH:mm:ss");
		
		String stringHeureCalcule = dateFormat.format(heureCalcule.getTime());

		s = stringHeureCalcule;

		return s;

	}

	public List<Integer> getListIdTrainByLigne(int id) {

		listeTrain = new ArrayList<Train>();

		trainDAO = new TrainDAO();

		List<Integer> listidTrain = new ArrayList<Integer>();

		listeTrain = trainDAO.listerTrainByLigne(id);

		for (int i = 0; i < listeTrain.size(); i++) {

			listidTrain.add(listeTrain.get(i).getIdTrain());

		}

		return listidTrain;

	}


	public Double calculTempsStation1a2(Station s1, Station s2, int vmoyenne) {

		double distance;

		distance = getDistancePol(s1.getLatitude(), s2.getLatitude(),
				s1.getLongitude(), s2.getLongitude());

		tempsStation1a2 = distance * 3600 / vmoyenne;

		return tempsStation1a2;

	}
	
	
	public boolean checkTempsArret(){
		checkTempsArret=false;
		String heureDebut = this.param.getHeurePremierTrainJO();
		String heureFin = this.param.getHeureDernierTrainJO();
		String tempsArretJO = this.param.getTempsStationnementJO();
		
		String splitTempsArret[] = tempsArretJO.split(":");

		int heureTempsArret = Integer.parseInt(splitTempsArret[0]);

		int minHeureTempsArret = Integer.parseInt(splitTempsArret[1]);

		int secTempsArret = Integer.parseInt(splitTempsArret[2]);
		dateCalendar3 = Calendar.getInstance();
		dateCalendar3.set (Calendar.HOUR_OF_DAY,0);
		dateCalendar3.set (Calendar.MINUTE,0);
		dateCalendar3.set (Calendar.SECOND,0);
		dateCalendar3.set (Calendar.MILLISECOND,0);
		dateCalendar3.set(Calendar.HOUR_OF_DAY,heureTempsArret);
		
		dateCalendar3.set(Calendar.MINUTE, minHeureTempsArret);

		dateCalendar3.set(Calendar.SECOND, secTempsArret);
		Calendar dateCalendartemp = Calendar.getInstance();
		dateCalendartemp.set (Calendar.HOUR_OF_DAY,0);
		dateCalendartemp.set (Calendar.MINUTE,0);
		dateCalendartemp.set (Calendar.SECOND,0);
		dateCalendartemp.set (Calendar.MILLISECOND,0);
		
		long temp = (dateCalendar3.getTimeInMillis()-dateCalendartemp.getTimeInMillis());
		System.out.println("temp "+temp);
		//System.out.println(dateCalendar3.getTimeInMillis());
	
		String splitHeureTrainDebut[] = heureDebut.split(":");

		int heureDeb = Integer.parseInt(splitHeureTrainDebut[0]);

		int minHeureDeb = Integer.parseInt(splitHeureTrainDebut[1]);

		int secDeb = Integer.parseInt(splitHeureTrainDebut[2]);
		dateCalendar = Calendar.getInstance();
		dateCalendar.set (Calendar.HOUR_OF_DAY,0);
		dateCalendar.set (Calendar.MINUTE,0);
		dateCalendar.set (Calendar.SECOND,0);
		dateCalendar.set (Calendar.MILLISECOND,0);
		dateCalendar.set(Calendar.HOUR_OF_DAY,heureDeb);
		
		dateCalendar.set(Calendar.MINUTE, minHeureDeb);

		dateCalendar.set(Calendar.SECOND, secDeb);
		System.out.println(dateCalendar.getTime());
		
		String splitHeureTrainFin[] = heureFin.split(":");

		int intheureFin = Integer.parseInt(splitHeureTrainFin[0]);

		int minHeureFin = Integer.parseInt(splitHeureTrainFin[1]);

		int secFin = Integer.parseInt(splitHeureTrainFin[2]);
		dateCalendar2 = Calendar.getInstance();
		dateCalendar2.set (Calendar.HOUR_OF_DAY,0);
		dateCalendar2.set (Calendar.MINUTE,0);
		dateCalendar2.set (Calendar.SECOND,0);
		dateCalendar2.set (Calendar.MILLISECOND,0);
		dateCalendar2.set(Calendar.HOUR_OF_DAY,intheureFin);
		
		dateCalendar2.set(Calendar.MINUTE, minHeureFin);

		dateCalendar2.set(Calendar.SECOND, secFin);
		
System.out.println(dateCalendar2.getTime());
System.out.println((dateCalendar2.getTimeInMillis()-dateCalendar.getTimeInMillis()));
		//System.out.println(this.param.getTempsStationnementJO());/ (1000 * 60 * 60) % 24
long tot = ((dateCalendar2.getTimeInMillis()-dateCalendar.getTimeInMillis()));
System.out.println("tot "+tot);
long tests= tot-temp;
System.out.println("tests "+tests);
if(tests>0){
	checkTempsArret = true;
}
if(tests<0){
	System.out.println("Temps d'arret superieur au temps d'activitŽ de la ligne");
	checkTempsArret = false;
	
	
}
		
		
		return checkTempsArret;
	}

	public double getDistancePol(double latitudeA, double latitudeB,
			double longitudeA, double longitudeB) {

		double distance = (6356.752 * Math.acos(

		Math.sin(Math.toRadians(latitudeA))

		* Math.sin(Math.toRadians(latitudeB))

		+ Math.cos(Math.toRadians(latitudeA))

		* Math.cos(Math.toRadians(latitudeB))

		* Math.cos(Math.toRadians(longitudeA) - Math.toRadians(longitudeB)))

		);

		// //System.out.println("Distance = "+distance);

		return (distance);

	}

}