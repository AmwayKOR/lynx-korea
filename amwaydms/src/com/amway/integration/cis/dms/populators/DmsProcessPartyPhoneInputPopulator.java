package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.dms.data.PartyPhoneDetailsRequestData;
import com.amway.core.dms.data.PhoneMasterRequestData;
import com.amway.core.dms.data.UsageRequestData;
import com.amway.integration.dms.services.PhoneData;
import com.amway.integration.dms.services.ProcessPartyPhoneRequest;


/**
 * Populator implementation for {@link PartyPhoneDetailsRequestData} as source and {@link ProcessPartyPhoneRequest} as
 * target type.
 */
public class DmsProcessPartyPhoneInputPopulator implements Populator<PartyPhoneDetailsRequestData, ProcessPartyPhoneRequest>
{

	@Override
	public void populate(final PartyPhoneDetailsRequestData source, final ProcessPartyPhoneRequest target)
			throws ConversionException
	{

		target.setAboNum(source.getAboNum());
		target.setPartyId(source.getPartyId());
		target.setSalesPlanAff(source.getSalesPlanAff());

		for (final PhoneMasterRequestData phoneMasterRequestData : source.getPhoneMasterListData())
		{
			final PhoneData phoneData = new PhoneData();
			phoneData.setCntryCd(phoneMasterRequestData.getCntryCd());
			phoneData.setContactPointName(phoneMasterRequestData.getContactPointName());
			phoneData.setContactPointTypeCd(phoneMasterRequestData.getContactPointTypeCd());
			phoneData.setPhoneAreaCd(phoneMasterRequestData.getPhoneAreaCd());
			phoneData.setPhoneCntryCd(phoneMasterRequestData.getPhoneCntryCd());

			phoneData.setPhoneExtNum(phoneMasterRequestData.getPhoneExtNum());
			phoneData.setPhoneLocalNum(phoneMasterRequestData.getPhoneLocalNum());

			for (final UsageRequestData usageData : phoneMasterRequestData.getUsageData())
			{
				phoneData.setPrimaryFlag(usageData.getPrimaryFlag());
			}
			target.getPhoneDataList().add(phoneData);
		}
	}
}
