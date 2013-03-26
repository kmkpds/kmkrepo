package beans;

import java.util.List;

public class Train {
	
	private Integer idTrain;
	private String nomTrain;
	private List<Capteur> listCapteur;
	private Canton position;
	
	public Canton getPosition() {
		return position;
	}
	
	public void setPosition(Canton position) {
		this.position = position;
	}
	
	public List<Capteur> getListCapteur() {
		return listCapteur;
	}
	
	public void setListCapteur(List<Capteur> listCapteur) {
		this.listCapteur = listCapteur;
	}
	
	public String getNomTrain() {
		return nomTrain;
	}
	
	public void setNomTrain(String nomTrain) {
		this.nomTrain = nomTrain;
	}
	
	public Integer getIdTrain() {
		return idTrain;
	}
	public void setIdTrain(Integer idTrain) {
		this.idTrain = idTrain;
	}
	
	
	
}
