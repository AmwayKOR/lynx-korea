package com.amway.integration.dam.populator;

import static com.amway.integration.dam.constants.AmwayDamConstants.*;
import static com.amway.integration.dam.data.AmwayDamAssetTypeEnum.DOCUMENT;
import static com.amway.integration.dam.data.AmwayDamAssetTypeEnum.IMAGE;
import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonMap;
import static org.junit.Assert.*;

import de.hybris.bootstrap.annotations.UnitTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.amway.integration.dam.data.AmwayDamRenditionData;
import com.google.common.collect.ImmutableMap;


/**
 * Unit Test for {@link AmwayDamRenditionDataPopulator}
 */
@UnitTest
public class AmwayDamRenditionDataPopulatorTest
{
	@InjectMocks
	private AmwayDamRenditionDataPopulator amwayDamRenditionDataPopulator;

	private List<AmwayDamRenditionData> dataToPopulate = new ArrayList<>();
	private Map<String, Object> sourceData;

	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

		//@formatter:off
		Map<String, String> amwayDamAliasMap = ImmutableMap.<String, String>builder()
				.put(ATTRIBUTE_FORMAT, "test:format")
				.put(ATTRIBUTE_HEIGHT, "test:height")
				.put(ATTRIBUTE_WIGHT, "test:width")
				.put(ATTRIBUTE_CONTENT, "test:content")
				.put(ATTRIBUTE_TYPE, "test:types")
				.build();

		amwayDamRenditionDataPopulator.setAmwayDamAliasMap(amwayDamAliasMap);
		amwayDamRenditionDataPopulator.setAmwayDamExceptionalTypes(emptyMap());

		Map<String, Object> renditionContent = ImmutableMap.<String,Object>builder()
				.put("test:height", 100)
				.put("test:width", 100)
				.put("test:format", "image/png")
				.put("test:types", Collections.singletonList("someType"))
				.build();
		Map<String, Object> rendition = ImmutableMap.<String,Object>builder()
				.put("test:content", renditionContent)
				.build();
		sourceData = ImmutableMap.<String, Object>builder()
				.put("first.image.png", rendition)
				.build();
		//@formatter:on
	}

	@Test
	public void shouldNotPopulateWhenRowDataNotContainsMapTypes()
	{
		//@formatter:off
		sourceData = ImmutableMap.<String, Object>builder()
				.put("test:someTag", false)
				.put("test:size", 100L)
				.build();
		//@formatter:on

		amwayDamRenditionDataPopulator.populate(sourceData, dataToPopulate);

		assertTrue(dataToPopulate.isEmpty());
	}

	@Test
	public void shouldNotPopulateWhenSourceIsEmpty()
	{
		sourceData = emptyMap();

		amwayDamRenditionDataPopulator.populate(sourceData, dataToPopulate);

		assertTrue(dataToPopulate.isEmpty());
	}

	@Test
	public void shouldPopulateDocumentTypeWhenNotExceptionalTypeAndAliasMapNotContainsAliasForType()
	{
		//@formatter:off
		Map<String, String> amwayDamAliasMap = ImmutableMap.<String, String>builder()
				.put(ATTRIBUTE_CONTENT, "test:content")
				.build();
		//@formatter:on
		amwayDamRenditionDataPopulator.setAmwayDamAliasMap(amwayDamAliasMap);

		amwayDamRenditionDataPopulator.populate(sourceData, dataToPopulate);

		assertEquals(DOCUMENT, dataToPopulate.iterator().next().getType());
	}

	@Test
	public void shouldPopulateExceptionalTypeWhenExceptionalTypesMapContainsSpecifiedMimeType()
	{
		amwayDamRenditionDataPopulator.setAmwayDamExceptionalTypes(singletonMap("image/png", DOCUMENT));

		amwayDamRenditionDataPopulator.populate(sourceData, dataToPopulate);

		assertEquals(DOCUMENT, dataToPopulate.iterator().next().getType());
	}

	@Test
	public void shouldPopulateNullValuesWhenAliasMapNotContainsAliasesForAttributes()
	{
		//@formatter:off
		Map<String, String> amwayDamAliasMap = ImmutableMap.<String, String>builder()
				.put(ATTRIBUTE_CONTENT, "test:content")
				.build();
		//@formatter:on
		amwayDamRenditionDataPopulator.setAmwayDamAliasMap(amwayDamAliasMap);

		amwayDamRenditionDataPopulator.populate(sourceData, dataToPopulate);

		AmwayDamRenditionData result = dataToPopulate.iterator().next();
		assertNull(result.getMimeType());
		assertNull(result.getWidth());
		assertNull(result.getHeight());
		assertNull(result.getFiltrationProperties());
	}

	@Test
	public void shouldPopulateNullValuesWhenSourceHasDataWithIncorrectType()
	{
		//@formatter:off
		Map<String, Object> renditionContent = ImmutableMap.<String,Object>builder()
				.put("test:height", "100")
				.put("test:width", "100")
				.put("test:format", false)
				.put("test:types", "someType")
				.build();
		Map<String, Object> rendition = ImmutableMap.<String,Object>builder()
				.put("test:content", renditionContent)
				.build();
		sourceData = ImmutableMap.<String, Object>builder()
				.put("first.image.png", rendition)
				.build();
		//@formatter:on

		amwayDamRenditionDataPopulator.populate(sourceData, dataToPopulate);

		AmwayDamRenditionData result = dataToPopulate.iterator().next();
		assertNull(result.getMimeType());
		assertNull(result.getWidth());
		assertNull(result.getHeight());
		assertNull(result.getFiltrationProperties());
	}

	@Test
	public void shouldPopulateNullValuesWhenSourceRenditionMapIsEmpty()
	{
		//@formatter:off
		Map<String, Object> rendition = ImmutableMap.<String,Object>builder()
				.put("test:content", emptyMap())
				.build();
		sourceData = ImmutableMap.<String, Object>builder()
				.put("first.image.png", rendition)
				.build();
		//@formatter:on

		amwayDamRenditionDataPopulator.populate(sourceData, dataToPopulate);

		AmwayDamRenditionData result = dataToPopulate.iterator().next();
		assertNull(result.getMimeType());
		assertNull(result.getWidth());
		assertNull(result.getHeight());
		assertNull(result.getFiltrationProperties());
	}

	@Test
	public void shouldPopulateRenditionIdByKeysFromSourceMap()
	{
		//@formatter:off
		sourceData = ImmutableMap.<String, Object>builder()
				.put("first.png", emptyMap())
				.put("second.png", emptyMap())
				.build();
		//@formatter:on

		amwayDamRenditionDataPopulator.populate(sourceData, dataToPopulate);

		Iterator<AmwayDamRenditionData> iterator = dataToPopulate.iterator();
		assertEquals("first.png", iterator.next().getRenditionId());
		assertEquals("second.png", iterator.next().getRenditionId());
	}

	@Test
	public void shouldPopulateRenditionDataWhenAliasesSpecified()
	{
		amwayDamRenditionDataPopulator.populate(sourceData, dataToPopulate);

		AmwayDamRenditionData result = dataToPopulate.iterator().next();
		assertEquals("first.image.png", result.getRenditionId());
		assertEquals("image/png", result.getMimeType());
		assertEquals(Integer.valueOf(100), result.getWidth());
		assertEquals(Integer.valueOf(100), result.getHeight());
		assertEquals(IMAGE, result.getType());
		assertEquals("someType", result.getFiltrationProperties().iterator().next());
	}
}