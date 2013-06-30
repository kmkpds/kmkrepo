package beans;

import java.sql.Timestamp;  
import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;  
import beans.ClientTourniquetID;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@Entity
@Table(name = "client_pass_tourniquet", catalog = "railsimulator")
@AssociationOverrides({
		@AssociationOverride(name = "pk.client", joinColumns = @JoinColumn(name = "idclient")),
		@AssociationOverride(name = "pk.tourniquet", joinColumns = @JoinColumn(name = "idtourniquet")) })
public class ClientPassTourniquet implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ClientTourniquetID pk = new ClientTourniquetID();
	private Date createdDate;
	private Timestamp created;
	private String validation;
	private int idtransaction;
	

	public ClientPassTourniquet() {
	}

	@EmbeddedId
	public ClientTourniquetID getPk() {
		return pk;
	}

	public void setPk(ClientTourniquetID pk) {
		this.pk = pk;
	}

	@Transient
	public Client getClient() {
		return getPk().getClient();
	}

	public void setClient(Client client) {
		getPk().setClient(client);
	}

	@Transient
	public Tourniquet getTourniquet() {
		return getPk().getTourniquet();
	}

	public void setTourniquet(Tourniquet tourniquet) {
		getPk().setTourniquet(tourniquet);
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", nullable = false)
	public Date getCreated() {
		return createdDate;
	}

	public void setCreated(Date created) {
		this.createdDate = created;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ClientPassTourniquet that = (ClientPassTourniquet) o;

		if (getPk() != null ? !getPk().equals(that.getPk())
				: that.getPk() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}
	
	@Column(name = "valdation", nullable = false)
	public String getValidation() {
		return this.validation;
	}

	public void setValidation(String validation) {
		this.validation = validation;
	}

	@Column(name="idtransaction")
	public int getIdtransaction() {
		return idtransaction;
	}

	public void setIdtransaction(int idtransaction) {
		this.idtransaction = idtransaction;
	}
}