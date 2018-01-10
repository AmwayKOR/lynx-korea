/**
 *
 */
package com.amway.report.service.impl;

import com.amway.report.dao.AmwayReportDao;
import com.amway.report.enums.AmwayReportType;
import com.amway.report.exceptions.AmwayReportFormatException;
import com.amway.report.generation.service.AmwayReportGenerationService;
import com.amway.report.model.AmwayReportModel;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.cockpit.reports.exceptions.JasperReportExportException;
import de.hybris.platform.cockpit.reports.model.JasperMediaModel;
import de.hybris.platform.core.PK;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.store.BaseStoreModel;
import net.sf.jasperreports.engine.JRException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessResourceFailureException;

import java.io.ByteArrayOutputStream;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;


/**
 * The Unit test class for DefaultAmwayReportService
 *
 * @author arjunduggal
 */
@UnitTest
public class DefaultAmwayReportServiceTest
{

	private final static String REPORT_CODE = "salesSummary";

	@InjectMocks
	private DefaultAmwayReportService amwayReportService;

	@Mock
	private AmwayReportDao amwayReportDao;

	@Mock
	private AmwayReportGenerationService reportGenerationService;

	private BaseStoreModel baseStore;
	private AmwayReportModel report;
	private JasperMediaModel jasperMedia;
	private Map<String, Object> reportFilterParamsData;


	@Before
	public void prepare() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		report = Mockito.mock(AmwayReportModel.class);
		baseStore = Mockito.mock(BaseStoreModel.class);
		jasperMedia = Mockito.mock(JasperMediaModel.class);
		reportFilterParamsData = Mockito.mock(HashMap.class);

