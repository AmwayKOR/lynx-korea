/**
 *
 */
package com.amway.integration.cis.los.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.util.Config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.amway.core.los.data.LosAccountDownlinesResponse;
import com.amway.core.los.data.LosAccountResponseData;
import com.amway.integration.cis.los.pojo.LineOfSponsorship;
import com.amway.integration.cis.los.pojo.LosResponse;


/**
 * populator to populate LosAccountResponse to LosAccountResponseData
 */
public class LosAccountOutputPopulator implements Populator<LosResponse, LosAccountResponseData>
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final LosResponse source, final LosAccountResponseData target) throws ConversionException
	{
		final List<LosAccountDownlinesResponse> losAccountDownlinesResponseList = new ArrayList<LosAccountDownlinesResponse>();

		if (source.getLOS() != null && CollectionUtils.isNotEmpty(source.getLOS().getDownlines()))
		{
			final List<String> abos = new ArrayList<String>();
			for (final LineOfSponsorship lineOfSponsorship : source.getLOS().getDownlines())
			{
				final LosAccountDownlinesResponse losAccountDownlinesResponse = new LosAccountDownlinesResponse();
				populateAboAndAff(lineOfSponsorship, abos);
				populateLineOfSponsorship(lineOfSponsorship, losAccountDownlinesResponseList, losAccountDownlinesResponse);

			}
			target.setAbo(retrieveAboAndAff(abos));
			target.setListOfAboAndAff(retriveListOfABOAndAffNumber(abos));
		}
		else
		{
			if (source.getLOS() != null)
			{
				final LosAccountDownlinesResponse losAccountDownlinesResponse = new LosAccountDownlinesResponse();
				final LineOfSponsorship lineOfSponsorship = source.getLOS();
				populateLineOfSponsorship(lineOfSponsorship, losAccountDownlinesResponseList, losAccountDownlinesResponse);
				target.setAbo(lineOfSponsorship.getAff() + "-" + lineOfSponsorship.getABONo());
				target.setListOfAboAndAff(Collections.singletonList(lineOfSponsorship.getAff() + "-" + lineOfSponsorship.getABONo()));
			}
		}

		target.setLosAccountResponseListData(losAccountDownlinesResponseList);
		target.setReturnCode(Integer.parseInt(source.getReturnCode()));
		target.setReturnMessage(source.getReturnMessage());
	}

	private void populateLineOfSponsorship(final LineOfSponsorship lineOfSponsorship,
			final List<LosAccountDownlinesResponse> losAccountDownlinesResponseList,
			final LosAccountDownlinesResponse losAccountDownlinesResponse)
	{
		losAccountDownlinesResponse.setAboNo(lineOfSponsorship.getABONo().toString());
		losAccountDownlinesResponse.setAff(lineOfSponsorship.getAff());
		losAccountDownlinesResponse.setCountry(lineOfSponsorship.getCountry());
		losAccountDownlinesResponse.setHasDownlines(lineOfSponsorship.isHasDownlines().booleanValue());

		if (lineOfSponsorship.isHasDownlines().booleanValue())
		{
			losAccountDownlinesResponse.setDownlines(extractDownlines(lineOfSponsorship.getDownlines()));
		}
		if (lineOfSponsorship.getPrivacyFlag() != null)
		{
			losAccountDownlinesResponse.setPrivacyFlag(lineOfSponsorship.getPrivacyFlag().booleanValue());
		}

		if (lineOfSponsorship.getPrivacyFlag() != null && lineOfSponsorship.getPrivacyFlag().booleanValue())
		{
			losAccountDownlinesResponse.setName(Config.getString("storefront.los.privacy.name", "CONFIDENTIAL"));
		}
		else
		{
			losAccountDownlinesResponse.setName(lineOfSponsorship.getName());

		}

		losAccountDownlinesResponse.setCurrentAwardRank(lineOfSponsorship.getCurrentAwardRank());
		losAccountDownlinesResponse.setHighestAwardRank(lineOfSponsorship.getHighestAwardRank());
		losAccountDownlinesResponseList.add(losAccountDownlinesResponse);
	}

	/**
	 * @param downlines
	 * @return
	 */
	private List<LosAccountDownlinesResponse> extractDownlines(final List<LineOfSponsorship> downlines)
	{
		final List<LosAccountDownlinesResponse> downlinesList = new ArrayList<LosAccountDownlinesResponse>();
		for (final LineOfSponsorship downline : downlines)
		{
			final LosAccountDownlinesResponse losAccountDownlinesResponse = new LosAccountDownlinesResponse();

			losAccountDownlinesResponse.setAboNo(downline.getABONo().toString());
			losAccountDownlinesResponse.setAff(downline.getAff());
			losAccountDownlinesResponse.setCountry(downline.getCountry());
			losAccountDownlinesResponse.setHasDownlines(downline.isHasDownlines().booleanValue());

			if (downline.isHasDownlines().booleanValue())
			{
				losAccountDownlinesResponse.setDownlines(extractDownlines(downline.getDownlines()));
			}
			if (downline.getPrivacyFlag() != null)
			{
				losAccountDownlinesResponse.setPrivacyFlag(downline.getPrivacyFlag().booleanValue());
			}

			if (downline.getPrivacyFlag() != null && downline.getPrivacyFlag().booleanValue())
			{
				losAccountDownlinesResponse.setName(Config.getString("storefront.los.privacy.name", "CONFIDENTIAL"));
			}
			else
			{
				losAccountDownlinesResponse.setName(downline.getName());

			}

			losAccountDownlinesResponse.setCurrentAwardRank(downline.getCurrentAwardRank());
			losAccountDownlinesResponse.setHighestAwardRank(downline.getHighestAwardRank());
			downlinesList.add(losAccountDownlinesResponse);
		}
		return downlinesList;
	}

	private void populateAboAndAff(final LineOfSponsorship lineOfSponsorship, final List<String> abos)
	{
		abos.add(lineOfSponsorship.getAff() + "-" + lineOfSponsorship.getABONo());
	}

	private String retrieveAboAndAff(final List<String> aboAndAffList)
	{
		String aboNo = null;
		for (final String aboAndAff : aboAndAffList)
		{
			if (aboNo == null)
			{
				aboNo = aboAndAff;
			}
			else
			{
				aboNo = aboNo + "|";
				aboNo = aboNo + aboAndAff;
			}
		}
		return aboNo;
	}

	private List<String> retriveListOfABOAndAffNumber(final List<String> aboAndAffList)
	{
		final List<String> aboAndAffNumberList = new ArrayList<String>();

		for (final String aboAndAff : aboAndAffList)
		{
			aboAndAffNumberList.add(aboAndAff);
		}
		return aboAndAffNumberList;
	}
}
