package com.amway.amwaydms.populators;

import com.amway.amwaydms.model.BusinessNatureChange;
import com.amway.core.annotations.AmwayBean;
import com.amway.dms.data.UpdateBusinessNatureInputRequestData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;


import com.amway.amwaydms.model.BusinessNatureChangeRequest;

/**
 * Created by aiueq92 on 10/10/17.
 */
@AmwayBean(ext="amwaydms2",docs="https://jira.amway.com:8444/display/HC/amwaydms2," +
        "https://jira.amway.com:8444/display/HC/DMS+Populators+and+Converters")
public class BusinessNatureInputPopulator implements Populator<UpdateBusinessNatureInputRequestData, BusinessNatureChangeRequest>
{

    @Override
    public void populate(final UpdateBusinessNatureInputRequestData source, final BusinessNatureChangeRequest target)
            throws ConversionException
    {
        target.setBusinessNatureChange(new BusinessNatureChange());
        target.getBusinessNatureChange().setAccountSubTypeCd(source.getAccountSubTypeCd());
        target.getBusinessNatureChange().setInvoiceNum(source.getOrderNum());
        target.getBusinessNatureChange().setReasonCd(source.getReasonCd());

    }
}