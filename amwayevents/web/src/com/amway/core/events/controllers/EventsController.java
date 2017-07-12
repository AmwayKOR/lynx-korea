package com.amway.core.events.controllers;

import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amway.core.events.beans.AmwayBusinessEvent;
import com.amway.core.events.constants.AmwayeventsConstants;
import com.amway.core.events.dto.AmwayBusinessEventWsDTO;
import com.amway.core.events.dto.AmwayBusinessEventsListWsDTO;
import com.amway.core.events.model.AmwayEventQueueEntryModel;
import com.amway.core.events.services.AmwayBusinessEventQueueService;


/**
 * Events endpoint
 */
@Controller
@RequestMapping(value = "/eventbus")
public class EventsController
{

	private static final String SUCCESS = "Success";

	@Autowired
	private AmwayBusinessEventQueueService amwayBusinessEventQueueService;

	@Autowired
	private Converter<AmwayBusinessEvent, AmwayEventQueueEntryModel> eventToQueueEntryConverter;

	@Autowired
	private Converter<AmwayBusinessEvent, AmwayBusinessEventWsDTO> eventToEventWsConverter;

	@Autowired
	private Converter<AmwayBusinessEventWsDTO, AmwayBusinessEvent> eventWsToEventConverter;

	@Secured({"ROLE_TRUSTED_CLIENT"})
	@RequestMapping(value = "/events", method = RequestMethod.GET)
	@ResponseBody
	public AmwayBusinessEventsListWsDTO getEvents(@RequestParam(required = false) final Integer limit) throws Exception
	{
		if (limit != null && limit < 0) {
			AmwayBusinessEventsListWsDTO res = new AmwayBusinessEventsListWsDTO();
			res.setReturnCode(HttpStatus.BAD_REQUEST.value());
			res.setReturnMessage("Bad syntax: limit must be a positive number");
			return res;
		}
		List<AmwayBusinessEvent> events = amwayBusinessEventQueueService.getUnexportedEvents(limit,
				AmwayeventsConstants.TARGET_WM);
		List<String> ids = events.stream().map(AmwayBusinessEvent::getCode).collect(Collectors.toList());
		List<AmwayBusinessEventWsDTO> wsList = events.stream().map(eventToEventWsConverter::convert).collect(Collectors.toList());
		amwayBusinessEventQueueService.confirmExported(ids);
		AmwayBusinessEventsListWsDTO res = new AmwayBusinessEventsListWsDTO();
		res.setReturnCode(HttpStatus.OK.value());
		res.setReturnMessage(SUCCESS);
		res.setEvents(wsList);
		return res;
	}

	@Secured({"ROLE_TRUSTED_CLIENT"})
	@RequestMapping(value = "/events/{eventCode}", method = RequestMethod.PUT)
	@ResponseBody
	public AmwayBusinessEventsListWsDTO setEventProperties(@PathVariable final String eventCode,
			@RequestBody AmwayBusinessEventWsDTO eventInfo) {
		AmwayBusinessEvent event = eventWsToEventConverter.convert(eventInfo);
		try {
			amwayBusinessEventQueueService.setEventProperties(eventCode, event);
		} catch (IllegalArgumentException ex) {
			AmwayBusinessEventsListWsDTO res = new AmwayBusinessEventsListWsDTO();
			res.setReturnCode(HttpStatus.NOT_FOUND.value());
			res.setReturnMessage(MessageFormat.format("Event {0} not found", eventCode));
			return res;
		}
		AmwayBusinessEventsListWsDTO res = new AmwayBusinessEventsListWsDTO();
		res.setReturnCode(HttpStatus.OK.value());
		res.setReturnMessage(SUCCESS);
		return res;
	}
}
