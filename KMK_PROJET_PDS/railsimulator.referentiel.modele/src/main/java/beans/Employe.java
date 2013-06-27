package beans;

import java.util.HashSet;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="employe")

public class Employe {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idemp")
	private Integer idemp;
	@Column(name="nom")
	private String nom;
	@Column(name="prenom")
	private String prenom;
	@Column(name="fonction")
	private String fonction;
	@Column(name="horairep_id")
	private Integer horairep_id;
	@Column(name="site_idsite")
	private Integer site_idsite;
	
	@ManyToOne(
			cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
	)
	@JoinColumn(name="site_idsite", insertable = false, updatable = false)	
	private  Site site;
	
	@ManyToOne(
			cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
	)
	@JoinColumn(name="horairep_id", insertable = false, updatable = false)
	private  HoraireP horairep;
	
	@OneToMany(mappedBy="employe")
	private Set<FactHoraire> listeFactHoraire= new HashSet<FactHoraire>();
	
	@OneToMany(mappedBy="employe")
	private Set<EmpTache> listeEmpTache= new HashSet<EmpTache>();
	
	
	
	public Employe() {
		// TODO Auto-generated constructor stub
	}
	
	public Employe (String nom, String prenom, String fonction, Integer horairep_id, Integer site_idsite) {
		
		this.nom=nom;
		this.prenom= prenom; 
		this.fonction=fonction;
		this.horairep_id=horairep_id;
		this.site_idsite=site_idsite;
	
		
	}

	public Integer getIdemp() {
		return idemp;
	}

	public void setIdemp(Integer idemp) {
		this.idemp = idemp;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getFonction() {
		return fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

	public Integer getHorairep_id() {
		return horairep_id;
	}

	public void setHorairep_id(Integer horairep_id) {
		this.horairep_id = horairep_id;
	}

	public Integer getSite_idsite() {
		return site_idsite;
	}

	public void setSite_idsite(Integer site_idsite) {
		this.site_idsite = site_idsite;
	}

	public HoraireP getHorairep() {
		return horairep;
	}

	public void setHorairep(HoraireP horairep) {
		this.horairep = horairep;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}


	public Set<FactHoraire> getListeFactHoraire() {
		return listeFactHoraire;
	}

	public void setListeFactHoraire(Set<FactHoraire> listeFactHoraire) {
		this.listeFactHoraire = listeFactHoraire;
	}

	public Set<EmpTache> getListeEmpTache() {
		return listeEmpTache;
	}

	public void setListeEmpTache(Set<EmpTache> listeEmpTache) {
		this.listeEmpTache = listeEmpTache;
	}




}
