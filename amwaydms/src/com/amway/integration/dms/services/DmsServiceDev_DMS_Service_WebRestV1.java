package com.amway.integration.dms.services;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;

import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;


/**
 * DmsServiceDev_DMS_Service_WebRestV1
 * 
 * 
 */
@Generated(value =
{ "wadl|http://dms-service-dev:8080/DMS_Service_Web/rest/v1/application.wadl" }, comments = "wadl2java, http://wadl.java.net", date = "2015-04-27T13:25:45.198+05:30")
public class DmsServiceDev_DMS_Service_WebRestV1
{

	/**
	 * The base URI for the resource represented by this proxy
	 * 
	 */
	private static Logger LOG = Logger.getLogger(DmsServiceDev_DMS_Service_WebRestV1.class);
	public final static URI BASE_URI;

	static
	{
		URI originalURI = URI.create("http://dms-service-dev:8080/DMS_Service_Web/rest/v1/");
		// Look up to see if we have any indirection in the local copy
		// of META-INF/java-rs-catalog.xml file, assuming it will be in the
		// oasis:name:tc:entity:xmlns:xml:catalog namespace or similar duck type
		final java.io.InputStream is = DmsServiceDev_DMS_Service_WebRestV1.class
				.getResourceAsStream("/META-INF/jax-rs-catalog.xml");
		if (is != null)
		{
			try
			{
				// Ignore the namespace in the catalog, can't use wildcard until
				// we are sure we have XPath 2.0
				final String found = javax.xml.xpath.XPathFactory
						.newInstance()
						.newXPath()
						.evaluate("/*[name(.) = 'catalog']/*[name(.) = 'uri' and @name ='" + originalURI + "']/@uri",
								new org.xml.sax.InputSource(is));
				if (found != null && found.length() > 0)
				{
					originalURI = java.net.URI.create(found);
				}

			}
			catch (final Exception ex)
			{
				LOG.error(ex.getMessage(),ex);
			}
			finally
			{
				try
				{
					is.close();
				}
				catch (final java.io.IOException e)
				{
					LOG.error(e.getMessage(),e);
				}
			}
		}
		BASE_URI = originalURI;
	}

	/**
	 * 
	 * @param client
	 * @param baseURI
	 * @return DmsServiceDev_DMS_Service_WebRestV1.PreferenceService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.PreferenceService preferenceService(
			final com.sun.jersey.api.client.Client client, final URI baseURI)
	{
		return new DmsServiceDev_DMS_Service_WebRestV1.PreferenceService(client, baseURI);
	}

	/**
	 * Template method to allow tooling to customize the new Client
	 * 
	 * @param cc
	 */
	private static void customizeClientConfiguration(final ClientConfig cc)
	{
		//
	}

	/**
	 * Template method to allow tooling to override Client factory
	 * 
	 */
	private static com.sun.jersey.api.client.Client createClientInstance(final ClientConfig cc)
	{
		return com.sun.jersey.api.client.Client.create(cc);
	}

	/**
	 * Create a new Client instance
	 * 
	 * @return createClientInstance
	 */
	public static com.sun.jersey.api.client.Client createClient()
	{
		final ClientConfig cc = new DefaultClientConfig();
		customizeClientConfiguration(cc);
		return createClientInstance(cc);
	}

	/**
	 * 
	 * @return preferenceService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.PreferenceService preferenceService()
	{
		return preferenceService(createClient(), BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @return preferenceService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.PreferenceService preferenceService(
			final com.sun.jersey.api.client.Client client)
	{
		return preferenceService(client, BASE_URI);
	}

	/**
	 * renewal service
	 * 
	 * @param client
	 * @param baseURI
	 * @return DmsServiceDev_DMS_Service_WebRestV1.RenewalService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.RenewalService renewalService(final com.sun.jersey.api.client.Client client,
			final URI baseURI)
	{
		return new DmsServiceDev_DMS_Service_WebRestV1.RenewalService(client, baseURI);
	}

	/**
	 * 
	 * @return renewalService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.RenewalService renewalService()
	{
		return renewalService(createClient(), BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @return renewalService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.RenewalService renewalService(final com.sun.jersey.api.client.Client client)
	{
		return renewalService(client, BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @param baseURI
	 * @return DmsServiceDev_DMS_Service_WebRestV1.BlocksPrivilegeService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.BlocksPrivilegeService blocksPrivilegeService(
			final com.sun.jersey.api.client.Client client, final URI baseURI)
	{
		return new DmsServiceDev_DMS_Service_WebRestV1.BlocksPrivilegeService(client, baseURI);
	}

	/**
	 * 
	 * @return blocksPrivilegeService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.BlocksPrivilegeService blocksPrivilegeService()
	{
		return blocksPrivilegeService(createClient(), BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @return blocksPrivilegeService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.BlocksPrivilegeService blocksPrivilegeService(
			final com.sun.jersey.api.client.Client client)
	{
		return blocksPrivilegeService(client, BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @param baseURI
	 * @return DmsServiceDev_DMS_Service_WebRestV1.RegistrationService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.RegistrationService registrationService(
			final com.sun.jersey.api.client.Client client, final URI baseURI)
	{
		return new DmsServiceDev_DMS_Service_WebRestV1.RegistrationService(client, baseURI);
	}

	/**
	 * 
	 * @return registrationService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.RegistrationService registrationService()
	{
		return registrationService(createClient(), BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @return registrationService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.RegistrationService registrationService(
			final com.sun.jersey.api.client.Client client)
	{
		return registrationService(client, BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @param baseURI
	 * @return EcommService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.EcommService ecommService(final com.sun.jersey.api.client.Client client,
			final URI baseURI)
	{
		return new DmsServiceDev_DMS_Service_WebRestV1.EcommService(client, baseURI);
	}

	/**
	 * 
	 * @return ecommService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.EcommService ecommService()
	{
		return ecommService(createClient(), BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @return ecommService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.EcommService ecommService(final com.sun.jersey.api.client.Client client)
	{
		return ecommService(client, BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @param baseURI
	 * @return LoadEligibleSponsor
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.LoadEligibleSponsor loadEligibleSponsor(
			final com.sun.jersey.api.client.Client client, final URI baseURI)
	{
		return new DmsServiceDev_DMS_Service_WebRestV1.LoadEligibleSponsor(client, baseURI);
	}

	/**
	 * 
	 * @return loadEligibleSponsor
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.LoadEligibleSponsor loadEligibleSponsor()
	{
		return loadEligibleSponsor(createClient(), BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @return loadEligibleSponsor
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.LoadEligibleSponsor loadEligibleSponsor(
			final com.sun.jersey.api.client.Client client)
	{
		return loadEligibleSponsor(client, BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @param baseURI
	 * @return DmsServiceDev_DMS_Service_WebRestV1.BusinessNatureService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.BusinessNatureService businessNatureService(
			final com.sun.jersey.api.client.Client client, final URI baseURI)
	{
		return new DmsServiceDev_DMS_Service_WebRestV1.BusinessNatureService(client, baseURI);
	}

	/**
	 * 
	 * @return businessNatureService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.BusinessNatureService businessNatureService()
	{
		return businessNatureService(createClient(), BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @return DmsServiceDev_DMS_Service_WebRestV1.BusinessNatureService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.BusinessNatureService businessNatureService(
			final com.sun.jersey.api.client.Client client)
	{
		return businessNatureService(client, BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @param baseURI
	 * @return DmsServiceDev_DMS_Service_WebRestV1.SubscriptionService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.SubscriptionService subscriptionService(
			final com.sun.jersey.api.client.Client client, final URI baseURI)
	{
		return new DmsServiceDev_DMS_Service_WebRestV1.SubscriptionService(client, baseURI);
	}

	/**
	 * 
	 * @return subscriptionService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.SubscriptionService subscriptionService()
	{
		return subscriptionService(createClient(), BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @return subscriptionService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.SubscriptionService subscriptionService(
			final com.sun.jersey.api.client.Client client)
	{
		return subscriptionService(client, BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @param baseURI
	 * @return DmsServiceDev_DMS_Service_WebRestV1.PersonalIdService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.PersonalIdService personalIdService(
			final com.sun.jersey.api.client.Client client, final URI baseURI)
	{
		return new DmsServiceDev_DMS_Service_WebRestV1.PersonalIdService(client, baseURI);
	}

	/**
	 * 
	 * @return personalIdService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.PersonalIdService personalIdService()
	{
		return personalIdService(createClient(), BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @return personalIdService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.PersonalIdService personalIdService(
			final com.sun.jersey.api.client.Client client)
	{
		return personalIdService(client, BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @param baseURI
	 * @return DmsServiceDev_DMS_Service_WebRestV1.BankAccountService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.BankAccountService bankAccountService(
			final com.sun.jersey.api.client.Client client, final URI baseURI)
	{
		return new DmsServiceDev_DMS_Service_WebRestV1.BankAccountService(client, baseURI);
	}

	/**
	 * 
	 * @return bankAccountService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.BankAccountService bankAccountService()
	{
		return bankAccountService(createClient(), BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @return bankAccountService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.BankAccountService bankAccountService(
			final com.sun.jersey.api.client.Client client)
	{
		return bankAccountService(client, BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @param baseURI
	 * @return DmsServiceDev_DMS_Service_WebRestV1.CreditProfileService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.CreditProfileService creditProfileService(
			final com.sun.jersey.api.client.Client client, final URI baseURI)
	{
		return new DmsServiceDev_DMS_Service_WebRestV1.CreditProfileService(client, baseURI);
	}

	/**
	 * 
	 * @return DmsServiceDev_DMS_Service_WebRestV1.CreditProfileService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.CreditProfileService creditProfileService()
	{
		return creditProfileService(createClient(), BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @return creditProfileService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.CreditProfileService creditProfileService(
			final com.sun.jersey.api.client.Client client)
	{
		return creditProfileService(client, BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @param baseURI
	 * @return DmsServiceDev_DMS_Service_WebRestV1.AccountService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.AccountService accountService(final com.sun.jersey.api.client.Client client,
			final URI baseURI)
	{
		return new DmsServiceDev_DMS_Service_WebRestV1.AccountService(client, baseURI);
	}

	/**
	 * 
	 * @return accountService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.AccountService accountService()
	{
		return accountService(createClient(), BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @return accountService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.AccountService accountService(final com.sun.jersey.api.client.Client client)
	{
		return accountService(client, BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @param baseURI
	 * @return DmsServiceDev_DMS_Service_WebRestV1.TaxIdService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.TaxIdService taxIdService(final com.sun.jersey.api.client.Client client,
			final URI baseURI)
	{
		return new DmsServiceDev_DMS_Service_WebRestV1.TaxIdService(client, baseURI);
	}

	/**
	 * 
	 * @return taxIdService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.TaxIdService taxIdService()
	{
		return taxIdService(createClient(), BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @return taxIdService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.TaxIdService taxIdService(final com.sun.jersey.api.client.Client client)
	{
		return taxIdService(client, BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @param baseURI
	 * @return DmsServiceDev_DMS_Service_WebRestV1.PrePrintedNumberService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.PrePrintedNumberService prePrintedNumberService(
			final com.sun.jersey.api.client.Client client, final URI baseURI)
	{
		return new DmsServiceDev_DMS_Service_WebRestV1.PrePrintedNumberService(client, baseURI);
	}

	/**
	 * 
	 * @return prePrintedNumberService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.PrePrintedNumberService prePrintedNumberService()
	{
		return prePrintedNumberService(createClient(), BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @return prePrintedNumberService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.PrePrintedNumberService prePrintedNumberService(
			final com.sun.jersey.api.client.Client client)
	{
		return prePrintedNumberService(client, BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @param baseURI
	 * @return DmsServiceDev_DMS_Service_WebRestV1.AccountBalanceService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.AccountBalanceService accountBalanceService(
			final com.sun.jersey.api.client.Client client, final URI baseURI)
	{
		return new DmsServiceDev_DMS_Service_WebRestV1.AccountBalanceService(client, baseURI);
	}

	/**
	 * 
	 * @return accountBalanceService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.AccountBalanceService accountBalanceService()
	{
		return accountBalanceService(createClient(), BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @return accountBalanceService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.AccountBalanceService accountBalanceService(
			final com.sun.jersey.api.client.Client client)
	{
		return accountBalanceService(client, BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @param baseURI
	 * @return DmsServiceDev_DMS_Service_WebRestV1.HistoryService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.HistoryService historyService(final com.sun.jersey.api.client.Client client,
			final URI baseURI)
	{
		return new DmsServiceDev_DMS_Service_WebRestV1.HistoryService(client, baseURI);
	}

	/**
	 * 
	 * @return historyService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.HistoryService historyService()
	{
		return historyService(createClient(), BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @return historyService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.HistoryService historyService(final com.sun.jersey.api.client.Client client)
	{
		return historyService(client, BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @param baseURI
	 * @return DmsServiceDev_DMS_Service_WebRestV1.PhoneService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.PhoneService phoneService(final com.sun.jersey.api.client.Client client,
			final URI baseURI)
	{
		return new DmsServiceDev_DMS_Service_WebRestV1.PhoneService(client, baseURI);
	}

	/**
	 * 
	 * @return phoneService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.PhoneService phoneService()
	{
		return phoneService(createClient(), BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @return phoneService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.PhoneService phoneService(final com.sun.jersey.api.client.Client client)
	{
		return phoneService(client, BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @param baseURI
	 * @return DmsServiceDev_DMS_Service_WebRestV1.PartyService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.PartyService partyService(final com.sun.jersey.api.client.Client client,
			final URI baseURI)
	{
		return new DmsServiceDev_DMS_Service_WebRestV1.PartyService(client, baseURI);
	}

	/**
	 * 
	 * @return partyService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.PartyService partyService()
	{
		return partyService(createClient(), BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @return partyService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.PartyService partyService(final com.sun.jersey.api.client.Client client)
	{
		return partyService(client, BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @param baseURI
	 * @return DmsServiceDev_DMS_Service_WebRestV1.AddressService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.AddressService addressService(final com.sun.jersey.api.client.Client client,
			final URI baseURI)
	{
		return new DmsServiceDev_DMS_Service_WebRestV1.AddressService(client, baseURI);
	}

	/**
	 * 
	 * @return addressService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.AddressService addressService()
	{
		return addressService(createClient(), BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @return addressService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.AddressService addressService(final com.sun.jersey.api.client.Client client)
	{
		return addressService(client, BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @param baseURI
	 * @return DmsServiceDev_DMS_Service_WebRestV1.DMSSyncService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.DMSSyncService dMSSyncService(final com.sun.jersey.api.client.Client client,
			final URI baseURI)
	{
		return new DmsServiceDev_DMS_Service_WebRestV1.DMSSyncService(client, baseURI);
	}

	/**
	 * 
	 * @return dMSSyncService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.DMSSyncService dMSSyncService()
	{
		return dMSSyncService(createClient(), BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @return dMSSyncService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.DMSSyncService dMSSyncService(final com.sun.jersey.api.client.Client client)
	{
		return dMSSyncService(client, BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @param baseURI
	 * @return DmsServiceDev_DMS_Service_WebRestV1.SponsorService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.SponsorService sponsorService(final com.sun.jersey.api.client.Client client,
			final URI baseURI)
	{
		return new DmsServiceDev_DMS_Service_WebRestV1.SponsorService(client, baseURI);
	}

	/**
	 * 
	 * @return sponsorService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.SponsorService sponsorService()
	{
		return sponsorService(createClient(), BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @return sponsorService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.SponsorService sponsorService(final com.sun.jersey.api.client.Client client)
	{
		return sponsorService(client, BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @param baseURI
	 * @return msServiceDev_DMS_Service_WebRestV1.PartyPreferenceService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.PartyPreferenceService partyPreferenceService(
			final com.sun.jersey.api.client.Client client, final URI baseURI)
	{
		return new DmsServiceDev_DMS_Service_WebRestV1.PartyPreferenceService(client, baseURI);
	}

	/**
	 * 
	 * @return partyPreferenceService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.PartyPreferenceService partyPreferenceService()
	{
		return partyPreferenceService(createClient(), BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @return partyPreferenceService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.PartyPreferenceService partyPreferenceService(
			final com.sun.jersey.api.client.Client client)
	{
		return partyPreferenceService(client, BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @param baseURI
	 * @return DmsServiceDev_DMS_Service_WebRestV1.PrivacyProfileService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.PrivacyProfileService privacyProfileService(
			final com.sun.jersey.api.client.Client client, final URI baseURI)
	{
		return new DmsServiceDev_DMS_Service_WebRestV1.PrivacyProfileService(client, baseURI);
	}

	/**
	 * 
	 * @return privacyProfileService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.PrivacyProfileService privacyProfileService()
	{
		return privacyProfileService(createClient(), BASE_URI);
	}

	/**
	 * 
	 * @param client
	 * @return privacyProfileService
	 */
	public static DmsServiceDev_DMS_Service_WebRestV1.PrivacyProfileService privacyProfileService(
			final com.sun.jersey.api.client.Client client)
	{
		return privacyProfileService(client, BASE_URI);
	}

