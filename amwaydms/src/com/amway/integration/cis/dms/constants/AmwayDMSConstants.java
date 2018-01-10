package com.amway.integration.cis.dms.constants;

@SuppressWarnings("PMD")
public class AmwayDMSConstants extends GeneratedAmwayDMSConstants
{
	public static final String EXTENSIONNAME = "amwaydms";
	/**
	 * Session attribute that indicates that the current thread requests must be served for the specified user.
	 * The object type which is stored under that attribute is 'com.amway.core.data.CommonRequestFieldsData'.
	 */
	public static final String IMPERSONATION_FLOW_SESSION_ATTR = "impersonation-flow-attr";

	public static final String DMSDATEPATTERN = "yyyy-MM-dd'T'HH:mm:ssXXX";

	public static final String RENEW_CD = "RN";

	public static final String REVERT_RENEW_CD = "RV";

	private AmwayDMSConstants()
	{
		//empty
	}


}
