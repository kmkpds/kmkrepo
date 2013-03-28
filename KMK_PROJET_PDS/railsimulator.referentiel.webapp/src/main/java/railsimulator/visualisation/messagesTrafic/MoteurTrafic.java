package railsimulator.visualisation.messagesTrafic;

import communications.jms.Consommateur;



public class MoteurTrafic {
	
	private Consommateur conso;
	
	public MoteurTrafic() {
		conso = new Consommateur("178.33.40.163", "queueVisualisationTrafic");
		conso.lancer(new ListenerTrafic());
	}
	
	

}
