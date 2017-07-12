
package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sponsorAttributesInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sponsorAttributesInput">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="acctIntlSponsorAboNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="acctIntlSponsorCntryCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="acctSponsorAboNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="acctSponsorAffCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="intlPrimaryBusFlg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sponsorListFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sponsorAttributesInput", propOrder = {
    "acctIntlSponsorAboNum",
    "acctIntlSponsorCntryCd",
    "acctSponsorAboNum",
    "acctSponsorAffCd",
    "intlPrimaryBusFlg",
    "sponsorListFlag"
})
public class SponsorAttributesInput {

    protected String acctIntlSponsorAboNum;
    protected String acctIntlSponsorCntryCd;
    protected String acctSponsorAboNum;
    protected String acctSponsorAffCd;
    protected String intlPrimaryBusFlg;
    protected String sponsorListFlag;

    /**
     * Gets the value of the acctIntlSponsorAboNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctIntlSponsorAboNum() {
        return acctIntlSponsorAboNum;
    }

    /**
     * Sets the value of the acctIntlSponsorAboNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctIntlSponsorAboNum(String value) {
        this.acctIntlSponsorAboNum = value;
    }

    /**
     * Gets the value of the acctIntlSponsorCntryCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctIntlSponsorCntryCd() {
        return acctIntlSponsorCntryCd;
    }

    /**
     * Sets the value of the acctIntlSponsorCntryCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctIntlSponsorCntryCd(String value) {
        this.acctIntlSponsorCntryCd = value;
    }

    /**
     * Gets the value of the acctSponsorAboNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctSponsorAboNum() {
        return acctSponsorAboNum;
    }

    /**
     * Sets the value of the acctSponsorAboNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctSponsorAboNum(String value) {
        this.acctSponsorAboNum = value;
    }

    /**
     * Gets the value of the acctSponsorAffCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctSponsorAffCd() {
        return acctSponsorAffCd;
    }

    /**
     * Sets the value of the acctSponsorAffCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctSponsorAffCd(String value) {
        this.acctSponsorAffCd = value;
    }

    /**
     * Gets the value of the intlPrimaryBusFlg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntlPrimaryBusFlg() {
        return intlPrimaryBusFlg;
    }

    /**
     * Sets the value of the intlPrimaryBusFlg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntlPrimaryBusFlg(String value) {
        this.intlPrimaryBusFlg = value;
    }

    /**
     * Gets the value of the sponsorListFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSponsorListFlag() {
        return sponsorListFlag;
    }

    /**
     * Sets the value of the sponsorListFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSponsorListFlag(String value) {
        this.sponsorListFlag = value;
    }

}
