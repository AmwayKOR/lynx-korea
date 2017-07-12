package com.amway.core.dms.service;

/**
 * Interface for DmsService.
 *
 * @param <X>
 * @param <Y>
 */
public interface DmsService<X, Y>
{
	/**
	 * To process requestData of service.
	 *
	 * @param requestData
	 * @return service response
	 */
	Y process(final X requestData);
}
