/**
 *
 */
package com.amway.core.orderperiod.job;

import com.amway.core.enums.AmwayPeriodTypeEnum;
import com.amway.core.model.AmwayOrderPeriodModel;
import com.amway.core.orderperiod.services.impl.DefaultAmwayOrderPeriodService;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.acceleratorservices.email.EmailService;
import de.hybris.platform.acceleratorservices.model.email.EmailAddressModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.util.Config;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

import static org.mockito.Mockito.when;


@SuppressWarnings("deprecation")
@Ignore("HE-210 - removing powermock and disabling test")
@UnitTest
//@RunWith(PowerMockRunner.class)
//@PrepareForTest(
//{ DefaultAmwayOrderPeriodService.class, EmailService.class, I18NService.class, AmwayOrderPeriodModel.class, Config.class,
//		ModelService.class })
public class AmwayOrderPeriodStatusJobPerformableUnitTest
{
	private static final String TOEMAIL = "test1@test.com";
	private static final String FROMEMAIL = "test3@test.com";
	@InjectMocks
	private final AmwayOrderPeriodStatusJobPerformable job = new AmwayOrderPeriodStatusJobPerformable();
	@Mock
	private DefaultAmwayOrderPeriodService orderPeriodService;
	@Mock
	private EmailService emailService;
	@Mock
	private I18NService i18nService;
	@Mock
	private ModelService modelService;
	private AmwayOrderPeriodModel orderPeriod1, orderPeriod2, orderPeriod3;
	private CronJobModel cronJob;
	private EmailAddressModel emailTo, emailFrom;
	private Calendar cal;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

		job.setOrderPeriodService(orderPeriodService);
		job.setEmailService(emailService);
		job.setI18nService(i18nService);
		job.setModelService(modelService);
		cronJob = new CronJobModel();
		cronJob.setEmailAddress("test@test.com");

		cal = Calendar.getInstance(Locale.US);
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		orderPeriod1 = Mockito.spy(new AmwayOrderPeriodModel());
		orderPeriod1.setStartDate(cal.getTime());
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		orderPeriod1.setEndDate(cal.getTime());
		orderPeriod1.setStatus(AmwayPeriodTypeEnum.CLOSED);

		cal = Calendar.getInstance(Locale.US);
		orderPeriod2 = Mockito.spy(new AmwayOrderPeriodModel());
		cal.add(Calendar.DAY_OF_MONTH, -1);
		orderPeriod2.setEndDate(cal.getTime());
		cal.set(Calendar.DAY_OF_MONTH, 1);
		orderPeriod2.setStartDate(cal.getTime());
		orderPeriod2.setStatus(AmwayPeriodTypeEnum.ACTIVE);

		cal = Calendar.getInstance(Locale.US);
		orderPeriod3 = Mockito.spy(new AmwayOrderPeriodModel());
		orderPeriod3.setStartDate(cal.getTime());
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		orderPeriod3.setEndDate(cal.getTime());
		orderPeriod3.setStatus(AmwayPeriodTypeEnum.INACTIVE);

		emailTo = Mockito.mock(EmailAddressModel.class);
		emailFrom = Mockito.mock(EmailAddressModel.class);

		when(emailTo.getEmailAddress()).thenReturn(TOEMAIL);
		when(emailFrom.getEmailAddress()).thenReturn(FROMEMAIL);

		when(orderPeriodService.findAllOrderPeriodsForSite()).thenReturn(Arrays.asList(orderPeriod1, orderPeriod2, orderPeriod3));
		when(emailService.getOrCreateEmailAddressForEmail(cronJob.getEmailAddress(), cronJob.getEmailAddress()))
				.thenReturn(emailTo);
		when(i18nService.getCurrentLocale()).thenReturn(Locale.US);

		PowerMockito.mockStatic(Config.class);
		PowerMockito.when(Config.getString("eom.fromAddress.email", "aiu0256@amway.com")).thenReturn("aiu0256@amway.com");
		PowerMockito.when(Config.getString("eom.fromAddress.email", "aiu0256@amway.com")).thenReturn("aiu0256@amway.com");
		when(emailService.getOrCreateEmailAddressForEmail(Config.getString("eom.fromAddress.email", "aiu0256@amway.com"),
				"AmwayOrderPeriodStatusJob")).thenReturn(emailFrom);
		PowerMockito.when(Config.getParameter("orderPeriod.status.subject")).thenReturn("Subject");

	}

	/**
	 * Test method for
	 * {@link com.amway.core.orderperiod.job.AmwayOrderPeriodStatusJobPerformable#perform(de.hybris.platform.cronjob.model.CronJobModel)}
	 * .
	 */
	@Test
	public void testPerformCronJobModel()
	{
		final PerformResult performResult = job.perform(cronJob);
		Assert.assertEquals(CronJobResult.SUCCESS, performResult.getResult());
		Assert.assertEquals(CronJobStatus.FINISHED, performResult.getStatus());
		//		fail("not yet implemented");
	}

}
