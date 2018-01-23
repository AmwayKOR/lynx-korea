package com.amway.apac.core.base.daos.impl;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.amway.apac.core.base.daos.BaseDao;


/**
 * Default implementation for BaseDao.
 *
 * @param <T>
 *           the generic type
 */
public class DefaultBaseDaoImpl<T extends ItemModel> extends DefaultGenericDao implements BaseDao
{

	/**
	 * attribute for code property name
	 */
	protected String codePropertyName;

	/**
	 * Instantiates a new default base dao impl.
	 *
	 * @param typecode
	 *           the typecode
	 * @param codePropertyName
	 *           the code property name
	 */
	public DefaultBaseDaoImpl(final String typecode, final String codePropertyName)
	{
		super(typecode);
		this.codePropertyName = codePropertyName;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see in.com.futuregroup.core.dao.BaseDao#findUniqueModelByCode(java.lang.String)
	 */
	@Override
	public T findUniqueModelByCode(final String code)
	{
		final List<T> items = this.findByCode(code);
		T item = null;
		if (CollectionUtils.isNotEmpty(items))
		{
			if (items.size() == 1)
			{
				item = items.get(0);
			}
			else
			{
				throw new AmbiguousIdentifierException("No unique element found. Ambiguous identifier passed : " + code);
			}
		}
		return item;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see in.com.futuregroup.core.dao.BaseDao#findByCode(java.lang.String)
	 */
	@Override
	public List<T> findByCode(final String code)
	{
		final Map<String, Object> params = new HashMap<>();
		params.put(getCodePropertyName(), code);
		return this.find(params);
	}

	/**
	 * @return the codePropertyName
	 */
	public String getCodePropertyName()
	{
		return codePropertyName;
	}

}
