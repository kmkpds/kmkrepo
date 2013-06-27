package beans.BI;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="typeabonnement")
public class TypeAbonnementBI {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idabonnement" )
	private Integer idTypeAboBi;
	
	@Column(name="nomabonnement")
	private String nomTypeAboBi;

	public Integer getIdTypeAboBi() {
		return idTypeAboBi;
	}

	public void setIdTypeAboBi(Integer idTypeAboBi) {
		this.idTypeAboBi = idTypeAboBi;
	}

	public String getNomTypeAboBi() {
		return nomTypeAboBi;
	}

	public void setNomTypeAboBi(String nomTypeAboBi) {
		this.nomTypeAboBi = nomTypeAboBi;
	}

}
