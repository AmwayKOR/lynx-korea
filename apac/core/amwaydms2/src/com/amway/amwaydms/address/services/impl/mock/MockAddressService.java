package com.amway.amwaydms.address.services.impl.mock;

import com.amway.core.annotations.AmwayBean;
import com.amway.core.b2b.services.AddressProcessDecision;
import com.amway.core.dms.data.AddressResultData;
import com.amway.core.dms.service.DmsService;
import com.amway.dms.data.AddressInformationRequestData;
import org.apache.commons.lang.StringUtils;
import com.amway.amwaydms.model.AddressResponse;
import com.amway.amwaydms.model.Address;
import com.amway.amwaydms.model.AddressContactUsage;

import com.amway.amwaydms.services.impl.AbstractDmsService;



/**
 * Created by aiueq92 on 9/12/17.
 */
@AmwayBean(ext="amwaydms2",docs="https://jira.amway.com:8444/display/HC/amwaydms2," +
        "https://jira.amway.com:8444/display/HC/Mocks")
public class MockAddressService extends
        AbstractDmsService<AddressResultData<AddressProcessDecision>, AddressInformationRequestData, AddressResponse>
        implements DmsService<AddressInformationRequestData, AddressResultData<AddressProcessDecision>>
{

    @Override
    public AddressResultData<AddressProcessDecision> process(final AddressInformationRequestData requestData)
    {
        return extractOutput(executeEvent(requestData));
    }

    /*
     * (non-Javadoc)
     *
     * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#createResultObject()
     */
    @Override
    protected AddressResultData<AddressProcessDecision> createResultObject()
    {
        return new AddressResultData<AddressProcessDecision>();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#createDefaultResult()
     */
    @Override
    protected AddressResultData<AddressProcessDecision> createDefaultResult()
    {
        final AddressResultData<AddressProcessDecision> result = createResultObject();
        result.setDecision(AddressProcessDecision.REJECT);
        result.setResponseMessage("Fail to get Assress information");
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#executeEvent(java.lang.Object)
     */
    @Override
    protected AddressResponse executeEvent(final Object input)
    {
        final AddressResponse response = new AddressResponse();
        final Address address1 = new Address();

        address1.setAddrStreet(convertToJAXBString("addrStreet", "AV MARIANA UBALDINA DO ESPIRITO SANTO, 249"));
        address1.setAddrLineTwo(convertToJAXBString("addrLineTwo", "APTO 102A "));
        address1.setAddrLineThree(convertToJAXBString("addrLineThree", "MACEDO "));
        address1.setCityName(convertToJAXBString("addrLineThree", ""));
        address1.setPostalCd(convertToJAXBString("postalCd", "07197000"));
        address1.setContactPointName(convertToJAXBString("contactPointName", "omeAddress"));
        address1.setAddrLineFour(convertToJAXBString("addrLineFour", ""));
        address1.setPartyId(convertToJAXBString("partyId", "171956"));
        address1.setContactPointTypeCd(convertToJAXBString("contactPointTypeCd", "HomeAddress"));
        address1.setCntryCd(convertToJAXBString("cntryCd", "BR"));
        address1.setStateCd(convertToJAXBString("stateCd", "SP"));

        final AddressContactUsage usageData1 = new AddressContactUsage();
        usageData1.setCareOf("BATISTA FERNANDA");
        usageData1.setContactPointPurposeCd("Billing");
        usageData1.setPrimaryFlag(true);

        final AddressContactUsage usageData2 = new AddressContactUsage();
        usageData2.setCareOf("BATISTA FERNANDA");
        usageData2.setContactPointPurposeCd("Mailing");
        usageData2.setPrimaryFlag(true);

        final AddressContactUsage usageData3 = new AddressContactUsage();
        usageData3.setCareOf("BATISTA FERNANDA");
        usageData3.setContactPointPurposeCd("Shipping");
        usageData3.setPrimaryFlag(true);
        address1.getUsageList().add(usageData1);
        address1.getUsageList().add(usageData2);
        address1.getUsageList().add(usageData3);

        response.getAddressList().add(address1);

        return response;
    }

    protected String convertToJAXBString(final String elementName, final String elementValue)
    {
        return StringUtils.isNotEmpty(elementValue) ? elementValue : StringUtils.EMPTY;
    }

}

