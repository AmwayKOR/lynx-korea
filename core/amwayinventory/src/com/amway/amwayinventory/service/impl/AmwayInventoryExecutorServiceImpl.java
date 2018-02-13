package com.amway.amwayinventory.service.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

import com.amway.amwayinventory.service.AmwayInventoryExecutorService;


public class AmwayInventoryExecutorServiceImpl implements AmwayInventoryExecutorService
{
	private final Map<String, Executor> warehouseToExecutorMap = new ConcurrentHashMap<>();

	public Executor getExecutorByWarehouse(String warehouseCode)
	{
		return warehouseToExecutorMap.computeIfAbsent(warehouseCode, entry -> createExecutor());
	}

	protected Executor createExecutor()
	{
		throw new UnsupportedOperationException(
				"please override AmwayInventoryExecutorServiceImpl#createExecutor() or use <lookup-method>");
	}
}