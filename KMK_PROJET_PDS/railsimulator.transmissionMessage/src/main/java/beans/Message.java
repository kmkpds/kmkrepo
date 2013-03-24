package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author faycallemseffer
 */
@XmlRootElement
@Entity
@Table(name="Message")
public class Message {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idMessage")
	int idMessage; 
	@Column(name="criticite")
	int criticité ;
	@Column(name="libelle")
	String libelleMessage ;
	
	public Message() {
		super();
	}

	public int getIdMessage() {
		return idMessage;
	}
	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}
	public int getCriticité() {
		return criticité;
	}
	public void setCriticité(int criticité) {
		this.criticité = criticité;
	}
	public String getLibelle() {
		return libelleMessage;
	}
	public void setLibelle(String libelle) {
		this.libelleMessage = libelle;
	}

	
	
	
}
