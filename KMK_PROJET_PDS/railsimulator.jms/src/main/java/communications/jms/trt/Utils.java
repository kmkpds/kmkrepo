package communications.jms.trt;

import java.io.Serializable;

import javax.jms.JMSException;

import beans.Incident;
import dao.IncidentDAO;

public class Utils {

	public static void exporterMessageInBase(Serializable serializable)
			throws Exception {
		try {
			if (serializable instanceof Incident) {

				IncidentDAO incidentDAO = new IncidentDAO();
				Incident incident = (Incident) serializable;
				boolean ok = incidentDAO.insert(incident);
				if (ok)
					System.out
							.println("insertion ok dans la base ppour l'incident");
				else {
					System.out
							.println("Erreur d'inerstion d'incident dans la base");
				}
			} else {
				throw new Exception("Erreur object non traiter :"
						+ serializable.getClass());
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
