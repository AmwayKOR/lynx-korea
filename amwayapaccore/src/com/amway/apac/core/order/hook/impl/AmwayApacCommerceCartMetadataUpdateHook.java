package com.amway.apac.core.order.hook.impl;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SalesApplication;
import de.hybris.platform.commerceservices.order.hook.CommerceCartMetadataUpdateMethodHook;
import de.hybris.platform.commerceservices.service.data.CommerceCartMetadataParameter;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.site.BaseSiteService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.enums.OrderType;
import com.amway.apac.serviceability.services.AmwayApacWarehouseServiceabilityService;
import com.amway.core.model.AmwayAccountModel;
import com.amway.core.service.AmwayAccountCommerceService;


/**
 * @author navishsharma
 *
 *         Responsible for populating additional data into CommerceCartMetadataParamter for updating the cart
 */
public class AmwayApacCommerceCartMetadataUpdateHook implements CommerceCartMetadataUpdateMethodHook {

	private BaseSiteService baseSiteService;

	private AmwayAccountCommerceService amwayAccountCommerceService;

	private AmwayApacWarehouseServiceabilityService amwayApacWarehouseServiceabilityService;

	private SessionService sessionService;

	/**
	 * Identifies and populates warehouseCode,deliveryMode,deliveryAddresss in the {@link CommerceCartMetadataParameter}
	 * before metadata update. It checks for the salesApplication channel in the session and updates the above fields in
	 * the parameter only if the session salesApplication channel is other than {@link SalesApplication.POS}
	 */
	@Override
	public void beforeMetadataUpdate(final CommerceCartMetadataParameter parameter) {

		final AmwayAccountModel currentAccount = getAmwayAccountCommerceService().getCurrentAccount();
		final SalesApplication salesApplication = getSalesApplicationFromSession();

		if (null != currentAccount){
			parameter.setVolumeAmwayAccount(currentAccount.getCode());

			if (null == salesApplication || !salesApplication.equals(SalesApplication.POS))
			{
				final AddressModel registeredAddress = currentAccount.getRegisteredAddress();
				final BaseSiteModel currentBaseSite = getBaseSiteService().getCurrentBaseSite();
				parameter.setDeliveryAddress(registeredAddress);
				if ((null != registeredAddress)
						&& (StringUtils.isNotBlank(registeredAddress.getPostalcode()) && (null != currentBaseSite))) {
					final WarehouseModel warehouse = amwayApacWarehouseServiceabilityService
							.getServiceableWareHouse(registeredAddress.getPostalcode(), currentBaseSite);
					parameter.setWarehouseCode(warehouse.getCode());
				}
				parameter.setOrderType(OrderType.NORMAL_ORDER);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.commerceservices.order.hook.
	 * CommerceCartMetadataUpdateMethodHook#afterMetadataUpdate(de.hybris.
	 * platform.commerceservices.service.data.CommerceCartMetadataParameter)
	 */
	@Override
	public void afterMetadataUpdate(final CommerceCartMetadataParameter parameter) {
		// empty
	}

	/**
	 * Loads the sales application channel from the session.
	 *
	 * @return SalesApplication - The salesApplicationChannel set in the session
	 */
	private SalesApplication getSalesApplicationFromSession() {
		SalesApplication salesApplication = null;
		if (sessionService.hasCurrentSession()) {
			salesApplication = sessionService.getAttribute("currentChannel");
		}
		return salesApplication;
	}

	/**
	 * @return the baseSiteService
	 */
	public BaseSiteService getBaseSiteService() {
		return baseSiteService;
	}

	/**
	 * @param baseSiteService
	 *            the baseSiteService to set
	 */
	@Required
	public void setBaseSiteService(final BaseSiteService baseSiteService) {
		this.baseSiteService = baseSiteService;
	}

	/**
	 * @return the amwayAccountCommerceService
	 */
	public AmwayAccountCommerceService getAmwayAccountCommerceService() {
		return amwayAccountCommerceService;
	}

	/**
	 * @param amwayAccountCommerceService
	 *            the amwayAccountCommerceService to set
	 */
	@Required
	public void setAmwayAccountCommerceService(final AmwayAccountCommerceService amwayAccountCommerceService) {
		this.amwayAccountCommerceService = amwayAccountCommerceService;
	}

	/**
	 * @return the amwayApacWarehouseServiceabilityService
	 */
	public AmwayApacWarehouseServiceabilityService getAmwayApacWarehouseServiceabilityService() {
		return amwayApacWarehouseServiceabilityService;
	}

	/**
	 * @param amwayApacWarehouseServiceabilityService
	 *            the amwayApacWarehouseServiceabilityService to set
	 */
	@Required
	public void setAmwayApacWarehouseServiceabilityService(
			final AmwayApacWarehouseServiceabilityService amwayApacWarehouseServiceabilityService) {
		this.amwayApacWarehouseServiceabilityService = amwayApacWarehouseServiceabilityService;
	}

	/**
	 * @return the sessionService
	 */
	public SessionService getSessionService() {
		return sessionService;
	}

	/**
	 * @param sessionService
	 *            the sessionService to set
	 */
	@Required
	public void setSessionService(final SessionService sessionService) {
		this.sessionService = sessionService;
	}

}
