package com.amway.integration.dam.processor.impl;

import static com.amway.integration.dam.enums.AmwayDamOperation.METADATA_UPDATE;
import static com.amway.integration.dam.enums.AmwayDamOperation.UPDATE;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.amway.integration.dam.data.AmwayDamProcessingData;
import com.amway.integration.dam.model.AmwayDamQueueEntryModel;
import com.amway.integration.dam.processor.AmwayDamAssetProcessor;
import com.amway.integration.dam.processor.AmwayDamEventProcessor;
import com.amway.integration.dam.validate.AmwayDamValidator;
import com.amway.core.annotations.AmwayBean;

/**
 * Processing DAM events of type "UPDATE" and "METADATA_UPDATE"
 */
@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamUpdateEventProcessor implements AmwayDamEventProcessor
{
	@Autowired
	private AmwayDamAssetProcessor amwayDamAssetProcessor;
	@Autowired
	private Converter<AmwayDamQueueEntryModel, AmwayDamProcessingData> amwayDamUpdateProcessingDataConverter;

	private List<AmwayDamValidator> validators;

	@Override
	public boolean isApplicable(AmwayDamQueueEntryModel event)
	{
		validateParameterNotNullStandardMessage("event", event);
		return UPDATE.equals(event.getOperation()) || METADATA_UPDATE.equals(event.getOperation());
	}

	@Override
	public void process(AmwayDamQueueEntryModel event)
	{
		validateParameterNotNullStandardMessage("event", event);
		AmwayDamProcessingData processingData = amwayDamUpdateProcessingDataConverter.convert(event);
		validators.forEach(validator -> validator.validate(processingData));
		amwayDamAssetProcessor.processCreate(processingData);
		amwayDamAssetProcessor.processUpdate(processingData);
		amwayDamAssetProcessor.processRemove(processingData);
		amwayDamAssetProcessor.processMetadata(processingData);
	}

	@Required
	public void setValidators(List<AmwayDamValidator> validators)
	{
		this.validators = validators;
	}
}
