package com.amway.facades;

import com.amway.core.enums.ProductAttributesTypeEnum;
import com.amway.core.model.ProductAttributeModel;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.jalo.*;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.product.data.ReviewData;
import de.hybris.platform.commercefacades.product.impl.DefaultProductFacade;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.customerreview.model.CustomerReviewModel;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.c2l.Language;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.type.AttributeDescriptor;
import de.hybris.platform.jalo.type.TypeManager;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.*;


/**
 * Extension of {@link DefaultProductFacade}
 *
 * @author Aliaksei Sery
 */
public class AmwayProductFacade extends DefaultProductFacade
{
	public static final String ONLINE_VERSION = "Online";
	public static final String CODE_DESCRIPTOR = "code";
	public static final String ALIAS_DESCRIPTOR = "alias";
	public static final String MANUFACTURERAID_DESCRIPTOR = "manufacturerAID";
	public static final String UNIT_DESCRIPTOR = "unit";
	public static final String PRODUCT_REVIEWS_DESCRIPTOR = "productReviews";
	public static final String NUMBER_OF_REVIEWS = "numberOfReviews";
	public static final String AVERAGE_RATING = "averageRating";
	public static final String PRODUCT_REFERENCES_DESCRIPTOR = "productReferences";
	public static final String PRODUCT_PRICE_QUANTITY = "priceQuantity";
	public static final String PRODUCT_PRICES = "europe1Prices";
	public static final String PRODUCT_APPROVAL_STATUS = "approvalStatus";

	private Populator<ProductData, ProductModel> productReversePopulator;

	@Resource(name = "sessionService")
	SessionService sessionService;

	@Resource(name = "userService")
	UserService userService;

	@Resource(name = "taskService")
	TaskService taskService;

	@Resource(name = "modelService")
	ModelService modelService;

	@Resource(name = "flexibleSearchService")
	private FlexibleSearchService flexibleSearchService;

	@Autowired
	public CatalogVersionService catalogVersionService;

	/**
	 * Update product model
	 *
	 * @param productData - ProductData
	 */
	public void updateProduct(final ProductData productData)
	{
		final ProductModel product = getProductService().getProductForCode(productData.getCode());
		getProductReversePopulator().populate(productData, product);
		getModelService().save(product);
	}

	/**
	 * Update product model
	 *
	 * @param catalogVersionModel - catalog version
	 * @param productData         - ProductData
	 */
	public void updateProduct(final CatalogVersionModel catalogVersionModel, final ProductData productData)
	{
		sessionService.executeInLocalView(new SessionExecutionBody()
		{
			@Override
			public void executeWithoutResult()
			{
				userService.setCurrentUser(userService.getAdminUser());
				final ProductModel product = getProductService().getProductForCode(catalogVersionModel, productData.getCode());
				getProductReversePopulator().populate(productData, product);
				getModelService().save(product);
			}
		});
	}

	/**
	 * Update product model and and synchronize with online catalog
	 *
	 * @param catalogVersionModel catalog version
	 * @param productData         product data
	 */
	@SuppressWarnings("deprecation")
	public void updateAndPromoteProduct(final CatalogVersionModel catalogVersionModel, final ProductData productData)
	{
		updateProduct(catalogVersionModel, productData);
		syncRomanceCopyProductAttributes(catalogVersionModel, productData);
	}

	/**
	 * Synchronize product
	 *
	 * @param srcCatalogVersionModel source catalog version model
	 * @param productData            product data
	 */
	protected void syncProduct(final CatalogVersionModel srcCatalogVersionModel, final ProductData productData)
	{
		final CatalogVersionModel trgCatalogTargetVersion = catalogVersionService
				.getCatalogVersion(srcCatalogVersionModel.getCatalog().getId(), ONLINE_VERSION);
		final ProductModel productModel = getProductService().getProduct(productData.getCode());

		final CatalogVersion source = modelService.getSource(srcCatalogVersionModel);
		final CatalogVersion target = modelService.getSource(trgCatalogTargetVersion);

		final SyncItemJob syncJob = CatalogManager.getInstance().getSyncJob(source, target);
		final SyncItemCronJob synchronizeJob = syncJob.newExecution();
		final Product sourceProduct = modelService.getSource(productModel);
		final Item targetProduct = CatalogManager.getInstance().getCounterpartItem(sourceProduct, target);

		final List itemPKs = new ArrayList();
		itemPKs.add(new PK[] { sourceProduct.getPK(), targetProduct == null ? null : targetProduct.getPK() });

		synchronizeJob.addPendingItems(itemPKs, false);
		syncJob.perform(synchronizeJob, true);
	}

