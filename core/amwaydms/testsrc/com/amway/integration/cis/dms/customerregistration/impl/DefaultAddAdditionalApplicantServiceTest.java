/**
 *
 */
package com.amway.integration.cis.dms.customerregistration.impl;

import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.site.BaseSiteService;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.amway.core.dms.data.RegisterRequestData;
import com.amway.core.dms.data.RegistrationResultData;
import com.amway.integration.cis.dms.customerregistration.mock.impl.MockAddAdditionalApplicantServiceImpl;


/**
 * @author admin
 */
public class DefaultAddAdditionalApplicantServiceTest extends ServicelayerTransactionalTest
{

	@Resource(name = "mockAddAdditionalApplicantServiceImpl")
	private MockAddAdditionalApplicantServiceImpl defaultAddAdditionalApplicantService;

	@Resource
	private BaseSiteService baseSiteService;

	@Resource
	private SessionService sessionService;

	private final RegisterRequestData requestData = new RegisterRequestData();

	@Before
	public void setUp() throws ImpExException
	{

		importCsv("/amwaycore/test/common.csv", "windows-1252");
		importCsv("/amwaycore/test/siteTestData.csv", "windows-1252");


		baseSiteService.setCurrentBaseSite("storetemplate", true);

		requestData.setAboNum("7000020315");
		requestData.setMinorFlag(false);
		requestData.setDob("04101992");
		requestData.setFirstName("test");
		requestData.setLastName("class");
	}

	@Test
	public void shouldAddAdditionalApllicantServiceTest()
	{
		final RegistrationResultData response = defaultAddAdditionalApplicantService.process(requestData);
		Assert.assertTrue(response.getReturnCd() == 1);
	}

}
