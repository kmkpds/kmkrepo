package railsimulator.tools;

import java.util.ArrayList;

import beans.Station;

import dao.StationDAO;

public class Algo {
	
	StationDAO station_dao = new StationDAO();
	Station station1 = new Station();
	Station station2 = new Station();

	public int [][] getMatricePoids( String reseauMatrice[][]) {
		
		
		int [][] matricePoids = new int[reseauMatrice[0].length][reseauMatrice[0].length] ;
		int ligne = 0;
		for(int i=1 ; i< reseauMatrice[0].length-1 ; i++) {
			
			for(int j=0 ; j< reseauMatrice[0].length ; j++) {
		
			 matricePoids [ligne][j] = Integer.parseInt( reseauMatrice [i][j]);
		
			}
			
		ligne ++;
		}				
		return(matricePoids);
	}
	
	
	public int [] getMatriceNomStation( String reseauMatrice[][]) {
	       
		int  nomStation [] = new int[reseauMatrice[0].length];
	
            
			for(int j=0 ; j< reseauMatrice[0].length ; j++) {
				 nomStation [j] = Integer.parseInt(reseauMatrice [0][j]);
			}		
		
				
		return(nomStation);
	}
	
	
	


	/* === DEBUT DES FONCTIONS POUR KRUSKAL === */

    public int getIndexMinPoids(int[] matrice) {
        int n       = matrice.length;
        int min     = Integer.MAX_VALUE;
        int index   = -1;

        for(int i=0 ; i<n ; i++) {
            if(matrice[i]>0 && matrice[i]<min) {
                min   = matrice[i];
                index = i;
            }
        }
        System.out.println("index:"+index);
        return index;
    }

	public int kruskal(String reseauMatrice[][]) {
		
	//	int graphe[][] = getMatricePoids(reseauMatrice);
	//	int corressNom[] =  getMatriceNomStation(reseauMatrice);
	int corressNom[] = {1,2,4,5,6,7};
		
		int graphe[][] ={ 
				
				{0,652,1739,2499,0,0},
				
				{652,0,1088,1855,0,0},
											
				{1739,1088,0,868,856,2280},
				
				{2499,1855,868,0,1181,2130},
				
				{0,0,856,1181,0,1485},
				
				{0,0,2280,2130,1485,0},		
					 
					
					
};
		 int n = graphe.length;
		 System.out.println("n:"+n);
		
         int[] matrice = new int[n*n];
         int index = -2;
         int row, col, rowListe, colListe, rowIndex, colIndex;
         boolean pasBon;

         ArrayList<Integer> res = new ArrayList<Integer>();


         // Initialisation d'une matrice à une dimension à partir du graphe
         for(int i=0 ; i<n ; i++) {
             for(int j=0 ; j<n ; j++) {
                 matrice[(i*n)+j] = graphe[i][j];
             }
         }

         while(res.size()<n-1 || index==-1) {
             index = getIndexMinPoids(matrice);

             if(index>=0) {
            	 
                 rowIndex   = index/n; // La nouvelle matrice n'étant plus qu'à une dimension on retrouve sa ligne en divisant nb par n
                 colIndex   = index%n; // La nouvelle matrice n'étant plus qu'à une dimension on retrouve sa colone en prenant nb modulo
                 pasBon     = false;

                 for(Integer nb : res) {
                     rowListe = nb/n; // La nouvelle matrice n'étant plus qu'à une dimension on retrouve sa ligne en divisant nb par n
                     colListe = nb%n; // La nouvelle matrice n'étant plus qu'à une dimension on retrouve sa colone en prenant nb modulo n
                    //  int  cptr = 0;
                   //  ArrayList<Integer> sommetAllantPoint1= new ArrayList<Integer>();
             	  // ArrayList<Integer> sommetAllantPoint2= new ArrayList<Integer>();
                    
                
             
                     
                    if(colListe==colIndex && rowIndex!=rowListe) {
                        pasBon = true;
                    }
                  

                     // Ne pas prendre A<->B ET B<->A
                    if(rowIndex==colListe && colIndex==rowListe) {
                         pasBon = true;
                     }
                    
                	/* ===  
                    while(pasBon==false && cptr < res.size() ){
                    	
                    	System.out.println(corressNom[rowIndex]);
                    	System.out.println(corressNom[colIndex]);
                    	
                    	for(Integer cycle : res) {
                    		
                    		int rowcycle = cycle/n; // La nouvelle matrice n'étant plus qu'à une dimension on retrouve sa ligne en divisant nb par n
                            int colcycle = cycle%n;
                    		
                            if(corressNom[rowIndex]== corressNom[rowcycle] && corressNom[rowIndex]== corressNom[colcycle] ){
                            	sommetAllantPoint1.add(index);
                            }
                            if(corressNom[colIndex]== corressNom[rowcycle] && corressNom[colIndex]== corressNom[colcycle] ){
                            	sommetAllantPoint2.add(index);
                            }
                    	}

                    	
                    	cptr++;
                    	
                    }
                    **/
                 }
                 
                 
                 
                 // Ne pas prendre A<->B ET B<->A
                 if(res.contains((colIndex*n)+rowIndex)) {
                     pasBon = true;
                 }

                 // On ajoute ce vecteur à la liste
                 if(!pasBon) { res.add(index); }

                 // On marque ce vecteur comme traité
                 matrice[index] = -1;
             }
         }




		
		
		
		// AFFICHAGE DU RESULTAT
        System.out.println("\n\n=== Kruscal : arbre couvrant minimal ===");
        for(int nb : res) {
            row = nb/n;
            col = nb%n;
			station1 =  station_dao.getStationByID(corressNom[row]);
			station2 =  station_dao.getStationByID(corressNom[col]);
			
			station_dao.createStationToStation(station1, station2);
            System.out.println("matrice["+row+"]["+col+"] - "+corressNom[row]+"->"+corressNom[col]);
        }
        System.out.println("=== FIN Kruscal ===");
    
		
		
		
		
		
		return 1;
	}

	/* === FIN DES FONCTIONS POUR KRUSKAL === */



}



