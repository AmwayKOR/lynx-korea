/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.amway.core.dms.data.AccounttUsageList;
import com.amway.core.dms.data.BankAccountDetails;
import com.amway.core.dms.data.BankAccountDetailsResponseData;
import com.amway.integration.dms.services.AccountDetailOutput;
import com.amway.integration.dms.services.BankAccountDetailResponse;


/**
 * Populator implementation for {@link BankAccountDetailResponse} as source and {@link BankAccountDetails} as target
 * type.
 */
public class DmsBankAccountOutputPopulator extends AbstractDmsPopulator
		implements Populator<BankAccountDetailResponse, BankAccountDetails>
{
	private static final Logger LOG = Logger.getLogger(DmsBankAccountOutputPopulator.class);

	@Override
	public void populate(final BankAccountDetailResponse source, final BankAccountDetails target) throws ConversionException
	{
		if (CollectionUtils.isNotEmpty(source.getBankAccountList()))
		{
			final List<AccountDetailOutput> accountDetailOutputList = source.getBankAccountList();
			final List<BankAccountDetailsResponseData> bankAccountDetailsDataList = new ArrayList();
			for (final AccountDetailOutput accountDetailOutput : accountDetailOutputList)
			{
				final BankAccountDetailsResponseData bankAccountDetailsData = new BankAccountDetailsResponseData();
				bankAccountDetailsData.setBankAcctHolderName((accountDetailOutput.getBankAcctHolderName()));
				bankAccountDetailsData.setBankAcctId((accountDetailOutput.getBankAcctId()));
				bankAccountDetailsData.setBankAcctName((accountDetailOutput.getBankAcctName()));
				bankAccountDetailsData.setBankAcctNum((accountDetailOutput.getBankAcctNum()));
				bankAccountDetailsData.setBankAcctTypeCd((accountDetailOutput.getBankAcctTypeCd()));
				bankAccountDetailsData.setBankBranchCode((accountDetailOutput.getBankBranchCode()));
				bankAccountDetailsData.setCurrencyCd((accountDetailOutput.getCurrencyCd()));
				bankAccountDetailsData.setEffectiveEndDate(
						formatDate((accountDetailOutput.getEffectiveEndDate()), DMSDATEPATTERN, "dd/MM/yyyy"));
				bankAccountDetailsData.setEffectiveStartDate(
						formatDate((accountDetailOutput.getEffectiveStartDate()), DMSDATEPATTERN, "dd/MM/yyyy"));
				bankAccountDetailsData.setIssuingBankId((accountDetailOutput.getIssuingBankId()));
				bankAccountDetailsData.setPrimaryRoutingNum((accountDetailOutput.getPrimaryRoutingNum()));
				bankAccountDetailsData.setSecondaryRoutingNum((accountDetailOutput.getSecondaryRoutingNum()));

				final AccounttUsageList accountUsage = new AccounttUsageList();
				if ((accountDetailOutput.getAcctUsageList().get(0).getUseFlag()).equals("1"))
				{
					accountUsage.setUseFlag("Active");
				}
				else
				{
					accountUsage.setUseFlag("Deactive");
				}
				accountUsage.setAcctUseCode((accountDetailOutput.getAcctUsageList().get(0).getAcctUseCode()));
				bankAccountDetailsData.setAcctUsageList(accountUsage);
				bankAccountDetailsDataList.add(bankAccountDetailsData);
			}
			target.setBankAccountDetailsDataList(bankAccountDetailsDataList);
		}
	}
}
