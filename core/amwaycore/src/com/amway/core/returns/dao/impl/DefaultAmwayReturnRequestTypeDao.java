package com.amway.core.returns.dao.impl;

import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;

import com.amway.core.model.returns.AmwayReturnRequestTypeModel;

public class DefaultAmwayReturnRequestTypeDao extends DefaultGenericDao<AmwayReturnRequestTypeModel>
{
	public DefaultAmwayReturnRequestTypeDao()
	{
		super(AmwayReturnRequestTypeModel._TYPECODE);
	}
}
