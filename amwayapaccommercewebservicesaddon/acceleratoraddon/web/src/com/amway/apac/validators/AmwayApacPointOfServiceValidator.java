package com.amway.apac.validators;

import com.amway.core.validator.PointOfServiceValidator;
import de.hybris.platform.commercefacades.order.dto.CartParameters;

public class AmwayApacPointOfServiceValidator extends PointOfServiceValidator {
    @Override
    public boolean supports(final Class<?> clazz)
    {
        return super.supports(clazz) || CartParameters.class.isAssignableFrom(clazz);
    }
}
