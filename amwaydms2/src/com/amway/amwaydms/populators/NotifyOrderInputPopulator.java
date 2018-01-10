package com.amway.amwaydms.populators;

import com.amway.amwaydms.model.OrderRequest;
import com.amway.amwaydms.model.OrderRequest.ActionTypeEnum;
import com.amway.amwaydms.model.OrderRequest.OrderTypeEnum;
import com.amway.core.annotations.AmwayBean;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import com.amway.core.dms.data.OrderRequestData;

/**
 * Created by aiueq92 on 10/10/17.
 */
@AmwayBean(ext="amwaydms2",docs="https://jira.amway.com:8444/display/HC/amwaydms2," +
        "https://jira.amway.com:8444/display/HC/DMS+Populators+and+Converters")
public class NotifyOrderInputPopulator implements Populator<OrderRequestData, OrderRequest>
{

    @Override
    public void populate(final OrderRequestData source, final OrderRequest target)
            throws ConversionException
    {
        if (ActionTypeEnum.ORDERCREATED.toString().equals(source.getActionType())){target.setActionType(ActionTypeEnum.ORDERCREATED);}
        if (ActionTypeEnum.ORDERCANCELLED.toString().equals(source.getActionType())){target.setActionType(ActionTypeEnum.ORDERCANCELLED);}
        if (ActionTypeEnum.ORDERCREATED.toString().equals(source.getActionType())){target.setActionType(ActionTypeEnum.ORDERCREATED);}

        if (OrderTypeEnum.FIRSTORDER.toString().equals(source.getOrderType())){target.setOrderType(OrderTypeEnum.FIRSTORDER);}
        if (OrderTypeEnum.RENEWAL.toString().equals(source.getOrderType())){target.setOrderType(OrderTypeEnum.RENEWAL);}

        target.setInvoiceNumber(source.getInvoiceNumber());
        target.setProcessDate(source.getProcessDate());

    }
}
