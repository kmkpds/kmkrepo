package beans;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.OneToMany;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Nytos
 *
 */
@Entity
@Table(name="train")
public class Train {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idtrain")
	private Integer idTrain;
	@Column(name="nomtrain")
	private String nomTrain;
	@Column(name="latitudetrain")
	private double latitudeTrain ;
	@Column(name="longitudetrain")
	private double longitudeTrain ;
	@Column(name="etat")
	private int etat;
	@ManyToOne(
			cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
	)
	@JoinColumn(name="ligne_idligne")
	private Ligne ligne;
	@OneToMany(fetch=FetchType.EAGER,mappedBy="train")
		private Set<TrainHoraireStation> TrainHoraireStationList = new HashSet<TrainHoraireStation>();
	public Integer getIdTrain() {
		return idTrain;
	}
	public void setIdTrain(Integer idTrain) {
		this.idTrain = idTrain;
	}
	public String getNomTrain() {
		return nomTrain;
	}
	public void setNomTrain(String nomTrain) {
		this.nomTrain = nomTrain;
	}
	public double getLatitudeTrain() {
		return latitudeTrain;
	}
	public void setLatitudeTrain(double latitudeTrain) {
		this.latitudeTrain = latitudeTrain;
	}
	public double getLongitudeTrain() {
		return longitudeTrain;
	}
	public void setLongitudeTrain(double longitudeTrain) {
		this.longitudeTrain = longitudeTrain;
	}
	public int getEtat() {
		return etat;
	}
	public void setEtat(int etat) {
		this.etat = etat;
	}
	public Ligne getLigne() {
		return ligne;
	}
	public void setLigne(Ligne ligne) {
		this.ligne = ligne;
	}
	public Set<TrainHoraireStation> getTrainHoraireStationList() {
		return TrainHoraireStationList;
	}
	public void setTrainHoraireStationList(
			Set<TrainHoraireStation> trainHoraireStationList) {
		TrainHoraireStationList = trainHoraireStationList;
	}

}
