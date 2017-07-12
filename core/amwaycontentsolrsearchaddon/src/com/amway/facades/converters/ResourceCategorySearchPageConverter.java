/**
 *
 */
package com.amway.facades.converters;

import de.hybris.platform.commerceservices.converter.Converters;
import de.hybris.platform.commerceservices.converter.impl.AbstractPopulatingConverter;
import de.hybris.platform.commerceservices.search.facetdata.BreadcrumbData;
import de.hybris.platform.commerceservices.search.facetdata.FacetData;
import de.hybris.platform.commerceservices.search.facetdata.SpellingSuggestionData;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Required;

import com.amway.facade.content.data.ContentData;
import com.amway.facade.search.facetdata.ResourseCategorySearchPageData;


public class ResourceCategorySearchPageConverter<QUERY, STATE, RESULT, ITEM extends ContentData, SCAT, CATEGORY> extends
		AbstractPopulatingConverter<ResourseCategorySearchPageData<QUERY, RESULT, SCAT>, ResourseCategorySearchPageData<STATE, ITEM, CATEGORY>>
{
	private Converter<QUERY, STATE> searchStateConverter;
	private Converter<BreadcrumbData<QUERY>, BreadcrumbData<STATE>> breadcrumbConverter;
	private Converter<FacetData<QUERY>, FacetData<STATE>> facetConverter;
	private Converter<SpellingSuggestionData<QUERY>, SpellingSuggestionData<STATE>> spellingSuggestionConverter;
	private Converter<RESULT, ITEM> searchResultContentConverter;
	private Converter<SCAT, CATEGORY> categoryConverter;


	protected Converter<QUERY, STATE> getSearchStateConverter()
	{
		return searchStateConverter;
	}

	@Required
	public void setSearchStateConverter(final Converter<QUERY, STATE> searchStateConverter)
	{
		this.searchStateConverter = searchStateConverter;
	}

	protected Converter<BreadcrumbData<QUERY>, BreadcrumbData<STATE>> getBreadcrumbConverter()
	{
		return breadcrumbConverter;
	}

	@Required
	public void setBreadcrumbConverter(final Converter<BreadcrumbData<QUERY>, BreadcrumbData<STATE>> breadcrumbConverter)
	{
		this.breadcrumbConverter = breadcrumbConverter;
	}

	protected Converter<FacetData<QUERY>, FacetData<STATE>> getFacetConverter()
	{
		return facetConverter;
	}

	@Required
	public void setFacetConverter(final Converter<FacetData<QUERY>, FacetData<STATE>> facetConverter)
	{
		this.facetConverter = facetConverter;
	}

	/**
	 * @return the searchResultContentConverter
	 */
	public Converter<RESULT, ITEM> getSearchResultContentConverter()
	{
		return searchResultContentConverter;
	}

	/**
	 * @param searchResultContentConverter the searchResultContentConverter to set
	 */
	public void setSearchResultContentConverter(final Converter<RESULT, ITEM> searchResultContentConverter)
	{
		this.searchResultContentConverter = searchResultContentConverter;
	}

	protected Converter<SCAT, CATEGORY> getCategoryConverter()
	{
		return categoryConverter;
	}

	@Required
	public void setCategoryConverter(final Converter<SCAT, CATEGORY> categoryConverter)
	{
		this.categoryConverter = categoryConverter;
	}

	protected Converter<SpellingSuggestionData<QUERY>, SpellingSuggestionData<STATE>> getSpellingSuggestionConverter()
	{
		return spellingSuggestionConverter;
	}

	@Required
	public void setSpellingSuggestionConverter(
			final Converter<SpellingSuggestionData<QUERY>, SpellingSuggestionData<STATE>> spellingSuggestionConverter)
	{
		this.spellingSuggestionConverter = spellingSuggestionConverter;
	}

	@Override
	protected ResourseCategorySearchPageData<STATE, ITEM, CATEGORY> createTarget()
	{
		return new ResourseCategorySearchPageData<STATE, ITEM, CATEGORY>();
	}

	@Override
	public void populate(final ResourseCategorySearchPageData<QUERY, RESULT, SCAT> source,
			final ResourseCategorySearchPageData<STATE, ITEM, CATEGORY> target)
	{
		target.setFreeTextSearch(source.getFreeTextSearch());
		target.setCategoryCode(source.getCategoryCode());

		if (source.getSubCategories() != null)
		{
			target.setSubCategories(Converters.convertAll(source.getSubCategories(), getCategoryConverter()));
		}

		if (source.getBreadcrumbs() != null)
		{
			target.setBreadcrumbs(Converters.convertAll(source.getBreadcrumbs(), getBreadcrumbConverter()));
		}

		target.setCurrentQuery(getSearchStateConverter().convert(source.getCurrentQuery()));

		if (source.getFacets() != null)
		{
			target.setFacets(Converters.convertAll(source.getFacets(), getFacetConverter()));
		}

		target.setPagination(source.getPagination());

		if (source.getResults() != null)
		{
			final List<ITEM> convertAll = Converters.convertAll(source.getResults(), getSearchResultContentConverter());
			//			for (final ITEM item : convertAll)
			//			{
			//				String tShort = item.getPageContent();
			//
			//				final Pattern pattern = Pattern.compile(".{1,128}" + target.getFreeTextSearch() + ".{1,128}");
			//				final Matcher matcher = pattern.matcher(tShort);
			//				while (matcher.find())
			//				{
			//					tShort = matcher.group();
			//					break;
			//				}
			//				
			//				tShort = tShort.replaceAll(target.getFreeTextSearch(), "");
			//
			//				item.setShortPageContent(tShort);
			//			}
			final Map<String, Map<String, List<String>>> highlighting = source.getHighlighting();
			if (highlighting != null)
			{
				for (final ITEM item : convertAll)
				{
					final String id = item.getContentId();
					final Map<String, List<String>> mapId = highlighting.get(id);
					item.setShortPageContent(mapId.values().iterator().next().get(0));
				}
			}

			target.setResults(convertAll);
		}

		target.setSorts(source.getSorts());

		if (source.getSpellingSuggestion() != null)
		{
			target.setSpellingSuggestion(getSpellingSuggestionConverter().convert(source.getSpellingSuggestion()));
		}

		target.setKeywordRedirectUrl(source.getKeywordRedirectUrl());

		super.populate(source, target);
	}
}
