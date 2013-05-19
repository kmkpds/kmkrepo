package railsimulator.tools;
import java.sql.Date;

import java.text.DateFormat;

import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Calendar;

import java.util.Collection;

import java.util.List;

import java.util.Collections;

import beans.Ligne;

import beans.Reseau;

import beans.Train;

import beans.Station;

import beans.TrainHoraireStation;

import beans.ParametreHoraire;

import beans.Zone;

import dao.ParametreHoraireDAO;

import dao.StationDAO;

import dao.TrainDAO;

import dao.TrainHoraireStationDAO;



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

	private List<String> tabHoraires;

	private List<String> tabHorairesDimancheJF;

	private List<String> tabHorairesSamedi;

	private String tempsCadencementString;

	private Calendar dateCalendarCadencement;

	private Calendar dateCalendarSamedi;

	private Calendar dateCalendarDimanche;

	public AlgoCreationHoraire(int idParam) {

		trainhorairestationdao = new TrainHoraireStationDAO();

		int i = trainhorairestationdao.deleteAllTrainHoraireStation();

		// System.out.println(i);

		int go = horaireTrainListStation(idParam);

	}

	public int horaireTrainListStation(int idParam) {

		listeTrain = new ArrayList<Train>();

		param = new ParametreHoraire();

		paramDAO = new ParametreHoraireDAO();

		param = paramDAO.getParametreHoraireByID(idParam);

		trainDAO = new TrainDAO();

		listeTrain = trainDAO.listerTrainByLigne(param.getLigne().getIdLigne());

		tabHoraires = new ArrayList<String>();

		tabHorairesSamedi = new ArrayList<String>();

		tabHorairesDimancheJF = new ArrayList<String>();

		listeStationLigne = new ArrayList<Station>();

		listeStationLigneReverse = new ArrayList<Station>();

		stationDAO = new StationDAO();

		listeDistanceStation2a2 = new ArrayList<Double>();

		listeDistanceStation2a2Reverse = new ArrayList<Double>();

		listeStationLigne = stationDAO.listerStationByLigne(param.getLigne()
				.getIdLigne());

		listeStationLigneReverse.addAll(listeStationLigne);

		// Collections.copy(listeStationLigneReverse, listeStationLigne);

		// System.out.println("ListStationLigne Reverse id1= "+listeStationLigneReverse.get(0).getIdStation());

		Collections.reverse(listeStationLigneReverse);

		// System.out.println("ListStationLigne Reverse Apres CollectionReverse id1= "+listeStationLigneReverse.get(0).getIdStation());

		for (int i = 0; i < listeStationLigne.size() - 1; i++) {

			// System.out.println(listeStationLigne.size());

			dist = getDistancePol(listeStationLigne.get(i).getLatitude(),
					listeStationLigne.get(i + 1).getLatitude(),
					listeStationLigne.get(i).getLongitude(), listeStationLigne
							.get(i + 1).getLongitude());

			distReverse = getDistancePol(listeStationLigneReverse.get(i)
					.getLatitude(), listeStationLigneReverse.get(i + 1)
					.getLatitude(), listeStationLigneReverse.get(i)
					.getLongitude(), listeStationLigneReverse.get(i + 1)
					.getLongitude());

			// System.out.println("id Station= "+listeStationLigne.get(i).getIdStation());

			listeDistanceStation2a2
					.add(dist * 3600 / param.getVitesseMoyenne());

			listeDistanceStation2a2Reverse.add(calculTempsStation1a2(
					listeStationLigneReverse.get(i),
					listeStationLigneReverse.get(i + 1),
					param.getVitesseMoyenne()));

			// System.out.println("tabDIstanceReverse "+listeDistanceStation2a2Reverse.get(i)+"=== "+listeDistanceStation2a2.get(i));

		}

		String heurePremierTrain = param.getHeurePremierTrainJO();

		tabHoraires.clear();

		tabHoraires.add(heurePremierTrain);

		String heurePremierTrainSamdei = param.getHeurePremierTrainSamedi();

		tabHorairesSamedi.clear();

		tabHorairesSamedi.add(heurePremierTrainSamdei);

		String heurePremierTrainDimanche = param
				.getHeurePremierTrainDimancheJF();

		tabHorairesDimancheJF.clear();

		tabHorairesDimancheJF.add(heurePremierTrainDimanche);

		for (int j = 0; j < listeStationLigne.size() - 1; j++) {

			String tempsArret = param.getTempsStationnementJO();

			String splitTempsArret[] = tempsArret.split(":");

			String splitHeureTrain1[] = heurePremierTrain.split(":");

			String heureStationPrecedent = null;

			int size = listeDistanceStation2a2.size();

			// System.out.println("j= "+j);

			// System.out.println("tabDistance Size "+size);

			// System.out.println("LIste temps station "+listeDistanceStation2a2.get(j));

			// /if(j==0){

			// tabHoraires.add(heurePremierTrain);

			// }

			// if(j>=1){

			// String test = ajoutTemps()

			// System.out.println("Ajout tabhoraire j-1 "+j+"-1");

			tabHorairesDimancheJF.add(ajoutTemps(tabHorairesDimancheJF.get(j),
					listeDistanceStation2a2.get(j), tempsArret));

			tabHoraires.add(ajoutTemps(tabHoraires.get(j),
					listeDistanceStation2a2.get(j), tempsArret));

			tabHorairesSamedi.add(ajoutTemps(tabHorairesSamedi.get(j),
					listeDistanceStation2a2.get(j), tempsArret));

			// }

			// System.out.println("tabhoraire SAmedi = "+tabHorairesSamedi.get(j));

			// System.out.println("size "+tabHoraires.get());

		}

		// System.out.println(listeDistanceStation2a2.size());

		// System.out.println(tab);

		// System.out.println("size TabHoraire "+tabHoraires.size());

		// System.out.println("tabhoraire de 0 : "+tabHoraires.get(0));

		tempsCadencementString = param.getCadencementJO();

		String splitCadencement1[] = tempsCadencementString.split(":");

		int heureCadencement = Integer.parseInt(splitCadencement1[0]);

		int minCadencement = Integer.parseInt(splitCadencement1[1]);

		int secCadencement = Integer.parseInt(splitCadencement1[2]);

		dateCalendarCadencement = Calendar.getInstance();

		// dateCalendarCadencement.set(Calendar.HOUR, 12+heureCadencement);
		dateCalendarCadencement.set(Calendar.HOUR, heureCadencement);

		dateCalendarCadencement.set(Calendar.MINUTE, minCadencement);

		dateCalendarCadencement.set(Calendar.SECOND, secCadencement);

		dateFormat = new SimpleDateFormat("hh:mm:ss");

		String stringHeurePlusCadencement = dateFormat
				.format(dateCalendarCadencement.getTime());

		// System.out.println(tabHoraires.size()+" ///// "+tabHorairesSamedi.size());

		// int kk=0;

		// while( kk<50){

		for (int i = 0; i < listeTrain.size(); i++) {

			for (int e = 0; e < tabHoraires.size(); e++) {

				dateCalendar = Calendar.getInstance();

				String heureAvant = tabHoraires.get(e);

				String splitHeureTrain1[] = heureAvant.split(":");

				int heureTrain1 = Integer.parseInt(splitHeureTrain1[0]);

				int minHeureTrain1 = Integer.parseInt(splitHeureTrain1[1]);

				int secHeureTrain1 = Integer.parseInt(splitHeureTrain1[2]);

				dateCalendarSamedi = Calendar.getInstance();

				String heureAvantSamedi = tabHorairesSamedi.get(e);

				// System.out.println("heureAvantSAmedi avant traitement "+heureAvantSamedi);

				String splitHeureTrain1Samedi[] = heureAvantSamedi.split(":");

				int heureTrain1Samedi = Integer
						.parseInt(splitHeureTrain1Samedi[0]);

				int minHeureTrain1Samedi = Integer
						.parseInt(splitHeureTrain1Samedi[1]);

				int secHeureTrain1Samedi = Integer
						.parseInt(splitHeureTrain1Samedi[2]);

				dateCalendarDimanche = Calendar.getInstance();

				String heureAvantDimanche = tabHorairesDimancheJF.get(e);

				// System.out.println("heureAvantDimanche avant traitement "+heureAvantDimanche);

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

					// System.out.println("e= "+e);

					trainhorairestationdao.createTrainHoraire(
							tabHoraires.get(e), tabHorairesSamedi.get(e),
							tabHorairesDimancheJF.get(e),
							listeStationLigne.get(e), listeTrain.get(i));

				}

				if (i > 0) {

					// System.out.println("e= "+e);

					// System.out.println("heure avant if e>0 "+heureAvant);

					// System.out.println(listeStationLigne.get(e).getIdStation());

					// System.out.println("tabHoraire de la station  "+listeStationLigne.get(e).getIdStation()+" : "+tabHoraires.get(e)+" "+param.getCadencementJO());

					// dateCalendar.set(Calendar.HOUR, 12+heureTrain1);
					dateCalendar.set(Calendar.HOUR, heureTrain1);

					dateCalendar.set(Calendar.MINUTE, minHeureTrain1);

					dateCalendar.set(Calendar.SECOND, secHeureTrain1);

					dateCalendar.add(Calendar.HOUR, heureCadencement * i);

					dateCalendar.add(Calendar.MINUTE, minCadencement * i);

					dateCalendar.add(Calendar.SECOND, secCadencement * i);

					dateFormat = new SimpleDateFormat("hh:mm:ss");

					heureAvant = dateFormat.format(dateCalendar.getTime());

					// System.out.println("h avant samedi avant calendar.Add "+heureAvantSamedi);

					// dateCalendarSamedi.set(Calendar.HOUR,
					// 12+heureTrain1Samedi);
					dateCalendarSamedi.set(Calendar.HOUR, heureTrain1Samedi);

					dateCalendarSamedi.set(Calendar.MINUTE,
							minHeureTrain1Samedi);

					dateCalendarSamedi.set(Calendar.SECOND,
							secHeureTrain1Samedi);

					dateCalendarSamedi.add(Calendar.HOUR, heureCadencement * i);

					dateCalendarSamedi.add(Calendar.MINUTE, minCadencement * i);

					dateCalendarSamedi.add(Calendar.SECOND, secCadencement * i);

					dateFormat = new SimpleDateFormat("hh:mm:ss");

					heureAvantSamedi = dateFormat.format(dateCalendarSamedi
							.getTime());

					// System.out.println("h avant samedi "+heureAvantSamedi);

					// dateCalendarDimanche.set(Calendar.HOUR,
					// 12+heureTrain1Dimanche);
					dateCalendarDimanche
							.set(Calendar.HOUR, heureTrain1Dimanche);

					dateCalendarDimanche
							.set(Calendar.MINUTE, minTrain1Dimanche);

					dateCalendarDimanche
							.set(Calendar.SECOND, secTrain1Dimanche);

					dateCalendarDimanche.add(Calendar.HOUR, heureCadencement
							* i);

					dateCalendarDimanche.add(Calendar.MINUTE, minCadencement
							* i);

					dateCalendarDimanche.add(Calendar.SECOND, secCadencement
							* i);

					dateFormat = new SimpleDateFormat("hh:mm:ss");

					heureAvantDimanche = dateFormat.format(dateCalendarDimanche
							.getTime());

					int idHoraire = trainhorairestationdao
							.createTrainHoraireReturnId(heureAvant,
									heureAvantSamedi, heureAvantDimanche,
									listeStationLigne.get(e), listeTrain.get(i));

					// trainhorairestationdao.createTrainHoraire(tabHoraires.get(e),
					// tabHoraires.get(e), tabHoraires.get(e),
					// listeStationLigne.get(e), listeTrain.get(i));

					// tabHoraires.add(e,heureAvant);

					// System.out.println("new tabHoraires "+tabHoraires.get(e));

					// System.out.println("heure train cadencement+1 : "+heureAvant);

				}

			}

			// kk++;

			// }

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

		// System.out.println("temps parcours en secondes "
		// +tempsParcoursrounded);

		dateCalendar = Calendar.getInstance();

		// dateCalendar.set(Calendar.HOUR, 12+heureTrain1);
		dateCalendar.set(Calendar.HOUR, heureTrain1);

		dateCalendar.set(Calendar.MINUTE, minHeureTrain1);

		dateCalendar.set(Calendar.SECOND, secHeureTrain1);

		// System.out.println(dateCalendar.getTime());

		Calendar heureCalcule = Calendar.getInstance();

		dateCalendar.add(Calendar.HOUR, heureTempsArret);

		dateCalendar.add(Calendar.MINUTE, minTempsArret);

		dateCalendar.add(Calendar.SECOND, secTempsArret + tempsParcoursrounded);

		heureCalcule = dateCalendar;

		// System.out.println("New date getTime "+heureCalcule.getTime());

		dateFormat = new SimpleDateFormat("hh:mm:ss");

		String stringHeureCalcule = dateFormat.format(heureCalcule.getTime());

		// System.out.println("dateformat : "+stringHeureCalcule);

		// System.out.println(dateCalendar2.getTime());

		// System.out.println(dateCalendar.getTime());

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

			// System.out.println(listidTrain.get(i));

		}

		return listidTrain;

	}

	public List<Train> getLigneListTrain(int idLigne) {

		listTrainhorairestation = new ArrayList<TrainHoraireStation>();

		param = new ParametreHoraire();

		trainhorairestation = new TrainHoraireStation();

		trainhorairestationdao = new TrainHoraireStationDAO();

		ligne = new Ligne();

		listeTrain = new ArrayList<Train>();

		trainDAO = new TrainDAO();

		paramDAO = new ParametreHoraireDAO();

		listeStationLigne = new ArrayList<Station>();

		stationDAO = new StationDAO();

		station1 = new Station();

		station2 = new Station();

		// trainhorairestation = new TrainHoraireStation();

		// trainhorairestationdao = new TrainHoraireStationDAO();

		param = paramDAO.getParametreHoraireByID(idLigne);

		ligne = param.getLigne();

		listeStationLigne = stationDAO.listerStationByLigne(ligne.getIdLigne());

		// int idHoraire
		// =trainhorairestationdao.createTrainHoraireReturnId(param.getHeurePremierTrainJO().t,param.getHeurePremierTrainSamedi(),param.getHeurePremierTrainDimancheJF(),station1,listeTrain.get(0));

		for (int i = 0; i < listeStationLigne.size() - 1; i++) {

			station1 = listeStationLigne.get(i);

			station2 = listeStationLigne.get(i + 1);

			// listIdTrainIdStation[(int)listeTrain.get(0).getIdTrain()][i];

			// listIdTrainIdStation[listeTrain.get(0).getIdTrain()][i]=1;

			// trainhorairestationdao.createTrainHoraire(heureJO, heureSamedi,
			// heureDimancheJF, station, train)

			// trainhorairestation.setStation(station1);

			// trainhorairestation.setHeureDimancheJF(param.getHeurePremierTrainDimancheJF());

			// trainhorairestation.setHeureJO(param.getHeureDernierTrainJO());

			// trainhorairestation.setHeureSamedi(param.getHeurePremierTrainSamedi());

			// listTrainhorairestation.add(trainhorairestation);

			// System.out.println("Sation ID -"+station1.getIdStation());

			// System.out.println("Station2 ID "+station2.getIdStation());

			distanceS1S2 = calculTempsStation1a2(station1, station2,
					param.getVitesseMoyenne());

			// System.out.println("Temps parcours Station en secondes===> "+distanceS1S2);

		}

		System.out.println(listeStationLigne.get(0).getNomStation());

		listeTrain = trainDAO.listerTrainByLigne(ligne.getIdLigne());

		distanceS1S2 = calculTempsStation1a2(listeStationLigne.get(0),
				listeStationLigne.get(1), param.getVitesseMoyenne());

		System.out.println(distanceS1S2);

		trainhorairestationdao
				.createTrainHoraire(param.getHeurePremierTrainJO(),
						param.getHeurePremierTrainDimancheJF(),
						param.getHeurePremierTrainSamedi(), station1,
						listeTrain.get(0));

		// trainhorairestation =
		// trainhorairestationdao.getTrainHoraireStationById(id)

		return listeTrain;

	}

	public Double calculTempsStation1a2(Station s1, Station s2, int vmoyenne) {

		double distance;

		// System.out.println("S1 latitude"+s1.getLatitude());

		// System.out.println("S1 longitude"+s1.getLongitude());

		// System.out.println("S2 latitude"+s2.getLatitude());

		// System.out.println("S2 longitude"+s2.getLongitude());

		distance = getDistancePol(s1.getLatitude(), s2.getLatitude(),
				s1.getLongitude(), s2.getLongitude());

		tempsStation1a2 = distance * 3600 / vmoyenne;

		return tempsStation1a2;

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

		// System.out.println("Distance = "+distance);

		return (distance);

	}

}