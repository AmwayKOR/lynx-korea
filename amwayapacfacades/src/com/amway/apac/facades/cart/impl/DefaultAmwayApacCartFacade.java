package com.amway.apac.facades.cart.impl;

import com.amway.apac.facades.cart.AmwayApacCartFacade;
import com.amway.apac.facades.constants.AmwayapacFacadesConstants;
import com.amway.facades.cart.impl.DefaultAmwayCartFacade;
import de.hybris.platform.commercefacades.order.EntryGroupData;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Govind on 11/27/2017.
 */
public class DefaultAmwayApacCartFacade extends DefaultAmwayCartFacade implements AmwayApacCartFacade {

    /**
     * @param sortBy
     * @return CartData
     * This method accepts sort by and returns CartData in given order.
     */
    @Override
    public CartData getSessionCartWithSortByOrdering(String sortBy) {
        if (hasSessionCart()) {
            final CartData data = getSessionCart();
            final List<OrderEntryData> recentlyAddedListEntries = new ArrayList<>(data.getEntries());

            if (AmwayapacFacadesConstants.SORT_BY_LAST_ITEM_ADDED.equals(sortBy)) {
                Collections.reverse(recentlyAddedListEntries);
            } else if (AmwayapacFacadesConstants.SORT_BY_PRICE_ASCENDING.equals(sortBy)) {
                recentlyAddedListEntries.sort((o1, o2) -> Double.compare(o1.getBasePrice().getValue().doubleValue(), o2.getBasePrice().getValue().doubleValue()));
            } else if (AmwayapacFacadesConstants.SORT_BY_PRICE_DESCEDNING.equals(sortBy)) {
                recentlyAddedListEntries.sort((o1, o2) -> Double.compare(o2.getBasePrice().getValue().doubleValue(), o1.getBasePrice().getValue().doubleValue()));
            } else if (AmwayapacFacadesConstants.SORT_BY_NAME_ASCENDING.equals(sortBy)) {
                recentlyAddedListEntries.sort((o1, o2) -> o1.getProduct().getName().compareTo(o2.getProduct().getName()));
            } else if (AmwayapacFacadesConstants.SORT_BY_NAME_DESCEDNING.equals(sortBy)) {
                recentlyAddedListEntries.sort((o1, o2) -> o2.getProduct().getName().compareTo(o1.getProduct().getName()));
            }
            data.setEntries(Collections.unmodifiableList(recentlyAddedListEntries));
            return data;
        }
        return createEmptyCart();
    }
}
