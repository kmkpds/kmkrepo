package dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import beans.BiFrequentationLigne;;


public class BiFrequentationLigneDAO {
	private Session se = null;	
	
	public List getFrequentationByLigne(int id) {
		se = HibernateUtils.getSession();
		Query query = se.createSQLQuery(
				"select * from bifrequentationligne where biligne_idbiligne = :id ORDER BY date")
				.addEntity(BiFrequentationLigne.class)
				.setParameter("id", id);
				List result = query.list();		
		return(result);
    }
	
	public List getFrequentationAnnee() {
		se = HibernateUtils.getSession();
		Query query = se.createSQLQuery(
				"select DISTINCT annee from bifrequentationligne");
				List result = query.list();	
				
		return(result);
    }
	
	public BigDecimal getFrequentationEtudiant(int id,int annee,int mois) {
		
		se = HibernateUtils.getSession();
		Query query = se.createSQLQuery(
				"select sum(frequentation_etudiant)from bifrequentationligne where biligne_idbiligne= ? AND mois= ? AND annee= ?")
				.setParameter(0, id)
				.setParameter(1, mois)
				.setParameter(2, annee);	        
		BigDecimal result =  (BigDecimal) query.uniqueResult();	

		if(result!= null){return(result);}
		else{
			result=new BigDecimal((double)0);
			return(result);}
		
    }
	public BigDecimal getFrequentationEtudiantTrimestrielle(int id,int annee,int trimestre) {
		BigDecimal result = new BigDecimal((double)0) ;
		
		switch (trimestre) {

		case 1 :
			se = HibernateUtils.getSession();
			Query query1 = se.createSQLQuery(
					"select sum(frequentation_etudiant)from bifrequentationligne where biligne_idbiligne= ? AND mois between 1 and 3 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (BigDecimal) query1.uniqueResult();
			

		 break;

		case 2 :
			se = HibernateUtils.getSession();
			Query query2 = se.createSQLQuery(
					"select sum(frequentation_etudiant)from bifrequentationligne where biligne_idbiligne= ? AND mois between 4 and 6 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (BigDecimal) query2.uniqueResult();	
		 
		 break;

		case 3 :
			se = HibernateUtils.getSession();
			Query query3 = se.createSQLQuery(
					"select sum(frequentation_etudiant)from bifrequentationligne where biligne_idbiligne= ? AND mois between 7 and 9 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (BigDecimal) query3.uniqueResult();	
		
		 break;
		 
		case 4 :
			se = HibernateUtils.getSession();
			Query query4 = se.createSQLQuery(
					"select sum(frequentation_etudiant)from bifrequentationligne where biligne_idbiligne= ? AND mois between 10 and 12 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (BigDecimal) query4.uniqueResult();	
			
		break;

	
		}
		if(result!= null){return(result);}
		else{
			result=new BigDecimal((double)0);
			return(result);}
	
    }
	public BigDecimal getFrequentationEtudiantSemestrielle(int id,int annee,int semestre) {
		BigDecimal result = new BigDecimal((double)0) ;
		
		switch (semestre) {

		case 1 :
			se = HibernateUtils.getSession();
			Query query1 = se.createSQLQuery(
					"select sum(frequentation_etudiant)from bifrequentationligne where biligne_idbiligne= ? AND mois between 1 and 6 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (BigDecimal) query1.uniqueResult();
			

		 break;

		case 2 :
			se = HibernateUtils.getSession();
			Query query2 = se.createSQLQuery(
					"select sum(frequentation_etudiant)from bifrequentationligne where biligne_idbiligne= ? AND mois between 7 and 12 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (BigDecimal) query2.uniqueResult();	
		 
		 break;

	
		}
		if(result!= null){return(result);}
		else{
			result=new BigDecimal((double)0);
			return(result);}
	
    }
	
	public BigDecimal getFrequentationSalarie(int id,int annee,int mois) {
		
		se = HibernateUtils.getSession();
		Query query = se.createSQLQuery(
				"select sum(frequentation_salarie)from bifrequentationligne where biligne_idbiligne= ? AND mois= ? AND annee= ?")
				.setParameter(0, id)
				.setParameter(1, mois)
				.setParameter(2, annee);	        
		BigDecimal result =  (BigDecimal) query.uniqueResult();	

		if(result!= null){return(result);}
		else{
			result=new BigDecimal((double)0);
			return(result);}
		
    }
	
	
	public BigDecimal getFrequentationSalarieTrimestrielle(int id,int annee,int trimestre) {
		BigDecimal result = new BigDecimal((double)0) ;
		switch (trimestre) {

		case 1 :
			se = HibernateUtils.getSession();
			Query query1 = se.createSQLQuery(
					"select sum(frequentation_salarie)from bifrequentationligne where biligne_idbiligne= ? AND mois between 1 and 3 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (BigDecimal) query1.uniqueResult();
			

		 break;

		case 2 :
			se = HibernateUtils.getSession();
			Query query2 = se.createSQLQuery(
					"select sum(frequentation_salarie)from bifrequentationligne where biligne_idbiligne= ? AND mois between 4 and 6 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (BigDecimal) query2.uniqueResult();	
		 
		 break;

		case 3 :
			se = HibernateUtils.getSession();
			Query query3 = se.createSQLQuery(
					"select sum(frequentation_salarie)from bifrequentationligne where biligne_idbiligne= ? AND mois between 7 and 9 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (BigDecimal) query3.uniqueResult();	
		
		 break;
		 
		case 4 :
			se = HibernateUtils.getSession();
			Query query4 = se.createSQLQuery(
					"select sum(frequentation_salarie)from bifrequentationligne where biligne_idbiligne= ? AND mois between 10 and 12 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (BigDecimal) query4.uniqueResult();	
			
		break;

	
		}
		if(result!= null){return(result);}
		else{
			result=new BigDecimal((double)0);
			return(result);}
	
    }
	
	
	public BigDecimal getFrequentationSalarieSemestrielle(int id,int annee,int semestre) {
		BigDecimal result = new BigDecimal((double)0) ;
		switch (semestre) {

		case 1 :
			se = HibernateUtils.getSession();
			Query query1 = se.createSQLQuery(
					"select sum(frequentation_salarie)from bifrequentationligne where biligne_idbiligne= ? AND mois between 1 and 6 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (BigDecimal) query1.uniqueResult();
			

		 break;

		case 2 :
			se = HibernateUtils.getSession();
			Query query2 = se.createSQLQuery(
					"select sum(frequentation_salarie)from bifrequentationligne where biligne_idbiligne= ? AND mois between 7 and 12 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (BigDecimal) query2.uniqueResult();	
		 
		 break;


	
		}
		if(result!= null){return(result);}
		else{
			result=new BigDecimal((double)0);
			return(result);}
	
    }
	
	public BigDecimal getFrequentationRetraite(int id,int annee,int mois) {
		
		se = HibernateUtils.getSession();
		Query query = se.createSQLQuery(
				"select sum(frequentation_retraite)from bifrequentationligne where biligne_idbiligne= ? AND mois= ? AND annee= ?")
				.setParameter(0, id)
				.setParameter(1, mois)
				.setParameter(2, annee);	        
		BigDecimal result =  (BigDecimal) query.uniqueResult();	

		if(result!= null){return(result);}
		else{
			result=new BigDecimal((double)0);
			return(result);}
		
    }
	
	public BigDecimal getFrequentationRetraiteTrimestrielle(int id,int annee,int trimestre) {
		BigDecimal result = new BigDecimal((double)0) ;
		switch (trimestre) {

		case 1 :
			se = HibernateUtils.getSession();
			Query query1 = se.createSQLQuery(
					"select sum(frequentation_retraite)from bifrequentationligne where biligne_idbiligne= ? AND mois between 1 and 3 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (BigDecimal) query1.uniqueResult();
			

		 break;

		case 2 :
			se = HibernateUtils.getSession();
			Query query2 = se.createSQLQuery(
					"select sum(frequentation_retraite)from bifrequentationligne where biligne_idbiligne= ? AND mois between 4 and 6 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (BigDecimal) query2.uniqueResult();	
		 
		 break;

		case 3 :
			se = HibernateUtils.getSession();
			Query query3 = se.createSQLQuery(
					"select sum(frequentation_retraite)from bifrequentationligne where biligne_idbiligne= ? AND mois between 7 and 9 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (BigDecimal) query3.uniqueResult();	
		
		 break;
		 
		case 4 :
			se = HibernateUtils.getSession();
			Query query4 = se.createSQLQuery(
					"select sum(frequentation_retraite)from bifrequentationligne where biligne_idbiligne= ? AND mois between 10 and 12 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (BigDecimal) query4.uniqueResult();	
			
		break;

	
		}
		if(result!= null){return(result);}
		else{
			result=new BigDecimal((double)0);
			return(result);}
	
    }
	
	public BigDecimal getFrequentationRetraiteSemestrielle(int id,int annee,int semestre) {
		BigDecimal result = new BigDecimal((double)0) ;
		switch (semestre) {

		case 1 :
			se = HibernateUtils.getSession();
			Query query1 = se.createSQLQuery(
					"select sum(frequentation_retraite)from bifrequentationligne where biligne_idbiligne= ? AND mois between 1 and 6 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (BigDecimal) query1.uniqueResult();
			

		 break;

		case 2 :
			se = HibernateUtils.getSession();
			Query query2 = se.createSQLQuery(
					"select sum(frequentation_retraite)from bifrequentationligne where biligne_idbiligne= ? AND mois between 7 and 12 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (BigDecimal) query2.uniqueResult();	
		 
		 break;



	
		}
		if(result!= null){return(result);}
		else{
			result=new BigDecimal((double)0);
			return(result);}
	
    }	
	
	public BigDecimal getFrequentationAutre(int id,int annee,int mois) {
		
		se = HibernateUtils.getSession();
		Query query = se.createSQLQuery(
				"select sum(frequentation_autre)from bifrequentationligne where biligne_idbiligne= ? AND mois= ? AND annee= ?")
				.setParameter(0, id)
				.setParameter(1, mois)
				.setParameter(2, annee);	        
		BigDecimal result =  (BigDecimal) query.uniqueResult();	

		if(result!= null){return(result);}
		else{
			result=new BigDecimal((double)0);
			return(result);}
		
    }
	
	public BigDecimal getFrequentationAutreTrimestrielle(int id,int annee,int trimestre) {
		BigDecimal result = new BigDecimal((double)0) ;
		switch (trimestre) {

		case 1 :
			se = HibernateUtils.getSession();
			Query query1 = se.createSQLQuery(
					"select sum(frequentation_autre)from bifrequentationligne where biligne_idbiligne= ? AND mois between 1 and 3 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (BigDecimal) query1.uniqueResult();
			

		 break;

		case 2 :
			se = HibernateUtils.getSession();
			Query query2 = se.createSQLQuery(
					"select sum(frequentation_autre)from bifrequentationligne where biligne_idbiligne= ? AND mois between 4 and 6 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (BigDecimal) query2.uniqueResult();	
		 
		 break;

		case 3 :
			se = HibernateUtils.getSession();
			Query query3 = se.createSQLQuery(
					"select sum(frequentation_autre)from bifrequentationligne where biligne_idbiligne= ? AND mois between 7 and 9 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (BigDecimal) query3.uniqueResult();	
		
		 break;
		 
		case 4 :
			se = HibernateUtils.getSession();
			Query query4 = se.createSQLQuery(
					"select sum(frequentation_autre)from bifrequentationligne where biligne_idbiligne= ? AND mois between 10 and 12 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (BigDecimal) query4.uniqueResult();	
			
		break;

	
		}
		if(result!= null){return(result);}
		else{
			result=new BigDecimal((double)0);
			return(result);}
	
    }
	
	public BigDecimal getFrequentationAutreSemestrielle(int id,int annee,int semestre) {
		BigDecimal result = new BigDecimal((double)0) ;
		switch (semestre) {

		case 1 :
			se = HibernateUtils.getSession();
			Query query1 = se.createSQLQuery(
					"select sum(frequentation_autre)from bifrequentationligne where biligne_idbiligne= ? AND mois between 1 and 6 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (BigDecimal) query1.uniqueResult();
			

		 break;

		case 2 :
			se = HibernateUtils.getSession();
			Query query2 = se.createSQLQuery(
					"select sum(frequentation_autre)from bifrequentationligne where biligne_idbiligne= ? AND mois between 7 and 12 AND annee= ?")
					.setParameter(0, id)
					.setParameter(1, annee);	        
			 result =  (BigDecimal) query2.uniqueResult();	
		 
		 break;


	
		}
		if(result!= null){return(result);}
		else{
			result=new BigDecimal((double)0);
			return(result);}
	
    }
	
	
public BigDecimal getTauxEtudiant(int id,int annee,int mois) {
		
		se = HibernateUtils.getSession();
		Query query = se.createSQLQuery(
				"select (sum(frequentation_etudiant)/sum(frequentation)) from bifrequentationligne where biligne_idbiligne= ? AND mois= ? AND annee= ?")
				.setParameter(0, id)
				.setParameter(1, mois)
				.setParameter(2, annee);	        
		BigDecimal result =  (BigDecimal) query.uniqueResult();	

		if(result!= null){return(result);}
		else{
			result=new BigDecimal((double)0);
			return(result);}
		
    }

public BigDecimal getTauxSalarie(int id,int annee,int mois) {
	
	se = HibernateUtils.getSession();
	Query query = se.createSQLQuery(
			"select (sum(frequentation_salarie)/sum(frequentation)) from bifrequentationligne where biligne_idbiligne= ? AND mois= ? AND annee= ?")
			.setParameter(0, id)
			.setParameter(1, mois)
			.setParameter(2, annee);	        
	BigDecimal result =  (BigDecimal) query.uniqueResult();		

	if(result!= null){return(result);}
	else{
		result=new BigDecimal((double)0);
		return(result);}
	
}

public BigDecimal getTauxRetraite(int id,int annee,int mois) {
	
	se = HibernateUtils.getSession();
	Query query = se.createSQLQuery(
			"select (sum(frequentation_retraite)/sum(frequentation)) from bifrequentationligne where biligne_idbiligne= ? AND mois= ? AND annee= ?")
			.setParameter(0, id)
			.setParameter(1, mois)
			.setParameter(2, annee);	        
	BigDecimal result =  (BigDecimal) query.uniqueResult();		

	if(result!= null){return(result);}
	else{
		result=new BigDecimal((double)0);
		return(result);}
	
}

public BigDecimal getTauxAutre(int id,int annee,int mois) {
	
	se = HibernateUtils.getSession();
	Query query = se.createSQLQuery(
			"select (sum(frequentation_autre)/sum(frequentation)) from bifrequentationligne where biligne_idbiligne= ? AND mois= ? AND annee= ?")
			.setParameter(0, id)
			.setParameter(1, mois)
			.setParameter(2, annee);	        
	BigDecimal result =  (BigDecimal) query.uniqueResult();	

	if(result!= null){return(result);}
	else{
		result=new BigDecimal((double)0);
		return(result);}
	
}
	

}
