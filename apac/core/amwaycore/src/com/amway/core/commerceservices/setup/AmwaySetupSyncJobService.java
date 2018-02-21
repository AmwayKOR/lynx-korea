package com.amway.core.commerceservices.setup;

import de.hybris.platform.catalog.jalo.*;
import de.hybris.platform.commerceservices.setup.impl.DefaultSetupSyncJobService;
import de.hybris.platform.core.model.type.ComposedTypeModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.JobModel;
import de.hybris.platform.cronjob.model.TriggerModel;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.type.AttributeDescriptor;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.TypeManager;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;


/**
 * @author Aliaksei Sery
 */
public class AmwaySetupSyncJobService extends DefaultSetupSyncJobService
{

	private static final Logger LOG = Logger.getLogger(AmwaySetupSyncJobService.class);
	public static final String PRODUCT_REVIEWS_DESCRIPTOR = "productReviews";

	public void createProductCatalogSyncJob(final String catalogId, final String sourceCatalogVersion,
			final String targetCatalogVersion, final User sessionUser)
	{
		if (this.getCatalogSyncJob(catalogId) == null)
		{
			LOG.info("Creating product sync item job for [" + catalogId + "]");
			final Catalog catalog = CatalogManager.getInstance().getCatalog(catalogId);
			final String jobName = createJobIdentifier(catalogId);
			final SyncItemJob syncItemJob = CatalogManager.getInstance()
					.configureSynchronizationJob(jobName, catalog, sourceCatalogVersion, targetCatalogVersion, true, false);
			syncItemJob.setSessionUser(sessionUser);

			this.processRootTypes(syncItemJob, catalogId, this.getProductCatalogRootTypeCodes());

			AttributeDescriptor productReviewsDescriptor = TypeManager.getInstance().getComposedType(Product.class)
					.getDeclaredAttributeDescriptor(PRODUCT_REVIEWS_DESCRIPTOR);

			SyncAttributeDescriptorConfig cfg = syncItemJob.getConfigFor(productReviewsDescriptor, true);
			cfg.setIncludedInSync(true);

			final AttributeDescriptor ad = TypeManager.getInstance().getComposedType("AmwayKitProduct").getDeclaredAttributeDescriptor("kitEntry");
			final SyncAttributeDescriptorConfig cfgSO = syncItemJob.getConfigFor(ad, true);
			cfgSO.setCopyByValue(true);

			this.processEditSyncAttributeDescriptors(syncItemJob, catalogId, this.getProductCatalogEditSyncDescriptors());

			LOG.info("Created product sync item job [" + syncItemJob.getCode() + "]");
		}
	}
}
