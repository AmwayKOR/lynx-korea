/**
 *
 */
package com.amway.core.stock.dao.impl;

import de.hybris.platform.core.model.type.ComposedTypeModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.servicelayer.exceptions.SystemException;
import de.hybris.platform.servicelayer.type.TypeService;
import de.hybris.platform.stock.impl.DefaultStockLevelDao;
import de.hybris.platform.stock.impl.StockLevelDao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.amway.core.stock.dao.AmwayStockLevelDao;


/**
 * 
 * Default Implementaion
 * 
 */
public class DefaultAmwayStockLevelDao extends DefaultStockLevelDao implements AmwayStockLevelDao
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayStockLevelDao.class);
	private StockLevelColumns stockLevelColumns = null;
	private TypeService typeService;
	private TransactionTemplate transactionTemplate;
	private JdbcTemplate jdbcTemplate;
	private StockLevelDao stockLevelDao;

	/**
	 * @param typeService
	 *           the typeService to set
	 */
	@Override
	@Required
	public void setTypeService(final TypeService typeService)
	{
		this.typeService = typeService;
	}

	/**
	 * @param transactionTemplate
	 *           the transactionTemplate to set
	 */
	@Override
	@Required
	public void setTransactionTemplate(final TransactionTemplate transactionTemplate)
	{
		this.transactionTemplate = transactionTemplate;
	}

	/**
	 * @param jdbcTemplate
	 *           the jdbcTemplate to set
	 */
	@Override
	@Required
	public void setJdbcTemplate(final JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 
	 * @return stockLevelDao
	 */
	public StockLevelDao getStockLevelDao()
	{
		return stockLevelDao;
	}

	/**
	 * 
	 * @param stockLevelDao
	 *           the stockLevelDao to set
	 */
	public void setStockLevelDao(final StockLevelDao stockLevelDao)
	{
		this.stockLevelDao = stockLevelDao;
	}

	private class StockLevelColumns
	{
		private final String tableName;
		private final String pkCol;
		private final String reservedCol;
		private final String availableCol;

		private StockLevelColumns(final TypeService typeService)
		{
			final ComposedTypeModel stockLevelType = typeService.getComposedTypeForClass(StockLevelModel.class);
			this.tableName = stockLevelType.getTable();
			this.pkCol = typeService.getAttributeDescriptor(stockLevelType, "pk").getDatabaseColumn();
			this.reservedCol = typeService.getAttributeDescriptor(stockLevelType, "reserved").getDatabaseColumn();
			this.availableCol = typeService.getAttributeDescriptor(stockLevelType, "available").getDatabaseColumn();
		}
	}

	private void prepareStockLevelColumns()
	{
		if (this.stockLevelColumns != null)
		{
			return;
		}
		this.stockLevelColumns = new StockLevelColumns(this.typeService);
	}

	private String assembleReserveAndAvailableStockLevelUpdateQuery()
	{
		prepareStockLevelColumns();
		final StringBuilder query = new StringBuilder("UPDATE " + this.stockLevelColumns.tableName);
		query.append(" SET " + this.stockLevelColumns.reservedCol + " = " + this.stockLevelColumns.reservedCol + " - ?");
		query.append(" , " + this.stockLevelColumns.availableCol + " = " + this.stockLevelColumns.availableCol + " - ? ");
		query.append(" WHERE " + this.stockLevelColumns.pkCol + "=?");

		return query.toString();
	}

	@Override
	public Integer reserve(final StockLevelModel stockLevel, final int amount)
	{
		return getStockLevelDao().reserve(stockLevel, amount);
	}

	@Override
	public Integer release(final StockLevelModel stockLevel, final int amount)
	{
		return getStockLevelDao().release(stockLevel, amount);
	}

	/**
	 * Method to commit stock for stock level. Used once order is passed to OMS. This method updates Available Amount &
	 * Reserved Amount.
	 * 
	 * {@link #commit(de.hybris.platform.ordersplitting.model.StockLevelModel, int)}
	 */
	@Override
	public void commit(final StockLevelModel stockLevel, final int amount)
	{
		this.transactionTemplate.execute(new TransactionCallbackWithoutResult()
		{
			@Override
			protected void doInTransactionWithoutResult(final TransactionStatus arg0)
			{
				try
				{
					LOG.info("Commiting " + amount + " on stockLevel " + stockLevel.getPk());
					final int rows = runJdbcQuery(assembleReserveAndAvailableStockLevelUpdateQuery(), amount, stockLevel);
					if (rows > 1)
					{
						throw new IllegalStateException("more stock level rows found for the update: [" + stockLevel.getPk() + "]");
					}
				}
				catch (final DataAccessException dae)
				{
					throw new SystemException(dae);
				}
			}
		});
	}

	private int runJdbcQuery(final String query, final int amount, final StockLevelModel stockLevel)
	{
		final Integer _amount = Integer.valueOf(amount);
		final Long _pk = Long.valueOf(stockLevel.getPk().getLongValue());

		final int rows = this.jdbcTemplate.update(query, new Object[]
		{ _amount, _amount, _pk });
		return rows;
	}
}