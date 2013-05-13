package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import beans.BiRecette;
import beans.BiSatisfaction;

public class BiSatisfactionDAO {
	private Session se = null;
	
	public List getSatisfactionByLigne(int id) {
		se = HibernateUtils.getSession();
		Query query = se.createSQLQuery(
				"select * from bisatisfaction where biligne_idbiligne = :id ORDER BY date")
				.addEntity(BiSatisfaction.class)
				.setParameter("id", id);
		List result = query.list();		
		return(result);
	}

}
