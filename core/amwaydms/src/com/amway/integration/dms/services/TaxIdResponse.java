
package com.amway.integration.dms.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for taxIdResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="taxIdResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{}returnInfoService">
 *       &lt;sequence>
 *         &lt;element name="taxIdList" type="{}taxIdDetail" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "taxIdResponse", propOrder = {
    "taxIdList"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaxIdResponse
    extends ReturnInfoService
{

    @XmlElement(nillable = true)
    protected List<TaxIdDetail> taxIdList;

    /**
     * Gets the value of the taxIdList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the taxIdList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTaxIdList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TaxIdDetail }
     * 
     * 
     */
    public List<TaxIdDetail> getTaxIdList() {
        if (taxIdList == null) {
            taxIdList = new ArrayList<TaxIdDetail>();
        }
        return this.taxIdList;
    }

}
