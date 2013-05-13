package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="biobjectif")
public class BiObjectif {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idobjectif")
	private Integer idObjectif ;
	@Column(name="annee")
	private int annee;
	@Column(name="objetudiant")
	private int objEtudiant;
	@Column(name="objsalarie")
	private int objSalarie;
	@Column(name="objretraite")
	private int objRetraite;
	@Column(name="aboetudiant")
	private int aboEtudiant;
	@Column(name="abosalarie")
	private int aboSalarie;
	@Column(name="aboretraite")
	private int aboRetraite;
	@Column(name="objca")
	private int objCa;
	@Column(name="ca")
	private int ca;
	public Integer getIdObjectif() {
		return idObjectif;
	}
	public void setIdObjectif(Integer idObjectif) {
		this.idObjectif = idObjectif;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public int getObjEtudiant() {
		return objEtudiant;
	}
	public void setObjEtudiant(int objEtudiant) {
		this.objEtudiant = objEtudiant;
	}
	public int getObjSalarie() {
		return objSalarie;
	}
	public void setObjSalarie(int objSalarie) {
		this.objSalarie = objSalarie;
	}
	public int getObjRetraite() {
		return objRetraite;
	}
	public void setObjRetraite(int objRetraite) {
		this.objRetraite = objRetraite;
	}
	public int getAboEtudiant() {
		return aboEtudiant;
	}
	public void setAboEtudiant(int aboEtudiant) {
		this.aboEtudiant = aboEtudiant;
	}
	public int getAboSalarie() {
		return aboSalarie;
	}
	public void setAboSalarie(int aboSalarie) {
		this.aboSalarie = aboSalarie;
	}
	public int getAboRetraite() {
		return aboRetraite;
	}
	public void setAboRetraite(int aboRetraite) {
		this.aboRetraite = aboRetraite;
	}
	public int getObjCa() {
		return objCa;
	}
	public void setObjCa(int objCa) {
		this.objCa = objCa;
	}
	public int getCa() {
		return ca;
	}
	public void setCa(int ca) {
		this.ca = ca;
	}
	
	
}
