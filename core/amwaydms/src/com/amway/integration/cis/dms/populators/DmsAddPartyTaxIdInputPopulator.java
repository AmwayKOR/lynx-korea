/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.List;

import com.amway.core.data.TaxDetailsData;
import com.amway.integration.dms.services.AddTaxIdInput;
import com.amway.integration.dms.services.AddTaxIdRequest;
import com.amway.integration.dms.services.TaxIdDetail;


/**
 * Populator implementation for {@link TaxDetailsData} as source and {@link AddTaxIdRequest} as target type.
 */
public class DmsAddPartyTaxIdInputPopulator extends AbstractDmsPopulator implements Populator<TaxDetailsData, AddTaxIdRequest>
{

	@Override
	public void populate(final TaxDetailsData source, final AddTaxIdRequest target) throws ConversionException
	{

		final List<AddTaxIdInput> taxList = new ArrayList<AddTaxIdInput>();
		final AddTaxIdInput addtaxInput = new AddTaxIdInput();
		addtaxInput.setAboNum(source.getAboNum());
		addtaxInput.setSalesPlanAff(source.getSalesPlanAff());

		final TaxIdDetail taxDetail = new TaxIdDetail();
		taxDetail.setTaxId(source.getTaxId());
		taxDetail.setTaxTypeCd(source.getTaxIdType());
		taxDetail.setPartyId(source.getPartyId());
		taxDetail.setTaxTypeCd(source.getTaxIdType());

		addtaxInput.setTaxIdAdd(taxDetail);
		taxList.add(addtaxInput);
		target.getAddTaxIdList().addAll(taxList);
	}
}
