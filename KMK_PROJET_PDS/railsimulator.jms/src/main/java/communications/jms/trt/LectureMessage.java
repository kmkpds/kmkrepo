package communications.jms.trt;

import communications.jms.Consommateur;

public class LectureMessage extends Thread {

	private Consommateur consommateur = null;
	private String nomQueue = "SIIncidentMessage";
	private String ip = "178.33.40.163:61616";

	public LectureMessage() {
		super();

		consommateur = new Consommateur(ip, nomQueue);
		producteur = new Producteur

	}

	@Override
	public void run() {

		while (true) {
			try {
				sleep(11000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				System.out.println("Fin du thread d'ecoute JMS");
			}

		}
	}

	public Consommateur getConsommateur() {
		return consommateur;
	}

	public void setConsommateur(Consommateur consommateur) {
		this.consommateur = consommateur;
	}

	public String getNomQueue() {
		return nomQueue;
	}

	public void setNomQueue(String nomQueue) {
		this.nomQueue = nomQueue;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
