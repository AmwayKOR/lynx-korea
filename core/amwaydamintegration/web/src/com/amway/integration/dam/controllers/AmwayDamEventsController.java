package com.amway.integration.dam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.amway.integration.dam.data.AmwayEventRegistrationRequest;
import com.amway.integration.dam.service.AmwayDamQueueService;


@RestController
@RequestMapping(value = "/assets")
public class AmwayDamEventsController
{
	@Autowired
	private AmwayDamQueueService amwayDamQueueService;

	@RequestMapping(method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void registerEvents(@RequestBody final AmwayEventRegistrationRequest request)
	{
		amwayDamQueueService.registerEvents(request.getEvents());
	}
}
