package com.amway.core.los.service;

/**
 * Interface for Los service
 *
 * @param <X>
 * @param <Y>
 */
public interface LosService<X, Y>
{
	/**
	 * To process requestData of service
	 *
	 * @param requestData
	 * @return service response
	 */
	Y process(final X requestData);
}
