/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.amway.core.dms.data.PartyPreferenceData;
import com.amway.core.dms.data.PartyPreferenceListData;
import com.amway.core.dms.data.PartyPreferenceResponse;
import com.amway.integration.dms.services.GetPartyPreferenceResponse;
import com.amway.integration.dms.services.PrefAffPartyData;
import com.amway.integration.dms.services.PreferenceList;


/**
 * Populator implementation for {@link GetPartyPreferenceResponse} as source and {@link PartyPreferenceResponse} as
 * target type.
 */
public class DmsGetPartyPreferenceOutputPopulator extends AbstractDmsPopulator implements
		Populator<GetPartyPreferenceResponse, PartyPreferenceResponse>
{
	@Override
	public void populate(final GetPartyPreferenceResponse source, final PartyPreferenceResponse target) throws ConversionException
	{
		final List<PrefAffPartyData> prefAffPartyDataList = source.getPrefAffPartyData();
		final List<PartyPreferenceData> partyPreferenceDataList = new ArrayList();
		for (final PrefAffPartyData prefAffPartyData : prefAffPartyDataList)
		{
			final PartyPreferenceData partyPreferenceData = new PartyPreferenceData();
			partyPreferenceData.setAboNum((prefAffPartyData.getAboNo()));
			if (StringUtils.equalsIgnoreCase((prefAffPartyData.getPreferenceId()), "AutoNotification"))
			{
				if (StringUtils.equalsIgnoreCase((prefAffPartyData.getIsTrueFlg()), "1"))
				{
					partyPreferenceData.setIsTrueFlag("True");
				}
				else
				{
					partyPreferenceData.setIsTrueFlag("False");
				}
			}
			partyPreferenceData.setPartyId((prefAffPartyData.getPartyId()));
			partyPreferenceData.setPreferenceId((prefAffPartyData.getPreferenceId()));
			final List<PreferenceList> preferenceList = prefAffPartyData.getPreferenceList();
			final List<PartyPreferenceListData> partyPreferenceList = new ArrayList();
			for (final PreferenceList preferenceListData : preferenceList)
			{
				final PartyPreferenceListData partyPreferenceListData = new PartyPreferenceListData();
				partyPreferenceListData.setPreferenceListId((preferenceListData.getPreferenceListId()));
				partyPreferenceData.setPreferenceListId((preferenceListData.getPreferenceListId()));
				partyPreferenceListData.setPreferenceValueCd((preferenceListData.getPreferenceValueCd()));
				partyPreferenceData.setPreferenceValueCd((preferenceListData.getPreferenceValueCd()));
				partyPreferenceListData.setPreferenceValueName((preferenceListData.getPreferenceValueName()));
				partyPreferenceList.add(partyPreferenceListData);
			}
			partyPreferenceData.setPreferenceList(partyPreferenceList);
			partyPreferenceData.setPreferenceNote((prefAffPartyData.getPreferenceNote()));
			partyPreferenceData.setSalesPlanAff((prefAffPartyData.getSalesPlanAff()));
			partyPreferenceDataList.add(partyPreferenceData);
		}
		target.setPartyPreferenceDataList(partyPreferenceDataList);
	}

}
