package com.amway.core.amwayapigateway.controllers;

import com.amway.com.v2.AmwayApiTokenDTO;
import com.amway.core.annotations.AmwayBean;
import com.amway.facades.apigatateway.AmwayApiGatewayFacade;
import com.amway.core.charon.data.ApiGatewayRequestData;
import com.amway.core.constants.AmwaycoreConstants;
import com.amway.core.model.AmwayAccountModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by aiueq92 on 9/4/17.
 */
@AmwayBean(ext="amwayapigateway",docs="https://jira.amway.com:8444/display/HC/amwayapigateway")
@Controller
@RequestMapping(value = "/auth")
public class AmwayApiGatewayController {

    @Autowired
    private AmwayApiGatewayFacade apiGatewayFacade;

    @RequestMapping(value = "/token", method = RequestMethod.GET)
    @ResponseBody
    public AmwayApiTokenDTO getToken( )
    {
        String token =  apiGatewayFacade.getAuthToken();

        //Pass token back via DTO
        AmwayApiTokenDTO retDTO = new AmwayApiTokenDTO();
        retDTO.setMashtoken(token);
        return retDTO;
    }




}
