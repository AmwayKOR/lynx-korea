package com.amway.amwayinventory.populator;

import static de.hybris.platform.testframework.Assert.assertCollection;
import static java.util.Arrays.asList;
import static java.util.Collections.singleton;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;

import java.util.Collection;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import com.amway.amwayinventory.data.AmwayInventoryBean;
import com.amway.amwayinventory.data.AmwayInventoryValidationResult;


@UnitTest
public class AmwayInventoryValidationResultPopulatorTest
{
	@InjectMocks
	private AmwayInventoryValidationResultPopulator amwayInventoryValidationResultPopulator;

	@Mock
	private MessageSource amwayInventoryValidationMessageSource;

	@Mock
	private Pair<AmwayInventoryBean, Errors> validationEntry;
	@Mock
	private Errors checkResult;
	@Mock
	private AmwayInventoryBean inventoryBean;
	@Mock
	private ObjectError error;

	private AmwayInventoryValidationResult dataToPopulate = new AmwayInventoryValidationResult();
	private Collection<Pair<AmwayInventoryBean, Errors>> sourceData;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		when(validationEntry.getKey()).thenReturn(inventoryBean);
		when(validationEntry.getValue()).thenReturn(checkResult);
		when(checkResult.getAllErrors()).thenReturn(singletonList(error));
		sourceData = singletonList(validationEntry);
	}

	@Test
	public void shouldPopulateTrueWhenAnySourceHasErrors()
	{
		when(checkResult.hasErrors()).thenReturn(Boolean.TRUE);

		amwayInventoryValidationResultPopulator.populate(sourceData, dataToPopulate);

		assertTrue(dataToPopulate.isAnyInvalid());
	}

	@Test
	public void shouldPopulateTrueWhenAllSourceHasErrors()
	{
		sourceData = asList(validationEntry, validationEntry);
		when(checkResult.hasErrors()).thenReturn(Boolean.TRUE);

		amwayInventoryValidationResultPopulator.populate(sourceData, dataToPopulate);

		assertTrue(dataToPopulate.isAllInvalid());
	}

	@Test
	public void shouldPopulateEmptyListWhenSourceContainsOnlyBeansWithErrors()
	{
		when(checkResult.hasErrors()).thenReturn(Boolean.TRUE);

		amwayInventoryValidationResultPopulator.populate(sourceData, dataToPopulate);

		assertTrue(dataToPopulate.getBeansWithoutErrors().isEmpty());
	}

	@Test
	public void shouldPopulateBeansWithoutErrorsWhenSourceContainsValidBeans()
	{
		amwayInventoryValidationResultPopulator.populate(sourceData, dataToPopulate);

		assertCollection(singleton(inventoryBean), dataToPopulate.getBeansWithoutErrors());
	}

	@Test
	public void shouldPopulateErrorMessageWhenAnySourceHasErrors()
	{
		when(checkResult.hasErrors()).thenReturn(Boolean.TRUE);

		amwayInventoryValidationResultPopulator.populate(sourceData, dataToPopulate);

		assertFalse(dataToPopulate.getErrorMessage().isEmpty());
	}

	@Test
	public void shouldBuildLocalizedMessageWhenAnySourceHasErrors()
	{
		String expectedResult = "any_error";
		when(checkResult.hasErrors()).thenReturn(Boolean.TRUE);
		when(amwayInventoryValidationMessageSource.getMessage(any(), any(), any())).thenReturn(expectedResult);

		amwayInventoryValidationResultPopulator.populate(sourceData, dataToPopulate);

		assertTrue(dataToPopulate.getErrorMessage().contains(expectedResult));
	}
}