	protected void syncRomanceCopyProductAttributes(final CatalogVersionModel srcCatalogVersionModel,
			final ProductData productData)
	{
		final CatalogVersionModel trgCatalogTargetVersion = catalogVersionService
				.getCatalogVersion(srcCatalogVersionModel.getCatalog().getId(), ONLINE_VERSION);
		final ProductModel productModel = getProductService().getProduct(productData.getCode());

		final CatalogVersion source = modelService.getSource(srcCatalogVersionModel);
		final CatalogVersion target = modelService.getSource(trgCatalogTargetVersion);

		final SyncItemJob syncJob = CatalogManager.getInstance().getSyncJob(source, target);
		final SyncItemCronJob synchronizeJob = syncJob.newExecution();
		final Product sourceProduct = modelService.getSource(productModel);
		final Item targetProduct = CatalogManager.getInstance().getCounterpartItem(sourceProduct, target);

		AttributeDescriptor codeDescriptor = TypeManager.getInstance().getComposedType(Product.class)
				.getDeclaredAttributeDescriptor(CODE_DESCRIPTOR);

		AttributeDescriptor aliasDescriptor = TypeManager.getInstance().getComposedType(Product.class)
				.getDeclaredAttributeDescriptor(ALIAS_DESCRIPTOR);

		AttributeDescriptor manufacturerAIDDescriptor = TypeManager.getInstance().getComposedType(Product.class)
				.getDeclaredAttributeDescriptor(MANUFACTURERAID_DESCRIPTOR);

		AttributeDescriptor unitDescriptor = TypeManager.getInstance().getComposedType(Product.class)
				.getDeclaredAttributeDescriptor(UNIT_DESCRIPTOR);

		AttributeDescriptor productReviewsDescriptor = TypeManager.getInstance().getComposedType(Product.class)
				.getDeclaredAttributeDescriptor(PRODUCT_REVIEWS_DESCRIPTOR);

		AttributeDescriptor productReferencesDescriptor = TypeManager.getInstance().getComposedType(Product.class)
				.getDeclaredAttributeDescriptor(PRODUCT_REFERENCES_DESCRIPTOR);

		AttributeDescriptor productPriceQuantityDescriptor = TypeManager.getInstance().getComposedType(Product.class)
				.getDeclaredAttributeDescriptor(PRODUCT_PRICE_QUANTITY);

		AttributeDescriptor productPricesDescriptor = TypeManager.getInstance().getComposedType(Product.class)
				.getDeclaredAttributeDescriptor(PRODUCT_PRICES);

		AttributeDescriptor productApprovalStatusDescriptor = TypeManager.getInstance().getComposedType(Product.class)
				.getDeclaredAttributeDescriptor(PRODUCT_APPROVAL_STATUS);

		AttributeDescriptor numberOfReviewsDescriptor = TypeManager.getInstance().getComposedType(Product.class)
				.getDeclaredAttributeDescriptor(NUMBER_OF_REVIEWS);

		AttributeDescriptor averageRatingDescriptor = TypeManager.getInstance().getComposedType(Product.class)
				.getDeclaredAttributeDescriptor(AVERAGE_RATING);

		excludeNotRomanceCopyProductAttributes(syncJob,
				Arrays.asList(aliasDescriptor, codeDescriptor, manufacturerAIDDescriptor, productReferencesDescriptor,
						productReviewsDescriptor, numberOfReviewsDescriptor, averageRatingDescriptor, unitDescriptor,
						productPriceQuantityDescriptor, productPricesDescriptor, productApprovalStatusDescriptor));

		final List itemPKs = new ArrayList();
		itemPKs.add(new PK[] { sourceProduct.getPK(), targetProduct == null ? null : targetProduct.getPK() });

		synchronizeJob.addPendingItems(itemPKs, false);

		final Language lang = modelService.getSource(getCommonI18NService().getCurrentLanguage());
		final Set<Language> syncLanguages = new HashSet<>();
		syncLanguages.add(lang);

		syncJob.setSyncLanguages(syncLanguages);
		syncJob.perform(synchronizeJob, true);
	}

	private void excludeNotRomanceCopyProductAttributes(SyncItemJob syncJob, List<AttributeDescriptor> attributeDescriptors)
	{
		SyncAttributeDescriptorConfig cfg;
		for (Object attributeDescriptor : TypeManager.getInstance().getComposedType(Product.class)
				.getDeclaredAttributeDescriptors())
		{
			if (!attributeDescriptors.contains(attributeDescriptor))
			{
				cfg = syncJob.getConfigFor((AttributeDescriptor) attributeDescriptor, true);
				cfg.setIncludedInSync(true);
			}
			else
				cfg = syncJob.getConfigFor((AttributeDescriptor) attributeDescriptor, true);
			cfg.setIncludedInSync(true);
		}
	}

	/**
	 * Returns product that belongs to given catalog/catalog version
	 *
	 * @param catalogVersionModel - catalog version
	 * @param code                - product code
	 * @param options             - additional options
	 * @return product data
	 */
	public ProductData getProductForCodeAndOptions(final CatalogVersionModel catalogVersionModel, final String code,
			final Collection<ProductOption> options)
	{
		// switch to admin user to remove restrictions
		// more details on hybris wiki https://wiki.hybris.com/display/release5/Restrictions
		final ProductModel productModel = sessionService.executeInLocalView(new SessionExecutionBody()
		{
			@Override
			public ProductModel execute()
			{
				userService.setCurrentUser(userService.getAdminUser());
				return getProductService().getProductForCode(catalogVersionModel, code);
			}
		});

		return getProductForOptions(productModel, options);
	}

