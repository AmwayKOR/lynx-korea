package com.amway.integration.cis.dms.client;

import java.util.Map;

import com.hybris.cis.api.exception.AbstractCisServiceException;
import com.hybris.cis.client.rest.common.CisClient;
import com.hybris.commons.client.RestResponse;


/**
 * Interface for DMSClient
 */
public abstract interface DMSClient extends CisClient
{

	/**
	 * To execute the DmsRequest using POST method.
	 *
	 * @param xclientRefId
	 * @param urlPath
	 * @param requestEntity
	 * @param responseEntity
	 * @return Response
	 * @throws AbstractCisServiceException
	 */
	public abstract <T> RestResponse<T> executeDmsRequest(final String xclientRefId, final String urlPath, Object requestEntity,
			Class<T> responseEntity) throws AbstractCisServiceException;

	/**
	 * Execute the DmsRequest using GET method.
	 *
	 * @param <T>
	 * @param xclientRefId
	 * @param urlPath
	 * @param requestEntity
	 * @param responseEntity
	 * @return
	 * @throws AbstractCisServiceException
	 */
	public abstract <T> RestResponse<T> executeDmsGetRequest(final String xclientRefId, final String urlPath, Object requestEntity,
			Class<T> responseEntity) throws AbstractCisServiceException;

	<T> RestResponse<T> executeDmsPutRequest(String xCisClientRef, String urlPath, Object requestEntity, Class<T> clazz, Object... params) throws AbstractCisServiceException;

	<T> RestResponse<T> executeDmsDeleteRequest(String xCisClientRef, String urlPath, Object requestEntity, Class<T> clazz) throws AbstractCisServiceException;
}
