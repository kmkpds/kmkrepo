package beans;
import java.util.ArrayList;    
import java.util.Collection;
import java.util.HashSet; 
import java.util.List;
import java.util.Set;
import beans.AbonnementMany;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="zoneabonnement")
public class ZoneAbo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idzoneabo")
	private Integer idzoneabo;
	@Column(name="numero")
	private Integer numZone;
	//@OneToMany(fetch=FetchType.EAGER,mappedBy="idabo")
//	private Set<AbonnementZ> AbonnementZ = new HashSet<AbonnementZ>();
//	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="idtourniquet")
	private Set<Tourniquet> tourniquets = new HashSet<Tourniquet>();
	
	@ManyToMany(fetch=FetchType.EAGER,mappedBy="zones")  
	 Set<AbonnementMany> zoneAbonnement = new HashSet<AbonnementMany>(); 
	public Integer getId() {
		return idzoneabo;
	}
	public void setId(Integer id) {
		this.idzoneabo = id;
	}
	public Integer getNumZone() {
		return numZone;
	}


	public void setNumZone(Integer numZone) {
		this.numZone = numZone;
	}
	public Set<AbonnementMany> getZoneAbonnement() {
		return zoneAbonnement;
	}
	
	public void setZoneAbonnement(Set<AbonnementMany> zoneAbonnement){
			this.zoneAbonnement = zoneAbonnement;
	}
	public Set<Tourniquet> getTourniquets() {
		return tourniquets;
	}
	public void setTourniquets(Set<Tourniquet> tourniquets) {
		this.tourniquets = tourniquets;
	}

	
}
