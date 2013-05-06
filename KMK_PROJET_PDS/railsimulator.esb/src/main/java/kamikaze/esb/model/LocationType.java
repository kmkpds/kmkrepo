//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.04.14 at 03:12:36 AM CEST 
//


package kamikaze.esb.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for locationType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="locationType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DOOR"/>
 *     &lt;enumeration value="PLATFORM"/>
 *     &lt;enumeration value="STATION_SUB_SECTION"/>
 *     &lt;enumeration value="SUBSECTION"/>
 *     &lt;enumeration value="STATION"/>
 *     &lt;enumeration value="CAR"/>
 *     &lt;enumeration value="TRAIN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "locationType")
@XmlEnum
public enum LocationType {

    DOOR,
    PLATFORM,
    STATION_SUB_SECTION,
    SUBSECTION,
    STATION,
    CAR,
    TRAIN;

    public String value() {
        return name();
    }

    public static LocationType fromValue(String v) {
        return valueOf(v);
    }

}