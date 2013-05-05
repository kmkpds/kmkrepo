package railsimulator.tools;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.CantonDAO;
import dao.StationDAO;
import beans.Canton;
import beans.Geolocalisation;
import beans.Station;
import beans.Zone;

public class AlgoDivTroncCanton {
	
	private Canton canton=new Canton();
	private CantonDAO canton_dao=new CantonDAO();
	private StationDAO station_dao=new StationDAO();
	private Algo algokruskal;
	
	
	public AlgoDivTroncCanton() {
        
	}
	
	public void decoupage(List<Station> listeStation,int[] stationList) {
        
		//List<Station>	test=	station_dao.listerStationHasStation();
		double distance=0;
        
		System.out.println("avant for stationlist");
		System.out.println("taille listeStation= " +listeStation.size());
		for(int i=0;i<stationList.length;i++){//for (String[] strings : stationList) {
            System.out.println("dans stationlist et avant listestation");
			//for (Station station : listeStation) {
        
			for(int y=0;y<listeStation.size()-1;y++){
				System.out.println("ds listestation");
				Station station1 = station_dao.getStationByID(listeStation.get(y).getIdStation()); //y
				Station station2 = station_dao.getStationByID(listeStation.get(y+1).getIdStation()); //y+1
				//Station station1 = station_dao.getStationByID(station.getIdStation()); //y
				//Station station2 = station_dao.getStationByID(station.getIdStation()); //y+1
				
				System.out.println("station1 " +station1.getIdStation() + "station2 " +station2.getIdStation());
				double longS1= station1.getLongitude();
				double latS1=station1.getLatitude();
				
				double longS2=station2.getLongitude();
				double latS2=station2.getLatitude();
				
				System.out.println("ancienne lat1=>" +latS1+ "lat2=>" +latS2);

				
				distance= getDistancePol(latS1,latS2,longS1 , longS2);
				System.out.println("distance==" +distance);

				Double nbCantons= distance/200;
				
				int modulo=(int) (nbCantons%200);
				System.out.println("modulo==>" +modulo);
				if (modulo !=0){
					System.out.println("nbCantons.intValue()" +nbCantons.intValue());
					System.out.println(nbCantons);
					for (int j = 0; j < nbCantons.intValue(); j++) {
						System.out.println("j" +j);
						if (j==nbCantons.intValue()-1){
						    int idcanton=canton_dao.createCantonParamStation(200+modulo,station1,station2);
						    System.out.println("idcanton ds if==" +idcanton);
						}
						else
						{
						    int idcanton=canton_dao.createCantonParamStation(200,station1,station2);
						    System.out.println("idcanton ds else ==" +idcanton);
						}
						
					} //fin boucle nbCantons
					
				}//finModulo
				else{
					for (int j = 0; j < nbCantons.intValue(); j++) {
					
						    int idcanton=canton_dao.createCantonParamStation(200,station1,station2);
						    System.out.println("idcanton ds elsemodulo ==" +idcanton);					
					} //fin boucle nbCantons
					
				}//fin else

                
			}
			
			
			
		}
		
		
        
        
        //		
        //		
        //		
        //				System.out.println("dans AlgoDivTroncCanton ");
        //				//on recupere les troncons dsn station_has_station
        //				//Object[] listeStation = station_dao.listerStation().toArray();
        //				int row;
        //				
        //				
        //				System.out.println("avant boucle");
        //				for(int y=0;y<listeStation.size();y++){
        //					System.out.println("dans boucle");
        //					Station g1 = (Station) listeStation.get(y);//[y];
        //					  row = y/200;
        //					  System.out.println("row==> " +row);
        //				System.out.println("apres division");
        //				//on creé un objet canton
        //				canton_dao.getCantonByID(canton.getIdCanton());
        //				canton.setStation(g1);
        //				canton_dao.createCantonReturnId(canton.getNomCanton(), canton.getCommentaireCanton(),canton.getStation());
        //				}//fin for
        
	}//fin decoupage
	
	
	/* === CALCUL LA DISTANCE ENTRE DEUX POINT VIA LES COORDONNEES POLAIRES === */
	public double  getDistancePol(double latitudeA,double latitudeB,double longitudeA,double longitudeB) {
		double distance = (6356.752*Math.acos(
                                              Math.sin(Math.toRadians(latitudeA))
                                              *Math.sin(Math.toRadians(latitudeB))
                                              +Math.cos(Math.toRadians(latitudeA))
                                              *Math.cos(Math.toRadians(latitudeB))
                                              *Math.cos(Math.toRadians(longitudeA)- Math.toRadians(longitudeB)))*1000
                           );
		return(distance);
	}
    
}//fin classe AlgoDivTroncCanton
