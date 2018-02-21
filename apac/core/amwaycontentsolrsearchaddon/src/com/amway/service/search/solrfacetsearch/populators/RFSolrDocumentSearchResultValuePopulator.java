/**
 *
 */
package com.amway.service.search.solrfacetsearch.populators;

import de.hybris.platform.catalog.model.classification.ClassAttributeAssignmentModel;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.DocumentData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.IndexedType;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.search.Document;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Required;


public class RFSolrDocumentSearchResultValuePopulator implements
		Populator<DocumentData<SearchQuery, Document>, SearchResultValueData>
{
	private FieldNameProvider fieldNameProvider;

	protected FieldNameProvider getFieldNameProvider()
	{
		return this.fieldNameProvider;
	}

	@Required
	public void setFieldNameProvider(final FieldNameProvider fieldNameProvider)
	{
		this.fieldNameProvider = fieldNameProvider;
	}

	@Override
	public void populate(final DocumentData<SearchQuery, Document> source, final SearchResultValueData target)
	{
		final Map values = new HashMap();

		for (final IndexedProperty property : source.getSearchQuery().getIndexedType().getIndexedProperties().values())
		{
			values.put(property.getName(), getValue(source, property.getName()));
		}

		target.setValues(values);
		target.setFeatureValues(getFeatureValues(source.getSearchQuery().getIndexedType(), source));
	}

	protected Object getValue(final DocumentData<SearchQuery, Document> source, final String propertyName)
	{
		final IndexedProperty indexedProperty = source.getSearchQuery().getIndexedType().getIndexedProperties().get(propertyName);
		if (indexedProperty == null)
		{
			return null;
		}

		return getValue(source, indexedProperty);
	}

	protected Object getValue(final DocumentData<SearchQuery, Document> source, final IndexedProperty property)
	{
		//		final String fieldName = "id".equals(property.getName()) ? property.getName() : translateFieldName(source.getSearchQuery(),
		//				property);
		return source.getDocument().getFieldValue(property.getName());
	}

	protected String translateFieldName(final SearchQuery searchQuery, final IndexedProperty property)
	{
		if (property.isLocalized())
		{
			return getFieldNameProvider().getFieldName(property, searchQuery.getLanguage(), FieldNameProvider.FieldType.INDEX);
		}
		if (property.isCurrency())
		{
			return getFieldNameProvider().getFieldName(property, searchQuery.getCurrency(), FieldNameProvider.FieldType.INDEX);
		}
		return getFieldNameProvider().getFieldName(property, null, FieldNameProvider.FieldType.INDEX);
	}

	protected Map<ClassAttributeAssignmentModel, Object> getFeatureValues(final IndexedType indexedType,
			final DocumentData<SearchQuery, Document> source)
	{
		final Map result = new LinkedHashMap();

		for (final IndexedProperty indexedProperty : indexedType.getIndexedProperties().values())
		{
			final Object value = getValue(source, indexedProperty.getName());
			final ClassAttributeAssignmentModel classAttributeAssignment = indexedProperty.getClassAttributeAssignment();
			if ((classAttributeAssignment == null) || (!(Boolean.TRUE.equals(classAttributeAssignment.getListable())))
					|| value == null)
			{
				continue;
			}
			result.put(classAttributeAssignment, value);
		}

		return result;
	}
}
