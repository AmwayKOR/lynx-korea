/**
 *
 */
package com.amway.core.cms.services.evaluator.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.cms2.servicelayer.data.RestrictionData;
import de.hybris.platform.servicelayer.session.MockSession;
import de.hybris.platform.servicelayer.session.Session;
import de.hybris.platform.servicelayer.session.SessionService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.core.constants.AmwaycoreConstants.SessionVariables;
import com.amway.core.enums.AmwayBusinessNature;
import com.amway.core.model.AmwayAccountModel;
import com.amway.core.model.CMSAccountRestrictionModel;


@UnitTest
public class CMSAccountRestrictionEvaluatorUnitTest
{
	@InjectMocks
	private final CMSAccountRestrictionEvaluator cmsAccountRestrictionEvaluator = new CMSAccountRestrictionEvaluator();
	@Mock
	private SessionService sessionService;
	private AmwayAccountModel accountModel1, accountModel2;
	private CMSAccountRestrictionModel cmsAccountUserRestriction1, cmsAccountUserRestriction2;
	private RestrictionData context;
	private Session session;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		accountModel1 = Mockito.mock(AmwayAccountModel.class);
		BDDMockito.given(accountModel1.getBusinessNature()).willReturn(AmwayBusinessNature.AMWAYBUSINESSNATURE_1);

		accountModel2 = Mockito.mock(AmwayAccountModel.class);
		BDDMockito.given(accountModel2.getBusinessNature()).willReturn(AmwayBusinessNature.AMWAYBUSINESSNATURE_7);

		cmsAccountUserRestriction1 = Mockito.mock(CMSAccountRestrictionModel.class);
		BDDMockito.given(cmsAccountUserRestriction1.getAccountType()).willReturn(AmwayBusinessNature.AMWAYBUSINESSNATURE_1);

		cmsAccountUserRestriction2 = Mockito.mock(CMSAccountRestrictionModel.class);
		BDDMockito.given(cmsAccountUserRestriction2.getAccountType()).willReturn(AmwayBusinessNature.AMWAYBUSINESSNATURE_4);

		context = Mockito.mock(RestrictionData.class);

		session = Mockito.mock(MockSession.class);
		BDDMockito.given(sessionService.getCurrentSession()).willReturn(session);

	}

	/**
	 * Test method for
	 * {@link com.amway.core.cms.services.evaluator.impl.CMSAccountRestrictionEvaluator#evaluate(com.amway.core.model.CMSAccountRestrictionModel, de.hybris.platform.cms2.servicelayer.data.RestrictionData)}
	 * .
	 */
	@Test
	public void testEvaluate()
	{
		BDDMockito.given(session.getAttribute(SessionVariables.ACCOUNT)).willReturn(accountModel1);
		Assert.assertTrue(cmsAccountRestrictionEvaluator.evaluate(cmsAccountUserRestriction1, context));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.cms.services.evaluator.impl.CMSAccountRestrictionEvaluator#evaluate(com.amway.core.model.CMSAccountRestrictionModel, de.hybris.platform.cms2.servicelayer.data.RestrictionData)}
	 * .
	 */
	@Test
	public void testEvaluateAccountIsNullAndBusinessNature4()
	{
		BDDMockito.given(session.getAttribute(SessionVariables.ACCOUNT)).willReturn(null);
		Assert.assertTrue(cmsAccountRestrictionEvaluator.evaluate(cmsAccountUserRestriction2, context));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.cms.services.evaluator.impl.CMSAccountRestrictionEvaluator#evaluate(com.amway.core.model.CMSAccountRestrictionModel, de.hybris.platform.cms2.servicelayer.data.RestrictionData)}
	 * .
	 */
	@Test
	public void testEvaluateBussinessNatureOtherThan1()
	{
		BDDMockito.given(session.getAttribute(SessionVariables.ACCOUNT)).willReturn(accountModel2);
		Assert.assertFalse(cmsAccountRestrictionEvaluator.evaluate(cmsAccountUserRestriction1, context));
	}

}
