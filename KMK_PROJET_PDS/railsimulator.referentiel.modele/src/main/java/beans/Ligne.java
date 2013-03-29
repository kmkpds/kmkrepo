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
@Table(name="ligne")
public class Ligne  {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idligne")
	private Integer idLigne ;
	@Column(name="nomligne")
	private String nomLigne;
	@Column(name="commentaire")
	private String commentaire;
	
	@OneToMany(mappedBy="ligne")
	private List<Station> stationlist;
	
	
	@ManyToOne(
			cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
	)
	@JoinColumn(name="reseau_idreseau")
	private Reseau reseau;
	
	
	public Ligne() {
		// TODO Auto-generated constructor stub
	}

	public Integer getIdLigne() {
		return idLigne;
	}

	public void setIdLigne(Integer idLigne) {
		this.idLigne = idLigne;
	}

	public String getNomLigne() {
		return nomLigne;
	}

	public void setNomLigne(String nomLigne) {
		this.nomLigne = nomLigne;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public List<Station> getStationlist() {
		return stationlist;
	}

	public void setStationlist(List<Station> stationlist) {
		this.stationlist = stationlist;
	}

	public Reseau getReseau() {
		return reseau;
	}

	public void setReseau(Reseau reseau) {
		this.reseau = reseau;
	}

	

	
}
