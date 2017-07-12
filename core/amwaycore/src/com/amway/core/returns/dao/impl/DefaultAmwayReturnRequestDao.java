package com.amway.core.returns.dao.impl;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.returns.dao.impl.DefaultReturnRequestDao;
import de.hybris.platform.returns.model.ReplacementOrderModel;
import de.hybris.platform.returns.model.ReturnRequestModel;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.HashMap;
import java.util.Map;

import com.amway.core.returns.dao.AmwayReturnRequestDao;


/**
 * Default Implementation
 */
public class DefaultAmwayReturnRequestDao extends DefaultReturnRequestDao implements AmwayReturnRequestDao
{
	/**
	 * Method to return corresponding return request for a replacement order
	 *
	 * @param replacementOrderModel {@link #getReturnRequest(de.hybris.platform.returns.model.ReplacementOrderModel)}
	 */
	@Override
	public ReturnRequestModel getReturnRequest(final ReplacementOrderModel replacementOrderModel)
	{
		if (replacementOrderModel == null)
		{
			return null;
		}

		final Map values = new HashMap();
		values.put("value", replacementOrderModel);
		final String query =
				"SELECT {" + Item.PK + "} FROM {" + "ReturnRequest" + "} WHERE { " + "replacementOrder" + "} = ?value ORDER BY {"
						+ Item.PK + "} ASC";
		final SearchResult result = getFlexibleSearchService().search(query, values);
		if (result.getResult().isEmpty())
		{
			return null;
		}
		else
		{
			return (ReturnRequestModel) result.getResult().get(0);
		}
	}
}
