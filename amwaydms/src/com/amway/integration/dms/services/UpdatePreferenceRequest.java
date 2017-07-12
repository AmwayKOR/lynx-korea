
package com.amway.integration.dms.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updatePreferenceRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updatePreferenceRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{}baseWebServiceInput">
 *       &lt;sequence>
 *         &lt;element name="choiceListFlg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isTrueFlg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="prefList" type="{}preferenceList" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="preferenceCatagoryTypeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preferenceDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preferenceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preferenceNote" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updatePreferenceRequest", propOrder = {
    "choiceListFlg",
    "isTrueFlg",
    "prefList",
    "preferenceCatagoryTypeId",
    "preferenceDesc",
    "preferenceId",
    "preferenceNote",
    "userId"
})
public class UpdatePreferenceRequest
    extends BaseWebServiceInput
{

    protected String choiceListFlg;
    protected String isTrueFlg;
    @XmlElement(nillable = true)
    protected List<PreferenceList> prefList;
    protected String preferenceCatagoryTypeId;
    protected String preferenceDesc;
    protected String preferenceId;
    protected String preferenceNote;
    protected String userId;

    /**
     * Gets the value of the choiceListFlg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChoiceListFlg() {
        return choiceListFlg;
    }

    /**
     * Sets the value of the choiceListFlg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChoiceListFlg(String value) {
        this.choiceListFlg = value;
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
     * Gets the value of the prefList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the prefList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrefList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PreferenceList }
     * 
     * 
     */
    public List<PreferenceList> getPrefList() {
        if (prefList == null) {
            prefList = new ArrayList<PreferenceList>();
        }
        return this.prefList;
    }

    /**
     * Gets the value of the preferenceCatagoryTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreferenceCatagoryTypeId() {
        return preferenceCatagoryTypeId;
    }

    /**
     * Sets the value of the preferenceCatagoryTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreferenceCatagoryTypeId(String value) {
        this.preferenceCatagoryTypeId = value;
    }

    /**
     * Gets the value of the preferenceDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreferenceDesc() {
        return preferenceDesc;
    }

    /**
     * Sets the value of the preferenceDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreferenceDesc(String value) {
        this.preferenceDesc = value;
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

    /**
     * Gets the value of the userId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserId(String value) {
        this.userId = value;
    }

}
