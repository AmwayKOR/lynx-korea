/**
 *
 */
package com.amway.core.order.provider.impl;

import de.hybris.platform.returns.model.ReturnEntryModel;
import de.hybris.platform.returns.model.ReturnRequestModel;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;

import java.util.Date;



/**
 * To retrieve the maximum current entry reached date for the return request
 */
public class ReturnsConsolidateDateAttributeProvider implements DynamicAttributeHandler<Date, ReturnRequestModel>
{

	/**
	 * To retrieve the maximum current entry reached date for the return request
	 *
	 * @param returnRequest
	 */
	@Override
	public Date get(final ReturnRequestModel returnRequest)
	{
		Date currentEntryReachedDate;
		Date maxCurrentEntryReachedDate = returnRequest.getDate();
		for (final ReturnEntryModel returnEntry : returnRequest.getReturnEntries())
		{
			currentEntryReachedDate = returnEntry.getReachedDate();
			if (currentEntryReachedDate != null && currentEntryReachedDate.after(maxCurrentEntryReachedDate))
			{
				maxCurrentEntryReachedDate = currentEntryReachedDate;
			}
		}
		return maxCurrentEntryReachedDate;
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	@Override
	public void set(final ReturnRequestModel arg0, final Date arg1)
	{
		throw new UnsupportedOperationException("The attribute is readonly");

	}




}
