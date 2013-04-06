package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

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
	@Column(name="commentaire")
	String commentaire ; 
	@Column(name="criticite")
	Integer criticite ; 
	@Column(name="train")
	String train; 
	@Column(name="ligne")
	String ligne;
	@Column(name="station")
	String station;
	@Column(name="wagon")
	String wagon;
	

	
	public AnnonceVoyageur() {
		super();
	}
	
	/**public AnnonceVoyageur(int idAnnonce ,String typeEvenement, String libelleAnnonce,String commentaire,
								int criticite, String train, String ligne, String station, String wagon){
		this.idAnnonce = idAnnonce;
		this.typeEvenement= typeEvenement;
		this.libelleAnnonce =libelleAnnonce ;
		this.commentaire=commentaire;
		this.criticite=criticite;
		this.train=train; 
		this.ligne = ligne;
		this.station = station; 
		this.wagon=wagon;
		
	}*/
	
	public AnnonceVoyageur(String typeEvenement, String libelleAnnonce,
			Integer criticite,String commentaire, String train,
			String ligne, String station, String wagon){

		this.typeEvenement= typeEvenement;
		this.libelleAnnonce =libelleAnnonce ;
		this.criticite = criticite;
		this.commentaire= commentaire;
		this.train=train; 
		this.ligne = ligne;
		this.station = station; 
		this.wagon=wagon;
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

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Integer getCriticite() {
		return criticite;
	}

	public void setCriticite(Integer criticite) {
		this.criticite = criticite;
	}

	public String getTrain() {
		return train;
	}

	public void setTrain(String train) {
		this.train = train;
	}

	public String getLigne() {
		return ligne;
	}

	public void setLigne(String ligne) {
		this.ligne = ligne;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getWagon() {
		return wagon;
	}

	public void setWagon(String wagon) {
		this.wagon = wagon;
	}
	
	
}
