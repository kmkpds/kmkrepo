package beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
		@Column(name="nomcanton")
		private String nomCanton;
		@Column(name="commentaire")
		private String commentaireCanton;
		
		@ManyToOne(
				cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
		)
		@JoinColumn(name="ligne_idligne")
		private Ligne ligne;
		
		@ManyToOne(
				cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
		)
		@JoinColumn(name="station_idstation")
		private Station station;

		
		
		
		public Integer getIdCanton() {
			return idCanton;
		}

		public void setIdCanton(Integer idCanton) {
			this.idCanton = idCanton;
		}

		public String getNomCanton() {
			return nomCanton;
		}

		public void setNomCanton(String nomCanton) {
			this.nomCanton = nomCanton;
		}

		public String getCommentaireCanton() {
			return commentaireCanton;
		}

		public void setCommentaireCanton(String commentaireCanton) {
			this.commentaireCanton = commentaireCanton;
		}

		public Ligne getLigne() {
			return ligne;
		}

		public void setLigne(Ligne ligne) {
			this.ligne = ligne;
		}

		public Station getStation() {
			return station;
		}

		public void setStation(Station station) {
			this.station = station;
		}
		
        
		
		

	
}
