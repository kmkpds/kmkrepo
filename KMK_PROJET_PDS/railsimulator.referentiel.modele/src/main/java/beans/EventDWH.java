package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="event")
public class EventDWH {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_Event_DWH")
	private int id_Event_dwh;
	@Column(name="id")
	private int idEvent;
	@Column(name="date")
	private String date;
	@Column(name="criticite")
	private String criticite;
	@Column(name="type_idtype")
	private int type_idtype;
	
	public EventDWH() {
		// TODO Auto-generated constructor stub
	}
	
	public EventDWH(int idEvent, String date, String criticite , int type_idtype){
		this.idEvent=idEvent ;
		this.date=date;
		this.criticite=criticite;
		this.type_idtype=type_idtype;
	}
	
	public int getId_Event_dwh() {
		return id_Event_dwh;
	}
	public void setId_Event_dwh(int id_Event_dwh) {
		this.id_Event_dwh = id_Event_dwh;
	}
	public int getIdEvent() {
		return idEvent;
	}
	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCriticite() {
		return criticite;
	}
	public void setCriticite(String criticite) {
		this.criticite = criticite;
	}
	public int getType_idtype() {
		return type_idtype;
	}
	public void setType_idtype(int type_idtype) {
		this.type_idtype = type_idtype;
	}

	
	
}
