package com.amway.integration.cis.dms.populators;

import com.amway.core.dms.data.*;
import com.amway.integration.dms.services.PartyMasterNameDetailsSvcObj;
import com.amway.integration.dms.services.PartyNameDetailResponse;
import com.amway.integration.dms.services.PartyPersonName;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.List;


/**
 * Populator implementation for {@link PartyNameDetailResponse} as source and {@link PartyNameDetailResponseData} as
 * target type.
 */
public class DmsPartyProfileNameDetailResponsePopulator implements Populator<PartyNameDetailResponse, PartyNameDetailResponseData>
{
	@Override
	public void populate(final PartyNameDetailResponse source, final PartyNameDetailResponseData target) throws ConversionException
	{
		if (null != source && null != target && !source.getPartyMasterNameDtlList().isEmpty())
			target.setPartyMasterNameDtlList(populatePartyMasterNameDtlList(source.getPartyMasterNameDtlList().get(0),
					target.getPartyMasterNameDtlList() == null ?
							new PartyMasterNameDtlListData() :
							target.getPartyMasterNameDtlList()));
	}

	private PartyMasterNameDtlListData populatePartyMasterNameDtlList(PartyMasterNameDetailsSvcObj source,
			PartyMasterNameDtlListData target)
	{

		target.setPartyMstData(populatePartyPersonalDetailsRequestData(source,
				target.getPartyMstData() == null ? new PartyPersonalDetailsRequestData() : target.getPartyMstData()));
		target.setPartyNameList(populateListPartyNameDetailsRequestData(source,
				target.getPartyNameList() == null ? new ArrayList<PartyNameDetailsRequestData>() : target.getPartyNameList()));
		return target;
	}

	private PartyPersonalDetailsRequestData populatePartyPersonalDetailsRequestData(PartyMasterNameDetailsSvcObj source,
			PartyPersonalDetailsRequestData target)
	{
		target.setBirthCountryCd(source.getPartyMst().getBirthCountryCd());
		target.setBirthdate(source.getPartyMst().getBirthdate());
		target.setLanguageCd(source.getPartyMst().getLanguageCd());
		target.setEducationTypeCd(source.getPartyMst().getEducationTypeCd());
		target.setEthnicCd(source.getPartyMst().getEthnicCd());
		target.setGenderCd(source.getPartyMst().getGenderCd());
		target.setMaritalStatusCd(source.getPartyMst().getMaritalStatusCd());
		target.setPartyIsMinorFlg(source.getPartyMst().getPartyIsMinorFlg());
		target.setPartyTypeCd(source.getPartyMst().getPartyTypeCd());
		target.setPrimaryOnAccount(source.getPartyMst().getPrimaryOnAccount());
		target.setProfessionCd(source.getPartyMst().getProfessionCd());
		target.setRelationShipToPrimaryPartyCd(source.getPartyMst().getRelationShipToPrimaryPartyCd());
		target.setRoleCd(source.getPartyMst().getRoleCd());
		target.setStatusCd(source.getPartyMst().getStatusCd());
		target.setTzOffset(source.getPartyMst().getTzOffset());
		target.setUserId(source.getPartyMst().getUserId());
		target.setUserPasswd(source.getPartyMst().getUserPasswd());
		target.setUserPin(source.getPartyMst().getUserPin());
		return target;
	}

	private List<PartyNameDetailsRequestData> populateListPartyNameDetailsRequestData(PartyMasterNameDetailsSvcObj source,
			List<PartyNameDetailsRequestData> target)
	{
		for (PartyPersonName s : source.getPartyNameList())
		{
			final PartyNameDetailsRequestData e = new PartyNameDetailsRequestData();
			e.setCharSetCd(s.getCharSetCd());
			e.setLanguageCd(s.getLanguageCd());
			e.setPersonNameTypeCd(s.getPersonNameTypeCd());
			e.setPreferredName(s.getPreferredName());
			if (null != s.getLatinName())
			{
				e.setLatinNameData(new LatinNameData());
				e.getLatinNameData().setFamilyName(s.getLatinName().getFamilyName());
				e.getLatinNameData().setGivenName(s.getLatinName().getGivenName());
				e.getLatinNameData().setMiddleName(s.getLatinName().getMiddleName());
				e.getLatinNameData().setSuffix(s.getLatinName().getSuffix());
				e.getLatinNameData().setTitle(s.getLatinName().getTitle());
			}
			if (null != s.getLocaleName())
			{
				e.setLocaleNameData(new LocaleNameData());
				e.getLocaleNameData().setFamilyName(s.getLocaleName().getFamilyName());
				e.getLocaleNameData().setGivenName(s.getLocaleName().getGivenName());
				e.getLocaleNameData().setMiddleName(s.getLocaleName().getMiddleName());
				e.getLocaleNameData().setSuffix(s.getLocaleName().getSuffix());
				e.getLocaleNameData().setTitle(s.getLocaleName().getTitle());
			}

			target.add(e);
		}
		return target;
	}

}
