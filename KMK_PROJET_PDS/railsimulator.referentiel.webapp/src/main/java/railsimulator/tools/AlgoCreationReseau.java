package railsimulator.tools;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import beans.Reseau;
import beans.Geolocalisation;
import beans.Zone;
import beans.Lieu;
import beans.Ligne;
import beans.Station;

import dao.LigneDAO;
import dao.ReseauDAO;
import dao.StationDAO;

public class AlgoCreationReseau {
	/* D�finition des variables */
	private StationDAO station_dao = new StationDAO();
	private ReseauDAO reseau_dao = new ReseauDAO();
	private LigneDAO ligne_dao = new LigneDAO();
	private List<Station> stationList = new ArrayList<Station>();
	Geolocalisation geo1 = new Geolocalisation()  ;
	Geolocalisation geo2 = new Geolocalisation()  ;
	Geolocalisation geo3 = new Geolocalisation()  ;
	Geolocalisation geo4 = new Geolocalisation()  ;
	Geolocalisation geo5 = new Geolocalisation();
	public Geolocalisation getGeo1() {
		return geo1;
	}

	public void setGeo1(Geolocalisation geo1) {
		this.geo1 = geo1;
	}

	public Geolocalisation getGeo2() {
		return geo2;
	}

	public void setGeo2(Geolocalisation geo2) {
		this.geo2 = geo2;
	}

	public Geolocalisation getGeo3() {
		return geo3;
	}

	public void setGeo3(Geolocalisation geo3) {
		this.geo3 = geo3;
	}

	public Geolocalisation getGeo4() {
		return geo4;
	}

	public void setGeo4(Geolocalisation geo4) {
		this.geo4 = geo4;
	}

	public Geolocalisation getGeo5() {
		return geo5;
	}

	public void setGeo5(Geolocalisation geo5) {
		this.geo5 = geo5;
	}

	public List<Station> getStationList() {
		return stationList;
	}

	public void setStationList(List<Station> stationList) {
		this.stationList = stationList;
	}

	private Reseau res = new Reseau();
	public Reseau getRes() {
		return res;
	}

	public void setRes(Reseau res) {
		this.res = res;
	}

	String matrice[][];
	public String[][] getMatrice() {
		return matrice;
	}

	public void setMatrice(String[][] matrice) {
		this.matrice = matrice;
	}

