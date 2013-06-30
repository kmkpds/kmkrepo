package railsimulator.abonnement;

import java.io.IOException;          

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import beans.ClientPassTourniquet;

import dao.ClientPassTDAO;




import railsimulator.tools.PassTourniquet;

/**
 * Servlet implementation class testAbonnement
 */
@WebServlet("/testAbonnement")
public class testAbonnement extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClientPassTDAO daoClientPass = new ClientPassTDAO();
	private ClientPassTourniquet clientPass = new ClientPassTourniquet();
	private List<ClientPassTourniquet> listClientP = new ArrayList<ClientPassTourniquet>();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public testAbonnement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		listClientP =  daoClientPass.listerClientPass();
		for(int i =0;i<5;i++){
		//	System.out.println(listClientP.get(i));
		}
		request.setAttribute("listClientPass", listClientP);
		//clientPass = listClientP.get(1);
		System.out.println(listClientP.size());
		
		//System.out.println(clientPass.getIdtransaction());
		
		this.getServletContext().getRequestDispatcher( "/WEB-INF/LogTourniquetActivite.jsp").forward( request, response );


	}
		
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
