package beans;


import java.util.List;
import java.util.Set;
import java.util.TreeSet;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import javax.persistence.Table;

@Entity
@Table(name="procedureintervention")
public class Procedure {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idprocedure")
	private Integer idProcedure ;
	@Column(name="libelleprocedure")
	private String libelleProcedure;
	
	
	@OneToMany(mappedBy="procedureAction",fetch=FetchType.EAGER)
	private List<Action> listeAction;
	

	public Integer getIdProcedure() {
		return idProcedure;
	}

	public void setIdProcedure(Integer idProcedure) {
		this.idProcedure = idProcedure;
	}

	public String getLibelleProcedure() {
		return libelleProcedure;
	}

	public void setLibelleProcedure(String libelleProcedure) {
		this.libelleProcedure = libelleProcedure;
	}

	public List<Action> getListeAction() {
		return listeAction;
	}

	public void setListeAction(List<Action> listeAction) {
		this.listeAction = listeAction;
	}







	

}

