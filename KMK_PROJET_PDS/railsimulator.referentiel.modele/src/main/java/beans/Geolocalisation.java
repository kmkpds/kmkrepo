package beans;

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
@Table(name="geolocalisation")
public class Geolocalisation {
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idgeolocalisation")
	private Integer idgeolocalisation ;
	@Column(name="latitude")
	private double LatitudeGeolocalisation ;
	@Column(name="longitude")
	private double LongitudeGeolocalisation ;
	
	@ManyToOne(
			cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
	)
	@JoinColumn(name="Zone_idzone")
	private Zone zone;
	

	public Geolocalisation() {
		// TODO Auto-generated constructor stub
	}


	public Integer getIdgeolocalisation() {
		return idgeolocalisation;
	}


	public void setIdgeolocalisation(Integer idgeolocalisation) {
		this.idgeolocalisation = idgeolocalisation;
	}


	public double getLatitudeGeolocalisation() {
		return LatitudeGeolocalisation;
	}


	public void setLatitudeGeolocalisation(double latitudeGeolocalisation) {
		LatitudeGeolocalisation = latitudeGeolocalisation;
	}


	public double getLongitudeGeolocalisation() {
		return LongitudeGeolocalisation;
	}


	public void setLongitudeGeolocalisation(double longitudeGeolocalisation) {
		LongitudeGeolocalisation = longitudeGeolocalisation;
	}


	public Zone getZone() {
		return zone;
	}


	public void setZone(Zone zone) {
		this.zone = zone;
	}
	

}
