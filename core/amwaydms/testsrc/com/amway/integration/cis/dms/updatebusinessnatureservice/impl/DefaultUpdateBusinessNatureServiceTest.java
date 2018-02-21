/**
 *
 */
package com.amway.integration.cis.dms.updatebusinessnatureservice.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.ServicelayerTest;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.dms.data.UpdateBusinessNatureInputRequestData;
import com.amway.integration.cis.dms.updatebusinessnature.mock.impl.MockUpdateBusinessNatureService;


@UnitTest
public class DefaultUpdateBusinessNatureServiceTest extends ServicelayerTest
{

	@Resource(name = "mockUpdateBusinessNatureService")
	private MockUpdateBusinessNatureService defaultUpdateBusinessNatureService;

	private UpdateBusinessNatureInputRequestData requestData;

	@Before
	public void setUp()
	{
		requestData = new UpdateBusinessNatureInputRequestData();
		requestData.setAboNum("3109215040");
		requestData.setAccountSubTypeCd("BusinessOwner");
		requestData.setSalesPlanAff("170");
		requestData.setOrderNum("7000100");
		requestData.setReasonCd("Deleted by Missing Docume");

	}

	@Test
	public void shouldUpdateBusinessNature()
	{
		final CommonResponseFieldsData response = defaultUpdateBusinessNatureService.process(requestData);
		Assert.assertTrue(response.getReturnCd() == 1);
	}

}
