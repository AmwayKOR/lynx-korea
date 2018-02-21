package com.amway.integration.cis.los.populators;

import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.commercefacades.user.data.RegionData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.store.services.BaseStoreService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.lf5.util.DateFormatManager;
import org.joda.time.DateTime;

import com.amway.core.enums.AmwayBusinessNature;
import com.amway.core.los.data.CurrentAwardDetails;
import com.amway.core.los.data.LosAccountDetailDownlinesResponse;
import com.amway.core.los.data.LosAccountDetailResponseData;
import com.amway.core.los.data.LosBonusPeriodData;
import com.amway.core.util.AmwayPeriodHelper;
import com.amway.integration.cis.los.pojo.AboLosAddress;
import com.amway.integration.cis.los.pojo.AboLosAward;
import com.amway.integration.cis.los.pojo.AboLosBonusPeriodSponsoringStatistic;
import com.amway.integration.cis.los.pojo.AboLosBonusPeriodStatistic;
import com.amway.integration.cis.los.pojo.AboLosExtended;
import com.amway.integration.cis.los.pojo.AboLosName;
import com.amway.integration.cis.los.pojo.AboLosQualifications;
import com.amway.integration.cis.los.pojo.BonusVolume;
import com.amway.integration.cis.los.pojo.CountryAward;
import com.amway.integration.cis.los.pojo.CountryOrderCount;
import com.amway.integration.cis.los.pojo.DetailResponse;
import com.amway.integration.cis.los.pojo.LineOfSponsorshipBusinessDetail;


/**
 * populator to populate DetailResponse to LosAccountDetailResponseData
 */
public class LosAccountDetailOutputPopulator implements Populator<DetailResponse, LosAccountDetailResponseData>
{
	private static final Logger LOG = Logger.getLogger(LosAccountDetailOutputPopulator.class);
	private static final String PERSONAL = "001";
	private static final String PERSONAL_TRANSACTION = "003";
	private static final String GROUP = "005";
	private static final String MEMBER = "007";
	private static final String INACTIVE_VOLUME = "009";
	private static final String AWARD = "015";
	private static final String ANNUAL_PERSONAL = "017";
	private static final String RUBY = "019";
	private static final String LEADERSHIP_GROUP = "105";
	private BaseStoreService baseStoreService;
	private EnumerationService enumerationService;

