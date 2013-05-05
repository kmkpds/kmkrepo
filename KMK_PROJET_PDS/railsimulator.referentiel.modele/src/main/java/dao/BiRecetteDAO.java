package dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;


import beans.BiFrequentationLigne;
import beans.BiRecette;


public class BiRecetteDAO {
	private Session se = null;	


	public List getCaByLigne(int id) {
		se = HibernateUtils.getSession();
		Query query = se.createSQLQuery(
				"select * from birecette where biligne_idligne = :id ORDER BY date")
				.addEntity(BiRecette.class)
				.setParameter("id", id);
		List result = query.list();		
		return(result);
	}

	public List getCaAnnee() {
		se = HibernateUtils.getSession();
		Query query = se.createSQLQuery(
				"select DISTINCT annee from birecette");
		List result = query.list();	

		return(result);
	}


	public Double getCoutFixe(int id,int annee,int mois) {

		se = HibernateUtils.getSession();
		Query query = se.createSQLQuery(
				"select sum(coutfixe) from birecette where biligne_idligne= ? AND mois= ? AND annee= ?")
				.setParameter(0, id)
				.setParameter(1, mois)
				.setParameter(2, annee);	        
		Double result =  (Double) query.uniqueResult();	

		if(result!= null){return(result);}
		else{
			result=(double)0;
			return(result);}

	}
	
	public Double getCoutFixeTrimestrielle(int id,int annee,int trimestre) {
		System.out.println(trimestre);
		Double result =(double)0;
		switch (trimestre) {

		case 1 :
		
			se = HibernateUtils.getSession();
			Query query = se.createSQLQuery(
					"select sum(coutfixe) from birecette where biligne_idligne= ? AND mois between 1 and 3 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (Double) query.uniqueResult();
			

		 break;

		case 2 :
			
			se = HibernateUtils.getSession();
			Query query2 = se.createSQLQuery(
					"select sum(coutfixe) from birecette where biligne_idligne= ? AND mois between 4 and 6 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (Double) query2.uniqueResult();	
		 
		 break;

		case 3 :
		
			se = HibernateUtils.getSession();
			Query query3 = se.createSQLQuery(
					"select sum(coutfixe) from birecette where biligne_idligne= ? AND mois between 7 and 9 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (Double) query3.uniqueResult();	
		
		 break;
		 
		case 4 :
		
			se = HibernateUtils.getSession();
			Query query4 = se.createSQLQuery(
					"select sum(coutfixe) from birecette where biligne_idligne= ? AND mois between 10 and 12 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (Double) query4.uniqueResult();
			
		break;

	
		}
		
		
		if(result!= null){return(result);}
		else{
			result=(double)0;
			return(result);}

	}
	
	public Double getCoutFixeSemestrielle(int id,int annee,int semestre) {
		
		Double result =(double)0;
		switch (semestre) {

		case 1 :
	
			se = HibernateUtils.getSession();
			Query query = se.createSQLQuery(
					"select sum(coutfixe) from birecette where biligne_idligne= ? AND mois between 1 and 6 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (Double) query.uniqueResult();
			

		 break;

		case 2 :
			
			se = HibernateUtils.getSession();
			Query query2 = se.createSQLQuery(
					"select sum(coutfixe) from birecette where biligne_idligne= ? AND mois between 7 and 12 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (Double) query2.uniqueResult();	
		 
		 break;
	
		}
		
		
		if(result!= null){return(result);}
		else{
			result=(double)0;
			return(result);}

	}

	
	
	public Double getCoutVariable(int id,int annee,int mois) {

		se = HibernateUtils.getSession();
		Query query = se.createSQLQuery(
				"select sum(coutvariable) from birecette where biligne_idligne= ? AND mois= ? AND annee= ?")
				.setParameter(0, id)
				.setParameter(1, mois)
				.setParameter(2, annee);	        
		Double result =  (Double) query.uniqueResult();	

		if(result!= null){return(result);}
		else{
			result=(double)0;
			return(result);}

	}
	
	public Double getCoutVariableTrimestrielle(int id,int annee,int trimestre) {
		System.out.println(trimestre);
		Double result =(double)0;
		switch (trimestre) {

		case 1 :
		
			se = HibernateUtils.getSession();
			Query query = se.createSQLQuery(
					"select sum(coutvariable) from birecette where biligne_idligne= ? AND mois between 1 and 3 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (Double) query.uniqueResult();
			

		 break;

		case 2 :
		
			se = HibernateUtils.getSession();
			Query query2 = se.createSQLQuery(
					"select sum(coutvariable) from birecette where biligne_idligne= ? AND mois between 4 and 6 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (Double) query2.uniqueResult();	
		 
		 break;

		case 3 :
		
			se = HibernateUtils.getSession();
			Query query3 = se.createSQLQuery(
					"select sum(coutvariable) from birecette where biligne_idligne= ? AND mois between 7 and 9 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (Double) query3.uniqueResult();	
		
		 break;
		 
		case 4 :
		
			se = HibernateUtils.getSession();
			Query query4 = se.createSQLQuery(
					"select sum(coutvariable) from birecette where biligne_idligne= ? AND mois between 10 and 12 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (Double) query4.uniqueResult();
			
		break;

	
		}
		
		
		if(result!= null){return(result);}
		else{
			result=(double)0;
			return(result);}

	}
	
	public Double getCoutVariableSemestrielle(int id,int annee,int semestre) {
		
		Double result =(double)0;
		switch (semestre) {

		case 1 :
	
			se = HibernateUtils.getSession();
			Query query = se.createSQLQuery(
					"select sum(coutvariable) from birecette where biligne_idligne= ? AND mois between 1 and 6 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (Double) query.uniqueResult();
			

		 break;

		case 2 :
			
			se = HibernateUtils.getSession();
			Query query2 = se.createSQLQuery(
					"select sum(coutvariable) from birecette where biligne_idligne= ? AND mois between 7 and 12 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (Double) query2.uniqueResult();	
		 
		 break;

		}
		
		
		if(result!= null){return(result);}
		else{
			result=(double)0;
			return(result);}

	}

}
