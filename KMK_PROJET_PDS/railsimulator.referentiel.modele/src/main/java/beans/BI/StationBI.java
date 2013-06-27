package beans.BI;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="station")
public class StationBI {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idstation" )
	private Integer idStationBI;
	
	@Column(name="nomstation" )
	private String nomStation;
	
	@ManyToOne(
			cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
	)
	@JoinColumn(name="lignestation")
	private LigneBI ligneStation;

	public Integer getIdStationBI() {
		return idStationBI;
	}

	public void setIdStationBI(Integer idStationBI) {
		this.idStationBI = idStationBI;
	}

	public String getNomStation() {
		return nomStation;
	}

	public void setNomStation(String nomStation) {
		this.nomStation = nomStation;
	}

	public LigneBI getLigneStation() {
		return ligneStation;
	}

	public void setLigneStation(LigneBI ligneStation) {
		this.ligneStation = ligneStation;
	}

}
