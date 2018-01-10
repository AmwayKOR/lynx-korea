
package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for sponsor complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="sponsor">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sponsorAboNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sponsorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stateCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sponsor", propOrder = {
	 "cityName",
	 "sponsorAboNum",
	 "sponsorABONo",
    "sponsorEmail",
    "sponsorName",
    "sponsorPhone",
    "stateCd"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sponsor {

    protected String sponsorABONo;
    protected String sponsorEmail;
    protected String sponsorName;
    protected String sponsorPhone;
    protected String sponsorAboNum;
    protected String stateCd;
    protected String cityName;

    public String getSponsorABONo()
    {
        return sponsorABONo;
    }

    public void setSponsorABONo(String sponsorABONo)
    {
        this.sponsorABONo = sponsorABONo;
    }

    public String getSponsorEmail()
    {
        return sponsorEmail;
    }

    public void setSponsorEmail(String sponsorEmail)
    {
        this.sponsorEmail = sponsorEmail;
    }

    public String getSponsorName()
    {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName)
    {
        this.sponsorName = sponsorName;
    }

    public String getSponsorPhone()
    {
        return sponsorPhone;
    }

    public void setSponsorPhone(String sponsorPhone)
    {
        this.sponsorPhone = sponsorPhone;
    }

	public String getSponsorAboNum()
	{
		return sponsorAboNum;
	}

	public void setSponsorAboNum(String sponsorAboNum)
	{
		this.sponsorAboNum = sponsorAboNum;
	}

	public String getStateCd()
	{
		return stateCd;
	}

	public void setStateCd(String stateCd)
	{
		this.stateCd = stateCd;
	}

	public String getCityName()
	{
		return cityName;
	}

	public void setCityName(String cityName)
	{
		this.cityName = cityName;
	}
}
