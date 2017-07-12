/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.List;

import com.amway.core.account.applicant.services.ApplicantProcessDecision;
import com.amway.core.dms.applicant.ApplicantData;
import com.amway.core.dms.applicant.ApplicantResultData;
import com.amway.integration.dms.services.AccountParty;
import com.amway.integration.dms.services.AccountPartyListResponse;




/**
 * Populator implementation for {@link AccountPartyListResponse} as source and {@link ApplicantResultData} as target
 * type.
 */
public class DmsApplicantListReversePopulator
		implements Populator<AccountPartyListResponse, ApplicantResultData<ApplicantProcessDecision>>
{

	@Override
	public void populate(final AccountPartyListResponse source, final ApplicantResultData<ApplicantProcessDecision> target)
			throws ConversionException
	{

		if (source.getAccountParty() != null && !source.getAccountParty().isEmpty())
		{
			final List<AccountParty> applicantList = source.getAccountParty();
			final List<ApplicantData> applicantdata = new ArrayList<ApplicantData>();
			for (final AccountParty accountParty : applicantList)
			{
				final ApplicantData data = new ApplicantData();
				data.setGender(accountParty.getGenderCd());
				data.setIsPrimary(Boolean.parseBoolean(accountParty.getPrimaryOnAccount()));
				data.setPartyId(accountParty.getPartyId());
				data.setPartyName(accountParty.getPartyName());
				data.setPartyType(accountParty.getPartyTypeCd());
				data.setRoleCode(accountParty.getRoleCd());
				applicantdata.add(data);
			}

			target.setApplicantData(applicantdata);
			target.setDecision(ApplicantProcessDecision.ACCEPT);
		}
		else
		{
			target.setDecision(ApplicantProcessDecision.UNKNOWN);
		}

	}
}
