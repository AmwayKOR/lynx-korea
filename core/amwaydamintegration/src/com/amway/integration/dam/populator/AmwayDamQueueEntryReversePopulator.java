package com.amway.integration.dam.populator;

import static com.amway.integration.dam.constants.AmwayDamConstants.EXTERNAL_DELIMITER;
import static com.amway.integration.dam.constants.AmwayDamConstants.INTERNAL_DELIMITER;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.enumeration.EnumerationService;

import org.springframework.beans.factory.annotation.Autowired;

import com.amway.integration.dam.data.AmwayDamEventData;
import com.amway.integration.dam.enums.AmwayDamOperation;
import com.amway.integration.dam.model.AmwayDamQueueEntryModel;
import com.amway.core.annotations.AmwayBean;

@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamQueueEntryReversePopulator implements Populator<AmwayDamEventData, AmwayDamQueueEntryModel>
{
	@Autowired
	private EnumerationService enumerationService;

	@Override
	public void populate(AmwayDamEventData source, AmwayDamQueueEntryModel target)
	{
		String enumCode = source.getOperation().replace(EXTERNAL_DELIMITER, INTERNAL_DELIMITER).toUpperCase();
		AmwayDamOperation operation = enumerationService.getEnumerationValue(AmwayDamOperation._TYPECODE, enumCode);
		target.setOperation(operation);
		target.setPath(source.getPath());
	}
}
