package com.amway.apac.core.search.solrfacetsearch.resolvers;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.indexer.IndexerBatchContext;
import de.hybris.platform.solrfacetsearch.indexer.spi.InputDocument;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractValueResolver;

import java.util.Date;


/**
 * Resolver to index creation time for products.
 *
 * @author Shubham Goyal
 */
public class AmwayApacProductCreationTimeValueResolver extends AbstractValueResolver<ProductModel, Object, Date>
{
	/**
	 * Implementation to resolve the creation time of the product.
	 */
	@Override
	protected void addFieldValues(final InputDocument document, final IndexerBatchContext batchContext,
			final IndexedProperty indexedProperty, final ProductModel model,
			final ValueResolverContext<Object, Date> resolverContext) throws FieldValueProviderException
	{
		document.addField(indexedProperty, model.getCreationtime(), resolverContext.getFieldQualifier());
	}
}
