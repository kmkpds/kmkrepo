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
@Table(name="parametrehoraire")
public class ParametreHoraire {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idparametrehoraire")
	private Integer idparametrehoraire ;
	@ManyToOne(
			cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
	)
	@JoinColumn(name="idLigne")
	private Ligne ligne;
	public Ligne getLigne() {
		return ligne;
	}
	public void setLigne(Ligne ligne) {
		this.ligne = ligne;
	}
	//@Column(name="idLigne")
	//private Integer idLigne;
	@Column(name="heurePremierTrainJO")
	private String heurePremierTrainJO;
	@Column(name="heurePremierTrainSamedi")
	private String heurePremierTrainSamedi;
	@Column(name="heurePremierTrainDimancheJF")
	private String heurePremierTrainDimancheJF;
	@Column(name="heureDernierTrainJO")
	private String heureDernierTrainJO;
	@Column(name="heureDernierTrainSamedi")
	private String heureDernierTrainSamedi;
	@Column(name="heureDernierTrainDimancheJF")
	private String heureDernierTrainDimancheJF;
	@Column(name="cadencementJO")
	private String cadencementJO;
	@Column(name="cadencementSamedi")
	private String cadencementSamedi;
	@Column(name="cadencementDimancheJF")
	private String cadencementDimancheJF;
	@Column(name="heuresPointeJO")
	private String heuresPointeJO;
	@Column(name="heuresPointeSamedi")
	private String heuresPointeSamedi;
	@Column(name="heuresPointeDimancheJF")
	private String heuresPointeDimancheJF;
	@Column(name="tempsStationnementJO")
	private String tempsStationnementJO;
	@Column(name="tempsStationnementSamedi")
	private String tempsStationnementSamedi;
	@Column(name="tempsStationnementDimancheJF")
	private String tempsStationnementDimancheJF;
	@Column(name="vitesseMoyenne")
	private Integer vitesseMoyenne;
	
	public ParametreHoraire() {

	}
	public Integer getIdparametrehoraire() {
		return idparametrehoraire;
	}
	public void setIdparametrehoraire(Integer idparametrehoraire) {
		this.idparametrehoraire = idparametrehoraire;
	}
/*	public Integer getIdLigne() {
		return idLigne;
	}
	public void setIdLigne(Integer idLigne) {
		this.idLigne = idLigne;
	}*/
	public String getHeurePremierTrainJO() {
		return heurePremierTrainJO;
	}
	public void setHeurePremierTrainJO(String heurePremierTrainJO) {
		this.heurePremierTrainJO = heurePremierTrainJO;
	}
	public String getHeurePremierTrainSamedi() {
		return heurePremierTrainSamedi;
	}
	public void setHeurePremierTrainSamedi(String heurePremierTrainSamedi) {
		this.heurePremierTrainSamedi = heurePremierTrainSamedi;
	}
	public String getHeurePremierTrainDimancheJF() {
		return heurePremierTrainDimancheJF;
	}
	public void setHeurePremierTrainDimancheJF(String heurePremierTrainDimancheJF) {
		this.heurePremierTrainDimancheJF = heurePremierTrainDimancheJF;
	}
	public String getHeureDernierTrainJO() {
		return heureDernierTrainJO;
	}
	public void setHeureDernierTrainJO(String heureDernierTrainJO) {
		this.heureDernierTrainJO = heureDernierTrainJO;
	}
	public String getHeureDernierTrainSamedi() {
		return heureDernierTrainSamedi;
	}
	public void setHeureDernierTrainSamedi(String heureDernierTrainSamedi) {
		this.heureDernierTrainSamedi = heureDernierTrainSamedi;
	}
	public String getHeureDernierTrainDimancheJF() {
		return heureDernierTrainDimancheJF;
	}
	public void setHeureDernierTrainDimancheJF(String heureDernierTrainDimancheJF) {
		this.heureDernierTrainDimancheJF = heureDernierTrainDimancheJF;
	}

	public String getCadencementJO() {
		return cadencementJO;
	}
	public void setCadencementJO(String cadencementJO) {
		this.cadencementJO = cadencementJO;
	}
	public String getCadencementSamedi() {
		return cadencementSamedi;
	}
	public void setCadencementSamedi(String cadencementSamedi) {
		this.cadencementSamedi = cadencementSamedi;
	}
	public String getCadencementDimancheJF() {
		return cadencementDimancheJF;
	}
	public void setCadencementDimancheJF(String cadencementDimancheJF2) {
		this.cadencementDimancheJF = cadencementDimancheJF2;
	}
	public String getHeuresPointeJO() {
		return heuresPointeJO;
	}
	public void setHeuresPointeJO(String heuresPointeJO) {
		this.heuresPointeJO = heuresPointeJO;
	}
	public String getHeuresPointeSamedi() {
		return heuresPointeSamedi;
	}
	public void setHeuresPointeSamedi(String heuresPointeSamedi) {
		this.heuresPointeSamedi = heuresPointeSamedi;
	}
	public String getHeuresPointeDimancheJF() {
		return heuresPointeDimancheJF;
	}
	public void setHeuresPointeDimancheJF(String heuresPointeDimancheJF) {
		this.heuresPointeDimancheJF = heuresPointeDimancheJF;
	}
	public String getTempsStationnementJO() {
		return tempsStationnementJO;
	}
	public void setTempsStationnementJO(String tempsStationnementJO) {
		this.tempsStationnementJO = tempsStationnementJO;
	}
	public String getTempsStationnementSamedi() {
		return tempsStationnementSamedi;
	}
	public void setTempsStationnementSamedi(String tempsStationnementSamedi) {
		this.tempsStationnementSamedi = tempsStationnementSamedi;
	}
	public String getTempsStationnementDimancheJF() {
		return tempsStationnementDimancheJF;
	}
	public void setTempsStationnementDimancheJF(String tempsStationnementDimancheJF) {
		this.tempsStationnementDimancheJF = tempsStationnementDimancheJF;
	}
	public Integer getVitesseMoyenne() {
		return vitesseMoyenne;
	}
	public void setVitesseMoyenne(Integer vitesseMoyenne) {
		this.vitesseMoyenne = vitesseMoyenne;
	}
	
}
