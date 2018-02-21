/**
 *
 */
package com.amway.core.bonusperiod.services.impl;

import static org.junit.Assert.assertTrue;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.returns.model.ReturnRequestModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.exceptions.BusinessException;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.site.BaseSiteService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.model.AmwayBonusPeriodModel;

import junit.framework.Assert;


@IntegrationTest
public class DefaultAmwayReturnBonusPeriodServiceTest extends ServicelayerTransactionalTest
{
	@Resource
	private DefaultAmwayReturnBonusPeriodService defaultAmwayReturnBonusPeriodService;

	@Resource
	DefaultAmwayBonusPeriodService defaultAmwayBonusPeriodService;

	@Resource
	private FlexibleSearchService flexibleSearchService;

	@Resource
	private BaseSiteService baseSiteService;

	@Resource
	private SessionService sessionService;

	private final static String RETURN_REQUEST_CODE_1 = "1000";
	private final static String RETURN_REQUEST_CODE_2 = "2000";
	private final static String RETURN_REQUEST_CODE_3 = "3000";

	private final static String BONUS_PERIOD_CODE_01 = "012016-op";
	private final static String BONUS_PERIOD_CODE_02 = "022016-op";
	private final static String BONUS_PERIOD_CODE_03 = "032016-op";
	private final static String BONUS_PERIOD_CODE_04 = "032016-op";

