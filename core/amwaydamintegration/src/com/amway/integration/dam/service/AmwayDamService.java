package com.amway.integration.dam.service;

/**
 * Interface for DAM service
 *
 * @param <RQ>
 * 		Request type
 * @param <RE>
 * 		Response type
 */
public interface AmwayDamService<RQ, RE>
{
	/**
	 * To process requestData of service
	 *
	 * @param requestData
	 * 		service request
	 * @return service response
	 */
	RE process(final RQ requestData);
}