	String name;
	Geolocalisation geocentre = new Geolocalisation() ;
	double distance = 0;
	private Ligne ligne = new Ligne();
	/* === CREATION D'UN JEU DE TEST === */
	/* public Reseau Test() {
		Reseau reseau = new Reseau();
		/*reseau.setNomReseau("Reseau_Test1");
		Zone zone1 = new Zone();
		zone1.setIdZone(1);
		zone1.setReseau(reseau);
		zone1.setNombreMaxDeStationParZone(1);
		Zone zone2 = new Zone();
		zone2.setIdZone(2);
		zone2.setReseau(reseau);
		zone2.setNombreMaxDeStationParZone(2);
		Zone zone3 = new Zone();
		zone3.setIdZone(3);
		zone3.setReseau(reseau);
		zone3.setNombreMaxDeStationParZone(3);
		Zone zone4 = new Zone();
		zone4.setIdZone(4);
		zone4.setReseau(reseau);
		zone4.setNombreMaxDeStationParZone(4);
		Zone zone5 = new Zone();
		zone5.setIdZone(5);
		zone5.setReseau(reseau);
		zone5.setNombreMaxDeStationParZone(5);
		
		Geolocalisation geo1 = new Geolocalisation();
		geo1.setIdgeolocalisation(1);
		geo1.setLatitudeGeolocalisation(20.00);
		geo1.setLongitudeGeolocalisation(-30.00);
		geo1.setZone(zone1);
		
		Geolocalisation geo2 = new Geolocalisation();
		geo2.setIdgeolocalisation(2);
		geo2.setLatitudeGeolocalisation(30.00);
		geo2.setLongitudeGeolocalisation(-10.00);
		geo2.setZone(zone1);
		
		Geolocalisation geo3 = new Geolocalisation();
		geo3.setIdgeolocalisation(3);
		geo3.setLatitudeGeolocalisation(-20.00);
		geo3.setLongitudeGeolocalisation(20.00);
		geo3.setZone(zone2);
		
		Geolocalisation geo4 = new Geolocalisation();
		geo4.setIdgeolocalisation(4);
		geo4.setLatitudeGeolocalisation(-10.00);
		geo4.setLongitudeGeolocalisation(20.00);
		geo4.setZone(zone2);
		
		Geolocalisation geo5 = new Geolocalisation();
		geo5.setIdgeolocalisation(5);
		geo5.setLatitudeGeolocalisation(40.00);
		geo5.setLongitudeGeolocalisation(50.00);
		geo5.setZone(zone3);
		
		Geolocalisation geo6 = new Geolocalisation();
		geo6.setIdgeolocalisation(6);
		geo6.setLatitudeGeolocalisation(60.00);
		geo6.setLongitudeGeolocalisation(90.00);
		geo6.setZone(zone3);
		
		Geolocalisation geo7 = new Geolocalisation();
		geo7.setIdgeolocalisation(7);
		geo7.setLatitudeGeolocalisation(10.00);
		geo7.setLongitudeGeolocalisation(20.00);
		geo7.setZone(zone4);
		
		Geolocalisation geo8 = new Geolocalisation();
		geo8.setIdgeolocalisation(8);
		geo8.setLatitudeGeolocalisation(20.00);
		geo8.setLongitudeGeolocalisation(40.00);
		geo8.setZone(zone4);
		
		Geolocalisation geo9 = new Geolocalisation();
		geo9.setIdgeolocalisation(9);
		geo9.setLatitudeGeolocalisation(-40.00);
		geo9.setLongitudeGeolocalisation(-30.00);
		geo9.setZone(zone5);
		
		Geolocalisation geo10 = new Geolocalisation();
		geo10.setIdgeolocalisation(10);
		geo10.setLatitudeGeolocalisation(-10.00);
		geo10.setLongitudeGeolocalisation(50.00);
		geo10.setZone(zone5);
		
		List<Geolocalisation> geolocalisationlist1 = new ArrayList<Geolocalisation>();
		geolocalisationlist1.add(geo1);
		geolocalisationlist1.add(geo2); 
		
		List<Geolocalisation> geolocalisationlist2 = new ArrayList<Geolocalisation>();
		geolocalisationlist2.add(geo3);
		geolocalisationlist2.add(geo4);
		
		List<Geolocalisation> geolocalisationlist3 = new ArrayList<Geolocalisation>();
		geolocalisationlist3.add(geo5);
		geolocalisationlist3.add(geo6);
		
		List<Geolocalisation> geolocalisationlist4 = new ArrayList<Geolocalisation>();
		geolocalisationlist4.add(geo7);
		geolocalisationlist4.add(geo8);
		
		List<Geolocalisation> geolocalisationlist5 = new ArrayList<Geolocalisation>();
		geolocalisationlist5.add(geo9);
		geolocalisationlist5.add(geo10);
		
		zone1.setGeolocalisationlist(geolocalisationlist1);
		zone2.setGeolocalisationlist(geolocalisationlist2);
		zone3.setGeolocalisationlist(geolocalisationlist3);
		zone4.setGeolocalisationlist(geolocalisationlist4);
		zone5.setGeolocalisationlist(geolocalisationlist5);
		
		List<Zone> zonelist = new ArrayList<Zone>();
		zonelist.add(zone1);
		zonelist.add(zone2);
		zonelist.add(zone3);
		zonelist.add(zone4);
		zonelist.add(zone5);

		reseau.setZonelist(zonelist);
		reseau_dao.createReseau(reseau.getNomReseau());//Fin du "*\""
		reseau = reseau_dao.getReseauByID(36);
		//List<Zone> zonelist = new ArrayList<Zone>();
		//zonelist = reseau.getZonelist();
		//System.out.println(zonelist.get(0).getIdZone());System.out.println(zonelist.get(1).getIdZone());System.out.println(zonelist.get(2).getIdZone());
		return reseau;
	}*/
	/* === FIN JEU DE TEST === */
	
	public Ligne getLigne() {
		return ligne;
	}

	public void setLigne(Ligne ligne) {
		this.ligne = ligne;
	}

