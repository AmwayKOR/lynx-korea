package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.store.services.BaseStoreService;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.amway.core.data.SubscriptionOptions;
import com.amway.core.dms.data.SubscriptionData;
import com.amway.core.dms.data.SubscriptionOption;
import com.amway.core.dms.data.SubscriptionOptionRequestData;
import com.amway.core.service.AmwayAccountCommerceService;
import com.amway.integration.dms.services.ContactPointTypeData;
import com.amway.integration.dms.services.Subsrciption;
import com.amway.integration.dms.services.UpdateSubscriptionRequest;



/**
 * Populator implementation for {@link SubscriptionOptionRequestData} as source and {@link UpdateSubscriptionRequest} as
 * target type
 */
public class DmsSubscriptionUpdatePopulator implements Populator<SubscriptionOptionRequestData, UpdateSubscriptionRequest>
{
	private AmwayAccountCommerceService amwayAccountCommerceService;

	private BaseStoreService baseStoreService;

	@Override
	public void populate(final SubscriptionOptionRequestData source, final UpdateSubscriptionRequest target)
			throws ConversionException
	{
		target.setAboNum(getAmwayAccountCommerceService().getCurrentAccountNumber());
		target.setSalesPlanAff(getBaseStoreService().getCurrentBaseStore().getAffiliateNumber());
		Subsrciption subsrciptionLast = new Subsrciption();
		for (final SubscriptionData data : source.getSubscriptionDataList())
		{
			final Subsrciption subsrciption = new Subsrciption();
			subsrciption.setSubscriptionName(data.getOption());
			subsrciption.setSubscriptionId(data.getOptionId());
			if (data.getSubscriptionStatus() != null)
			{
				if (data.getSubscriptionStatus().equalsIgnoreCase(SubscriptionOptions.PAUSE.name()))
				{
					subsrciption.setPauseCd("Y");
					subsrciption.setRestoreCd("N");
					subsrciption.setCancelCd("N");
				}
				else if (data.getSubscriptionStatus().equalsIgnoreCase(SubscriptionOptions.SUBSCRIBED.name()))
				{
					subsrciption.setSubscribeFlag("Y");
					subsrciption.setPauseCd("N");
					subsrciption.setCancelCd("N");
				}
				else if (data.getSubscriptionStatus().equalsIgnoreCase(SubscriptionOptions.CANCEL.name()))
				{
					subsrciption.setCancelCd("Y");
					subsrciption.setSubscribeFlag("N");
					subsrciption.setPauseCd("N");
				}
			}
			boolean isSubscribed = false;
			for (final SubscriptionOption option : data.getSubscriptionOptionList())
			{
				final ContactPointTypeData pointTypeData = new ContactPointTypeData();
				String contactPointTypeCdvalue = StringUtils.EMPTY;

				switch (option.getName())
				{
					case "Email":
						contactPointTypeCdvalue = "EM";
						break;
					case "SMS":
						contactPointTypeCdvalue = "SM";
						break;
					case "Social Media":
						contactPointTypeCdvalue = "SO";
						break;
					case "Address":
						contactPointTypeCdvalue = "AD";
						break;
					default:
						contactPointTypeCdvalue = StringUtils.EMPTY;
						break;
				}
				pointTypeData.setContactPointTypeCd(contactPointTypeCdvalue);
				if (option.isValue())
				{
					isSubscribed = isSubscribed ? isSubscribed : true;
					pointTypeData.setDeliveryChoiceCd("Y");
				}
				else
				{
					pointTypeData.setDeliveryChoiceCd("N");
				}
				subsrciption.getDeliveryTypeDataList().add(pointTypeData);
			}
			if (isSubscribed)
			{
				target.getSubscriptionList().add(subsrciption);
			}
			subsrciptionLast = subsrciption;
		}
		if (CollectionUtils.isEmpty(target.getSubscriptionList()))
		{
			target.getSubscriptionList().add(subsrciptionLast);
		}

	}

	public AmwayAccountCommerceService getAmwayAccountCommerceService()
	{
		return amwayAccountCommerceService;
	}

	public void setAmwayAccountCommerceService(final AmwayAccountCommerceService amwayAccountCommerceService)
	{
		this.amwayAccountCommerceService = amwayAccountCommerceService;
	}

	public BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

}
