package com.amway.integration.dam.populator;

import static com.amway.integration.dam.constants.AmwayDamConstants.*;
import static com.amway.integration.dam.data.AmwayDamAssetTypeEnum.DOCUMENT;
import static com.amway.integration.dam.data.AmwayDamAssetTypeEnum.IMAGE;
import static java.util.Arrays.asList;
import static java.util.Collections.*;
import static org.apache.commons.collections.CollectionUtils.isEqualCollection;
import static org.apache.commons.lang3.time.DateUtils.parseDate;
import static org.junit.Assert.*;

import de.hybris.bootstrap.annotations.UnitTest;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.amway.integration.dam.data.AmwayDamAssetData;
import com.google.common.collect.ImmutableMap;


/**
 * Unit Test for {@link AmwayDamAssetDataPopulator}
 */
@UnitTest
public class AmwayDamAssetDataPopulatorTest
{
	private static final String TEST_ISO_CODE = "FR";

	private final AmwayDamAssetDataPopulator amwayDamAssetDataPopulator = new AmwayDamAssetDataPopulator();

	private AmwayDamAssetData dataToPopulate = new AmwayDamAssetData();
	private Map<String, Object> sourceData;

	@Before
	public void setUp()
	{
		//@formatter:off
		Map<String, String> amwayDamAliasMap = ImmutableMap.<String, String>builder()
			    .put(ATTRIBUTE_FORMAT, "test:format")
			    .put(ATTRIBUTE_TYPE, "test:MIMEtype")
				.put(ATTRIBUTE_DEFAULT_ASSET, "test:default")
				.put(ATTRIBUTE_TAGS, "test:tags")
				.put(ATTRIBUTE_ASSET_ID, "test:asset-id")
				.put(ATTRIBUTE_ASSET_MODIFIED, "test:modifyTime")
				.put(ATTRIBUTE_KEYWORDS, "test:keywords")
				.put(ATTRIBUTE_DURATION, "test:duration")
				.put(ATTRIBUTE_VIDEO_ID, "test:video-id")
				.build();

		Map<String, String> amwayDamTagNamesMap = ImmutableMap.<String, String>builder()
				.put(TAG_NAME_COUNTRIES, "country")
				.put(TAG_NAME_LANGUAGES, "lang")
				.put(TAG_NAME_MCP, "mcp-tag")
				.build();

		Map<String, String> amwayDamTitlesMap = ImmutableMap.<String, String>builder()
				.put(TEST_ISO_CODE, "test:title")
				.build();

		Map<String, String> amwayDamDescriptionMap = ImmutableMap.<String, String>builder()
				.put(TEST_ISO_CODE, "test:desc")
				.build();

		List<String> amwayDamIsoCodes = singletonList(TEST_ISO_CODE);

		amwayDamAssetDataPopulator.setAmwayDamAliasMap(amwayDamAliasMap);
		amwayDamAssetDataPopulator.setAmwayDamTagNamesMap(amwayDamTagNamesMap);
		amwayDamAssetDataPopulator.setAmwayDamTitlesMap(amwayDamTitlesMap);
		amwayDamAssetDataPopulator.setAmwayDamDescriptionMap(amwayDamDescriptionMap);
		amwayDamAssetDataPopulator.setAmwayDamIsoCodes(amwayDamIsoCodes);
		amwayDamAssetDataPopulator.setAmwayDamExceptionalTypes(emptyMap());

		sourceData = ImmutableMap.<String, Object>builder()
				.put("test:format", "image/png")
				.put("test:default", true)
				.put("test:tags", asList("country:france", "lang:french", "mcp-tag:mcp"))
				.put("test:asset-id", "some_asset_id")
				.put("test:modifyTime", "2017-01-01 10:02")
				.put("test:keywords", asList("key", "word"))
				.put("test:duration", "3000")
				.put("test:video-id", "some_video_id")
				.put("test:title", "some_title")
				.put("test:desc", "some_description")
				.build();
		//@formatter:on
	}

	@Test
	public void shouldPopulateWithNullWhenAliasMapIsEmpty()
	{
		amwayDamAssetDataPopulator.setAmwayDamAliasMap(emptyMap());

		amwayDamAssetDataPopulator.populate(sourceData, dataToPopulate);

		assertNull(dataToPopulate.getAssetId());
		assertNull(dataToPopulate.getAssetModified());
		assertNull(dataToPopulate.getMimeType());
		assertNull(dataToPopulate.getKeyWords());
		assertNull(dataToPopulate.getDuration());
		assertNull(dataToPopulate.getVideoId());
	}

