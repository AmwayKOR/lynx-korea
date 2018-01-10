package com.amway.integration.dam.validate;

import com.amway.integration.dam.data.AmwayDamProcessingData;


/**
 * Validator for {@link AmwayDamProcessingData}
 */
public interface AmwayDamValidator
{
	/**
	 * Validate {@link AmwayDamProcessingData}
	 *
	 * @param processingData
	 * 	  information of processing DAM data
	 */
	void validate(AmwayDamProcessingData processingData);
}
