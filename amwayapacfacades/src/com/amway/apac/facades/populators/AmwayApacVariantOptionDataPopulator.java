package com.amway.apac.facades.populators;

import com.amway.apac.core.jalo.AmwayApacSecondVariantProduct;
import com.amway.apac.core.model.AmwayApacFirstVariantProductModel;
import com.amway.apac.core.model.AmwayApacSecondVariantProductModel;
import com.amway.apac.core.model.VariantModel;
import com.amway.facades.populators.AcceleratorVariantOptionDataPopulator;
import de.hybris.platform.commercefacades.product.data.VariantOptionData;
import de.hybris.platform.commercefacades.product.data.VariantOptionQualifierData;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by MY020221 on 10/6/2017.
 */
public class AmwayApacVariantOptionDataPopulator extends AcceleratorVariantOptionDataPopulator{

    @Override
    public void populate(final VariantProductModel source, final VariantOptionData target){
        super.populate(source, target);
        final Collection<VariantOptionQualifierData> variantOptionQualifiers = new ArrayList<VariantOptionQualifierData>();
        VariantModel variant = null;
        if(source instanceof AmwayApacSecondVariantProductModel){
            variant = ((AmwayApacSecondVariantProductModel)source).getSecondVariant();
        } else if(source instanceof  AmwayApacFirstVariantProductModel){
            variant = ((AmwayApacFirstVariantProductModel)source).getFirstVariant();
        }
        for (final VariantOptionQualifierData variantOptionQualifier : target.getVariantOptionQualifiers())
        {
            //Add Variant Name and Value
            variantOptionQualifier.setName(variant.getName());
            variantOptionQualifier.setValue(variant.getValue());
            if(variant.getImage() != null){
                variantOptionQualifier.setImage(getImageConverter().convert(variant.getImage()));
            }
        }
    }
}
