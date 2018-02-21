package com.amway.core.cart.strategy.impl;

import de.hybris.platform.commerceservices.strategies.impl.DefaultCartValidationStrategy;
import de.hybris.platform.core.model.order.CartEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.stock.exception.InsufficientStockLevelException;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.amway.core.account.restrictions.impl.AccountExpirationRestriction;
import com.amway.core.account.service.AmwayAccountService;
import com.amway.core.service.AmwayAccountCommerceService;
import com.amway.core.stock.service.AmwayCommerceStockService;



/**
 * Default Implementation for Amway Cart Validation
 */
public class DefaultAmwayCartValidationStrategy extends DefaultCartValidationStrategy
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayCartValidationStrategy.class);
	private AmwayAccountCommerceService amwayAccountCommerceService;
	private AmwayAccountService amwayAccountService;
	private AccountExpirationRestriction accountExpirationRestriction;
	private AmwayCommerceStockService amwayCommerceStockService;

	@Resource(name = "sessionService")
	private SessionService sessionService;

	@Override
	protected void validateDelivery(final CartModel cartModel)
	{
		return;
	}

	@Override
	protected Long getStockLevel(final CartEntryModel cartEntryModel)
	{
		return super.getStockLevel(cartEntryModel);
	}


	/**
	 * @return amwayAccountCommerceService
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
	 * @return accountExpirationRestriction
	 */
	public AccountExpirationRestriction getAccountExpirationRestriction()
	{
		return accountExpirationRestriction;
	}

	/**
	 * @param accountExpirationRestriction the accountExpirationRestriction to set
	 */
	public void setAccountExpirationRestriction(final AccountExpirationRestriction accountExpirationRestriction)
	{
		this.accountExpirationRestriction = accountExpirationRestriction;
	}

	/**
	 * @return amwayCommerceStockService
	 */
	public AmwayCommerceStockService getAmwayCommerceStockService()
	{
		return amwayCommerceStockService;
	}

	/**
	 * @param amwayCommerceStockService the amwayCommerceStockService to set
	 */
	public void setAmwayCommerceStockService(final AmwayCommerceStockService amwayCommerceStockService)
	{
		this.amwayCommerceStockService = amwayCommerceStockService;
	}

	/**
	 * @return amwayAccountService
	 */
	public AmwayAccountService getAmwayAccountService()
	{
		return amwayAccountService;
	}

	/**
	 * @param amwayAccountService the amwayAccountService to set
	 */
	public void setAmwayAccountService(final AmwayAccountService amwayAccountService)
	{
		this.amwayAccountService = amwayAccountService;
	}
}
