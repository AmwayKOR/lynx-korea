/**
 *
 */
package com.amway.core.pos.dao;


import com.amway.core.model.AmwayTerminalMacAddressModel;
import com.amway.core.model.AmwayTerminalModel;
import de.hybris.platform.servicelayer.internal.dao.GenericDao;

import java.util.List;


/**
 * Data access to {@link AmwayTerminalModel}
 */
public interface AmwayTerminalMacAddressDao extends GenericDao<AmwayTerminalMacAddressModel>
{
    /**
     * @param macAddress
     * @return
     */
    List<AmwayTerminalMacAddressModel> getMacAddress(String macAddress);
}
