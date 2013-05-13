package railsimulator.procedure;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import railsimulator.web.incident.base.UtilsProjet;

import beans.Incident;

/**
 * Servlet implementation class DetailIncidentServlet
 */
public class DetailIncidentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailIncidentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idincident=request.getParameter("idincident");
		Incident incident = UtilsProjet.getIncidentById(Integer.parseInt(idincident));
		request.getSession().setAttribute("incident", incident);
		getServletContext().getRequestDispatcher("/detailIncident.jsp").forward(request,response);
	}

}
