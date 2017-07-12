package com.amway.integration.cis.los.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * AboLos Extended POJO
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aboLosExtended", propOrder = { "name", "address", "entryDate", "renewalDate", "expireDate", "primaryPhoneNo",
		"primaryPhoneIntDialCode", "primaryEmail" })
public class AboLosExtended
{
	@XmlElement(name = "Name")
	protected List<AboLosName> name;

	@XmlElement(name = "Address")
	protected List<AboLosAddress> address;

	@XmlElement(name = "EntryDate")
	protected String entryDate;
	@XmlElement(name = "RenewalDate")
	protected String renewalDate;
	@XmlElement(name = "ExpireDate")
	protected String expireDate;
	@XmlElement(name = "PrimaryPhoneNo")
	protected String primaryPhoneNo;
	@XmlElement(name = "PrimaryPhoneIntDialCode")
	protected String primaryPhoneIntDialCode;
	@XmlElement(name = "PrimaryEmail")
	protected String primaryEmail;

	/**
	 * @return name
	 */
	public List<AboLosName> getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(final List<AboLosName> name)
	{
		this.name = name;
	}

	/**
	 * @return address
	 */
	public List<AboLosAddress> getAddress()
	{
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(final List<AboLosAddress> address)
	{
		this.address = address;
	}

	/**
	 * @return entryDate
	 */
	public String getEntryDate()
	{
		return entryDate;
	}

	/**
	 * @param entryDate the entryDate to set
	 */
	public void setEntryDate(final String entryDate)
	{
		this.entryDate = entryDate;
	}

	/**
	 * @return renewalDate
	 */
	public String getRenewalDate()
	{
		return renewalDate;
	}

	/**
	 * @param renewalDate the renewalDate to set
	 */
	public void setRenewalDate(final String renewalDate)
	{
		this.renewalDate = renewalDate;
	}

	/**
	 * @return expireDate
	 */
	public String getExpireDate()
	{
		return expireDate;
	}

	/**
	 * @param expireDate the expireDate to set
	 */
	public void setExpireDate(final String expireDate)
	{
		this.expireDate = expireDate;
	}

	/**
	 * @return primaryPhoneNo
	 */
	public String getPrimaryPhoneNo()
	{
		return primaryPhoneNo;
	}

	/**
	 * @param primaryPhoneNo the primaryPhoneNo to set
	 */
	public void setPrimaryPhoneNo(final String primaryPhoneNo)
	{
		this.primaryPhoneNo = primaryPhoneNo;
	}

	/**
	 * @return primaryPhoneIntDialCode
	 */
	public String getPrimaryPhoneIntDialCode()
	{
		return primaryPhoneIntDialCode;
	}

	/**
	 * @param primaryPhoneIntDialCode the primaryPhoneIntDialCode to set
	 */
	public void setPrimaryPhoneIntDialCode(final String primaryPhoneIntDialCode)
	{
		this.primaryPhoneIntDialCode = primaryPhoneIntDialCode;
	}

	/**
	 * @return primaryEmail
	 */
	public String getPrimaryEmail()
	{
		return primaryEmail;
	}

	/**
	 * @param primaryEmail the primaryEmail to set
	 */
	public void setPrimaryEmail(final String primaryEmail)
	{
		this.primaryEmail = primaryEmail;
	}

}
