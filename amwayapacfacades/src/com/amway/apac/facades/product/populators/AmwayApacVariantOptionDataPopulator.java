package com.amway.apac.facades.product.populators;

import static org.springframework.util.Assert.notNull;

import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.commercefacades.product.data.VariantOptionData;
import de.hybris.platform.commercefacades.product.data.VariantOptionQualifierData;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.jalo.order.price.PriceInformation;
import de.hybris.platform.variants.model.VariantProductModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.amway.facades.populators.AcceleratorVariantOptionDataPopulator;


/**
 * Overriding {@link AcceleratorVariantOptionDataPopulator} to update variant option qualifiers population.
 *
 * @author shubhamgoyal
 *
 */
public class AmwayApacVariantOptionDataPopulator extends AcceleratorVariantOptionDataPopulator
{

	/**
	 * Overriding OOTB implementation to update variant option qualifiers population.
	 */
	@Override
	public void populate(final VariantProductModel source, final VariantOptionData target)
	{
		notNull(source, "Parameter source cannot be null.");
		notNull(target, "Parameter target cannot be null.");

		if (source.getBaseProduct() != null)
		{
			final Collection<VariantOptionQualifierData> variantOptionQualifiers = new ArrayList<VariantOptionQualifierData>();

			populateVariantOptionQualifierData(source, variantOptionQualifiers);
			target.setVariantOptionQualifiers(variantOptionQualifiers);

			target.setCode(source.getCode());
			target.setUrl(getProductModelUrlResolver().resolve(source));
			target.setStock(getStockConverter().convert(source));

			final PriceDataType priceType;
			final PriceInformation info;
			if (CollectionUtils.isEmpty(source.getVariants()))
			{
				priceType = PriceDataType.BUY;
				info = getCommercePriceService().getWebPriceForProduct(source);
			}
			else
			{
				priceType = PriceDataType.FROM;
				info = getCommercePriceService().getFromPriceForProduct(source);
			}

			if (info != null)
			{
				final PriceData priceData = getPriceDataFactory().create(priceType,
						BigDecimal.valueOf(info.getPriceValue().getValue()), info.getPriceValue().getCurrencyIso());
				target.setPriceData(priceData);
			}
		}
	}

	/**
	 * Method to populate variant option qualifier data.
	 *
	 * @param source
	 * @param variantOptionQualifiers
	 */
	protected void populateVariantOptionQualifierData(final VariantProductModel source,
			final Collection<VariantOptionQualifierData> variantOptionQualifiers)
	{
		if ((StringUtils.isNotBlank(source.getVariantAttributeName1())) && (StringUtils.isNotBlank(source.getVariantAttribute1())))
		{
			final VariantOptionQualifierData variantOptionQualifier = new VariantOptionQualifierData();
			variantOptionQualifier.setQualifier(source.getVariantAttributeName1());
			variantOptionQualifier.setName(source.getVariantAttributeName1());
			variantOptionQualifier.setValue(source.getVariantAttribute1());
			variantOptionQualifiers.add(variantOptionQualifier);
			final MediaModel swatchImage = source.getOthers().iterator().next();
			if (null != swatchImage)
			{
				variantOptionQualifier.setImage(getImageConverter().convert(swatchImage));
			}
			if ((StringUtils.isNotBlank(source.getVariantAttributeName2()))
					&& (StringUtils.isNotBlank(source.getVariantAttribute2())))
			{
				final VariantOptionQualifierData variantOptionQualifier2 = new VariantOptionQualifierData();
				variantOptionQualifier2.setQualifier(source.getVariantAttributeName2());
				variantOptionQualifier2.setName(source.getVariantAttributeName2());
				variantOptionQualifier2.setValue(source.getVariantAttribute2());
				variantOptionQualifiers.add(variantOptionQualifier2);
			}
		}
	}

}
