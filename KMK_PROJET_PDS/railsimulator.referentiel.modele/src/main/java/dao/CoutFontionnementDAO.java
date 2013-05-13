package dao;

import java.util.*;

import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.CoutFonctionnement;
import beans.Ligne;
import beans.Reseau;
import beans.Train;

public class CoutFontionnementDAO {
	
	private Session se = null;
	private List<CoutFonctionnement> listeCoutFonctionnement;
    private CoutFonctionnement coutfonctionnement;
    
    
    public CoutFonctionnement getCoutFonctionnementByID(int id) {
    	se = HibernateUtils.getSession();
    	se.beginTransaction(); 
    	
    	coutfonctionnement = (CoutFonctionnement) se.createQuery("from coutfonctionnement where idLigne="+id).uniqueResult();
    	 se.close();
    	
        return coutfonctionnement;
    }
}
