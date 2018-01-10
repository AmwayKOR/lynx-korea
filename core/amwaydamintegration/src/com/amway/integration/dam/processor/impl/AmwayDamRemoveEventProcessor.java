package com.amway.integration.dam.processor.impl;

import static com.amway.integration.dam.enums.AmwayDamOperation.REMOVE;
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
 * Processing DAM events of type "REMOVE"
 */
@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamRemoveEventProcessor implements AmwayDamEventProcessor
{
	@Autowired
	private AmwayDamAssetProcessor amwayDamAssetProcessor;
	@Autowired
	private Converter<AmwayDamQueueEntryModel, AmwayDamProcessingData> amwayDamRemoveProcessingDataConverter;

	private List<AmwayDamValidator> validators;

	@Override
	public boolean isApplicable(AmwayDamQueueEntryModel event)
	{
		validateParameterNotNullStandardMessage("event", event);
		return REMOVE.equals(event.getOperation());
	}

	@Override
	public void process(AmwayDamQueueEntryModel event)
	{
		validateParameterNotNullStandardMessage("event", event);
		AmwayDamProcessingData processingData = amwayDamRemoveProcessingDataConverter.convert(event);
		validators.forEach(validator -> validator.validate(processingData));
		amwayDamAssetProcessor.processRemove(processingData);
		amwayDamAssetProcessor.processRemoveMetadata(processingData);
	}

	@Required
	public void setValidators(List<AmwayDamValidator> validators)
	{
		this.validators = validators;
	}
}
