package com.amway.integration.cis.los.client.impl;

import com.amway.core.los.data.BonusStatementRequestData;
import com.amway.integration.cis.los.client.LOSClient;
import com.amway.integration.cis.los.pojo.DetailResponse;
import com.amway.integration.cis.los.pojo.LosAccountRequest;
import com.amway.integration.cis.los.pojo.LosResponse;
import com.amway.integration.cis.los.pojo.TransactionGroup;
import com.hybris.cis.api.exception.AbstractCisServiceException;
import com.hybris.cis.client.rest.common.impl.AbstractCisClient;
import com.hybris.commons.client.RestCallBuilder;
import com.hybris.commons.client.RestResponse;
import com.hybris.commons.client.RestResponseException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;
import java.io.File;


/**
 * Implementation for LOS CLIENT
 */
public class LOSClientImpl extends AbstractCisClient implements LOSClient
{
	private final Logger LOG = Logger.getLogger(LOSClientImpl.class);

	/**
	 * @param xCisClientRef
	 */
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
			return (RestResponse) e.unwrap(AbstractCisServiceException.class);
		}
	}

	/**
	 * {@link #executeLosRequest(java.lang.String, java.lang.String, java.lang.Object, java.lang.Class)}
	 */
	@Override
	public <T> RestResponse<T> executeLosRequest(final String xCisClientRef, final String urlPath, final Object requestEntity,
			final Class<T> clazz) throws AbstractCisServiceException
	{
		final RestCallBuilder builder = getClient().call(urlPath, new Object[0]);
		//builder.accept(MediaType.APPLICATION_JSON_TYPE);
		builder.type(MediaType.APPLICATION_JSON_TYPE);
		addHeader(builder, xCisClientRef);
		try
		{
			return builder.post(clazz, requestEntity);
		}
		catch (final RestResponseException e)
		{
			return (RestResponse) e.unwrap(AbstractCisServiceException.class);
		}
	}



	/**
	 * {@link #executeLosOrderRequest(java.lang.String, java.lang.String, java.lang.Object)}
	 */
	@Override
	public <T> RestResponse<Void> executeLosOrderRequest(final String xCisClientRef, final String urlPath,
			final Object requestEntity) throws AbstractCisServiceException
	{
		if (urlPath.endsWith("order/reverse"))
		{
			final TransactionGroup transactionGroup = (TransactionGroup) requestEntity;
			LOG.info("Sending bonus reverse transaction for order " + transactionGroup.getId());
			LOG.info(transactionGroup.getSourceApplication());
			if (CollectionUtils.isNotEmpty(transactionGroup.getTransactions()))
			{
				LOG.info(String.valueOf(transactionGroup.getTransactions().size()));
				LOG.info("ABOID: " + transactionGroup.getTransactions().get(0).getAboId());
				LOG.info("Amount: " + transactionGroup.getTransactions().get(0).getAmount());
			}
			else
			{
				LOG.info("Empty Transactions found");
			}
		}

		final RestCallBuilder builder = getClient().call(urlPath, new Object[0]);
		//builder.accept(MediaType.APPLICATION_JSON_TYPE);
		builder.type(MediaType.APPLICATION_JSON_TYPE);
		addHeader(builder, xCisClientRef);
		try
		{
			return builder.put(requestEntity);
		}
		catch (final RestResponseException e)
		{
			return (RestResponse) e.unwrap(AbstractCisServiceException.class);
		}
	}

	/**
	 * {@link #executeLosAccountRequest(java.lang.String, java.lang.String, java.lang.Object, java.lang.Class)}
	 */
	@Override
	public <T> RestResponse<LosResponse> executeLosAccountRequest(final String xCisClientRef, final String urlPath,
			final Object requestEntity, final Class<T> clazz) throws AbstractCisServiceException
	{
		final LosAccountRequest request = convertToJAXBElement(requestEntity);

		final RestCallBuilder builder = getClient()
				.call(urlPath + (request.getAbo() != null ? request.getAbo() : request.getRequestingAbo()) + getResourceRoot()
								+ request.getBonusPeriod(), new Object[0]);

		builder.queryParam("requestingabo", request.getRequestingAbo());
		builder.queryParam("depth", request.getDepth());
		builder.queryParam("getcustomers", "true");
		//builder.accept(MediaType.APPLICATION_JSON_TYPE);
		builder.type(MediaType.APPLICATION_JSON_TYPE);
		addHeader(builder, xCisClientRef);
		try
		{
			return builder.get(LosResponse.class);
		}
		catch (final RestResponseException e)
		{
			return (RestResponse) e.unwrap(AbstractCisServiceException.class);
		}
	}

	/**
	 * {@link #executeLosBonusStatementRequest(java.lang.String, java.lang.String, java.lang.Object, java.lang.Class)}
	 */
	@Override
	public <T> RestResponse<File> executeLosBonusStatementRequest(final String xCisClientRef, final String urlPath,
			final Object requestEntity, final Class<T> clazz) throws AbstractCisServiceException
	{
		final BonusStatementRequestData request = (BonusStatementRequestData) requestEntity;

		final RestCallBuilder builder = getClient()
				.call("account/" + request.getRequestingAbo() + urlPath + request.getBonusPeriod(), new Object[0]);

		builder.accept("application/pdf");
		//builder.type(MediaType.APPLICATION_JSON_TYPE);
		addHeader(builder, xCisClientRef);
		try
		{
			return builder.get(File.class);
		}
		catch (final RestResponseException e)
		{
			return (RestResponse) e.unwrap(AbstractCisServiceException.class);
		}

	}

	/**
	 * {@link #executeLosAccountDetailRequest(java.lang.String, java.lang.String, java.lang.Object, java.lang.Class)}
	 */
	@Override
	public <T> RestResponse<DetailResponse> executeLosAccountDetailRequest(final String xCisClientRef, final String urlPath,
			final Object requestEntity, final Class<T> clazz) throws AbstractCisServiceException
	{
		final LosAccountRequest request = convertToJAXBElement(requestEntity);

		final RestCallBuilder builder = getClient().call(urlPath + request.getBonusPeriod(), new Object[0]);

		if (StringUtils.isNotBlank(request.getBonusPeriodCount()))
		{
			builder.queryParam("bonusperiodcount", request.getBonusPeriodCount());
		}
		builder.queryParam("requestingabo", request.getRequestingAbo());
		builder.queryParam("getvolume", request.getGetVolume().toString());
		builder.queryParam("getsponsorstats", request.getGetSponsorStats().toString());
		builder.queryParam("getextattributes", request.getGetExtAttributes().toString());
		builder.queryParam("abo", request.getAbo());
		builder.queryParam("BonusPeriod", request.getBonusPeriod());
		builder.queryParam("getqualification", request.getGetQualification().toString());
		if (request.getGetCustomers() != null)
		{
			builder.queryParam("getcustomers", request.getGetCustomers().toString());
		}
		//builder.accept(MediaType.APPLICATION_JSON_TYPE);
		builder.type(MediaType.APPLICATION_JSON_TYPE);
		addHeader(builder, xCisClientRef);
		try
		{
			return builder.get(DetailResponse.class);
		}
		catch (final RestResponseException e)
		{
			return (RestResponse) e.unwrap(AbstractCisServiceException.class);
		}
	}

	protected String getResourceRoot()
	{
		return "/los/";
	}

	protected LosAccountRequest convertToJAXBElement(final Object requestEntity)
	{
		final JAXBElement<LosAccountRequest> jaxbRequest = (JAXBElement<LosAccountRequest>) requestEntity;
		return jaxbRequest.getValue();
	}

}