		given(report.getCode()).willReturn(REPORT_CODE);
		given(report.getBaseStores()).willReturn(Collections.singletonList(baseStore));
		given(report.getJasperMedia()).willReturn(jasperMedia);
		given(baseStore.getReports()).willReturn(new HashSet<AmwayReportModel>(Collections.singletonList(report)));
		given(baseStore.getPk()).willReturn(PK.fromLong(12345L));
	}

	@Test
	public void testGetAllReportsWithResult()
	{
		final Set<AmwayReportModel> reports = new HashSet<AmwayReportModel>(Collections.singletonList(report));
		final Set<AmwayReportModel> result = amwayReportService.getAllReports(baseStore);
		assertTrue(result.size() == reports.size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetAllReportsIfBaseStoreIsNull()
	{
		amwayReportService.getAllReports(null);
	}

	@Test
	public void testGetReportForCodeWithResult()
	{
		final List<AmwayReportModel> reports = Collections.singletonList(report);
		when(amwayReportDao.getReportByReportCode(REPORT_CODE, baseStore)).thenReturn(reports);

		final AmwayReportModel result = amwayReportService.getReportByReportCode(REPORT_CODE, baseStore);
		assertNotNull("No Store found ", result);
		assertEquals(report, result);
	}

	@Test(expected = UnknownIdentifierException.class)
	public void testGetReportForCodeIfCodeIsUnknown()
	{
		when(amwayReportDao.getReportByReportCode(REPORT_CODE, baseStore)).thenReturn(Collections.emptyList());
		amwayReportService.getReportByReportCode(REPORT_CODE, baseStore);
	}

	@Test(expected = AmbiguousIdentifierException.class)
	public void testGetReportForCodeIfCodeIsAmbigious()
	{
		final List<AmwayReportModel> reports = Arrays.asList(report, report);
		when(amwayReportDao.getReportByReportCode(REPORT_CODE, baseStore)).thenReturn(reports);
		amwayReportService.getReportByReportCode(REPORT_CODE, baseStore);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetReportForCodeIfCodeIsNull()
	{
		amwayReportService.getReportByReportCode(null, baseStore);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetReportForCodeIfBaseStoreIsNull()
	{
		amwayReportService.getReportByReportCode(REPORT_CODE, null);
	}

	@Test
	public void testExportReportWithResult() throws AmwayReportFormatException, DataAccessResourceFailureException, JRException
	{
		final List<AmwayReportModel> reports = Collections.singletonList(report);
		final AmwayReportType reportType = AmwayReportType.EXCEL;

		final byte[] reportData = (new ByteArrayOutputStream()).toByteArray();

		when(amwayReportDao.getReportByReportCode(REPORT_CODE, baseStore)).thenReturn(reports);
		when(reportGenerationService.generateReport(eq(report.getJasperMedia()), eq(reportType), any(Map.class)))
				.thenReturn(reportData);

		final byte[] result = amwayReportService.exportReport(REPORT_CODE, reportType, baseStore, reportFilterParamsData);
		assertNotNull("No Report Data found ", result);
		assertEquals(reportData, result);
	}

	@Test(expected = AmwayReportFormatException.class)
	public void testExportReportIfJRExceptionOccurs()
			throws AmwayReportFormatException, DataAccessResourceFailureException, JRException
	{
		final List<AmwayReportModel> reports = Collections.singletonList(report);
		final AmwayReportType reportType = AmwayReportType.EXCEL;

		when(amwayReportDao.getReportByReportCode(REPORT_CODE, baseStore)).thenReturn(reports);
		when(reportGenerationService.generateReport(eq(report.getJasperMedia()), eq(reportType), any(Map.class)))
				.thenThrow(new JRException("error"));
		amwayReportService.exportReport(REPORT_CODE, reportType, baseStore, reportFilterParamsData);
	}

	@Test(expected = AmwayReportFormatException.class)
	public void testExportReportIfDataAccessResourceFailureExceptionOccurs()
			throws AmwayReportFormatException, DataAccessResourceFailureException, JRException
	{
		final List<AmwayReportModel> reports = Collections.singletonList(report);
		final AmwayReportType reportType = AmwayReportType.EXCEL;

		when(amwayReportDao.getReportByReportCode(REPORT_CODE, baseStore)).thenReturn(reports);
		when(reportGenerationService.generateReport(eq(report.getJasperMedia()), eq(reportType), any(Map.class)))
				.thenThrow(new DataAccessResourceFailureException("error"));
		amwayReportService.exportReport(REPORT_CODE, reportType, baseStore, reportFilterParamsData);
	}

	@Test(expected = AmwayReportFormatException.class)
	public void testExportReportIfExportExceptionOccurs()
			throws AmwayReportFormatException, DataAccessResourceFailureException, JRException
	{
		final List<AmwayReportModel> reports = Collections.singletonList(report);
		final AmwayReportType reportType = AmwayReportType.EXCEL;

		when(amwayReportDao.getReportByReportCode(REPORT_CODE, baseStore)).thenReturn(reports);
		when(reportGenerationService.generateReport(eq(report.getJasperMedia()), eq(reportType), any(Map.class)))
				.thenThrow(new JasperReportExportException(new JRException("error")));
		amwayReportService.exportReport(REPORT_CODE, reportType, baseStore, reportFilterParamsData);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExportReportIfReportCodeIsNull()
			throws AmwayReportFormatException, DataAccessResourceFailureException, JRException
	{
		final AmwayReportType reportType = AmwayReportType.EXCEL;
		amwayReportService.exportReport(null, reportType, baseStore, reportFilterParamsData);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExportReportIfReportTypeIsNull()
			throws AmwayReportFormatException, DataAccessResourceFailureException, JRException
	{
		amwayReportService.exportReport(REPORT_CODE, null, baseStore, reportFilterParamsData);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExportReportIfBaseStoreIsNull()
			throws AmwayReportFormatException, DataAccessResourceFailureException, JRException
	{
		final AmwayReportType reportType = AmwayReportType.EXCEL;
		amwayReportService.exportReport(REPORT_CODE, reportType, null, reportFilterParamsData);
	}

	@Test
	public void testExportReportForNullFilterParameters()
			throws AmwayReportFormatException, DataAccessResourceFailureException, JRException
	{
		final List<AmwayReportModel> reports = Collections.singletonList(report);
		final AmwayReportType reportType = AmwayReportType.EXCEL;
		final byte[] reportData = (new ByteArrayOutputStream()).toByteArray();

		when(amwayReportDao.getReportByReportCode(REPORT_CODE, baseStore)).thenReturn(reports);
		when(reportGenerationService.generateReport(eq(report.getJasperMedia()), eq(reportType), any(Map.class)))
				.thenReturn(reportData);

		final byte[] result = amwayReportService.exportReport(REPORT_CODE, reportType, baseStore, null);
		assertNotNull("No Report Data found ", result);
		assertEquals(reportData, result);
	}

}
