package com.amway.integration.cis.dms.client.impl;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import javax.ws.rs.core.MediaType;

import com.amway.integration.cis.dms.client.DMSClient;
import com.hybris.cis.api.exception.AbstractCisServiceException;
import com.hybris.cis.client.rest.common.impl.AbstractCisClient;
import com.hybris.commons.client.RestCallBuilder;
import com.hybris.commons.client.RestResponse;
import com.hybris.commons.client.RestResponseException;
import org.apache.log4j.Logger;

/**
 * Implementation for DMSClient
 */
public class DMSClientImpl extends AbstractCisClient implements DMSClient
{
	private static final Logger LOG = Logger.getLogger(DMSClientImpl.class);

	@Override
	public RestResponse<Void> ping(final String xCisClientRef)
	{
		final RestCallBuilder builder = getClient().call(null, new Object[0]);
		addHeader(builder, xCisClientRef);
		try
		{
			return builder.head();
		}
		catch (final RestResponseException e)
		{
			LOG.warn(e.getMessage(), e);
			return (RestResponse) e.unwrap(AbstractCisServiceException.class);
		}
	}

	@Override
	public <T> RestResponse<T> executeDmsRequest(final String xCisClientRef, final String urlPath, final Object requestEntity,
			final Class<T> clazz) throws AbstractCisServiceException
	{
		final RestCallBuilder builder = getClient().call(urlPath, new Object[0]);
		builder.type(MediaType.APPLICATION_JSON_TYPE);
		addHeader(builder, xCisClientRef);
		try
		{
			return builder.post(clazz, requestEntity);
		}
		catch (final RestResponseException e)
		{
			LOG.warn(e.getMessage(), e);
			return (RestResponse) e.unwrap(AbstractCisServiceException.class);
		}
	}

	@Override
	public <T> RestResponse<T> executeDmsGetRequest(final String xCisClientRef, final String urlPath, final Object requestEntity,
			final Class<T> clazz) throws AbstractCisServiceException
	{
		final RestCallBuilder builder = getClient().call(urlPath, new Object[0]);
		builder.type(MediaType.APPLICATION_JSON_TYPE);
		addHeader(builder, xCisClientRef);
		try
		{
			return builder.get(clazz);
		}
		catch (final RestResponseException e)
		{
			LOG.warn(e.getMessage(), e);
			return (RestResponse) e.unwrap(AbstractCisServiceException.class);
		}
	}

	@Override
	public <T> RestResponse<T> executeDmsPutRequest(final String xCisClientRef, final String urlPath, final Object requestEntity,
			final Class<T> clazz, Object... params) throws AbstractCisServiceException
	{
		final RestCallBuilder builder = getClient().call(urlPath, params);
		builder.type(MediaType.APPLICATION_JSON_TYPE);
		addHeader(builder, xCisClientRef);
		try
		{
			return builder.put(clazz, requestEntity);
		}
		catch (final RestResponseException e)
		{
			LOG.warn(e.getMessage(), e);
			return (RestResponse) e.unwrap(AbstractCisServiceException.class);
		}
	}

	@Override
	public <T> RestResponse<T> executeDmsDeleteRequest(final String xCisClientRef, final String urlPath, final Object requestEntity,
			final Class<T> clazz) throws AbstractCisServiceException
	{
		final RestCallBuilder builder = getClient().call(urlPath, new Object[0]);
		addHeader(builder, xCisClientRef);
		try
		{
			return builder.delete(clazz);
		}
		catch (final RestResponseException e)
		{
			LOG.warn(e.getMessage(), e);
			return (RestResponse) e.unwrap(AbstractCisServiceException.class);
		}
	}
}
