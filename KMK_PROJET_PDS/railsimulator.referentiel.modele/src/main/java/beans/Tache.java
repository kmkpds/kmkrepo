package beans;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="tache")

public class Tache {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idtache")
	private Integer idtache;
	@Column(name="libelletache")
	private String libelletache;
	
	
	@OneToMany(mappedBy="tache")
	private Set<EmpTache> listeEmpTache= new HashSet<EmpTache>();
	
	public Tache() {
		// TODO Auto-generated constructor stub
	}
	
	public Tache (String libelletache) {
		
		
		this.libelletache=libelletache;
				
	}

	public Integer getIdtache() {
		return idtache;
	}

	public void setIdtache(Integer idtache) {
		this.idtache = idtache;
	}

	public String getLibelletache() {
		return libelletache;
	}

	public void setLibelletache(String libelletache) {
		this.libelletache = libelletache;
	}



	public Set<EmpTache> getListeEmpTache() {
		return listeEmpTache;
	}

	public void setListeEmpTache(Set<EmpTache> listeEmpTache) {
		this.listeEmpTache = listeEmpTache;
	}
	
	

	
}
	
	
	
	
	
	