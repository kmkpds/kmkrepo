package beans;
import java.util.HashSet;  
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import beans.AbonnementMany;

@XmlRootElement
@Entity
@Table(name="type_abonnement")

public class TypeAbonnement {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	@Column(name="libelle")
	private String libelle;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="type")
	private Set<AbonnementMany> AbonnementMany = new HashSet<AbonnementMany>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
}
