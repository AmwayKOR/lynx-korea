package com.amway.integration.cis.los.client;

import java.io.File;

import com.amway.integration.cis.los.pojo.DetailResponse;
import com.amway.integration.cis.los.pojo.LosResponse;
import com.hybris.cis.api.exception.AbstractCisServiceException;
import com.hybris.cis.client.rest.common.CisClient;
import com.hybris.commons.client.RestResponse;


/**
 * Interface for LOSClient
 */
public abstract interface LOSClient extends CisClient
{
	/**
	 * To execute Los request
	 *
	 * @param xclientRefId
	 * @param urlPath
	 * @param requestEntity
	 * @param responseEntity
	 * @return
	 * @throws AbstractCisServiceException
	 */
	public abstract <T> RestResponse<T> executeLosRequest(final String xclientRefId, final String urlPath, Object requestEntity,
			Class<T> responseEntity) throws AbstractCisServiceException;


	/**
	 * To execute Los order request
	 *
	 * @param xclientRefId
	 * @param urlPath
	 * @param requestEntity
	 * @return
	 * @throws AbstractCisServiceException
	 */
	public abstract <T> RestResponse<Void> executeLosOrderRequest(final String xclientRefId, final String urlPath,
			Object requestEntity) throws AbstractCisServiceException;


	/**
	 * To execute Los account request
	 *
	 * @param xCisClientRef
	 * @param urlPath
	 * @param requestEntity
	 * @param clazz
	 * @return
	 * @throws AbstractCisServiceException
	 */
	public abstract <T> RestResponse<LosResponse> executeLosAccountRequest(final String xCisClientRef, final String urlPath,
			final Object requestEntity, final Class<T> clazz) throws AbstractCisServiceException;

	/**
	 * To execute Los Account Detail request
	 *
	 * @param xCisClientRef
	 * @param urlPath
	 * @param requestEntity
	 * @param clazz
	 * @return
	 * @throws AbstractCisServiceException
	 */
	public <T> RestResponse<DetailResponse> executeLosAccountDetailRequest(final String xCisClientRef, final String urlPath,
			final Object requestEntity, final Class<T> clazz) throws AbstractCisServiceException;

	/**
	 * To execute Los bonus statement request
	 *
	 * @param xCisClientRef
	 * @param urlPath
	 * @param requestEntity
	 * @param clazz
	 * @return
	 * @throws AbstractCisServiceException
	 */
	public <T> RestResponse<File> executeLosBonusStatementRequest(final String xCisClientRef, final String urlPath,
			final Object requestEntity, final Class<T> clazz) throws AbstractCisServiceException;
}
