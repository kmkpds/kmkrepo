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
@Table(name="frein")
public class Frein {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idfrein")
	private Integer idFrein;
	
	@Column(name="temperature")
    private Float temperature;
	
    
	@ManyToOne(
			cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
	)
	@JoinColumn(name="wagon_idwagon")
	private Wagon wagon;


    public Frein() {
	}

	public Integer getIdFrein() {
		return idFrein;
	}

	public void setIdFrein(Integer idFrein) {
		this.idFrein = idFrein;
	}

	public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float value) {
        this.temperature = value;
    }

	public Wagon getWagon() {
		return wagon;
	}

	public void setWagon(Wagon wagon) {
		this.wagon = wagon;
	}


}
