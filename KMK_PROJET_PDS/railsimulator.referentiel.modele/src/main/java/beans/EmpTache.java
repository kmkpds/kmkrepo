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
	@Table(name="emptache")


	public class EmpTache {
		
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="idemptache")
		private Integer idemptache;
		@Column(name="idemp")
		private Integer idemp;
		@Column(name="idtache")
		private Integer idtache;
		@Column(name="date")
		private String date;
		@Column(name="heuredtache")
		private String heuredtache;
		@Column(name="duree")
		private String duree;

		@Column(name="commentaire")
		private String commentaire;
		
		@ManyToOne(
				cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
		)
		@JoinColumn(name="idemp" ,  insertable = false, updatable = false)	
		private  Employe employe;
		
		@ManyToOne(
				cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
		)
		@JoinColumn(name="idtache" ,  insertable = false, updatable = false)	
		private  Tache tache;
		
		public EmpTache(){
			
		}
		
		
		public EmpTache (Integer idemp, Integer idtache , String date, String heuredtache , String duree , String commentaire) {
			
			this.idemp=idemp;
			this.idtache=idtache;
			this.date=date;
			this.heuredtache=heuredtache;
			this.duree=duree;
			this.commentaire=commentaire; 
						
		}

		

		public String getHeuredtache() {
			return heuredtache;
		}

		public void setHeuredtache(String heuredtache) {
			this.heuredtache = heuredtache;
		}

		public Integer getIdemptache() {
			return idemptache;
		}

		public void setIdemptache(Integer idemptache) {
			this.idemptache = idemptache;
		}

		public Integer getIdemp() {
			return idemp;
		}

		public void setIdemp(Integer idemp) {
			this.idemp = idemp;
		}

		public Integer getIdtache() {
			return idtache;
		}

		public void setIdtache(Integer idtache) {
			this.idtache = idtache;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getCommentaire() {
			return commentaire;
		}

		public void setCommentaire(String commentaire) {
			this.commentaire = commentaire;
		}

		public Employe getEmploye() {
			return employe;
		}

		public void setEmploye(Employe employe) {
			this.employe = employe;
		}

		public Tache getTache() {
			return tache;
		}

		public void setTache(Tache tache) {
			this.tache = tache;
		}

		public String getDuree() {
			return duree;
		}

		public void setDuree(String duree) {
			this.duree = duree;
		}
		
		
		

}
