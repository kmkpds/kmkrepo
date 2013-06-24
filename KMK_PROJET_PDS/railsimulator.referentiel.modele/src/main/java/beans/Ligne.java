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
	@Column(name="longueur")
	private Integer longueur;
	@Column(name="nombredestation")
	private Integer nombredestation;
	@Column(name="dureemoyennetrajet")
	private Integer dureemoyennetrajet;
	@Column(name="nombredetrain")
	private Integer nombredetrain;
	@Column(name="nombrepassagers")
	private Integer nombrepassagers;
	@Column(name="prixdeplace")
	private Double prixdeplace;
	
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="ligne")
	private Set<Station> stationlist = new HashSet<Station>();
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="ligne")
	private Set<ParametreHoraire> parametreHoraireList = new HashSet<ParametreHoraire>();


	@OneToMany(fetch = FetchType.EAGER,mappedBy="ligne")
	private Set<Train> trainlist = new HashSet<Train>();
	
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="ligne")	
	private Set<CoutFonctionnement> CoutFonctionnementlist = new HashSet<CoutFonctionnement>();
	
	
	@ManyToOne(
			cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
	)
	@JoinColumn(name="reseau_idreseau")
	private Reseau reseau;
	
	public Set<Train> getTrainlist() {
		return trainlist;
	}

	public void setTrainlist(Set<Train> trainlist) {
		this.trainlist = trainlist;
	}

	public Set<CoutFonctionnement> getCoutFonctionnementlist() {
		return CoutFonctionnementlist;
	}

	public void setCoutFonctionnementlist(
			Set<CoutFonctionnement> coutFonctionnementlist) {
		CoutFonctionnementlist = coutFonctionnementlist;
	}
	

	
	
	
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

	public Integer getLongueur() {
		return longueur;
	}

	public void setLongueur(Integer longueur) {
		this.longueur = longueur;
	}

	public Integer getNombredestation() {
		return nombredestation;
	}

	public void setNombredestation(Integer nombredestation) {
		this.nombredestation = nombredestation;
	}

	public Integer getDureemoyennetrajet() {
		return dureemoyennetrajet;
	}

	public void setDureemoyennetrajet(Integer dureemoyennetrajet) {
		this.dureemoyennetrajet = dureemoyennetrajet;
	}

	public Integer getNombredetrain() {
		return nombredetrain;
	}

	public void setNombredetrain(Integer nombredetrain) {
		this.nombredetrain = nombredetrain;
	}

	public Integer getNombrepassagers() {
		return nombrepassagers;
	}

	public void setNombrepassagers(Integer nombrepassagers) {
		this.nombrepassagers = nombrepassagers;
	}

	public Double getPrixdeplace() {
		return prixdeplace;
	}

	public void setPrixdeplace(Double prixdeplace) {
		this.prixdeplace = prixdeplace;
	}

	

	
}