	@Test
	public void shouldNotPopulateWhenSourceIsEmpty()
	{
		sourceData = emptyMap();

		amwayDamAssetDataPopulator.populate(sourceData, dataToPopulate);

		assertNull(dataToPopulate.getAssetId());
		assertNull(dataToPopulate.getAssetModified());
		assertNull(dataToPopulate.getMimeType());
		assertNull(dataToPopulate.getKeyWords());
		assertNull(dataToPopulate.getDuration());
		assertNull(dataToPopulate.getVideoId());
		assertNull(dataToPopulate.getType());
		assertNull(dataToPopulate.getDefaultAsset());
		assertNull(dataToPopulate.getCountries());
		assertNull(dataToPopulate.getLanguages());
	}

	@Test
	public void shouldPopulateWithNullWhenSourceHasDataWithIncorrectType() throws Exception
	{
		//@formatter:off
		sourceData = ImmutableMap.<String, Object>builder()
				.put("test:asset-id", 1L)
				.put("test:modifyTime", parseDate("2017-01-01", "yyyy-mm-dd"))
				.put("test:type", false)
				.put("test:keywords", 0)
				.put("test:duration", 3000)
				.put("test:video-id", 2L)
				.build();
		//@formatter:on

		amwayDamAssetDataPopulator.populate(sourceData, dataToPopulate);

		assertNull(dataToPopulate.getAssetId());
		assertNull(dataToPopulate.getAssetModified());
		assertNull(dataToPopulate.getMimeType());
		assertNull(dataToPopulate.getKeyWords());
		assertNull(dataToPopulate.getDuration());
		assertNull(dataToPopulate.getVideoId());
	}

	@Test
	public void shouldPopulateWithCorrectDataWhenSourceHasDataAndAliasesSpecified() throws Exception
	{
		amwayDamAssetDataPopulator.populate(sourceData, dataToPopulate);

		assertEquals("some_asset_id", dataToPopulate.getAssetId());
		assertEquals("2017-01-01 10:02", dataToPopulate.getAssetModified());
		assertEquals("image/png", dataToPopulate.getMimeType());
		assertEquals(asList("key", "word"), dataToPopulate.getKeyWords());
		assertEquals("3000", dataToPopulate.getDuration());
		assertEquals("some_video_id", dataToPopulate.getVideoId());
	}

	@Test
	public void shouldPopulateFalseIntoDefaultAssetWhenAliasMapIsEmpty()
	{
		amwayDamAssetDataPopulator.setAmwayDamAliasMap(emptyMap());

		amwayDamAssetDataPopulator.populate(sourceData, dataToPopulate);

		assertFalse(dataToPopulate.getDefaultAsset());
	}

	@Test
	public void shouldPopulateFalseIntoDefaultAssetWhenSourceHasDataWithIncorrectType()
	{
		//@formatter:off
		sourceData = ImmutableMap.<String, Object>builder()
				.put("test:type", "false")
				.build();
		//@formatter:on

		amwayDamAssetDataPopulator.populate(sourceData, dataToPopulate);

		assertFalse(dataToPopulate.getDefaultAsset());
	}

	@Test
	public void shouldPopulateCorrectValueToDefaultAssetWhenSourceHasDataAndAliasesSpecified()
	{
		amwayDamAssetDataPopulator.populate(sourceData, dataToPopulate);

		assertTrue(dataToPopulate.getDefaultAsset());
	}

	@Test
	public void shouldPopulateRawDataFromSource()
	{
		amwayDamAssetDataPopulator.populate(sourceData, dataToPopulate);

		assertEquals(sourceData, dataToPopulate.getRowAssetMetadata());
	}

	@Test
	public void shouldPopulateEmptyMapWhenTitlesAndDescriptionsAliasesIsEmpty()
	{
		amwayDamAssetDataPopulator.setAmwayDamTitlesMap(emptyMap());
		amwayDamAssetDataPopulator.setAmwayDamDescriptionMap(emptyMap());

		amwayDamAssetDataPopulator.populate(sourceData, dataToPopulate);

		assertTrue(dataToPopulate.getTitle().isEmpty());
		assertTrue(dataToPopulate.getDescription().isEmpty());
	}

