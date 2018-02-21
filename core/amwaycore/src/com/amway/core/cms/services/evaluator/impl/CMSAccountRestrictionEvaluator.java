/**
 *
 */
package com.amway.core.cms.services.evaluator.impl;

import de.hybris.platform.cms2.servicelayer.data.RestrictionData;
import de.hybris.platform.cms2.servicelayer.services.evaluator.CMSRestrictionEvaluator;
import de.hybris.platform.servicelayer.session.SessionService;

import com.amway.core.constants.AmwaycoreConstants.SessionVariables;
import com.amway.core.constants.GeneratedAmwaycoreConstants.Enumerations.AmwayBusinessNature;
import com.amway.core.model.AmwayAccountModel;
import com.amway.core.model.CMSAccountRestrictionModel;



/**
 * This method evaluates the CMS account restriction.
 */
public class CMSAccountRestrictionEvaluator implements CMSRestrictionEvaluator<CMSAccountRestrictionModel>
{

	private SessionService sessionService;

	/**
	 * {@link #evaluate(com.amway.core.model.CMSAccountRestrictionModel, de.hybris.platform.cms2.servicelayer.data.RestrictionData)}
	 */
	@Override
	public boolean evaluate(final CMSAccountRestrictionModel cmsAccountUserRestriction, final RestrictionData context)
	{
		//Get current session unit
		final AmwayAccountModel unit = getSessionService().getCurrentSession().getAttribute(SessionVariables.ACCOUNT);
		if (unit != null && unit.getBusinessNature() != null && cmsAccountUserRestriction.getAccountType().getCode()
				.equals(unit.getBusinessNature().getCode()))
		{
			return true;
		}
		else if (unit == null && AmwayBusinessNature.AMWAYBUSINESSNATURE_4
				.equals(cmsAccountUserRestriction.getAccountType().getCode()))
		{
			return true;
		}
		return false;
	}

	/**
	 * @return sessionService
	 */
	public SessionService getSessionService()
	{
		return sessionService;
	}

	/**
	 * @param sessionService the sessionService to set
	 */
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

}
