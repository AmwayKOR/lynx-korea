package com.amway.integration.dam.strategy;

import java.util.Collection;

import com.amway.integration.dam.data.AmwayDamRenditionData;


/**
 * Strategy for filtering {@link AmwayDamRenditionData}
 */
public interface AmwayDamRenditionFilterStrategy
{
	/**
	 * Find suitable renditions by applying filtration
	 *
	 * @param renditions
	 * 		all renditions specified for asset in DAM
	 * @return collection of suitable renditions
	 */
	Collection<AmwayDamRenditionData> getSuitableRenditions(Collection<AmwayDamRenditionData> renditions);
}
