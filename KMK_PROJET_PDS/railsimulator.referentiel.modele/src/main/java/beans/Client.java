package beans;
import static javax.persistence.GenerationType.IDENTITY;  

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet; 
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@Entity
@Table(name="client")
public class Client implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name="idclient")
	private Integer id;
	@Column(name="nom")
	private String nom;
	@Column(name="prenom")
	private String prenom;
	@Column(name="dateFinAbo")
	private Date dateFinAbo;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="idclient")
	private Set<AbonnementMany> AbonnementMany = new HashSet<AbonnementMany>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.client", cascade=CascadeType.ALL)
	private List<ClientPassTourniquet> clientPass = new ArrayList<ClientPassTourniquet>();
	
	public List<ClientPassTourniquet> getClientPass() {
		return clientPass;
	}
	public void setClientPass(List<ClientPassTourniquet> clientPass) {
		this.clientPass = clientPass;
	}
	public Integer getId() {
		return id; 
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Date getDateFinAbo() {
		return dateFinAbo;
	}
	public void setDateFinAbo(Date dateFinAbo) {
		this.dateFinAbo = dateFinAbo;
	}
	public Set<AbonnementMany> getAbonnementMany() {
		return AbonnementMany;
	}
	public void setAbonnementMany(Set<AbonnementMany> abonnementMany) {
		this.AbonnementMany = abonnementMany;
	}
	
	

}
