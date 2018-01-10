package com.amway.integration.dam.client.handler;


import static com.amway.integration.dam.constants.AmwayDamConstants.WARNING_HTTP_STATUS_RESPONSE;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;


public class AmwayDamErrorResponseHandler implements ResponseErrorHandler
{
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getName());

	@Override
	public boolean hasError(final ClientHttpResponse response) throws IOException
	{
		final HttpStatus.Series series = response.getStatusCode().series();
		return HttpStatus.Series.CLIENT_ERROR.equals(series) || HttpStatus.Series.SERVER_ERROR.equals(series);
	}

	@Override
	public void handleError(final ClientHttpResponse response) throws IOException
	{
		LOGGER.warn(WARNING_HTTP_STATUS_RESPONSE + ": Response status: statusCode=[" + response.getStatusCode() + "], statusText=["
				+ response.getStatusText() + "]");
	}
}
