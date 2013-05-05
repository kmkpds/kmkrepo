package dao;
import java.util.*;

import javax.persistence.Column;

import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Ligne;
import beans.ParametreHoraire;
import beans.Reseau;

public class ParametreHoraireDAO {

	private Session se = null;
    private ParametreHoraire parametreHoraire;
    private List<ParametreHoraire> parametreHoraireList;
    private Ligne ligne;
    private LigneDAO ligneDAO;
    
	public  void createParametreHoraire(Ligne Ligne , String heurePremierTrainJO, String heurePremierTrainSamedi, String heurePremierTrainDimancheJF, String heureDernierTrainJO, String heureDernierTrainSamedi, String heureDernierTrainDimancheJF, String cadencementJO, String cadencementSamedi, String cadencementDimancheJF, String heuresPointeJO, String heuresPointeSamedi, String heuresPointeDimancheJF, String tempsStationnementJO, String tempsStationnementSamedi, String tempsStationnementDimancheJF, int vitesseMoyenne) {
	      
		 //ligne = ligneDAO.getLigneByID(idLigne);
		se = HibernateUtils.getSession();
	     Transaction t = se.beginTransaction();
	     parametreHoraire = new ParametreHoraire();
	     parametreHoraire.setLigne(Ligne);
	     parametreHoraire.setHeurePremierTrainJO(heurePremierTrainJO);
	     parametreHoraire.setHeurePremierTrainDimancheJF(heurePremierTrainDimancheJF);
	     parametreHoraire.setHeurePremierTrainSamedi(heurePremierTrainSamedi);
	     parametreHoraire.setHeureDernierTrainJO(heureDernierTrainJO);
	     parametreHoraire.setHeureDernierTrainDimancheJF(heureDernierTrainDimancheJF);
	     parametreHoraire.setHeureDernierTrainSamedi(heureDernierTrainSamedi);
	     parametreHoraire.setCadencementDimancheJF(cadencementDimancheJF);
	     parametreHoraire.setCadencementJO(cadencementJO);
	     parametreHoraire.setCadencementSamedi(cadencementSamedi);
	     parametreHoraire.setHeuresPointeDimancheJF(heuresPointeDimancheJF);
	     parametreHoraire.setHeuresPointeJO(heuresPointeJO);
	     parametreHoraire.setHeuresPointeSamedi(heuresPointeSamedi);
	     parametreHoraire.setTempsStationnementDimancheJF(tempsStationnementDimancheJF);
	     parametreHoraire.setTempsStationnementJO(tempsStationnementJO);
	     parametreHoraire.setTempsStationnementSamedi(tempsStationnementSamedi);
	     parametreHoraire.setVitesseMoyenne(vitesseMoyenne);
	     se.save(parametreHoraire);
		
	     t.commit();
	     se.close();
	}

	public  int createParametreHoraireReturnId(Ligne Ligne , String heurePremierTrainJO, String heurePremierTrainSamedi, String heurePremierTrainDimancheJF, String heureDernierTrainJO, String heureDernierTrainSamedi, String heureDernierTrainDimancheJF, String cadencementJO, String cadencementSamedi, String cadencementDimancheJF, String heuresPointeJO, String heuresPointeSamedi, String heuresPointeDimancheJF, String tempsStationnementJO, String tempsStationnementSamedi, String tempsStationnementDimancheJF, int vitesseMoyenne) {
	    
		// ligne = ligneDAO.getLigneByID(idLigne);     
		se = HibernateUtils.getSession();
	     Transaction t = se.beginTransaction();
	     parametreHoraire = new ParametreHoraire();
	     parametreHoraire.setLigne(Ligne);
	     parametreHoraire.setHeurePremierTrainJO(heurePremierTrainJO);
	     parametreHoraire.setHeurePremierTrainDimancheJF(heurePremierTrainDimancheJF);
	     parametreHoraire.setHeurePremierTrainSamedi(heurePremierTrainSamedi);
	     parametreHoraire.setHeureDernierTrainJO(heureDernierTrainJO);
	     parametreHoraire.setHeureDernierTrainDimancheJF(heureDernierTrainDimancheJF);
	     parametreHoraire.setHeureDernierTrainSamedi(heureDernierTrainSamedi);
	     parametreHoraire.setCadencementDimancheJF(cadencementDimancheJF);
	     parametreHoraire.setCadencementJO(cadencementJO);
	     parametreHoraire.setCadencementSamedi(cadencementSamedi);
	     parametreHoraire.setHeuresPointeDimancheJF(heuresPointeDimancheJF);
	     parametreHoraire.setHeuresPointeJO(heuresPointeJO);
	     parametreHoraire.setHeuresPointeSamedi(heuresPointeSamedi);
	     parametreHoraire.setTempsStationnementDimancheJF(tempsStationnementDimancheJF);
	     parametreHoraire.setTempsStationnementJO(tempsStationnementJO);
	     parametreHoraire.setTempsStationnementSamedi(tempsStationnementSamedi);
	     parametreHoraire.setVitesseMoyenne(vitesseMoyenne);
	     int idparametreHoraire = (Integer) se.save(parametreHoraire);
		
	     t.commit();
	     se.close();
	     
	     return idparametreHoraire;
	}

	public List<ParametreHoraire> listerParametreHoraire() {
    	se = HibernateUtils.getSession();
    	se.beginTransaction();  	 	
    	parametreHoraireList = se.createQuery("from ParametreHoraire").list();
        return parametreHoraireList;
    }

	public ParametreHoraire getParametreHoraireByID(int id) {
    	se = HibernateUtils.getSession();
    	se.beginTransaction(); 
    	
    	parametreHoraire = (ParametreHoraire) se.createQuery("from ParametreHoraire where idparametrehoraire="+id).uniqueResult();
    	 se.close();
    	
        return parametreHoraire;
    }


}