	/* === RECUPERATION DES INFORMATIONS RESEAU  === */
	public String[][] creerReseau(Reseau reseau){
		res = reseau;
		ligne.setCommentaire("Ligne de test");
		ligne.setNomLigne("Ligne Test");
		ligne.setStationlist(null);
		ligne.setReseau(res);
		int idLigne = ligne_dao.createLigneReturnId(ligne.getNomLigne(), ligne.getCommentaire(), ligne.getReseau());
		ligne = ligne_dao.getLigneByID(idLigne);
		//Set<Zone> zoneList = res.getZonelist();
		Object[] zoneList = res.getZonelist().toArray();
		Zone z;
		for(int i=0; i < zoneList.length; i++){
			//z = zoneList.get(i);			
			z=(Zone) zoneList[i];
			placerStation(z, i, 0);
		}
		int Ligne = stationList.size() + 1;
		matrice = new String[Ligne][stationList.size()];
		creerMatriceDistance(stationList);
		return matrice;
	}
	/* === FIN RECUPERATION DES INFORMATIONS RESEAU  === */
	
	/* === VERIFICATION DU PLACEMENT DE LA STATION=== */
	public void placerStation(Zone zone, int i, int nbStation){
		if (nbStation==0){
			nbStation=zone.getNombreMaxDeStationParZone();
		}
		double Lat=0, Long=0;
		double LatMin = 0.0, LatMax = 0.0, LongMin = 0.0, LongMax = 0.0;
		List<Geolocalisation> geolocalisationlist = zone.getGeolocalisationlist();
		double distanceExtremite =0, distanceV=0, distanceH=0;
		if (geolocalisationlist.get(0).getLatitudeGeolocalisation()>=geolocalisationlist.get(1).getLatitudeGeolocalisation()){
			LatMin = geolocalisationlist.get(1).getLatitudeGeolocalisation();
			LatMax = geolocalisationlist.get(0).getLatitudeGeolocalisation();
		}
		else{
			LatMin = geolocalisationlist.get(0).getLatitudeGeolocalisation();
			LatMax = geolocalisationlist.get(1).getLatitudeGeolocalisation();
		}
		if (geolocalisationlist.get(0).getLongitudeGeolocalisation()>=geolocalisationlist.get(1).getLongitudeGeolocalisation()){
			LongMin = geolocalisationlist.get(1).getLongitudeGeolocalisation();
			LongMax = geolocalisationlist.get(0).getLongitudeGeolocalisation();
		}
		else{
			LongMin = geolocalisationlist.get(0).getLongitudeGeolocalisation();
			LongMax = geolocalisationlist.get(1).getLongitudeGeolocalisation();
		}
		distanceExtremite = getDistancePol(LatMin, LatMax, LongMin, LongMax);
		distanceV = getDistancePol(LatMin, LatMax, LongMin, LongMin);
		distanceH = getDistancePol(LatMin, LatMin, LongMin, LongMax);
		double distLong = 0.0, distLat = 0.0;
		//System.out.println("Distance entre les extrŽmitŽs ==> " + distanceExtremite);
		if(nbStation == 1){
			/* FORMAT DE PLACEMENT DES STATIONS
			  	|-----------------------|
			    |                 	 	|
			    |		   S1			|	
			    |						|
			    |-----------------------|
			 */
			System.out.println("CrŽation d'une station");
			Lat = 0.0;
			Long = 0.0; 
			/*for(int j=0; j < geolocalisationlist.size(); j++){
				geo = geolocalisationlist.get(j);
				Lat = Lat + geo.getLatitudeGeolocalisation();
				Long = Long + geo.getLongitudeGeolocalisation();
			}*/
			Lat = LatMin+LatMax;
			Long = LongMin+LongMax;
			Lat = Lat/2;
			Long = Long/2;
			geo1.setLatitudeGeolocalisation(Lat);
			geo1.setLongitudeGeolocalisation(Long);
			name = "Station" +res.getNomReseau()+"_"+i+"_1/1";
			creerStation(geo1, name);
		}
		else{
			if(nbStation == 2){
				/* FORMAT DE PLACEMENT DES STATIONS
				   	|-----------------------|
				    |                 S1 	|
				    |						|	
				    |	S2					|
				    |-----------------------|
				    <600 = 1 station
				    600<= 1200 = aux extrŽmitŽs
				    > 1200 = milieu des demi-diagonale
				 */
				System.out.println("CrŽation de deux stations");
				if(distanceExtremite<6.0){
					System.out.println("DŽsolŽ : La distance maximum possible entre deux points ne permet pas de placer plus d'une station sur cette zone");
					System.out.println("Une seule station est crŽŽe pour cette zone");
					placerStation(zone, i, 1);
				}
				else{
					if(distanceExtremite<=1.2){
						geo1.setLatitudeGeolocalisation(LatMin);
						geo1.setLongitudeGeolocalisation(LongMin);
						name = "Station" +res.getNomReseau()+"_"+i+"_1/2";
						creerStation(geo1, name);
						geo2.setLatitudeGeolocalisation(LatMax);
						geo2.setLongitudeGeolocalisation(LongMax);
						name = "Station" +res.getNomReseau()+"_"+i+"_2/2";
						creerStation(geo2, name);
					}
					else{
						distLong = (LongMax - LongMin)/4;
						distLat = (LatMax - LatMin)/4;
						geo1.setLatitudeGeolocalisation(LatMin+distLat);
						geo1.setLongitudeGeolocalisation(LongMin+distLong);
						geo2.setLatitudeGeolocalisation(LatMax-distLat);
						geo2.setLongitudeGeolocalisation(LongMax-distLong);
						double DistanceStation = getDistancePol(geo1.getLatitudeGeolocalisation(), geo2.getLatitudeGeolocalisation(), geo1.getLongitudeGeolocalisation(), geo2.getLongitudeGeolocalisation());
						if(DistanceStation >= 6.0){
							creerStation(geo1, "Station" +res.getNomReseau()+"_"+i+"_1/2");
							creerStation(geo2, "Station" +res.getNomReseau()+"_"+i+"_2/2");
						}
						else{
							System.out.println("Attention : la distance entre les deux stations est de : "+distanceExtremite);
						}	
					}
				}
			}
			else{
				if(nbStation == 3){
					/* FORMAT DE PLACEMENT DES STATIONS
					   	|-----------------------|
					    |                 	S3 	|
					    |		   S2			|	
					    | S1					|
					    |-----------------------|
					    <1200 = 1 station
					    >=1200 et <2400 = S2 est au centre. S1 et S3 sont aux extrŽmitŽs
					    >= 2400 = S2 est au centre et S1 et S3 sont au milieu des demi-diagonale
					 */
					System.out.println("Placement de trois stations");
					if(distanceExtremite<1.2){
						System.out.println("DŽsolŽ : La distance maximum possible entre deux points ne permet pas de placer plus d'une station sur cette zone");
						System.out.println("Une seule station est crŽŽe pour cette zone");
						placerStation(zone, i, 1);
					}
					else{
						if(distanceExtremite>=2.4){
							Lat = 0.0;
							Long = 0.0; 
							distLong = (LongMax - LongMin)/4;
							distLat = (LatMax - LatMin)/4;
							Lat = LatMin+LatMax;
							Long = LongMin+LongMax;
							Lat = Lat/2;
							Long = Long/2;
							geo2.setLatitudeGeolocalisation(Lat);
							geo2.setLongitudeGeolocalisation(Long);
							name = "Station" +res.getNomReseau()+"_"+i+"_2/3";
							creerStation(geo2, name);
							distLong = (LongMax - LongMin)/4;
							distLat = (LatMax - LatMin)/4;
							Lat = LatMin+distLat;
							Long = LongMin+distLong;
							geo1.setLatitudeGeolocalisation(Lat);
							geo1.setLongitudeGeolocalisation(Long);
							name = "Station" +res.getNomReseau()+"_"+i+"_1/3";
							creerStation(geo1, name);
							Lat = LatMax-distLat;
							Long = LongMax-distLong;
							geo3.setLatitudeGeolocalisation(Lat);
							geo3.setLongitudeGeolocalisation(Long);
							name = "Station" +res.getNomReseau()+"_"+i+"_3/3";
							creerStation(geo3, name);
						}
						else{
							geo1.setLatitudeGeolocalisation(LatMin);
							geo1.setLongitudeGeolocalisation(LongMin);
							name = "Station" +res.getNomReseau()+"_"+i+"_1/3";
							creerStation(geo1, name);
							Lat = 0.0;
							Long = 0.0; 
							Lat = LatMin+LatMax;
							Long = LongMin+LongMax;
							Lat = Lat/2;
							Long = Long/2;
							geo2.setLatitudeGeolocalisation(Lat);
							geo2.setLongitudeGeolocalisation(Long);
							name = "Station" +res.getNomReseau()+"_"+i+"_2/3";
							creerStation(geo2, name);
							geo3.setLatitudeGeolocalisation(LatMax);
							geo3.setLongitudeGeolocalisation(LongMax);
							name = "Station" +res.getNomReseau()+"_"+i+"_3/3";
							creerStation(geo3, name);
						}
					}
				}
				else{
					if(nbStation == 4){
						/* FORMAT DE PLACEMENT DES STATIONS
						  	|-----------------------|
						    | S1                 S2 |
						    |		   				|	
						    |						|
						    | S3				 S4	|
						    |-----------------------|
						    Calcule des longueurs et largeurs du rectangle.
						    Si longueur ou largeur infŽrieur ˆ 600 mtres --> on rappelle la mŽthode pour tenter de placer trois stations
						    Si longueur et largeur supŽrieur ˆ 1200 mtres --> on calcule les positions en partant des extrŽmitŽs et en ajoutant/rŽduisant d'une quart de la longueur/largeur
						    Sinon, on place les stations aux extrŽmitŽs
						 */
						System.out.println("Placement de quatre stations");
						if(distanceH<6.0 | distanceV<6.0){
							System.out.println("DŽsolŽ : La distance maximum possible entre deux points ne permet pas de placer 4 stations sur cette zone");
							System.out.println("Nous allons tenter de placer 3 stations");
							placerStation(zone, i, 3);
						}
						else{
							if(distanceH>=1.2 && distanceV>=1.2){
								Lat = (LatMax - LatMin)/4;
								Long = (LongMax-LongMin)/4;
								geo1.setLatitudeGeolocalisation(LatMax-Lat);
								geo1.setLongitudeGeolocalisation(LongMin+Long);
								name = "Station" +res.getNomReseau()+"_"+i+"_1/4";
								creerStation(geo1, name);
								geo2.setLatitudeGeolocalisation(LatMax-Lat);
								geo2.setLongitudeGeolocalisation(LongMax-Long);
								name = "Station" +res.getNomReseau()+"_"+i+"_2/4";
								creerStation(geo2, name);
								geo3.setLatitudeGeolocalisation(LatMin+Lat);
								geo3.setLongitudeGeolocalisation(LongMin+Long);
								name = "Station" +res.getNomReseau()+"_"+i+"_3/4";
								creerStation(geo3, name);
								geo4.setLatitudeGeolocalisation(LatMin+Lat);
								geo4.setLongitudeGeolocalisation(LongMax-Long);
								name = "Station" +res.getNomReseau()+"_"+i+"_4/4";
								creerStation(geo4, name);
							}
							else{
								geo1.setLatitudeGeolocalisation(LatMax);
								geo1.setLongitudeGeolocalisation(LongMin);
								name = "Station" +res.getNomReseau()+"_"+i+"_1/4";
								creerStation(geo1, name);
								geo2.setLatitudeGeolocalisation(LatMax);
								geo2.setLongitudeGeolocalisation(LongMax);
								name = "Station" +res.getNomReseau()+"_"+i+"_2/4";
								creerStation(geo2, name);
								geo3.setLatitudeGeolocalisation(LatMin);
								geo3.setLongitudeGeolocalisation(LongMin);
								name = "Station" +res.getNomReseau()+"_"+i+"_3/4";
								creerStation(geo3, name);
								geo4.setLatitudeGeolocalisation(LatMin);
								geo4.setLongitudeGeolocalisation(LongMax);
								name = "Station" +res.getNomReseau()+"_"+i+"_4/4";
								creerStation(geo4, name);
							}
						}
					}
					else{
						if(nbStation == 5){
							/* FORMAT DE PLACEMENT DES STATIONS
							  	|-----------------------|
							    | S2                 S3 |
							    |		   				|
							    |		   S1			|
							    |						|
							    | S4				 S5	|
							    |-----------------------|
							    Les tests de placement de 3 et 4 stations sont appliquŽs : on vŽrifie les longueurs, largeurs et les diagonales.
							    S1 est toujours au centre.
							    Calcule sur base de carrŽ
							 */
							System.out.println("Placement de cinq stations");
							//double testDistance;
							//testDistance = Math.pow((distanceV/2), 2) + Math.pow((distanceH/2),2);
							//if(distanceExtremite>=1200 && testDistance>=360000){
							//if(distanceExtremite>=1200 && distanceV>=850 && distanceH>=850){
							if(distanceExtremite>=1.2 && distanceV>=6.0 && distanceH>=6.0){
								Lat = 0.0;
								Long = 0.0; 
								Lat = LatMin+LatMax;
								Long = LongMin+LongMax;
								Lat = Lat/2;
								Long = Long/2;
								geo1.setLatitudeGeolocalisation(Lat);
								geo1.setLongitudeGeolocalisation(Long);
								name = "Station" +res.getNomReseau()+"_"+i+"_1/5";
								creerStation(geo1, name);
								//if(distanceExtremite>=3000 && testDistance>=360000){
								//if(distanceExtremite>=3000 && distanceV>=850 && distanceH>=850){
								if(distanceExtremite>=3.0){
									Lat = (LatMax-LatMin)/4;
									Long =(LongMax-LongMin)/4;
									geo2.setLatitudeGeolocalisation(LatMax-Lat);
									geo2.setLongitudeGeolocalisation(LongMin+Long);
									name = "Station" +res.getNomReseau()+"_"+i+"_2/5";
									creerStation(geo2, name);
									geo3.setLatitudeGeolocalisation(LatMax-Lat);
									geo3.setLongitudeGeolocalisation(LongMax-Long);
									name = "Station" +res.getNomReseau()+"_"+i+"_3/5";
									creerStation(geo3, name);
									geo4.setLatitudeGeolocalisation(LatMin+Lat);
									geo4.setLongitudeGeolocalisation(LongMin+Long);
									name = "Station" +res.getNomReseau()+"_"+i+"_4/5";
									creerStation(geo4, name);
									geo5.setLatitudeGeolocalisation(LatMin+Lat);
									geo5.setLongitudeGeolocalisation(LongMax-Long);
									name = "Station" +res.getNomReseau()+"_"+i+"_5/5";
									creerStation(geo5, name);
								}
								else{
									geo2.setLatitudeGeolocalisation(LatMax);
									geo2.setLongitudeGeolocalisation(LongMin);
									name = "Station" +res.getNomReseau()+"_"+i+"_2/5";
									creerStation(geo2, name);
									geo3.setLatitudeGeolocalisation(LatMax);
									geo3.setLongitudeGeolocalisation(LongMax);
									name = "Station" +res.getNomReseau()+"_"+i+"_3/5";
									creerStation(geo3, name);
									geo4.setLatitudeGeolocalisation(LatMin);
									geo4.setLongitudeGeolocalisation(LongMin);
									name = "Station" +res.getNomReseau()+"_"+i+"_4/5";
									creerStation(geo4, name);
									geo5.setLatitudeGeolocalisation(LatMin);
									geo5.setLongitudeGeolocalisation(LongMax);
									name = "Station" +res.getNomReseau()+"_"+i+"_5/5";
									creerStation(geo5, name);
								}
							}
							else{
								System.out.println("Désolé : La distance maximum possible entre deux points ne permet pas de placer 5 stations sur cette zone");
								System.out.println("Nous allons tenter de placer 4 stations");
								placerStation(zone, i, 4);
							}
						}
						else{
							System.out.println("Trop de stations !!");
						}
					}
				}
			}
		}
	}
	/* === FIN DE LA VERIFICATION DU PLACEMENT DE LA STATION=== */
	
