package com.amway.integration.dam.util;

import static com.amway.integration.dam.constants.AmwayDamConstants.MIME_SEPARATOR;
import static com.amway.integration.dam.constants.AmwayDamConstants.TAGS_SEPARATOR;
import static java.util.stream.Collectors.toMap;
import static org.apache.commons.lang.StringUtils.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.amway.integration.dam.data.AmwayDamAssetTypeEnum;


public class AmwayDamTransformationUtil
{
	private AmwayDamTransformationUtil()
	{
	}

	public static <T> T getByAlias(Class<T> clazz, String alias, Map<String, Object> source, Map<String, String> aliasMap)
	{
		Object objectToConvert = source.get(aliasMap.get(alias));
		//@formatter:off
		return Optional.ofNullable(objectToConvert)
				.filter(clazz::isInstance)
				.map(clazz::cast)
				.orElse(null);
		//@formatter:on
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> getListByAlias(Class<T> itemClass, String alias, Map<String, Object> source, Map<String, String> aliasMap)
	{
		Object objectToConvert = source.get(aliasMap.get(alias));
		if (objectToConvert instanceof List)
		{
			return (List<T>) objectToConvert;
		}
		else if(itemClass.isInstance(objectToConvert))
		{
			return Collections.singletonList(itemClass.cast(objectToConvert));
		}

		return null;
	}

	public static void populateLocalizedProperty(Map<String, String> source, BiConsumer<String, Locale> consumer)
	{
		source.forEach((key, value) ->
		{
			Locale locale = new Locale.Builder().setLanguage(key).build();
			consumer.accept(value, locale);
		});
	}

	public static Map<String, String> populateMapByIsoCodes(Map<String, Object> source, Map<String, String> targetMap,
			Map<String, String> aliasMap, List<String> isoCodes)
	{
		Map<String, String> result = Optional.ofNullable(targetMap).orElse(new HashMap<>());
		//@formatter:off
		isoCodes.forEach(isoCode -> result.putIfAbsent(isoCode, getByAlias(String.class, isoCode, source, aliasMap)));
		return result.entrySet().stream()
				.filter(entry -> entry.getValue() != null)
				.collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
		//@formatter:off
	}

	public static void populateByTagNameWithSplit(String tagName, String attribute, Consumer<String> consumer) {
		if (isNotEmpty(tagName) && attribute.contains(tagName))
		{
			String country = substringAfter(attribute, TAGS_SEPARATOR);
			if (isNotEmpty(country))
			{
				consumer.accept(country);
			}
		}
	}

	public static void populateTypeByMimeType(String mimeType, Map<String, AmwayDamAssetTypeEnum> exceptionalTypes, Consumer<AmwayDamAssetTypeEnum> consumer) {
		AmwayDamAssetTypeEnum type;
		if(exceptionalTypes.containsKey(mimeType)) {
			type = exceptionalTypes.get(mimeType);
		} else {
			type = AmwayDamAssetTypeEnum.fromString(substringBefore(mimeType, MIME_SEPARATOR));
		}
		consumer.accept(type);
	}
}
