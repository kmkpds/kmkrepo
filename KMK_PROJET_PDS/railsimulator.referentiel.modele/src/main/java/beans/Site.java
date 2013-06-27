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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="site")
public class Site {

		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="idsite")
		private Integer idsite;
	
		@Column(name="libellesite")
		private String libellesite;
		@Column(name="adresse")
		private String adresse;
		@Column(name="secteur")
		private String secteur;
		@Column(name="nbemp")
		private Integer nbemp;
		
		
		
		
		@OneToMany(fetch=FetchType.EAGER, mappedBy="site")
		private List<Employe> listeEmploye;
		
	
			
		public List<Employe> getListeEmploye() {
			return listeEmploye;
		}
		public void setListeEmploye(List<Employe> listeEmploye) {
			this.listeEmploye = listeEmploye;
		}
		public Integer getIdsite() {
			return idsite;
		}
		public void setIdsite(Integer idsite) {
			this.idsite = idsite;
		}
		
		public String getLibellesite() {
			return libellesite;
		}
		public void setLibellesite(String libellesite) {
			this.libellesite = libellesite;
		}
		public String getAdresse() {
			return adresse;
		}
		public void setAdresse(String adresse) {
			this.adresse = adresse;
		}
		public String getSecteur() {
			return secteur;
		}
		public void setSecteur(String secteur) {
			this.secteur = secteur;
		}
		public Integer getNbemp() {
			return nbemp;
		}
		public void setNbemp(Integer nbemp) {
			this.nbemp = nbemp;
		}
		
		
		
}
