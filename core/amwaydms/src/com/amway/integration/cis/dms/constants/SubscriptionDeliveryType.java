package com.amway.integration.cis.dms.constants;

public enum SubscriptionDeliveryType
{
    ADDRESS("AD", "Address"),
    EMAIL("EM","Email"),
    PHONE("PH", "Phone"),
    SMS("SM","SMS"),
    SOCIAL_MEDIA("SO","Social Media")
    ;

    public final String code, label;

    SubscriptionDeliveryType(String code, String label)
    {
        this.code = code;
        this.label = label;
    }
}
