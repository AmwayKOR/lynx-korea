/**
 *
 */
package com.amway.core.product.unit.service.impl;

import de.hybris.bootstrap.annotations.UnitTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.core.model.AmwayUnitModel;
import com.amway.core.unit.dao.AmwayUnitDao;


@UnitTest
public class DefaultAmwayUnitServiceUnitTest
{
	@InjectMocks
	private final DefaultAmwayUnitService unitService = new DefaultAmwayUnitService();
	@Mock
	private AmwayUnitDao amwayUnitDao;
	private AmwayUnitModel unitModel;
	private static final String UNIT_CODE = "unitcode";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		unitModel = Mockito.mock(AmwayUnitModel.class);
		BDDMockito.when(unitModel.getCode()).thenReturn(UNIT_CODE);
		BDDMockito.when(amwayUnitDao.findAmwayUnit(UNIT_CODE)).thenReturn(unitModel);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.product.unit.service.impl.DefaultAmwayUnitService#getUnitForCode(java.lang.String)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetUnitForNullCode()
	{
		final AmwayUnitModel unit = unitService.getUnitForCode(null);
		Assert.assertNotNull(unit);
		Assert.assertEquals(UNIT_CODE, unit.getCode());
	}

	/**
	 * Test method for
	 * {@link com.amway.core.product.unit.service.impl.DefaultAmwayUnitService#getUnitForCode(java.lang.String)}.
	 */
	@Test
	public void testGetUnitForCode()
	{
		final AmwayUnitModel unit = unitService.getUnitForCode(UNIT_CODE);
		Assert.assertNotNull(unit);
		Assert.assertEquals(UNIT_CODE, unit.getCode());
	}

}
