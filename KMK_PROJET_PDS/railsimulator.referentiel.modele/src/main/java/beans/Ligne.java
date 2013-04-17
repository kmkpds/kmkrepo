package beans;






import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.OneToMany;

import java.util.HashSet;
import java.util.List;
import java.util.Set;




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
	//private List<Station> stationlist;
	private Set<Station> stationlist = new HashSet<Station>();
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="ligne")
	//private List<ParametreHoraire> parametreHoraireList;
	private Set<ParametreHoraire> parametreHoraireList = new HashSet<ParametreHoraire>();
	/*public List<ParametreHoraire> getParametreHoraireList() {
		return parametreHoraireList;
	}

	public void setParametreHoraireList(List<ParametreHoraire> parametreHoraireList) {
		this.parametreHoraireList = parametreHoraireList;
	}*/

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

	/*public List<Station> getStationlist() {
		return stationlist;
	}

	public void setStationlist(List<Station> stationlist) {
		this.stationlist = stationlist;
	}*/

	public Reseau getReseau() {
		return reseau;
	}

	public Set<Station> getStationlist() {
		return stationlist;
	}

	public void setStationlist(Set<Station> stationlist) {
		this.stationlist = stationlist;
	}

	public Set<ParametreHoraire> getParametreHoraireList() {
		return parametreHoraireList;
	}

	public void setParametreHoraireList(Set<ParametreHoraire> parametreHoraireList) {
		this.parametreHoraireList = parametreHoraireList;
	}

	public void setReseau(Reseau reseau) {
		this.reseau = reseau;
	}

	

	
}
