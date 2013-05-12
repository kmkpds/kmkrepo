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
@Table(name="coutfonctionement")
public class CoutFonctionnement {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idcoutfonct")
	private Integer idcoutfonct ;
	@Column(name="tarificationdedeplacement")
	private Double tarificationdedeplacement ;
	@Column(name="entretienmaterielroulant")
	private Integer entretienmaterielroulant ;
	@Column(name="consommationenergie")
	private Double consommationenergie ;
	
	
	
	@ManyToOne(
			cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
	)
	@JoinColumn(name="idligne")
	private Ligne ligne;



	public Integer getIdcoutfonct() {
		return idcoutfonct;
	}



	public void setIdcoutfonct(Integer idcoutfonct) {
		this.idcoutfonct = idcoutfonct;
	}



	public Double getTarificationdedeplacement() {
		return tarificationdedeplacement;
	}



	public void setTarificationdedeplacement(Double tarificationdedeplacement) {
		this.tarificationdedeplacement = tarificationdedeplacement;
	}



	public Integer getEntretienmaterielroulant() {
		return entretienmaterielroulant;
	}



	public void setEntretienmaterielroulant(Integer entretienmaterielroulant) {
		this.entretienmaterielroulant = entretienmaterielroulant;
	}



	public Double getConsommationenergie() {
		return consommationenergie;
	}



	public void setConsommationenergie(Double consommationenergie) {
		this.consommationenergie = consommationenergie;
	}



	public Ligne getLigne() {
		return ligne;
	}



	public void setLigne(Ligne ligne) {
		this.ligne = ligne;
	}

	
	
}
