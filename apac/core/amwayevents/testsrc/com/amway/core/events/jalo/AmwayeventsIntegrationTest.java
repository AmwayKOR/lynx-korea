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
package com.amway.core.events.jalo;

import static org.junit.Assert.assertTrue;

import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.servicelayer.event.EventService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.events.beans.AmwayBusinessEvent;
import com.amway.core.events.services.AmwayBusinessEventQueueService;


/**
 * JUnit Tests for the Amwayevents extension
 */
public class AmwayeventsIntegrationTest extends ServicelayerTest
{
	private static final Logger LOG = Logger.getLogger(AmwayeventsIntegrationTest.class.getName());

	@Resource
	EventService eventService;

	@Resource
	AmwayBusinessEventQueueService amwayBusinessEventQueueService;

	@Before
	public void setUp()
	{
		// implement here code executed before each test
	}

	@After
	public void tearDown()
	{
		// implement here code executed after each test
	}

	/**
	 * Test that events are put into the queue.
	 */
	@Test
	public void testEventsPutIntoQueueAndExtract() throws Exception
	{
		AmwayBusinessEvent ev = createTestEvent();

		eventService.publishEvent(ev);

		List<AmwayBusinessEvent> events = amwayBusinessEventQueueService.getUnexportedEvents(10, "WM");
		Assert.assertEquals(1, events.size());
		AmwayBusinessEvent e = events.get(0);
		Assert.assertNotNull(e.getCode());
		Assert.assertEquals(ev.getName(), e.getName());
		Assert.assertEquals(ev.getEntityID(), e.getEntityID());
		Assert.assertEquals(ev.getEntityName(), e.getEntityName());
		Assert.assertEquals(ev.getTriggeredBy(), e.getTriggeredBy());
		Assert.assertEquals(ev.getAccountID(), e.getAccountID());
		Assert.assertEquals(ev.getCountryCode(), e.getCountryCode());
		Assert.assertEquals(ev.getTargetSystem(), e.getTargetSystem());
		Assert.assertEquals(ev.getMeta().size(), e.getMeta().size());
		Assert.assertEquals(ev.getMeta().get("test1"), e.getMeta().get("test1"));
		Assert.assertEquals(ev.getMeta().get("test2"), e.getMeta().get("test2"));

		amwayBusinessEventQueueService.confirmExported(e.getCode());
		List<AmwayBusinessEvent> afterExportedEvents = amwayBusinessEventQueueService.getUnexportedEvents(10, "WM");
		Assert.assertEquals(0, afterExportedEvents.size());
	}

	private AmwayBusinessEvent createTestEvent()
	{
		Map<String, String> meta = new HashMap<>();
		meta.put("test1", "value1");
		meta.put("test2", "value2");
		AmwayBusinessEvent ev = new AmwayBusinessEvent();
		ev.setName("testEvent");
		ev.setEntityID("1234");
		ev.setEntityName("TestEntity");
		ev.setTriggeredBy("Test");
		ev.setAccountID("accountID");
		ev.setCountryCode("BLR");
		ev.setGenerationTime(new java.util. Date());
		ev.setTargetSystem("WM");
		ev.setMeta(meta);
		return ev;
	}

	@Test
	public void testPurgeOldEvents() throws Exception
	{
		AmwayBusinessEvent ev = createTestEvent();
		eventService.publishEvent(ev);
		List<AmwayBusinessEvent> events = amwayBusinessEventQueueService.getUnexportedEvents(10, "WM");
		Assert.assertEquals(1, events.size());
		amwayBusinessEventQueueService.purgeOldEvents(new Date(System.currentTimeMillis() + 1000)); //it purges all events
		events = amwayBusinessEventQueueService.getUnexportedEvents(10, "WM");
		Assert.assertEquals(0, events.size());
	}

}
