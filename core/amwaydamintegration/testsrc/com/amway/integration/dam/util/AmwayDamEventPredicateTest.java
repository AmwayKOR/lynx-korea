package com.amway.integration.dam.util;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import de.hybris.bootstrap.annotations.UnitTest;

import org.junit.Before;
import org.junit.Test;

import com.amway.integration.dam.data.AmwayDamEventData;


/**
 * Unit Test for {@link AmwayDamEventPredicate}
 */
@UnitTest
public class AmwayDamEventPredicateTest
{
	private static final String SUPPORTED_OPERATION = "operation";
	private static final String NOT_SUPPORTED_OPERATION = "another-operation";
	private static final String ANY_PATH = "/some/path";

	private final AmwayDamEventPredicate amwayDamEventPredicate = new AmwayDamEventPredicate();

	private final AmwayDamEventData eventData = new AmwayDamEventData();

	@Before
	public void setUp()
	{
		amwayDamEventPredicate.setAllowedOperations(singletonList(SUPPORTED_OPERATION));
		eventData.setOperation(SUPPORTED_OPERATION);
		eventData.setPath(ANY_PATH);
	}

	@Test
	public void shouldReturnFalseWhenPathIsNull()
	{
		eventData.setPath(null);

		boolean result = amwayDamEventPredicate.test(eventData);

		assertFalse(result);
	}

	@Test
	public void shouldReturnFalseWhenOperationIsNull()
	{
		eventData.setOperation(null);

		boolean result = amwayDamEventPredicate.test(eventData);

		assertFalse(result);
	}

	@Test
	public void shouldReturnFalseWhenUnsupportedOperation()
	{
		eventData.setOperation(NOT_SUPPORTED_OPERATION);

		boolean result = amwayDamEventPredicate.test(eventData);

		assertFalse(result);
	}

	@Test
	public void shouldReturnTrueWhenPathAndOperationCorrect()
	{
		boolean result = amwayDamEventPredicate.test(eventData);

		assertTrue(result);
	}
}