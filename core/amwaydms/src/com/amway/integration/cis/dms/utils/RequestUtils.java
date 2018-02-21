package com.amway.integration.cis.dms.utils;

/**
 * Utility class.
 * Contains useful methods to prepare requests for MDMS.
 */
public class RequestUtils
{
	public static String addContextPathParams(String salesPlanAff, String aboNum, String partyId, String detailLevel, String uri)
	{
		if (salesPlanAff != null)
		{
			uri = uri.replaceAll("\\{" + "salesPlanAff" + "\\}", salesPlanAff);
		}
		if (aboNum != null)
		{
			uri = uri.replaceAll("\\{" + "aboNum" + "\\}", aboNum);
		}
		if (partyId != null)
		{
			uri = uri.replaceAll("\\{" + "partyId" + "\\}", partyId);
		}
		if (detailLevel != null)
		{
			uri = uri.replaceAll("\\{" + "detailLevel" + "\\}", detailLevel);
		}
		return uri;
	}

}
