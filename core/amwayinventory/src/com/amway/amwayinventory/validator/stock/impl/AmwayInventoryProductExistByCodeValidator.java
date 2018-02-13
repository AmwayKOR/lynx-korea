package com.amway.amwayinventory.validator.stock.impl;

import static com.amway.amwayinventory.constants.AmwayInventoryConstants.ERROR_PRODUCT_NOT_FOUND;

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
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.amway.amwayinventory.data.AmwayInventoryBean;


/**
 * Validator for checking inventory beans on valid product code
 */
public class AmwayInventoryProductExistByCodeValidator implements Validator
{
	private static final Logger LOG = Logger.getLogger(AmwayInventoryProductExistByCodeValidator.class);

	@Autowired
	private ProductService productService;
	@Autowired
	private WarehouseService warehouseService;
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
		WarehouseModel warehouse = getWarehouse(amwayInventoryBean.getWarehouseCode());
		String productCode = amwayInventoryBean.getBaseItemNumber();
		if (warehouse != null && StringUtils.isNotBlank(productCode))
		{
			//@formatter:off
			List<ProductModel> products = warehouse.getBaseStores().stream()
					.flatMap(this::toCatalogStream)
					.flatMap(this::toCatalogVersionStream)
					.map(catalogVersion -> getProductAsAdmin(productCode, catalogVersion))
					.filter(Objects::nonNull)
					.collect(Collectors.toList());
			//@formatter:on
			if (CollectionUtils.isEmpty(products))
			{
				errors.reject(ERROR_PRODUCT_NOT_FOUND, new Object[] { productCode }, null);
			}
		}
	}

	private ProductModel getProductAsAdmin(String productCode, CatalogVersionModel catalogVersion)
	{
		return executeAsAdmin(() -> getProduct(productCode, catalogVersion));
	}

	private WarehouseModel getWarehouse(String warehouseCode)
	{
		try
		{
			return warehouseService.getWarehouseForCode(warehouseCode);
		}
		catch (UnknownIdentifierException | IllegalArgumentException | AmbiguousIdentifierException e)
		{
			LOG.debug(e.getMessage(), e);
			return null;
		}
	}

	private ProductModel getProduct(String productCode, CatalogVersionModel catalogVersion)
	{
		try
		{
			return productService.getProductForCode(catalogVersion, productCode);
		}
		catch (UnknownIdentifierException | IllegalArgumentException | AmbiguousIdentifierException e)
		{
			LOG.debug(e.getMessage(), e);
			return null;
		}
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
