/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.apache.commons.lang.StringUtils;

import com.amway.core.dms.data.AddUpdatePartyAddressRequestData;
import com.amway.core.dms.data.AddressRequestData;
import com.amway.core.dms.data.UsageRequestData;
import com.amway.integration.dms.services.UpdatePartyAddress;
import com.amway.integration.dms.services.UpdatePartyAddressRequest;
import com.amway.integration.dms.services.UsageData;


/**
 * Populator implementation for {@link AddUpdatePartyAddressRequestData} as source and {@link UpdatePartyAddressRequest}
 * as target type
 */
public class DmsUpdateAddressInputPopulator extends AbstractDmsPopulator
		implements Populator<AddUpdatePartyAddressRequestData, UpdatePartyAddressRequest>
{

	@Override
	public void populate(final AddUpdatePartyAddressRequestData source, final UpdatePartyAddressRequest target)
			throws ConversionException
	{
		final AddressRequestData addressRequestData = source.getPartyAddress();
		final UpdatePartyAddress updatePartyAddress = new UpdatePartyAddress();


		updatePartyAddress.setSalesPlanAff(addressRequestData.getSalesPlanAff());
		updatePartyAddress.setAboNum(addressRequestData.getAboNum());
		updatePartyAddress.setPartyId(convertToJAXBString("partyId", addressRequestData.getPartyId()));
		updatePartyAddress.setAddrLineTwo(addressRequestData.getCompliment());
		//AT-1991 Make street name to 42 chars and append building number(apartment)
		updatePartyAddress.setAddrStreet(StringUtils.substring(addressRequestData.getAddressLine1(), 0, 42) + (StringUtils
				.isNotBlank(addressRequestData.getNumber()) ? ", " + addressRequestData.getNumber() : StringUtils.EMPTY));
		updatePartyAddress.setAddrLineThree(addressRequestData.getNeighbour());
		updatePartyAddress.setAddrLineFour(addressRequestData.getAttention());
		updatePartyAddress.setCityName(addressRequestData.getCityName());
		updatePartyAddress.setCntryCd(addressRequestData.getCntryCd());
		updatePartyAddress.setCountyName("");
		updatePartyAddress.setAddrDeliveryTypeCd("MailingAddress");
		updatePartyAddress.setTaxJursidictionCd("");
		updatePartyAddress.setGeoCd("");
		updatePartyAddress.setLatitude("");
		updatePartyAddress.setLongitude("");
		updatePartyAddress.setPostalBoxNum("");
		updatePartyAddress.setPostalCd(addressRequestData.getPostalCd());
		updatePartyAddress.setStateCd(addressRequestData.getState());
		updatePartyAddress.setWarehouseCd("");
		updatePartyAddress.setTzOffSet("");
		updatePartyAddress.setLanguageCd(addressRequestData.getLanguageCd());
		updatePartyAddress.setCharSetCd("");
		updatePartyAddress
				.setContactPointTypeCd(convertToJAXBString("contactPointTypeCd", addressRequestData.getContactPointTypeCd()));
		updatePartyAddress.setContactPointName(convertToJAXBString("contactPointName", addressRequestData.getContactPointName()));
		updatePartyAddress.setValidationResultOverRideFlg(addressRequestData.getValidationResultOverRideFlg());
		updatePartyAddress
				.setAddressValidatedDate(formatDate(addressRequestData.getAddressValidatedDate(), "ddMMyyyy", DMSDATEPATTERN));
		updatePartyAddress.setValidationResultCd(addressRequestData.getValidationResultCd());

		for (final UsageRequestData UsageRequestData : addressRequestData.getUsageData())
		{
			final UsageData usageData = new UsageData();
			usageData.setContactPointPurposeCd(UsageRequestData.getContactPointPurposeCd());
			usageData.setPrimaryFlag(UsageRequestData.getPrimaryFlag());
			final String careOf = (UsageRequestData.getCareOf()).length() > 50 ?
					UsageRequestData.getCareOf().substring(0, 50) :
					UsageRequestData.getCareOf();
			usageData.setCareOf(careOf);
			updatePartyAddress.getUsageDataList().add(usageData);
		}
		target.setUpdatePartyAddress(updatePartyAddress);

	}
}
