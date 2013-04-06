package dao;



import org.hibernate.Session;

import beans.AnnonceVoyageur;

public class AnnonceVoyageurDAO {

	/**
	 * @param args
	 */
	private Session se = null;
	
	private AnnonceVoyageur annonceVoyageur = new AnnonceVoyageur();
	
	
	public String getAnnonceVoyageurByID(int id) {
    	se = HibernateUtils.getSession();
    	se.beginTransaction();   	
    	annonceVoyageur = (AnnonceVoyageur) se.createQuery("from AnnonceVoyageur where idAnnonce="+id).uniqueResult();
    	String annonceLibelle = annonceVoyageur.getLibelleAnnonce();
    	se.close();
        return annonceLibelle;
    }

}
