package dao;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;


import beans.BiFrequentationLigne;
import beans.BiRecette;

public class BiIncidentDAO {
	private Session se = null;	

	public List getIncidentAnnee() {
		se = HibernateUtils.getSession();
		Query query = se.createSQLQuery(
				"select DISTINCT annee from incident");
		List result = query.list();	

		return(result);
	}



	public int getNbIncidentParLigne(int idtype,int annee,int idligne, String affichage) {
		int result= 0;
		
		if(affichage.equals("annuelle")){
			se = HibernateUtils.getSession();
			Query query = se.createSQLQuery(
					"select count(*) from incident where type_idtype= ? and annee= ? and biligne_idbiligne= ? ")
					.setParameter(0, idtype)
					.setParameter(1, annee)
					.setParameter(2, idligne);	        
			 result =   ((BigInteger) query.uniqueResult()).intValue();		
		}
		else if(affichage.equals("trimestre 1")){
			se = HibernateUtils.getSession();
			Query query = se.createSQLQuery(
					"select count(*) from incident where type_idtype= ? and annee= ? and biligne_idbiligne= ? and mois between 1 and 3")
					.setParameter(0, idtype)
					.setParameter(1, annee)
					.setParameter(2, idligne);	        
			 result =   ((BigInteger) query.uniqueResult()).intValue();	
		}
		else if(affichage.equals("trimestre 2")){
			se = HibernateUtils.getSession();
			Query query = se.createSQLQuery(
					"select count(*) from incident where type_idtype= ? and annee= ? and biligne_idbiligne= ? and mois between 4 and 6")
					.setParameter(0, idtype)
					.setParameter(1, annee)
					.setParameter(2, idligne);	        
			 result =   ((BigInteger) query.uniqueResult()).intValue();	
		}
		else if(affichage.equals("trimestre 3")){
			se = HibernateUtils.getSession();
			Query query = se.createSQLQuery(
					"select count(*) from incident where type_idtype= ? and annee= ? and biligne_idbiligne= ? and mois between 7 and 9")
					.setParameter(0, idtype)
					.setParameter(1, annee)
					.setParameter(2, idligne);	        
			 result =   ((BigInteger) query.uniqueResult()).intValue();	
		}
		else if(affichage.equals("trimestre 4")){
			se = HibernateUtils.getSession();
			Query query = se.createSQLQuery(
					"select count(*) from incident where type_idtype= ? and annee= ? and biligne_idbiligne= ? and mois between 10 and 12")
					.setParameter(0, idtype)
					.setParameter(1, annee)
					.setParameter(2, idligne);	        
			 result =   ((BigInteger) query.uniqueResult()).intValue();	
		}
		else if(affichage.equals("semestre 1")){
			se = HibernateUtils.getSession();
			Query query = se.createSQLQuery(
					"select count(*) from incident where type_idtype= ? and annee= ? and biligne_idbiligne= ? and mois between 1 and 6")
					.setParameter(0, idtype)
					.setParameter(1, annee)
					.setParameter(2, idligne);	        
			 result =   ((BigInteger) query.uniqueResult()).intValue();	
		}
		else if(affichage.equals("semestre 2")){
			se = HibernateUtils.getSession();
			Query query = se.createSQLQuery(
					"select count(*) from incident where type_idtype= ? and annee= ? and biligne_idbiligne= ? and mois between 7 and 12")
					.setParameter(0, idtype)
					.setParameter(1, annee)
					.setParameter(2, idligne);	        
			 result =   ((BigInteger) query.uniqueResult()).intValue();	
		}
		
		return result;
	}
	public Time getTempsMoyenIncident(int idtype,int annee,int idligne, String affichage) {
		
		Time result= new Time(0,0,0);
		if(affichage.equals("annuelle")){
			se = HibernateUtils.getSession();
			Query query = se.createSQLQuery(
					"SELECT SEC_TO_TIME( AVG( TIME_TO_SEC( TIMEDIFF( datefin, datedebut ) ) ) ) FROM incident WHERE datefin IS NOT NULL AND type_idtype= ? AND annee= ? and biligne_idbiligne= ? ")
					.setParameter(0, idtype)
					.setParameter(1, annee)
					.setParameter(2, idligne);	        
			result =    (Time) query.uniqueResult();		
		}

		if(result!= null){return(result);}
		else{
			result= new Time(0,0,0);
			return(result);}
		
	}
	
	public Time getTempsMaxIncident(int idtype,int annee,int idligne, String affichage) {
		
		Time result= new Time(0,0,0);
		if(affichage.equals("annuelle")){
			se = HibernateUtils.getSession();
			Query query = se.createSQLQuery(
					"SELECT SEC_TO_TIME( MAX( TIME_TO_SEC( TIMEDIFF( datefin, datedebut ) ) ) ) FROM incident WHERE datefin IS NOT NULL AND type_idtype= ? AND annee= ? and biligne_idbiligne= ? ")
					.setParameter(0, idtype)
					.setParameter(1, annee)
					.setParameter(2, idligne);	        
			result =    (Time) query.uniqueResult();		
		}

		if(result!= null){return(result);}
		else{
			result= new Time(0,0,0);
			return(result);}
		
	}
public Time getTempsMinIncident(int idtype,int annee,int idligne, String affichage) {
		
		Time result= new Time(0,0,0);
		if(affichage.equals("annuelle")){
			se = HibernateUtils.getSession();
			Query query = se.createSQLQuery(
					"SELECT SEC_TO_TIME( Min( TIME_TO_SEC( TIMEDIFF( datefin, datedebut ) ) ) ) FROM incident WHERE datefin IS NOT NULL AND type_idtype= ? AND annee= ? and biligne_idbiligne= ? ")
					.setParameter(0, idtype)
					.setParameter(1, annee)
					.setParameter(2, idligne);	        
			result =    (Time) query.uniqueResult();		
		}

		if(result!= null){return(result);}
		else{
			result= new Time(0,0,0);
			return(result);}
		
	}
	
}
