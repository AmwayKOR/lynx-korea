package com.amway.apac.facades.cms.types.populator;

import de.hybris.platform.cmsfacades.data.ComponentTypeAttributeData;
import de.hybris.platform.cmsfacades.types.populator.CMSItemDropdownComponentTypeAttributePopulator;
import de.hybris.platform.core.model.type.AttributeDescriptorModel;


/**
 * Overriding OOTB {@link CMSItemDropdownComponentTypeAttributePopulator} to update the component type.
 */
public class AmwayApacCMSItemDropdownComponentTypeAttributePopulator extends CMSItemDropdownComponentTypeAttributePopulator
{
	/**
	 * custom cms item drop down type code
	 */
	private static final String AMWAY_APAC_CMS_ITEM_DROPDOWN = "AmwayApacCMSItemDropdown";

	/**
	 * Overriding OOTB implementation to update the type code of the cms item dropdown.
	 */
	@Override
	public void populate(final AttributeDescriptorModel source, final ComponentTypeAttributeData target)
	{
		super.populate(source, target);
		target.setCmsStructureType(AMWAY_APAC_CMS_ITEM_DROPDOWN);
	}
}
