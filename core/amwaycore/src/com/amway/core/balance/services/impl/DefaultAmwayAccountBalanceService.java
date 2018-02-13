/**
 *
 */
package com.amway.core.balance.services.impl;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.store.services.BaseStoreService;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.amway.core.balance.services.AmwayAccountBalanceService;
import com.amway.core.data.CommonRequestFieldsData;
import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.CreateBalanceRequestData;
import com.amway.core.dms.data.GetBalanceResponseData;
import com.amway.core.dms.service.DmsService;
import com.amway.core.model.AmwayAccountModel;
import com.amway.core.service.AmwayAccountCommerceService;


/**
 * Default Implementation for Amway Account Balance Service
 */
public class DefaultAmwayAccountBalanceService implements AmwayAccountBalanceService
{
	private DmsService<CreateBalanceRequestData, CommonResponseFieldsData> createBalanceService;
	private DmsService<CommonRequestFieldsData, GetBalanceResponseData> getBalanceService;
	private BaseStoreService baseStoreService;
	private AmwayAccountCommerceService amwayAccountCommerceService;

	/**
	 * {@link com.amway.core.balance.services.AmwayAccountBalanceService#creditAccountBalance(de.hybris.platform.payment.model.PaymentTransactionEntryModel, java.lang.String, java.lang.String)}
	 */
	@Override
	public CommonResponseFieldsData creditAccountBalance(final PaymentTransactionEntryModel paymentTransactionEntryModel,
														 final String balanceTypeCd, final String referenceNumber)
	{
		final CreateBalanceRequestData requestData = new CreateBalanceRequestData();
		final AbstractOrderModel abstractOrderModel = paymentTransactionEntryModel.getPaymentTransaction().getOrder();
		requestData.setCurrencyCd(paymentTransactionEntryModel.getCurrency().getIsocode());
		requestData.setTxSourceCd("HB");
		requestData.setTxTypeCd(getTxnTypeCode(paymentTransactionEntryModel));
		requestData.setSourcRefNum(referenceNumber);
		requestData.setClientCntryCd(abstractOrderModel.getSite().getDefaultCountry().getIsocode());
		requestData.setBalanceTypeCd(balanceTypeCd);
		requestData.setSalesPlanAff(abstractOrderModel.getStore().getAffiliateNumber());
		requestData.setBalanceAmount(String.valueOf(paymentTransactionEntryModel.getAmount().doubleValue()));
		final AmwayAccountModel unit = abstractOrderModel.getAccount();
		if (unit != null)
		{
			requestData.setAboNum(unit.getCode());
			requestData.setLoggedInAccountId(unit.getCode());
		}
		return getCreateBalanceService().process(requestData);
	}

	private String getTxnTypeCode(final PaymentTransactionEntryModel paymentTransactionEntryModel)
	{
		switch (paymentTransactionEntryModel.getType().getCode())
		{
			case "CAPTURE":
				return "adj";

			case "EXTERNAL_CAPTURE":
				return "adj";

			case "REFUND_FOLLOW_ON":
				return "Rtn";

			case "REFUND_STANDALONE":
				return "Rtn";

			case "CANCEL":
				return "adj";

			default:
				return "adj";
		}
	}

	/**
	 * {@link com.amway.core.balance.services.AmwayAccountBalanceService#debitAccountBalance(de.hybris.platform.core.model.order.AbstractOrderModel, java.math.BigDecimal, java.lang.String)}
	 */
	@Override
	public CommonResponseFieldsData debitAccountBalance(final AbstractOrderModel orderModel, final BigDecimal amount,
														final String balanceTypeCd)
	{
		final CreateBalanceRequestData requestData = new CreateBalanceRequestData();
		requestData.setCurrencyCd(orderModel.getCurrency().getIsocode());
		requestData.setTxSourceCd("HB");
		requestData.setTxTypeCd("Ord");
		requestData.setSourcRefNum(orderModel.getCode());
		final String[] orderCode = StringUtils.split(orderModel.getCode(), "-");

		for (int i = 0; i < orderCode.length; i++)
		{
			if (i == 1)
			{
				requestData.setSourcRefNum(orderCode[i]);
			}
		}
		requestData.setBalanceAmount("-" + amount.toString());
		requestData.setClientCntryCd(orderModel.getSite().getDefaultCountry().getIsocode());
		requestData.setBalanceTypeCd(balanceTypeCd);
		if (getBaseStoreService().getCurrentBaseStore() != null)
		{
			requestData.setSalesPlanAff(getBaseStoreService().getCurrentBaseStore().getAffiliateNumber());
		}
		else
		{
			requestData.setSalesPlanAff(orderModel.getStore().getAffiliateNumber());
		}
		final AmwayAccountModel unit = orderModel.getAccount();

		if (unit != null)
		{
			requestData.setAboNum(unit.getCode());
			requestData.setLoggedInAccountId(unit.getCode());
		}

		return getCreateBalanceService().process(requestData);
	}

	/**
	 * {@link com.amway.core.balance.services.AmwayAccountBalanceService#getAccountBalance(de.hybris.platform.core.model.order.AbstractOrderModel)}
	 */
	@Override
	public GetBalanceResponseData getAccountBalance(final AbstractOrderModel orderModel)
	{
		final CommonRequestFieldsData requestData = new CommonRequestFieldsData();
		requestData.setClientCntryCd(orderModel.getSite().getDefaultCountry().getIsocode());
		if (getBaseStoreService().getCurrentBaseStore() != null)
		{
			requestData.setSalesPlanAff(getBaseStoreService().getCurrentBaseStore().getAffiliateNumber());
		}
		else
		{
			requestData.setSalesPlanAff(orderModel.getStore().getAffiliateNumber());
		}
		final AmwayAccountModel unit = orderModel.getAccount();

		if (unit != null)
		{
			requestData.setAboNum(unit.getCode());
			requestData.setLoggedInAccountId(unit.getCode());
		}
		return getGetBalanceService().process(requestData);
	}

	/**
	 * @return createBalanceService
	 */
	public DmsService<CreateBalanceRequestData, CommonResponseFieldsData> getCreateBalanceService()
	{
		return createBalanceService;
	}

	/**
	 * @param createBalanceService the createBalanceService to set
	 */
	public void setCreateBalanceService(final DmsService<CreateBalanceRequestData, CommonResponseFieldsData> createBalanceService)
	{
		this.createBalanceService = createBalanceService;
	}

	/**
	 * @return baseStoreService
	 */
	public BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	/**
	 * @param baseStoreService the baseStoreService to set
	 */
	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

	/**
	 * @return amwayAccountCommerceService
	 */
	public AmwayAccountCommerceService getAmwayAccountCommerceService()
	{
		return amwayAccountCommerceService;
	}

	/**
	 * @param amwayAccountCommerceService the amwayAccountCommerceService to set
	 */
	public void setAmwayAccountCommerceService(final AmwayAccountCommerceService amwayAccountCommerceService)
	{
		this.amwayAccountCommerceService = amwayAccountCommerceService;
	}

	/**
	 * @return getBalanceService
	 */
	public DmsService<CommonRequestFieldsData, GetBalanceResponseData> getGetBalanceService()
	{
		return getBalanceService;
	}

	/**
	 * @param getBalanceService the getBalanceService to set
	 */
	public void setGetBalanceService(final DmsService<CommonRequestFieldsData, GetBalanceResponseData> getBalanceService)
	{
		this.getBalanceService = getBalanceService;
	}
}
