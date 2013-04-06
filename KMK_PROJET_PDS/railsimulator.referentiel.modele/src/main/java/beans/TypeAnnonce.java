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
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@Entity
@Table(name="typeannonce")
public class TypeAnnonce {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idtypeannonce")
	int idtypeannonce; 
	@Column(name="libelletypeannonce")
	String libelletypeannonce ;
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name="annoncevoyageur_idAnnonce")
	
	public int getIdtypeannonce() {
		return idtypeannonce;
	}
	public void setIdtypeannonce(int idtypeannonce) {
		this.idtypeannonce = idtypeannonce;
	}
	public String getLibelletypeannonce() {
		return libelletypeannonce;
	}
	public void setLibelletypeannonce(String libelletypeannonce) {
		this.libelletypeannonce = libelletypeannonce;
	}

	

}
