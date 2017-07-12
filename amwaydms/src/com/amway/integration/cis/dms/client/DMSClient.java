package com.amway.integration.cis.dms.client;

import com.hybris.cis.api.exception.AbstractCisServiceException;
import com.hybris.cis.client.rest.common.CisClient;
import com.hybris.commons.client.RestResponse;


/**
 * Interface for DMSClient
 */
public abstract interface DMSClient extends CisClient
{

	/**
	 * To execute the DmsRequest
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

}
