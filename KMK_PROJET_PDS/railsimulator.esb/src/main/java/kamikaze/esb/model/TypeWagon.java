//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.03.21 at 11:09:46 PM CET 
//


package kamikaze.esb.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TypeWagon complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TypeWagon">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="idTypeWagon" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="moteur" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TypeWagon")
public class TypeWagon {

    @XmlAttribute
    protected Integer idTypeWagon;
    @XmlAttribute
    protected Boolean moteur;

    /**
     * Gets the value of the idTypeWagon property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdTypeWagon() {
        return idTypeWagon;
    }

    /**
     * Sets the value of the idTypeWagon property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdTypeWagon(Integer value) {
        this.idTypeWagon = value;
    }

    /**
     * Gets the value of the moteur property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMoteur() {
        return moteur;
    }

    /**
     * Sets the value of the moteur property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMoteur(Boolean value) {
        this.moteur = value;
    }

}
