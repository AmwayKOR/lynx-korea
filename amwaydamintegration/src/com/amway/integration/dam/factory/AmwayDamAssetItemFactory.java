package com.amway.integration.dam.factory;

import com.amway.integration.dam.model.AmwayDamAssetItemModel;


/**
 * Factory for creating instances of {@link AmwayDamAssetItemModel}
 */
public interface AmwayDamAssetItemFactory
{
	/**
	 * Create instance of {@link AmwayDamAssetItemModel}
	 *
	 * @return instance of {@link AmwayDamAssetItemModel}
	 */
	AmwayDamAssetItemModel create();
}
