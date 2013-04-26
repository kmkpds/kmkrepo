package beans;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity
@Table(name="reseau")
public class Reseau {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idreseau")
	private Integer idReseau ;
	@Column(name="nomreseau")
	private String nomReseau;
	@OneToMany(fetch=FetchType.EAGER,
			mappedBy="reseau")
	//private List<Zone> zonelist;
	private Set<Zone> zonelist = new HashSet<Zone>();
	@OneToMany( mappedBy="reseau")
	//private List<Ligne> lignelist;
	private Set<Ligne> lignelist = new HashSet<Ligne>();
	
	public Set<Ligne> getLignelist() {
		return lignelist;
	}
	public void setLignelist(Set<Ligne> lignelist) {
		this.lignelist = lignelist;
	}
	public Reseau() {
		// TODO Auto-generated constructor stub
	}
	public Integer getIdReseau() {
		return idReseau;
	}
	public void setIdReseau(Integer idReseau) {
		this.idReseau = idReseau;
	}
	public String getNomReseau() {
		return nomReseau;
	}
	public void setNomReseau(String nomReseau) {
		this.nomReseau = nomReseau;
	}
	public Set<Zone> getZonelist() {
		return zonelist;
	}
	public void setZonelist(Set<Zone> zonelist) {
		this.zonelist = zonelist;
	}
}
