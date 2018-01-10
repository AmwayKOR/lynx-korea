package com.amway.amwaydms.populators;

import com.amway.core.annotations.AmwayBean;
import com.amway.core.data.CommonResponseFieldsData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import com.amway.amwaydms.model.CommonResponse;


/**
 * Populator to populate common response fields for any Magic service
 */
@AmwayBean(ext="amwaydms2",docs="https://jira.amway.com:8444/display/HC/amwaydms2," +
        "https://jira.amway.com:8444/display/HC/DMS+Populators+and+Converters")
public class CommonResponsePopulator implements Populator<CommonResponse, CommonResponseFieldsData>
{

    @Override
    public void populate(final CommonResponse source, final CommonResponseFieldsData target) throws ConversionException
    {
        target.setReturnCd(source.getErrorMessage().getCode());
        target.setReturnMessage(source.getErrorMessage().getMessage());
    }

}

