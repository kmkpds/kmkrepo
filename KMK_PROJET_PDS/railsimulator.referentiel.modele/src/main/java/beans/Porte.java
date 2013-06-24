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

@Entity
@Table(name="porte")
public class Porte {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idporte")
	private Integer idPorte;
	
	@Column(name="statut")
    private Integer statut;
    
	@ManyToOne(
			cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
	)
	@JoinColumn(name="wagon_idwagon")
	private Wagon wagon;

    public Porte() {
		
	}

	public Integer getIdPorte() {
		return idPorte;
	}

	public void setIdPorte(Integer idPorte) {
		this.idPorte = idPorte;
	}
	public Integer getStatut() {
        return statut;
    }

    public void setStatut(Integer value) {
        this.statut = value;
    }

	public Wagon getWagon() {
		return wagon;
	}

	public void setWagon(Wagon wagon) {
		this.wagon = wagon;
	}

}
