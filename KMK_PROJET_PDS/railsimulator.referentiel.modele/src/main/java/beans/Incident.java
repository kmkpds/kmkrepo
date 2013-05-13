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
import javax.persistence.OneToOne;


import javax.persistence.Table;


@Entity
@Table(name="incident")
public class Incident {
	
	@Id
	@Column(name="idincident")
	private Integer idIncident;
	@Column(name="description")
	private String description;
	@Column(name="datedebut")
	private String dateDebut;
	@Column(name="mois")
	private int mois;
	@Column(name="annee")
	private int annee;
	@Column(name="datefin")
	private String dateFin;
	@Column(name="criticite")
	private String criticite;
	
	
	@ManyToOne(
			cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
	)
	@JoinColumn(name="type_idtype")
	private  TypeIncident typeIncident;
	
	@OneToOne
	@JoinColumn(
	        name="procedureintervention_idprocedure ",
	        referencedColumnName="idprocedure"
	)
	private Procedure procedure;
	@ManyToOne(
			cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
	)
	@JoinColumn(name="biligne_idbiligne")
	private BiLigne biLigne;

	public Integer getIdIncident() {
		return idIncident;
	}

	public void setIdIncident(Integer idIncident) {
		this.idIncident = idIncident;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public String getCriticite() {
		return criticite;
	}

	public void setCriticite(String criticite) {
		this.criticite = criticite;
	}

	public TypeIncident getTypeIncident() {
		return typeIncident;
	}

	public void setTypeIncident(TypeIncident typeIncident) {
		this.typeIncident = typeIncident;
	}

	public Procedure getProcedure() {
		return procedure;
	}

	public void setProcedure(Procedure procedure) {
		this.procedure = procedure;
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