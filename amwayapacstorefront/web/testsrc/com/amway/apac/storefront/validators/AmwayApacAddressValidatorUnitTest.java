package com.amway.apac.storefront.validators;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.c2l.RegionModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.amway.apac.storefront.forms.AmwayApacAddressForm;


/**
 * @author Aaron Yong
 *
 */

@UnitTest
public class AmwayApacAddressValidatorUnitTest
{
	private AddressModel a1;
	private CountryModel c1;
	private RegionModel r1;
	private CountryModel c2;
	private RegionModel r2;
	private UserModel u1;
	private AmwayApacAddressForm f1;

	@Before
	public void setUp() throws Exception
	{
		c1 = new CountryModel();
		c1.setActive(Boolean.TRUE);
		c1.setIsocode("c1");

		r1 = new RegionModel();
		r1.setActive(Boolean.TRUE);
		r1.setCountry(c1);
		r1.setIsocode("r1");
		final List<RegionModel> l1 = new ArrayList();
		l1.add(r1);
		c1.setRegions(l1);

		c2 = new CountryModel();
		c2.setActive(Boolean.TRUE);
		c2.setIsocode("c2");
		r2 = new RegionModel();
		r2.setActive(Boolean.TRUE);
		r2.setCountry(c2);
		r2.setIsocode("r2");
		final List<RegionModel> l2 = new ArrayList();
		l2.add(r2);
		c2.setRegions(l2);

		a1 = new AddressModel();
		a1.setOwner(u1);
		a1.setStreetname("Street");
		a1.setTown("Town");
		a1.setCountry(c1);
		a1.setRegion(r1);

		f1 = new AmwayApacAddressForm();
		f1.setFirstName("first");
		f1.setLastName("last");
		f1.setLine1("line1");
		f1.setLine2("line2");
		f1.setLine3("line3");
		f1.setTownCity("KL");
		f1.setPostcode("55555");
		f1.setCountryIso("US");
	}

	@Test(expected = InterceptorException.class)
	public void testAddressValidator() throws InterceptorException
	{
		final AmwayApacAddressValidator vali = new AmwayApacAddressValidator();
		a1.setCountry(c1);
		a1.setRegion(r2);
		vali.validate(a1, null);
	}

	@Test
	public void testAmwayApacAddressFormValidator() throws InterceptorException
	{
		final AmwayApacAddressValidator vali = new AmwayApacAddressValidator();
		vali.validate(f1, null);
	}
}