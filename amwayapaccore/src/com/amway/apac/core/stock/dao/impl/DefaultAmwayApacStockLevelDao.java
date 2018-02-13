package com.amway.apac.core.stock.dao.impl;

import de.hybris.platform.core.model.type.ComposedTypeModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.servicelayer.exceptions.SystemException;
import de.hybris.platform.servicelayer.type.TypeService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.amway.apac.core.stock.dao.AmwayApacStockLevelDao;
import com.amway.core.stock.dao.impl.DefaultAmwayStockLevelDao;


/**
 * This Class is used to provide implementation for AmwayApac level stock interface methods or override any methods of
 * AmwayCore or OOTB stock DAO methods.
 */
public class DefaultAmwayApacStockLevelDao extends DefaultAmwayStockLevelDao implements AmwayApacStockLevelDao
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayApacStockLevelDao.class);
	private StockLevelColumns stockLevelColumns = null;
	private TransactionTemplate transactionTemplate;
	private TypeService typeService;
	private JdbcTemplate jdbcTemplate;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateAvailableAmount(final StockLevelModel stockLevel, final int amount)
	{
		getTransactionTemplate().execute(new TransactionCallbackWithoutResult()
		{
			@Override
			protected void doInTransactionWithoutResult(final TransactionStatus arg0)
			{
				try
				{
					LOG.info("Updating Available to : " + amount + " for stockLevel " + stockLevel.getPk());
					final int rows = runJdbcQuery(assembleAvailableStockLevelUpdateQuery(), amount, stockLevel);
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

	protected class StockLevelColumns
	{
		private final String tableName;
		private final String pkCol;
		private final String reservedCol;
		private final String availableCol;

		protected StockLevelColumns(final TypeService typeService)
		{
			final ComposedTypeModel stockLevelType = typeService.getComposedTypeForClass(StockLevelModel.class);
			this.tableName = stockLevelType.getTable();
			this.pkCol = typeService.getAttributeDescriptor(stockLevelType, "pk").getDatabaseColumn();
			this.reservedCol = typeService.getAttributeDescriptor(stockLevelType, "reserved").getDatabaseColumn();
			this.availableCol = typeService.getAttributeDescriptor(stockLevelType, "available").getDatabaseColumn();
		}

		/**
		 * @return the tableName
		 */
		protected String getTableName()
		{
			return tableName;
		}

		/**
		 * @return the pkCol
		 */
		protected String getPkCol()
		{
			return pkCol;
		}

		/**
		 * @return the reservedCol
		 */
		protected String getReservedCol()
		{
			return reservedCol;
		}

		/**
		 * @return the availableCol
		 */
		protected String getAvailableCol()
		{
			return availableCol;
		}
	}

	/**
	 * Returns the query for updating the available field only
	 *
	 * @return String
	 */
	protected String assembleAvailableStockLevelUpdateQuery()
	{
		prepareStockLevelColumns();
		return "UPDATE " + getStockLevelColumns().getTableName() + " SET " + getStockLevelColumns().getAvailableCol() + " = "
				+ getStockLevelColumns().getAvailableCol() + " - ?" + " WHERE " + getStockLevelColumns().getPkCol() + "=?";
	}

	protected void prepareStockLevelColumns()
	{
		if (getStockLevelColumns() != null)
		{
			return;
		}
		setStockLevelColumns(new StockLevelColumns(getTypeService()));
	}

	/**
	 * Method to execute the JDBC query
	 *
	 * @return int
	 */
	protected int runJdbcQuery(final String query, final int amount, final StockLevelModel stockLevel)
	{
		return getJdbcTemplate().update(query, Integer.valueOf(amount), Long.valueOf(stockLevel.getPk().getLongValue()));
	}


	/**
	 * @return the stockLevelColumns
	 */
	public StockLevelColumns getStockLevelColumns()
	{
		return stockLevelColumns;
	}

	/**
	 * @param stockLevelColumns
	 *           the stockLevelColumns to set
	 */
	public void setStockLevelColumns(final StockLevelColumns stockLevelColumns)
	{
		this.stockLevelColumns = stockLevelColumns;
	}

	/**
	 * @return the transactionTemplate
	 */
	public TransactionTemplate getTransactionTemplate()
	{
		return transactionTemplate;
	}

	/**
	 * @param transactionTemplate
	 *           the transactionTemplate to set
	 */
	@Required
	@Override
	public void setTransactionTemplate(final TransactionTemplate transactionTemplate)
	{
		super.setTransactionTemplate(transactionTemplate);
		this.transactionTemplate = transactionTemplate;
	}

	/**
	 * @return the typeService
	 */
	public TypeService getTypeService()
	{
		return typeService;
	}

	/**
	 * @param typeService
	 *           the typeService to set
	 */
	@Required
	@Override
	public void setTypeService(final TypeService typeService)
	{
		super.setTypeService(typeService);
		this.typeService = typeService;
	}

	/**
	 * @return the jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate()
	{
		return jdbcTemplate;
	}

	/**
	 * @param jdbcTemplate
	 *           the jdbcTemplate to set
	 */
	@Required
	@Override
	public void setJdbcTemplate(final JdbcTemplate jdbcTemplate)
	{
		super.setJdbcTemplate(jdbcTemplate);
		this.jdbcTemplate = jdbcTemplate;
	}


}


