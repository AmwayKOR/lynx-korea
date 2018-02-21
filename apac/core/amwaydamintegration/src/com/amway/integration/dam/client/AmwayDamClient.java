package com.amway.integration.dam.client;

import java.io.InputStream;
import java.util.Map;


/**
 * Interface for DAM Client
 */
public interface AmwayDamClient
{
	/**
	 * Execute GET call to specified endpoint of REST service.
	 *
	 * @param relativePath
	 * 		path to asset
	 * @param urlPath
	 * 		service endpoint constant
	 * @return JSON in Map representation
	 */
	Map<String, Object> executeRestRequest(final String relativePath, final String urlPath);

	/**
	 * Execute GET call to specified endpoint to download binary.
	 *
	 * @param relativePath
	 * 		path to asset
	 * @param urlPath
	 * 		service endpoint constant
	 * @param renditionId
	 * 		id of rendition in DAM
	 * @return stream of bytes
	 */
	InputStream executeBinaryRequest(final String relativePath, final String urlPath, final String renditionId);
}