	/* === MODIFICATION DE L'EMPLACEMENT === */
	/*
	public double[] modifierEmplacement(double latitude, double longitude, double lattest, double longtest, double latMax, double latMin, double longMax, double longMin){
		double latTemp=0, longTemp=0;
		double[] coordonnŽes = new double[2];
		if(latitude>lattest){
			latTemp = latitude + 0.001;
			if (latTemp <= latMax){
				latitude = latTemp;
			}
		}
		else{
			latTemp = latitude - 0.001;
			if (latTemp >= latMax){
				latitude = latTemp;
			}
		}
		if(longitude>longtest){
			longTemp = longitude + 0.001;
			if(longTemp <= longMax){
				longitude = longTemp;
			}
			
		}
		else{
			longTemp = longitude - 0.001;
			if(longTemp >= longMax){
				longitude = longTemp;
			}
		}
		coordonnŽes[0] = latitude ;
		coordonnŽes[1] = longitude;
		return coordonnŽes;
	}
	*/
	/* === FIN DE LA MODIFICATION DE L'EMPLACEMENT === */

	/* === CREATION DE LA STATION  === */
	public void creerStation(Geolocalisation geo, String name){
		Station s = new Station();
		s.setLatitude(geo.getLatitudeGeolocalisation());
		s.setLongitude(geo.getLongitudeGeolocalisation());
		s.setNomStation(name);	
		s.setIdStation(station_dao.createStationReturnId(s.getNomStation(), null, s.getLatitude(), s.getLongitude(), ligne)); 
		stationList.add(s);
	}
	/* === FIN DE CREATION DE LA STATION  === */
	
