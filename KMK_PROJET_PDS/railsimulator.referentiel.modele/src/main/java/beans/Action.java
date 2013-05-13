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
@Table(name="actionintervention")
public class Action {
	
	
	@Id
	@Column(name="idactionintervention")
	private Integer idActionIntervention;
	@Column(name="libelleactionintervention")
	private String libelleActionIntervention;
	@Column(name="datedebut")
	private String dateDebut;
	@Column(name="mois")
	private int mois;
	@Column(name="annee")
	private int annee;
	@Column(name="datefin")
	private String dateFin;
	@Column(name="statut")
	private String statut;
	
	@ManyToOne(
			cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
	)
	@JoinColumn(name="procedureintervention_idprocedure")
	private  Procedure procedureAction;

	public Integer getIdActionIntervention() {
		return idActionIntervention;
	}

	public void setIdActionIntervention(Integer idActionIntervention) {
		this.idActionIntervention = idActionIntervention;
	}

	public String getLibelleActionIntervention() {
		return libelleActionIntervention;
	}

	public void setLibelleActionIntervention(String libelleActionIntervention) {
		this.libelleActionIntervention = libelleActionIntervention;
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

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public Procedure getProcedureAction() {
		return procedureAction;
	}

	public void setProcedureAction(Procedure procedureAction) {
		this.procedureAction = procedureAction;
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