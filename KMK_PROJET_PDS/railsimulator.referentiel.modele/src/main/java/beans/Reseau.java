package beans;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import java.util.List;
@Entity
@Table(name="reseau")
public class Reseau {
	
	//Constructeur Réseau
	public Reseau() {
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idreseau")
	private Integer idReseau ;
	@Column(name="nomreseau")
	private String nomReseau;
	
	@OneToMany(mappedBy="reseau")
	private List<Zone> zonelist;
	
	@OneToMany(mappedBy="reseau")
	private List<Ligne> lignelist;

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
	public List<Zone> getZonelist() {
		return zonelist;
	}
	public void setZonelist(List<Zone> zonelist) {
		this.zonelist = zonelist;
	}
}