	@Test
	public void shouldPopulateEmptyMapWhenIsoCodesIsEmpty()
	{
		amwayDamAssetDataPopulator.setAmwayDamIsoCodes(emptyList());

		amwayDamAssetDataPopulator.populate(sourceData, dataToPopulate);

		assertTrue(dataToPopulate.getTitle().isEmpty());
		assertTrue(dataToPopulate.getDescription().isEmpty());
	}

	@Test
	public void shouldPopulateEmptyMapWhenSourceHasDataWithIncorrectType()
	{
		//@formatter:off
		sourceData = ImmutableMap.<String, Object>builder()
				.put("test:title", false)
				.put("test:desc", false)
				.build();
		//@formatter:on

		amwayDamAssetDataPopulator.populate(sourceData, dataToPopulate);

		assertTrue(dataToPopulate.getTitle().isEmpty());
		assertTrue(dataToPopulate.getDescription().isEmpty());
	}

	@Test
	public void shouldPopulateTitleAndDescriptionWhenAliasesSpecifiedAndSourceContainsData()
	{
		amwayDamAssetDataPopulator.populate(sourceData, dataToPopulate);

		assertTrue(dataToPopulate.getTitle().containsKey(TEST_ISO_CODE));
		assertTrue(dataToPopulate.getTitle().containsValue("some_title"));
		assertTrue(dataToPopulate.getDescription().containsKey(TEST_ISO_CODE));
		assertTrue(dataToPopulate.getDescription().containsValue("some_description"));
	}

	@Test
	public void shouldNotPopulateByTagsWhenAliasMapIsEmpty()
	{
		amwayDamAssetDataPopulator.setAmwayDamAliasMap(emptyMap());

		amwayDamAssetDataPopulator.populate(sourceData, dataToPopulate);

		assertNull(dataToPopulate.getCountries());
		assertNull(dataToPopulate.getLanguages());
	}

	@Test
	public void shouldNotPopulateByTagsWhenSourceHasDataWithIncorrectType()
	{
		//@formatter:off
		sourceData = ImmutableMap.<String, Object>builder()
				.put("test:tags", "country:france")
				.build();
		//@formatter:on

		amwayDamAssetDataPopulator.populate(sourceData, dataToPopulate);

		assertNull(dataToPopulate.getCountries());
		assertNull(dataToPopulate.getLanguages());
	}

	@Test
	public void shouldPopulateEmptyListWhenTagsAliasesMapNotContainsAppropriateTag()
	{
		amwayDamAssetDataPopulator.setAmwayDamTagNamesMap(singletonMap("someTagName", "someValue"));

		amwayDamAssetDataPopulator.populate(sourceData, dataToPopulate);

		assertTrue(dataToPopulate.getCountries().isEmpty());
		assertTrue(dataToPopulate.getLanguages().isEmpty());
	}

	@Test
	public void shouldPopulateEmptyWhenTagsAliasesMapContainsTagButTagHasIncorrectFormat()
	{
		//@formatter:off
		sourceData = ImmutableMap.<String, Object>builder()
				.put("test:tags", asList("country=france", "lang=french", "mcp-tag=mcp"))
				.build();
		//@formatter:on

		amwayDamAssetDataPopulator.populate(sourceData, dataToPopulate);

		assertTrue(dataToPopulate.getCountries().isEmpty());
		assertTrue(dataToPopulate.getLanguages().isEmpty());
	}

	@Test
	public void shouldPopulateCountriesAndLanguagesWhenAliasesSpecifiedAndSourceContainsData()
	{
		amwayDamAssetDataPopulator.populate(sourceData, dataToPopulate);

		assertFalse(dataToPopulate.getCountries().isEmpty());
		assertFalse(dataToPopulate.getLanguages().isEmpty());
	}

	@Test
	public void shouldPopulateTypeWhenNotExceptionalTypeAndSourceContainsTagFormat()
	{
		//@formatter:off
		Map<String, Object> sourceData = ImmutableMap.<String, Object>builder()
			  .put("test:format", "image/png")
			  .build();
		//@formatter:on

		amwayDamAssetDataPopulator.populate(sourceData, dataToPopulate);

		assertEquals(IMAGE, dataToPopulate.getType());
	}

