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
@Table(name="incident")
public class IncidentDWH {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_Incident_DWH")
	private int id_incident_dwh;
	@Column(name="idincident")
	private int idIncident;
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
	@Column(name="id_procedureintervention")
	private int id_procedureintervention;
	@Column(name="id_type")
	private int id_type;
	
	public IncidentDWH() {}
	public IncidentDWH(int idIncident,String description, String dateDebut, int mois, int annee,
			String dateFin, String criticite, int id_procedureintervention, int id_type){
		this.idIncident=idIncident;
		this.description=description;
		this.dateDebut=dateDebut;
		this.mois=mois;
		this.annee=annee;
		this.dateFin=dateFin;
		this.criticite=criticite;
		this.id_procedureintervention=id_procedureintervention;
		this.id_type=id_type;
	}
	
	public int getId_incident_dwh() {
		return id_incident_dwh;
	}
	public void setId_incident_dwh(int id_incident_dwh) {
		this.id_incident_dwh = id_incident_dwh;
	}
	public int getIdIncident() {
		return idIncident;
	}
	public void setIdIncident(int idIncident) {
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
	public int getId_procedureintervention() {
		return id_procedureintervention;
	}
	public void setId_procedureintervention(int id_procedureintervention) {
		this.id_procedureintervention = id_procedureintervention;
	}
	public int getId_type() {
		return id_type;
	}
	public void setId_type(int id_type) {
		this.id_type = id_type;
	}
		
}
