package railsimulator.mockBi;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.BI.LigneBI;
import beans.BI.StationBI;
import beans.BI.TypeAbonnementBI;

import dao.BI.FrequentationBiDAO;
import dao.BI.HibernateUtilsBiAuto;
import dao.BI.StationBiDAO;
import dao.BI.TypeAbonnementBiDAO;


public class MockBiControler extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		String action = request.getParameter("action");
		
		if(action.equals("accueilMockBi")){
			request.logout();
			this.getServletContext().getRequestDispatcher( "/WEB-INF/mockBi.jsp").forward( request, response );
		}
		
		if(action.equals("purger")){
			request.logout();
			purger();
			this.getServletContext().getRequestDispatcher( "/WEB-INF/mockBi.jsp").forward( request, response );
		}
		
		if(action.equals("lancerMoteur")){
			request.logout();
			lancerMoteur();
			this.getServletContext().getRequestDispatcher( "/WEB-INF/mockBi.jsp").forward( request, response );
		}
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	private void purger(){
		Session se = null;
		se = HibernateUtilsBiAuto.getSession();

		Transaction t12 = se.beginTransaction();
		Query create12 = se.createSQLQuery("delete from FrequentationBi");
		create12.executeUpdate();
		t12.commit();
		
	}
	
	private void lancerMoteur(){
		
		FrequentationBiDAO freqDao = new FrequentationBiDAO();
		
		for(long i=1;i<=2;i++){
			
			Calendar dateFreq;
			dateFreq = genererDate();
			
			StationBI stationFreq = new StationBI();
			stationFreq = genereStationFreq();
			
			LigneBI ligneFreq = new LigneBI();
			ligneFreq.setIdLigneBi(1);
			ligneFreq.setNomLigneBi("Ligne 1");
			
			TypeAbonnementBI typeFreq = new TypeAbonnementBI();
			typeFreq = genereTypeFreq();	
			
			freqDao.createFreq(dateFreq, stationFreq, ligneFreq, typeFreq);
			
		}
	}
	
	private static Calendar genererDate(){
		int jour = (int) Math.random() * 31;
		int mois = (int) Math.random() * 12;
		int annee = 2013;
		
		int heure = (int) Math.random() * 12;
		int minute = (int) Math.random() * 59;
		int seconde = (int) Math.random() * 59;
		
		Calendar dateReturn = Calendar.getInstance();
		dateReturn.set(annee, mois, jour, heure, minute, seconde);
		
		
		return dateReturn;
	}
	
	private static StationBI genereStationFreq(){
		StationBiDAO stationDao = new StationBiDAO();
		List<StationBI> listStation;
		listStation = stationDao.listerStation();
		StationBI stationReturn;
		
		int intReturn = (int) (Math.random()*listStation.size());
		stationReturn = listStation.get(intReturn); 
		
		return stationReturn;
	}
	
	private static TypeAbonnementBI genereTypeFreq(){
		TypeAbonnementBiDAO typeDao = new TypeAbonnementBiDAO();
		List<TypeAbonnementBI> listType;
		listType = typeDao.listerTypeAbo();
		TypeAbonnementBI typeReturn;
		
		int intReturn = (int) (Math.random()*listType.size());
		typeReturn = listType.get(intReturn); 
		
		return typeReturn;
	}
}
