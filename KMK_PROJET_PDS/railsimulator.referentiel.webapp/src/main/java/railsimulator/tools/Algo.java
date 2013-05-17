package railsimulator.tools;

import java.util.ArrayList;
import java.util.List;

import beans.Reseau;
import beans.Station;

import dao.LigneDAO;
import dao.OptimisationCheminDAO;
import dao.StationDAO;

public class Algo {
	
	StationDAO station_dao = new StationDAO();
	Station station1 = new Station();
	Station station2 = new Station();
	LigneDAO ligne_dao = new LigneDAO();
	OptimisationCheminDAO optimisation_dao=new OptimisationCheminDAO();

	public double[][] matriceIncidence, tabPoids;
	public double[] tabAntecedents;
	public int dep, arrivee;
	//public String[] corressNom;
	String matrice[][];
	
	 
	
	
	/*
	 * Création Matrice reseau incidence
	 */

	public double [][] getMatriceIncidence( String reseauMatrice[][]) {
		
		
		double [][] matriceIncidence = new double[reseauMatrice[0].length][reseauMatrice[0].length] ;
		
		int ligne = 0;
		
		for(int i=1 ; i<= reseauMatrice[0].length ; i++) {
			for(int j=0 ; j< reseauMatrice[0].length ; j++) {
		        
			//	matriceIncidence [ligne][j] = Integer.parseInt( reseauMatrice [i][j]);
				matriceIncidence [ligne][j] = Double.parseDouble( reseauMatrice [i][j]);

		
			}
			
		ligne ++;
		}	
		return(matriceIncidence);
	}
	
	
	/*
	 * Création Matrice nomStation
	 */
	
	public int [] getMatriceNomStation( String reseauMatrice[][]) {
	       
		int  nomStation [] = new int[reseauMatrice[0].length];
	
            
			for(int j=0 ; j< reseauMatrice[0].length ; j++) {
				 nomStation [j] = Integer.parseInt(reseauMatrice [0][j]);
				
			}		
		
		return(nomStation);
	}
	
	
	
	/*
	 * récupération index de l'arc minimal
	 */



    public int getIndexMinPoids(double[] matrice) {
        int n       = matrice.length;
        double min     = Integer.MAX_VALUE;
        int index   = -1;

        for(int i=0 ; i<n ; i++) {
            if(matrice[i]>0 && matrice[i]<min) {
                min   = matrice[i];
                index = i;
            }
        }
        System.out.println(index);
        return index;
    }

    
	/*
	 * Création des tronons
	 */
    
	public void stationToStation(String reseauMatrice[][]) {
		
		double matriceIncidence[][] = getMatriceIncidence(reseauMatrice);
		int nomStation[] =  getMatriceNomStation(reseauMatrice);
		int n = matriceIncidence.length;

		
         double[] tabDistance = new double[n*n];
         int index = -2;
         int row, col, rowListe, colListe, rowIndex, colIndex;
         boolean erreur;

         
         // array de stockage des arcs optimaux
         ArrayList<Integer> res = new ArrayList<Integer>();


         // Initialisation d'une matrice à une dimension à partir du graphe
         for(int i=0 ; i<n ; i++) {
             for(int j=0 ; j<n ; j++) {
            	 tabDistance[(i*n)+j] = matriceIncidence[i][j];
             }
         }

         while(res.size()<n-1 || index==-1) {
             index = getIndexMinPoids(tabDistance);

             if(index>=0) {
            	 
                 rowIndex   = index/n; // La nouvelle matrice n'étant plus qu'à une dimension on retrouve sa ligne en divisant nb par n
                 colIndex   = index%n; // La nouvelle matrice n'étant plus qu'à une dimension on retrouve sa colone en prenant nb modulo
                 erreur     = false;

                 for(Integer nb : res) {
                     rowListe = nb/n; // La nouvelle matrice n'étant plus qu'à une dimension on retrouve sa ligne en divisant nb par n
                     colListe = nb%n; // La nouvelle matrice n'étant plus qu'à une dimension on retrouve sa colone en prenant nb modulo n

                    
                
             
                     
                    if(colListe==colIndex && rowIndex!=rowListe  ) {
                    	erreur = true;
                    }
                    
                  
                    
                    

                     // Ne pas prendre A<->B ET B<->A
                    if(rowIndex==colListe && colIndex==rowListe) {
                    erreur = true;
                    }
                    
                	
                 }
                 
                 
                 
                 // Ne pas prendre A<->B ET B<->A
                 if(res.contains((colIndex*n)+rowIndex)) {
                	 erreur = true;
                 }

                 // On ajoute ce vecteur à la liste
                 if(!erreur) { res.add(index); }

                 // On marque ce vecteur comme traité
                 tabDistance[index] = -1;
             }
         }

		//Enregistrement
        System.out.println("\n\n=== Kruscal : arbre couvrant minimal ===");
        for(int nb : res) {
            row = nb/n;
            col = nb%n;
			station1 =  station_dao.getStationByID(nomStation[row]);
			station2 =  station_dao.getStationByID(nomStation[col]);
			
		    station_dao.createStationToStation(station1, station2);
            System.out.println("matrice["+row+"]["+col+"] - "+nomStation[row]+"->"+nomStation[col]);
            
        }
        System.out.println("=== FIN Kruscal ===");
	
	
	}//fin stationToStation
//=============FIN KRUSKAL=======================

//=============DEBUT DIJKSTRA====================
//    === DEBUT DES FONCTIONS POUR DIJKSTRA === 

