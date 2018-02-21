package com.amway.amwayinventory.service.impl;

import static com.amway.amwayinventory.AmwayInventoryTestConstants.WAREHOUSE_1;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import de.hybris.bootstrap.annotations.UnitTest;

import java.util.concurrent.Executor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;


@UnitTest
public class AmwayInventoryExecutorServiceImplTest
{
	@Spy
	private AmwayInventoryExecutorServiceImpl amwayInventoryExecutorService;

	@Mock
	private Executor executor;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void shouldThrowExceptionWhenTryCreateExecutor()
	{
		amwayInventoryExecutorService.createExecutor();
	}

	@Test
	public void shouldCreateNewExecutorWhenObtainExecutorForWarehouseFromServiceFirstTime()
	{
		doReturn(executor).when(amwayInventoryExecutorService).createExecutor();

		amwayInventoryExecutorService.getExecutorByWarehouse(WAREHOUSE_1);

		verify(amwayInventoryExecutorService).createExecutor();
	}

	@Test
	public void shouldReturnSameExecutorWhenExecutorForWarehouseAlreadyObtainedFromService()
	{
		doReturn(executor).when(amwayInventoryExecutorService).createExecutor();
		amwayInventoryExecutorService.getExecutorByWarehouse(WAREHOUSE_1);

		Executor result = amwayInventoryExecutorService.getExecutorByWarehouse(WAREHOUSE_1);

		assertEquals(executor, result);
		verifyZeroInteractions(amwayInventoryExecutorService.createExecutor());
	}
}