package railsimulator.horaire;

import java.io.IOException; 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.testDAOMAX;

/**
 * Servlet implementation class testMaxime
 */
@WebServlet("/testMaxime")
public class testMaxime extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private testDAOMAX test;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public testMaxime() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("tessst");
		
			test = new testDAOMAX();
			System.out.println("test lancé");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		test = new testDAOMAX();
		System.out.println("test lancé");
	}

}
