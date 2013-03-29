package railsimulator.tools;

import java.util.ArrayList;

import beans.Station;

import dao.StationDAO;

public class Algo {
	
	StationDAO station_dao = new StationDAO();
	Station station1 = new Station();
	Station station2 = new Station();
	
	
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
	 * Création des tronçons
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
    
		
		
		
		
		
	
	}





}



