package beans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="horairep")

public class HoraireP {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idhorairep")
	private Integer idhorairep;
	@Column(name="heured")
	private String heured;
	@Column(name="heuref")
	private String heuref;
	

	@OneToMany(fetch=FetchType.EAGER, mappedBy="horairep")
	private List<Employe> listeEmploye;
	
	public List<Employe> getListeEmploye() {
		return listeEmploye;
	}
	public void setListeEmploye(List<Employe> listeEmploye) {
		this.listeEmploye = listeEmploye;
	}
	
	public Integer getIdhorairep() {
		return idhorairep;
	}
	public void setIdhorairep(Integer idhorairep) {
		this.idhorairep = idhorairep;
	}
	public String getHeured() {
		return heured;
	}
	public void setHeured(String heured) {
		this.heured = heured;
	}
	public String getHeuref() {
		return heuref;
	}
	public void setHeuref(String heuref) {
		this.heuref = heuref;
	}
	

}
