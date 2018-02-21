package com.amway.core.customjalo.price;

import de.hybris.platform.catalog.jalo.CatalogAwareEurope1PriceFactory;
import de.hybris.platform.europe1.channel.strategies.RetrieveChannelStrategy;
import de.hybris.platform.europe1.enums.PriceRowChannel;
import de.hybris.platform.europe1.jalo.PriceRow;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.Currency;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.jalo.order.price.JaloPriceFactoryException;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.product.Unit;
import de.hybris.platform.jalo.user.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;


/**
 * Implementation for matching price rows.
 */
public class AmwayEurope1PriceFactory extends CatalogAwareEurope1PriceFactory
{
	private final static Logger LOG = Logger.getLogger(AmwayEurope1PriceFactory.class);
	private RetrieveChannelStrategy amwayRetrieveChannelStrategy;

	/**
	 * @return amwayRetrieveChannelStrategy
	 */
	public RetrieveChannelStrategy getAmwayRetrieveChannelStrategy()
	{
		return amwayRetrieveChannelStrategy;
	}

	/**
	 * To match the price row info with product,productgroup,user,usergroup,currency and date.
	 *
	 * @param ctx
	 * @param product
	 * @param productGroup
	 * @param user
	 * @param userGroup
	 * @param currency
	 * @param date
	 * @param net
	 * @return PriceRow
	 * @throws JaloPriceFactoryException
	 */
	@Override
	public List<PriceRow> matchPriceRowsForInfo(final SessionContext ctx, final Product product,
			final EnumerationValue productGroup, final User user, final EnumerationValue userGroup, final Currency currency,
			final Date date, final boolean net) throws JaloPriceFactoryException
	{
		if ((product == null) && (productGroup == null))
		{
			throw new JaloPriceFactoryException(
					"cannot match price info without product and product group - at least one must be present", 0);
		}
		if ((user == null) && (userGroup == null))
		{
			throw new JaloPriceFactoryException("cannot match price info without user and user group - at least one must be present",
					0);
		}
		if (currency == null)
		{
			throw new JaloPriceFactoryException("cannot match price info without currency", 0);
		}
		if (date == null)
		{
			throw new JaloPriceFactoryException("cannot match price info without date", 0);
		}
		final Collection rows = queryPriceRows4Price(ctx, product, productGroup, user, userGroup);
		if (!(rows.isEmpty()))
		{
			final PriceRowChannel channel = getAmwayRetrieveChannelStrategy().getChannel(ctx);
			final List ret = new ArrayList(rows);
			if (ret.size() > 1)
			{
				Collections.sort(ret, new AmwayPriceRowInfoComparator(currency, net));
			}
			return filterPriceRows4Info(ret, currency, date, channel);

		}

		return Collections.EMPTY_LIST;
	}

	/**
	 * To match the price row
	 *
	 * @param ctx
	 * @param product
	 * @param productGroup
	 * @param user
	 * @param userGroup
	 * @param qtd
	 * @param unit
	 * @param currency
	 * @param date
	 * @param net
	 * @param giveAwayMode
	 * @return PriceRow
	 * @throws JaloPriceFactoryException
	 */
	@Override
	public PriceRow matchPriceRowForPrice(final SessionContext ctx, final Product product, final EnumerationValue productGroup,
			final User user, final EnumerationValue userGroup, final long qtd, final Unit unit, final Currency currency,
			final Date date, final boolean net, final boolean giveAwayMode) throws JaloPriceFactoryException
	{
		if ((product == null) && (productGroup == null))
		{
			throw new JaloPriceFactoryException(
					"cannot match price without product and product group - at least one must be present", 0);
		}
		if ((user == null) && (userGroup == null))
		{
			throw new JaloPriceFactoryException("cannot match price without user and user group - at least one must be present", 0);
		}
		if (currency == null)
		{
			throw new JaloPriceFactoryException("cannot match price without currency", 0);
		}
		if (date == null)
		{
			throw new JaloPriceFactoryException("cannot match price without date", 0);
		}
		if (unit == null)
		{
			throw new JaloPriceFactoryException("cannot match price without unit", 0);
		}

		final Collection rows = queryPriceRows4Price(ctx, product, productGroup, user, userGroup);
		if (!(rows.isEmpty()))
		{
			final PriceRowChannel channel = getAmwayRetrieveChannelStrategy().getChannel(ctx);
			final List list = filterPriceRows4Price(rows, qtd, unit, currency, new Date(), giveAwayMode, channel);

			if (list.isEmpty())
			{
				return null;
			}
			if (list.size() == 1)
			{
				return (PriceRow) list.get(0);

			}

			Collections.sort(list, new AmwayPriceRowMatchComparator(currency, net, unit));
			return (PriceRow) list.get(0);
		}

		return null;
	}

	protected class AmwayPriceRowInfoComparator implements Comparator<PriceRow>
	{
		private final Currency curr;
		private final boolean net;

		protected AmwayPriceRowInfoComparator(final Currency paramCurrency, final boolean paramBoolean)
		{
			this.curr = paramCurrency;
			this.net = paramBoolean;
		}

