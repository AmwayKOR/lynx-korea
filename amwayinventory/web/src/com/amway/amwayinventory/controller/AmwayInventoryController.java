package com.amway.amwayinventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amway.amwayinventory.data.AmwayInventoryUpdateRequest;
import com.amway.amwayinventory.data.AmwayInventoryUpdateResponse;
import com.amway.amwayinventory.facade.AmwayInventoryUpdateFacade;


@RestController
@RequestMapping(value = "/{warehouseCode:.+}")
public class AmwayInventoryController
{
	@Autowired
	private AmwayInventoryUpdateFacade amwayInventoryUpdateFacade;

	@Secured("ROLE_TRUSTED_CLIENT")
	@RequestMapping(value = "/stocklevels",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AmwayInventoryUpdateResponse> updateStockLevels(@RequestBody final AmwayInventoryUpdateRequest request,
			@PathVariable final String warehouseCode)
	{
		request.setWarehouseCode(warehouseCode);
		AmwayInventoryUpdateResponse response = amwayInventoryUpdateFacade.process(request);
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}

}