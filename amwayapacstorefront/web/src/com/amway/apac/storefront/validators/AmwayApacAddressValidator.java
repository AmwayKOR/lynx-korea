/**
 *
 */
package com.amway.apac.storefront.validators;

import de.hybris.platform.acceleratorstorefrontcommons.forms.validation.AddressValidator;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.amway.apac.storefront.forms.AmwayApacAddressForm;


/**
 * @author Aaron Yong
 *
 */
@Component("amwayApacAddressValidator")
public class AmwayApacAddressValidator extends AddressValidator
{
	private static final int MAX_FIELD_LENGTH = 255;
	private static final int MAX_POSTCODE_LENGTH = 10;


	@Override
	public boolean supports(final Class<?> aClass)
	{
		return AmwayApacAddressForm.class.equals(aClass);
	}

	@Override
	public void validate(final Object object, final Errors errors)
	{
		final AmwayApacAddressForm AmwayApacAddressForm = (AmwayApacAddressForm) object;
		validateStandardFields(AmwayApacAddressForm, errors);
		validateCountrySpecificFields(AmwayApacAddressForm, errors);
	}

	protected void validateStandardFields(final AmwayApacAddressForm AmwayApacAddressForm, final Errors errors)
	{
		validateStringField(AmwayApacAddressForm.getCountryIso(), AddressField.COUNTRY, MAX_FIELD_LENGTH, errors);
		validateStringField(AmwayApacAddressForm.getLine1(), AddressField.LINE1, MAX_FIELD_LENGTH, errors);
		validateStringField(AmwayApacAddressForm.getTownCity(), AddressField.TOWN, MAX_FIELD_LENGTH, errors);
		validateStringField(AmwayApacAddressForm.getPostcode(), AddressField.POSTCODE, MAX_POSTCODE_LENGTH, errors);
	}

	protected void validateCountrySpecificFields(final AmwayApacAddressForm AmwayApacAddressForm, final Errors errors)
	{
		final String isoCode = AmwayApacAddressForm.getCountryIso();
		if (isoCode != null)
		{
			switch (CountryCode.lookup(isoCode))
			{
				case USA:
					validateFieldNotNull(AmwayApacAddressForm.getRegionIso(), AddressField.REGION, errors);
					break;
				default:
					break;
			}
		}
	}

	protected static void validateStringField(final String addressField, final AddressField fieldType, final int maxFieldLength,
			final Errors errors)
	{
		if (addressField == null || StringUtils.isEmpty(addressField) || (StringUtils.length(addressField) > maxFieldLength))
		{
			errors.rejectValue(fieldType.getFieldKey(), fieldType.getErrorKey());
		}
	}

	protected static void validateFieldNotNull(final String addressField, final AddressField fieldType, final Errors errors)
	{
		if (addressField == null)
		{
			errors.rejectValue(fieldType.getFieldKey(), fieldType.getErrorKey());
		}
	}

	protected enum CountryCode
	{
		USA("US"), CANADA("CA"), JAPAN("JP"), CHINA("CN"), BRITAIN("GB"), GERMANY("DE"), DEFAULT("");

		private final String isoCode;

		private static Map<String, CountryCode> lookupMap = new HashMap<String, CountryCode>();
		static
		{
			for (final CountryCode code : CountryCode.values())
			{
				lookupMap.put(code.getIsoCode(), code);
			}
		}

		private CountryCode(final String isoCodeStr)
		{
			this.isoCode = isoCodeStr;
		}

		public static CountryCode lookup(final String isoCodeStr)
		{
			CountryCode code = lookupMap.get(isoCodeStr);
			if (code == null)
			{
				code = DEFAULT;
			}
			return code;
		}

		public String getIsoCode()
		{
			return isoCode;
		}
	}

	protected enum AddressField
	{
		TITLE("titleCode", "address.title.invalid"), FIRSTNAME("firstName", "address.firstName.invalid"), LASTNAME("lastName",
				"address.lastName.invalid"), LINE1("line1", "address.line1.invalid"), LINE2("line2", "address.line2.invalid"), TOWN(
						"townCity", "address.townCity.invalid"), POSTCODE("postcode", "address.postcode.invalid"), REGION("regionIso",
								"address.regionIso.invalid"), COUNTRY("countryIso", "address.country.invalid");

		private final String fieldKey;
		private final String errorKey;

		private AddressField(final String fieldKey, final String errorKey)
		{
			this.fieldKey = fieldKey;
			this.errorKey = errorKey;
		}

		public String getFieldKey()
		{
			return fieldKey;
		}

		public String getErrorKey()
		{
			return errorKey;
		}
	}


}
