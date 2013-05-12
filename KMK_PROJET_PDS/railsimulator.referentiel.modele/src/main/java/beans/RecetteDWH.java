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
@Table(name="recette")
public class RecetteDWH {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_Recette_DWH")
	private int idRecetteDWH ;
	@Column(name="idrecette")
	private int idRecette ;
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
	@Column(name="id_ligne")
	private int id_ligne;

	public RecetteDWH() {
	}
	public RecetteDWH(int idRecette, float ca, float cout, float coutFixe, float coutVariable, String date,
			int mois, int annee, int id_ligne){
		this.idRecette=idRecette;
		this.ca=ca;
		this.cout=cout;
		this.coutFixe=coutFixe;
		this.coutVariable=coutVariable;
		this.date=date;
		this.mois=mois;
		this.annee=annee;
		this.id_ligne=id_ligne;
	}
	public int getIdRecetteDWH() {
		return idRecetteDWH;
	}
	public void setIdRecetteDWH(int idRecetteDWH) {
		this.idRecetteDWH = idRecetteDWH;
	}
	public int getIdRecette() {
		return idRecette;
	}
	public void setIdRecette(int idRecette) {
		this.idRecette = idRecette;
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
	public int getId_ligne() {
		return id_ligne;
	}
	public void setId_ligne(int id_ligne) {
		this.id_ligne = id_ligne;
	}
	


}
