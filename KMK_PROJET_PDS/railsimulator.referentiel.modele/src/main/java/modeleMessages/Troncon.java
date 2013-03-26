//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.03.24 at 02:13:20 AM CET 
//


package modeleMessages;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Troncon complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Troncon">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="capteur" type="{http://www.example.org/MockTrain}Capteur" maxOccurs="unbounded"/>
 *         &lt;element name="StationDepart" type="{http://www.example.org/MockTrain}Station"/>
 *         &lt;element name="StationArrivee" type="{http://www.example.org/MockTrain}Station"/>
 *         &lt;element name="listeCanton" type="{http://www.example.org/MockTrain}Canton" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="idTroncon" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="taille" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="latitude" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="longitude" type="{http://www.w3.org/2001/XMLSchema}double" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Troncon", propOrder = {
    "capteur",
    "stationDepart",
    "stationArrivee",
    "listeCanton"
})
public class Troncon {

    @XmlElement(required = true)
    protected List<Capteur> capteur;
    @XmlElement(name = "StationDepart", required = true)
    protected Station stationDepart;
    @XmlElement(name = "StationArrivee", required = true)
    protected Station stationArrivee;
    @XmlElement(required = true)
    protected List<Canton> listeCanton;
    @XmlAttribute(name = "idTroncon")
    protected Integer idTroncon;
    @XmlAttribute(name = "taille")
    protected Float taille;
    @XmlAttribute(name = "latitude")
    protected Double latitude;
    @XmlAttribute(name = "longitude")
    protected Double longitude;

    /**
     * Gets the value of the capteur property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the capteur property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCapteur().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Capteur }
     * 
     * 
     */
    public List<Capteur> getCapteur() {
        if (capteur == null) {
            capteur = new ArrayList<Capteur>();
        }
        return this.capteur;
    }

    /**
     * Gets the value of the stationDepart property.
     * 
     * @return
     *     possible object is
     *     {@link Station }
     *     
     */
    public Station getStationDepart() {
        return stationDepart;
    }

    /**
     * Sets the value of the stationDepart property.
     * 
     * @param value
     *     allowed object is
     *     {@link Station }
     *     
     */
    public void setStationDepart(Station value) {
        this.stationDepart = value;
    }

    /**
     * Gets the value of the stationArrivee property.
     * 
     * @return
     *     possible object is
     *     {@link Station }
     *     
     */
    public Station getStationArrivee() {
        return stationArrivee;
    }

    /**
     * Sets the value of the stationArrivee property.
     * 
     * @param value
     *     allowed object is
     *     {@link Station }
     *     
     */
    public void setStationArrivee(Station value) {
        this.stationArrivee = value;
    }

    /**
     * Gets the value of the listeCanton property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listeCanton property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListeCanton().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Canton }
     * 
     * 
     */
    public List<Canton> getListeCanton() {
        if (listeCanton == null) {
            listeCanton = new ArrayList<Canton>();
        }
        return this.listeCanton;
    }

    /**
     * Gets the value of the idTroncon property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdTroncon() {
        return idTroncon;
    }

    /**
     * Sets the value of the idTroncon property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdTroncon(Integer value) {
        this.idTroncon = value;
    }

    /**
     * Gets the value of the taille property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getTaille() {
        return taille;
    }

    /**
     * Sets the value of the taille property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setTaille(Float value) {
        this.taille = value;
    }

    /**
     * Gets the value of the latitude property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Sets the value of the latitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setLatitude(Double value) {
        this.latitude = value;
    }

    /**
     * Gets the value of the longitude property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * Sets the value of the longitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setLongitude(Double value) {
        this.longitude = value;
    }

}
