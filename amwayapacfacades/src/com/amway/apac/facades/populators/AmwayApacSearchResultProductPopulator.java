package com.amway.apac.facades.populators;

import static com.amway.apac.core.constants.AmwayapacCoreConstants.MULTIDIMENSIONAL;
import static com.amway.apac.core.constants.AmwayapacCoreConstants.PRODUCT_LIST_GRID_FORMAT;
import static com.amway.apac.core.constants.AmwayapacCoreConstants.RECENTLY_VIEWED_FORMAT;
import static com.amway.apac.core.constants.AmwayapacCoreConstants.SEARCH_AUTO_COMPLETE_FORMAT;

import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;
import de.hybris.platform.commerceservices.stock.strategies.WarehouseSelectionStrategy;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.amway.facades.populators.AmwaySearchResultProductPopulator;


/**
 * Overridding {@link AmwaySearchResultProductPopulator} to populate apac specific data
 *
 * @author Parvesh Goyal
 *
 */
public class AmwayApacSearchResultProductPopulator extends AmwaySearchResultProductPopulator
{

	private static final Logger LOG = LoggerFactory.getLogger(AmwayApacSearchResultProductPopulator.class);

	private CartService cartService;


	/**
	 * Overriding to populate required attributes from SolR.
	 */
	@Override
	public void populate(final SearchResultValueData source, final ProductData target)
	{
		super.populate(source, target);
		target.setMultidimensional((Boolean) source.getValues().get(MULTIDIMENSIONAL));
	}

	/**
	 * Overridding OOTB implementation to populate the media specific to apac
	 */
	@Override
	protected List<ImageData> createImageData(final SearchResultValueData source)
	{
		final List<ImageData> result = new ArrayList<>();
		addImageData(source, PRODUCT_LIST_GRID_FORMAT, result);
		addImageData(source, SEARCH_AUTO_COMPLETE_FORMAT, result);
		addImageData(source, RECENTLY_VIEWED_FORMAT, result);
		return result;
	}

	/**
	 * Overriding the implementation to populate stock data as per the warehouse selected through
	 * {@link WarehouseSelectionStrategy}.
	 */
	@Override
	protected void populateStock(final SearchResultValueData source, final ProductData target)
	{

		StockLevelStatus stockLevelStatusEnum = StockLevelStatus.OUTOFSTOCK;
		if ((!getUserService().isAnonymousUser(getUserService().getCurrentUser())) && (getCartService().hasSessionCart()))
		{
			final WarehouseModel cartWarehouse = getCartService().getSessionCart().getWarehouse();
			if (null != cartWarehouse)
			{
				final String stockLevelStatus = this.<String> getValue(source, new StringBuilder(30).append("warehouse_")
						.append(cartWarehouse.getPk().toString()).append("_string").toString());
				stockLevelStatusEnum = StringUtils.isNotBlank(stockLevelStatus) ? StockLevelStatus.valueOf(stockLevelStatus)
						: stockLevelStatusEnum;
			}
		}

		if ((stockLevelStatusEnum.equals(StockLevelStatus.LOWSTOCK))
				|| (stockLevelStatusEnum.equals(StockLevelStatus.NOTSPECIFIED)))
		{
			populateStockDataFromModel(target);
		}
		else
		{
			target.setStock(getStockLevelStatusConverter().convert(stockLevelStatusEnum));
		}
	}


	/**
	 * @param productData
	 */
	protected void populateStockDataFromModel(final ProductData productData)
	{
		try
		{
			final ProductModel productModel = getProductService().getProductForCode(productData.getCode());
			if (productModel != null)
			{
				productData.setStock(getStockConverter().convert(productModel));
			}
		}
		catch (final UnknownIdentifierException ex)
		{
			LOG.info("Exception while getting product for code", ex);
			// If the product is no longer visible to the customergroup
			// then this exception can be thrown

			// We can't remove the product from the results, but we can
			// mark it as out of stock
			productData.setStock(getStockLevelStatusConverter().convert(StockLevelStatus.OUTOFSTOCK));
		}
	}

	/**
	 * @return the cartService
	 */
	public CartService getCartService()
	{
		return cartService;
	}

	/**
	 * @param cartService
	 *           the cartService to set
	 */
	@Required
	public void setCartService(final CartService cartService)
	{
		this.cartService = cartService;
	}

}
