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
 * <p>Java class for StationSubSection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StationSubSection">
 *   &lt;complexContent>
 *     &lt;extension base="{http://model.facade.pds}SubSection">
 *       &lt;sequence>
 *         &lt;element name="platformBack" type="{http://model.facade.pds}Platform"/>
 *         &lt;element name="platformGo" type="{http://model.facade.pds}Platform"/>
 *         &lt;element name="devices" type="{http://model.facade.pds}Device" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StationSubSection", propOrder = {
    "platformBack",
    "platformGo",
    "devices"
})
public class StationSubSection
    extends SubSection
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(required = true)
    protected Platform platformBack;
    @XmlElement(required = true)
    protected Platform platformGo;
    @XmlElement(required = true)
    protected List<Device> devices;

    /**
     * Gets the value of the platformBack property.
     * 
     * @return
     *     possible object is
     *     {@link Platform }
     *     
     */
    public Platform getPlatformBack() {
        return platformBack;
    }

    /**
     * Sets the value of the platformBack property.
     * 
     * @param value
     *     allowed object is
     *     {@link Platform }
     *     
     */
    public void setPlatformBack(Platform value) {
        this.platformBack = value;
    }

    public boolean isSetPlatformBack() {
        return (this.platformBack!= null);
    }

    /**
     * Gets the value of the platformGo property.
     * 
     * @return
     *     possible object is
     *     {@link Platform }
     *     
     */
    public Platform getPlatformGo() {
        return platformGo;
    }

    /**
     * Sets the value of the platformGo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Platform }
     *     
     */
    public void setPlatformGo(Platform value) {
        this.platformGo = value;
    }

    public boolean isSetPlatformGo() {
        return (this.platformGo!= null);
    }

    /**
     * Gets the value of the devices property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the devices property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDevices().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Device }
     * 
     * 
     */
    public List<Device> getDevices() {
        if (devices == null) {
            devices = new ArrayList<Device>();
        }
        return this.devices;
    }

    public boolean isSetDevices() {
        return ((this.devices!= null)&&(!this.devices.isEmpty()));
    }

    public void unsetDevices() {
        this.devices = null;
    }

}
