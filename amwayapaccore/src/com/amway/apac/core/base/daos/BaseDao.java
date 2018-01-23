package com.amway.apac.core.base.daos;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.servicelayer.internal.dao.GenericDao;

import java.util.List;


/**
 * Base interface for all daos.
 *
 * @param <T>
 *           the generic type
 */
public interface BaseDao<T extends ItemModel> extends GenericDao
{

	/**
	 * Function returning unique element by code (business key)
	 *
	 * It will throw AmbiguousIdentifierException exception if there are multiple elements exist with this identifier
	 * Returns null if no element found
	 *
	 * @param code
	 * @return model item
	 */
	public T findUniqueModelByCode(String code);

	/**
	 * Function returning list of item by code
	 *
	 * @param code
	 * @return list of model items
	 */
	public List<T> findByCode(String code);

}
