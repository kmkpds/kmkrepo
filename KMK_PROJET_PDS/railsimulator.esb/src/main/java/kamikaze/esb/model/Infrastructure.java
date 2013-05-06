//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.04.14 at 03:12:36 AM CEST 
//


package kamikaze.esb.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Infrastructure complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Infrastructure">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="lines" type="{http://model.facade.pds}Line" maxOccurs="unbounded"/>
 *         &lt;element name="stations" type="{http://model.facade.pds}Station" maxOccurs="unbounded"/>
 *         &lt;element name="trains" type="{http://model.facade.pds}Train" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Infrastructure", propOrder = {
    "lines",
    "stations",
    "trains"
})
public class Infrastructure
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(required = true)
    protected List<Line> lines;
    @XmlElement(required = true)
    protected List<Station> stations;
    @XmlElement(required = true)
    protected List<Train> trains;

    /**
     * Gets the value of the lines property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lines property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLines().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Line }
     * 
     * 
     */
    public List<Line> getLines() {
        if (lines == null) {
            lines = new ArrayList<Line>();
        }
        return this.lines;
    }

    public boolean isSetLines() {
        return ((this.lines!= null)&&(!this.lines.isEmpty()));
    }

    public void unsetLines() {
        this.lines = null;
    }

    /**
     * Gets the value of the stations property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stations property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStations().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Station }
     * 
     * 
     */
    public List<Station> getStations() {
        if (stations == null) {
            stations = new ArrayList<Station>();
        }
        return this.stations;
    }

    public boolean isSetStations() {
        return ((this.stations!= null)&&(!this.stations.isEmpty()));
    }

    public void unsetStations() {
        this.stations = null;
    }

    /**
     * Gets the value of the trains property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trains property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrains().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Train }
     * 
     * 
     */
    public List<Train> getTrains() {
        if (trains == null) {
            trains = new ArrayList<Train>();
        }
        return this.trains;
    }

    public boolean isSetTrains() {
        return ((this.trains!= null)&&(!this.trains.isEmpty()));
    }

    public void unsetTrains() {
        this.trains = null;
    }

}