package com.amway.core.hmc;

import de.hybris.platform.hmc.attribute.DateEditorChip;
import de.hybris.platform.hmc.webchips.Chip;
import de.hybris.platform.hmc.webchips.DisplayState;


/**
 * @author Aryadna_Birchanka
 */
public class ProductOfflineDateEditorChip extends DateEditorChip
{
	public ProductOfflineDateEditorChip(DisplayState displayState, Chip parent)
	{
		super(displayState, parent);
	}

	@Override
	public boolean isEditable()
	{
		if(this.getAttributeChip().getEditor().getValue()!=null){
			this.getAttributeChip().setEditable(false);
		}
		return super.isEditable();
	}
}
