package beans;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;




@Entity
@Table(name="optimisationchemin")
public class OptimisationChemin {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idoptimisationchemin")
	private Integer idOptimisationChemin ;
	@Column(name="distanceLigne")
	private double distanceligne;
	@Column(name="chemin")
	private String chemin;

	@ManyToOne(
			cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
	)
	@JoinColumn(name="station_idstation1")
	private Station station1;
	
	@ManyToOne(
			cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
	)
	@JoinColumn(name="station_idstation2")
	private Station station2;

	public Integer getIdOptimisationChemin() {
		return idOptimisationChemin;
	}

	public void setIdOptimisationChemin(Integer idOptimisationChemin) {
		this.idOptimisationChemin = idOptimisationChemin;
	}

	public double getDistanceligne() {
		return distanceligne;
	}

	public void setDistanceligne(double distanceligne) {
		this.distanceligne = distanceligne;
	}

	public String getChemin() {
		return chemin;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
	}

	public Station getStation1() {
		return station1;
	}

	public void setStation1(Station station1) {
		this.station1 = station1;
	}

	public Station getStation2() {
		return station2;
	}

	public void setStation2(Station station2) {
		this.station2 = station2;
	}

	
}
