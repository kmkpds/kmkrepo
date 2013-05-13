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
@Table(name="bisatisfaction")
public class BiSatisfaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idsatisfaction")
	private Integer idSatisfaction ;
	@Column(name="satisfactionetudiant")
	private float satisfactionEtudiant;
	@Column(name="satisfactionsalarie")
	private float satisfactionSalarie;
	@Column(name="satisfactionretraite")
	private float satisfactionRetraite;

	
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


	public Integer getIdSatisfaction() {
		return idSatisfaction;
	}


	public void setIdSatisfaction(Integer idSatisfaction) {
		this.idSatisfaction = idSatisfaction;
	}


	public float getSatisfactionEtudiant() {
		return satisfactionEtudiant;
	}


	public void setSatisfactionEtudiant(float satisfactionEtudiant) {
		this.satisfactionEtudiant = satisfactionEtudiant;
	}


	public float getSatisfactionSalarie() {
		return satisfactionSalarie;
	}


	public void setSatisfactionSalarie(float satisfactionSalarie) {
		this.satisfactionSalarie = satisfactionSalarie;
	}


	public float getSatisfactionRetraite() {
		return satisfactionRetraite;
	}


	public void setSatisfactionRetraite(float satisfactionRetraite) {
		this.satisfactionRetraite = satisfactionRetraite;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
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


	public BiLigne getBiLigne() {
		return biLigne;
	}


	public void setBiLigne(BiLigne biLigne) {
		this.biLigne = biLigne;
	}

}
