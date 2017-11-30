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

            final List<EntryGroupData> recentlyChangedEntryGroups = new ArrayList<>(data.getRootGroups());

            if (AmwayapacFacadesConstants.SORT_BY_LAST_ITEM_ADDED.equals(sortBy)) {
                final List<OrderEntryData> recentlyAddedListEntries = new ArrayList<>(data.getEntries());
                Collections.reverse(recentlyAddedListEntries);
                data.setEntries(Collections.unmodifiableList(recentlyAddedListEntries));
            } else if (AmwayapacFacadesConstants.SORT_BY_PRODUCT_NAME.equals(sortBy)) {
                final List<OrderEntryData> recentlyAddedListEntries = new ArrayList<>(data.getEntries());
                recentlyAddedListEntries.sort((o1, o2) -> o1.getProduct().getName().compareTo(o2.getProduct().getName()));
                data.setEntries(Collections.unmodifiableList(recentlyAddedListEntries));
            } else if (AmwayapacFacadesConstants.SORT_BY_CATEGORY_NAME.equals(sortBy)) {
                final List<OrderEntryData> recentlyAddedListEntries = new ArrayList<>(data.getEntries());
                recentlyAddedListEntries.sort((o1, o2) -> o1.getProduct().getCategories().stream().findFirst().get().getName().compareTo(o2.getProduct().getCategories().stream().findFirst().get().getName()));
                data.setEntries(Collections.unmodifiableList(recentlyAddedListEntries));
            }
            return data;
        }
        return createEmptyCart();
    }
}
