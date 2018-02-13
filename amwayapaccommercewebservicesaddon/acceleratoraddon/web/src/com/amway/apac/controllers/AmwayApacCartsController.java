package com.amway.apac.controllers;

import com.amway.apac.facades.cart.AmwayApacCartFacade;
import com.amway.core.v2.controller.BaseCommerceController;
import de.hybris.platform.commercefacades.order.data.CommerceCartMetadata;
import de.hybris.platform.commercefacades.order.dto.CartParameters;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commercewebservicescommons.dto.order.CartWsDTO;
import de.hybris.platform.webservicescommons.cache.CacheControl;
import de.hybris.platform.webservicescommons.cache.CacheControlDirective;
import de.hybris.platform.webservicescommons.errors.exceptions.WebserviceValidationException;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.amway.apac.controllers.AmwayapaccommercewebservicesaddonControllerConstants.AmwayApacOAuthTokenScope;

@Controller
@RequestMapping(value = "/{baseSiteId}")
@CacheControl(directive = CacheControlDirective.NO_CACHE)
public class AmwayApacCartsController extends BaseCommerceController {

    @Resource(name = "cartFacade")
    private AmwayApacCartFacade cartFacade;

    @Resource(name = "cartParametersValidator")
    private Validator cartParametersValidator;

    @PreAuthorize("#oauth2.hasAnyScope('"+ AmwayApacOAuthTokenScope.SALES+ "')")
    @RequestMapping(value = "/account/{accountId}/users/{userId}/carts", method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public CartWsDTO getCart(@PathVariable String accountId, @PathVariable String userId,
                             @RequestBody CartParameters cartParameters,
                             @RequestParam(required = false, defaultValue = DEFAULT_FIELD_SET) String fields)
            throws CommerceCartModificationException, WebserviceValidationException
    {
        /**
         * Before coming to this line, AmwayAccount & User has been set into current session using
         * filter @link PosMatchingFilter & @link AmwayUserMatchingFilter respectively.
         */

        /**
         * Step 1: Validations:
         *          a) Validate Pickup store    | It must be a valid store.
         *          b) Validate cart Type       | Must be nonBlank.
         *          c) Validate delivery Mode   | Must be nonBlank. Data integrity validation will be performed in service layers.
         */
        validate(cartParameters, "cartParameters", cartParametersValidator);

        /**
         * Step 2: create cart & return  @link CartWsDTO to client
         */

        CommerceCartMetadata commerceCartMetadata = cartFacade.createCommerceCartMetadata(cartParameters);

        cartFacade.updateCartMetadata(commerceCartMetadata);

        return getDataMapper().map(getSessionCart(), CartWsDTO.class, fields);

    }

}
