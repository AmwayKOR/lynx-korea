package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import com.amway.core.dms.data.PrePrintedNumberRequestData;
import com.amway.integration.dms.services.ValidatePrePrntdNmbrnput;


/**
 * Populator implementation for {@link PrePrintedNumberRequestData} as source and {@link ValidatePrePrntdNmbrnput} as
 * target type.
 */
public class DmsPrePrintedNumberInputPopulator implements Populator<PrePrintedNumberRequestData, ValidatePrePrntdNmbrnput>
{
	private BaseStoreService baseStoreService;

	@Override
	public void populate(final PrePrintedNumberRequestData source, final ValidatePrePrntdNmbrnput target)
			throws ConversionException
	{
		final BaseStoreModel currentStore = getBaseStoreService().getCurrentBaseStore();

		target.setPrePrintedNum(source.getPrePrintedNum());
		target.setSalesPlanAff(currentStore.getAffiliateNumber());
		target.setCntryCd(source.getCntryCd());
	}

	/**
	 * @return baseStoreService
	 */
	public BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	/**
	 * @param baseStoreService the baseStoreService to set
	 */
	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

}
