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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Train complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Train">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cars" type="{http://model.facade.pds}Car" maxOccurs="4" minOccurs="2"/>
 *         &lt;element name="sensors" type="{http://model.facade.pds}Sensor" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}long" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Train", propOrder = {
    "cars",
    "sensors"
})
public class Train
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(required = true)
    protected List<Car> cars;
    @XmlElement(required = true)
    protected List<Sensor> sensors;
    @XmlAttribute
    protected Long id;

    /**
     * Gets the value of the cars property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cars property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCars().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Car }
     * 
     * 
     */
    public List<Car> getCars() {
        if (cars == null) {
            cars = new ArrayList<Car>();
        }
        return this.cars;
    }

    public boolean isSetCars() {
        return ((this.cars!= null)&&(!this.cars.isEmpty()));
    }

    public void unsetCars() {
        this.cars = null;
    }

    /**
     * Gets the value of the sensors property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sensors property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSensors().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Sensor }
     * 
     * 
     */
    public List<Sensor> getSensors() {
        if (sensors == null) {
            sensors = new ArrayList<Sensor>();
        }
        return this.sensors;
    }

    public boolean isSetSensors() {
        return ((this.sensors!= null)&&(!this.sensors.isEmpty()));
    }

    public void unsetSensors() {
        this.sensors = null;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(long value) {
        this.id = value;
    }

    public boolean isSetId() {
        return (this.id!= null);
    }

    public void unsetId() {
        this.id = null;
    }

}
