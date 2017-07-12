/**
 *
 */
package com.amway.core.processes.services.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.acceleratorservices.email.CMSEmailPageService;
import de.hybris.platform.acceleratorservices.email.EmailGenerationService;
import de.hybris.platform.acceleratorservices.email.EmailService;
import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.acceleratorservices.process.strategies.ProcessContextResolutionStrategy;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.processengine.model.BusinessProcessModel;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.core.processes.dao.AmwayFetchProcessStatusDao;


@UnitTest
public class DefaultAmwayFetchProcessStatusServiceUnitTest
{
	@InjectMocks
	private final DefaultAmwayFetchProcessStatusService statusService = new DefaultAmwayFetchProcessStatusService();
	@Mock
	private AmwayFetchProcessStatusDao fetchProcessStatusDao;
	@Mock
	private CMSEmailPageService emailPageService;
	@Mock
	private ProcessContextResolutionStrategy contextResolutionStrategy;
	@Mock
	private EmailGenerationService emailGenerationService;
	@Mock
	private EmailService emailService;
	List<BusinessProcessModel> businessProcessModels;
	private BusinessProcessModel process1, process2;
	private static final String PROCESS_STATUS_CODE = "processStatus";
	private static final String RETURN_STATUS_CODE = "processStatus";
	private Date endDate;
	private final String FRONTENDTEMPLATENAME = "testTemplate";
	private CatalogVersionModel contentCatalogVersion;
	private EmailPageModel emailPageModel;
	private EmailMessageModel emailMessageModel;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		statusService.setFrontendTemplateName(FRONTENDTEMPLATENAME);
		endDate = new Date();
		process1 = Mockito.mock(OrderProcessModel.class);
		process2 = Mockito.mock(OrderProcessModel.class);
		BDDMockito.when(process1.getProcessDefinitionName()).thenReturn("process1");
		BDDMockito.when(process1.getProcessDefinitionName()).thenReturn("process2");
		contentCatalogVersion = Mockito.mock(CatalogVersionModel.class);
		emailPageModel = Mockito.mock(EmailPageModel.class);
		emailMessageModel = Mockito.mock(EmailMessageModel.class);
		BDDMockito.when(fetchProcessStatusDao.getListOfAllFailedProcessStatus(PROCESS_STATUS_CODE, endDate))
				.thenReturn(Arrays.asList(process1, process2));
		BDDMockito.when(contextResolutionStrategy.getContentCatalogVersion(process1)).thenReturn(contentCatalogVersion);
		BDDMockito.when(contextResolutionStrategy.getContentCatalogVersion(process2)).thenReturn(contentCatalogVersion);

	}

	/**
	 * Test method for
	 * {@link com.amway.core.processes.services.impl.DefaultAmwayFetchProcessStatusService#sendEmailForFailedProcesses(java.lang.String, java.lang.String, java.util.Date)}
	 * .
	 */
	@Test
	public void testSendEmailForFailedProcessesEmailPageNull()
	{
		BDDMockito.when(emailPageService.getEmailPageForFrontendTemplate(FRONTENDTEMPLATENAME, contentCatalogVersion))
				.thenReturn(null);
		Assert.assertFalse(statusService.sendEmailForFailedProcesses(PROCESS_STATUS_CODE, RETURN_STATUS_CODE, endDate));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.processes.services.impl.DefaultAmwayFetchProcessStatusService#sendEmailForFailedProcesses(java.lang.String, java.lang.String, java.util.Date)}
	 * .
	 */
	@Test
	public void testSendEmailForFailedProcessesMessageNull()
	{
		BDDMockito.when(emailPageService.getEmailPageForFrontendTemplate(FRONTENDTEMPLATENAME, contentCatalogVersion))
				.thenReturn(emailPageModel);
		BDDMockito.when(emailGenerationService.generate(process1, emailPageModel)).thenReturn(null);
		Assert.assertFalse(statusService.sendEmailForFailedProcesses(PROCESS_STATUS_CODE, RETURN_STATUS_CODE, endDate));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.processes.services.impl.DefaultAmwayFetchProcessStatusService#sendEmailForFailedProcesses(java.lang.String, java.lang.String, java.util.Date)}
	 * .
	 */
	@Test
	public void testSendEmailForFailedProcessesFailed()
	{
		BDDMockito.when(emailPageService.getEmailPageForFrontendTemplate(FRONTENDTEMPLATENAME, contentCatalogVersion))
				.thenReturn(emailPageModel);
		BDDMockito.when(emailGenerationService.generate(process1, emailPageModel)).thenReturn(emailMessageModel);
		BDDMockito.when(Boolean.valueOf(emailService.send(emailMessageModel))).thenReturn(Boolean.FALSE);
		Assert.assertFalse(statusService.sendEmailForFailedProcesses(PROCESS_STATUS_CODE, RETURN_STATUS_CODE, endDate));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.processes.services.impl.DefaultAmwayFetchProcessStatusService#sendEmailForFailedProcesses(java.lang.String, java.lang.String, java.util.Date)}
	 * .
	 */
	@Test
	public void testSendEmailForFailedProcessesSuccess()
	{
		BDDMockito.when(emailPageService.getEmailPageForFrontendTemplate(FRONTENDTEMPLATENAME, contentCatalogVersion))
				.thenReturn(emailPageModel);
		BDDMockito.when(emailGenerationService.generate(process1, emailPageModel)).thenReturn(emailMessageModel);
		BDDMockito.when(Boolean.valueOf(emailService.send(emailMessageModel))).thenReturn(Boolean.TRUE);
		Assert.assertTrue(statusService.sendEmailForFailedProcesses(PROCESS_STATUS_CODE, RETURN_STATUS_CODE, endDate));
	}
}
