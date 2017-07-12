
package com.amway.integration.dms.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for customerRegistrationOutput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="customerRegistrationOutput">
 *   &lt;complexContent>
 *     &lt;extension base="{}returnInfoService">
 *       &lt;sequence>
 *         &lt;element name="messageCreatDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="messageDetails" type="{}messageDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="moduleId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="moduleTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="newABOPartyList" type="{}newABOPartyInfo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerRegistrationOutput", propOrder = {
    "messageCreatDate",
    "messageDetails",
    "moduleId",
    "moduleTypeCd",
    "newABOPartyList"
})
public class CustomerRegistrationOutput
    extends ReturnInfoService
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar messageCreatDate;
    @XmlElement(nillable = true)
    protected List<MessageDetails> messageDetails;
    protected String moduleId;
    protected String moduleTypeCd;
    @XmlElement(nillable = true)
    protected List<NewABOPartyInfo> newABOPartyList;

    /**
     * Gets the value of the messageCreatDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMessageCreatDate() {
        return messageCreatDate;
    }

    /**
     * Sets the value of the messageCreatDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMessageCreatDate(XMLGregorianCalendar value) {
        this.messageCreatDate = value;
    }

    /**
     * Gets the value of the messageDetails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the messageDetails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMessageDetails().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MessageDetails }
     * 
     * 
     */
    public List<MessageDetails> getMessageDetails() {
        if (messageDetails == null) {
            messageDetails = new ArrayList<MessageDetails>();
        }
        return this.messageDetails;
    }

    /**
     * Gets the value of the moduleId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModuleId() {
        return moduleId;
    }

    /**
     * Sets the value of the moduleId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModuleId(String value) {
        this.moduleId = value;
    }

    /**
     * Gets the value of the moduleTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModuleTypeCd() {
        return moduleTypeCd;
    }

    /**
     * Sets the value of the moduleTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModuleTypeCd(String value) {
        this.moduleTypeCd = value;
    }

    /**
     * Gets the value of the newABOPartyList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the newABOPartyList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNewABOPartyList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NewABOPartyInfo }
     * 
     * 
     */
    public List<NewABOPartyInfo> getNewABOPartyList() {
        if (newABOPartyList == null) {
            newABOPartyList = new ArrayList<NewABOPartyInfo>();
        }
        return this.newABOPartyList;
    }

}
