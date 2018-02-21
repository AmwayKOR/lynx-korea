package com.amway.amwayinventory.populator;

import static com.amway.amwayinventory.AmwayInventoryTestConstants.WAREHOUSE_1;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amway.amwayinventory.data.AmwayInventoryBean;
import com.amway.amwayinventory.data.AmwayInventoryDeltaAdjustmentData;
import com.amway.amwayinventory.data.AmwayInventoryUpdateRequest;


@UnitTest
public class AmwayInventoryUpdateBeansPopulatorTest
{
	@InjectMocks
	private AmwayInventoryUpdateBeansPopulator amwayInventoryUpdateBeansPopulator;

	@Mock
	private Converter<AmwayInventoryDeltaAdjustmentData, AmwayInventoryBean> amwayInventoryBeanAdjustmentConverter;

	@Mock
	private AmwayInventoryUpdateRequest updateRequest;
	@Mock
	private AmwayInventoryBean inventoryBean;
	@Mock
	private AmwayInventoryDeltaAdjustmentData adjustmentData;

	private List<AmwayInventoryBean> dataToPopulate = new ArrayList<>();

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		when(amwayInventoryBeanAdjustmentConverter.convert(any())).thenReturn(inventoryBean);
		when(updateRequest.getArray()).thenReturn(singletonList(adjustmentData));
	}

	@Test
	public void shouldNotPopulateWhenSourceAdjustmentsIsNull()
	{
		when(updateRequest.getArray()).thenReturn(null);

		amwayInventoryUpdateBeansPopulator.populate(updateRequest, dataToPopulate);

		assertTrue(dataToPopulate.isEmpty());
	}

	@Test
	public void shouldNotPopulateWhenSourceAdjustmentsIsEmpty()
	{
		when(updateRequest.getArray()).thenReturn(emptyList());

		amwayInventoryUpdateBeansPopulator.populate(updateRequest, dataToPopulate);

		assertTrue(dataToPopulate.isEmpty());
	}

	@Test
	public void shouldConvertEachAdjustmentToInventoryBeanWhenSourceAdjustmentsNotEmpty()
	{
		amwayInventoryUpdateBeansPopulator.populate(updateRequest, dataToPopulate);

		verify(amwayInventoryBeanAdjustmentConverter, times(updateRequest.getArray().size())).convert(any());
	}

	@Test
	public void shouldPopulateWarehouseCodeForEachInventoryBeanWhenSourceAdjustmentsNotEmpty()
	{
		when(updateRequest.getWarehouseCode()).thenReturn(WAREHOUSE_1);

		amwayInventoryUpdateBeansPopulator.populate(updateRequest, dataToPopulate);

		verify(inventoryBean, times(updateRequest.getArray().size())).setWarehouseCode(WAREHOUSE_1);
	}

	@Test
	public void shouldPopulateSameCountOfInventoryBeansAsSourceAdjustmentsCount()
	{
		amwayInventoryUpdateBeansPopulator.populate(updateRequest, dataToPopulate);

		assertTrue(updateRequest.getArray().size() == dataToPopulate.size());
	}
}