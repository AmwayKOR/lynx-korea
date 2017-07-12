/**
 * 
 */
package com.amway.core.pos.dao;


import com.amway.core.model.AmwayTerminalModel;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import java.util.List;

/**
 * Data access to {@link AmwayTerminalModel}
 */
public interface AmwayTerminalDao
{
	public List<AmwayTerminalModel> findTerminals(final PointOfServiceModel pointOfService);
}
