package com.amway.integration.dam.processor;

import com.amway.integration.dam.data.AmwayDamProcessingData;


/**
 * Processing of {@link AmwayDamProcessingData}
 */
public interface AmwayDamAssetProcessor
{
	/**
	 * Processing creation of different DAM instances
	 *
	 * @param processingData
	 * 	  information of processing DAM data
	 */
	void processCreate(AmwayDamProcessingData processingData);

	/**
	 * Processing update of different DAM instances
	 *
	 * @param processingData
	 * 	  information of processing DAM data
	 */
	void processUpdate(AmwayDamProcessingData processingData);

	/**
	 * Processing removal of different DAM instances
	 *
	 * @param processingData
	 * 	  information of processing DAM data
	 */
	void processRemove(AmwayDamProcessingData processingData);

	/**
	 * Processing storage of metadata of DAM assets
	 *
	 * @param processingData
	 * 	  information of processing DAM data
	 */
	void processMetadata(AmwayDamProcessingData processingData);

	/**
	 * Processing removal of metadata of DAM assets
	 *
	 * @param processingData
	 * 	  information of processing DAM data
	 */
	void processRemoveMetadata(AmwayDamProcessingData processingData);
}
