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

import com.amway.core.dms.data.PrePrintedNumberRequestData;
import com.amway.core.dms.data.PrePrintedNumberResultData;
import com.amway.integration.cis.dms.customerregistration.mock.impl.MockValidatePrePrintedNumberService;


/**
 * @author admin
 */
public class DefaultValidatePrePrintedNumberServiceTest extends ServicelayerTransactionalTest
{
	@Resource(name = "mockValidatePrePrintedNumberService")
	private MockValidatePrePrintedNumberService defaultValidatePrePrintedNumberService;

	@Resource
	private BaseSiteService baseSiteService;

	@Resource
	private SessionService sessionService;

	PrePrintedNumberRequestData requestData = new PrePrintedNumberRequestData();

	@Before
	public void setup() throws ImpExException
	{
		importCsv("/amwaycore/test/common.csv", "windows-1252");
		importCsv("/amwaycore/test/siteTestData.csv", "windows-1252");

		baseSiteService.setCurrentBaseSite("storetemplate", true);

		requestData.setPrePrintedNum("3101005801");
		requestData.setSalesPlanAff("170");
		requestData.setCntryCd("BR");
	}

	@Test
	public void shouldValidatePreprintedNumberTest()
	{
		final PrePrintedNumberResultData response = defaultValidatePrePrintedNumberService.process(requestData);
		Assert.assertTrue(response.getReturnCd() == 1);
	}

}