    public void dijkstra(String[][] MatriceDistance,Reseau reseau){
    	double[][] matPoid = new double[MatriceDistance.length-1][MatriceDistance.length-1];
    	int nbColonnes = (matPoid.length*matPoid.length)-matPoid.length;
    	String[][] matriceDijkstra = new String[nbColonnes][4];
    	double valueMin, valueMinTMP;
    	String chemin="";
    	for(int i=0; i<matPoid.length;i++){
    		for(int j=0; j<matPoid.length;j++){
    			matPoid[i][j]=Double.parseDouble(MatriceDistance[i+1][j]);
    		}
    	}
    	int  nomStation [] = new int[MatriceDistance[0].length];
    	for(int j=0 ; j< MatriceDistance[0].length ; j++) {
    		nomStation [j] = Integer.parseInt(MatriceDistance [0][j]);	
		}
    	int compteur = 0;
    	StationDAO stationDAO = new StationDAO();
    	for(int i=0; i<matPoid.length;i++){
    		chemin = "";
    		valueMin=0;
    		for(int j=0; j<matPoid.length;j++){
    			if(j!=i){
    				valueMin = matPoid[i][j];
    				chemin = stationDAO.getStationByID(nomStation[i]).getIdStation() +"-"+stationDAO.getStationByID(nomStation[i]).getNomStation() +" --> " +stationDAO.getStationByID(nomStation[j]).getIdStation() +"-"+stationDAO.getStationByID(nomStation[j]).getNomStation(); 
    				for(int k=0; k<matPoid.length;k++){
    					valueMinTMP = matPoid[i][k]+matPoid[k][j];
    					if(valueMin>valueMinTMP){
    						valueMin = valueMinTMP;
    						chemin = stationDAO.getStationByID(nomStation[i]).getIdStation() +"-"+stationDAO.getStationByID(nomStation[i]).getNomStation() +" --> "+stationDAO.getStationByID(nomStation[k]).getIdStation() +"-"+stationDAO.getStationByID(nomStation[k]).getNomStation() +" --> "+stationDAO.getStationByID(nomStation[j]).getIdStation() +"-"+stationDAO.getStationByID(nomStation[j]).getNomStation();
    					}	
    				}
    				matriceDijkstra[compteur][0]=String.valueOf(nomStation[i]);
            		matriceDijkstra[compteur][1]=String.valueOf(nomStation[j]);
            		matriceDijkstra[compteur][2]=String.valueOf(valueMin);
            		matriceDijkstra[compteur][3]=chemin;
            		compteur++;
    			}
    		}
	
    	}
    	//creation
    	for (int i =0; i<matriceDijkstra.length;i++){
    		optimisation_dao.createOptimisationChemin(stationDAO.getStationByID(Integer.parseInt(matriceDijkstra[i][0])),stationDAO.getStationByID(Integer.parseInt(matriceDijkstra[i][1])),Double.parseDouble(matriceDijkstra[i][2]),matriceDijkstra[i][3]);
			ligne_dao.createLigne(null, null, reseau);
    	}
    	System.out.println("matPoid");
    	for (int z =0; z<matPoid.length;z++){
    		for (int p =0; p<matPoid.length;p++){
    			System.out.print(matPoid[z][p]);
    		}
			System.out.println(" | ");
			
		}
    	System.out.println("matriceDijkstra");
		for (int z =0; z<matriceDijkstra.length;z++){
			System.out.print(matriceDijkstra[z][0] + " | " + matriceDijkstra[z][1] + " | " + matriceDijkstra[z][2] + " | "+ matriceDijkstra[z][3]);
			System.out.println(" | ");
		}
    	
    }//fin Dijkstra

}//Fin classe Algo



