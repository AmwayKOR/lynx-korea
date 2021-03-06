package com.amway.integration.cis.dms.utils;

import static com.amway.integration.cis.dms.constants.AmwayDMSConstants.IMPERSONATION_FLOW_SESSION_ATTR;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;

import com.amway.core.data.CommonRequestFieldsData;
import com.amway.core.model.AmwayAccountModel;
import com.amway.core.service.AmwayAccountCommerceService;
import com.hybris.commons.client.RestCallBuilder;
import com.hybris.commons.client.RestCallDecorator;


/**
 * Decorator adds specific headers to make calls via API Gateway possible.
 */
public class AmwayMasheryAuthDecorator implements RestCallDecorator
{
	private static final String X_REQUEST_ID = "X-Request-Id";
	private static final String X_MASHERY_OAUTH_CLIENT_ID = "X-Mashery-Oauth-Client-Id";
	private static final String X_MASHERY_OAUTH_USER_CONTEXT = "X-Mashery-Oauth-User-Context";
	private static final String X_MASHERY_OAUTH_SCOPE = "X-Mashery-Oauth-Scope";

	private BaseStoreService baseStoreService;

	private AmwayAccountCommerceService accountCommerceService;

	private UserService userService;

	private SessionService sessionService;

	private String clientId;
	private String scope;

	@Override
	public RestCallBuilder decorate(RestCallBuilder builder)
	{
		final String salesPlanAff;
		final String aboCode;
		final String partyId;
		CommonRequestFieldsData impersonationData = getSessionService().getAttribute(IMPERSONATION_FLOW_SESSION_ATTR);
		if (impersonationData == null)
		{
			final CustomerModel currentUser = (CustomerModel) userService.getCurrentUser();
			if (currentUser == null)
			{
				return builder;
			}
			AmwayAccountModel amwayAccountModel = getAccountCommerceService().getCurrentAccount();
			if (amwayAccountModel == null)
			{
				if (!CollectionUtils.isEmpty(currentUser.getAccounts()))
				{
					amwayAccountModel = currentUser.getAccounts().iterator().next();
				}
				if (amwayAccountModel == null)
				{
					return builder;
				}
			}
			BaseStoreModel currentBaseStore = getBaseStoreService().getCurrentBaseStore();
			if (currentBaseStore == null)
			{
				return builder;
			}
			salesPlanAff = currentBaseStore.getAffiliateNumber();
			aboCode = amwayAccountModel.getCode();
			partyId = currentUser.getCustomerID();
		}
		else
		{
			salesPlanAff = impersonationData.getSalesPlanAff();
			aboCode = impersonationData.getAboNum();
			partyId = impersonationData.getPartyId();
		}

		final String localScope = String.format(scope, salesPlanAff, aboCode, partyId);

		builder.header(X_REQUEST_ID, UUID.randomUUID().toString());
		builder.header(X_MASHERY_OAUTH_CLIENT_ID, clientId);
		builder.header(X_MASHERY_OAUTH_USER_CONTEXT, partyId);
		builder.header(X_MASHERY_OAUTH_SCOPE, localScope);
		return builder;
	}

	public String getClientId()
	{
		return clientId;
	}

	public void setClientId(String clientId)
	{
		this.clientId = clientId;
	}

	public String getScope()
	{
		return scope;
	}

	public void setScope(String scope)
	{
		this.scope = scope;
	}

	public BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	public void setBaseStoreService(BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

	public AmwayAccountCommerceService getAccountCommerceService()
	{
		return accountCommerceService;
	}

	public void setAccountCommerceService(AmwayAccountCommerceService accountCommerceService)
	{
		this.accountCommerceService = accountCommerceService;
	}

	public UserService getUserService()
	{
		return userService;
	}

	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	public SessionService getSessionService()
	{
		return sessionService;
	}

	public void setSessionService(SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

}
