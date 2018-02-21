package com.amway.integration.dam.service.impl;

import static com.amway.integration.dam.constants.AmwayDamConstants.DEFAULT_LIMIT;

import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;

import com.amway.integration.dam.dao.AmwayDamQueueDao;
import com.amway.integration.dam.data.AmwayDamEventData;
import com.amway.integration.dam.model.AmwayDamQueueEntryModel;
import com.amway.integration.dam.service.AmwayDamQueueService;
import com.amway.core.annotations.AmwayBean;

@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamEventQueueServiceImpl implements AmwayDamQueueService
{
	@Autowired
	private ModelService modelService;
	@Autowired
	private AmwayDamQueueDao amwayDamQueueDao;
	@Autowired
	private Predicate<AmwayDamEventData> amwayDamEventDataPredicate;
	@Autowired
	private Converter<AmwayDamEventData, AmwayDamQueueEntryModel> amwayDamQueueEntryReverseConverter;

	@Override
	public void registerEvents(List<AmwayDamEventData> events)
	{
		//@formatter:off
		events.stream()
				.filter(amwayDamEventDataPredicate)
				.forEach(this::createDamEventModel);
		//@formatter:on
	}

	@Override
	public List<AmwayDamQueueEntryModel> getEvents(Integer limit)
	{
		int count = (limit == null) ? DEFAULT_LIMIT : limit;
		return amwayDamQueueDao.findEvents(count);
	}

	private void createDamEventModel(AmwayDamEventData eventData)
	{
		AmwayDamQueueEntryModel eventModel = modelService.create(AmwayDamQueueEntryModel.class);
		amwayDamQueueEntryReverseConverter.convert(eventData, eventModel);
		modelService.save(eventModel);
	}
}
