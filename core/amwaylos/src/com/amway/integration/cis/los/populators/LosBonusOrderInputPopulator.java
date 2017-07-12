package com.amway.integration.cis.los.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.List;

import com.amway.core.los.data.BonusOrderRequestData;
import com.amway.core.los.data.TransactionData;
import com.amway.integration.cis.los.pojo.Transaction;
import com.amway.integration.cis.los.pojo.TransactionGroup;


/**
 * Input populator for Los Bonus Order.
 */
public class LosBonusOrderInputPopulator implements Populator<BonusOrderRequestData, TransactionGroup>
{
	@Override
	public void populate(final BonusOrderRequestData source, final TransactionGroup target) throws ConversionException
	{
		if (source != null)
		{
			target.setAffiliateCode(source.getAffiliateCode());
			target.setIsoCountryCode(source.getIsoCountryCode());
			target.setId(source.getId());
			target.setSourceApplication(source.getSourceApplication());
			target.setTimestamp(source.getTimestamp());
			final List<TransactionData> transactions = source.getTransactions();
			final List<Transaction> transactionList = new ArrayList();
			for (final TransactionData data : transactions)
			{
				final Transaction transaction = new Transaction();
				transaction.setAboId(data.getAboId());
				if (null != data.getAmount())
				{
					transaction.setAmount(data.getAmount().doubleValue());
				}

				transaction.setBonusPeriod(data.getBonusPeriod());
				transaction.setBusinessVolume(data.getBusinessVolume() != null ? data.getBusinessVolume().doubleValue() : 0);
				transaction.setChannel(data.getChannel());
				transaction.setIsoCurrencyCode(data.getIsoCurrencyCode());
				transaction.setPointValue(data.getPointValue() != null ? data.getPointValue().doubleValue() : 0);

				transaction.setProfit(data.getProfit() != null ? data.getProfit().doubleValue() : 0);
				transaction.setReferenceId(data.getReferenceId());
				transaction.setReferenceInvoice(data.getReferenceId());
				transaction.setWarehouseCode(data.getWarehouseCode());
				transactionList.add(transaction);
			}
			target.setTransactions(transactionList);
		}
	}
}
