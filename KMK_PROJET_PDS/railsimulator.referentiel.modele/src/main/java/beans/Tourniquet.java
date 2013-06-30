package beans;
import java.util.ArrayList;        
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import static javax.persistence.GenerationType.IDENTITY;  
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="tourniquet")
public class Tourniquet implements java.io.Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name="idtourniquet")
	private Integer idtourniquet;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.tourniquet", cascade=CascadeType.ALL)
	List<ClientPassTourniquet> clientPass = new ArrayList<ClientPassTourniquet>();

	
	@ManyToOne(
			cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
		)
		@JoinColumn(name="zone")
		private ZoneAbo zoneAbo;
	public Integer getIdtourniquet() {
		return idtourniquet;
	}
	public void setIdtourniquet(Integer idtourniquet) {
		this.idtourniquet = idtourniquet;
	}
	public ZoneAbo getZoneAbo() {
		return zoneAbo;
	}
	public void setZoneAbo(ZoneAbo zoneAbo) {
		this.zoneAbo = zoneAbo;
	}
	public List<ClientPassTourniquet> getClientPass() {
		return this.clientPass;
	}
	public void setClientPass(List<ClientPassTourniquet> clientPass) {
		this.clientPass = clientPass;
	}

}
