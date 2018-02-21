package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.amway.core.dms.data.AddUpdatePartyAddressRequestData;
import com.amway.core.dms.data.AddressRequestData;
import com.amway.core.dms.data.UsageRequestData;
import com.amway.integration.dms.services.AddPartyAddress;
import com.amway.integration.dms.services.AddPartyAddressRequest;
import com.amway.integration.dms.services.UsageData;



/**
 * Populator implementation for {@link AddUpdatePartyAddressRequestData} as source and {@link AddPartyAddressRequest} as
 * target type.
 */
public class DmsSaveAddressInputPopulator extends AbstractDmsPopulator
		implements Populator<AddUpdatePartyAddressRequestData, AddPartyAddressRequest>
{
	private BaseStoreService baseStoreService;

	@Override
	public void populate(final AddUpdatePartyAddressRequestData source, final AddPartyAddressRequest target)
			throws ConversionException
	{

		final AddPartyAddress addPartyAddress = new AddPartyAddress();
		final AddressRequestData addressRequestData = source.getPartyAddress();

		addPartyAddress.setSalesPlanAff(addressRequestData.getSalesPlanAff());
		addPartyAddress.setAboNum(addressRequestData.getAboNum());
		addPartyAddress.setPartyId(convertToJAXBString("partyId", addressRequestData.getPartyId()));
		addPartyAddress.setAddrLineTwo(addressRequestData.getCompliment());
		//AT-1991 Make street name to 42 chars and append building number(apartment)
		addPartyAddress.setAddrStreet(StringUtils.substring(addressRequestData.getAddressLine1(), 0, 42) + (StringUtils
				.isNotBlank(addressRequestData.getNumber()) ? ", " + addressRequestData.getNumber() : StringUtils.EMPTY));
		addPartyAddress.setAddrLineThree(addressRequestData.getNeighbour());
		addPartyAddress.setAddrLineFour(addressRequestData.getAttention());
		addPartyAddress.setCityName(addressRequestData.getCityName());
		addPartyAddress.setCntryCd(addressRequestData.getCntryCd());
		addPartyAddress.setCountyName("");
		addPartyAddress.setAddrDeliveryTypeCd("MailingAddress");
		addPartyAddress.setTaxJursidictionCd("");
		addPartyAddress.setGeoCd("");
		addPartyAddress.setLatitude("");
		addPartyAddress.setLongitude("");
		addPartyAddress.setPostalBoxNum("");
		addPartyAddress.setPostalCd(addressRequestData.getPostalCd());
		addPartyAddress.setStateCd(addressRequestData.getState());
		addPartyAddress.setWarehouseCd("");
		addPartyAddress.setTzOffSet("");
		addPartyAddress.setLanguageCd(addressRequestData.getLanguageCd());
		addPartyAddress.setCharSetCd("");

		//TODO: Remove hardcoded values
		addPartyAddress
				.setContactPointTypeCd(convertToJAXBString("contactPointTypeCd", addressRequestData.getContactPointTypeCd()));
		addPartyAddress.setContactPointName(convertToJAXBString("contactPointName", addressRequestData.getContactPointName()));
		addPartyAddress.setValidationResultOverRideFlg(addressRequestData.getValidationResultOverRideFlg());
		addPartyAddress.setAddressValidatedDate(addressRequestData.getAddressValidatedDate());
		addPartyAddress.setValidationResultCd(addressRequestData.getValidationResultCd());
		final List<UsageData> usageDataList = new ArrayList<UsageData>();
		for (final UsageRequestData UsageRequestData : addressRequestData.getUsageData())
		{
			final UsageData usageData = new UsageData();
			usageData.setContactPointPurposeCd(UsageRequestData.getContactPointPurposeCd());
			usageData.setPrimaryFlag(UsageRequestData.getPrimaryFlag());
			final String careOf = (UsageRequestData.getCareOf()).length() > 50 ?
					UsageRequestData.getCareOf().substring(0, 50) :
					UsageRequestData.getCareOf();
			usageData.setCareOf(careOf);
			usageDataList.add(usageData);
		}
		addPartyAddress.getUsageDataList().addAll(usageDataList);

		addPartyAddress.setValidationResultOverRideFlg(addressRequestData.getValidationResultOverRideFlg());
		addPartyAddress
				.setAddressValidatedDate(formatDate(addressRequestData.getAddressValidatedDate(), "MMddyyyy", DMSDATEPATTERN));
		addPartyAddress.setValidationResultCd(addressRequestData.getValidationResultCd());
		target.setAddPartyAddress(addPartyAddress);
	}

	/**
	 * @return baseStoreService
	 */
	public BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	/**
	 * @param baseStoreService the baseStoreService
	 */
	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

}
