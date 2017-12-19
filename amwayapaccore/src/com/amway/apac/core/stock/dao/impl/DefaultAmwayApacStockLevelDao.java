/**
 *
 */
package com.amway.apac.core.stock.dao.impl;

import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.servicelayer.exceptions.SystemException;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import com.amway.apac.core.stock.dao.AmwayApacStockLevelDao;
import com.amway.core.stock.dao.impl.DefaultAmwayStockLevelDao;


/**
 * This Class is used to provide implementation for AmwayApac level stock interface methods or override any methods of
 * AmwayCore or OOTB stock DAO methods
 */
public class DefaultAmwayApacStockLevelDao extends DefaultAmwayStockLevelDao implements AmwayApacStockLevelDao
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayApacStockLevelDao.class);

	/**
	 * {@inheritDoc} This API updates the AVAILABLE field for stock level but does not change the RESERVED field value in
	 * the stock level
	 */
	@Override
	public void updateAvailableAmount(final StockLevelModel stockLevel, final int amount)
	{
		this.transactionTemplate.execute(new TransactionCallbackWithoutResult()
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

	/**
	 * @return query for updating the available field
	 */
	private String assembleAvailableStockLevelUpdateQuery()
	{
		prepareStockLevelColumns();
		return "UPDATE " + this.stockLevelColumns.getTableName() + " SET " + this.stockLevelColumns.getAvailableCol() + " = "
				+ this.stockLevelColumns.getAvailableCol() + " - ?" + " WHERE " + this.stockLevelColumns.getPkCol() + "=?";
	}

	private int runJdbcQuery(final String query, final int amount, final StockLevelModel stockLevel)
	{
		final Integer _amount = Integer.valueOf(amount);
		final Long _pk = Long.valueOf(stockLevel.getPk().getLongValue());

		return this.jdbcTemplate.update(query, _amount, _pk);
	}

}


