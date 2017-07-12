package com.amway.integration.cis.dms.client.impl;


import org.glassfish.jersey.client.ClientConfig;

import com.hybris.commons.client.ClientConfigFactoryBean;


public class AmwayDmsClientConfigFactoryBean extends ClientConfigFactoryBean
{
	@Override
	public ClientConfig getObject()
	{
		final ClientConfig config = super.getObject();
		config.register(AmwayDmsJAXBContextResolver.class);
		return config;
	}
}