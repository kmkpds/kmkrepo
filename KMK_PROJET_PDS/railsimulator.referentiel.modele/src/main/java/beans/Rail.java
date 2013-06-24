package beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.OneToMany;

import java.util.HashSet;
import java.util.List;
import java.util.Set;




@Entity
@Table(name="rail")
public class Rail  {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idrail")
	private Integer idRail;
	@Column(name="direction")
	private String direction;

	@OneToMany(fetch = FetchType.EAGER,mappedBy="rail")
	private Set<Train> trainlist = new HashSet<Train>();

	

	public Rail() {
		super();
	}
	
	
	public Integer getIdRail() {
		return idRail;
	}

	public void setIdRail(Integer idRail) {
		this.idRail = idRail;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Set<Train> getTrainlist() {
		return trainlist;
	}

	public void setTrainlist(Set<Train> trainlist) {
		this.trainlist = trainlist;
	}


	
}
