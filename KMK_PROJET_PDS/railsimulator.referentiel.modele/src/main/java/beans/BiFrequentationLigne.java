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



@Entity
@Table(name="bifrequentationligne")
public class BiFrequentationLigne {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idfrequentationligne")
	private Integer idFrequentationLigne ;
	@Column(name="frequentation")
	private Integer frequentation;
	@Column(name="frequentation_etudiant")
	private Integer frequentationEtudiant;
	@Column(name="frequentation_salarie")
	private Integer frequentationSalarie;
	@Column(name="frequentation_retraite")
	private Integer frequentationRetraite;
	@Column(name="frequentation_autre")
	private Integer frequentationAutre;
	@Column(name="date")
	private String date;
	@Column(name="mois")
	private int mois;
	@Column(name="annee")
	private int annee;
	
	@ManyToOne(
			cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
	)
	@JoinColumn(name="biligne_idbiligne")
	private BiLigne biLigne;
	
	public BiFrequentationLigne() {
		// TODO Auto-generated constructor stub
	}

	public Integer getIdFrequentationLigne() {
		return idFrequentationLigne;
	}

	public void setIdFrequentationLigne(Integer idFrequentationLigne) {
		this.idFrequentationLigne = idFrequentationLigne;
	}

	public Integer getFrequentation() {
		return frequentation;
	}

	public void setFrequentation(Integer frequuentation) {
		this.frequentation = frequuentation;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public BiLigne getBiLigne() {
		return biLigne;
	}

	public void setBiLigne(BiLigne biLigne) {
		this.biLigne = biLigne;
	}



	public Integer getFrequentationEtudiant() {
		return frequentationEtudiant;
	}

	public void setFrequentationEtudiant(Integer frequentationEtudiant) {
		this.frequentationEtudiant = frequentationEtudiant;
	}

	public Integer getFrequentationSalarie() {
		return frequentationSalarie;
	}

	public void setFrequentationSalarie(Integer frequentationSalarie) {
		this.frequentationSalarie = frequentationSalarie;
	}

	public Integer getFrequentationRetraite() {
		return frequentationRetraite;
	}

	public void setFrequentationRetraite(Integer frequentationRetraite) {
		this.frequentationRetraite = frequentationRetraite;
	}

	public Integer getFrequentationAutre() {
		return frequentationAutre;
	}

	public void setFrequentationAutre(Integer frequentationAutre) {
		this.frequentationAutre = frequentationAutre;
	}

	public int getMois() {
		return mois;
	}

	public void setMois(int mois) {
		this.mois = mois;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}





}