	public Populator<ProductData, ProductModel> getProductReversePopulator()
	{
		return productReversePopulator;
	}

	public void setProductReversePopulator(final Populator<ProductData, ProductModel> productReversePopulator)
	{
		this.productReversePopulator = productReversePopulator;
	}



	/**
	 * Return list of product restricted by timestamp. All products with modifiedtime > timestamp will be returned
	 *
	 * @param timestamp - Date of product modification
	 * @param options   - additional options
	 * @return DTO with product list
	 */
	public List<ProductData> getAllProductsWithOptions(Date timestamp, final Collection<ProductOption> options)
	{
		final String query = "SELECT {pk} from {Product} WHERE {modifiedtime} >= ?timestamp";
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("timestamp", timestamp);

		final SearchResult<ProductModel> searchResult = flexibleSearchService.search(query, params);
		Iterator<ProductModel> it = searchResult.getResult().iterator();
		List<ProductData> result = new ArrayList<ProductData>();

		while (it.hasNext())
		{
			result.add(getProductForOptions(it.next(), options));
		}
		return result;
	}

	/**
	 * Return DTO with list of product for particular catalog and restricted by timestamp.
	 * All products with modifiedtime > timestamp will be returned
	 *
	 * @param catalogVersionModel - Catalog version (Imported, Staged, Online)
	 * @param timestamp           - Date of product modification
	 * @param options             - additional options
	 * @return DTO with product list
	 */
	public List<ProductData> getAllProductsWithOptions(final CatalogVersionModel catalogVersionModel, Date timestamp,
			final Collection<ProductOption> options)
	{
		final String query = "SELECT {pk} from {Product} WHERE {modifiedtime} >= ?timestamp AND {catalogVersion} = ?catalogVersion";
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("timestamp", timestamp);
		params.put("catalogVersion", catalogVersionModel);

		final SearchResult<ProductModel> searchResult = sessionService.executeInLocalView(new SessionExecutionBody()
		{
			@Override
			public SearchResult<ProductModel> execute()
			{
				userService.setCurrentUser(userService.getAdminUser());
				return flexibleSearchService.search(query, params);
			}
		});

		Iterator<ProductModel> it = searchResult.getResult().iterator();
		List<ProductData> result = new ArrayList<ProductData>();

		while (it.hasNext())
		{
			ProductModel product = it.next();
			if (ArticleApprovalStatus.APPROVED.getCode().equalsIgnoreCase(product.getApprovalStatus().getCode()))
			{
				result.add(getProductForOptions(product, options));
			}
		}

		return result;
	}

	/**
	 * Returns product reviews that belongs to given catalog/catalog version
	 *
	 * @param catalogVersionModel - catalog version
	 * @param productCode         - product code
	 * @param numberOfReviews
	 * @return list of product reviews
	 */

	public List<ReviewData> getReviews(final CatalogVersionModel catalogVersionModel, final String productCode,
			Integer numberOfReviews)
	{
		final ProductModel product = sessionService.executeInLocalView(new SessionExecutionBody()
		{
			@Override
			public ProductModel execute()
			{
				userService.setCurrentUser(userService.getAdminUser());
				return getProductService().getProductForCode(catalogVersionModel, productCode);
			}
		});

		List<CustomerReviewModel> reviews = getCustomerReviewService()
				.getReviewsForProductAndLanguage(product, getCommonI18NService().getCurrentLanguage());
		if (numberOfReviews == null)
		{
			return Converters.convertAll(reviews, getCustomerReviewConverter());
		}
		if (numberOfReviews.intValue() < 0)
		{
			throw new IllegalArgumentException();
		}
		return Converters
				.convertAll(reviews.subList(0, Math.min(numberOfReviews.intValue(), reviews.size())), getCustomerReviewConverter());
	}

	public Map<String, String> getProductAttributesByCode(final CatalogVersionModel catalogVersionModel, final String productCode)
	{
		final ProductModel productModel = sessionService.executeInLocalView(new SessionExecutionBody()
		{
			@Override
			public ProductModel execute()
			{
				userService.setCurrentUser(userService.getAdminUser());
				return getProductService().getProductForCode(catalogVersionModel, productCode);
			}
		});
		final List<ProductAttributeModel> productAttributes = productModel.getProductAttributes();
		if (productAttributes != null)
		{
			final HashMap info = new HashMap();
			final Iterator attributesIterator = productAttributes.iterator();
			while (attributesIterator.hasNext())
			{
				final ProductAttributeModel attribute = (ProductAttributeModel) attributesIterator.next();
				final ProductAttributesTypeEnum attributeKey = (ProductAttributesTypeEnum) attribute.getAttributeType();
				final String attributeValue = (String) attribute.getAttributeValue();
				info.put(attributeKey.getCode(), attributeValue);
			}
			return info;
		}
		return Collections.emptyMap();
	}
}