		/**
		 * Compares the pricerow 1 and pricerow 2
		 *
		 * @param row1
		 * @param row2
		 */
		@Override
		public int compare(final PriceRow row1, final PriceRow row2)
		{
			final long u1Match = row1.getUnit().getPK().getLongValue();
			final long u2Match = row2.getUnit().getPK().getLongValue();
			if (u1Match != u2Match)
			{
				return (u1Match < u2Match) ? -1 : 1;
			}

			final long min1 = row1.getMinqtdAsPrimitive();
			final long min2 = row2.getMinqtdAsPrimitive();
			if (min1 != min2)
			{
				return (min1 > min2) ? -1 : 1;
			}

			final int matchValue1 = row1.getMatchValueAsPrimitive();
			final int matchValue2 = row2.getMatchValueAsPrimitive();
			if (matchValue1 != matchValue2)
			{
				return matchValue2 - matchValue1;
			}

			final boolean c1Match = this.curr.equals(row1.getCurrency());
			final boolean c2Match = this.curr.equals(row2.getCurrency());
			if (c1Match != c2Match)
			{
				return c1Match ? -1 : 1;
			}

			final boolean n1Match = this.net == row1.isNetAsPrimitive();
			final boolean n2Match = this.net == row2.isNetAsPrimitive();
			if (n1Match != n2Match)
			{
				return n1Match ? -1 : 1;
			}

			final boolean row1Range = row1.getStartTime() != null;
			final boolean row2Range = row2.getStartTime() != null;

			if (row1Range != row2Range)
			{
				return row1Range ? -1 : 1;
			}
			else if (row1Range && row2Range)
			{
				//return closest pricerow for today
				return row2.getStartTime().compareTo(row1.getStartTime());
			}

			return row1.getPK().compareTo(row2.getPK());
		}
	}

	/**
	 * get the price rows for price.
	 *
	 * @param ctx
	 * @param product
	 * @param productGroup
	 * @param user
	 * @param userGroup
	 * @return PriceRow
	 */
	@Override
	public Collection<PriceRow> queryPriceRows4Price(final SessionContext ctx, final Product product,
			final EnumerationValue productGroup, final User user, final EnumerationValue userGroup)
	{
		return super.queryPriceRows4Price(ctx, product, productGroup, user, userGroup);
	}

	protected class AmwayPriceRowMatchComparator implements Comparator<PriceRow>
	{
		private final Currency curr;
		private final boolean net;
		private final Unit unit;

		protected AmwayPriceRowMatchComparator(final Currency paramCurrency, final boolean paramBoolean, final Unit paramUnit)
		{
			this.curr = paramCurrency;
			this.net = paramBoolean;
			this.unit = paramUnit;
		}

		/**
		 * Compares pricerow1 and pricerow2
		 *
		 * @param row1
		 * @param row2
		 */
		@Override
		public int compare(final PriceRow row1, final PriceRow row2)
		{
			final int matchValue1 = row1.getMatchValueAsPrimitive();
			final int matchValue2 = row2.getMatchValueAsPrimitive();
			if (matchValue1 != matchValue2)
			{
				return matchValue2 - matchValue1;
			}

			final boolean c1Match = this.curr.equals(row1.getCurrency());
			final boolean c2Match = this.curr.equals(row2.getCurrency());
			if (c1Match != c2Match)
			{
				return c1Match ? -1 : 1;
			}

			final boolean n1Match = this.net == row1.isNetAsPrimitive();
			final boolean n2Match = this.net == row2.isNetAsPrimitive();
			if (n1Match != n2Match)
			{
				return n1Match ? -1 : 1;
			}

			final boolean u1Match = this.unit.equals(row1.getUnit());
			final boolean u2Match = this.unit.equals(row2.getUnit());
			if (u1Match != u2Match)
			{
				return u1Match ? -1 : 1;
			}

			final long min1 = row1.getMinqtdAsPrimitive();
			final long min2 = row2.getMinqtdAsPrimitive();
			if (min1 != min2)
			{
				return (min1 > min2) ? -1 : 1;
			}

			final boolean row1Range = row1.getStartTime() != null;
			final boolean row2Range = row2.getStartTime() != null;

			if (row1Range != row2Range)
			{
				return row1Range ? -1 : 1;
			}
			else if (row1Range && row2Range)
			{
				//return closest pricerow for today
				return row2.getStartTime().compareTo(row1.getStartTime());
			}

			LOG.warn("found ambigous price rows " + row1 + " and " + row2 + " - using PK to distinguish");

			return row1.getPK().compareTo(row2.getPK());
		}
	}

	/**
	 * @param retrieveChannelStrategy the retrieveChannelStrategy to set
	 */
	@Override
	@Required
	public void setRetrieveChannelStrategy(final RetrieveChannelStrategy retrieveChannelStrategy)
	{
		super.setRetrieveChannelStrategy(retrieveChannelStrategy);
		this.amwayRetrieveChannelStrategy = retrieveChannelStrategy;
	}
}
