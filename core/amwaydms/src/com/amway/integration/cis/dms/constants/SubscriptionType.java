package com.amway.integration.cis.dms.constants;

/**
 *  http://dms-service-dev:8080/DMS_Service_Web/DomainCodeLookUp.jsp?code=SUBCD
 */
public enum SubscriptionType
{
    // @formatter:off
    All_100	("All",	"100",	"USA Today"),
    AR_1	("AR",	"1",	    "Amway Magazine"),
    AU_1	("AU",	"1",	    "Amway Magazine"),
    BR_300	("BR",	"300",	"Market Updates"),
    CA_1	("CA",	"1",	    "Amway Magazine"),
    CA_2	("CA",	"2",	    "Artistry"),
    CA_50	("CA",	"50",	    "Auto-Renewal"),
    CA_3	("CA",	"3",	    "Nutrilite"),
    CA_100	("CA",	"100",	"USA Today"),
    CN_59	("CN",	"59",	    "Renewal Processed, Consent Reqd"),
    DE_1	("DE",	"1",	    "Amway Magazine"),
    DE_2	("DE",	"2",	    "Artistry"),
    DE_3	("DE",	"3",	    "Nutrilite"),
    DE_5	("DE",	"5",	    "Platinum News"),
    DE_6	("DE",	"6",	    "Registration Alert"),
    DK_1	("DK",	"1",	    "Amway Magazine"),
    DK_50	("DK",	"50",	    "Auto-Renewal"),
    DK_400	("DK",	"400",	"Leads ABO"),
    DK_500	("DK",	"500",	"Leads Customer"),
    DK_600	("DK",	"600",	"Leads Member"),
    DK_250	("DK",	"250",	"Product Catalog & Price List"),
    DO_1	("DO",	"1",	    "Amway Magazine"),
    DO_2	("DO",	"2",	    "Artistry"),
    DO_50	("DO",	"50",	    "Auto-Renewal"),
    DO_3	("DO",	"3",	    "Nutrilite"),
    FI_1	("FI",	"1",	    "Amway Magazine"),
    FI_50	("FI",	"50",	    "Auto-Renewal"),
    FI_400	("FI",	"400",	"Leads ABO"),
    FI_500	("FI",	"500",	"Leads Customer"),
    FI_600	("FI",	"600",	"Leads Member"),
    FI_250	("FI",	"250",	"Product Catalog & Price List"),
    HT_2	("HT",	"2",	    "Artistry"),
    NO_1	("NO",	"1",	    "Amway Magazine"),
    NO_50	("NO",	"50",	    "Auto-Renewal"),
    NO_400	("NO",	"400",	"Leads ABO"),
    NO_500	("NO",	"500",	"Leads Customer"),
    NO_600	("NO",	"600",	"Leads Member"),
    NO_250	("NO",	"250",	"Product Catalog & Price List"),
    SE_1	("SE",	"1",	    "Amway Magazine"),
    SE_50	("SE",	"50",	    "Auto-Renewal"),
    SE_400	("SE",	"400",	"Leads ABO"),
    SE_500	("SE",	"500",	"Leads Customer"),
    SE_600	("SE",	"600",	"Leads Member"),
    SE_250	("SE",	"250",	"Product Catalog & Price List"),
    US_1	("US",	"1",	    "Amway Magazine"),
    US_2	("US",	"2",	    "Artistry"),
    US_50	("US",	"50",	    "Auto-Renewal"),
    US_400	("US",	"400",	"Leads ABO"),
    US_500	("US",	"500",	"Leads Customer"),
    US_600	("US",	"600",	"Leads Member"),
    US_3	("US",	"3",	    "Nutrilite"),
    US_5	("US",	"5",	    "Platinum News"),
    US_6	("US",	"6",	    "Registration Alert"),
    US_55	("US",	"55",	    "Renewal Reminder");
    // @formatter:on

    public final String countryIso;
    public final String label;
    public final String code;

    SubscriptionType(final String countryIso, final String code, final String label)
    {
        this.countryIso = countryIso;
        this.code = code;
        this.label = label;
    }
}
