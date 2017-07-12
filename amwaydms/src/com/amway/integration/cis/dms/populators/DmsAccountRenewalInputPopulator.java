/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.dms.renewal.data.AccountRenewalRequestData;
import com.amway.integration.dms.services.AccountRenewalRequest;


/**
 * populate the account renewal data
 */
public class DmsAccountRenewalInputPopulator extends AbstractDmsPopulator
		implements Populator<AccountRenewalRequestData, AccountRenewalRequest>
{
	private String renewalCd;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final AccountRenewalRequestData source, final AccountRenewalRequest target) throws ConversionException
	{
		target.setAboNum(source.getAboNum());
		target.setSalesPlanAff(source.getSalesPlanAff());
		target.setAccountSubTypeCd(source.getAccountSubTypeCd());
		target.setInvoiceNumber(source.getInvoiceNumber());
		target.setLegalConsentFlg(source.getLegalConsentFlg());
		target.setRenewalDate(formatDate(source.getRenewalDate(), DMSDATEPATTERN, DMSDATEPATTERN));
		target.setRenewalWithGroupFlg(source.getRenewalWithGroupFlg());
		target.setRenewalCd(renewalCd);
	}

	/**
	 * @return the renewalCd
	 */
	public String getRenewalCd()
	{
		return renewalCd;
	}

	/**
	 * @param renewalCd the renewalCd to set
	 */
	public void setRenewalCd(final String renewalCd)
	{
		this.renewalCd = renewalCd;
	}

}