	/* === CREATION DE LA MATRICE DES DISTANCES ENTRE STATION  === */
	public void creerMatriceDistance(List<Station> statList){
		int Ligne = statList.size() + 1;
		for(int i=0; i < statList.size(); i++){ 
			//System.out.println("Test" + statList.get(i).getIdStation());
			matrice[0][i] =String.valueOf(statList.get(i).getIdStation());		
		}
		int counti = 0, countj =0;
		for(int i=1; i < Ligne; i++){ 
			countj=0;
			for(int j=0; j < statList.size(); j++){ 
				if (i == j+1){
					matrice[i][j] = "0";
				}
				else{
					matrice[i][j] = String.valueOf(getDistancePol(statList.get(counti).getLatitude(), statList.get(countj).getLatitude(), statList.get(counti).getLongitude(), statList.get(countj).getLongitude()));

				}
				countj = countj+1;
			}
			counti = counti +1;	
		}	
		//Affichage de la matrice
		for(int i=0; i < Ligne; i++){ 
			for(int j=0; j < statList.size(); j++){
				if (j==statList.size()-1){
					System.out.println(matrice[i][j]);
				}
				else{
					System.out.print(matrice[i][j]+"  ");
				}
				
			}
		}
	}
	/* === FIN DE CREATION DE LA MATRICE DES DISTANCES ENTRE STATION  === */
	
