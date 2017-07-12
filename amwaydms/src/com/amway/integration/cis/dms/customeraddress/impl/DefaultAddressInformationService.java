package com.amway.integration.cis.dms.customeraddress.impl;

import de.hybris.platform.commercefacades.user.data.AddressData;

import java.util.ArrayList;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.springframework.util.Assert;

import com.amway.core.b2b.services.AddressProcessDecision;
import com.amway.core.dms.data.AddressResultData;
import com.amway.core.dms.service.DmsService;
import com.amway.dms.data.AddressInformationRequestData;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.AddressInformationResponse;
import com.amway.integration.dms.services.GetPartyAddressInput;
import com.hybris.commons.client.RestResponse;


/**
 * Service for to get address information from MAGIC.
 */
public class DefaultAddressInformationService extends
		AbstractDmsService<AddressResultData<AddressProcessDecision>, AddressInformationRequestData, AddressInformationResponse>
		implements DmsService<AddressInformationRequestData, AddressResultData<AddressProcessDecision>>
{

	@Override
	protected AddressInformationResponse executeEvent(final Object input)
	{
		final RestResponse<AddressInformationResponse> dmsResultRestResponse = getDmsClient()
				.executeDmsRequest(getXclientRefId(), getUrlPath(),
						 input,
						AddressInformationResponse.class);
		Assert.notNull(dmsResultRestResponse, "Failed to get addresses from Magic");
		return dmsResultRestResponse.getResult();
	}

	@Override
	protected AddressResultData<AddressProcessDecision> createDefaultResult()
	{
		final AddressResultData<AddressProcessDecision> addressInformationResult = createResultObject();
		addressInformationResult.setDecision(AddressProcessDecision.UNKNOWN);
		addressInformationResult.setAddressList(new ArrayList<AddressData>());

		return addressInformationResult;
	}

	@Override
	protected AddressResultData<AddressProcessDecision> createResultObject()
	{
		return new AddressResultData<AddressProcessDecision>();
	}

}
