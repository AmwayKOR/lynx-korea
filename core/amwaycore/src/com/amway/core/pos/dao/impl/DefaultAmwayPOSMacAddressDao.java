/*
 *
 */
package com.amway.core.pos.dao.impl;

import com.amway.core.model.AmwayTerminalMacAddressModel;
import com.amway.core.pos.dao.AmwayTerminalMacAddressDao;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;


/**
 * Default implementation
 */
public class DefaultAmwayPOSMacAddressDao extends DefaultGenericDao<AmwayTerminalMacAddressModel>
        implements AmwayTerminalMacAddressDao
{
    private static final Logger LOG = Logger.getLogger(DefaultAmwayPOSMacAddressDao.class);

    private ModelService modelService;

    public DefaultAmwayPOSMacAddressDao()
    {
        super(AmwayTerminalMacAddressModel._TYPECODE);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<AmwayTerminalMacAddressModel> getMacAddress(final String macAddress)
    {
        validateParameterNotNull(macAddress, "No mac address is specified");
        final Map<String, Object> params = new HashMap<>(1);
        params.put(AmwayTerminalMacAddressModel.MACADDRESS, macAddress);
        return find(params);
    }

    public ModelService getModelService()
    {
        return modelService;
    }

    public void setModelService(final ModelService modelService)
    {
        this.modelService = modelService;
    }

}
