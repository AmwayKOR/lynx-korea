package com.amway.core.controllers.v2;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.amway.core.commercewebservices.dto.renewal.AmwayAutoRenewalResponseWsDTO;
import com.amway.core.v2.controller.BaseController;
import com.amway.facades.renewal.AmwayRenewalFacade;
import com.amway.facades.renewal.data.AmwayAutoRenewalRequestData;
import com.amway.facades.renewal.data.AmwayAutoRenewalResponseData;


@RestController
@RequestMapping("/{baseSiteId}/registration/renewal/accounts")
public class AmwayAutoRenewalController extends BaseController
{
	private static final Logger LOGGER = LoggerFactory.getLogger(AmwayAutoRenewalController.class);

	@Resource(name = "amwayRenewalFacade")
	private AmwayRenewalFacade amwayRenewalFacade;

	@Resource(name = "autoRenewalRequestDataValidator")
	private Validator autoRenewalRequestDataValidator;

	@RequestMapping(value = "/{aboNum}/parties/{partyId}", method = RequestMethod.POST)
	@ResponseBody
	public AmwayAutoRenewalResponseWsDTO handleAutoRenewal(@PathVariable String aboNum, @PathVariable String partyId,
			@RequestBody AmwayAutoRenewalRequestData autoRenewalRequest)
	{
		LOGGER.info("Start AutoRenewal process: ABO {}, Party {}, ", aboNum, partyId);

		validate(autoRenewalRequest, "AmwayAutoRenewalRequestData", autoRenewalRequestDataValidator);

		AmwayAutoRenewalResponseData response = amwayRenewalFacade.placeAutoRenewalOrder(aboNum, partyId, autoRenewalRequest);
		AmwayAutoRenewalResponseWsDTO autoRenewalResponseWsDTO = getDataMapper().map(response, AmwayAutoRenewalResponseWsDTO.class);

		LOGGER.info("End AutoRenewal process: ABO : {}, Party : {} ", aboNum, partyId);

		return autoRenewalResponseWsDTO;
	}
}
