package com.amway.apac.message.center.notification.facades.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amway.apac.core.account.services.impl.DefaultAmwayApacAccountService;
import com.amway.apac.core.constants.AmwayapacCoreConstants;
import com.amway.apac.core.enums.AccountClassificationEnum;
import com.amway.apac.message.center.enums.AmwayNotificationUserActionStatus;
import com.amway.apac.message.center.model.AmwayNotificationModel;
import com.amway.apac.message.center.model.AmwayNotificationUserActionModel;
import com.amway.apac.message.center.notification.AmwayApacNotificationData;
import com.amway.apac.message.center.notification.services.impl.DefaultAmwayApacNotificationService;
import com.amway.core.model.AmwayAccountModel;


/**
 * The Class DefaultAmwayApacNotificationFacadeIntegrationTest.
 */
@IntegrationTest
public class DefaultAmwayApacNotificationFacadeIntegrationTest extends ServicelayerTransactionalTest
{
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAmwayApacNotificationFacadeIntegrationTest.class);

	private static final String TEST_USER_UID_ONE = "ahertz";

	private static final String TEST_SITE_UID = "testSite";

	private static final String GROUP_NOTIFICATION_CODE = "group_notification";

	private static final String PLATINUM_NOTIFICATION_CODE = "platinum_notification";

	private static final int PAGE_SIZE = 100;

	private static final int CURRENT_PAGE = 0;

	private static final String SORT_STRING = "date";

	private static final String UNREAD = "UNREAD";

	private static final String READ = "READ";

	@Resource
	private UserService userService;

	@Resource
	private BaseSiteService baseSiteService;

	@Resource
	private PageableData pageableData;

	@Resource
	private SessionService sessionService;

	@Resource
	DefaultAmwayApacNotificationFacade defaultAmwayApacNotificationFacade;

	@Resource
	private DefaultAmwayApacAccountService defaultAmwayApacAccountService;

	@Resource
	private DefaultAmwayApacNotificationService defaultAmwayApacNotificationService;

	@Before
	public void setUp() throws ImpExException
	{
		importCsv("/amwayapaccore/test/testCommerceCart.csv", "UTF-8");
		setUpSite();
	}

	/**
	 * Setup site and pageable data for integration test.
	 */
	protected void setUpSite()
	{
		pageableData = new PageableData();
		pageableData.setPageSize(PAGE_SIZE);
		pageableData.setCurrentPage(CURRENT_PAGE);
		pageableData.setSort(SORT_STRING);
		baseSiteService.setCurrentBaseSite(baseSiteService.getBaseSiteForUID(TEST_SITE_UID), true);
	}

	/**
	 * Integration test method of Message Center for Group User Notification
	 */
	@Test
	public void testGetAmwayNotificationSectionForCurrentUserWithPageData()
	{
//		final CustomerModel user = (CustomerModel) userService.getUserForUID(TEST_USER_UID_ONE);
//		userService.setCurrentUser(user);
//
//		final SearchPageData<AmwayApacNotificationData> result = defaultAmwayApacNotificationFacade
//				.getNotificationsForCurrentUser(pageableData, "");
//
//		final List<AmwayApacNotificationData> resultList = result.getResults();
//		final String resultNotificationCode = resultList.get(0).getCode();
//		Assert.assertEquals(GROUP_NOTIFICATION_CODE, resultNotificationCode);
	}

	/**
	 * Integration test method of Message Center for Platinum Classification User Notification
	 */
	@Test
	public void testGetAmwayNotificationSectionForCurrentUserWithPageDataForPlatinumUser()
	{
//		sessionService.setAttribute(AmwayapacCoreConstants.ACCOUNT_CLASSIFICATION_CODE,
//				AccountClassificationEnum.PLATINUM_AND_ABOVE.toString());
//		final AmwayAccountModel account = defaultAmwayApacAccountService.getAmwayAccount(TEST_USER_UID_ONE, "100");
//		final CustomerModel user = account.getPrimaryParty();
//		userService.setCurrentUser(user);
//
//		final SearchPageData<AmwayApacNotificationData> result = defaultAmwayApacNotificationFacade
//				.getNotificationsForCurrentUser(pageableData, "");
//
//		final List<AmwayApacNotificationData> resultList = result.getResults();
//		final String resultNotificationCode = resultList.get(0).getCode();
//		Assert.assertEquals(PLATINUM_NOTIFICATION_CODE, resultNotificationCode);
	}

	@Test
	public void testChangeUserNotificationStatus()
	{
//		final CustomerModel user = (CustomerModel) userService.getUserForUID(TEST_USER_UID_ONE);
//		userService.setCurrentUser(user);
//
//		final AmwayNotificationModel notification = defaultAmwayApacNotificationService
//				.getNotificationByCode(GROUP_NOTIFICATION_CODE);
//
//		defaultAmwayApacNotificationFacade.changeUserNotificationStatus(GROUP_NOTIFICATION_CODE,
//				AmwayNotificationUserActionStatus.valueOf(READ));
//
//		final AmwayNotificationUserActionModel resultsWithRead = defaultAmwayApacNotificationService
//				.getNotificationActionByUserAndNotification(user, notification);
//		Assert.assertEquals(AmwayNotificationUserActionStatus.valueOf(READ), resultsWithRead.getStatus());
//
//		defaultAmwayApacNotificationFacade.changeUserNotificationStatus(GROUP_NOTIFICATION_CODE,
//				AmwayNotificationUserActionStatus.valueOf(UNREAD));
//		final AmwayNotificationUserActionModel resultsWithUnread = defaultAmwayApacNotificationService
//				.getNotificationActionByUserAndNotification(user, notification);
//		Assert.assertEquals(AmwayNotificationUserActionStatus.valueOf(UNREAD), resultsWithUnread.getStatus());
	}
}
