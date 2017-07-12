package com.amway.amwayinventory.validator.stock.impl;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.BaseStoreModel;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.amway.amwayinventory.data.AmwayInventoryBean;

/**
 * Validator for checking inventory beans on valid product code
 */
public class AmwayInventoryProductCodeValidator implements Validator
{
	private static final Logger LOG = Logger.getLogger(AmwayInventoryProductCodeValidator.class);

	@Autowired
	private ProductService productService;
	@Autowired
	private WarehouseService warehouseService;
	@Autowired
	private CatalogVersionService catalogVersionService;
	@Autowired
	private SessionService sessionService;
	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> aClass)
	{
		return true;
	}

	@Override
	public void validate(Object inventoryBean, Errors errors)
	{
		AmwayInventoryBean amwayInventoryBean = (AmwayInventoryBean) inventoryBean;

		WarehouseModel warehouse = warehouseService.getWarehouseForCode(amwayInventoryBean.getWarehouseCode());
		String productCode = amwayInventoryBean.getBaseItemNumber();
		//@formatter:off
		List<ProductModel> products = warehouse.getBaseStores().stream()
				.flatMap(this::toCatalogStream)
				.flatMap(this::toCatalogVersionStream)
				.map(catalogVersion -> getProduct(productCode, catalogVersion))
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
		//@formatter:on
		if (CollectionUtils.isEmpty(products))
		{
			errors.reject("amway.inventory.bean.baseitemnumber.nonexists", new Object[] { productCode }, null);
		}
	}

	private ProductModel getProduct(String productCode, CatalogVersionModel catalogVersion)
	{

		return executeAsAdmin(() ->
		{
			try
			{
				return productService.getProductForCode(catalogVersion, productCode);
			}
			catch (UnknownIdentifierException | IllegalArgumentException | AmbiguousIdentifierException e)
			{
				LOG.debug(e,e);
				return null;
			}
		});

	}

	private <T> T executeAsAdmin(Supplier<T> supplier)
	{
		return sessionService.executeInLocalView(new SessionExecutionBody()
		{
			@Override
			public Object execute()
			{
				return supplier.get();
			}
		}, userService.getAdminUser());
	}

	private Stream<CatalogModel> toCatalogStream(BaseStoreModel baseStore)
	{
		return baseStore.getCatalogs().stream();
	}

	private Stream<CatalogVersionModel> toCatalogVersionStream(CatalogModel catalog)
	{
		return catalog.getCatalogVersions().stream();
	}
}
