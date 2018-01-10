package com.amway.integration.dam.dao.impl;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Mockito.*;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amway.integration.dam.model.AmwayDamQueueEntryModel;


/**
 * Unit Test for {@link AmwayDamEventQueueDaoImpl}
 */
@UnitTest
public class AmwayDamEventQueueDaoImplTest
{
	@InjectMocks
	private AmwayDamEventQueueDaoImpl amwayDamEventQueueDao;

	@Mock
	private FlexibleSearchService flexibleSearchService;

	@Mock
	private SearchResult searchResult;
	@Mock
	private AmwayDamQueueEntryModel queueEntry;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		when(flexibleSearchService.search(any(FlexibleSearchQuery.class))).thenReturn(searchResult);
	}

	@Test
	public void shouldSearchWithSpecifiedLimit()
	{
		AmwayDamEventQueueDaoImpl spyAmwayDamEventQueueDao = spy(amwayDamEventQueueDao);
		Integer limit = 1000;

		spyAmwayDamEventQueueDao.findEvents(limit);

		verify(spyAmwayDamEventQueueDao).find(anyMap(), any(), eq(limit));
	}

	@Test
	public void shouldReturnResultsWhenResultsExistByQuery()
	{
		List<AmwayDamQueueEntryModel> expected = singletonList(queueEntry);
		when(searchResult.getResult()).thenReturn(expected);

		List<AmwayDamQueueEntryModel> result = amwayDamEventQueueDao.findEvents(anyInt());

		assertEquals(expected, result);
	}
}