	/* === CALCUL LA DISTANCE ENTRE DEUX POINT VIA LES COORDONNEES CARTESIENNES === */
	/*public double getDistanceCart(double Xa,double Xb,double Ya,double Yb) {
	 
		double distance = Math.sqrt(Math.pow(Xb-Xa, 2)+ Math.pow(Yb-Ya, 2));
		return(distance);
	}*/
	/* === FIN CALCUL COORDONNEES CARTESIENNES === */
	
	/* === CALCUL LA DISTANCE ENTRE DEUX POINT VIA LES COORDONNEES POLAIRES === */
	public double  getDistancePol(double latitudeA,double latitudeB,double longitudeA,double longitudeB) {
		double distance = 6356.752*Math.acos(
				Math.sin(Math.toRadians(latitudeA))
				*Math.sin(Math.toRadians(latitudeB))
				+Math.cos(Math.toRadians(latitudeA))
				*Math.cos(Math.toRadians(latitudeB))
				*Math.cos(Math.toRadians(longitudeA)- Math.toRadians(longitudeB))
				);
		return(distance);
	}
	/* === FIN CALCUL COORDONNEES POLAIRES === */
	
	/* === DEBUT VERIFICATION DISTANCES ENTRE LES ZONES === */
	/*public boolean getDistanceZone(double lat1, double lat2, double long1, double long2, List<double[]> tableau) {
		boolean check = true;
		boolean checkTmp;
		for (int i=0; i<tableau.size(); i++){
			checkTmp = distancePointsMultiples(lat1, lat2, tableau.get(i)[0], tableau.get(i)[1], long1, long2, tableau.get(i)[2],tableau.get(i)[3]);
			if (checkTmp==false){
				check = false;
			}
			else{
				checkTmp = verifierPointZone(lat1, lat2, tableau.get(i)[0], tableau.get(i)[1], long1, long2, tableau.get(i)[2],tableau.get(i)[3]);
			}
		}
		return check;
	}*/
	/* === FIN VERIFICATION DISTANCES ENTRE LES ZONES === */
	
