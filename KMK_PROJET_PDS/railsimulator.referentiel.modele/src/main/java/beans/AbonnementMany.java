package beans;
import java.util.ArrayList;   
import java.util.Collection;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import beans.ZoneAbo;

@XmlRootElement
@Entity
@Table(name="abonnementMany")
public class AbonnementMany {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idabo")
	private Integer id;
	@Column(name="nom")
	private String nom;
	@ManyToOne(
			cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
		)
		@JoinColumn(name="type")
		private TypeAbonnement type;
	
	@ManyToOne(
			cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
		)
		@JoinColumn(name="idclient")
		private Client idclient;

	@ManyToMany(fetch = FetchType.EAGER,
			cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(name="abonnement_zones",
	 joinColumns=@JoinColumn(name="idabo"),
	 inverseJoinColumns=@JoinColumn(name="idzoneabo")
	)
	List<ZoneAbo> zones = new ArrayList<ZoneAbo>() ;
	
	public List<ZoneAbo> getZoneAbo() {
		return zones;
	}


	public void setZoneAbo(List<ZoneAbo> zone) {
		this.zones = zone;
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


	public TypeAbonnement getType() {
		return type;
	}


	public void setType(TypeAbonnement type) {
		this.type = type;
	}


	public Client getIdclient() {
		return idclient;
	}


	public void setIdclient(Client idclient) {
		this.idclient = idclient;
	}


  
	
}