	/**
	 * 
	 * Account Balance Service
	 * 
	 */
	public static class AccountBalanceService
	{

		private final com.sun.jersey.api.client.Client _client;
		private UriBuilder _uriBuilder;
		private final Map<String, Object> _templateAndMatrixParameterValues;

		private AccountBalanceService(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
				final Map<String, Object> map)
		{
			_client = client;
			_uriBuilder = uriBuilder.clone();
			_templateAndMatrixParameterValues = map;
		}

		/**
		 * Create new instance using existing Client instance, and a base URI and any parameters
		 * 
		 * @param client
		 * @param baseUri
		 */
		public AccountBalanceService(final com.sun.jersey.api.client.Client client, final URI baseUri)
		{
			_client = client;
			_uriBuilder = UriBuilder.fromUri(baseUri);
			_uriBuilder = _uriBuilder.path("AccountBalanceService");
			_templateAndMatrixParameterValues = new HashMap<String, Object>();
		}

		/**
		 * 
		 * @return DmsServiceDev_DMS_Service_WebRestV1.AccountBalanceService.GetBalance
		 */
		public DmsServiceDev_DMS_Service_WebRestV1.AccountBalanceService.GetBalance getBalance()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.AccountBalanceService.GetBalance(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		/**
		 * 
		 * @return DmsServiceDev_DMS_Service_WebRestV1.AccountBalanceService.CreateBalance
		 */
		public DmsServiceDev_DMS_Service_WebRestV1.AccountBalanceService.CreateBalance createBalance()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.AccountBalanceService.CreateBalance(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		/**
		 * 
		 * CreateBalance
		 * 
		 */
		public static class CreateBalance
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private CreateBalance(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 */
			public CreateBalance(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/createBalance");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postJsonAsReturnInfoService(final BaseWebServiceInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "baseWebServiceInput"), BaseWebServiceInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postXmlAsReturnInfoService(final BaseWebServiceInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "baseWebServiceInput"), BaseWebServiceInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		/**
		 * 
		 * Get Balance
		 * 
		 */
		public static class GetBalance
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private GetBalance(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 */
			public GetBalance(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/getBalance");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return AccountBalanceResponse
			 */
			public AccountBalanceResponse postJsonAsAccountBalanceResponse(final AccountBalanceRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "accountBalanceRequest"), AccountBalanceRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(AccountBalanceResponse.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return AccountBalanceResponse
			 */
			public AccountBalanceResponse postXmlAsAccountBalanceResponse(final AccountBalanceRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "accountBalanceRequest"), AccountBalanceRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(AccountBalanceResponse.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

	}

	/**
	 * 
	 * Account service
	 * 
	 */
	public static class AccountService
	{

		private final com.sun.jersey.api.client.Client _client;
		private UriBuilder _uriBuilder;
		private final Map<String, Object> _templateAndMatrixParameterValues;

		private AccountService(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
				final Map<String, Object> map)
		{
			_client = client;
			_uriBuilder = uriBuilder.clone();
			_templateAndMatrixParameterValues = map;
		}

		/**
		 * Create new instance using existing Client instance, and a base URI and any parameters
		 * 
		 * @param client
		 * @param baseUri
		 */
		public AccountService(final com.sun.jersey.api.client.Client client, final URI baseUri)
		{
			_client = client;
			_uriBuilder = UriBuilder.fromUri(baseUri);
			_uriBuilder = _uriBuilder.path("AccountService");
			_templateAndMatrixParameterValues = new HashMap<String, Object>();
		}

		/**
		 * 
		 * @return DmsServiceDev_DMS_Service_WebRestV1.AccountService.UpdateAccount
		 */
		public DmsServiceDev_DMS_Service_WebRestV1.AccountService.UpdateAccount updateAccount()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.AccountService.UpdateAccount(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		/**
		 * 
		 * @return DmsServiceDev_DMS_Service_WebRestV1.AccountService.UpdateAccountSegmentation
		 */
		public DmsServiceDev_DMS_Service_WebRestV1.AccountService.UpdateAccountSegmentation updateAccountSegmentation()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.AccountService.UpdateAccountSegmentation(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		/**
		 * 
		 * @return DmsServiceDev_DMS_Service_WebRestV1.AccountService.Test
		 */
		public DmsServiceDev_DMS_Service_WebRestV1.AccountService.Test test()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.AccountService.Test(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		/**
		 * 
		 * @return DmsServiceDev_DMS_Service_WebRestV1.AccountService.GetAmwayProfile
		 */
		public DmsServiceDev_DMS_Service_WebRestV1.AccountService.GetAmwayProfile getAmwayProfile()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.AccountService.GetAmwayProfile(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		/**
		 * 
		 * @return DmsServiceDev_DMS_Service_WebRestV1.AccountService.GetAccount
		 */
		public DmsServiceDev_DMS_Service_WebRestV1.AccountService.GetAccount getAccount()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.AccountService.GetAccount(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		/**
		 * 
		 * @return DmsServiceDev_DMS_Service_WebRestV1.AccountService.GetAccountPartyList
		 */
		public DmsServiceDev_DMS_Service_WebRestV1.AccountService.GetAccountPartyList getAccountPartyList()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.AccountService.GetAccountPartyList(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		/**
		 * 
		 * Get account
		 * 
		 */
		public static class GetAccount
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private GetAccount(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 */
			public GetAccount(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/getAccount");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return AccountResponse
			 */
			public AccountResponse postJsonAsAccountResponse(final AccountInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "accountInput"), AccountInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(AccountResponse.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return AccountResponse
			 */
			public AccountResponse postXmlAsAccountResponse(final AccountInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "accountInput"), AccountInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(AccountResponse.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		/**
		 * 
		 * Get Account Party List
		 * 
		 */
		public static class GetAccountPartyList
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private GetAccountPartyList(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 */
			public GetAccountPartyList(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/getAccountPartyList");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return AccountPartyListResponse
			 */
			public AccountPartyListResponse postJsonAsAccountPartyListResponse(final AccountInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "accountInput"), AccountInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(AccountPartyListResponse.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return AccountPartyListResponse
			 */
			public AccountPartyListResponse postXmlAsAccountPartyListResponse(final AccountInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "accountInput"), AccountInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(AccountPartyListResponse.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		/**
		 * 
		 * Get Amway Profile
		 * 
		 */
		public static class GetAmwayProfile
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private GetAmwayProfile(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseuri
			 */
			public GetAmwayProfile(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/getAmwayProfile");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return AmwayProfileOutput
			 */
			public AmwayProfileOutput postJsonAsAmwayProfileOutput(final AmwayProfileInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "amwayProfileInput"), AmwayProfileInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(AmwayProfileOutput.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return AmwayProfileOutput
			 */
			public AmwayProfileOutput postXmlAsAmwayProfileOutput(final AmwayProfileInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "amwayProfileInput"), AmwayProfileInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(AmwayProfileOutput.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		/**
		 * 
		 * test
		 * 
		 */
		public static class Test
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private Test(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder, final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 */
			public Test(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/test");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param returnType
			 * @return
			 */
			public <T> T getAsJson(final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("GET", com.sun.jersey.api.client.ClientResponse.class);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param returnType
			 * @return
			 */
			public <T> T getAsJson(final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("GET", com.sun.jersey.api.client.ClientResponse.class);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		/**
		 * 
		 * Update Account
		 * 
		 */
		public static class UpdateAccount
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private UpdateAccount(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 */
			public UpdateAccount(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/updateAccount");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postJsonAsReturnInfoService(final UpdateAccountRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "updateAccountRequest"), UpdateAccountRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postXmlAsReturnInfoService(final UpdateAccountRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "updateAccountRequest"), UpdateAccountRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		/**
		 * 
		 * Update Account Segmentation
		 * 
		 */
		public static class UpdateAccountSegmentation
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private UpdateAccountSegmentation(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 */
			public UpdateAccountSegmentation(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/updateAccountSegmentation");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postJsonAsReturnInfoService(final BaseWebServiceInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "baseWebServiceInput"), BaseWebServiceInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postXmlAsReturnInfoService(final BaseWebServiceInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "baseWebServiceInput"), BaseWebServiceInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

	}

	/**
	 * 
	 * Address Service
	 * 
	 */
	public static class AddressService
	{

		private final com.sun.jersey.api.client.Client _client;
		private UriBuilder _uriBuilder;
		private final Map<String, Object> _templateAndMatrixParameterValues;

		private AddressService(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
				final Map<String, Object> map)
		{
			_client = client;
			_uriBuilder = uriBuilder.clone();
			_templateAndMatrixParameterValues = map;
		}

		/**
		 * Create new instance using existing Client instance, and a base URI and any parameters
		 * 
		 * @param client
		 * @param baseUri
		 * 
		 */
		public AddressService(final com.sun.jersey.api.client.Client client, final URI baseUri)
		{
			_client = client;
			_uriBuilder = UriBuilder.fromUri(baseUri);
			_uriBuilder = _uriBuilder.path("AddressService");
			_templateAndMatrixParameterValues = new HashMap<String, Object>();
		}

		/**
		 * 
		 * @return DmsServiceDev_DMS_Service_WebRestV1.AddressService.AddPartyAddress
		 */
		public DmsServiceDev_DMS_Service_WebRestV1.AddressService.AddPartyAddress addPartyAddress()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.AddressService.AddPartyAddress(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		/**
		 * 
		 * @return DmsServiceDev_DMS_Service_WebRestV1.AddressService.UpdatePartyAddress
		 */
		public DmsServiceDev_DMS_Service_WebRestV1.AddressService.UpdatePartyAddress updatePartyAddress()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.AddressService.UpdatePartyAddress(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		/**
		 * 
		 * @return DmsServiceDev_DMS_Service_WebRestV1.AddressService.GetPartyAddress
		 */
		public DmsServiceDev_DMS_Service_WebRestV1.AddressService.GetPartyAddress getPartyAddress()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.AddressService.GetPartyAddress(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		/**
		 * 
		 * @return DmsServiceDev_DMS_Service_WebRestV1.AddressService.DeletePartyAddress
		 */
		public DmsServiceDev_DMS_Service_WebRestV1.AddressService.DeletePartyAddress deletePartyAddress()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.AddressService.DeletePartyAddress(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		/**
		 * 
		 * Add Party Address
		 * 
		 */
		public static class AddPartyAddress
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private AddPartyAddress(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 */
			public AddPartyAddress(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/addPartyAddress");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postJsonAsReturnInfoService(final AddPartyAddressRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "addPartyAddressRequest"), AddPartyAddressRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postXmlAsReturnInfoService(final AddPartyAddressRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "addPartyAddressRequest"), AddPartyAddressRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		/**
		 * 
		 * Delete party address
		 * 
		 */
		public static class DeletePartyAddress
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private DeletePartyAddress(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 * 
			 */
			public DeletePartyAddress(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/deletePartyAddress");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postJsonAsReturnInfoService(final DeletePartyAddressRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "deletePartyAddressRequest"), DeletePartyAddressRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postXmlAsReturnInfoService(final DeletePartyAddressRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "deletePartyAddressRequest"), DeletePartyAddressRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		/**
		 * 
		 * Get party address
		 * 
		 */
		public static class GetPartyAddress
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private GetPartyAddress(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 */
			public GetPartyAddress(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/getPartyAddress");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return GetPartyAddressResponse
			 */
			public GetPartyAddressResponse postJsonAsGetPartyAddressResponse(final GetPartyAddressRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "getPartyAddressRequest"), GetPartyAddressRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(GetPartyAddressResponse.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return GetPartyAddressResponse
			 */
			public GetPartyAddressResponse postXmlAsGetPartyAddressResponse(final GetPartyAddressRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "getPartyAddressRequest"), GetPartyAddressRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(GetPartyAddressResponse.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		/**
		 * 
		 * Update Party Address
		 * 
		 */
		public static class UpdatePartyAddress
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private UpdatePartyAddress(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 */
			public UpdatePartyAddress(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/updatePartyAddress");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postJsonAsReturnInfoService(final UpdatePartyAddressRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "updatePartyAddressRequest"), UpdatePartyAddressRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postXmlAsReturnInfoService(final UpdatePartyAddressRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "updatePartyAddressRequest"), UpdatePartyAddressRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

	}

	/**
	 * 
	 * Bank Account Service
	 * 
	 */
	public static class BankAccountService
	{

		private final com.sun.jersey.api.client.Client _client;
		private UriBuilder _uriBuilder;
		private final Map<String, Object> _templateAndMatrixParameterValues;

		private BankAccountService(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
				final Map<String, Object> map)
		{
			_client = client;
			_uriBuilder = uriBuilder.clone();
			_templateAndMatrixParameterValues = map;
		}

		/**
		 * Create new instance using existing Client instance, and a base URI and any parameters
		 * 
		 * @param client
		 * @param baseUri
		 */
		public BankAccountService(final com.sun.jersey.api.client.Client client, final URI baseUri)
		{
			_client = client;
			_uriBuilder = UriBuilder.fromUri(baseUri);
			_uriBuilder = _uriBuilder.path("BankAccountService");
			_templateAndMatrixParameterValues = new HashMap<String, Object>();
		}

		/**
		 * 
		 * @return DmsServiceDev_DMS_Service_WebRestV1.BankAccountService.ProcessBankAccount
		 */
		public DmsServiceDev_DMS_Service_WebRestV1.BankAccountService.ProcessBankAccount processBankAccount()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.BankAccountService.ProcessBankAccount(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		/**
		 * 
		 * @return DmsServiceDev_DMS_Service_WebRestV1.BankAccountService.GetBankAccount
		 */
		public DmsServiceDev_DMS_Service_WebRestV1.BankAccountService.GetBankAccount getBankAccount()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.BankAccountService.GetBankAccount(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		/**
		 * 
		 * Get Bank Account
		 * 
		 */
		public static class GetBankAccount
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private GetBankAccount(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 */
			public GetBankAccount(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/getBankAccount");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return BankAccountDetailResponse
			 */
			public BankAccountDetailResponse postJsonAsBankAccountDetailResponse(final AccountInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "accountInput"), AccountInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(BankAccountDetailResponse.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return BankAccountDetailResponse
			 */
			public BankAccountDetailResponse postXmlAsBankAccountDetailResponse(final AccountInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "accountInput"), AccountInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(BankAccountDetailResponse.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		/**
		 * 
		 * Process Bank Account
		 * 
		 */
		public static class ProcessBankAccount
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private ProcessBankAccount(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 */
			public ProcessBankAccount(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/processBankAccount");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postJsonAsReturnInfoService(final AddBankAccountRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "addBankAccountRequest"), AddBankAccountRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postXmlAsReturnInfoService(final AddBankAccountRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "addBankAccountRequest"), AddBankAccountRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

	}

	/**
	 * 
	 * Block Privilege service
	 * 
	 */
	public static class BlocksPrivilegeService
	{

		private final com.sun.jersey.api.client.Client _client;
		private UriBuilder _uriBuilder;
		private final Map<String, Object> _templateAndMatrixParameterValues;

		private BlocksPrivilegeService(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
				final Map<String, Object> map)
		{
			_client = client;
			_uriBuilder = uriBuilder.clone();
			_templateAndMatrixParameterValues = map;
		}

		/**
		 * Create new instance using existing Client instance, and a base URI and any parameters
		 * 
		 * @param client
		 * @param baseUri
		 */
		public BlocksPrivilegeService(final com.sun.jersey.api.client.Client client, final URI baseUri)
		{
			_client = client;
			_uriBuilder = UriBuilder.fromUri(baseUri);
			_uriBuilder = _uriBuilder.path("BlocksPrivilegeService");
			_templateAndMatrixParameterValues = new HashMap<String, Object>();
		}

		/**
		 * 
		 * @return DmsServiceDev_DMS_Service_WebRestV1.BlocksPrivilegeService.GetABOBlockPriv
		 */
		public DmsServiceDev_DMS_Service_WebRestV1.BlocksPrivilegeService.GetABOBlockPriv getABOBlockPriv()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.BlocksPrivilegeService.GetABOBlockPriv(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		/**
		 * 
		 * Get ABO Block Privilege
		 * 
		 */
		public static class GetABOBlockPriv
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private GetABOBlockPriv(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 */
			public GetABOBlockPriv(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/getABOBlockPriv");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return BlockPrivilegeServiceOutput
			 */
			public BlockPrivilegeServiceOutput postJsonAsBlockPrivilegeServiceOutput(final GetBlockPrivDetInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "getBlockPrivDetInput"), GetBlockPrivDetInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(BlockPrivilegeServiceOutput.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return BlockPrivilegeServiceOutput
			 */
			public BlockPrivilegeServiceOutput postXmlAsBlockPrivilegeServiceOutput(final GetBlockPrivDetInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "getBlockPrivDetInput"), GetBlockPrivDetInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(BlockPrivilegeServiceOutput.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

	}

	/**
	 * 
	 * Business Nature Service
	 * 
	 */
	public static class BusinessNatureService
	{

		private final com.sun.jersey.api.client.Client _client;
		private UriBuilder _uriBuilder;
		private final Map<String, Object> _templateAndMatrixParameterValues;

		private BusinessNatureService(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
				final Map<String, Object> map)
		{
			_client = client;
			_uriBuilder = uriBuilder.clone();
			_templateAndMatrixParameterValues = map;
		}

		/**
		 * Create new instance using existing Client instance, and a base URI and any parameters
		 * 
		 * @param client
		 * @param baseUri
		 */
		public BusinessNatureService(final com.sun.jersey.api.client.Client client, final URI baseUri)
		{
			_client = client;
			_uriBuilder = UriBuilder.fromUri(baseUri);
			_uriBuilder = _uriBuilder.path("BusinessNatureService");
			_templateAndMatrixParameterValues = new HashMap<String, Object>();
		}

		/**
		 * 
		 * @return DmsServiceDev_DMS_Service_WebRestV1.BusinessNatureService.UpdateBusinessNature
		 */
		public DmsServiceDev_DMS_Service_WebRestV1.BusinessNatureService.UpdateBusinessNature updateBusinessNature()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.BusinessNatureService.UpdateBusinessNature(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		/**
		 * 
		 * Update Business Nature
		 * 
		 */
		public static class UpdateBusinessNature
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private UpdateBusinessNature(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param cleint
			 * @param baseUri
			 */
			public UpdateBusinessNature(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/updateBusinessNature");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postJsonAsReturnInfoService(final UpdateBusinessNatureRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "updateBusinessNatureRequest"), UpdateBusinessNatureRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postXmlAsReturnInfoService(final UpdateBusinessNatureRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "updateBusinessNatureRequest"), UpdateBusinessNatureRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

	}

	/**
	 * 
	 * Credit Profile Service
	 * 
	 */
	public static class CreditProfileService
	{

		private final com.sun.jersey.api.client.Client _client;
		private UriBuilder _uriBuilder;
		private final Map<String, Object> _templateAndMatrixParameterValues;

		private CreditProfileService(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
				final Map<String, Object> map)
		{
			_client = client;
			_uriBuilder = uriBuilder.clone();
			_templateAndMatrixParameterValues = map;
		}

		/**
		 * Create new instance using existing Client instance, and a base URI and any parameters
		 * 
		 * @param client
		 * @param baseUri
		 */
		public CreditProfileService(final com.sun.jersey.api.client.Client client, final URI baseUri)
		{
			_client = client;
			_uriBuilder = UriBuilder.fromUri(baseUri);
			_uriBuilder = _uriBuilder.path("CreditProfileService");
			_templateAndMatrixParameterValues = new HashMap<String, Object>();
		}

		/**
		 * 
		 * @return DmsServiceDev_DMS_Service_WebRestV1.CreditProfileService.GetPartyCreditProfile
		 */
		public DmsServiceDev_DMS_Service_WebRestV1.CreditProfileService.GetPartyCreditProfile getPartyCreditProfile()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.CreditProfileService.GetPartyCreditProfile(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		/**
		 * 
		 * @return DmsServiceDev_DMS_Service_WebRestV1.CreditProfileService.AddPartyCreditProfile
		 */
		public DmsServiceDev_DMS_Service_WebRestV1.CreditProfileService.AddPartyCreditProfile addPartyCreditProfile()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.CreditProfileService.AddPartyCreditProfile(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		/**
		 * 
		 * @return DmsServiceDev_DMS_Service_WebRestV1.CreditProfileService.UpdatePartyCreditProfile
		 */
		public DmsServiceDev_DMS_Service_WebRestV1.CreditProfileService.UpdatePartyCreditProfile updatePartyCreditProfile()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.CreditProfileService.UpdatePartyCreditProfile(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		/**
		 * 
		 * @return DmsServiceDev_DMS_Service_WebRestV1.CreditProfileService.DeletePartyCreditProfile
		 */
		public DmsServiceDev_DMS_Service_WebRestV1.CreditProfileService.DeletePartyCreditProfile deletePartyCreditProfile()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.CreditProfileService.DeletePartyCreditProfile(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		/**
		 * 
		 * Add Party Credit Profile
		 * 
		 */
		public static class AddPartyCreditProfile
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private AddPartyCreditProfile(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 */
			public AddPartyCreditProfile(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/addPartyCreditProfile");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postJsonAsReturnInfoService(final BaseWebServiceInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "baseWebServiceInput"), BaseWebServiceInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postXmlAsReturnInfoService(final BaseWebServiceInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "baseWebServiceInput"), BaseWebServiceInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		/**
		 * 
		 * Delete Party Credit Profile
		 * 
		 */
		public static class DeletePartyCreditProfile
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private DeletePartyCreditProfile(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 */
			public DeletePartyCreditProfile(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/deletePartyCreditProfile");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postJsonAsReturnInfoService(final BaseWebServiceInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "baseWebServiceInput"), BaseWebServiceInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postXmlAsReturnInfoService(final BaseWebServiceInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "baseWebServiceInput"), BaseWebServiceInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		/**
		 * 
		 * Get Party Credit Profile
		 * 
		 */
		public static class GetPartyCreditProfile
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private GetPartyCreditProfile(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 */
			public GetPartyCreditProfile(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/getPartyCreditProfile");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postJsonAsReturnInfoService(final BaseWebServiceInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "baseWebServiceInput"), BaseWebServiceInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			public ReturnInfoService postXmlAsReturnInfoService(final BaseWebServiceInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "baseWebServiceInput"), BaseWebServiceInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		public static class UpdatePartyCreditProfile
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private UpdatePartyCreditProfile(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public UpdatePartyCreditProfile(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/updatePartyCreditProfile");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public ReturnInfoService postJsonAsReturnInfoService(final BaseWebServiceInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "baseWebServiceInput"), BaseWebServiceInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			public ReturnInfoService postXmlAsReturnInfoService(final BaseWebServiceInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "baseWebServiceInput"), BaseWebServiceInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

	}

	public static class DMSSyncService
	{

		private final com.sun.jersey.api.client.Client _client;
		private UriBuilder _uriBuilder;
		private final Map<String, Object> _templateAndMatrixParameterValues;

		private DMSSyncService(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
				final Map<String, Object> map)
		{
			_client = client;
			_uriBuilder = uriBuilder.clone();
			_templateAndMatrixParameterValues = map;
		}

		/**
		 * Create new instance using existing Client instance, and a base URI and any parameters
		 * 
		 */
		public DMSSyncService(final com.sun.jersey.api.client.Client client, final URI baseUri)
		{
			_client = client;
			_uriBuilder = UriBuilder.fromUri(baseUri);
			_uriBuilder = _uriBuilder.path("DMSSyncService");
			_templateAndMatrixParameterValues = new HashMap<String, Object>();
		}

		public DmsServiceDev_DMS_Service_WebRestV1.DMSSyncService.AboMassSync aboMassSync()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.DMSSyncService.AboMassSync(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public static class AboMassSync
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private AboMassSync(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public AboMassSync(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/aboMassSync");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public ReturnInfoService postJsonAsReturnInfoService(final AboMassSyncRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "aboMassSyncRequest"), AboMassSyncRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			public ReturnInfoService postXmlAsReturnInfoService(final AboMassSyncRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "aboMassSyncRequest"), AboMassSyncRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

	}

	public static class EcommService
	{

		private final com.sun.jersey.api.client.Client _client;
		private UriBuilder _uriBuilder;
		private final Map<String, Object> _templateAndMatrixParameterValues;

		private EcommService(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
				final Map<String, Object> map)
		{
			_client = client;
			_uriBuilder = uriBuilder.clone();
			_templateAndMatrixParameterValues = map;
		}

		/**
		 * Create new instance using existing Client instance, and a base URI and any parameters
		 * 
		 */
		public EcommService(final com.sun.jersey.api.client.Client client, final URI baseUri)
		{
			_client = client;
			_uriBuilder = UriBuilder.fromUri(baseUri);
			_uriBuilder = _uriBuilder.path("EcommService");
			_templateAndMatrixParameterValues = new HashMap<String, Object>();
		}

		public DmsServiceDev_DMS_Service_WebRestV1.EcommService.AddPartyEcomm addPartyEcomm()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.EcommService.AddPartyEcomm(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public DmsServiceDev_DMS_Service_WebRestV1.EcommService.UpdatePartyEcomm updatePartyEcomm()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.EcommService.UpdatePartyEcomm(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public DmsServiceDev_DMS_Service_WebRestV1.EcommService.GetPartyEcomm getPartyEcomm()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.EcommService.GetPartyEcomm(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public DmsServiceDev_DMS_Service_WebRestV1.EcommService.DeletePartyEcomm deletePartyEcomm()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.EcommService.DeletePartyEcomm(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public static class AddPartyEcomm
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private AddPartyEcomm(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public AddPartyEcomm(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/addPartyEcomm");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public ReturnInfoService postJsonAsReturnInfoService(final AddPartyEcommRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "addPartyEcommRequest"), AddPartyEcommRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			public ReturnInfoService postXmlAsReturnInfoService(final AddPartyEcommRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "addPartyEcommRequest"), AddPartyEcommRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		public static class DeletePartyEcomm
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private DeletePartyEcomm(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public DeletePartyEcomm(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/deletePartyEcomm");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public ReturnInfoService postJsonAsReturnInfoService(final DeletePartyEcommRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "deletePartyEcommRequest"), DeletePartyEcommRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			public ReturnInfoService postXmlAsReturnInfoService(final DeletePartyEcommRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "deletePartyEcommRequest"), DeletePartyEcommRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		public static class GetPartyEcomm
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private GetPartyEcomm(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public GetPartyEcomm(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/getPartyEcomm");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public GetPartyEcommResponse postJsonAsGetPartyEcommResponse(final GetPartyEcommRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "getPartyEcommRequest"), GetPartyEcommRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(GetPartyEcommResponse.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			public GetPartyEcommResponse postXmlAsGetPartyEcommResponse(final GetPartyEcommRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "getPartyEcommRequest"), GetPartyEcommRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(GetPartyEcommResponse.class);
			}

			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		public static class UpdatePartyEcomm
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private UpdatePartyEcomm(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public UpdatePartyEcomm(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/updatePartyEcomm");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public ReturnInfoService postJsonAsReturnInfoService(final UpdatePartyEcommRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "updatePartyEcommRequest"), UpdatePartyEcommRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			public ReturnInfoService postXmlAsReturnInfoService(final UpdatePartyEcommRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "updatePartyEcommRequest"), UpdatePartyEcommRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

	}

	public static class HistoryService
	{

		private final com.sun.jersey.api.client.Client _client;
		private UriBuilder _uriBuilder;
		private final Map<String, Object> _templateAndMatrixParameterValues;

		private HistoryService(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
				final Map<String, Object> map)
		{
			_client = client;
			_uriBuilder = uriBuilder.clone();
			_templateAndMatrixParameterValues = map;
		}

		/**
		 * Create new instance using existing Client instance, and a base URI and any parameters
		 * 
		 */
		public HistoryService(final com.sun.jersey.api.client.Client client, final URI baseUri)
		{
			_client = client;
			_uriBuilder = UriBuilder.fromUri(baseUri);
			_uriBuilder = _uriBuilder.path("HistoryService");
			_templateAndMatrixParameterValues = new HashMap<String, Object>();
		}

		public DmsServiceDev_DMS_Service_WebRestV1.HistoryService.GetPartyHistoryData getPartyHistoryData()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.HistoryService.GetPartyHistoryData(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public DmsServiceDev_DMS_Service_WebRestV1.HistoryService.GetAccountHistoryData getAccountHistoryData()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.HistoryService.GetAccountHistoryData(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public static class GetAccountHistoryData
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private GetAccountHistoryData(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public GetAccountHistoryData(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/getAccountHistoryData");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public AccountHistoryResponse postJsonAsAccountHistoryResponse(final AccountHistoryRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "accountHistoryRequest"), AccountHistoryRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(AccountHistoryResponse.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			public AccountHistoryResponse postXmlAsAccountHistoryResponse(final AccountHistoryRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "accountHistoryRequest"), AccountHistoryRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(AccountHistoryResponse.class);
			}

			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		public static class GetPartyHistoryData
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private GetPartyHistoryData(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public GetPartyHistoryData(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/getPartyHistoryData");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public PartyHistoryResponse postJsonAsPartyHistoryResponse(final PartyHistoryRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "partyHistoryRequest"), PartyHistoryRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(PartyHistoryResponse.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			public PartyHistoryResponse postXmlAsPartyHistoryResponse(final PartyHistoryRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "partyHistoryRequest"), PartyHistoryRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(PartyHistoryResponse.class);
			}

			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

	}

	public static class LoadEligibleSponsor
	{

		private final com.sun.jersey.api.client.Client _client;
		private UriBuilder _uriBuilder;
		private final Map<String, Object> _templateAndMatrixParameterValues;

		private LoadEligibleSponsor(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
				final Map<String, Object> map)
		{
			_client = client;
			_uriBuilder = uriBuilder.clone();
			_templateAndMatrixParameterValues = map;
		}

		/**
		 * Create new instance using existing Client instance, and a base URI and any parameters
		 * 
		 */
		public LoadEligibleSponsor(final com.sun.jersey.api.client.Client client, final URI baseUri)
		{
			_client = client;
			_uriBuilder = UriBuilder.fromUri(baseUri);
			_uriBuilder = _uriBuilder.path("LoadEligibleSponsor");
			_templateAndMatrixParameterValues = new HashMap<String, Object>();
		}

		public DmsServiceDev_DMS_Service_WebRestV1.LoadEligibleSponsor.LoadEligibleSponsors loadEligibleSponsors()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.LoadEligibleSponsor.LoadEligibleSponsors(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public static class LoadEligibleSponsors
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private LoadEligibleSponsors(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public LoadEligibleSponsors(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/loadEligibleSponsors");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public ReturnInfoService postJsonAsReturnInfoService(final EligibleSponsorInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "eligibleSponsorInput"), EligibleSponsorInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			public ReturnInfoService postXmlAsReturnInfoService(final EligibleSponsorInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "eligibleSponsorInput"), EligibleSponsorInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

	}

	public static class PartyPreferenceService
	{

		private final com.sun.jersey.api.client.Client _client;
		private UriBuilder _uriBuilder;
		private final Map<String, Object> _templateAndMatrixParameterValues;

		private PartyPreferenceService(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
				final Map<String, Object> map)
		{
			_client = client;
			_uriBuilder = uriBuilder.clone();
			_templateAndMatrixParameterValues = map;
		}

		/**
		 * Create new instance using existing Client instance, and a base URI and any parameters
		 * 
		 */
		public PartyPreferenceService(final com.sun.jersey.api.client.Client client, final URI baseUri)
		{
			_client = client;
			_uriBuilder = UriBuilder.fromUri(baseUri);
			_uriBuilder = _uriBuilder.path("PartyPreferenceService");
			_templateAndMatrixParameterValues = new HashMap<String, Object>();
		}

		public DmsServiceDev_DMS_Service_WebRestV1.PartyPreferenceService.CreatePartyPreference createPartyPreference()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.PartyPreferenceService.CreatePartyPreference(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public DmsServiceDev_DMS_Service_WebRestV1.PartyPreferenceService.UpdatePartyPreference updatePartyPreference()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.PartyPreferenceService.UpdatePartyPreference(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public DmsServiceDev_DMS_Service_WebRestV1.PartyPreferenceService.DeletePartyPreference deletePartyPreference()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.PartyPreferenceService.DeletePartyPreference(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public DmsServiceDev_DMS_Service_WebRestV1.PartyPreferenceService.GetPartyPreferenceList getPartyPreferenceList()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.PartyPreferenceService.GetPartyPreferenceList(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public static class CreatePartyPreference
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private CreatePartyPreference(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public CreatePartyPreference(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/createPartyPreference");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public ReturnInfoService postJsonAsReturnInfoService(final CreatePartyPreferenceRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "createPartyPreferenceRequest"), CreatePartyPreferenceRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			public ReturnInfoService postXmlAsReturnInfoService(final CreatePartyPreferenceRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "createPartyPreferenceRequest"), CreatePartyPreferenceRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		public static class DeletePartyPreference
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private DeletePartyPreference(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public DeletePartyPreference(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/deletePartyPreference");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public ReturnInfoService postJsonAsReturnInfoService(final DeletePartyPreferenceRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "deletePartyPreferenceRequest"), DeletePartyPreferenceRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			public ReturnInfoService postXmlAsReturnInfoService(final DeletePartyPreferenceRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "deletePartyPreferenceRequest"), DeletePartyPreferenceRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		public static class GetPartyPreferenceList
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private GetPartyPreferenceList(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public GetPartyPreferenceList(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/getPartyPreferenceList");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public GetPartyPreferenceResponse postJsonAsGetPartyPreferenceResponse(final GetPartyPrefrenceListRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "getPartyPrefrenceListRequest"), GetPartyPrefrenceListRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(GetPartyPreferenceResponse.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			public GetPartyPreferenceResponse postXmlAsGetPartyPreferenceResponse(final GetPartyPrefrenceListRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "getPartyPrefrenceListRequest"), GetPartyPrefrenceListRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(GetPartyPreferenceResponse.class);
			}

			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		public static class UpdatePartyPreference
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private UpdatePartyPreference(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public UpdatePartyPreference(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/updatePartyPreference");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public ReturnInfoService postJsonAsReturnInfoService(final UpdatePartyPreferenceRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "updatePartyPreferenceRequest"), UpdatePartyPreferenceRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			public ReturnInfoService postXmlAsReturnInfoService(final UpdatePartyPreferenceRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "updatePartyPreferenceRequest"), UpdatePartyPreferenceRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

	}

	public static class PartyService
	{

		private final com.sun.jersey.api.client.Client _client;
		private UriBuilder _uriBuilder;
		private final Map<String, Object> _templateAndMatrixParameterValues;

		private PartyService(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
				final Map<String, Object> map)
		{
			_client = client;
			_uriBuilder = uriBuilder.clone();
			_templateAndMatrixParameterValues = map;
		}

		/**
		 * Create new instance using existing Client instance, and a base URI and any parameters
		 * 
		 */
		public PartyService(final com.sun.jersey.api.client.Client client, final URI baseUri)
		{
			_client = client;
			_uriBuilder = UriBuilder.fromUri(baseUri);
			_uriBuilder = _uriBuilder.path("PartyService");
			_templateAndMatrixParameterValues = new HashMap<String, Object>();
		}

		public DmsServiceDev_DMS_Service_WebRestV1.PartyService.GetPartyProfileAndName getPartyProfileAndName()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.PartyService.GetPartyProfileAndName(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public DmsServiceDev_DMS_Service_WebRestV1.PartyService.AddParty addParty()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.PartyService.AddParty(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public DmsServiceDev_DMS_Service_WebRestV1.PartyService.AddPartyName addPartyName()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.PartyService.AddPartyName(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public DmsServiceDev_DMS_Service_WebRestV1.PartyService.UpdateParty updateParty()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.PartyService.UpdateParty(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public DmsServiceDev_DMS_Service_WebRestV1.PartyService.UpdatePartyName updatePartyName()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.PartyService.UpdatePartyName(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public DmsServiceDev_DMS_Service_WebRestV1.PartyService.DeletePartyName deletePartyName()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.PartyService.DeletePartyName(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public static class AddParty
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private AddParty(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public AddParty(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/addParty");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public AddPartyResponse postJsonAsAddPartyResponse(final AddPartyRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "addPartyRequest"), AddPartyRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(AddPartyResponse.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			public AddPartyResponse postXmlAsAddPartyResponse(final AddPartyRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "addPartyRequest"), AddPartyRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(AddPartyResponse.class);
			}

			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		public static class AddPartyName
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private AddPartyName(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public AddPartyName(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/addPartyName");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public ReturnInfoService postJsonAsReturnInfoService(final AddPartyNameRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "addPartyNameRequest"), AddPartyNameRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			public ReturnInfoService postXmlAsReturnInfoService(final AddPartyNameRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "addPartyNameRequest"), AddPartyNameRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		public static class DeletePartyName
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private DeletePartyName(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public DeletePartyName(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/deletePartyName");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public ReturnInfoService postJsonAsReturnInfoService(final DeletePartyNameRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "deletePartyNameRequest"), DeletePartyNameRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			public ReturnInfoService postXmlAsReturnInfoService(final DeletePartyNameRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "deletePartyNameRequest"), DeletePartyNameRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		public static class GetPartyProfileAndName
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private GetPartyProfileAndName(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public GetPartyProfileAndName(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/getPartyProfileAndName");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public PartyNameDetailResponse postJsonAsPartyNameDetailResponse(final PartyProfileAndNameInputRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "partyProfileAndNameInputRequest"), PartyProfileAndNameInputRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(PartyNameDetailResponse.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			public PartyNameDetailResponse postXmlAsPartyNameDetailResponse(final PartyProfileAndNameInputRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "partyProfileAndNameInputRequest"), PartyProfileAndNameInputRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(PartyNameDetailResponse.class);
			}

			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		public static class UpdateParty
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private UpdateParty(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public UpdateParty(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/updateParty");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public ReturnInfoService postJsonAsReturnInfoService(final UpdatePartyRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "updatePartyRequest"), UpdatePartyRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			public ReturnInfoService postXmlAsReturnInfoService(final UpdatePartyRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "updatePartyRequest"), UpdatePartyRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		public static class UpdatePartyName
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private UpdatePartyName(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public UpdatePartyName(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/updatePartyName");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public ReturnInfoService postJsonAsReturnInfoService(final UpdatePartyNameRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "updatePartyNameRequest"), UpdatePartyNameRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			public ReturnInfoService postXmlAsReturnInfoService(final UpdatePartyNameRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "updatePartyNameRequest"), UpdatePartyNameRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

	}

	public static class PersonalIdService
	{

		private final com.sun.jersey.api.client.Client _client;
		private UriBuilder _uriBuilder;
		private final Map<String, Object> _templateAndMatrixParameterValues;

		private PersonalIdService(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
				final Map<String, Object> map)
		{
			_client = client;
			_uriBuilder = uriBuilder.clone();
			_templateAndMatrixParameterValues = map;
		}

		/**
		 * Create new instance using existing Client instance, and a base URI and any parameters
		 * 
		 */
		public PersonalIdService(final com.sun.jersey.api.client.Client client, final URI baseUri)
		{
			_client = client;
			_uriBuilder = UriBuilder.fromUri(baseUri);
			_uriBuilder = _uriBuilder.path("PersonalIdService");
			_templateAndMatrixParameterValues = new HashMap<String, Object>();
		}

		public DmsServiceDev_DMS_Service_WebRestV1.PersonalIdService.GetPersonalIdList getPersonalIdList()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.PersonalIdService.GetPersonalIdList(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public DmsServiceDev_DMS_Service_WebRestV1.PersonalIdService.AddPersonalId addPersonalId()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.PersonalIdService.AddPersonalId(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public DmsServiceDev_DMS_Service_WebRestV1.PersonalIdService.DeletePersonalId deletePersonalId()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.PersonalIdService.DeletePersonalId(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public DmsServiceDev_DMS_Service_WebRestV1.PersonalIdService.UpdatePersonalId updatePersonalId()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.PersonalIdService.UpdatePersonalId(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public static class AddPersonalId
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private AddPersonalId(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public AddPersonalId(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/addPersonalId");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public ReturnInfoService postJsonAsReturnInfoService(final AddPersonalIdRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "addPersonalIdRequest"), AddPersonalIdRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		public static class DeletePersonalId
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private DeletePersonalId(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public DeletePersonalId(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/deletePersonalId");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public ReturnInfoService postJsonAsReturnInfoService(final DeletePersonalIdRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "deletePersonalIdRequest"), DeletePersonalIdRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		public static class GetPersonalIdList
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private GetPersonalIdList(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public GetPersonalIdList(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/getPersonalIdList");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public PersonalIdDetailsResponse postJsonAsPersonalIdDetailsResponse(final GetPersonalIdInputRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "getPersonalIdInputRequest"), GetPersonalIdInputRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(PersonalIdDetailsResponse.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		public static class UpdatePersonalId
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private UpdatePersonalId(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public UpdatePersonalId(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/updatePersonalId");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public ReturnInfoService postJsonAsReturnInfoService(final UpdatePersonalIdRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "updatePersonalIdRequest"), UpdatePersonalIdRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

	}

	public static class PhoneService
	{

		private final com.sun.jersey.api.client.Client _client;
		private UriBuilder _uriBuilder;
		private final Map<String, Object> _templateAndMatrixParameterValues;

		private PhoneService(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
				final Map<String, Object> map)
		{
			_client = client;
			_uriBuilder = uriBuilder.clone();
			_templateAndMatrixParameterValues = map;
		}

		/**
		 * Create new instance using existing Client instance, and a base URI and any parameters
		 * 
		 */
		public PhoneService(final com.sun.jersey.api.client.Client client, final URI baseUri)
		{
			_client = client;
			_uriBuilder = UriBuilder.fromUri(baseUri);
			_uriBuilder = _uriBuilder.path("PhoneService");
			_templateAndMatrixParameterValues = new HashMap<String, Object>();
		}

		public DmsServiceDev_DMS_Service_WebRestV1.PhoneService.AddPartyPhone addPartyPhone()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.PhoneService.AddPartyPhone(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public DmsServiceDev_DMS_Service_WebRestV1.PhoneService.DeletePartyPhone deletePartyPhone()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.PhoneService.DeletePartyPhone(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public DmsServiceDev_DMS_Service_WebRestV1.PhoneService.UpdatePartyPhone updatePartyPhone()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.PhoneService.UpdatePartyPhone(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public DmsServiceDev_DMS_Service_WebRestV1.PhoneService.GetPartyPhone getPartyPhone()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.PhoneService.GetPartyPhone(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public static class AddPartyPhone
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private AddPartyPhone(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public AddPartyPhone(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/addPartyPhone");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public ReturnInfoService postJsonAsReturnInfoService(final AddPartyPhoneRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "addPartyPhoneRequest"), AddPartyPhoneRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			public ReturnInfoService postXmlAsReturnInfoService(final AddPartyPhoneRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "addPartyPhoneRequest"), AddPartyPhoneRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		public static class DeletePartyPhone
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private DeletePartyPhone(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public DeletePartyPhone(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/deletePartyPhone");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public ReturnInfoService postJsonAsReturnInfoService(final DeletePartyPhoneRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "deletePartyPhoneRequest"), DeletePartyPhoneRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			public ReturnInfoService postXmlAsReturnInfoService(final DeletePartyPhoneRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "deletePartyPhoneRequest"), DeletePartyPhoneRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		public static class GetPartyPhone
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private GetPartyPhone(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public GetPartyPhone(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/getPartyPhone");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public GetPartyPhoneResponse postJsonAsGetPartyPhoneResponse(final GetPartyPhoneRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "getPartyPhoneRequest"), GetPartyPhoneRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(GetPartyPhoneResponse.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			public GetPartyPhoneResponse postXmlAsGetPartyPhoneResponse(final GetPartyPhoneRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "getPartyPhoneRequest"), GetPartyPhoneRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(GetPartyPhoneResponse.class);
			}

			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		public static class UpdatePartyPhone
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private UpdatePartyPhone(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public UpdatePartyPhone(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/updatePartyPhone");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public ReturnInfoService postJsonAsReturnInfoService(final UpdatePartyPhoneRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "updatePartyPhoneRequest"), UpdatePartyPhoneRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			public ReturnInfoService postXmlAsReturnInfoService(final UpdatePartyPhoneRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "updatePartyPhoneRequest"), UpdatePartyPhoneRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

	}

	public static class PrePrintedNumberService
	{

		private final com.sun.jersey.api.client.Client _client;
		private UriBuilder _uriBuilder;
		private final Map<String, Object> _templateAndMatrixParameterValues;

		private PrePrintedNumberService(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
				final Map<String, Object> map)
		{
			_client = client;
			_uriBuilder = uriBuilder.clone();
			_templateAndMatrixParameterValues = map;
		}

		/**
		 * Create new instance using existing Client instance, and a base URI and any parameters
		 * 
		 */
		public PrePrintedNumberService(final com.sun.jersey.api.client.Client client, final URI baseUri)
		{
			_client = client;
			_uriBuilder = UriBuilder.fromUri(baseUri);
			_uriBuilder = _uriBuilder.path("PrePrintedNumberService");
			_templateAndMatrixParameterValues = new HashMap<String, Object>();
		}

		public DmsServiceDev_DMS_Service_WebRestV1.PrePrintedNumberService.ValidatePrePrintedNumber validatePrePrintedNumber()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.PrePrintedNumberService.ValidatePrePrintedNumber(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public static class ValidatePrePrintedNumber
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private ValidatePrePrintedNumber(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public ValidatePrePrintedNumber(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/validatePrePrintedNumber");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public ValidatePrePrntdNmbrResponse postJsonAsValidatePrePrntdNmbrResponse(final ValidatePrePrntdNmbrnput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "validatePrePrntdNmbrnput"), ValidatePrePrntdNmbrnput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ValidatePrePrntdNmbrResponse.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			public ValidatePrePrntdNmbrResponse postXmlAsValidatePrePrntdNmbrResponse(final ValidatePrePrntdNmbrnput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "validatePrePrntdNmbrnput"), ValidatePrePrntdNmbrnput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ValidatePrePrntdNmbrResponse.class);
			}

			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

	}

	public static class PreferenceService
	{

		private final com.sun.jersey.api.client.Client _client;
		private UriBuilder _uriBuilder;
		private final Map<String, Object> _templateAndMatrixParameterValues;

		private PreferenceService(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
				final Map<String, Object> map)
		{
			_client = client;
			_uriBuilder = uriBuilder.clone();
			_templateAndMatrixParameterValues = map;
		}

		/**
		 * Create new instance using existing Client instance, and a base URI and any parameters
		 * 
		 */
		public PreferenceService(final com.sun.jersey.api.client.Client client, final URI baseUri)
		{
			_client = client;
			_uriBuilder = UriBuilder.fromUri(baseUri);
			_uriBuilder = _uriBuilder.path("PreferenceService");
			_templateAndMatrixParameterValues = new HashMap<String, Object>();
		}

		public DmsServiceDev_DMS_Service_WebRestV1.PreferenceService.CreatePreference createPreference()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.PreferenceService.CreatePreference(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public DmsServiceDev_DMS_Service_WebRestV1.PreferenceService.UpdatePreference updatePreference()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.PreferenceService.UpdatePreference(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public DmsServiceDev_DMS_Service_WebRestV1.PreferenceService.GetPreferenceList getPreferenceList()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.PreferenceService.GetPreferenceList(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public static class CreatePreference
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private CreatePreference(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public CreatePreference(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/createPreference");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public ReturnInfoService postJsonAsReturnInfoService(final AddPreferenceRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "addPreferenceRequest"), AddPreferenceRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			public ReturnInfoService postXmlAsReturnInfoService(final AddPreferenceRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "addPreferenceRequest"), AddPreferenceRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		public static class GetPreferenceList
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private GetPreferenceList(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public GetPreferenceList(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/getPreferenceList");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public GetPreferenceResponse postJsonAsGetPreferenceResponse(final GetPrefrenceListRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "getPrefrenceListRequest"), GetPrefrenceListRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(GetPreferenceResponse.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			public GetPreferenceResponse postXmlAsGetPreferenceResponse(final GetPrefrenceListRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "getPrefrenceListRequest"), GetPrefrenceListRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(GetPreferenceResponse.class);
			}

			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		public static class UpdatePreference
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private UpdatePreference(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public UpdatePreference(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/updatePreference");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public ReturnInfoService postJsonAsReturnInfoService(final UpdatePreferenceRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "updatePreferenceRequest"), UpdatePreferenceRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			public ReturnInfoService postXmlAsReturnInfoService(final UpdatePreferenceRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "updatePreferenceRequest"), UpdatePreferenceRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

	}

	public static class PrivacyProfileService
	{

		private final com.sun.jersey.api.client.Client _client;
		private UriBuilder _uriBuilder;
		private final Map<String, Object> _templateAndMatrixParameterValues;

		private PrivacyProfileService(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
				final Map<String, Object> map)
		{
			_client = client;
			_uriBuilder = uriBuilder.clone();
			_templateAndMatrixParameterValues = map;
		}

		/**
		 * Create new instance using existing Client instance, and a base URI and any parameters
		 * 
		 */
		public PrivacyProfileService(final com.sun.jersey.api.client.Client client, final URI baseUri)
		{
			_client = client;
			_uriBuilder = UriBuilder.fromUri(baseUri);
			_uriBuilder = _uriBuilder.path("PrivacyProfileService");
			_templateAndMatrixParameterValues = new HashMap<String, Object>();
		}

		public DmsServiceDev_DMS_Service_WebRestV1.PrivacyProfileService.GetPartyPrivacyProfile getPartyPrivacyProfile()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.PrivacyProfileService.GetPartyPrivacyProfile(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public DmsServiceDev_DMS_Service_WebRestV1.PrivacyProfileService.AddPartyPrivacyProfile addPartyPrivacyProfile()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.PrivacyProfileService.AddPartyPrivacyProfile(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public DmsServiceDev_DMS_Service_WebRestV1.PrivacyProfileService.UpdatePartyPrivacyProfile updatePartyPrivacyProfile()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.PrivacyProfileService.UpdatePartyPrivacyProfile(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public static class AddPartyPrivacyProfile
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private AddPartyPrivacyProfile(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public AddPartyPrivacyProfile(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("addPartyPrivacyProfile");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public ReturnInfoService postJsonAsReturnInfoService(final AddPartyPrivacyProfileRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "addPartyPrivacyProfileRequest"), AddPartyPrivacyProfileRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			public ReturnInfoService postXmlAsReturnInfoService(final AddPartyPrivacyProfileRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "addPartyPrivacyProfileRequest"), AddPartyPrivacyProfileRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		public static class GetPartyPrivacyProfile
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private GetPartyPrivacyProfile(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public GetPartyPrivacyProfile(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/getPartyPrivacyProfile");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public ProfileSearchResponse postJsonAsProfileSearchResponse(final PrivacySearchInputRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "privacySearchInputRequest"), PrivacySearchInputRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ProfileSearchResponse.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			public ProfileSearchResponse postXmlAsProfileSearchResponse(final PrivacySearchInputRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "privacySearchInputRequest"), PrivacySearchInputRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ProfileSearchResponse.class);
			}

			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		public static class UpdatePartyPrivacyProfile
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private UpdatePartyPrivacyProfile(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public UpdatePartyPrivacyProfile(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("updatePartyPrivacyProfile");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public ReturnInfoService postJsonAsReturnInfoService(final UpdatePartyPrivacyProfileRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "updatePartyPrivacyProfileRequest"), UpdatePartyPrivacyProfileRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			public ReturnInfoService postXmlAsReturnInfoService(final UpdatePartyPrivacyProfileRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "updatePartyPrivacyProfileRequest"), UpdatePartyPrivacyProfileRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

	}

	public static class RegistrationService
	{

		private final com.sun.jersey.api.client.Client _client;
		private UriBuilder _uriBuilder;
		private final Map<String, Object> _templateAndMatrixParameterValues;

		private RegistrationService(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
				final Map<String, Object> map)
		{
			_client = client;
			_uriBuilder = uriBuilder.clone();
			_templateAndMatrixParameterValues = map;
		}

		/**
		 * Create new instance using existing Client instance, and a base URI and any parameters
		 * 
		 */
		public RegistrationService(final com.sun.jersey.api.client.Client client, final URI baseUri)
		{
			_client = client;
			_uriBuilder = UriBuilder.fromUri(baseUri);
			_uriBuilder = _uriBuilder.path("RegistrationService");
			_templateAndMatrixParameterValues = new HashMap<String, Object>();
		}

		public DmsServiceDev_DMS_Service_WebRestV1.RegistrationService.ValidatePartyMasterDetails validatePartyMasterDetails()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.RegistrationService.ValidatePartyMasterDetails(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public DmsServiceDev_DMS_Service_WebRestV1.RegistrationService.ValidatePartyNameDetails validatePartyNameDetails()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.RegistrationService.ValidatePartyNameDetails(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public DmsServiceDev_DMS_Service_WebRestV1.RegistrationService.ValidatePartyAddressDetails validatePartyAddressDetails()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.RegistrationService.ValidatePartyAddressDetails(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public DmsServiceDev_DMS_Service_WebRestV1.RegistrationService.ValidatePartyPhoneDetails validatePartyPhoneDetails()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.RegistrationService.ValidatePartyPhoneDetails(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public DmsServiceDev_DMS_Service_WebRestV1.RegistrationService.ValidateBankAccountDetails validateBankAccountDetails()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.RegistrationService.ValidateBankAccountDetails(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public DmsServiceDev_DMS_Service_WebRestV1.RegistrationService.ValidatePartyTaxDetails validatePartyTaxDetails()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.RegistrationService.ValidatePartyTaxDetails(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public DmsServiceDev_DMS_Service_WebRestV1.RegistrationService.CaptureCustDtlsForRegistartion captureCustDtlsForRegistartion()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.RegistrationService.CaptureCustDtlsForRegistartion(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public DmsServiceDev_DMS_Service_WebRestV1.RegistrationService.ValidateCustDtlsForRegistration validateCustDtlsForRegistration()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.RegistrationService.ValidateCustDtlsForRegistration(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public DmsServiceDev_DMS_Service_WebRestV1.RegistrationService.ValidatePartyEcommDetails validatePartyEcommDetails()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.RegistrationService.ValidatePartyEcommDetails(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public DmsServiceDev_DMS_Service_WebRestV1.RegistrationService.ProcessABORegistration processABORegistration()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.RegistrationService.ProcessABORegistration(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		public static class CaptureCustDtlsForRegistartion
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private CaptureCustDtlsForRegistartion(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public CaptureCustDtlsForRegistartion(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/captureCustDtlsForRegistartion");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			public CustomerRegistrationOutput postJsonAsCustomerRegistrationOutput(final CustomerRegistrationInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "customerRegistrationInput"), CustomerRegistrationInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(CustomerRegistrationOutput.class);
			}

			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			public CustomerRegistrationOutput postXmlAsCustomerRegistrationOutput(final CustomerRegistrationInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "customerRegistrationInput"), CustomerRegistrationInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(CustomerRegistrationOutput.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		/**
		 * 
		 * Process ABO Registration
		 * 
		 */
		public static class ProcessABORegistration
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private ProcessABORegistration(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 */
			public ProcessABORegistration(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/processABORegistration");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return CustomerRegistrationOutput
			 */
			public CustomerRegistrationOutput postJsonAsCustomerRegistrationOutput(final CustomerRegistrationInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "customerRegistrationInput"), CustomerRegistrationInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(CustomerRegistrationOutput.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return CustomerRegistrationOutput
			 */
			public CustomerRegistrationOutput postXmlAsCustomerRegistrationOutput(final CustomerRegistrationInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "customerRegistrationInput"), CustomerRegistrationInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(CustomerRegistrationOutput.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		/**
		 * 
		 * Validat eBank Account Details
		 * 
		 */
		public static class ValidateBankAccountDetails
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private ValidateBankAccountDetails(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 */
			public ValidateBankAccountDetails(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/validateBankAccountDetails");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return CustomerRegistrationOutput
			 */
			public CustomerRegistrationOutput postJsonAsCustomerRegistrationOutput(final PartyAccountDetailsInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "partyAccountDetailsInput"), PartyAccountDetailsInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(CustomerRegistrationOutput.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return CustomerRegistrationOutput
			 */
			public CustomerRegistrationOutput postXmlAsCustomerRegistrationOutput(final PartyAccountDetailsInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "partyAccountDetailsInput"), PartyAccountDetailsInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(CustomerRegistrationOutput.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		/**
		 * Validate Customer Details For Registration
		 * 
		 * 
		 */
		public static class ValidateCustDtlsForRegistration
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private ValidateCustDtlsForRegistration(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 */
			public ValidateCustDtlsForRegistration(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/validateCustDtlsForRegistration");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return CustomerRegistrationOutput
			 */
			public CustomerRegistrationOutput postJsonAsCustomerRegistrationOutput(final CustomerRegistrationInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "customerRegistrationInput"), CustomerRegistrationInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(CustomerRegistrationOutput.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return CustomerRegistrationOutput
			 */
			public CustomerRegistrationOutput postXmlAsCustomerRegistrationOutput(final CustomerRegistrationInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "customerRegistrationInput"), CustomerRegistrationInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(CustomerRegistrationOutput.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		/**
		 * 
		 * Validate Party Address Details
		 * 
		 */
		public static class ValidatePartyAddressDetails
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private ValidatePartyAddressDetails(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 */
			public ValidatePartyAddressDetails(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/validatePartyAddressDetails");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return CustomerRegistrationOutput
			 */
			public CustomerRegistrationOutput postJsonAsCustomerRegistrationOutput(final PartyAddressDetailsInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "partyAddressDetailsInput"), PartyAddressDetailsInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(CustomerRegistrationOutput.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return CustomerRegistrationOutput
			 */
			public CustomerRegistrationOutput postXmlAsCustomerRegistrationOutput(final PartyAddressDetailsInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "partyAddressDetailsInput"), PartyAddressDetailsInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(CustomerRegistrationOutput.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		/**
		 * 
		 * ValidatePartyEcommDetails
		 * 
		 */
		public static class ValidatePartyEcommDetails
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private ValidatePartyEcommDetails(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 */
			public ValidatePartyEcommDetails(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/validatePartyEcommDetails");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return CustomerRegistrationOutput
			 */
			public CustomerRegistrationOutput postJsonAsCustomerRegistrationOutput(final PartyEcommDetailsInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "partyEcommDetailsInput"), PartyEcommDetailsInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(CustomerRegistrationOutput.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return CustomerRegistrationOutput
			 */
			public CustomerRegistrationOutput postXmlAsCustomerRegistrationOutput(final PartyEcommDetailsInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "partyEcommDetailsInput"), PartyEcommDetailsInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(CustomerRegistrationOutput.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		/**
		 * 
		 * Validate Party Master Details
		 * 
		 */
		public static class ValidatePartyMasterDetails
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private ValidatePartyMasterDetails(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 */
			public ValidatePartyMasterDetails(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/validatePartyMasterDetails");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return CustomerRegistrationOutput
			 */
			public CustomerRegistrationOutput postJsonAsCustomerRegistrationOutput(final PartyMasterDetailsInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "partyMasterDetailsInput"), PartyMasterDetailsInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(CustomerRegistrationOutput.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return CustomerRegistrationOutput
			 */
			public CustomerRegistrationOutput postXmlAsCustomerRegistrationOutput(final PartyMasterDetailsInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "partyMasterDetailsInput"), PartyMasterDetailsInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(CustomerRegistrationOutput.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		/**
		 * 
		 * Validate Party Name Details
		 * 
		 */
		public static class ValidatePartyNameDetails
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private ValidatePartyNameDetails(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 * 
			 */
			public ValidatePartyNameDetails(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/validatePartyNameDetails");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return CustomerRegistrationOutput
			 */
			public CustomerRegistrationOutput postJsonAsCustomerRegistrationOutput(final PartyNameDetailsInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "partyNameDetailsInput"), PartyNameDetailsInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(CustomerRegistrationOutput.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return
			 */
			public CustomerRegistrationOutput postXmlAsCustomerRegistrationOutput(final PartyNameDetailsInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "partyNameDetailsInput"), PartyNameDetailsInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(CustomerRegistrationOutput.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		/**
		 * 
		 * Validate party phone details
		 * 
		 */
		public static class ValidatePartyPhoneDetails
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private ValidatePartyPhoneDetails(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 */
			public ValidatePartyPhoneDetails(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/validatePartyPhoneDetails");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return CustomerRegistrationOutput
			 */
			public CustomerRegistrationOutput postJsonAsCustomerRegistrationOutput(final PartyPhoneDetailsInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "partyPhoneDetailsInput"), PartyPhoneDetailsInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(CustomerRegistrationOutput.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return CustomerRegistrationOutput
			 */
			public CustomerRegistrationOutput postXmlAsCustomerRegistrationOutput(final PartyPhoneDetailsInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "partyPhoneDetailsInput"), PartyPhoneDetailsInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(CustomerRegistrationOutput.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		/**
		 * 
		 * Validate party tax details
		 * 
		 */
		public static class ValidatePartyTaxDetails
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private ValidatePartyTaxDetails(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 */
			public ValidatePartyTaxDetails(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/validatePartyTaxDetails");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return CustomerRegistrationOutput
			 */
			public CustomerRegistrationOutput postJsonAsCustomerRegistrationOutput(final PartyTaxDetailsInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "partyTaxDetailsInput"), PartyTaxDetailsInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(CustomerRegistrationOutput.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return CustomerRegistrationOutput
			 */
			public CustomerRegistrationOutput postXmlAsCustomerRegistrationOutput(final PartyTaxDetailsInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "partyTaxDetailsInput"), PartyTaxDetailsInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(CustomerRegistrationOutput.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

	}

	/**
	 * 
	 * Renewal service
	 * 
	 * 
	 */
	public static class RenewalService
	{

		private final com.sun.jersey.api.client.Client _client;
		private UriBuilder _uriBuilder;
		private final Map<String, Object> _templateAndMatrixParameterValues;

		private RenewalService(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
				final Map<String, Object> map)
		{
			_client = client;
			_uriBuilder = uriBuilder.clone();
			_templateAndMatrixParameterValues = map;
		}

		/**
		 * Create new instance using existing Client instance, and a base URI and any parameters
		 * 
		 * @param client
		 * @param baseUri
		 */
		public RenewalService(final com.sun.jersey.api.client.Client client, final URI baseUri)
		{
			_client = client;
			_uriBuilder = UriBuilder.fromUri(baseUri);
			_uriBuilder = _uriBuilder.path("RenewalService");
			_templateAndMatrixParameterValues = new HashMap<String, Object>();
		}

		/**
		 * 
		 * @return DmsServiceDev_DMS_Service_WebRestV1.RenewalService.ProcessABORenewal
		 */
		public DmsServiceDev_DMS_Service_WebRestV1.RenewalService.ProcessABORenewal processABORenewal()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.RenewalService.ProcessABORenewal(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		/**
		 * 
		 * Process abo renewal
		 * 
		 */
		public static class ProcessABORenewal
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private ProcessABORenewal(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 */
			public ProcessABORenewal(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/processABORenewal");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postJsonAsReturnInfoService(final BaseWebServiceInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "baseWebServiceInput"), BaseWebServiceInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postXmlAsReturnInfoService(final BaseWebServiceInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "baseWebServiceInput"), BaseWebServiceInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

	}

	/**
	 * 
	 * sponsor service
	 * 
	 */
	public static class SponsorService
	{

		private final com.sun.jersey.api.client.Client _client;
		private UriBuilder _uriBuilder;
		private final Map<String, Object> _templateAndMatrixParameterValues;

		private SponsorService(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
				final Map<String, Object> map)
		{
			_client = client;
			_uriBuilder = uriBuilder.clone();
			_templateAndMatrixParameterValues = map;
		}

		/**
		 * Create new instance using existing Client instance, and a base URI and any parameters
		 * 
		 * @param client
		 * @param baseUri
		 */
		public SponsorService(final com.sun.jersey.api.client.Client client, final URI baseUri)
		{
			_client = client;
			_uriBuilder = UriBuilder.fromUri(baseUri);
			_uriBuilder = _uriBuilder.path("SponsorService");
			_templateAndMatrixParameterValues = new HashMap<String, Object>();
		}

		/**
		 * 
		 * @return DmsServiceDev_DMS_Service_WebRestV1.SponsorService.GetSponsorAboNumber
		 */
		public DmsServiceDev_DMS_Service_WebRestV1.SponsorService.GetSponsorAboNumber getSponsorAboNumber()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.SponsorService.GetSponsorAboNumber(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		/**
		 * 
		 * @return DmsServiceDev_DMS_Service_WebRestV1.SponsorService.GetSponsorList
		 */
		public DmsServiceDev_DMS_Service_WebRestV1.SponsorService.GetSponsorList getSponsorList()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.SponsorService.GetSponsorList(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		/**
		 * 
		 * @return DmsServiceDev_DMS_Service_WebRestV1.SponsorService.UpdateSponsor
		 */
		public DmsServiceDev_DMS_Service_WebRestV1.SponsorService.UpdateSponsor updateSponsor()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.SponsorService.UpdateSponsor(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		/**
		 * 
		 * get sponsor abo number
		 * 
		 */
		public static class GetSponsorAboNumber
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private GetSponsorAboNumber(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 */
			public GetSponsorAboNumber(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/getSponsorAboNumber");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return SponsorSearchResponse
			 */
			public SponsorSearchResponse postJsonAsSponsorSearchResponse(final SponsorSearchInputRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "sponsorSearchInputRequest"), SponsorSearchInputRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(SponsorSearchResponse.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return SponsorSearchResponse
			 */
			public SponsorSearchResponse postXmlAsSponsorSearchResponse(final SponsorSearchInputRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "sponsorSearchInputRequest"), SponsorSearchInputRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(SponsorSearchResponse.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		/**
		 * 
		 * get sponsor list
		 * 
		 */
		public static class GetSponsorList
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private GetSponsorList(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 */
			public GetSponsorList(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/getSponsorList");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return GetSponsorListResponse
			 */
			public GetSponsorListResponse postJsonAsGetSponsorListResponse(final GetSponsorListRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "getSponsorListRequest"), GetSponsorListRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(GetSponsorListResponse.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return GetSponsorListResponse
			 */
			public GetSponsorListResponse postXmlAsGetSponsorListResponse(final GetSponsorListRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "getSponsorListRequest"), GetSponsorListRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(GetSponsorListResponse.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		/**
		 * 
		 * update sponsor
		 * 
		 */
		public static class UpdateSponsor
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private UpdateSponsor(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 */
			public UpdateSponsor(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/updateSponsor");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postJsonAsReturnInfoService(final UpdateSponsorRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "updateSponsorRequest"), UpdateSponsorRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postXmlAsReturnInfoService(final UpdateSponsorRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "updateSponsorRequest"), UpdateSponsorRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

	}

	/**
	 * 
	 * Subscriptionservice
	 * 
	 */
	public static class SubscriptionService
	{

		private final com.sun.jersey.api.client.Client _client;
		private UriBuilder _uriBuilder;
		private final Map<String, Object> _templateAndMatrixParameterValues;

		private SubscriptionService(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
				final Map<String, Object> map)
		{
			_client = client;
			_uriBuilder = uriBuilder.clone();
			_templateAndMatrixParameterValues = map;
		}

		/**
		 * Create new instance using existing Client instance, and a base URI and any parameters
		 * 
		 * @param client
		 * @param baseUri
		 */
		public SubscriptionService(final com.sun.jersey.api.client.Client client, final URI baseUri)
		{
			_client = client;
			_uriBuilder = UriBuilder.fromUri(baseUri);
			_uriBuilder = _uriBuilder.path("SubscriptionService");
			_templateAndMatrixParameterValues = new HashMap<String, Object>();
		}

		/**
		 * 
		 * @return DmsServiceDev_DMS_Service_WebRestV1.SubscriptionService.GetSubscription
		 */
		public DmsServiceDev_DMS_Service_WebRestV1.SubscriptionService.GetSubscription getSubscription()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.SubscriptionService.GetSubscription(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		/**
		 * 
		 * @return DmsServiceDev_DMS_Service_WebRestV1.SubscriptionService.UpdateSubscription
		 */
		public DmsServiceDev_DMS_Service_WebRestV1.SubscriptionService.UpdateSubscription updateSubscription()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.SubscriptionService.UpdateSubscription(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		/**
		 * 
		 * @return DmsServiceDev_DMS_Service_WebRestV1.SubscriptionService.AddSubscription
		 */
		public DmsServiceDev_DMS_Service_WebRestV1.SubscriptionService.AddSubscription addSubscription()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.SubscriptionService.AddSubscription(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		/**
		 * 
		 * To add subscription
		 * 
		 */
		public static class AddSubscription
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private AddSubscription(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 */
			public AddSubscription(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/addSubscription");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postJsonAsReturnInfoService(final AddSubscriptionRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "addSubscriptionRequest"), AddSubscriptionRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postXmlAsReturnInfoService(final AddSubscriptionRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "addSubscriptionRequest"), AddSubscriptionRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		/**
		 * 
		 * to get subscription
		 * 
		 */
		public static class GetSubscription
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private GetSubscription(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 */
			public GetSubscription(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/getSubscription");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return SubscriptionResponse
			 */
			public SubscriptionResponse postJsonAsSubscriptionResponse(final SubscriptionInputRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "subscriptionInputRequest"), SubscriptionInputRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(SubscriptionResponse.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return SubscriptionResponse
			 */
			public SubscriptionResponse postXmlAsSubscriptionResponse(final SubscriptionInputRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "subscriptionInputRequest"), SubscriptionInputRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(SubscriptionResponse.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		/**
		 * 
		 * Update subscription
		 * 
		 */
		public static class UpdateSubscription
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private UpdateSubscription(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 * 
			 */
			public UpdateSubscription(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/updateSubscription");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postJsonAsReturnInfoService(final UpdateSubscriptionRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "updateSubscriptionRequest"), UpdateSubscriptionRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postXmlAsReturnInfoService(final UpdateSubscriptionRequest input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "updateSubscriptionRequest"), UpdateSubscriptionRequest.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

	}

	/**
	 * 
	 * TaxId service
	 * 
	 */
	public static class TaxIdService
	{

		private final com.sun.jersey.api.client.Client _client;
		private UriBuilder _uriBuilder;
		private final Map<String, Object> _templateAndMatrixParameterValues;

		private TaxIdService(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
				final Map<String, Object> map)
		{
			_client = client;
			_uriBuilder = uriBuilder.clone();
			_templateAndMatrixParameterValues = map;
		}

		/**
		 * Create new instance using existing Client instance, and a base URI and any parameters
		 * 
		 * @param client
		 * @param baseUri
		 */
		public TaxIdService(final com.sun.jersey.api.client.Client client, final URI baseUri)
		{
			_client = client;
			_uriBuilder = UriBuilder.fromUri(baseUri);
			_uriBuilder = _uriBuilder.path("TaxIdService");
			_templateAndMatrixParameterValues = new HashMap<String, Object>();
		}

		/**
		 * 
		 * @return DmsServiceDev_DMS_Service_WebRestV1.TaxIdService.GetTaxIdList
		 */
		public DmsServiceDev_DMS_Service_WebRestV1.TaxIdService.GetTaxIdList getTaxIdList()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.TaxIdService.GetTaxIdList(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		/**
		 * 
		 * @return DmsServiceDev_DMS_Service_WebRestV1.TaxIdService.AddTaxId
		 */
		public DmsServiceDev_DMS_Service_WebRestV1.TaxIdService.AddTaxId addTaxId()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.TaxIdService.AddTaxId(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		/**
		 * 
		 * @return DmsServiceDev_DMS_Service_WebRestV1.TaxIdService.UpdateTaxId
		 */
		public DmsServiceDev_DMS_Service_WebRestV1.TaxIdService.UpdateTaxId updateTaxId()
		{
			return new DmsServiceDev_DMS_Service_WebRestV1.TaxIdService.UpdateTaxId(_client,
					_uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
		}

		/**
		 * 
		 * to add tax id
		 * 
		 */
		public static class AddTaxId
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private AddTaxId(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 */
			public AddTaxId(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/addTaxId");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postJsonAsReturnInfoService(final BaseWebServiceInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "baseWebServiceInput"), BaseWebServiceInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postXmlAsReturnInfoService(final BaseWebServiceInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "baseWebServiceInput"), BaseWebServiceInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		/**
		 * 
		 * To get tax id list.
		 * 
		 */
		public static class GetTaxIdList
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private GetTaxIdList(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 * @param client
			 * @param baseUri
			 */
			public GetTaxIdList(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/getTaxIdList");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postJsonAsReturnInfoService(final BaseWebServiceInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "baseWebServiceInput"), BaseWebServiceInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postXmlAsReturnInfoService(final BaseWebServiceInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "baseWebServiceInput"), BaseWebServiceInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

		/**
		 * Update tax id.
		 * 
		 */
		public static class UpdateTaxId
		{

			private final com.sun.jersey.api.client.Client _client;
			private UriBuilder _uriBuilder;
			private final Map<String, Object> _templateAndMatrixParameterValues;

			private UpdateTaxId(final com.sun.jersey.api.client.Client client, final UriBuilder uriBuilder,
					final Map<String, Object> map)
			{
				_client = client;
				_uriBuilder = uriBuilder.clone();
				_templateAndMatrixParameterValues = map;
			}

			/**
			 * Create new instance using existing Client instance, and a base URI and any parameters
			 * 
			 */
			public UpdateTaxId(final com.sun.jersey.api.client.Client client, final URI baseUri)
			{
				_client = client;
				_uriBuilder = UriBuilder.fromUri(baseUri);
				_uriBuilder = _uriBuilder.path("/updateTaxId");
				_templateAndMatrixParameterValues = new HashMap<String, Object>();
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postJsonAsReturnInfoService(final BaseWebServiceInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "baseWebServiceInput"), BaseWebServiceInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postJson(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/json");
				resourceBuilder = resourceBuilder.type("application/json");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

			/**
			 * 
			 * @param input
			 * @return ReturnInfoService
			 */
			public ReturnInfoService postXmlAsReturnInfoService(final BaseWebServiceInput input)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, new JAXBElement(new QName(
						"", "baseWebServiceInput"), BaseWebServiceInput.class, input));
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(ReturnInfoService.class);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final com.sun.jersey.api.client.GenericType<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (response.getStatus() >= 400)
				{
					throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
							response.getClientResponseStatus()).build());
				}
				return response.getEntity(returnType);
			}

			/**
			 * 
			 * @param input
			 * @param returnType
			 * @return
			 */
			public <T> T postXml(final Object input, final Class<T> returnType)
			{
				final UriBuilder localUriBuilder = _uriBuilder.clone();
				final com.sun.jersey.api.client.WebResource resource = _client.resource(localUriBuilder
						.buildFromMap(_templateAndMatrixParameterValues));
				com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
				resourceBuilder = resourceBuilder.accept("application/xml");
				resourceBuilder = resourceBuilder.type("application/xml");
				com.sun.jersey.api.client.ClientResponse response;
				response = resourceBuilder.method("POST", com.sun.jersey.api.client.ClientResponse.class, input);
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					if (response.getStatus() >= 400)
					{
						throw new DmsServiceDev_DMS_Service_WebRestV1.WebApplicationExceptionMessage(Response.status(
								response.getClientResponseStatus()).build());
					}
				}
				if (!com.sun.jersey.api.client.ClientResponse.class.isAssignableFrom(returnType))
				{
					return response.getEntity(returnType);
				}
				else
				{
					return returnType.cast(response);
				}
			}

		}

	}


	/**
	 * Workaround for JAX_RS_SPEC-312
	 * 
	 */
	private static class WebApplicationExceptionMessage extends WebApplicationException
	{


		private WebApplicationExceptionMessage(final Response response)
		{
			super(response);
		}

		/**
		 * Workaround for JAX_RS_SPEC-312
		 * 
		 */
		@Override
		public String getMessage()
		{
			final Response response = getResponse();
			final Response.Status status = Response.Status.fromStatusCode(response.getStatus());
			if (status != null)
			{
				return (response.getStatus() + (" " + status.getReasonPhrase()));
			}
			else
			{
				return Integer.toString(response.getStatus());
			}
		}

		@Override
		public String toString()
		{
			final String s = "javax.ws.rs.WebApplicationException";
			final String message = getLocalizedMessage();
			return (s + (": " + message));
		}

	}

}
