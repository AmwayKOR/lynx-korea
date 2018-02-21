/**
 *
 */
package com.amway.apac.storefront.forms;

import java.util.List;


/**
 * @author avnishalaugh
 *
 */
public class AmwayApacMultiShoppingListUpdateForm
{
	private List<AmwayApacShoppingListForm> shoppingLists;
	private List<String> productCodes;

	/**
	 * @return the shoppingLists
	 */
	public List<AmwayApacShoppingListForm> getShoppingLists()
	{
		return shoppingLists;
	}

	/**
	 * @param shoppingLists
	 *           the shoppingLists to set
	 */
	public void setShoppingLists(final List<AmwayApacShoppingListForm> shoppingLists)
	{
		this.shoppingLists = shoppingLists;
	}

	/**
	 * @return the productCodes
	 */
	public List<String> getProductCodes()
	{
		return productCodes;
	}

	/**
	 * @param productCodes
	 *           the productCodes to set
	 */
	public void setProductCodes(final List<String> productCodes)
	{
		this.productCodes = productCodes;
	}


}