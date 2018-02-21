/**
 *
 */
package com.amway.core.service.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.voucher.VoucherService;
import de.hybris.platform.voucher.model.PromotionVoucherModel;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;


@UnitTest
public class DefaultAmwayVoucherModelServiceUnitTest extends ServicelayerTransactionalTest
{
	@Resource(name = "voucherModelService")
	DefaultAmwayVoucherModelService amwayVoucherModelService;
	@Resource(name = "userService")
	UserService userService;
	@Resource(name = "voucherService")
	VoucherService voucherService;
	@Resource(name = "modelService")
	ModelService modelService;
	@Resource
	private FlexibleSearchService flexibleSearchService;

	private PromotionVoucherModel promotionVoucherModel;
	private UserModel userModel;

	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		importCsv("/amwaycore/test/common.csv", "UTF-8");
		importCsv("/amwaycore/test/siteTestData.csv", "UTF-8");
		importCsv("/amwaycore/test/accountDaoTestData.csv", "windows-1252");
		importCsv("/amwaycore/test/productTestData.csv", "UTF-8");
		//importCsv("/amwaycore/test/vouchers.csv", "UTF-8");
		userModel = userService.getUserForUID("amway_party_test_1");
		promotionVoucherModel = modelService.create(PromotionVoucherModel.class);
		promotionVoucherModel.setCode("pqr");
		promotionVoucherModel.setVoucherCode("abc123");
		promotionVoucherModel.setValue(Double.valueOf(12.00));
		modelService.save(promotionVoucherModel);
	}

	@Test
	public void testIsReservableForTrue()
	{
		Assert.assertEquals(Boolean.TRUE,
				Boolean.valueOf(amwayVoucherModelService.isReservable(promotionVoucherModel, "pqr", userModel)));
	}
}
