/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.dms.data.CreateBalanceRequestData;
import com.amway.integration.dms.services.AccountCreateBalanceRequest;


/**
 * Populator implementation for {@link CreateBalanceRequestData} as source and {@link AccountCreateBalanceRequest} as
 * target type.
 */
public class DmsCreateAccountBalanceInputPopulator extends AbstractDmsPopulator
		implements Populator<CreateBalanceRequestData, AccountCreateBalanceRequest>
{
	@Override
	public void populate(final CreateBalanceRequestData source, final AccountCreateBalanceRequest target)
			throws ConversionException
	{
		target.setBalanceAmount(source.getBalanceAmount());
		target.setBalanceTypeCd(source.getBalanceTypeCd());
		target.setCurrencyCd(source.getCurrencyCd());
		target.setBalanceAmount(source.getBalanceAmount());
		target.setTxSourceCd(source.getTxSourceCd());
		target.setSourcRefNum(source.getSourcRefNum());
		target.setTxTypeCd(source.getTxTypeCd());
		target.setSalesPlanAff(source.getSalesPlanAff());
		target.setAboNum(source.getAboNum());
		target.setBalanceTypeCd(source.getBalanceTypeCd());
		target.setClientCntryCd(source.getClientCntryCd());

	}
}
