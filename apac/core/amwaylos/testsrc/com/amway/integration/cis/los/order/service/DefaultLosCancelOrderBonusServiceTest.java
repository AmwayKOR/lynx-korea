package com.amway.integration.cis.los.order.service;

import static org.junit.Assert.assertNotNull;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.ServicelayerTest;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.annotation.Resource;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.los.data.BonusOrderRequestData;
import com.amway.core.los.data.BonusOrderResultData;
import com.amway.core.los.data.TransactionData;
import com.amway.integration.cis.los.bonusadjustment.service.DefaultLosBonusAdjustmentServiceTest;
import com.amway.integration.cis.los.orderbonusservice.mock.impl.MockOrderBonusService;


@UnitTest
public class DefaultLosCancelOrderBonusServiceTest extends ServicelayerTest
{

	private static final String ERROR = "ERROR";
	private final static Logger LOG = Logger.getLogger(DefaultLosBonusAdjustmentServiceTest.class);

	private static final String ABO_NO = "7001002605";

	private static final String PROFIT = "0.0";

	private static final String AMWAY_BR_WHS_65 = "amway-br-whs-65";

	private static final String CHANNEL = "Web";

	private static final String CURRENCY_CODE = "BRL";

	private static final String SRV0101 = "SRV0101";

	private static final String HYBRIS = "Hybris";

	private static final String AFFlI_NO = "170";

	private static final String BR = "BR";

	@Resource(name = "mockOrderBonusService")
	private MockOrderBonusService defaultLosCancelOrderBonusService;

	private BonusOrderRequestData bonusRequestData;
	private TransactionData transactionData;


	@Before
	public void setUp()
	{
		bonusRequestData = new BonusOrderRequestData();
		final Date systemDate = new Date();
		transactionData = new TransactionData();

		bonusRequestData.setTimestamp(getDate(systemDate));
		bonusRequestData.setIsoCountryCode(BR);
		bonusRequestData.setSourceApplication(HYBRIS);
		bonusRequestData.setId(SRV0101);
		bonusRequestData.setAffiliateCode(AFFlI_NO);
		transactionData.setAboId(ABO_NO);
		transactionData.setBusinessVolume(new Double(10.10));
		transactionData.setPointValue(new Double(10.10));
		transactionData.setIsoCurrencyCode(CURRENCY_CODE);
		transactionData.setChannel(CHANNEL);
		transactionData.setReferenceInvoice(ABO_NO);
		transactionData.setReferenceId(ABO_NO);
		transactionData.setProfit(Double.valueOf(PROFIT));
		transactionData.setWarehouseCode(AMWAY_BR_WHS_65);
		bonusRequestData.setTransactions(Arrays.asList(transactionData));
	}

	@Test
	public void shouldCancelLosOrderBonusTest()
	{
		final BonusOrderResultData responce = defaultLosCancelOrderBonusService.process(bonusRequestData);
		assertNotNull(responce);
		Assert.assertFalse(ERROR.equals(responce.getDecision()));
	}

	private XMLGregorianCalendar getDate(final Date systemDate)
	{
		try
		{
			final GregorianCalendar gregory = new GregorianCalendar();
			gregory.setTime(systemDate);
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
		}
		catch (final DatatypeConfigurationException e)
		{
			LOG.error("Couldnt create XMLGregorianCalendar ", e);
		}
		return null;
	}
}
