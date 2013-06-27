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
@Table(name="facthoraire")

public class FactHoraire {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	@Column(name="idemp")
	private Integer idemp;
	@Column(name="date")
	private String date;
	@Column(name="heured")
	private String heured;
	@Column(name="heuref")
	private String heuref;
	
	@ManyToOne(
		//	cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
	)
	@JoinColumn(name="idemp", insertable = false, updatable = false)
	private  Employe employe;
	
	public FactHoraire() {
		// TODO Auto-generated constructor stub
	}
	public FactHoraire(Integer idemp, String date, String heured, String heuref) {
		this.idemp=idemp; 
		this.date=date;
		this.heured=heured;
		this.heuref=heuref;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdemp() {
		return idemp;
	}

	public void setIdemp(Integer idemp) {
		this.idemp = idemp;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHeured() {
		return heured;
	}

	public void setHeured(String heured) {
		this.heured = heured;
	}

	public String getHeuref() {
		return heuref;
	}

	public void setHeuref(String heuref) {
		this.heuref = heuref;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}
	
	
	

}
