package com.amway.integration.cis.los.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * AboLos Address POJO
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aboLosAddress", propOrder = { "type", "line", "city", "state", "postalCode", "country", "countryName" })
public class AboLosAddress
{
	@XmlElement(name = "Type")
	protected String type;

	@XmlElement(name = "Line")
	protected List<String> line;

	@XmlElement(name = "City")
	protected String city;
	@XmlElement(name = "State")
	protected String state;
	@XmlElement(name = "PostalCode")
	protected String postalCode;
	@XmlElement(name = "Country")
	protected String country;
	@XmlElement(name = "CountryName")
	protected String countryName;

	/**
	 * @return type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(final String type)
	{
		this.type = type;
	}

	/**
	 * @return line
	 */
	public List<String> getLine()
	{
		return line;
	}

	/**
	 * @param line the line to set
	 */
	public void setLine(final List<String> line)
	{
		this.line = line;
	}

	/**
	 * @return city
	 */
	public String getCity()
	{
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(final String city)
	{
		this.city = city;
	}

	/**
	 * @return state
	 */
	public String getState()
	{
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(final String state)
	{
		this.state = state;
	}

	/**
	 * @return postalCode
	 */
	public String getPostalCode()
	{
		return postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(final String postalCode)
	{
		this.postalCode = postalCode;
	}

	/**
	 * @return country
	 */
	public String getCountry()
	{
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(final String country)
	{
		this.country = country;
	}

	/**
	 * @return countryName
	 */
	public String getCountryName()
	{
		return countryName;
	}

	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(final String countryName)
	{
		this.countryName = countryName;
	}


}