	@Test
	public void shouldPopulateTypeWhenNotExceptionalTypeAndSourceDoesNotContainsTagFormatButContainsTagMimeType()
	{
		//@formatter:off
		Map<String, Object> sourceData = ImmutableMap.<String, Object>builder()
			  .put("test:MIMEtype", "image/png")
			  .build();
		//@formatter:on

		amwayDamAssetDataPopulator.populate(sourceData, dataToPopulate);

		assertEquals(IMAGE, dataToPopulate.getType());
	}

	@Test
	public void shouldPopulateTypeToDocumentWhenNotExceptionalTypeAndSourceDoesNotContainsTagFormatAndTagMimeType()
	{
		//@formatter:off
		Map<String, Object> sourceData = ImmutableMap.<String, Object>builder()
			  .put("test:not-format-tag", "image/png")
			  .build();
		//@formatter:on

		amwayDamAssetDataPopulator.populate(sourceData, dataToPopulate);

		assertEquals(DOCUMENT, dataToPopulate.getType());
	}

	@Test
	public void shouldPopulateExceptionalTypeWhenExceptionalTypesMapContainsSpecifiedMimeType()
	{
		amwayDamAssetDataPopulator.setAmwayDamExceptionalTypes(singletonMap("image/png", DOCUMENT));

		amwayDamAssetDataPopulator.populate(sourceData, dataToPopulate);

		assertEquals(DOCUMENT, dataToPopulate.getType());
	}

	@Test
	public void shouldPopulateMimeTypeWhenSourceContainsTagFormat()
	{
		//@formatter:off
		Map<String, Object> sourceData = ImmutableMap.<String, Object>builder()
			  .put("test:format", "image/png")
			  .build();
		//@formatter:on

		amwayDamAssetDataPopulator.populate(sourceData, dataToPopulate);

		assertEquals("image/png", dataToPopulate.getMimeType());
	}

	@Test
	public void shouldPopulateMimeTypeWhenSourceDoesNotContainsTagFormatButContainsTagMimeType()
	{
		//@formatter:off
		Map<String, Object> sourceData = ImmutableMap.<String, Object>builder()
			  .put("test:MIMEtype", "image/png")
			  .build();
		//@formatter:on

		amwayDamAssetDataPopulator.populate(sourceData, dataToPopulate);

		assertEquals("image/png", dataToPopulate.getMimeType());
	}

	@Test
	public void shouldNotPopulateMimeTypeWhenSourceDoesNotContainsTagFormatAndTagMimeType()
	{
		//@formatter:off
		Map<String, Object> sourceData = ImmutableMap.<String, Object>builder()
			  .put("test:not-mime-type-tag", "image/png")
			  .build();
		//@formatter:on

		amwayDamAssetDataPopulator.populate(sourceData, dataToPopulate);

		assertNull(dataToPopulate.getMimeType());
	}

	@Test
	public void shouldPopulateMcpIntoCountriesWhenAliasesSpecifiedAndSourceTagsContainsMcpTag()
	{
		//@formatter:off
		sourceData = ImmutableMap.<String, Object>builder()
				.put("test:tags", singletonList("mcp-tag:mcp"))
				.build();
		//@formatter:on

		amwayDamAssetDataPopulator.populate(sourceData, dataToPopulate);

		assertTrue(dataToPopulate.getCountries().contains("mcp"));
	}

	@Test
	public void shouldPopulateKeywordsWhenSourceContainsListOfKeywordValues()
	{
		List expectedKeywordsList = asList("key", "word");
		//@formatter:off
		sourceData = ImmutableMap.<String, Object>builder()
				.put("test:keywords", asList("key", "word"))
				.build();
		//@formatter:on

		amwayDamAssetDataPopulator.populate(sourceData, dataToPopulate);

		assertTrue(isEqualCollection(expectedKeywordsList, dataToPopulate.getKeyWords()));
	}

	@Test
	public void shouldPopulateKeywordsWhenSourceContainsKeywordString()
	{
		List expectedKeywordsList = Collections.singletonList("keyword");
		//@formatter:off
		sourceData = ImmutableMap.<String, Object>builder()
				.put("test:keywords", "keyword")
				.build();
		//@formatter:on

		amwayDamAssetDataPopulator.populate(sourceData, dataToPopulate);

		assertTrue(isEqualCollection(expectedKeywordsList, dataToPopulate.getKeyWords()));
	}
}