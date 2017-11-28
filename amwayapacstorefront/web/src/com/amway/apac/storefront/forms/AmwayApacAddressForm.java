package com.amway.apac.storefront.forms;

import de.hybris.platform.acceleratorstorefrontcommons.forms.AddressForm;

import com.amway.core.constants.GeneratedAmwaycoreConstants.Attributes.Address;


/**
 * Extending OOTB {@link Address} to add APAC specific attributes.
 *
 * @author Shubham Goyal
 */
public class AmwayApacAddressForm extends AddressForm
{

	private String email;

	/**
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @param email
	 *           the email to set
	 */
	public void setEmail(final String email)
	{
		this.email = email;
	}
}
