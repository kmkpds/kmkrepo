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
import javax.persistence.OneToMany;
import java.util.List;


@Entity
@Table(name="zone")


public class Zone {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idzone")
	private Integer idZone ;
	@Column(name="nombrehabitants")
	private Integer nombreHabitantsParZone;
	@Column(name="nombrestations")
	private Integer nombreMaxDeStationParZone;
	@Column(name="surface")
	private Float surfaceZone;
	@OneToMany(mappedBy="zone")
	private List<Lieu> lieulist;
	@OneToMany(mappedBy="zone")
	private List<Geolocalisation> geolocalisationlist;
	@ManyToOne(
		cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
	)
	@JoinColumn(name="reseau_idreseau")
	private Reseau reseau;
	public Zone() {
		// TODO Auto-generated constructor stub
	}
	public Integer getIdZone() {
		return idZone;
	}
	public void setIdZone(Integer idZone) {
		this.idZone = idZone;
	}
	public Integer getNombreHabitantsParZone() {
		return nombreHabitantsParZone;
	}
	public void setNombreHabitantsParZone(Integer nombreHabitantsParZone) {
		this.nombreHabitantsParZone = nombreHabitantsParZone;
	}
	public Integer getNombreMaxDeStationParZone() {
		return nombreMaxDeStationParZone;
	}
	public void setNombreMaxDeStationParZone(Integer nombreMaxDeStationParZone) {
		this.nombreMaxDeStationParZone = nombreMaxDeStationParZone;
	}
	public Float getSurfaceZone() {
		return surfaceZone;
	}
	public void setSurfaceZone(Float surfaceZone) {
		this.surfaceZone = surfaceZone;
	}
	public List<Lieu> getLieulist() {
		return lieulist;
	}
	public void setLieulist(List<Lieu> lieulist) {
		this.lieulist = lieulist;
	}
	public List<Geolocalisation> getGeolocalisationlist() {
		return geolocalisationlist;
	}
	public void setGeolocalisationlist(List<Geolocalisation> geolocalisationlist) {
		this.geolocalisationlist = geolocalisationlist;
	}
	public Reseau getReseau() {
		return reseau;
	}
	public void setReseau(Reseau reseau) {
		this.reseau = reseau;
	}
	
}
