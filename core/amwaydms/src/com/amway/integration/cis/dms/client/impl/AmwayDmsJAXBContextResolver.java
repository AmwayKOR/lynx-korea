package com.amway.integration.cis.dms.client.impl;


import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ext.ContextResolver;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONJAXBContext;


public class AmwayDmsJAXBContextResolver implements ContextResolver<JAXBContext>
{
	private final Logger LOG = Logger.getLogger(AmwayDmsJAXBContextResolver.class);
	private final JAXBContext context;
	private final String[] classes =
	{ "com.amway.integration.dms.services.AddPartyAddressRequest", "com.amway.integration.dms.services.AddPartyEcommRequest",
			"com.amway.integration.dms.services.AddBankAccountRequest", "com.amway.integration.dms.services.AddPartyPhoneRequest",
			"com.amway.integration.dms.services.AddPartyCreditProfileRequest", "com.amway.integration.dms.services.Contact",
			"com.amway.integration.dms.services.AddPersonalIdRequest",
			"com.amway.integration.dms.services.UpdateSubscriptionRequest",
			"com.amway.integration.dms.services.UpdatePartyEcommRequest",
			"com.amway.integration.dms.services.UpdatePartyPhoneRequest", "com.amway.integration.dms.services.AddTaxIdRequest",
			"com.amway.integration.dms.services.ProcessPartyEcommRequest", "com.amway.integration.dms.services.AmwayProfileOutput" };


	private final Set<Class> types = new HashSet();

	public AmwayDmsJAXBContextResolver() throws JAXBException
	{
		LOG.info("Loading AmwayDmsJAXBContextResolver ");
		for (final String className : classes)
		{
			try
			{
				types.add(Class.forName(className));
			}
			catch (final ClassNotFoundException e)
			{
				LOG.error("Error in getting the clazz for " + className, e);
			}
		}

		this.context = new JSONJAXBContext(JSONConfiguration.natural().build(), types.toArray(new Class[types.size()]));
	}

	@Override
	public JAXBContext getContext(final Class objectType)
	{
		if (types.contains(objectType))
		{
			LOG.info(" AmwayDmsJAXBContextResolver getContext - called for " + objectType.getName());
			return (context);
		}
		return (null);
	}
}