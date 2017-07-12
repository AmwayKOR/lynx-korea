package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.lf5.util.DateFormatManager;

import com.amway.core.dms.data.PartyPersonalDetailsRequestData;
import com.amway.integration.dms.services.UpdatePartyRequest;


/**
 * Populator implementation for {@link PartyPersonalDetailsRequestData} as source and {@link UpdatePartyRequest} as
 * target type.
 */
public class DmsUpdatePartyInformationServiceInputPopulator extends AbstractDmsPopulator
		implements Populator<PartyPersonalDetailsRequestData, UpdatePartyRequest>
{
	@Override
	public void populate(final PartyPersonalDetailsRequestData source, final UpdatePartyRequest target) throws ConversionException
	{
		target.setAboNum(source.getAboNum());
		target.setSalesPlanAff(source.getSalesPlanAff());
		target.setBirthCountryCd(source.getBirthCountryCd());

		if (StringUtils.isNotBlank(source.getBirthdate()))
		{
			final String dateOfbirth = source.getBirthdate().replaceAll("/", "");
			final SimpleDateFormat sdf = new SimpleDateFormat(DMSDATEPATTERN);
			String birthDate;
			try
			{
				birthDate = sdf.format(new DateFormatManager("MMddyyyy").getDateFormatInstance().parse(dateOfbirth));
				birthDate = birthDate.split("T")[0] + "T00:00:00+00:00";
				target.setBirthDate(birthDate);
			}
			catch (final ParseException e)
			{
				LOG.warn("Parse exception on birth date.", e);
			}
		}
		else
		{
			target.setBirthDate(source.getBirthdate());
		}
		target.setGenderCd(source.getGenderCd());
		target.setLanguageCd(source.getLanguageCd());
		target.setMaritalStatusCd(source.getMaritalStatusCd());
		target.setPartyId(source.getPartyId());
		target.setPartyTypeCd(source.getPartyTypeCd());
		target.setPartyIsMinorFlg(source.getPartyIsMinorFlg());
	}
}
