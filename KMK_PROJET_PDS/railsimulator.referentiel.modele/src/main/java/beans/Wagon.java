package beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="wagon")
public class Wagon {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idwagon")
    private Integer idWagon;
	@Column(name="temperatureWagon")
    private Integer temperatureWagon;
	@Column(name="frequentation")
    private Integer frequentation;
		
	@OneToMany(fetch=FetchType.EAGER,mappedBy="wagon")
    private Set<Porte> listePortes=new HashSet<Porte>();
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="wagon")
    private Set<Frein> listeFreins=new HashSet<Frein>();
	
    

	@ManyToOne(
			cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
	)
	@JoinColumn(name="train_idtrain")
	private Train train;
		
	

	//constructeur
    public Wagon() {
		
	}


	public Integer getIdWagon() {
        return idWagon;
    }

    public void setIdWagon(Integer value) {
        this.idWagon = value;
    }


    public Integer getTemperatureWagon() {
		return temperatureWagon;
	}


	public void setTemperatureWagon(Integer temperatureWagon) {
		this.temperatureWagon = temperatureWagon;
	}


	public Integer getFrequentation() {
		return frequentation;
	}


	public void setFrequentation(Integer frequentation) {
		this.frequentation = frequentation;
	}


	public Set<Porte> getListePortes() {
		return listePortes;
	}


	public void setListePortes(Set<Porte> listePortes) {
		this.listePortes = listePortes;
	}


	public Set<Frein> getListeFreins() {
		return listeFreins;
	}


	public void setListeFreins(Set<Frein> listeFreins) {
		this.listeFreins = listeFreins;
	}

	public Train getTrain() {
		return train;
	}


	public void setTrain(Train train) {
		this.train = train;
	}

}
