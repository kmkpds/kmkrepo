package railsimulator.horaire.controleur;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import railsimulator.tools.Algo;
import railsimulator.tools.AlgoCreationReseau;
import dao.HibernateUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ParametreHoraireDAO;
import dao.LigneDAO;

import beans.ParametreHoraire;
import beans.Ligne;


public class HoraireControler extends HttpServlet {
	private ParametreHoraire parametreHoraire = new ParametreHoraire();
	private ParametreHoraireDAO parametreHoraireDAO = new ParametreHoraireDAO();
	private LigneDAO ligneDAO = new LigneDAO();
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

		String action = request.getParameter("action");
		if(action.equals("creerHoraire")){
			List<Ligne> ligneList = ligneDAO.listerLigne();
			request.logout();
			request.setAttribute("listeLigne",ligneList);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/creationHoraire.jsp").forward( request, response );
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		if(action.equals("creerHoraire")){
			List<Ligne> ligneList = ligneDAO.listerLigne();
			request.logout();
			request.setAttribute("listeLigne",ligneList);
			this.getServletContext().getRequestDispatcher("/WEB-INF/creationHoraire.jsp").forward( request, response );
	
		}
		if(action.equals("genererGrilleHoraire")){
			int idLigne = Integer.parseInt(request.getParameter("idLigne"));
			int vitesseMoyenne=  Integer.parseInt(request.getParameter("vitesseMoyenne"));
			
			int heurePTJO = Integer.parseInt(request.getParameter("heurePTJO"));
			String heurePTJOStr;
			if(heurePTJO<10){
				heurePTJOStr="0"+heurePTJO;
			}
			else{
				heurePTJOStr= String.valueOf(heurePTJO);
			}
			int minutePTJO = Integer.parseInt(request.getParameter("minutePTJO"));
			String minutePTJOStr;
			if(minutePTJO<10){
				minutePTJOStr="0"+minutePTJO;
			}
			else{
				minutePTJOStr= String.valueOf(minutePTJO);
			}
			String HeurePTJO = heurePTJOStr+ ":" + minutePTJOStr;
			
			int heurePTSamedi = Integer.parseInt(request.getParameter("heurePTSamedi"));
			String heurePTSamediStr;
			if(heurePTSamedi<10){
				heurePTSamediStr="0"+heurePTSamedi;
			}
			else{
				heurePTSamediStr= String.valueOf(heurePTSamedi);
			}
			int minutePTSamedi = Integer.parseInt(request.getParameter("minutePTSamedi"));
			String minutePTSamediStr;
			if(minutePTSamedi<10){
				minutePTSamediStr="0"+minutePTSamedi;
			}
			else{
				minutePTSamediStr= String.valueOf(minutePTSamedi);
			}
			String HeurePTSamedi = heurePTSamediStr+ ":" + minutePTSamediStr;
			
			int heurePTDimancheJF = Integer.parseInt(request.getParameter("heurePTDimancheJF"));
			String heurePTDimancheJFStr;
			if(heurePTDimancheJF<10){
				heurePTDimancheJFStr="0"+heurePTDimancheJF;
			}
			else{
				heurePTDimancheJFStr= String.valueOf(heurePTDimancheJF);
			}
			int minutePTDimancheJF = Integer.parseInt(request.getParameter("minutePTDimancheJF"));
			String minutePTDimancheJFStr;
			if(minutePTDimancheJF<10){
				minutePTDimancheJFStr="0"+minutePTDimancheJF;
			}
			else{
				minutePTDimancheJFStr= String.valueOf(minutePTDimancheJF);
			}
			String HeurePTDimancheJF = heurePTDimancheJFStr+ ":" + minutePTDimancheJFStr;
			
		}
		
	}
}
