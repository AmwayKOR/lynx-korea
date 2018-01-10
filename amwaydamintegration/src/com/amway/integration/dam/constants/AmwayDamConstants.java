package com.amway.integration.dam.constants;

@SuppressWarnings("PMD")
public class AmwayDamConstants extends GeneratedAmwayDamConstants
{

	private AmwayDamConstants()
	{

	}

	private static final String DAM_LOG_PREFIX = "dam.integration.";

	/**
	 * Default limit for fetching events query.
	 */
	public static final int DEFAULT_LIMIT = 50;
	public static final int WITHOUT_SIZE = -1;

	public static final String BASIC_AUTH_HEADER = "Basic ";

	public static final String EXTERNAL_DELIMITER = "-";
	public static final String INTERNAL_DELIMITER = "_";
	public static final String ASSET_KEYWORD_DELIMITER = ",";
	public static final String PATH_DELIMITER = "/";

	public static final String ERROR_EVENT_EMPTY_PATH = DAM_LOG_PREFIX + "event.emptypath";
	public static final String ERROR_EVENT_EMPTY_OPERATION = DAM_LOG_PREFIX + "event.emptyoperation";
	public static final String ERROR_EVENT_WRONG_OPERATION = DAM_LOG_PREFIX + "event.wrongoperation";
	public static final String ERROR_ASSET_DATA_NOT_EXIST = DAM_LOG_PREFIX + "asset.notexists";
	public static final String ERROR_ASSET_ITEM_NOT_EXIST = DAM_LOG_PREFIX + "remove.notexists";
	public static final String ERROR_ASSET_ALREADY_REMOVED = DAM_LOG_PREFIX + "remove.removed";
	public static final String ERROR_DUPLICATE_ASSET = DAM_LOG_PREFIX + "create.duplicate.assetid";
	public static final String ERROR_CREATE_REMOVED = DAM_LOG_PREFIX + "create.removed";
	public static final String ERROR_UPDATE_WEB_ASSET_TYPE = DAM_LOG_PREFIX + "update.webassettype";
	public static final String ERROR_UNKNOWN_WEB_ASSET_TYPE = DAM_LOG_PREFIX + "unknown.webassettype";
	public static final String ERROR_UNKNOWN_ASSET_TYPE = DAM_LOG_PREFIX + "unknown.assettype";
	public static final String ERROR_COUNTRY_NOT_EXIST = DAM_LOG_PREFIX + "asset.country.notexists";
	public static final String ERROR_DAM_UNEXPECTED = DAM_LOG_PREFIX + "unexpected";

	public static final String WARNING_HTTP_STATUS_RESPONSE = DAM_LOG_PREFIX + "response.error";
	public static final String WARNING_UNKNOWN_MEDIA_FORMAT = DAM_LOG_PREFIX + "unknown.mediaformat";
	public static final String WARNING_ASSET_NOT_EXIST = DAM_LOG_PREFIX + "update.notexists";

	public static final String MIME_SEPARATOR = "/";
	public static final String TAGS_SEPARATOR = ":";

	public static final String ATTRIBUTE_TAGS = "tags";
	public static final String ATTRIBUTE_ASSET_ID = "assetId";
	public static final String ATTRIBUTE_ASSET_MODIFIED = "assetModified";
	public static final String ATTRIBUTE_TYPE = "type";
	public static final String ATTRIBUTE_KEYWORDS = "keyWords";
	public static final String ATTRIBUTE_DEFAULT_ASSET = "defaultAsset";
	public static final String ATTRIBUTE_DURATION = "duration";
	public static final String ATTRIBUTE_VIDEO_ID = "videoId";
	public static final String ATTRIBUTE_CONTENT = "content";
	public static final String ATTRIBUTE_HEIGHT = "height";
	public static final String ATTRIBUTE_WIGHT = "width";
	public static final String ATTRIBUTE_FORMAT = "format";

	public static final String TAG_NAME_COUNTRIES = "countries";
	public static final String TAG_NAME_LANGUAGES = "languages";
	public static final String TAG_NAME_MCP = "MCP";

	public static final String ENGLISH_ISO = "EN";

	public static final String CATALOG_VERSION_STAGED = "Staged";

	public static final String PARAM_ASSET_TYPE = "assetType";
	public static final String PARAM_MIN_HEIGHT = "minHeight";
	public static final String PARAM_MAX_HEIGHT = "maxHeight";
	public static final String PARAM_MIN_WIDTH = "minWidth";
	public static final String PARAM_MAX_WIDTH = "maxWidth";

}
