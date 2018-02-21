package com.amway.amwayfulfillment.controllers.shipment;

import static com.amway.amwayfulfillment.constants.AmwayfulfillmentConstants.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amway.amwayfulfillment.exceptions.shipment.AmwayIgnoredConsignmentException;
import com.amway.amwayfulfillment.exceptions.shipment.AmwayModelNotFoundException;
import com.amway.amwayfulfillment.exceptions.shipment.AmwayShipmentConfirmationException;
import com.amway.amwayfulfillment.facades.shipment.AmwayShipmentConfirmationFacade;
import com.amway.amwayfulfillment.order.ShippingEvent;
import com.amway.amwayfulfillment.order.data.AmwayConsignmentCreationInfo;
import com.amway.amwayfulfillment.ws.dto.ShippingError;
import com.amway.amwayfulfillment.ws.dto.ShippingResponse;


@Controller
@RequestMapping(value = "/{baseStoreId:.+}/orders")
public class AmwayOrderShipmentConfirmationController
{
	private static final Logger LOG = Logger.getLogger(AmwayOrderShipmentConfirmationController.class);

	@Resource(name = "amwayShipmentConfirmationFacade")
	private AmwayShipmentConfirmationFacade amwayShipmentConfirmationFacade;

	@Resource(name = "shippingEventValidator")
	private Validator shippingEventValidator;

	@Secured("ROLE_TRUSTED_CLIENT")
	@RequestMapping(value = "/{orderCode:.+}/shipping",
			method = RequestMethod.PUT,
			consumes = { MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ShippingResponse> confirmShipment(@PathVariable final String baseStoreId,
			@PathVariable final String orderCode, @RequestBody final ShippingEvent shippingEvent, final BindingResult bindingResult)
	{
		LOG.info("Shipping confirmation event for orderCode: " + orderCode);

		shippingEventValidator.validate(shippingEvent, bindingResult);
		if (bindingResult.hasErrors())
		{
			// @formatter:off
			final String errorMessage = bindingResult.getFieldErrors().stream()
					.map(FieldError::getField)
					.collect(Collectors.toList())
					.toString();
			// @formatter:on

			return validationError(errorMessage);
		}

		try
		{
			final List<AmwayConsignmentCreationInfo> infos = amwayShipmentConfirmationFacade
					.confirmShipment(shippingEvent, baseStoreId, orderCode);

			final List<ShippingError> errors = infos.stream()
					.filter(i -> !SUCCESS.equals(i.getErrorCode()))
					.map(this::creationError)
					.collect(Collectors.toList());

			if (!errors.isEmpty())
			{
				final ShippingResponse shippingResponse = new ShippingResponse();
				shippingResponse.setErrors(errors);

				return ResponseEntity.status(HttpStatus.MULTI_STATUS).body(shippingResponse);
			}
		}
		catch (final AmwayModelNotFoundException e)
		{
			return exceptionError(e, HttpStatus.NOT_FOUND);
		}
		catch (final AmwayIgnoredConsignmentException e)
		{
			return exceptionError(e, HttpStatus.BAD_REQUEST);
		}

		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	private ShippingError creationError(final AmwayConsignmentCreationInfo info)
	{
		final ShippingError error = new ShippingError();
		error.setMessage(info.getStatusMessage());
		error.setType(info.getErrorCode());

		return error;
	}

	private ResponseEntity<ShippingResponse> validationError(final String msg)
	{
		final ShippingError error = new ShippingError();
		error.setMessage(msg == null ? msg : String.format(NULL_PARAMETER_MSG, msg));
		error.setType(NULL_PARAMETER_ERROR);

		final ShippingResponse response = new ShippingResponse();
		response.setErrors(Collections.singletonList(error));

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	private ResponseEntity<ShippingResponse> exceptionError(final AmwayShipmentConfirmationException e, final HttpStatus httpStatus)
	{
		final ShippingError error = new ShippingError();
		error.setMessage(e.getMessage());
		error.setType(e.getCode());

		final ShippingResponse response = new ShippingResponse();
		response.setErrors(Collections.singletonList(error));

		return ResponseEntity.status(httpStatus).body(response);
	}

}
