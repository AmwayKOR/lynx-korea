package com.amway.integration.dms.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for amwayProfileOutput complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="amwayProfileOutput">
 *   &lt;complexContent>
 *     &lt;extension base="{}returnInfoService">
 *       &lt;sequence>
 *         &lt;element name="accountBalance" type="{}accountBalance" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="accountMasterDetails" type="{}accountFullResponse" minOccurs="0"/>
 *         &lt;element name="bankAccountDetailList" type="{}accountDetailOutput" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="blockPrivList" type="{}getBlockPrivDetSrvcOutput" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="partyDetailList" type="{}partyDetailsOutput" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="sponsorDetails" type="{}sponsorDetailResponse" minOccurs="0"/>
 *         &lt;element name="subscriptionList" type="{}subsrciptionSvcData" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "amwayProfileOutput",
		propOrder = { "accountBalance", "accountMasterDetails", "bankAccountDetailList", "blockPrivList", "partyDetailList",
				"sponsorDetails", "subscriptionList" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class AmwayProfileOutput extends ReturnInfoService
{
	//v3 start
	@XmlElement(nillable = true)
	protected String serverName;

	@XmlElementRef(name = "account",
			type = JAXBElement.class,
			required = false)
	@XmlElement(nillable = true)
	protected Account account;

	@XmlElementRef(name = "errorMessage",
			type = JAXBElement.class,
			required = false)
	@XmlElement(nillable = true)
	protected ErrorMessage errorMessage;
	//v3 end

	@XmlElement(nillable = true)
	protected List<AccountBalance> accountBalance;
	@XmlElementRef(name = "accountMasterDetails",
			type = JAXBElement.class,
			required = false)
	protected AccountFullResponse accountMasterDetails;
	@XmlElement(nillable = true)
	protected List<AccountDetailOutput> bankAccountDetailList;
	@XmlElement(nillable = true)
	protected List<GetBlockPrivDetSrvcOutput> blockPrivList;
	@XmlElement(nillable = true)
	protected List<PartyDetailsOutput> partyDetailList;
	@XmlElementRef(name = "sponsorDetails",
			type = JAXBElement.class,
			required = false)
	protected SponsorDetailResponse sponsorDetails;
	@XmlElement(nillable = true)
	protected List<SubsrciptionSvcData> subscriptionList;

	/**
	 * Gets the value of the accountBalance property.
	 * <p>
	 * <p>
	 * This accessor method returns a reference to the live list,
	 * not a snapshot. Therefore any modification you make to the
	 * returned list will be present inside the JAXB object.
	 * This is why there is not a <CODE>set</CODE> method for the accountBalance property.
	 * <p>
	 * <p>
	 * For example, to add a new item, do as follows:
	 * <pre>
	 *    getAccountBalance().add(newItem);
	 * </pre>
	 * <p>
	 * <p>
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link AccountBalance }
	 */
	public List<AccountBalance> getAccountBalance()
	{
		if (accountBalance == null)
		{
			accountBalance = new ArrayList<AccountBalance>();
		}
		return this.accountBalance;
	}

	/**
	 * Gets the value of the accountMasterDetails property.
	 *
	 * @return possible object is
	 * {@link JAXBElement }{@code <}{@link AccountFullResponse }{@code >}
	 */
	public AccountFullResponse getAccountMasterDetails()
	{
		return accountMasterDetails;
	}

	/**
	 * Sets the value of the accountMasterDetails property.
	 *
	 * @param value
	 * 		allowed object is
	 * 		{@link JAXBElement }{@code <}{@link AccountFullResponse }{@code >}
	 */
	public void setAccountMasterDetails(AccountFullResponse value)
	{
		this.accountMasterDetails = value;
	}

	/**
	 * Gets the value of the bankAccountDetailList property.
	 * <p>
	 * <p>
	 * This accessor method returns a reference to the live list,
	 * not a snapshot. Therefore any modification you make to the
	 * returned list will be present inside the JAXB object.
	 * This is why there is not a <CODE>set</CODE> method for the bankAccountDetailList property.
	 * <p>
	 * <p>
	 * For example, to add a new item, do as follows:
	 * <pre>
	 *    getBankAccountDetailList().add(newItem);
	 * </pre>
	 * <p>
	 * <p>
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link AccountDetailOutput }
	 */
	public List<AccountDetailOutput> getBankAccountDetailList()
	{
		if (bankAccountDetailList == null)
		{
			bankAccountDetailList = new ArrayList<AccountDetailOutput>();
		}
		return this.bankAccountDetailList;
	}

	/**
	 * Gets the value of the blockPrivList property.
	 * <p>
	 * <p>
	 * This accessor method returns a reference to the live list,
	 * not a snapshot. Therefore any modification you make to the
	 * returned list will be present inside the JAXB object.
	 * This is why there is not a <CODE>set</CODE> method for the blockPrivList property.
	 * <p>
	 * <p>
	 * For example, to add a new item, do as follows:
	 * <pre>
	 *    getBlockPrivList().add(newItem);
	 * </pre>
	 * <p>
	 * <p>
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link GetBlockPrivDetSrvcOutput }
	 */
	public List<GetBlockPrivDetSrvcOutput> getBlockPrivList()
	{
		if (blockPrivList == null)
		{
			blockPrivList = new ArrayList<GetBlockPrivDetSrvcOutput>();
		}
		return this.blockPrivList;
	}

	/**
	 * Gets the value of the partyDetailList property.
	 * <p>
	 * <p>
	 * This accessor method returns a reference to the live list,
	 * not a snapshot. Therefore any modification you make to the
	 * returned list will be present inside the JAXB object.
	 * This is why there is not a <CODE>set</CODE> method for the partyDetailList property.
	 * <p>
	 * <p>
	 * For example, to add a new item, do as follows:
	 * <pre>
	 *    getPartyDetailList().add(newItem);
	 * </pre>
	 * <p>
	 * <p>
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link PartyDetailsOutput }
	 */
	public List<PartyDetailsOutput> getPartyDetailList()
	{
		if (partyDetailList == null)
		{
			partyDetailList = new ArrayList<PartyDetailsOutput>();
		}
		return this.partyDetailList;
	}

	/**
	 * Gets the value of the sponsorDetails property.
	 *
	 * @return possible object is
	 * {@link JAXBElement }{@code <}{@link SponsorDetailResponse }{@code >}
	 */
	public SponsorDetailResponse getSponsorDetails()
	{
		return sponsorDetails;
	}

	/**
	 * Sets the value of the sponsorDetails property.
	 *
	 * @param value
	 * 		allowed object is
	 * 		{@link JAXBElement }{@code <}{@link SponsorDetailResponse }{@code >}
	 */
	public void setSponsorDetails(SponsorDetailResponse value)
	{
		this.sponsorDetails = value;
	}

	/**
	 * Gets the value of the subscriptionList property.
	 * <p>
	 * <p>
	 * This accessor method returns a reference to the live list,
	 * not a snapshot. Therefore any modification you make to the
	 * returned list will be present inside the JAXB object.
	 * This is why there is not a <CODE>set</CODE> method for the subscriptionList property.
	 * <p>
	 * <p>
	 * For example, to add a new item, do as follows:
	 * <pre>
	 *    getSubscriptionList().add(newItem);
	 * </pre>
	 * <p>
	 * <p>
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link SubsrciptionSvcData }
	 */
	public List<SubsrciptionSvcData> getSubscriptionList()
	{
		if (subscriptionList == null)
		{
			subscriptionList = new ArrayList<SubsrciptionSvcData>();
		}
		return this.subscriptionList;
	}

	@Override
	public String getServerName()
	{
		return serverName;
	}

	@Override
	public void setServerName(String serverName)
	{
		this.serverName = serverName;
	}

	public Account getAccount()
	{
		return account;
	}

	public void setAccount(Account account)
	{
		this.account = account;
	}

	public ErrorMessage getErrorMessage()
	{
		return errorMessage;
	}

	public void setErrorMessage(ErrorMessage errorMessage)
	{
		this.errorMessage = errorMessage;
	}
}
