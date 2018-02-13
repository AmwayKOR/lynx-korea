package com.amway.amwayordermanagementwebservices.controllers;

import static com.amway.amwayordermanagementwebservices.services.OrderSearchCriteria.*;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.ordermanagementwebservices.dto.order.OrderSearchPageWsDto;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.webservicescommons.errors.exceptions.WebserviceValidationException;
import de.hybris.platform.webservicescommons.mapping.DataMapper;
import de.hybris.platform.webservicescommons.mapping.FieldSetLevelHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amway.amwayordermanagementwebservices.services.AmwayOrderSearchService;
import com.amway.amwayordermanagementwebservices.services.OrderSearchCriteria;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Multimaps;


/**
 * IDD-1994 Integration with CMP
 * https://amway-prod.tt.com.pl/confluence/display/AMWEIA/IDD-1994+Integration+with+CMP
 */
@Controller
@RequestMapping(value = "/findorders")
public class AmwayOrderSearchController
{
	@Resource
	private Validator amwayOrderManagementValidator;

	@Resource
	private DataMapper dataMapper;

	@Resource
	private BaseSiteService baseSiteService;

	@Resource
	private AmwayOrderSearchService amwayOrderSearchService;

	@Resource(name = "orderConverter")
	private Converter<OrderModel, OrderData> orderConverter;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public OrderSearchPageWsDto getOrders(
			// @formatter:off
			@RequestParam(required = false) final String aboNum,
			@RequestParam(required = false) final String orderNum,
			@RequestParam(required = false) final String startDate,
			@RequestParam(required = false) final String endDate,
			@RequestParam(required = false) final String orderStatus,
			@RequestParam(required = false) final String productCode,
			@RequestParam(required = false) final String country,
			@RequestParam(required = false, defaultValue = DEFAULT_CURRENT_PAGE) final int currentPage,
			@RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE) final int pageSize,
			@RequestParam(required = false, defaultValue = DEFAULT_SORT) final String sort)
			// @formatter:on
	{

		final OrderSearchCriteria criteria = prepareCriteria(aboNum, orderNum, startDate, endDate, orderStatus, productCode,
				country);
		final PageableData pageableData = createPageable(currentPage, pageSize, sort);
		criteria.setPageableData(pageableData);
		validate(criteria);

		SearchPageData searchData = amwayOrderSearchService.findOrders(criteria);
		// @formatter:off
		ImmutableListMultimap<BaseSiteModel, OrderModel> sortedStores = Multimaps.index(
				((List<OrderModel>) searchData.getResults()),
				om -> om.getSite());
		// @formatter:on

		final Map<String, Integer> indexedKeys = new HashMap<>();
		int i = 0;
		for (OrderModel om : (List<OrderModel>) searchData.getResults())
		{
			indexedKeys.put(om.getCode(), i++);
		}

		if (sortedStores.isEmpty())
		{
			return dataMapper.map(searchData, OrderSearchPageWsDto.class, FieldSetLevelHelper.FULL_LEVEL);
		}

		// @formatter:off
		OrderSearchPageWsDto result = sortedStores.keySet()
				.stream()
				.map(site -> convertSearchResult(site, sortedStores.get(site), searchData))
				.reduce(new OrderSearchPageWsDto(),
				(aOrders, bOrders) ->
				{
					if (aOrders.getOrders() == null)
					{
						aOrders.setOrders(Collections.synchronizedList(new ArrayList<>(indexedKeys.size())));
						aOrders.setPagination(bOrders.getPagination());
						aOrders.setSorts(bOrders.getSorts());
					}

					bOrders.getOrders().stream().forEach(orderWsDTO ->
					{
						int idx = indexedKeys.get(orderWsDTO.getCode());
						aOrders.getOrders().add(idx, orderWsDTO);
					});

					return aOrders;
				});
		// @formatter:on

		return result;
	}

	protected OrderSearchPageWsDto convertSearchResult(final BaseSiteModel site, ImmutableList<OrderModel> orders,
			final SearchPageData globalSearchData)
	{
		baseSiteService.setCurrentBaseSite(site, true);

		final SearchPageData searchData = new SearchPageData();

		// @formatter:off
      searchData.setAvailableFacetsCount( globalSearchData.getAvailableFacetsCount());
      searchData.setPagination(           globalSearchData.getPagination());
      searchData.setSelectedStatsInfo(    globalSearchData.getSelectedStatsInfo());
      searchData.setSorts(                globalSearchData.getSorts());
      searchData.setStatsInfo(            globalSearchData.getStatsInfo());

		List<OrderData> ordersData = orders
				.stream()
				.filter(om->om.getVersionID() == null)
				.map(om -> orderConverter.convert(om, new OrderData()))
				.collect(Collectors.toList());
		 // @formatter:on

		searchData.setResults(ordersData);

		return dataMapper.map(searchData, OrderSearchPageWsDto.class, FieldSetLevelHelper.FULL_LEVEL);
	}

	/**
	 * @param criteria
	 */
	private void validate(final OrderSearchCriteria criteria)
	{
		final Errors errors = new BeanPropertyBindingResult(criteria, "request.errors");
		amwayOrderManagementValidator.validate(criteria, errors);
		if (errors.hasErrors())
		{
			throw new WebserviceValidationException(errors);
		}
	}

	/**
	 * @param aboNum
	 * @param orderNum
	 * @return
	 */
	private OrderSearchCriteria prepareCriteria(final String aboNum, final String orderNum, final String startDate,
			final String endDate, final String orderStatus, final String productCode, final String country)
	{
		final OrderSearchCriteria criteria = new OrderSearchCriteria();

		criteria.setAccount(aboNum);
		criteria.setCode(orderNum);
		criteria.setStartDateString(startDate);
		criteria.setEndDateString(endDate);
		criteria.setStatus(orderStatus);
		criteria.setProductCode(productCode);
		criteria.setCountry(country);

		return criteria;
	}

	/**
	 * @param page
	 * @param pageSize
	 * @param sort
	 * @return
	 */
	protected PageableData createPageable(final int page, final int pageSize, final String sort)
	{
		final PageableData pageableData = new PageableData();
		pageableData.setCurrentPage(page);
		pageableData.setPageSize(pageSize);
		pageableData.setSort(sort);
		return pageableData;
	}
}