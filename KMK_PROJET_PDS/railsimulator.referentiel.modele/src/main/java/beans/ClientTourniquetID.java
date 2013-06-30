package beans;

import javax.persistence.Embeddable;  
import javax.persistence.ManyToOne;

@Embeddable
public class ClientTourniquetID implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Client client;
    private Tourniquet tourniquet;

	@ManyToOne
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@ManyToOne
	public Tourniquet getTourniquet() {
		return tourniquet;
	}

	public void setTourniquet(Tourniquet tourniquet) {
		this.tourniquet = tourniquet;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientTourniquetID that = (ClientTourniquetID) o;

        if (client != null ? !client.equals(that.client) : that.client != null) return false;
        if (tourniquet != null ? !tourniquet.equals(that.tourniquet) : that.tourniquet != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (client != null ? client.hashCode() : 0);
        result = 31 * result + (tourniquet != null ? tourniquet.hashCode() : 0);
        return result;
    }
    
}