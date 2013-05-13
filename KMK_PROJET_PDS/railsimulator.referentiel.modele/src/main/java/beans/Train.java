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




@Entity
@Table(name="train")
public class Train  {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idtrain")
	private Integer idtrain ;
	@Column(name="nomtrain")
	private String nomTrain;
	@Column(name="latitudetrain")
	private double latitudeTrain ;
	@Column(name="longitudetrain")
	private double longitudeTrain ;
	@Column(name="etat")
	private int etat;
	@Column(name="prixtrain")
	private Integer prixtrain ;
	@Column(name="nombredewagon")
	private Integer nombredewagon ;
	@Column(name="vitesse")
	private Integer vitesse ;
	@Column(name="nombredeplacesa")
	private Integer nombredeplacea ;
	@Column(name="nombredeplacesd")
	private Integer nombredeplaced ;
	@ManyToOne(
			cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
	)
	@JoinColumn(name="ligne_idligne")
	private Ligne ligne;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="train")
	private Set<TrainHoraireStation> TrainHoraireStationList = new HashSet<TrainHoraireStation>();
	
	public Integer getIdTrain() {
		return idtrain;
	}
	public void setIdTrain(Integer idtrain) {
		this.idtrain = idtrain;
	}
	public Integer getPrixtrain() {
		return prixtrain;
	}
	public void setPrixtrain(Integer prixtrain) {
		this.prixtrain = prixtrain;
	}
	public Integer getNombredewagon() {
		return nombredewagon;
	}
	public void setNombredewagon(Integer nombredewagon) {
		this.nombredewagon = nombredewagon;
	}
	public Integer getVitesse() {
		return vitesse;
	}
	public void setVitesse(Integer vitesse) {
		this.vitesse = vitesse;
	}
	public Integer getNombredeplacea() {
		return nombredeplacea;
	}
	public void setNombredeplacea(Integer nombredeplacea) {
		this.nombredeplacea = nombredeplacea;
	}
	public Integer getNombredeplaced() {
		return nombredeplaced;
	}
	public void setNombredeplaced(Integer nombredeplaced) {
		this.nombredeplaced = nombredeplaced;
	}
	public Ligne getLigne() {
		return ligne;
	}
	public void setLigne(Ligne ligne) {
		this.ligne = ligne;
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
	public Set<TrainHoraireStation> getTrainHoraireStationList() {
		return TrainHoraireStationList;
	}
	public void setTrainHoraireStationList(
			Set<TrainHoraireStation> trainHoraireStationList) {
		TrainHoraireStationList = trainHoraireStationList;
	}
	
	
}