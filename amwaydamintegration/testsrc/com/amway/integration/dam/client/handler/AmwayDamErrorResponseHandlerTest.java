package com.amway.integration.dam.client.handler;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.*;

import de.hybris.bootstrap.annotations.UnitTest;

import org.junit.Test;
import org.springframework.http.client.ClientHttpResponse;


/**
 * Unit Test for {@link AmwayDamErrorResponseHandler}
 */
@UnitTest
public class AmwayDamErrorResponseHandlerTest
{
	private final AmwayDamErrorResponseHandler amwayDamErrorResponseHandler = new AmwayDamErrorResponseHandler();

	private final ClientHttpResponse clientHttpResponse = mock(ClientHttpResponse.class);

	@Test
	public void shouldHasErrorWhenHttpCodeStartFromFour() throws Exception
	{
		when(clientHttpResponse.getStatusCode()).thenReturn(BAD_REQUEST, UNAUTHORIZED, NOT_FOUND);

		boolean resultBadRequest = amwayDamErrorResponseHandler.hasError(clientHttpResponse);
		boolean resultUnauthorized = amwayDamErrorResponseHandler.hasError(clientHttpResponse);
		boolean resultNotFound = amwayDamErrorResponseHandler.hasError(clientHttpResponse);

		assertTrue(resultBadRequest);
		assertTrue(resultUnauthorized);
		assertTrue(resultNotFound);
	}

	@Test
	public void shouldHasErrorWhenHttpCodeStartFromFive() throws Exception
	{
		when(clientHttpResponse.getStatusCode()).thenReturn(INTERNAL_SERVER_ERROR, BAD_GATEWAY, SERVICE_UNAVAILABLE);

		boolean resultInternalError = amwayDamErrorResponseHandler.hasError(clientHttpResponse);
		boolean resultBadGateway = amwayDamErrorResponseHandler.hasError(clientHttpResponse);
		boolean resultUnavailable = amwayDamErrorResponseHandler.hasError(clientHttpResponse);

		assertTrue(resultInternalError);
		assertTrue(resultBadGateway);
		assertTrue(resultUnavailable);
	}

	@Test
	public void shouldHasNoErrorWhenHttpCodeNotFromSeriesFiveOrFour() throws Exception
	{
		when(clientHttpResponse.getStatusCode()).thenReturn(OK, CONTINUE, FOUND);

		boolean resultOk = amwayDamErrorResponseHandler.hasError(clientHttpResponse);
		boolean resultContinue = amwayDamErrorResponseHandler.hasError(clientHttpResponse);
		boolean resultFound = amwayDamErrorResponseHandler.hasError(clientHttpResponse);

		assertFalse(resultOk);
		assertFalse(resultContinue);
		assertFalse(resultFound);
	}
}