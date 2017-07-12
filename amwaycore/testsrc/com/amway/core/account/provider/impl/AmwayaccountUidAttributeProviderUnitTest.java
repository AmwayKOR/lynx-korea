/**
 *
 */
package com.amway.core.account.provider.impl;

import de.hybris.bootstrap.annotations.UnitTest;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.core.model.AmwayAccountModel;

import junit.framework.Assert;


@UnitTest
public class AmwayaccountUidAttributeProviderUnitTest
{
	@InjectMocks
	private final AmwayaccountUidAttributeProvider amwayaccountUidAttributeProvider = new AmwayaccountUidAttributeProvider();
	private AmwayAccountModel amwayAccount;

	@Before
	public void setUp() throws ParseException
	{
		MockitoAnnotations.initMocks(this);
		amwayAccount = Mockito.mock(AmwayAccountModel.class);
		amwayAccount.setCode("123456");

	}

	/**
	 * Test method for
	 * {@link com.amway.core.account.provider.impl.AmwayaccountUidAttributeProvider#get(com.amway.core.model.AmwayAccountModel)}
	 * .
	 */
	@Test
	public void testGet()
	{
		final String accountCode = amwayaccountUidAttributeProvider.get(amwayAccount);
		Assert.assertEquals(String.valueOf(amwayAccount.getCode()), accountCode);
	}

}
