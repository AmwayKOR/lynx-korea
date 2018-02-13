
package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for ActionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ActionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="OrderCreated"/>
 *     &lt;enumeration value="OrderCancelled"/>
 *     &lt;enumeration value="OrderReturned"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ActionType")
@XmlEnum
@JsonIgnoreProperties(ignoreUnknown = true)
public enum ActionType {

    @XmlEnumValue("OrderCreated")
    OrderCreated("OrderCreated"),
    @XmlEnumValue("OrderCancelled")
    OrderCancelled("OrderCancelled"),
    @XmlEnumValue("OrderReturned")
    OrderReturned("OrderReturned");
    private final String value;

    ActionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ActionType fromValue(String v) {
        for (ActionType c: ActionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