	private final static String SITE_UID = "storetemplate";
	private final static String CURRENT_SITE = "currentSite";
	private BaseSiteModel site;


	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		createCoreData();
		createDefaultCatalog();
		importCsv("/amwaycore/test/common.csv", "windows-1252");
		importCsv("/amwaycore/test/siteTestData.csv", "windows-1252");
		importCsv("/amwaycore/test/accountDaoTestData.csv", "windows-1252");
		importCsv("/amwaycore/test/productTestData.csv", "windows-1252");
		importCsv("/amwaycore/test/orderTestData.csv", "windows-1252");
		importCsv("/amwaycore/test/bonusPeriodForReturn.csv", "windows-1252");
		importCsv("/amwaycore/test/returnRequestTestData.csv", "windows-1252");
		site = baseSiteService.getBaseSiteForUID(SITE_UID);
		sessionService.getCurrentSession().setAttribute(CURRENT_SITE, site);
	}

	private ReturnRequestModel getReturnRequest(final String code)
	{
		final String query = "SELECT {PK} FROM {ReturnRequest AS rq} WHERE {rq.code} = ?code";
		final Map<String, Object> values = new HashMap<String, Object>();
		values.put("code", code);
		final SearchResult<ReturnRequestModel> modelSearchResult = flexibleSearchService.search(query, values);
		final List<ReturnRequestModel> returnRequests = modelSearchResult.getResult();
		assertTrue(returnRequests.size() == 1);
		return returnRequests.get(0);
	}

	private AmwayBonusPeriodModel getBonusPeriod(final String code)
	{
		final String query = "SELECT {PK} FROM {AmwayBonusPeriod AS bp} WHERE {bp.code} = ?code";
		final Map<String, Object> values = new HashMap<String, Object>();
		values.put("code", code);
		final SearchResult<AmwayBonusPeriodModel> modelSearchResult = flexibleSearchService.search(query, values);
		final List<AmwayBonusPeriodModel> bonusPeriods = modelSearchResult.getResult();
		assertTrue(bonusPeriods.size() == 1);
		return bonusPeriods.get(0);
	}

	//	@Test
	//	public void testAssignBonusPeriod() throws BusinessException
	//	{
	//		final ReturnRequestModel returnRequest = getReturnRequest(RETURN_REQUEST_CODE_1);
	//		final AmwayBonusPeriodModel bonusPeriod = getBonusPeriod(BONUS_PERIOD_CODE_02);
	//		defaultAmwayReturnBonusPeriodService.assignBonusPeriod(returnRequest);
	//		Assert.assertEquals("Should be same ", bonusPeriod, returnRequest.getReturnBonusPeriod());
	//	}

	//	@Test(expected = BusinessException.class)
	//	public void testAssignCurrentBonusPeriod() throws BusinessException
	//	{
	//		final ReturnRequestModel returnRequest = getReturnRequest(RETURN_REQUEST_CODE_3);
	//		final AmwayBonusPeriodModel bonusPeriod = getBonusPeriod(BONUS_PERIOD_CODE_04);
	//		defaultAmwayReturnBonusPeriodService.assignBonusPeriod(returnRequest);
	//		Assert.assertEquals("Should be same ", bonusPeriod, returnRequest.getReturnBonusPeriod());
	//	}

	//	@Test(expected = BusinessException.class)
	//	public void testTryToAssignNextActiveBonusPeriodForCuttOffDate() throws BusinessException
	//	{
	//		final ReturnRequestModel returnRequest = getReturnRequest(RETURN_REQUEST_CODE_2);
	//		final AmwayBonusPeriodModel bonusPeriod = getBonusPeriod(BONUS_PERIOD_CODE_02);
	//		defaultAmwayReturnBonusPeriodService.assignBonusPeriod(returnRequest);
	//		Assert.assertEquals("Should be same ", bonusPeriod, returnRequest.getReturnBonusPeriod());
	//	}

	@Test(expected = BusinessException.class)
	public void testAssignNextActiveBonusPeriodForCuttOffDate() throws BusinessException, ImpExException
	{
		// test for current bonus period
		ReturnRequestModel returnRequest = getReturnRequest(RETURN_REQUEST_CODE_1);
		AmwayBonusPeriodModel bonusPeriod = getBonusPeriod(BONUS_PERIOD_CODE_02);
		defaultAmwayReturnBonusPeriodService.assignBonusPeriod(returnRequest);
		Assert.assertEquals("Should be same ", bonusPeriod, returnRequest.getReturnBonusPeriod());

		// test for current bonus period which is not active
		returnRequest = getReturnRequest(RETURN_REQUEST_CODE_3);
		bonusPeriod = getBonusPeriod(BONUS_PERIOD_CODE_04);
		defaultAmwayReturnBonusPeriodService.assignBonusPeriod(returnRequest);
		Assert.assertEquals("Should be same ", bonusPeriod, returnRequest.getReturnBonusPeriod());

		// test for assign next active bonus period after cutt off date and no other next bonus period is active
		returnRequest = getReturnRequest(RETURN_REQUEST_CODE_2);
		bonusPeriod = getBonusPeriod(BONUS_PERIOD_CODE_02);
		defaultAmwayReturnBonusPeriodService.assignBonusPeriod(returnRequest);
		Assert.assertEquals("Should be same ", bonusPeriod, returnRequest.getReturnBonusPeriod());

		// test for assign next active bonus period after cutt off date
		importCsv("/amwaycore/test/bonusPeriodReturnExceedsCuttoffDate.csv", "windows-1252");
		returnRequest = getReturnRequest(RETURN_REQUEST_CODE_2);
		bonusPeriod = getBonusPeriod(BONUS_PERIOD_CODE_03);
		defaultAmwayReturnBonusPeriodService.assignBonusPeriod(returnRequest);
		Assert.assertEquals("Should be same ", bonusPeriod, returnRequest.getReturnBonusPeriod());
	}


	//	@Test(expected = IllegalArgumentException.class)
	//	public void testAssignGivenBonusPeriod() throws BusinessException
	//	{
	//		final ReturnRequestModel returnRequest = getReturnRequest(RETURN_REQUEST_CODE_1);
	//		final AmwayBonusPeriodModel bonusPeriod = getBonusPeriod(BONUS_PERIOD_CODE_01);
	//		defaultAmwayReturnBonusPeriodService.assignBonusPeriod(returnRequest, bonusPeriod);
	//		Assert.assertEquals("Should be same ", bonusPeriod, returnRequest.getReturnBonusPeriod());
	//	}

	@Test(expected = IllegalArgumentException.class)
	public void testAssignGivenBonusPeriod() throws BusinessException, ImpExException
	{
		//test for inactive bonus period
		ReturnRequestModel returnRequest = getReturnRequest(RETURN_REQUEST_CODE_1);
		AmwayBonusPeriodModel bonusPeriod = getBonusPeriod(BONUS_PERIOD_CODE_01);
		defaultAmwayReturnBonusPeriodService.assignBonusPeriod(returnRequest, bonusPeriod);
		Assert.assertEquals("Should be same ", bonusPeriod, returnRequest.getReturnBonusPeriod());

		// test for active bonus period
		returnRequest = getReturnRequest(RETURN_REQUEST_CODE_1);
		bonusPeriod = getBonusPeriod(BONUS_PERIOD_CODE_02);
		defaultAmwayReturnBonusPeriodService.assignBonusPeriod(returnRequest, bonusPeriod);
		Assert.assertEquals("Should be same ", bonusPeriod, returnRequest.getReturnBonusPeriod());

		// test for active bonus period but return consolidateDate exceeds cutt off date of given active bonus period
		returnRequest = getReturnRequest(RETURN_REQUEST_CODE_2);
		bonusPeriod = getBonusPeriod(BONUS_PERIOD_CODE_02);
		defaultAmwayReturnBonusPeriodService.assignBonusPeriod(returnRequest, bonusPeriod);
		Assert.assertEquals("Should be same ", bonusPeriod, returnRequest.getReturnBonusPeriod());

		// test for active bonus period but return consolidateDate exceeds cutt off date of given active bonus period
		//	then seting next active bonus period for return request
		importCsv("/amwaycore/test/periodServicesTestData/bonusPeriodReturnExceedsCuttoffDate.csv", "windows-1252");
		returnRequest = getReturnRequest(RETURN_REQUEST_CODE_2);
		final AmwayBonusPeriodModel activeBonusPeriod = getBonusPeriod(BONUS_PERIOD_CODE_02);
		final AmwayBonusPeriodModel nextActiveBonusPeriod = getBonusPeriod(BONUS_PERIOD_CODE_03);
		defaultAmwayReturnBonusPeriodService.assignBonusPeriod(returnRequest, activeBonusPeriod);
		Assert.assertEquals("Should be same ", nextActiveBonusPeriod, returnRequest.getReturnBonusPeriod());
	}

	//	@Test(expected = IllegalArgumentException.class)
	//	public void testAssignGivenInActiveBonusPeriod() throws BusinessException
	//	{
	//		final ReturnRequestModel returnRequest = getReturnRequest(RETURN_REQUEST_CODE_1);
	//		final AmwayBonusPeriodModel bonusPeriod = getBonusPeriod(BONUS_PERIOD_CODE_03);
	//		defaultAmwayReturnBonusPeriodService.assignBonusPeriod(returnRequest, bonusPeriod);
	//		//		Assert.assertEquals("Invalid bonus period. Should be active",bonusPeriod, returnRequest.getReturnBonusPeriod());
	//	}

	@Test
	public void testFindAllActiveBonusPeriodsForSite()
	{
		Assert.assertTrue(CollectionUtils.size(defaultAmwayReturnBonusPeriodService.findAllActiveBonusPeriodsForSite()) == 1);
	}
}
