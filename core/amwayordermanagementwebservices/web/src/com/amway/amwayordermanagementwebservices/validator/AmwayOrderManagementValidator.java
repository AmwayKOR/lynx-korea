package com.amway.amwayordermanagementwebservices.validator;

import com.amway.amwayordermanagementwebservices.services.OrderSearchCriteria;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Validates
 * <p>
 * 1) if either aboNum or orderNum field is present
 * 2) startDate / endDate input formats
 * <p>
 */
public class AmwayOrderManagementValidator implements Validator {
    private static final String ABONUM_OR_ORDERNUM_SHOULD_BE_PRESENT = "amwayordermanagementwebservices.validation.abonum_or_ordernum_required";
    private static final String START_DATE_INCORRECT_FORMAT = "amwayordermanagementwebservices.validation.startdate_incorrect_format";
    private static final String END_DATE_INCORRECT_FORMAT = "amwayordermanagementwebservices.validation.enddate_incorrect_format";

    private static final String DATE_FORMAT = "ddMMyyyy";

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    /**
     * Checks for presence of either ORDER_NUM or ABO_NUM and validates passed date formats
     *
     * @param object
     * @param errors
     */
    @Override
    @SuppressWarnings("unchecked")
    public void validate(Object object, Errors errors) {
        OrderSearchCriteria criteria = (OrderSearchCriteria) object;
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

        if (StringUtils.isEmpty(criteria.getAccount()) && StringUtils.isEmpty(criteria.getCode())) {
            errors.reject(ABONUM_OR_ORDERNUM_SHOULD_BE_PRESENT);
        }

        if (!StringUtils.isEmpty(criteria.getStartDateString())) {
            try {
                criteria.setStartDate(
                        dateFormat.parse(criteria.getStartDateString()));
            } catch (ParseException e) {
                errors.reject(START_DATE_INCORRECT_FORMAT);
            }
        }

        if (!StringUtils.isEmpty(criteria.getEndDateString())) {
            try {
                criteria.setEndDate(
                        dateFormat.parse(criteria.getEndDateString()));
            } catch (ParseException e) {
                errors.reject(END_DATE_INCORRECT_FORMAT);
            }
        }
    }
}
