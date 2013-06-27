package beans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="posteparsite")
public class PosteParSite {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	@Column(name="typeposte")
	private String typeposte;
	@Column(name="nbminemp")
	private Integer nbminemp;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTypeposte() {
		return typeposte;
	}
	public void setTypeposte(String typeposte) {
		this.typeposte = typeposte;
	}
	public Integer getNbminemp() {
		return nbminemp;
	}
	public void setNbminemp(Integer nbminemp) {
		this.nbminemp = nbminemp;
	}
	
	
	

}
