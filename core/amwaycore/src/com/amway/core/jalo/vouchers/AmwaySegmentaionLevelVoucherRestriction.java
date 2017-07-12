package com.amway.core.jalo.vouchers;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.security.JaloSecurityException;
import de.hybris.platform.jalo.type.ComposedType;

import org.apache.log4j.Logger;

import com.amway.core.jalo.AmwayAccount;


/**
 * Restriction for Amway segmentation level voucher
 */
public class AmwaySegmentaionLevelVoucherRestriction extends GeneratedAmwaySegmentaionLevelVoucherRestriction
{
	@SuppressWarnings("unused")
	private final static Logger LOG = Logger.getLogger(AmwaySegmentaionLevelVoucherRestriction.class.getName());

	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes)
			throws JaloBusinessException
	{
		// business code placed here will be executed before the item is created
		// then create the item
		final Item item = super.createItem(ctx, type, allAttributes);
		// business code placed here will be executed after the item was created
		// and return the item
		return item;
	}


	@Override
	protected boolean isFulfilledInternal(final AbstractOrder paramAbstractOrder)
	{
		final EnumerationValue restSegmentationLevel = getSegmentationLevel();

		try
		{
			final AmwayAccount account = (AmwayAccount) paramAbstractOrder.getAttribute("account");

			if (account != null && null != account.getLevel().getAttribute("segmentaionlevel"))
			{
				final EnumerationValue segmentationLevel = (EnumerationValue) account.getLevel().getAttribute("segmentaionlevel");

				if (null != segmentationLevel && segmentationLevel.equals(restSegmentationLevel))
				{
					return isPositiveAsPrimitive() == true;
				}
			}
		}
		catch (JaloInvalidParameterException | JaloSecurityException e)
		{
			LOG.error(e.getMessage(), e);
		}

		return false;

	}

	@Override
	protected boolean isFulfilledInternal(final Product paramProduct)
	{
		// YTODO Auto-generated method stub
		return false;
	}

}
