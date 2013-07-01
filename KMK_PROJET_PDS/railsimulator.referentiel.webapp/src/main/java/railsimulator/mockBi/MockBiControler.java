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

import beans.BiFrequentationLigne;
import beans.FrequentationLigneDWH;

import dao.FrequentationLigneDWHDAO;
import dao.BI.HibernateUtilsBiAuto;


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
		Query create12 = se.createSQLQuery("delete from bifrequentationligne");
		create12.executeUpdate();
		t12.commit();
		
	}
	
	private void lancerMoteur(){
		
		FrequentationLigneDWH freqLigne;
		FrequentationLigneDWHDAO freqLigneDao;
		
		for(int i=1;i<=49;i++){
			int idFreq = i; 
			
			Integer freq, freqEtu, freqSalarie, freqRetraite, freqAutres;
			freqEtu = genererFreq();
			freqSalarie = genererFreq();
			freqRetraite = genererFreq();
			freqAutres = genererFreq();
			freq = freqEtu + freqSalarie + freqRetraite + freqAutres;
			
			int mois, annee;
			mois = genererMois();
			annee = 2012;
			
			int idligne = 1;
			
			String dateFreq = genererDate(mois, annee);
			
			freqLigneDao = new FrequentationLigneDWHDAO();
			freqLigne = new FrequentationLigneDWH(idFreq, freq, freqEtu, freqSalarie, freqRetraite, freqAutres, dateFreq, mois, annee, idligne);
			freqLigneDao.insertFrequentation(freqLigne);
			
		}
		
	}
	
	private static int genererMois(){
		return (int) (Math.random() * 12);
	}
	
	private static String genererDate(int mois, int annee){
		int jour = (int) (Math.random() * 31);
		String date = String.valueOf(annee) + "-" + String.valueOf(mois) + "-" + String.valueOf(jour);
		return date;
	}
	
	private static int genererFreq(){
		return (int) (Math.random() * 100000);
	}

	
}
