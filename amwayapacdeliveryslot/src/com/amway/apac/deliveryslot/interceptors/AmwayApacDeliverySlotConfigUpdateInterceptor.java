package com.amway.apac.deliveryslot.interceptors;

import static com.amway.apac.deliveryslot.constants.AmwayapacdeliveryslotConstants.DELIVERY_SLOT_CONFIG_MODEL;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.model.ModelService;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.deliveryslot.model.AmwayDeliverySlotAvailabilityModel;
import com.amway.apac.deliveryslot.model.AmwayDeliverySlotConfigModel;
import com.amway.apac.deliveryslot.services.AmwayApacDeliverySlotManagementService;


/**
 * {@link AmwayApacDeliverySlotConfigUpdateInterceptor} is to update the {@link AmwayDeliverySlotAvailabilityModel} on
 * save.For ex. if user changed the value of a Config, it updates next all availability models that exits in DB.
 *
 * @author Ashish Sabal
 *
 */
public class AmwayApacDeliverySlotConfigUpdateInterceptor implements PrepareInterceptor<AmwayDeliverySlotConfigModel>
{
	/** The LOGGER Constant. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AmwayApacDeliverySlotConfigUpdateInterceptor.class);

	/** The model service. */
	private ModelService modelService;

	/** The amway apac delivery slot management service. */
	private AmwayApacDeliverySlotManagementService amwayApacDeliverySlotManagementService;


	/**
	 * Update availability models if update in slot config model
	 *
	 * @param slotConfigModel
	 *           the slot config model
	 * @param ctx
	 *           the ctx
	 * @throws InterceptorException
	 *            the interceptor exception
	 */
	@Override
	public void onPrepare(final AmwayDeliverySlotConfigModel slotConfigModel, final InterceptorContext ctx)
			throws InterceptorException
	{
		validateParameterNotNullStandardMessage(DELIVERY_SLOT_CONFIG_MODEL, slotConfigModel);

		if (Objects.nonNull(slotConfigModel.getDeliveryDay()) && Objects.nonNull(slotConfigModel.getSlotTime()))
		{
			final LocalDate currentDate = LocalDate.now();
			final LocalDate deliveryDate = getAmwayApacDeliverySlotManagementService().getDeliveryDate(currentDate,
					slotConfigModel.getDeliveryDay());
			if (LOGGER.isInfoEnabled())
			{
				LOGGER.info(new StringBuilder(100).append("Modification of Slot data for  Delivery Date : ").append(deliveryDate)
						.append(" started ...").toString());
			}

			// Fetch slot models from slot management service
			final List<AmwayDeliverySlotAvailabilityModel> slotModels = getAmwayApacDeliverySlotManagementService()
					.getNextDeliverySlotByDeliveryDateAndSlot(deliveryDate, slotConfigModel.getSlotTime());

			getAmwayApacDeliverySlotManagementService().updateInfoInSlotsAvailabilityModels(slotConfigModel, slotModels);
		}
		else
		{
			throw new InterceptorException("Delivery Day or Slot Time is NULL ");
		}
	}

	/**
	 * Gets the model service.
	 *
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * Sets the model service.
	 *
	 * @param modelService
	 *           the modelService to set
	 */
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * Gets the amway apac delivery slot management service.
	 *
	 * @return the amwayApacDeliverySlotManagementService
	 */
	public AmwayApacDeliverySlotManagementService getAmwayApacDeliverySlotManagementService()
	{
		return amwayApacDeliverySlotManagementService;
	}

	/**
	 * Sets the amway apac delivery slot management service.
	 *
	 * @param amwayApacDeliverySlotManagementService
	 *           the amwayApacDeliverySlotManagementService to set
	 */
	@Required
	public void setAmwayApacDeliverySlotManagementService(
			final AmwayApacDeliverySlotManagementService amwayApacDeliverySlotManagementService)
	{
		this.amwayApacDeliverySlotManagementService = amwayApacDeliverySlotManagementService;
	}

}
