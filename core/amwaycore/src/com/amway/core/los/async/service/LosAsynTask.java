/**
 *
 */
package com.amway.core.los.async.service;

import de.hybris.platform.core.Registry;
import de.hybris.platform.jalo.JaloConnection;
import de.hybris.platform.jalo.security.JaloSecurityException;
import de.hybris.platform.site.BaseSiteService;

import java.util.concurrent.Callable;

import com.amway.core.los.service.LosService;


/**
 * @author ashishalishetty
 */
public class LosAsynTask<X, Y> implements Callable<Y>
{

	private BaseSiteService baseSiteService;
	private LosService<X, Y> losService;

	private String baseStoreUid;
	private X request;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public Y call() throws JaloSecurityException
	{
		Registry.activateMasterTenant();
		JaloConnection.getInstance().createAnonymousCustomerSession().activate();
		getBaseSiteService().setCurrentBaseSite(baseStoreUid, true);
		return losService.process(request);
	}

	/**
	 * @return the losService
	 */
	public LosService<X, Y> getLosService()
	{
		return losService;
	}

	/**
	 * @param losService the losService to set
	 */
	public void setLosService(final LosService<X, Y> losService)
	{
		this.losService = losService;
	}

	/**
	 * @return the baseSiteService
	 */
	public BaseSiteService getBaseSiteService()
	{
		return baseSiteService;
	}

	/**
	 * @param baseSiteService the baseSiteService to set
	 */
	public void setBaseSiteService(final BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}

	/**
	 * @param baseStoreUid the baseStoreUid to set
	 */
	public void setBaseStoreUid(final String baseStoreUid)
	{
		this.baseStoreUid = baseStoreUid;
	}

	/**
	 * @param request the request to set
	 */
	public void setRequest(final X request)
	{
		this.request = request;
	}
}
