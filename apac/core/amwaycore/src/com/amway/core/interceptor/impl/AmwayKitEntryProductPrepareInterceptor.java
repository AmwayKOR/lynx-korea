package com.amway.core.interceptor.impl;

import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;

import com.amway.core.model.AmwayKitEntryProductModel;


/**
 * Prepare Interceptor for Amway Kit Entry Product
 */
public class AmwayKitEntryProductPrepareInterceptor implements PrepareInterceptor<AmwayKitEntryProductModel>
{

	/**
	 * @param arg0
	 * @param arg1
	 * @throws InterceptorException
	 */
	@Override
	public void onPrepare(final AmwayKitEntryProductModel arg0, final InterceptorContext arg1) throws InterceptorException
	{
		arg0.setCode(arg0.getKitProduct().getCode() + "-" + arg0.getEntry().getCode());
	}
}
