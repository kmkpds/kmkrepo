package beans.BI;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.sourceforge.jtds.jdbc.DateTime;

@Entity
@Table(name="frequentation")
public class FrequentationBI {
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idfrequentation" )
	private Integer idFreqBi;
	
	@Column(name="datefrequentation")
	private DateTime dateFreq;
	
	@ManyToOne(
			cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
	)
	@JoinColumn(name="stationfrequentation")
	private StationBI stationFreq;
	
	@ManyToOne(
			cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
	)
	@JoinColumn(name="lignefrequentation")
	private LigneBI ligneFreq;
	
	@ManyToOne(
			cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
	)
	@JoinColumn(name="abonnementfrequentation")
	private TypeAbonnementBI typeAboFreq;
	
	public FrequentationBI(DateTime dateFreq, StationBI stationFreq,
			LigneBI ligneFreq, TypeAbonnementBI typeAboFreq) {
		super();
		this.dateFreq = dateFreq;
		this.stationFreq = stationFreq;
		this.ligneFreq = ligneFreq;
		this.typeAboFreq = typeAboFreq;
	}

}
