package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.lf5.util.DateFormatManager;

import com.amway.core.dms.data.RegisterRequestData;
import com.amway.integration.dms.services.AddPartyName;
import com.amway.integration.dms.services.AddPartyRequest;
import com.amway.integration.dms.services.NameDetailsService;


/**
 * Populate party service request data
 */
public class DmsPartyServiceDataInputPopulator extends AbstractDmsPopulator
		implements Populator<RegisterRequestData, AddPartyRequest>
{
	private BaseStoreService baseStoreService;

	@Override
	public void populate(final RegisterRequestData source, final AddPartyRequest target) throws ConversionException
	{
		final BaseStoreModel currentStore = getBaseStoreService().getCurrentBaseStore();
		target.setSalesPlanAff(currentStore.getAffiliateNumber());
		//Not required to set userID
		target.setAboNum(source.getAboNumber());

		target.setPartyTypeCd("Person");
		if (source.isMinorFlag())
		{
			target.setPartyIsMinorFlg("Y");
		}
		else
		{
			target.setPartyIsMinorFlg("N");
		}
		target.setRoleCd("Applicant");


		if (StringUtils.isNotBlank(source.getDob()))
		{
			final String dateOfbirth = source.getDob().replaceAll("/", "");
			final SimpleDateFormat sdf = new SimpleDateFormat(DMSDATEPATTERN);
			String birthDate;
			try
			{
				birthDate = sdf.format(new DateFormatManager("ddMMyyyy").getDateFormatInstance().parse(dateOfbirth));
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
			target.setBirthDate(source.getDob());
		}

		/*
		 * try { final SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy"); final String birthDate = sdf.format(new
		 * DateFormatManager("ddMMyyyy").getDateFormatInstance().parse(dateOfbirth)); target.setBirthDate(birthDate); }
		 * catch (final ParseException e) { // YTODO Auto-generated catch block e.printStackTrace(); }
		 */
		//target.setBirthDate(source.getDob());
		final NameDetailsService latinNameService = new NameDetailsService();
		latinNameService.setFamilyName(source.getLastName());
		latinNameService.setGivenName(source.getFirstName());
		latinNameService.setMiddleName(source.getLastName());
		latinNameService.setSuffix("");
		latinNameService.setTitle("");

		final NameDetailsService localeNameService = new NameDetailsService();
		localeNameService.setTitle("");
		localeNameService.setSuffix("");
		localeNameService.setGivenName(source.getFirstName());
		localeNameService.setFamilyName(source.getLastName());
		localeNameService.setMiddleName(source.getFirstName());

		// setting PartyPersonNameService inputs
		final AddPartyName nameServiceInput = new AddPartyName();
		nameServiceInput.setLanguageCd(currentStore.getDefaultLanguage().getIsocode());
		nameServiceInput.setPersonNameTypeCd("Legal");
		nameServiceInput.setLocaleName(localeNameService);
		nameServiceInput.setLatinName(latinNameService);
		nameServiceInput.setCharSetCd("");
		nameServiceInput.setPreferredName("");
		target.setName(nameServiceInput);

	}

	public BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

}
