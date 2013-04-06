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
@Table(name="Etape")
public class Etape {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idEtape")
	private Integer idEtape ;
	@Column(name="nomEtape")
	private String nomEtape;
	@Column(name="descriptionEtape")
	private String descriptionEtape;
	
	public Etape() {
		
	}
	public Etape(String nomEtape, String descriptionEtape){
		this.nomEtape=nomEtape;
		this.descriptionEtape=descriptionEtape;
	}
	public Integer getIdEtape() {
		return idEtape;
	}
	public void setIdEtape(Integer idEtape) {
		this.idEtape = idEtape;
	}
	public String getNomEtape() {
		return nomEtape;
	}
	public void setNomEtape(String nomEtape) {
		this.nomEtape = nomEtape;
	}
	public String getDescriptionEtape() {
		return descriptionEtape;
	}
	public void setDescriptionEtape(String descriptionEtape) {
		this.descriptionEtape = descriptionEtape;
	}
	
}
