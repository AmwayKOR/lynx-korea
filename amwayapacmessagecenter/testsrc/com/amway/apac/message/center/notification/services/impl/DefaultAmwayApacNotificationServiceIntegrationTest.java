package com.amway.apac.message.center.notification.services.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.apac.core.account.services.impl.DefaultAmwayApacAccountService;
import com.amway.apac.core.base.daos.BaseDao;
import com.amway.apac.core.constants.AmwayapacCoreConstants;
import com.amway.apac.core.enums.AccountClassificationEnum;
import com.amway.apac.message.center.enums.AmwayNotificationUserActionStatus;
import com.amway.apac.message.center.model.AmwayNotificationModel;
import com.amway.apac.message.center.model.AmwayNotificationUserActionModel;
import com.amway.core.model.AmwayAccountModel;


/**
 * @author Shubham Goyal
 */

@IntegrationTest
public class DefaultAmwayApacNotificationServiceIntegrationTest extends ServicelayerTransactionalTest
{
	private static final String TEST_USER_UID_ONE = "ahertz";

	private static final String TEST_SITE_UID = "testSite";

	private static final int PAGE_SIZE = 100;

	private static final int CURRENT_PAGE = 0;

	private static final String SORT_STRING = "date";

	private static final String UNREAD = "UNREAD";

	private static final String READ = "READ";

	private static final String GROUP_NOTIFICATION_CODE = "group_notification";

	private static final String PLATINUM_NOTIFICATION_CODE = "platinum_notification";

	private static final String DIAMOND_NOTIFICATION_CODE = "diamond_notification";

	@Resource
	private UserService userService;
	@Resource
	private BaseSiteService baseSiteService;
	@Resource
	private ModelService modelService;
	@Resource
	private PageableData pageableData;
	@Resource
	private DefaultAmwayApacNotificationService defaultAmwayApacNotificationService;
	@Resource
	private BaseDao<AmwayNotificationUserActionModel> amwayNotificationUserActionDao;
	@Resource
	private DefaultAmwayApacAccountService defaultAmwayApacAccountService;
	@Resource
	private SessionService sessionService;

	/**
	 * Setup required data for integration test.
	 *
	 * @throws ImpExException
	 */
	@Before
	public void setup() throws ImpExException
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
	public void testGetMessageNotificationByMappingForGroupUser()
	{
		final CustomerModel user = (CustomerModel) userService.getUserForUID(TEST_USER_UID_ONE);
		userService.setCurrentUser(user);
		final List<AmwayNotificationUserActionStatus> statuses = new ArrayList<AmwayNotificationUserActionStatus>();
		statuses.add(AmwayNotificationUserActionStatus.valueOf(UNREAD));
		statuses.add(AmwayNotificationUserActionStatus.valueOf(READ));
		final SearchPageData<AmwayNotificationModel> result = defaultAmwayApacNotificationService.getNotifications(pageableData,
				user, statuses, "");
		final List<AmwayNotificationModel> resultList = result.getResults();
		final String resultNotificationCode = resultList.get(0).getCode();
		Assert.assertEquals(GROUP_NOTIFICATION_CODE, resultNotificationCode);
	}

	/**
	 * Integration test method of Message Center for Platinum Classification User Notification
	 */
	@Test
	public void testGetMessageNotificationByMappingForPlatinumUser()
	{
		sessionService.setAttribute(AmwayapacCoreConstants.ACCOUNT_CLASSIFICATION_CODE,
				AccountClassificationEnum.PLATINUM_AND_ABOVE.toString());
		final AmwayAccountModel account = defaultAmwayApacAccountService.getAmwayAccount(TEST_USER_UID_ONE, "100").iterator()
				.next();
		final CustomerModel user = account.getPrimaryParty();
		userService.setCurrentUser(user);
		final List<AmwayNotificationUserActionStatus> statuses = new ArrayList<AmwayNotificationUserActionStatus>();
		statuses.add(AmwayNotificationUserActionStatus.valueOf(UNREAD));
		statuses.add(AmwayNotificationUserActionStatus.valueOf(READ));
		final SearchPageData<AmwayNotificationModel> result = defaultAmwayApacNotificationService.getNotifications(pageableData,
				user, statuses, "");
		final List<AmwayNotificationModel> resultList = result.getResults();
		final String resultNotificationCode = resultList.get(0).getCode();
		Assert.assertEquals(PLATINUM_NOTIFICATION_CODE, resultNotificationCode);
	}

