package beans;

import java.util.List;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


	
	@Entity
	@Table(name="canton")
	public class Canton  {


		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="idcanton")
		private Integer idCanton ;
		@Column(name="distance")
		private double distance ;
		@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
		)
		@JoinColumn(name="station_station_idstation1") //@JoinColumn(name="station_has_station_station_idstation1")
		private Station station1; //ajouter by kate
		
		@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
		)
		@JoinColumn(name="station_station_idstation2")//@JoinColumn(name="station_has_station_station_idstation2") 
		private Station station2; //ajouter by kate
		

		public Integer getIdCanton() {
			return idCanton;
		}
		public void setIdCanton(Integer idCanton) {
			this.idCanton = idCanton;
		}
		public double getDistance() {
			return distance;
		}
		public void setDistance(double distance) {
			this.distance = distance;
		}
		public Station getStation1() {
			return station1;
		}
		public void setStation1(Station station1) {
			this.station1 = station1;
		}
		public Station getStation2() {
			return station2;
		}
		public void setStation2(Station station2) {
			this.station2 = station2;
		}

	
}
