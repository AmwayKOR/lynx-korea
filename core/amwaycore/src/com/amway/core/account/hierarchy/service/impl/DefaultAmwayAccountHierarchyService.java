/**
 *
 */
package com.amway.core.account.hierarchy.service.impl;

import de.hybris.platform.core.Registry;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.amway.core.account.hierarchy.service.AmwayAccountHierarchyService;
import com.amway.core.facades.account.los.data.LosHierarchyData;
import com.amway.core.los.async.service.LosAsynTask;
import com.amway.core.los.data.LosAccountDetailDownlinesResponse;
import com.amway.core.los.data.LosAccountDetailResponseData;
import com.amway.core.los.data.LosAccountDownlinesResponse;
import com.amway.core.los.data.LosAccountRequestData;
import com.amway.core.los.data.LosAccountResponseData;
import com.amway.core.los.service.LosService;
import com.google.common.collect.Lists;


/**
 * Default implementation for Amway Account Hierarchy Service
 */
public class DefaultAmwayAccountHierarchyService implements AmwayAccountHierarchyService
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayAccountHierarchyService.class);//NOPMD

	private static final String STR_COMMA = ",";

	private BaseStoreService baseStoreService;
	private BaseSiteService baseSiteService;
	private LosService<LosAccountRequestData, LosAccountResponseData> losAccountService;
	private LosService<LosAccountRequestData, LosAccountDetailResponseData> losAccountDetailService;
	private ConfigurationService configurationService;

	@Override
	public Collection<LosHierarchyData> getAccountHierarchy(final String bonusPeriod, final String aboNumn)
	{
		final String affNumber = getBaseStoreService().getCurrentBaseStore().getAffiliateNumber();

		final LosAccountRequestData losAccountRequestData = new LosAccountRequestData();
		final String abo = affNumber + "-" + aboNumn;
		losAccountRequestData.setAbo(abo);
		losAccountRequestData.setRequestingAbo(abo);
		losAccountRequestData.setDepth("0");
		losAccountRequestData.setBonusPeriod(bonusPeriod);

		final LosAccountResponseData losAccountResponseData = getLosAccountService().process(losAccountRequestData); //first call
		final Map<String, LosHierarchyData> losHierarcyData = new HashMap<String, LosHierarchyData>();
		final List<String> losAboAff = new ArrayList<String>();
		final List<LosHierarchyData> losHierarcyDataList = new ArrayList<LosHierarchyData>();
		if (1 == losAccountResponseData.getReturnCode())
		{
			extractDownlineData(losAccountResponseData.getLosAccountResponseListData(), losHierarcyData, losHierarcyDataList,
					losAboAff, 1);

			extractDownlineDetailsAsync(bonusPeriod, abo, losHierarcyData, losAboAff);
		}
		return losHierarcyDataList;
	}


	/**
	 * @param bonusPeriod
	 * @param abo
	 * @param losHierarcyData
	 */
	private void extractDownlineDetails(final String bonusPeriod, final String abo,
			final Map<String, LosHierarchyData> losHierarcyData, final List<String> losAboAff)
	{
		final List<List<String>> chunkListOfAboAndAff = Lists
				.partition(losAboAff, getConfigurationService().getConfiguration().getInt("los.stmt.downline.chunk.size", 50));
		for (final List<String> aboAndAffNumber : chunkListOfAboAndAff)
		{
			final LosAccountRequestData downLinelosAccReqData = new LosAccountRequestData();
			downLinelosAccReqData.setAbo(StringUtils.join(aboAndAffNumber, STR_COMMA));
			downLinelosAccReqData.setRequestingAbo(abo);
			downLinelosAccReqData.setDepth("1");
			downLinelosAccReqData.setBonusPeriod(bonusPeriod);
			downLinelosAccReqData.setBonusPeriodCount(String.valueOf(1));
			downLinelosAccReqData.setGetVolume(Boolean.TRUE);
			downLinelosAccReqData.setGetExtAttributes(Boolean.TRUE);
			downLinelosAccReqData.setGetSponsorStats(Boolean.TRUE);
			downLinelosAccReqData.setGetQualification(Boolean.TRUE);





			final LosAccountDetailResponseData accountDetailResponseData = getLosAccountDetailService()
					.process(downLinelosAccReqData);
			if (1 == accountDetailResponseData.getReturnCode())
			{
				for (final LosAccountDetailDownlinesResponse losAccountDetailDownlinesResponse : accountDetailResponseData
						.getLosAccountDetailResponseListData())
				{
					final LosHierarchyData downLineLosHierarchyData = losHierarcyData
							.get(losAccountDetailDownlinesResponse.getAboNo());
					downLineLosHierarchyData.setPv(extractSafe(String.valueOf(losAccountDetailDownlinesResponse.getPpv())));
					downLineLosHierarchyData.setGrpPv(extractSafe(String.valueOf(losAccountDetailDownlinesResponse.getGpv())));
					downLineLosHierarchyData.setPrimaryEmail(extractSafe(losAccountDetailDownlinesResponse.getEmail()));
					downLineLosHierarchyData.setPrimaryTelephone(extractSafe(losAccountDetailDownlinesResponse.getPhone()));
					downLineLosHierarchyData
							.setRegistrationDate(extractSafe(String.valueOf(losAccountDetailDownlinesResponse.getBusinessEntryDate())));
					downLineLosHierarchyData
							.setRenewed(extractSafe(String.valueOf(losAccountDetailDownlinesResponse.getRenewalDate())));
				}
			}
		}

	}


	private void extractDownlineDetailsAsync(final String bonusPeriod, final String abo,
			final Map<String, LosHierarchyData> losHierarcyData, final List<String> losAboAff)
	{
		final long startTime = Calendar.getInstance().getTimeInMillis();
		final List<List<String>> chunkListOfAboAndAff = Lists
				.partition(losAboAff, getConfigurationService().getConfiguration().getInt("los.stmt.downline.chunk.size", 50));
		final ExecutorService executorService = Executors
				.newFixedThreadPool(getConfigurationService().getConfiguration().getInt("los.stmt.downline.thread.size", 1));
		final List<Future<LosAccountDetailResponseData>> responseList = new ArrayList<Future<LosAccountDetailResponseData>>();
		final String currentSiteUid = getBaseSiteService().getCurrentBaseSite().getUid();
		for (final List<String> aboAndAffNumber : chunkListOfAboAndAff)
		{
			final LosAccountRequestData downLinelosAccReqData = new LosAccountRequestData();
			downLinelosAccReqData.setAbo(StringUtils.join(aboAndAffNumber, STR_COMMA));
			downLinelosAccReqData.setRequestingAbo(abo);
			downLinelosAccReqData.setDepth("1");
			downLinelosAccReqData.setBonusPeriod(bonusPeriod);
			downLinelosAccReqData.setBonusPeriodCount(String.valueOf(1));
			downLinelosAccReqData.setGetVolume(Boolean.TRUE);
			downLinelosAccReqData.setGetExtAttributes(Boolean.TRUE);
			downLinelosAccReqData.setGetSponsorStats(Boolean.TRUE);
			downLinelosAccReqData.setGetQualification(Boolean.TRUE);

			final LosAsynTask asynTask = Registry.getApplicationContext().getBean("asynLosAccountDetailTask", LosAsynTask.class);
			asynTask.setRequest(downLinelosAccReqData);
			asynTask.setBaseStoreUid(currentSiteUid);
			responseList.add(executorService.submit(asynTask));
		}

		for (final Future<LosAccountDetailResponseData> response : responseList)
		{
			try
			{
				final LosAccountDetailResponseData accountDetailResponseData = response.get();
				if (1 == accountDetailResponseData.getReturnCode())
				{
					for (final LosAccountDetailDownlinesResponse losAccountDetailDownlinesResponse : accountDetailResponseData
							.getLosAccountDetailResponseListData())
					{
						final LosHierarchyData downLineLosHierarchyData = losHierarcyData
								.get(losAccountDetailDownlinesResponse.getAboNo());
						downLineLosHierarchyData.setPv(extractSafe(String.valueOf(losAccountDetailDownlinesResponse.getPpv())));
						downLineLosHierarchyData.setGrpPv(extractSafe(String.valueOf(losAccountDetailDownlinesResponse.getGpv())));
						downLineLosHierarchyData.setPrimaryEmail(extractSafe(losAccountDetailDownlinesResponse.getEmail()));
						downLineLosHierarchyData.setPrimaryTelephone(extractSafe(losAccountDetailDownlinesResponse.getPhone()));
						downLineLosHierarchyData.setRegistrationDate(
								extractSafe(String.valueOf(losAccountDetailDownlinesResponse.getBusinessEntryDate())));
						downLineLosHierarchyData
								.setRenewed(extractSafe(String.valueOf(losAccountDetailDownlinesResponse.getRenewalDate())));
					}
				}
			}
			catch (final Exception e)
			{
				LOG.error(e.getMessage(), e);
				response.cancel(true);
			}
		}
		executorService.shutdownNow();
		LOG.info("extracting downline took :" + (Calendar.getInstance().getTimeInMillis() - startTime) + "ms");
	}

	/**
	 * @param losHierarcyData
	 * @param list
	 */
	private void extractDownlineData(final List<LosAccountDownlinesResponse> list,
			final Map<String, LosHierarchyData> losHierarcyData, final List<LosHierarchyData> losHierarcyDataList,
			final List<String> losAboAff, final int downlineLevel)
	{
		for (final LosAccountDownlinesResponse losAccountDownlinesData : list)
		{
			final LosHierarchyData data = new LosHierarchyData();
			final String aboNo = losAccountDownlinesData.getAboNo();
			losHierarcyData.put(aboNo, data);
			losHierarcyDataList.add(data);
			losAboAff.add(losAccountDownlinesData.getAff() + "-" + aboNo);

			data.setCode(aboNo);
			data.setCountry(extractSafe(losAccountDownlinesData.getCountry()));
			data.setDepth(String.valueOf(downlineLevel));
			data.setHandedAddendum(StringUtils.EMPTY);
			data.setIcon(StringUtils.EMPTY);
			data.setLastName(StringUtils.EMPTY);
			data.setName(extractSafe(losAccountDownlinesData.getName()));
			data.setNewNumber(StringUtils.EMPTY);
			data.setOfficialIdentification(StringUtils.EMPTY);
			data.setSponsorshipRequest(StringUtils.EMPTY);
			data.setLastOrderDate(StringUtils.EMPTY);
			if (losAccountDownlinesData.isHasDownlines())
			{
				data.setDownlines(new ArrayList<LosHierarchyData>());
				extractDownlineData(losAccountDownlinesData.getDownlines(), losHierarcyData, data.getDownlines(), losAboAff,
						downlineLevel + 1);
			}
		}
	}

	private String extractSafe(final String input)
	{
		return StringUtils.isNotBlank(input) && !"null".equals(input) ? input : StringUtils.EMPTY;
	}


	/**
	 * @return baseStoreService
	 */
	public BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	/**
	 * @param baseStoreService the baseStoreService to set
	 */
	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

	/**
	 * @return the losAccountService
	 */
	public LosService<LosAccountRequestData, LosAccountResponseData> getLosAccountService()
	{
		return losAccountService;
	}


	/**
	 * @param losAccountService the losAccountService to set
	 */
	public void setLosAccountService(final LosService<LosAccountRequestData, LosAccountResponseData> losAccountService)
	{
		this.losAccountService = losAccountService;
	}


	/**
	 * @return the configurationService
	 */
	public ConfigurationService getConfigurationService()
	{
		return configurationService;
	}


	/**
	 * @param configurationService the configurationService to set
	 */
	public void setConfigurationService(final ConfigurationService configurationService)
	{
		this.configurationService = configurationService;
	}


	/**
	 * @return the losAccountDetailService
	 */
	public LosService<LosAccountRequestData, LosAccountDetailResponseData> getLosAccountDetailService()
	{
		return losAccountDetailService;
	}


	/**
	 * @param losAccountDetailService the losAccountDetailService to set
	 */
	public void setLosAccountDetailService(
			final LosService<LosAccountRequestData, LosAccountDetailResponseData> losAccountDetailService)
	{
		this.losAccountDetailService = losAccountDetailService;
	}


	/**
	 * @return the baseSiteService
	 */
	public BaseSiteService getBaseSiteService()
	{
		return baseSiteService;
	}


	/**
	 * @param baseSiteService the baseSiteService to set
	 */
	public void setBaseSiteService(final BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}
}
