package com.amway.apac.core.customer.daos;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.store.BaseStoreModel;

import java.time.LocalDate;

import com.amway.core.customer.dao.AmwayCustomerAccountDao;
import com.amway.core.model.AmwayAccountModel;
import com.amway.core.model.AmwayBusinessRestrictionModel;


/**
 * Extending {@link AmwayCustomerAccountDao} to add more order functionalities
 *
 * @author Aaron Yong
 */
public interface AmwayApacCustomerAccountDao extends AmwayCustomerAccountDao
{
	/**
	 * Finds the number of orders placed for the user and the base store given.
	 *
	 * @param user
	 *           user for which number of orders is to be found
	 * @param baseStore
	 *           base store for which the orders are to be checked
	 * @return number of orders found.
	 * @throws IllegalArgumentException
	 *            if either of the parameters user, baseStore is null
	 */
	Integer findOrderCountsForUser(final UserModel user, final BaseStoreModel baseStore);

	/**
	 * Returns orders list for given customer, basestore, date limits and order type.
	 *
	 * @param customerModel
	 *           Customer for whom, orders need to be searched.
	 * @param store
	 *           Base Store
	 * @param datefrom
	 *           From Date
	 * @param dateto
	 *           To Date
	 * @param type
	 *           Order Type
	 * @param pageableData
	 * @return OrderModels as per given restrictions.
	 * @throws IllegalArgumentException
	 *            if customerModel or store is null.
	 */
	SearchPageData<OrderModel> findOrdersByCustomerAndStoreAndFilterByDateAndType(final CustomerModel customerModel,
			final BaseStoreModel store, final LocalDate datefrom, final LocalDate dateto, final String type,
			final PageableData pageableData);

	/**
	 * Gets the MOP restriction.
	 *
	 * @param amwayAccount
	 *           the amway account
	 * @return the MOP restriction
	 */
	AmwayBusinessRestrictionModel getMOPRestriction(AmwayAccountModel amwayAccount);
}
