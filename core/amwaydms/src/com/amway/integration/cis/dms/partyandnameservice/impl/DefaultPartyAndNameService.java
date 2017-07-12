package com.amway.integration.cis.dms.partyandnameservice.impl;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;

import com.amway.core.dms.party.data.AccountInfoData;
import com.amway.core.dms.party.data.PartyData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.customerregistration.impl.DefaultProcessCustomerRegistrationService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.PartyNameDetailResponse;
import com.amway.integration.dms.services.PartyNameDetailsInput;
import com.hybris.commons.client.RestResponse;


/**
 * 
 * Service for to get the party details.
 *
 */
public class DefaultPartyAndNameService extends AbstractDmsService<PartyData, AccountInfoData, PartyNameDetailResponse>
		implements DmsService<AccountInfoData, PartyData>
{
	private static final Logger LOG = Logger.getLogger(DefaultProcessCustomerRegistrationService.class);

	@Override
	protected PartyNameDetailResponse executeEvent(final Object input)
	{
		LOG.debug("Executing request");
		final RestResponse<PartyNameDetailResponse> dmsResultRestResponse = getDmsClient().executeDmsRequest("x-ref",
				"/PartyNameService/getPartyAndNameDetails",
				new JAXBElement(new QName("", "PartyAndNameSearchInputRequest"), PartyNameDetailsInput.class, input),
				PartyNameDetailResponse.class);
		return dmsResultRestResponse.getResult();

	}

	@Override
	protected PartyData createDefaultResult()
	{
		return createResultObject();
	}

	@Override
	protected PartyData createResultObject()
	{
		return new PartyData();
	}
}
