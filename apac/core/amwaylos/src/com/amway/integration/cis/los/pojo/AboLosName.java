package com.amway.integration.cis.los.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aboLosName", propOrder = { "nameType", "name", "localizedName" })
/**
 *
 * AboLos Name POJO
 *
 */ public class AboLosName
{
	@XmlElement(name = "NameType")
	protected String nameType;
	@XmlElement(name = "Name")
	protected String name;
	@XmlElement(name = "LocalizedName")
	protected String localizedName;


	/**
	 * @return nameType
	 */
	public String getNameType()
	{
		return nameType;
	}

	/**
	 * @param nameType the nameType to set
	 */
	public void setNameType(final String nameType)
	{
		this.nameType = nameType;
	}

	/**
	 * @return name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(final String name)
	{
		this.name = name;
	}

	/**
	 * @return localizedName
	 */
	public String getLocalizedName()
	{
		return localizedName;
	}

	/**
	 * @param localizedName the localizedName to set
	 */
	public void setLocalizedName(final String localizedName)
	{
		this.localizedName = localizedName;
	}


}
