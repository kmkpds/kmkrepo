package beans;


import java.util.HashSet;
import java.util.Set;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import javax.persistence.Table;
import javax.persistence.OneToMany;

@Entity
@Table(name="biligne")
public class BiLigne {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idbiligne")
	private Integer idBiLigne ;
	@Column(name="nombiligne")
	private String nomBiLigne;
	
	@OneToMany(mappedBy="biLigne")
	private Set<BiFrequentationLigne> listeFrequentationLigne = new HashSet<BiFrequentationLigne>();
	
	@OneToMany(mappedBy="biLigne")
	private Set<BiRecette> listeBiRecette = new HashSet<BiRecette>();
	
	@OneToMany(mappedBy="biLigne")
	private Set<Incident> listeIncident = new HashSet<Incident>();
	
	public BiLigne() {
		// TODO Auto-generated constructor stub
	}

	public Integer getIdBiLigne() {
		return idBiLigne;
	}

	public void setIdBiLigne(Integer idBiLigne) {
		this.idBiLigne = idBiLigne;
	}

	public String getNomBiLigne() {
		return nomBiLigne;
	}

	public void setNomBiLigne(String nomBiLigne) {
		this.nomBiLigne = nomBiLigne;
	}

	public Set<BiFrequentationLigne> getListeFrequentationLigne() {
		return listeFrequentationLigne;
	}

	public void setListeFrequentationLigne(
			Set<BiFrequentationLigne> listeFrequentationLigne) {
		this.listeFrequentationLigne = listeFrequentationLigne;
	}

	public Set<BiRecette> getListeBiRecette() {
		return listeBiRecette;
	}

	public void setListeBiRecette(Set<BiRecette> listeBiRecette) {
		this.listeBiRecette = listeBiRecette;
	}

	public Set<Incident> getListeIncident() {
		return listeIncident;
	}

	public void setListeIncident(Set<Incident> listeIncident) {
		this.listeIncident = listeIncident;
	}





}