	/* === DEBUT VERIFICATION DISTANCES ENTRE LES POINTS MULTIPLE === */
	/*public boolean distancePointsMultiples(double lat1, double lat2, double lat3, double lat4, double long1, double long2 ,double long3 ,double long4) {
		boolean check = true;
		double distanceTmp;
		//VŽrification entre les diffŽrents points des zones
		distanceTmp = getDistancePol(lat1, lat3, long1, long3);
		if (distanceTmp<0.6){
			//System.out.println("1 - " + distanceTmp);
			check=false;
		}
		distanceTmp = getDistancePol(lat1, lat3, long1, long4);
		if (distanceTmp<0.6){
			//System.out.println("2 - " + distanceTmp);
			check=false;
		}
		distanceTmp = getDistancePol(lat1, lat4, long1, long3);
		if (distanceTmp<0.6){
			//System.out.println("3 - " + distanceTmp);
			check=false;
		}
		distanceTmp = getDistancePol(lat1, lat4, long1, long4);
		if (distanceTmp<0.6){
			//System.out.println("4 - " + distanceTmp);
			check=false;
		}

		distanceTmp = getDistancePol(lat1, lat3, long2, long3);
		if (distanceTmp<0.6){
			//System.out.println("5 - " + distanceTmp);
			check=false;
		}
		distanceTmp = getDistancePol(lat1, lat3, long2, long4);
		if (distanceTmp<0.6){
			//System.out.println("6 - " + distanceTmp);
			check=false;
		}
		distanceTmp = getDistancePol(lat1, lat4, long2, long3);
		if (distanceTmp<0.6){
			//System.out.println("7 - " + distanceTmp);
			check=false;
		}
		distanceTmp = getDistancePol(lat1, lat4, long2, long4);
		if (distanceTmp<0.6){
			//System.out.println("8 - " + distanceTmp);
			check=false;
		}
		
		distanceTmp = getDistancePol(lat2, lat3, long2, long3);
		if (distanceTmp<0.6){
			//System.out.println("9 - " + distanceTmp);
			check=false;
		}
		distanceTmp = getDistancePol(lat2, lat3, long2, long4);
		if (distanceTmp<0.6){
			//System.out.println("10 - " + distanceTmp);
			check=false;
		}
		distanceTmp = getDistancePol(lat2, lat4, long2, long3);
		if (distanceTmp<0.6){
			//System.out.println("11 - " + distanceTmp);
			check=false;
		}
		distanceTmp = getDistancePol(lat2, lat4, long2, long4);
		if (distanceTmp<0.6){
			//System.out.println("12 - " + distanceTmp);
			check=false;
		}
		
		distanceTmp = getDistancePol(lat2, lat3, long1, long3);
		if (distanceTmp<0.6){
			//System.out.println("13 - " + distanceTmp);
			check=false;
		}
		distanceTmp = getDistancePol(lat2, lat3, long1, long4);
		if (distanceTmp<0.6){
			//System.out.println("14 - " + distanceTmp);
			check=false;
		}
		distanceTmp = getDistancePol(lat2, lat4, long1, long3);
		if (distanceTmp<0.6){
			//System.out.println("15 - " + distanceTmp);
			check=false;
		}
		distanceTmp = getDistancePol(lat2, lat4, long1, long4);
		if (distanceTmp<0.6){
			//System.out.println("16 - " + distanceTmp);
			check=false;
		}
		System.out.println("Check - distancePointsMultiples = " + check);
		return check;
	}*/
	/* === FIN` VERIFICATION DISTANCES ENTRE LES POINTS MULTIPLE === */
	
