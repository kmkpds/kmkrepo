package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@Entity
@Table(name="bifrequentationligne")
public class FrequentationLigneDWH {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idfrequentationligne")
	private int idFrequentationLigne ;
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
	
	@Column(name="id_ligne")
	private int idligne;
	
	public FrequentationLigneDWH() {}
	
	public FrequentationLigneDWH(
			int idFrequentationLigne,
			Integer frequentation,
			Integer frequentationEtudiant,
			Integer frequentationSalarie,
			Integer frequentationRetraite,
			Integer frequentationAutre,
			String date,
			int mois,
			int annee ,
			int idligne){
		this.idFrequentationLigne = idFrequentationLigne;
		this.frequentation=frequentation;
		this.frequentationEtudiant=frequentationEtudiant;
		this.frequentationRetraite=frequentationRetraite;
		this.frequentationSalarie=frequentationSalarie;
		this.frequentationAutre=frequentationAutre;
		this.date=date;
		this.mois=mois;
		this.annee=annee;
		this.idligne=idligne;
		
	}

	public int getIdFrequentationLigne() {
		return idFrequentationLigne;
	}

	public void setIdFrequentationLigne(int idFrequentationLigne) {
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

	public int getLigne() {
		return idligne;
	}

	public void setBiLigne(int idligne) {
		this.idligne = idligne;
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
	public int getIdligne() {
		return idligne;
	}
	public void setIdligne(int idligne) {
		this.idligne = idligne;
	}
	
}

