package beans;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class InstructionInfrastructure {

	int idInfra ;
	String libelleInfra ;
	public InstructionInfrastructure() {
		// TODO Auto-generated constructor stub
	}
	
	public InstructionInfrastructure(int idInfra , String libelleInfra){
		this.idInfra = idInfra;
		this.libelleInfra = libelleInfra;
	}

	public int getIdInfra() {
		return idInfra;
	}

	public void setIdInfra(int idInfra) {
		this.idInfra = idInfra;
	}

	public String getLibelleInfra() {
		return libelleInfra;
	}

	public void setLibelleInfra(String libelleInfra) {
		this.libelleInfra = libelleInfra;
	}
	
	
}
