package beans;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="type_incident")
public class TypeIncident {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idtype")
	private Integer idType ;
	@Column(name="libelletype")
	private String libelleType;
	
	@OneToMany(mappedBy="typeIncident")
	private Set<Incident> listeIncident = new TreeSet<Incident>();

	public Integer getIdType() {
		return idType;
	}

	public void setIdType(Integer idType) {
		this.idType = idType;
	}

	public String getLibelleType() {
		return libelleType;
	}

	public void setLibelleType(String libelleType) {
		this.libelleType = libelleType;
	}

	public Set<Incident> getListeIncident() {
		return listeIncident;
	}

	public void setListeIncident(Set<Incident> listeIncident) {
		this.listeIncident = listeIncident;
	}
	

}
