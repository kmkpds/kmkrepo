//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.03.24 at 02:13:20 AM CET 
//


package modeleMessages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mess" type="{http://www.example.org/MockTrain}Event"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "mess"
})
@XmlRootElement(name = "envoyerEventToServiceMix")
public class EnvoyerEventToServiceMix {

    @XmlElement(required = true)
    protected Event mess;

    /**
     * Gets the value of the mess property.
     * 
     * @return
     *     possible object is
     *     {@link Event }
     *     
     */
    public Event getMess() {
        return mess;
    }

    /**
     * Sets the value of the mess property.
     * 
     * @param value
     *     allowed object is
     *     {@link Event }
     *     
     */
    public void setMess(Event value) {
        this.mess = value;
    }

}
