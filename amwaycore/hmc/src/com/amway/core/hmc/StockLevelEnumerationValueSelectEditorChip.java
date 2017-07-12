package com.amway.core.hmc;

import de.hybris.platform.basecommerce.enums.InStockStatus;
import de.hybris.platform.hmc.attribute.EnumerationValueSelectEditorChip;
import de.hybris.platform.hmc.webchips.Chip;
import de.hybris.platform.hmc.webchips.DisplayState;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.jalo.type.ComposedType;


/**
 * @author Aryadna_Birchanka
 */
public class StockLevelEnumerationValueSelectEditorChip extends EnumerationValueSelectEditorChip
{
	public StockLevelEnumerationValueSelectEditorChip(DisplayState displayState, Chip parent, ComposedType enumerationType)
	{
		super(displayState, parent, enumerationType);
	}

	@Override
	public boolean isEditable()
	{
		if(this.getAttributeChip()!=null)
		{
			Object value = this.getAttributeChip().getEditor().getValue();
			if (value!=null && value instanceof EnumerationValue)
			{
				EnumerationValue enumerationType = (EnumerationValue) value;
				if (enumerationType.getCode().equals(InStockStatus.NOLONGERAVAILABLE.getCode()))
				{
					this.getAttributeChip().setEditable(false);
				}
			}
		}
		return super.isEditable();
	}

}