	/**
	 * Integration test method of Message Center for Diamond Classification User Notification
	 */
	@Test
	public void testGetMessageNotificationByMappingForDiamondUser()
	{
		sessionService.setAttribute(AmwayapacCoreConstants.ACCOUNT_CLASSIFICATION_CODE,
				AccountClassificationEnum.DIAMOND_AND_ABOVE.toString());
		final AmwayAccountModel account = defaultAmwayApacAccountService.getAmwayAccount(TEST_USER_UID_ONE, "100").iterator()
				.next();
		final CustomerModel user = account.getPrimaryParty();
		userService.setCurrentUser(user);
		final List<AmwayNotificationUserActionStatus> statuses = new ArrayList<AmwayNotificationUserActionStatus>();
		statuses.add(AmwayNotificationUserActionStatus.valueOf(UNREAD));
		statuses.add(AmwayNotificationUserActionStatus.valueOf(READ));
		final SearchPageData<AmwayNotificationModel> result = defaultAmwayApacNotificationService.getNotifications(pageableData,
				user, statuses, "");
		final List<AmwayNotificationModel> resultList = result.getResults();
		final String resultNotificationCode = resultList.get(0).getCode();
		Assert.assertEquals(DIAMOND_NOTIFICATION_CODE, resultNotificationCode);
	}

	/**
	 * Integration test method of Message Center by code for Group User Notification
	 */
	@Test
	public void testGetMessageNotificationByCodeOfGroupUser()
	{
		final AmwayNotificationModel result = defaultAmwayApacNotificationService.getNotificationByCode(GROUP_NOTIFICATION_CODE);
		Assert.assertEquals(GROUP_NOTIFICATION_CODE, result.getCode());
	}

	/**
	 * Integration test method of Message Center by code for Platinum Classification User Notification
	 */
	@Test
	public void testGetMessageNotificationByCodeOfPlatinumUser()
	{
		final AmwayNotificationModel result = defaultAmwayApacNotificationService.getNotificationByCode(PLATINUM_NOTIFICATION_CODE);
		Assert.assertEquals(PLATINUM_NOTIFICATION_CODE, result.getCode());
	}

	/**
	 * Integration test method of Message Center by code for Diamond Classification User Notification
	 */
	@Test
	public void testGetMessageNotificationByCodeOfDiamondUser()
	{
		final AmwayNotificationModel result = defaultAmwayApacNotificationService.getNotificationByCode(DIAMOND_NOTIFICATION_CODE);
		Assert.assertEquals(DIAMOND_NOTIFICATION_CODE, result.getCode());
	}

	/**
	 * Integration test method of notification status change for Message Center
	 */
	@Test
	public void testChangeUserNotificationStatus()
	{
		final CustomerModel user = (CustomerModel) userService.getUserForUID(TEST_USER_UID_ONE);
		userService.setCurrentUser(user);
		final AmwayNotificationModel notification = defaultAmwayApacNotificationService
				.getNotificationByCode(GROUP_NOTIFICATION_CODE);
		defaultAmwayApacNotificationService.changeUserNotificationStatus(notification, user,
				AmwayNotificationUserActionStatus.valueOf(READ));

		final List<AmwayNotificationUserActionModel> resultsWithRead = defaultAmwayApacNotificationService
				.getNotificationActionByUserAndNotification(user, notification);
		Assert.assertEquals(AmwayNotificationUserActionStatus.valueOf(READ), resultsWithRead.get(0).getStatus());

		defaultAmwayApacNotificationService.changeUserNotificationStatus(notification, user,
				AmwayNotificationUserActionStatus.valueOf(UNREAD));
		final List<AmwayNotificationUserActionModel> resultsWithUnread = defaultAmwayApacNotificationService
				.getNotificationActionByUserAndNotification(user, notification);
		Assert.assertEquals(AmwayNotificationUserActionStatus.valueOf(UNREAD), resultsWithUnread.get(0).getStatus());
	}

	@Test
	public void testGetNotificationActionByUserAndNotification()
	{
		final CustomerModel user = (CustomerModel) userService.getUserForUID(TEST_USER_UID_ONE);
		final AmwayNotificationModel notification = defaultAmwayApacNotificationService
				.getNotificationByCode(GROUP_NOTIFICATION_CODE);
		defaultAmwayApacNotificationService.changeUserNotificationStatus(notification, user,
				AmwayNotificationUserActionStatus.valueOf(UNREAD));

		final List<AmwayNotificationUserActionModel> results = defaultAmwayApacNotificationService
				.getNotificationActionByUserAndNotification(user, notification);

		Assert.assertEquals(TEST_USER_UID_ONE, results.get(0).getUser().getUid());
		Assert.assertEquals(UNREAD, results.get(0).getStatus().toString());
	}
}
