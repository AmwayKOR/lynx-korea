/**
 *
 */
package com.amway.apac.core.resolvers;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.indexer.IndexerBatchContext;
import de.hybris.platform.solrfacetsearch.indexer.spi.InputDocument;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractValueResolver;

import java.util.Date;


/**
 * @author shubhamgoyal
 *
 */
public class ProductModifiedTimeValueResolver extends AbstractValueResolver<ProductModel, Object, Date>
{


	@Override
	protected void addFieldValues(final InputDocument document, final IndexerBatchContext batchContext,
			final IndexedProperty indexedProperty, final ProductModel model,
			final ValueResolverContext<Object, Date> resolverContext) throws FieldValueProviderException
	{
		final Date timeValue = model.getModifiedtime();
		document.addField(indexedProperty, timeValue, resolverContext.getFieldQualifier());
	}

}
