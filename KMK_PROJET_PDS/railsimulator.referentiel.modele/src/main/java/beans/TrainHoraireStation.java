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
import java.util.List;

@Entity
@Table(name="trainhorairestation")
public class TrainHoraireStation {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer idTrain;
	@Column(name="heurejo")
	private String heureJO ;
	@Column(name="heuresamedi")
	private String heureSamedi;
	@Column(name="heuredimanchejf")
	private String heureDimancheJF;
	@ManyToOne(
			cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
	)
	@JoinColumn(name="station_idstation")
	private Station station;
	@ManyToOne(
			cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
	)
	@JoinColumn(name="train_idtrain")
	private Train train;
	public Integer getIdTrain() {
		return idTrain;
	}
	public void setIdTrain(Integer idTrain) {
		this.idTrain = idTrain;
	}
	public String getHeureJO() {
		return heureJO;
	}
	public void setHeureJO(String heureJO) {
		this.heureJO = heureJO;
	}
	public String getHeureSamedi() {
		return heureSamedi;
	}
	public void setHeureSamedi(String heureSamedi) {
		this.heureSamedi = heureSamedi;
	}
	public String getHeureDimancheJF() {
		return heureDimancheJF;
	}
	public void setHeureDimancheJF(String heureDimancheJF) {
		this.heureDimancheJF = heureDimancheJF;
	}
	public Station getStation() {
		return station;
	}
	public void setStation(Station station) {
		this.station = station;
	}
	public Train getTrain() {
		return train;
	}
	public void setTrain(Train train) {
		this.train = train;
	}


}