	/* === VERIFIER SI AUCUN POINT DE LA ZONE A CREER N'APPARTIENT A UNE ZONE EXISTANTE === */
	/*public boolean verifierPointZone(double lat1, double lat2, double lat3, double lat4, double long1, double long2 ,double long3 ,double long4){
		boolean check = true;
		double latMin, latMax, longMin, longMax;
		if (lat3>=lat4){
			latMin = lat4;
			latMax = lat3;
		}
		else{
			latMin = lat3;
			latMax = lat4;
		}
		if (long3>=long4){
			longMin = long4;
			longMax = long3;
		}
		else{
			longMin = long3;
			longMax = long4;
		}
		if(lat1>=latMin && lat1<=latMax && long1>=longMin && long1<=longMax){
			//System.out.println("17");
			check = false;
		}
		if(lat1>=latMin && lat1<=latMax && long2>=longMin && long2<=longMax){
			//System.out.println("18");
			check = false;
		}
		if(lat2>=latMin && lat2<=latMax && long1>=longMin && long1<=longMax){
			//System.out.println("19");
			check = false;
		}
		if(lat2>=latMin && lat2<=latMax && long2>=longMin && long2<=longMax){
			//System.out.println("20");
			check = false;
		}
		System.out.println("Check - verifierPointZone = "+check);
		return check;
	}*/
	/* === FIN DE LA VERIFICATION === */
	
}
