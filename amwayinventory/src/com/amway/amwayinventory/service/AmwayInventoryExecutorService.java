package com.amway.amwayinventory.service;

import java.util.concurrent.Executor;


/**
 * Service for work with executors for inventory updates
 */
public interface AmwayInventoryExecutorService
{
	/**
	 * Find executor for specified warehouse
	 *
	 * @param warehouseCode
	 * 		code of warehouse model
	 * @return executor for warehouse
	 */
	Executor getExecutorByWarehouse(String warehouseCode);
}
