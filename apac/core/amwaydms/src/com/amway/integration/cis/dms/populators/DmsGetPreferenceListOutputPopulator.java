/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.List;

import com.amway.core.dms.data.PartyPreferenceListData;
import com.amway.core.dms.data.PreferenceListData;
import com.amway.core.dms.data.PreferenceListResponse;
import com.amway.integration.dms.services.GetPreferenceResponse;
import com.amway.integration.dms.services.PrefMasterData;
import com.amway.integration.dms.services.PreferenceList;


/**
 * Populator implementation for {@link GetPreferenceResponse} as source and {@link PreferenceListResponse} as target
 * type.
 */
public class DmsGetPreferenceListOutputPopulator extends AbstractDmsPopulator
		implements Populator<GetPreferenceResponse, PreferenceListResponse>
{
	@Override
	public void populate(final GetPreferenceResponse source, final PreferenceListResponse target) throws ConversionException
	{
		final List<PreferenceListData> preferenceListDataList = new ArrayList<>();
		final List<PrefMasterData> prefMasterListData = source.getPrefMasterData();
		for (final PrefMasterData prefMasterData : prefMasterListData)
		{
			final PreferenceListData preferenceListData = new PreferenceListData();
			final List<PartyPreferenceListData> partyPreferenceList = new ArrayList<>();
			for (final PreferenceList prefList : prefMasterData.getPrefListObject())
			{
				final PartyPreferenceListData partyPreferenceListData = new PartyPreferenceListData();
				partyPreferenceListData.setPreferenceListId((prefList.getPreferenceListId()));
				partyPreferenceList.add(partyPreferenceListData);
			}
			preferenceListData.setPreferenceList(partyPreferenceList);
			preferenceListData.setChoiceListFlg(prefMasterData.getChoiceListFlg());
			preferenceListData.setIsTrueFlag(prefMasterData.getIsTrueFlg());
			preferenceListData.setPreferenceCatagoryTypeId(prefMasterData.getPreferenceCatagoryTypeId());
			preferenceListData.setPreferenceDesc(prefMasterData.getPreferenceDesc());
			preferenceListData.setPreferenceId(prefMasterData.getPreferenceId());
			preferenceListData.setPreferenceNote(prefMasterData.getPreferenceNote());

			preferenceListDataList.add(preferenceListData);
		}
		target.setPreferenceListDataList(preferenceListDataList);
	}

}
