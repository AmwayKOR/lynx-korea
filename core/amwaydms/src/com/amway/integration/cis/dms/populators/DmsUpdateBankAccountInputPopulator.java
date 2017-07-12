/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.dms.data.ProcessBankAccountRequestData;
import com.amway.integration.dms.services.AddBankAccountRequest;



/**
 * Populator implementation for {@link ProcessBankAccountRequestData} as source and {@link AddBankAccountRequest} as
 * target type.
 */
public class DmsUpdateBankAccountInputPopulator extends AbstractDmsPopulator
		implements Populator<ProcessBankAccountRequestData, AddBankAccountRequest>
{


	@Override
	public void populate(final ProcessBankAccountRequestData source, final AddBankAccountRequest target) throws ConversionException
	{
		target.setAboNum(source.getAboNum());
		target.setSalesPlanAff(source.getSalesPlanAff());
		target.setPartyId(source.getPartyId());
		target.setIssuingBankId(source.getIssuingBankId());
		target.setBankBranchCode(source.getBankBranchCode());
		target.setBankAcctNum(source.getBankAcctNum());
		target.setStatusFlg(source.getStatusFlg());
		target.setEffectiveStartDate(formatInputDate(source.getEffectiveStartDate(), DMSDATEPATTERN));
		target.setCurrencyCd(source.getCurrencyCd());
		for (final String accUseCode : source.getAccountUseCodes())
		{
			target.getAccountUseCodes().add(accUseCode);
		}
		target.setBankAcctHolderName(source.getBankAcctHolderName());
		target.setBankAcctTypeCd(source.getBankAcctTypeCd());
	}


}
