/**
 *
 */
package com.amway.integration.cis.los.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.codehaus.jackson.annotate.JsonAnySetter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.List;


/**
 * TransactionGroup POJO
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transactionGroup", propOrder =
{ "affiliateCode", "isoCountryCode", "sourceApplication", "id", "timestamp", "transactions" })
public class TransactionGroup
{
	protected String affiliateCode;
	protected String isoCountryCode;
	protected String sourceApplication;
	protected String id;

	@XmlSchemaType(name = "dateTime")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
	protected XMLGregorianCalendar timestamp;

	protected List<Transaction> transactions;

	/**
	 * @return the transactions
	 */
	public List<Transaction> getTransactions()
	{
		return transactions;
	}

	@JsonAnySetter
	public void setTransactions(final List<Transaction> transactions)
	{
		this.transactions = transactions;
	}

	/**
	 * @return affiliateCode
	 */
	public String getAffiliateCode()
	{
		return this.affiliateCode;
	}

	/**
	 * @param value
	 */
	public void setAffiliateCode(final String value)
	{
		this.affiliateCode = value;
	}

	/**
	 * @return isoCountryCode
	 */
	public String getIsoCountryCode()
	{
		return this.isoCountryCode;
	}

	/**
	 * @param value
	 */
	public void setIsoCountryCode(final String value)
	{
		this.isoCountryCode = value;
	}

	/**
	 * @return sourceApplication
	 */
	public String getSourceApplication()
	{
		return this.sourceApplication;
	}

	/**
	 * @param value
	 */
	public void setSourceApplication(final String value)
	{
		this.sourceApplication = value;
	}

	/**
	 * @return id
	 */
	public String getId()
	{
		return this.id;
	}

	/**
	 * @param value
	 */
	public void setId(final String value)
	{
		this.id = value;
	}

	/**
	 * @return timestamp
	 */
	public XMLGregorianCalendar getTimestamp()
	{
		return this.timestamp;
	}

	/**
	 * @param value
	 */
	public void setTimestamp(final XMLGregorianCalendar value)
	{
		this.timestamp = value;
	}
}
