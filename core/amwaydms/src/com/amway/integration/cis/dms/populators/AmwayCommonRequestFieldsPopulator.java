package com.amway.integration.cis.dms.populators;

import de.hybris.platform.commerceservices.enums.SalesApplication;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;


import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.amway.core.data.CommonRequestFieldsData;
import com.amway.core.service.AmwayAccountCommerceService;
import com.amway.integration.dms.services.BaseWebServiceInput;


/**
 * Populator to populate common request fields to magic services.
 */
public class AmwayCommonRequestFieldsPopulator implements Populator<CommonRequestFieldsData, BaseWebServiceInput>
{
	private AmwayAccountCommerceService amwayAccountCommerceService;
	private UserService userService;
	private SessionService sessionService;
	private static final Logger LOG = Logger.getLogger(AmwayCommonRequestFieldsPopulator.class);
	private static String HYBRISCOCKPIT = "HybrisCockpit";
	private static String HYBRISWEBSITE = "HybrisWebsite";


	@Override
	public void populate(final CommonRequestFieldsData source, final BaseWebServiceInput target) throws ConversionException
	{
		//Field 1 :: ClientIpAddress
		final String clinetIPAddress = sessionService.getAttribute("clientIpAddress");
		if (clinetIPAddress != null)
		{

			target.setClientIpAddress(clinetIPAddress);
		}
		else
		{
			//code for setting some default ip if sessionService.getAttribute("clientIpAddress") is empty
			target.setClientIpAddress(getIpAddress());

		}
		//Field 2 :: ClientCntryCd -- use lynx populator be careful with chron job context

		//Field 3 :: LoggedInAccountId
		if (StringUtils.isNotBlank(source.getAboNum()))
		{
			target.setLoggedInAccountId(source.getAboNum());
		}
		else
		{
			target.setLoggedInAccountId(amwayAccountCommerceService.getCurrentAccountNumber());
		}

		//Field 4 :: ClientApplicationId
		//Field 5 :: LoggedInPartyId
		//Field 6 :: LoggedInCustomerServiceId
		//Field 7 :: ClientRoleList
		target.setClientApplicationId(HYBRISWEBSITE);
		target.setClientRoleList("System");
		final SalesApplication salesApplication = (SalesApplication) JaloSession.getCurrentSession().getAttribute("currentChannel");
		final UserModel user = userService.getCurrentUser();
		if (SalesApplication.WEB.equals(salesApplication))
		{
			if (!userService.isAnonymousUser(user) && user instanceof CustomerModel)
			{
				//LoggedInPartyId
				final CustomerModel customer = (CustomerModel) user;
				target.setLoggedInPartyId(customer.getCustomerID());
			}

			// condition when using assisted service
			final UserModel csUser = sessionService.getAttribute("selectedCSCustomer");
			if (csUser != null)
			{
				//LoggedInCustomerServiceId
				target.setLoggedInCustomerServiceId(csUser.getUid());
			}
		}
		if (SalesApplication.CALLCENTER.equals(salesApplication))
		{
			//ClientApplicationId
			target.setClientApplicationId(HYBRISCOCKPIT);

			//LoggedInPartyId
			final CustomerModel customer = sessionService.getAttribute("selectedCSCustomer");
			if (customer != null)
			{
				target.setLoggedInPartyId(customer.getCustomerID());
			}

			//ClientRoleList
			target.setClientRoleList("Admin");

			//LoggedInCustomerServiceId
			target.setLoggedInCustomerServiceId(user.getUid());
		}
	}

	protected String getIpAddress()
	{
		Enumeration<NetworkInterface> n;
		try
		{
			n = NetworkInterface.getNetworkInterfaces();
			for (; n.hasMoreElements(); )
			{
				final NetworkInterface e = n.nextElement();
				final Enumeration<InetAddress> a = e.getInetAddresses();
				for (; a.hasMoreElements(); )
				{
					final InetAddress address = a.nextElement();
					if (isValidPublicIp(address))
					{
						LOG.info("Server address identified as " + address.getHostAddress());
						return address.getHostAddress();
					}
				}
			}
		}
		catch (final Exception e)
		{
			LOG.warn("Exception during identifying IP address", e);
		}
		return "";
	}

	private boolean isValidPublicIp(final InetAddress address)
	{
		return !(address.isAnyLocalAddress() || address.isLinkLocalAddress() || address.isLoopbackAddress() || address
				.isMulticastAddress());
	}

	/**
	 * @return the sessionService
	 */
	public SessionService getSessionService()
	{
		return sessionService;
	}

	/**
	 * @param sessionService the sessionService to set
	 */
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	/**
	 * @return the amwayAccountCommerceService
	 */
	public AmwayAccountCommerceService getAmwayAccountCommerceService()
	{
		return amwayAccountCommerceService;
	}

	/**
	 * @param amwayAccountCommerceService the amwayAccountCommerceService to set
	 */
	public void setAmwayAccountCommerceService(final AmwayAccountCommerceService amwayAccountCommerceService)
	{
		this.amwayAccountCommerceService = amwayAccountCommerceService;
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService()
	{
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}
}
