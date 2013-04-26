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
@Table(name="lieu")
public class Lieu {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idlieu")
	private Integer idLieu ;
	@Column(name="nomlieu")
	private String nomLieu;
	@Column(name="typelieu")
	private String typeLieu;
	@Column(name="longitude")
	private double longitudeLieu;
	@Column(name="latitude")
	private double latitudeLieu;
	@ManyToOne(
			cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
	)
	@JoinColumn(name="zone_idzone")
	private Zone zone;
	public Lieu() {
		// TODO Auto-generated constructor stub
	}
	public Integer getIdLieu() {
		return idLieu;
	}
	public void setIdLieu(Integer idLieu) {
		this.idLieu = idLieu;
	}
	public String getNomLieu() {
		return nomLieu;
	}
	public void setNomLieu(String nomLieu) {
		this.nomLieu = nomLieu;
	}
	public String getTypeLieu() {
		return typeLieu;
	}
	public void setTypeLieu(String typeLieu) {
		this.typeLieu = typeLieu;
	}
	public double getLongitudeLieu() {
		return longitudeLieu;
	}
	public void setLongitudeLieu(double longitudeLieu) {
		this.longitudeLieu = longitudeLieu;
	}
	public double getLatitudeLieu() {
		return latitudeLieu;
	}
	public void setLatitudeLieu(double latitudeLieu) {
		this.latitudeLieu = latitudeLieu;
	}
	public Zone getZone() {
		return zone;
	}
	public void setZone(Zone zone) {
		this.zone = zone;
	}

}
