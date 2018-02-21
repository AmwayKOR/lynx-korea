
package com.amway.integration.dms.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for Preference complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Preference">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="preferenceTypeList" type="{http://tempuri.org}PreferenceType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="isTrueFlg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preferenceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preferenceNote" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Preference", propOrder = {
    "preferenceTypeList",
    "isTrueFlg",
    "preferenceId",
    "preferenceNote"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Preference {

    protected List<PreferenceType> preferenceTypeList;
    protected String isTrueFlg;
    protected String preferenceId;
    protected String preferenceNote;

    /**
     * Gets the value of the preferenceTypeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the preferenceTypeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPreferenceTypeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PreferenceType }
     * 
     * 
     */
    public List<PreferenceType> getPreferenceTypeList() {
        if (preferenceTypeList == null) {
            preferenceTypeList = new ArrayList<PreferenceType>();
        }
        return this.preferenceTypeList;
    }

    /**
     * Gets the value of the isTrueFlg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsTrueFlg() {
        return isTrueFlg;
    }

    /**
     * Sets the value of the isTrueFlg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsTrueFlg(String value) {
        this.isTrueFlg = value;
    }

    /**
     * Gets the value of the preferenceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreferenceId() {
        return preferenceId;
    }

    /**
     * Sets the value of the preferenceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreferenceId(String value) {
        this.preferenceId = value;
    }

    /**
     * Gets the value of the preferenceNote property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreferenceNote() {
        return preferenceNote;
    }

    /**
     * Sets the value of the preferenceNote property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreferenceNote(String value) {
        this.preferenceNote = value;
    }

}
