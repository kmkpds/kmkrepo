package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 
 * @author faycallemseffer
 *
 */
@XmlRootElement
@Entity
@Table(name="Procedure")
public class Procedure {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idProcedure")
	int idProcedure; 
	@Column(name="nomProcedure")
	String nomProcedure ;
	@Column(name="commentaireProcedure")
	String commentaireProcedure ;
	
	public Procedure(String nomProcedure , String commentaireProcedure){	
		this.nomProcedure=nomProcedure;
		this.commentaireProcedure=commentaireProcedure;
	}
	public int getIdProcedure() {
		return idProcedure;
	}
	public void setIdProcedure(int idProcedure) {
		this.idProcedure = idProcedure;
	}
	public String getNomProcedure() {
		return nomProcedure;
	}
	public void setNomProcedure(String nomProcedure) {
		this.nomProcedure = nomProcedure;
	}
	public String getCommentaireProcedure() {
		return commentaireProcedure;
	}
	public void setCommentaireProcedure(String commentaireProcedure) {
		this.commentaireProcedure = commentaireProcedure;
	}	
}