package com.amway.amwaydms.populators;

import com.amway.core.annotations.AmwayBean;
import com.amway.core.data.SubscriptionOptions;
import com.amway.core.dms.data.SubscriptionData;
import com.amway.core.dms.data.SubscriptionInputData;
import com.amway.core.dms.data.SubscriptionOption;
import com.amway.core.dms.data.SubscriptionOptionRequestData;
import com.amway.core.service.AmwayAccountCommerceService;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.store.services.BaseStoreService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.amway.amwaydms.model.SubscriptionRequest;
import com.amway.amwaydms.model.Subscription;
import com.amway.amwaydms.model.SubscriptionDelivery;
import com.amway.amwaydms.model.SubscriptionDelivery.DeliveryChoiceCdEnum;

/**
 * Created by aiueq92 on 10/10/17.
 */
@AmwayBean(ext="amwaydms2",docs="https://jira.amway.com:8444/display/HC/amwaydms2," +
        "https://jira.amway.com:8444/display/HC/DMS+Populators+and+Converters")
public class SubscriptionUpdateInputPopulator implements Populator<SubscriptionOptionRequestData, SubscriptionRequest> {
    private AmwayAccountCommerceService amwayAccountCommerceService;

    private BaseStoreService baseStoreService;

    @Override
    public void populate(final SubscriptionOptionRequestData source, final SubscriptionRequest target)
            throws ConversionException {
        Subscription subsrciptionLast = new Subscription();
        for (final SubscriptionData data : source.getSubscriptionDataList()) {
            final Subscription subsrciption = new Subscription();
            subsrciption.setSubscriptionName(data.getOption());
            subsrciption.setSubscriptionId(data.getOptionId());
            if (data.getSubscriptionStatus() != null) {
                if (data.getSubscriptionStatus().equalsIgnoreCase(SubscriptionOptions.PAUSE.name())) {
                    subsrciption.setPauseCd("Y");
                    subsrciption.setRestoreCd("N");
                    subsrciption.setCancelCd("N");
                } else if (data.getSubscriptionStatus().equalsIgnoreCase(SubscriptionOptions.SUBSCRIBED.name())) {
                    subsrciption.setSubscribeFlag("Y");
                    subsrciption.setPauseCd("N");
                    subsrciption.setCancelCd("N");
                } else if (data.getSubscriptionStatus().equalsIgnoreCase(SubscriptionOptions.CANCEL.name())) {
                    subsrciption.setCancelCd("Y");
                    subsrciption.setSubscribeFlag("N");
                    subsrciption.setPauseCd("N");
                }
            }
            boolean isSubscribed = false;
            for (final SubscriptionOption option : data.getSubscriptionOptionList()) {
                final SubscriptionDelivery pointTypeData = new SubscriptionDelivery();
                String contactPointTypeCdvalue = StringUtils.EMPTY;

                switch (option.getName()) {
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
                pointTypeData.setDeliveryTypeCd(contactPointTypeCdvalue);
                if (option.isValue()) {
                    isSubscribed = isSubscribed ? isSubscribed : true;
                    pointTypeData.setDeliveryChoiceCd(DeliveryChoiceCdEnum.Y);
                } else {
                    pointTypeData.setDeliveryChoiceCd(DeliveryChoiceCdEnum.N);
                }
                subsrciption.getSubscriptionDeliveryList().add(pointTypeData);
            }
            if (isSubscribed) {
                target.getSubscriptionList().add(subsrciption);
            }
            subsrciptionLast = subsrciption;
        }
        if (CollectionUtils.isEmpty(target.getSubscriptionList())) {
            target.getSubscriptionList().add(subsrciptionLast);
        }

    }

    public AmwayAccountCommerceService getAmwayAccountCommerceService() {
        return amwayAccountCommerceService;
    }

    public void setAmwayAccountCommerceService(final AmwayAccountCommerceService amwayAccountCommerceService) {
        this.amwayAccountCommerceService = amwayAccountCommerceService;
    }

    public BaseStoreService getBaseStoreService() {
        return baseStoreService;
    }

    public void setBaseStoreService(final BaseStoreService baseStoreService) {
        this.baseStoreService = baseStoreService;
    }
}
