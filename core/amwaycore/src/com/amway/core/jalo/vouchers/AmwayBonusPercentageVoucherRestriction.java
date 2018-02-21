package com.amway.core.jalo.vouchers;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.security.JaloSecurityException;
import de.hybris.platform.jalo.type.ComposedType;

import org.apache.log4j.Logger;

import com.amway.core.jalo.AmwayAccount;
import com.amway.core.jalo.BonusPercentageRange;


/**
 * Restriction for Bonus percentage voucher
 */
public class AmwayBonusPercentageVoucherRestriction extends GeneratedAmwayBonusPercentageVoucherRestriction
{
	@SuppressWarnings("unused")
	private final static Logger LOG = Logger.getLogger(AmwayBonusPercentageVoucherRestriction.class.getName());

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
	protected boolean isFulfilledInternal(final AbstractOrder order)
	{
		final BonusPercentageRange bonusPercentageRange = super.getBonusPercentageRange();

		try
		{
			final AmwayAccount account = (AmwayAccount) order.getAttribute("account");
			if (null != account && null != account.getLevel().getAttribute("bonuspercentage"))
			{
				final Double bonusPercentage = (Double) account.getLevel().getAttribute("bonuspercentage");
				if (null != bonusPercentageRange && null != bonusPercentage)
				{
					final String rangeFrom = bonusPercentageRange.getFrom();
					final String rangeTo = bonusPercentageRange.getTo();
					if (bonusPercentage.doubleValue() >= Double.parseDouble(rangeFrom) && bonusPercentage.doubleValue() <= Double
							.parseDouble(rangeTo))
					{
						return isPositiveAsPrimitive() == true;
					}
				}
			}
		}
		catch (JaloInvalidParameterException | NumberFormatException | JaloSecurityException e)
		{
			LOG.error(e.getMessage(), e);
		}
		return false;
	}


	@Override
	protected boolean isFulfilledInternal(final Product paramProduct)
	{
		return false;
	}

}
