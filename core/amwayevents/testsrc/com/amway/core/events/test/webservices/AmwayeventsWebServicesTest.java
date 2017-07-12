/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2016 SAP SE or an SAP affiliate company.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 *
 *
 */
package com.amway.core.events.test.webservices;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.oauth2.constants.OAuth2Constants;
import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.webservicescommons.testsupport.client.WsRequestBuilder;
import de.hybris.platform.webservicescommons.testsupport.client.WsSecuredRequestBuilder;
import de.hybris.platform.webservicescommons.testsupport.server.NeedsEmbeddedServer;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.amway.core.events.beans.AmwayBusinessEvent;
import com.amway.core.events.constants.AmwayeventsConstants;
import com.amway.core.events.dto.AmwayBusinessEventWsDTO;
import com.amway.core.events.dto.AmwayBusinessEventsListWsDTO;


@NeedsEmbeddedServer(webExtensions = {AmwayeventsConstants.EXTENSIONNAME, OAuth2Constants.EXTENSIONNAME})
@IntegrationTest
public class AmwayeventsWebServicesTest extends ServicelayerTest
{
	public static final String OAUTH_CLIENT_ID = "events-consumer-test";
	public static final String OAUTH_CLIENT_PASS = "secret";

	private static final String BASE_URI = "eventbus";
	private static final String EVENTS_URI = BASE_URI + "/events";

	@Resource
	EventService eventService;

	@Before
	public void setUp() throws Exception
	{
		importCsv("/amwayevents/test/democustomer-data.impex", "utf-8");
	}

	private WsSecuredRequestBuilder getRequestBuilder()
	{
		return new WsSecuredRequestBuilder()//
				.extensionName(AmwayeventsConstants.EXTENSIONNAME)//
				.client(OAUTH_CLIENT_ID, OAUTH_CLIENT_PASS)//
				.grantClientCredentials();
	}

	@Test
	public void testGetEventsWithoutAuthorization()
	{
		WsRequestBuilder wsRequestBuilder = new WsRequestBuilder()//
				.extensionName(AmwayeventsConstants.EXTENSIONNAME);

		final Response result = wsRequestBuilder//
				.path(EVENTS_URI)//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.get();
		result.bufferEntity();
		Assert.assertEquals(Status.UNAUTHORIZED.getStatusCode(), result.getStatus());
	}

	@Test
	public void testGetEventsForAuthorizedUser()
	{
		final Response result = getRequestBuilder()//
				.path(EVENTS_URI)//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.get();
		result.bufferEntity();
		Assert.assertEquals(Status.OK.getStatusCode(), result.getStatus());
	}

	@Test
	public void testCreateAndGetEvents()
	{

		eventService.publishEvent(createTestEventObject("1234"));
		eventService.publishEvent(createTestEventObject("4321"));

		final Response result = getRequestBuilder()//
				.path(EVENTS_URI)//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.get();

		AmwayBusinessEventsListWsDTO list = result.readEntity(AmwayBusinessEventsListWsDTO.class);
		List<AmwayBusinessEventWsDTO> events = list.getEvents();
		Assert.assertEquals(2, events.size());
		AmwayBusinessEventWsDTO readEvent1 = events.get(0);
		Assert.assertEquals("testEvent", readEvent1.getName());
		Assert.assertEquals("1234", readEvent1.getEntityID());
		Assert.assertEquals("TestEntity", readEvent1.getEntityName());
		Assert.assertEquals("Test", readEvent1.getTriggeredBy());
		Assert.assertEquals("accountID", readEvent1.getAccountID());
		Assert.assertEquals("BLR", readEvent1.getCountryCode());

		AmwayBusinessEventWsDTO readEvent2 = events.get(1);
		Assert.assertEquals("4321", readEvent2.getEntityID());

		//second call
		final Response result2 = getRequestBuilder()//
				.path(EVENTS_URI)//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.get();
		AmwayBusinessEventsListWsDTO list2 = result2.readEntity(AmwayBusinessEventsListWsDTO.class);
		List<AmwayBusinessEventWsDTO> listEvents2 = list2.getEvents();
		if (listEvents2 == null)
		{
			listEvents2 = Collections.EMPTY_LIST;
		}
		Assert.assertEquals(0, listEvents2.size());
	}

	@Test
	public void testSetRetrievalTimeToNull()
	{
		eventService.publishEvent(createTestEventObject("5678"));
		final Response result = getRequestBuilder()//
				.path(EVENTS_URI)//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.get();
		AmwayBusinessEventsListWsDTO list = result.readEntity(AmwayBusinessEventsListWsDTO.class);
		List<AmwayBusinessEventWsDTO> events = list.getEvents();
		Assert.assertEquals(1, events.size());
		String code = events.get(0).getCode();

		AmwayBusinessEventWsDTO dto = new AmwayBusinessEventWsDTO();
		dto.setRetrievalTime(null);

		final Response result2 = getRequestBuilder()//
				.path(EVENTS_URI + "/" + code)//
				.build()
				.accept(MediaType.APPLICATION_JSON)
				.put(Entity.json(dto));

		AmwayBusinessEventsListWsDTO updateAnswer = result2.readEntity(AmwayBusinessEventsListWsDTO.class);
		Assert.assertEquals(new Integer(HttpStatus.OK.value()), updateAnswer.getReturnCode());
		Assert.assertEquals("Success", updateAnswer.getReturnMessage());
		Assert.assertEquals(Status.OK.getStatusCode(), result2.getStatus());

		//now request the event again - it must be in the result since we've reset retrieval time
		final Response resultAfterSetTime = getRequestBuilder()//
				.path(EVENTS_URI)//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.get();
		AmwayBusinessEventsListWsDTO newList = resultAfterSetTime.readEntity(AmwayBusinessEventsListWsDTO.class);
		Assert.assertEquals(1, newList.getEvents().size());
		Assert.assertEquals(code, newList.getEvents().get(0).getCode());
	}

	private AmwayBusinessEvent createTestEventObject(String entityId)
	{
		Map<String, String> meta = new HashMap<>();
		meta.put("test1", "value1");
		meta.put("test2", "value2");
		AmwayBusinessEvent ev = new AmwayBusinessEvent();
		ev.setName("testEvent");
		ev.setEntityID(entityId);
		ev.setEntityName("TestEntity");
		ev.setTriggeredBy("Test");
		ev.setAccountID("accountID");
		ev.setCountryCode("BLR");
		ev.setTargetSystem("wm");
		return ev;
	}

}
