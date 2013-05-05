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
@Table(name="birecette")
public class BiRecette {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idrecette")
	private Integer idBiLigne ;
	@Column(name="ca")
	private float ca;
	@Column(name="cout")
	private float cout;
	@Column(name="coutfixe")
	private float coutFixe;
	@Column(name="coutvariable")
	private float coutVariable;
	@Column(name="date")
	private String date;
	@Column(name="mois")
	private int mois;
	@Column(name="annee")
	private int annee;

	
	@ManyToOne(
			cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
	)
	@JoinColumn(name="biligne_idligne")
	private BiLigne biLigne;


	public Integer getIdBiLigne() {
		return idBiLigne;
	}


	public void setIdBiLigne(Integer idBiLigne) {
		this.idBiLigne = idBiLigne;
	}


	public float getCa() {
		return ca;
	}


	public void setCa(float ca) {
		this.ca = ca;
	}


	public float getCout() {
		return cout;
	}


	public void setCout(float cout) {
		this.cout = cout;
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


	public float getCoutFixe() {
		return coutFixe;
	}


	public void setCoutFixe(float coutFixe) {
		this.coutFixe = coutFixe;
	}


	public float getCoutVariable() {
		return coutVariable;
	}


	public void setCoutVariable(float coutVariable) {
		this.coutVariable = coutVariable;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}
}
