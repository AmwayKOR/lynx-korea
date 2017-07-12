/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;

import org.apache.commons.lang.StringUtils;

import com.amway.core.dms.data.DmsSubscriptionData;
import com.amway.core.dms.data.DmsSubscriptionResultData;
import com.amway.core.dms.data.SubscriptStatus;
import com.amway.core.dms.data.SubscriptionOption;
import com.amway.integration.dms.services.ContactPointTypeData;
import com.amway.integration.dms.services.SubscriptionResponse;
import com.amway.integration.dms.services.SubsrciptionSvcData;
import com.hybris.commons.conversion.ConversionException;


/**
 * Populator implementation for {@link SubscriptionResponse} as source and {@link DmsSubscriptionResultData} as target
 * type
 */
public class DmsSubscriptionOutputReversePopulator implements Populator<SubscriptionResponse, DmsSubscriptionResultData>
{

	private static final String SUBSCRIBED = "Subscribed";
	private static final String PAUSE = "Pause";
	private static final String CANCEL = "Cancel";

	@Override
	public void populate(final SubscriptionResponse response, final DmsSubscriptionResultData resultData)
			throws ConversionException, IllegalArgumentException
	{
		final Map<String, Boolean> subscriptionStatus = new LinkedHashMap<>();
		final List<DmsSubscriptionData> responseDatalist = new ArrayList<>();
		final List<SubsrciptionSvcData> subscriptionLists = response.getSubscriptionList();
		for (final SubsrciptionSvcData svcData : subscriptionLists)
		{
			final List<SubscriptStatus> subscriptionStatusList = new ArrayList<>();
			final List<SubscriptionOption> subscriptionOptionList = new ArrayList<>();
			final DmsSubscriptionData subscriptionResponseData = new DmsSubscriptionData();
			boolean statusFlg = false;

			if (("Y").equalsIgnoreCase(svcData.getIsPublicationCd()))
			{
				statusFlg = true;
			}
			if (("Y").equalsIgnoreCase(svcData.getCancelCd()))
			{
				subscriptionStatus.put(CANCEL, Boolean.TRUE);
				subscriptionStatus.put(PAUSE, Boolean.FALSE);
				subscriptionStatus.put(SUBSCRIBED, Boolean.FALSE);
			}
			else if (("Y").equalsIgnoreCase(svcData.getPauseCd()))
			{
				subscriptionStatus.put(PAUSE, Boolean.TRUE);
				subscriptionStatus.put(CANCEL, Boolean.FALSE);
				subscriptionStatus.put(SUBSCRIBED, Boolean.FALSE);

			}
			else
			{
				subscriptionStatus.put(SUBSCRIBED, Boolean.TRUE);
				subscriptionStatus.put(CANCEL, Boolean.FALSE);
				subscriptionStatus.put(PAUSE, Boolean.FALSE);
			}
			for (final Map.Entry<String, Boolean> status : subscriptionStatus.entrySet())
			{
				final SubscriptStatus subscriptStatus = new SubscriptStatus();
				subscriptStatus.setName(status.getKey());
				subscriptStatus.setValue(status.getValue().booleanValue());
				subscriptionStatusList.add(subscriptStatus);

			}
			subscriptionResponseData.setSubscriptionStatus(subscriptionStatusList);
			subscriptionResponseData.setOptionValue(statusFlg);
			subscriptionResponseData.setOption(svcData.getSubscriptionName());
			subscriptionResponseData.setOptionId(svcData.getSubscriptionId());

			for (final ContactPointTypeData pointTypeData : svcData.getDeliveryTypeData())
			{
				if (pointTypeData.getContactPointTypeCd() != null || pointTypeData.getDeliveryChoiceCd() != null)
				{
					boolean choiseFlg = false;
					if (("Y").equalsIgnoreCase(pointTypeData.getDeliveryChoiceCd()))
					{
						choiseFlg = true;
					}

					final String value = pointTypeData.getContactPointTypeCd();
					String subscriptionOptionName = StringUtils.EMPTY;
					switch (value)
					{
						case "EM":
							subscriptionOptionName = "Email";
							break;
						case "SM":
							subscriptionOptionName = "SMS";
							break;
						case "SO":
							subscriptionOptionName = "Social Media";
							break;
						case "AD":
							subscriptionOptionName = "Address";
							break;
						default:
							subscriptionOptionName = StringUtils.EMPTY;
							break;
					}

					final SubscriptionOption option = new SubscriptionOption();
					option.setName(subscriptionOptionName);
					option.setValue(choiseFlg);
					subscriptionOptionList.add(option);
				}
			}

			subscriptionResponseData.setSubscriptionOptionList(subscriptionOptionList);
			responseDatalist.add(subscriptionResponseData);
		}

		resultData.setSubscriptionResultData(responseDatalist);
		resultData.setDecision(response.getReturnMessage());
		resultData.setReturnCd(response.getReturnCd());
	}
}
