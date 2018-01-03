/**
 *
 */
package com.amway.apac.product.services.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateIfSingleResult;
import static java.lang.String.format;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.impl.DefaultProductService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.amway.apac.core.enums.PaymentType;
import com.amway.apac.core.model.AmwayPaymentOptionModel;
import com.amway.apac.core.product.daos.AmwayApacProductDao;
import com.amway.apac.product.services.AmwayApacProductService;


/**
 * Implementation for {@link AmwayApacProductService}
 */
public class AmwayApacProductServiceImpl extends DefaultProductService implements AmwayApacProductService
{
	private AmwayApacProductDao amwayApacProductDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AmwayPaymentOptionModel getAllPaymentOptionForOmsCode(final String omsCode, final CatalogVersionModel catalogVersion)
	{
		ServicesUtil.validateParameterNotNullStandardMessage("omsCode", omsCode);
		ServicesUtil.validateParameterNotNullStandardMessage("catalogVersion", catalogVersion);

		final List<AmwayPaymentOptionModel> paymentOptions = amwayApacProductDao.getAllAmwayPaymentOptionFromOmsCode(omsCode,
				catalogVersion);

		validateIfSingleResult(paymentOptions, format("omsCode '%s' not found!", omsCode),
				format("omsCode '%s' is not unique, %d products found!", omsCode, Integer.valueOf(0)));

		return paymentOptions.iterator().next();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AmwayPaymentOptionModel getPaymentOptionForOmsCode(final String omsCode, final CatalogVersionModel catalogVersion)
	{
		ServicesUtil.validateParameterNotNullStandardMessage("omsCode", omsCode);
		ServicesUtil.validateParameterNotNullStandardMessage("catalogVersion", catalogVersion);

		final List<AmwayPaymentOptionModel> paymentOptions = amwayApacProductDao.getAmwayPaymentOptionFromOmsCode(omsCode,
				catalogVersion);

		validateIfSingleResult(paymentOptions, format("omsCode '%s' not found!", omsCode),
				format("omsCode '%s' is not unique, %d products found!", omsCode, Integer.valueOf(0)));

		return paymentOptions.get(0);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean checkIfPIFIsActive(final ProductModel productModel)
	{
		boolean isActive = Boolean.FALSE.booleanValue();
		if (CollectionUtils.isNotEmpty(productModel.getPaymentOption()))
		{
			for (final AmwayPaymentOptionModel paymentOption : productModel.getPaymentOption())
			{
				if ((paymentOption.getActive().booleanValue()) && (PaymentType.PIF.equals(paymentOption.getType()))
						&& (isPaymentOptionCurrentlyAvailable(paymentOption)))
				{
					isActive = Boolean.TRUE.booleanValue();
				}
			}
		}
		return isActive;
	}

	/**
	 * Checks if the payment option is currently available i.e. if start and end date are present for the payment option
	 * then current date should lie in start and end date.
	 *
	 * @param paymentOption
	 * @return boolean for payment option availability
	 */
	private boolean isPaymentOptionCurrentlyAvailable(final AmwayPaymentOptionModel paymentOption)
	{
		final Date currentDate = new Date();
		return (Objects.isNull(paymentOption.getStartDate()) && Objects.isNull(paymentOption.getEndDate()))
				|| (currentDate.after(paymentOption.getStartDate()) && currentDate.before(paymentOption.getEndDate()));
	}

	/**
	 * @return the amwayApacProductDao
	 */
	public AmwayApacProductDao getAmwayApacProductDao()
	{
		return amwayApacProductDao;
	}

	/**
	 * @param amwayApacProductDao
	 *           the amwayApacProductDao to set
	 */
	public void setAmwayApacProductDao(final AmwayApacProductDao amwayApacProductDao)
	{
		this.amwayApacProductDao = amwayApacProductDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.apac.product.services.AmwayApacProductService#validateOmsCode(java.lang.String[], int[])
	 */
	@Override
	public boolean validateOmsCode(final String[] splitOmsCode, final int[] omsparams)
	{
		return (null != splitOmsCode) && (splitOmsCode.length == omsparams[0])
				&& (StringUtils.isNotBlank(splitOmsCode[omsparams[1]])) && (StringUtils.isNotBlank(splitOmsCode[omsparams[2]]));
	}
}
