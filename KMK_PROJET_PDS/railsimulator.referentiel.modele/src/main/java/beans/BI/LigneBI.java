package beans.BI;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="biligne")
public class LigneBI {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idbiligne" )
	private Integer idLigneBi;
	
	@Column(name="nombiligne")
	private String nomLigneBi;

	public Integer getIdLigneBi() {
		return idLigneBi;
	}

	public void setIdLigneBi(Integer idLigneBi) {
		this.idLigneBi = idLigneBi;
	}

	public String getNomLigneBi() {
		return nomLigneBi;
	}

	public void setNomLigneBi(String nomLigneBi) {
		this.nomLigneBi = nomLigneBi;
	}

}
