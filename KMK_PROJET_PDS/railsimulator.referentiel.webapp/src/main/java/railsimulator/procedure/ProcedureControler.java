package railsimulator.procedure;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import railsimulator.tools.AlgoCreationHoraire;
import dao.HibernateUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ParametreHoraireDAO;
import dao.LigneDAO;
import dao.ReseauDAO;
import dao.StationDAO;
import dao.TrainDAO;
import dao.TrainHoraireStationDAO;
import dao.StationDAO;

import beans.ParametreHoraire;
import beans.Ligne;
import beans.Reseau;
import beans.Train;
import beans.TrainHoraireStation;
import beans.Station;


public class ProcedureControler extends HttpServlet {
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

		String action = request.getParameter("action");
		if(action.equals("incident")){

			request.logout();
			this.getServletContext().getRequestDispatcher( "/WEB-INF/index.jsp").forward( request, response );
		}
	}
}