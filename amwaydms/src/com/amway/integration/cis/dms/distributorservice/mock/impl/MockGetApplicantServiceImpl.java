package com.amway.integration.cis.dms.distributorservice.mock.impl;

import java.util.ArrayList;
import java.util.List;

import com.amway.core.account.applicant.services.ApplicantProcessDecision;
import com.amway.core.account.applicant.services.ApplicantService;
import com.amway.core.dms.applicant.ApplicantData;
import com.amway.core.dms.applicant.ApplicantResultData;


/**
 * Mock Service for get account party info.
 */
public class MockGetApplicantServiceImpl implements ApplicantService
{
	/**
	 *
	 */
	private static final String BUSINEES_OWNER = "businees owner";
	private static final String ORGANISATION = "organisation";

	@Override
	public ApplicantResultData<ApplicantProcessDecision> getApplicantList(final String affNo, final String aboNo)
	{
		final ApplicantResultData result = new ApplicantResultData();
		result.setDecision(ApplicantProcessDecision.ACCEPT);
		final List<ApplicantData> applicantDataList = new ArrayList<ApplicantData>();
		ApplicantData applicantData = new ApplicantData();
		applicantData.setPartyId("10");
		applicantData.setPartyType(ORGANISATION);
		applicantData.setIsPrimary(true);
		applicantData.setPartyName("ksp1");
		applicantData.setRoleCode(BUSINEES_OWNER);
		applicantDataList.add(applicantData);
		applicantData = new ApplicantData();
		applicantData.setPartyId("10");
		applicantData.setPartyType(ORGANISATION);
		applicantData.setIsPrimary(true);
		applicantData.setPartyName("ksp1");
		applicantData.setRoleCode(BUSINEES_OWNER);
		applicantDataList.add(applicantData);
		applicantData = new ApplicantData();
		applicantData.setPartyId("10");
		applicantData.setPartyType(ORGANISATION);
		applicantData.setIsPrimary(true);
		applicantData.setPartyName("ksp1");
		applicantData.setRoleCode(BUSINEES_OWNER);
		applicantDataList.add(applicantData);
		applicantData = new ApplicantData();
		applicantData.setPartyId("10");
		applicantData.setPartyType(ORGANISATION);
		applicantData.setIsPrimary(true);
		applicantData.setPartyName("ksp1");
		applicantData.setRoleCode(BUSINEES_OWNER);
		applicantDataList.add(applicantData);
		result.setApplicantData(applicantDataList);
		return result;


	}

	private ApplicantResultData createApplicantResultData()
	{
		return new ApplicantResultData();
	}


}