	public BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final DetailResponse source, final LosAccountDetailResponseData target) throws ConversionException
	{
		final String storeCurrency = getBaseStoreService().getCurrentBaseStore() != null ?
				getBaseStoreService().getCurrentBaseStore().getDefaultCurrency().getIsocode() :
				"BRL";
		final List<LosAccountDetailDownlinesResponse> downlinesResponseList = new ArrayList<LosAccountDetailDownlinesResponse>();

		if (CollectionUtils.isNotEmpty(source.getDetail()))
		{
			for (final LineOfSponsorshipBusinessDetail businessDetail : source.getDetail())
			{
				final LosAccountDetailDownlinesResponse downlinesResponse = new LosAccountDetailDownlinesResponse();
				if (businessDetail.getABONo() != null)
				{
					downlinesResponse.setAboNo(businessDetail.getABONo().toString());
				}

				populateBonusPeriodStats(businessDetail.getBonusPeriodStats(), downlinesResponse, storeCurrency);
				populateSponsorStats(businessDetail.getSponsoringStats(), downlinesResponse);
				if (businessDetail.getHighestAward() != null)
				{
					populateHighestAward(businessDetail.getHighestAward(), downlinesResponse);
				}
				if (businessDetail.getExtended() != null)
				{
					populateExtended(businessDetail.getExtended(), downlinesResponse);
				}

				downlinesResponseList.add(downlinesResponse);
				if (businessDetail.getCurrentAward() != null)
				{
					populateCurrentAward(businessDetail.getCurrentAward(), target);
				}
				if (businessDetail.getQualifications() != null)
				{
					populateQaulifications(businessDetail.getQualifications(), downlinesResponse);
				}
			}
		}

		target.setLosAccountDetailResponseListData(downlinesResponseList);
		target.setReturnCode(1);
		target.setReturnMessage("Valid Result.");
	}

	/**
	 * @param qualificationsList
	 * @param downlinesResponse
	 */
	private void populateQaulifications(final List<AboLosQualifications> qualificationsList,
			final LosAccountDetailDownlinesResponse downlinesResponse)
	{
		//this is if a certain bonus period is requested then it has to allow that bonus period pv to be populated
		final String currentBonusPeriod =
				qualificationsList.size() == 1 ? qualificationsList.get(0).getBonusPeriod() : getCurrentBonusPeriod();
		for (final AboLosQualifications aboLosQualifications : qualificationsList)
		{
			if (StringUtils.equals(aboLosQualifications.getBonusPeriod(), currentBonusPeriod))
			{
				downlinesResponse.setSpMonth(true);
			}
		}
	}

	/**
	 * @param currentAward
	 * @param target
	 */
	private void populateCurrentAward(final AboLosAward currentAward, final LosAccountDetailResponseData target)
	{
		final CurrentAwardDetails awardDetails = new CurrentAwardDetails();
		awardDetails.setCode(currentAward.getCode());
		awardDetails.setName(currentAward.getName());
		awardDetails.setRank(currentAward.getRank());
		target.setCurrentAwardDetails(awardDetails);

	}

	private void populateHighestAward(final AboLosAward aboLosAward, final LosAccountDetailDownlinesResponse downlinesResponse)
	{
		downlinesResponse.setHighestPinName(aboLosAward.getName());
		try
		{
			if (StringUtils.isNotBlank(aboLosAward.getQualifcationPeriod()))
			{
				downlinesResponse.setHighestPinQualificationPeriod(
						new DateFormatManager("yyyyMM").getDateFormatInstance().parse(aboLosAward.getQualifcationPeriod()));
			}
		}
		catch (final ParseException e)
		{
			LOG.error("Exception occured during parsing", e);
		}
	}

	private void populateExtended(final AboLosExtended extended, final LosAccountDetailDownlinesResponse downlinesResponse)
	{
		if (extended.getName() != null)
		{
			for (final AboLosName aboLosName : extended.getName())
			{
				downlinesResponse.setName(aboLosName.getName());
			}
		}

		downlinesResponse.setEmail(extended.getPrimaryEmail());
		downlinesResponse.setPhone(extended.getPrimaryPhoneNo());

		try
		{
			if (StringUtils.isNotBlank(extended.getEntryDate()))
			{
				downlinesResponse.setBusinessEntryDate(
						new DateFormatManager("yyyy-MM-dd").getDateFormatInstance().parse(extended.getEntryDate()));
			}
			if (StringUtils.isNotBlank(extended.getRenewalDate()))
			{
				downlinesResponse
						.setRenewalDate(new DateFormatManager("yyyy-MM-dd").getDateFormatInstance().parse(extended.getRenewalDate()));
			}

		}
		catch (final ParseException e)
		{
			LOG.error("Exception occured during parsing", e);
		}

		if (CollectionUtils.isNotEmpty(extended.getAddress()))
		{
			final List<AddressData> addressDataList = new ArrayList<AddressData>();
			for (final AboLosAddress aboLosAddress : extended.getAddress())
			{
				final AddressData addressData = new AddressData();
				addressData.setTown(aboLosAddress.getCity());
				addressData.setPostalCode(aboLosAddress.getPostalCode());
				final List<String> addLines = aboLosAddress.getLine();
				addressData.setLine1(addLines.size() > 0 ? addLines.get(0) : StringUtils.EMPTY);
				addressData.setLine2(addLines.size() > 1 ? addLines.get(1) : StringUtils.EMPTY);
				final RegionData regionData = new RegionData();
				regionData.setIsocode(aboLosAddress.getState());

				addressData.setRegion(regionData);
				final CountryData countryData = new CountryData();
				countryData.setIsocode(aboLosAddress.getCountry());
				countryData.setName(aboLosAddress.getCountryName());

				addressData.setCountry(countryData);
				addressDataList.add(addressData);
			}
			downlinesResponse.setAddress(addressDataList);
		}
	}

	private void populateBonusVolume(final List<BonusVolume> bonusVolumes,
			final LosAccountDetailDownlinesResponse downlinesResponse, final String storeCurrency)
	{
		for (final BonusVolume bonusVolume : bonusVolumes)
		{
			if (storeCurrency.equals(bonusVolume.getCurrency()))
			{
				if (bonusVolume.getCode().equals(GROUP))
				{
					downlinesResponse.setGpv(bonusVolume.getPV());
					downlinesResponse.setGbv(bonusVolume.getBV());
				}
				if (bonusVolume.getCode().equals(PERSONAL))
				{
					downlinesResponse.setPpv(bonusVolume.getPV());
					downlinesResponse.setPbv(bonusVolume.getBV());
				}
				if (bonusVolume.getCode().equals(RUBY))
				{
					downlinesResponse.setRuby(bonusVolume.getPV());
				}
			}
		}
	}

	private void populateBonusVolume(final List<BonusVolume> bonusVolumes, final LosBonusPeriodData bonusPeriodData,
			final String storeCurrency)
	{
		for (final BonusVolume bonusVolume : bonusVolumes)
		{
			if (storeCurrency.equals(bonusVolume.getCurrency()))
			{
				if (bonusVolume.getCode().equals(GROUP))
				{
					bonusPeriodData.setGpv(bonusVolume.getPV());
				}
				if (bonusVolume.getCode().equals(PERSONAL))
				{
					bonusPeriodData.setPpv(bonusVolume.getPV());
				}
			}
		}
	}

	private void populateCountryOrderCount(final List<CountryOrderCount> countryOrderCounts,
			final LosAccountDetailDownlinesResponse downlinesResponse)
	{
		for (final CountryOrderCount countryOrderCount : countryOrderCounts)
		{
			if (countryOrderCount.getGroupOrderCount() != 0)
			{
				downlinesResponse.setGroupOrders(countryOrderCount.getGroupOrderCount());
			}
			if (countryOrderCount.getNewGroupABOCount() != 0)
			{
				downlinesResponse.setNewAbos(countryOrderCount.getNewGroupABOCount());
			}
			if (countryOrderCount.getPersonalOrderCount() != 0)
			{
				downlinesResponse.setPersonalOrders(countryOrderCount.getPersonalOrderCount());
			}
			if (countryOrderCount.getGroupABOCount() != 0)
			{
				downlinesResponse.setTotalAbos(countryOrderCount.getGroupABOCount());
			}
		}
	}

	private void populateBonusPeriodStats(final List<AboLosBonusPeriodStatistic> bonusPeriodStatistics,
			final LosAccountDetailDownlinesResponse downlinesResponse, final String storeCurrency)
	{
		if (CollectionUtils.isNotEmpty(bonusPeriodStatistics))
		{
			final List<LosBonusPeriodData> bonusPeriodDatas = new ArrayList<LosBonusPeriodData>();
			//this is if a certain bonus period is requested then it has to allow that bonus period pv to be populated
			final String currentBonusPeriod =
					bonusPeriodStatistics.size() == 1 ? bonusPeriodStatistics.get(0).getBonusPeriod() : getCurrentBonusPeriod();
			for (final AboLosBonusPeriodStatistic bonusPeriodStatistic : bonusPeriodStatistics)
			{
				if (bonusPeriodStatistic.getBonusPeriod().equals(currentBonusPeriod))
				{
					if (CollectionUtils.isNotEmpty(bonusPeriodStatistic.getVolumes()))
					{
						populateBonusVolume(bonusPeriodStatistic.getVolumes(), downlinesResponse, storeCurrency);
					}
					downlinesResponse.setNextPerformance(bonusPeriodStatistic.getNextGroupPercentage().getBonusPercent());
					final String businessNatureCd = bonusPeriodStatistic.getBusinessNatureCode();
					String businessNature = StringUtils.EMPTY;
					if ("1".equals(businessNatureCd))
					{
						businessNature = "AMWAY BUSINESS OWNER";
					}
					else if ("4".equals(businessNatureCd))
					{
						businessNature = "CLIENT";
					}
					else if ("3".equals(businessNatureCd))
					{
						businessNature = "EMPLOYEE";
					}

					final AmwayBusinessNature businessNatureName = getEnumerationService()
							.getEnumerationValue(AmwayBusinessNature.class, "AmwayBusinessNature_" + businessNatureCd);
					//		downlinesResponse.setBusinessNatureName(businessNatureName != null ? getEnumerationService().getEnumerationName(
					//				businessNatureName) : businessNature);
					//		downlinesResponse.setBusinessNature(businessNature);
					downlinesResponse.setQualifiedLegs(
							bonusPeriodStatistic.getFosterQualifiedLegs() + bonusPeriodStatistic.getInMarketQualifiedLegs());
					downlinesResponse.setBonus(bonusPeriodStatistic.getCurrentPersonalPercentage().getBonusPercent());
					downlinesResponse.setSponsor(bonusPeriodStatistic.getSponsor().getABONo());
					downlinesResponse.setPlatinumSponsor(bonusPeriodStatistic.getPlatinumSponsor().getABONo());
					downlinesResponse.setLeaderShipPer(bonusPeriodStatistic.getCurrentLeadershipPercentage().getBonusPercent());
					downlinesResponse.setPersonalPer(bonusPeriodStatistic.getCurrentPersonalPercentage().getBonusPercent());
					if (bonusPeriodStatistic.getInternationalSponsorABO() != null)
					{
						downlinesResponse.setIntlSponserAbo(bonusPeriodStatistic.getInternationalSponsorABO().getABONo());
						downlinesResponse.setIntlSponserAff(bonusPeriodStatistic.getInternationalSponsorABO().getAff());
					}

					for (final BonusVolume bonusVolume : bonusPeriodStatistic.getVolumes())
					{
						if (bonusVolume.getCode() != null && bonusVolume.getCode().equals(GROUP) && storeCurrency
								.equals(bonusVolume.getCurrency()))
						{
							final Double volume = Double.valueOf(bonusPeriodStatistic.getNextGroupPercentage().getVolume());
							final Double pv = bonusVolume.getPV();
							double difference = volume.doubleValue() - pv.doubleValue();
							difference = Math.round(difference * 100);
							difference = difference / 100;
							if (difference > 0)
							{
								downlinesResponse.setNextPerformancePoints(Double.valueOf(difference));
							}
							else
							{
								downlinesResponse.setNextPerformancePoints(Double.valueOf(0));
							}
						}
					}
				}
				if (bonusPeriodStatistics.size() > 1)
				{
					bonusPeriodDatas.add(populateLosBonusPeriodData(bonusPeriodStatistic, storeCurrency));
				}
			}
			downlinesResponse.setBonusPeriodList(bonusPeriodDatas);
		}
	}

	private void populateSponsorStats(final List<AboLosBonusPeriodSponsoringStatistic> sponsoringStatistics,
			final LosAccountDetailDownlinesResponse downlinesResponse)
	{
		if (CollectionUtils.isNotEmpty(sponsoringStatistics))
		{
			//this is if a certain bonus period is requested then it has to allow that bonus period pv to be populated
			final String currentBonusPeriod =
					sponsoringStatistics.size() == 1 ? sponsoringStatistics.get(0).getBonusPeriod() : getCurrentBonusPeriod();
			for (final AboLosBonusPeriodSponsoringStatistic sponsoringStatistic : sponsoringStatistics)
			{
				if (sponsoringStatistic.getBonusPeriod().equals(currentBonusPeriod))
				{
					populateCountryAward(sponsoringStatistic.getAward(), downlinesResponse);
				}
			}
		}
	}

	private void populateCountryAward(final List<CountryAward> countryAwards,
			final LosAccountDetailDownlinesResponse downlinesResponse)
	{
		if (CollectionUtils.isNotEmpty(countryAwards))
		{
			for (final CountryAward countryAward : countryAwards)
			{
				downlinesResponse.setCurrent(countryAward.getBonusAwardCode());
				if (CollectionUtils.isNotEmpty(countryAward.getCountry()))
				{
					populateCountryOrderCount(countryAward.getCountry(), downlinesResponse);
				}
			}
		}
	}

	private String getCurrentBonusPeriod()
	{
		return AmwayPeriodHelper.getCurrentActiveBonusPeriod();
	}

	private LosBonusPeriodData populateLosBonusPeriodData(final AboLosBonusPeriodStatistic bonusPeriodStatistic,
			final String storeCurrency)
	{
		final LosBonusPeriodData losBonusPeriodData = new LosBonusPeriodData();
		populateBonusVolume(bonusPeriodStatistic.getVolumes(), losBonusPeriodData, storeCurrency);
		losBonusPeriodData.setBonusPeriod(getFormattedDate(bonusPeriodStatistic));
		losBonusPeriodData.setBonusPercentage(bonusPeriodStatistic.getCurrentPersonalPercentage().getBonusPercent());
		losBonusPeriodData
				.setQualifiedLegs(bonusPeriodStatistic.getFosterQualifiedLegs() + bonusPeriodStatistic.getInMarketQualifiedLegs());

		return losBonusPeriodData;
	}

	private String getFormattedDate(final AboLosBonusPeriodStatistic bonusPeriodStatistic)
	{
		final String year = bonusPeriodStatistic.getBonusPeriod().substring(0, 4);
		final String month = bonusPeriodStatistic.getBonusPeriod().substring(4, 6);
		final String monthName = DateTime.now().withMonthOfYear(Integer.valueOf(month).intValue()).toString("MMM");
		return monthName.toUpperCase() + " " + year;
	}

	/**
	 * @return the enumerationService
	 */
	public EnumerationService getEnumerationService()
	{
		return enumerationService;
	}

	/**
	 * @param enumerationService the enumerationService to set
	 */
	public void setEnumerationService(final EnumerationService enumerationService)
	{
		this.enumerationService = enumerationService;
	}
}
