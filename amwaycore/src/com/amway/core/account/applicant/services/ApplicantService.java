package com.amway.core.account.applicant.services;

import com.amway.core.dms.applicant.ApplicantResultData;


/**
 * interface for Applicant Service
 */
public interface ApplicantService
{
	/**
	 * POJO representation of an applicant result.
	 *
	 * @param affNo
	 * @param aboNo
	 * @return ApplicantResultData
	 */
	ApplicantResultData getApplicantList(String affNo, String aboNo);
}
