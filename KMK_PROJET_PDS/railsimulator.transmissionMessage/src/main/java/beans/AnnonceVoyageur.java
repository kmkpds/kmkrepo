package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 
 * @author faycallemseffer
 *
 */
@XmlRootElement
@Entity
@Table(name="AnnonceVoyageur")
public class AnnonceVoyageur {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idAnnonce")
	int idAnnonce;
	@Column(name="libelleAnnonce")
	String libelleAnnonce ;
	@Column(name="typeEvenement")
	String typeEvenement ; 
	public AnnonceVoyageur() {
		super();
	}
	
	public AnnonceVoyageur(int idAnnonce ,String typeEvenement, String libelleAnnonce){
		this.idAnnonce = idAnnonce;
		this.typeEvenement= typeEvenement;
		this.libelleAnnonce =libelleAnnonce ;
	}
	
	public AnnonceVoyageur(String typeEvenement, String libelleAnnonce){

		this.typeEvenement= typeEvenement;
		this.libelleAnnonce =libelleAnnonce ;
	}
		

	public int getIdAnnonce() {
		return idAnnonce;
	}

	public void setIdAnnonce(int idAnnonce) {
		this.idAnnonce = idAnnonce;
	}

	public String getLibelleAnnonce() {
		return libelleAnnonce;
	}

	public void setLibelleAnnonce(String libelleAnnonce) {
		this.libelleAnnonce = libelleAnnonce;
	}

	public String getTypeEvenement() {
		return typeEvenement;
	}

	public void setTypeEvenement(String typeEvenement) {
		this.typeEvenement = typeEvenement;
	}

	
}
