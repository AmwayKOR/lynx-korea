package com.amway.apac.cockpits.label.providers;

import org.apache.commons.lang.LocaleUtils;
import org.apache.commons.lang.StringUtils;

import com.amway.apac.resourcecentre.model.media.AbstractAmwayAssetModel;

import de.hybris.platform.cockpit.services.label.AbstractModelLabelProvider;

/**
 * Provides the label for assets model
 */
public class AmwayApacAssetLabelProvider extends AbstractModelLabelProvider<AbstractAmwayAssetModel> {
	private static final String ASSET_NAME_ID_SEPARATOR = " - ";

	@Override
	protected String getItemLabel(final AbstractAmwayAssetModel asset) {
		return new StringBuilder(50).append(StringUtils.trimToEmpty(asset.getTitle())).append(ASSET_NAME_ID_SEPARATOR)
				.append(StringUtils.trimToEmpty(asset.getAssetId())).toString();
	}

	@Override
	protected String getItemLabel(final AbstractAmwayAssetModel asset, final String languageIso) {
		return new StringBuilder(50).append(StringUtils.trimToEmpty(asset.getTitle(LocaleUtils.toLocale(languageIso)))).append(ASSET_NAME_ID_SEPARATOR)
				.append(StringUtils.trimToEmpty(asset.getAssetId())).toString();
	}
	
	@Override
	protected String getIconPath(final AbstractAmwayAssetModel item) {
		return null;
	}

	@Override
	protected String getIconPath(final AbstractAmwayAssetModel item, final String languageIso) {
		return null;
	}

	@Override
	protected String getItemDescription(final AbstractAmwayAssetModel item) {
		return "";
	}

	@Override
	protected String getItemDescription(final AbstractAmwayAssetModel item, final String languageIso) {
		return "";
	}
}
