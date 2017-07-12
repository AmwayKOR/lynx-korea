package com.amway.core.order.interceptor.impl;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.order.interceptors.DefaultOrderPrepareInterceptor;
import de.hybris.platform.order.strategies.ordercloning.OrderPartOfMembersCloningStrategy;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PersistenceOperation;
import de.hybris.platform.servicelayer.internal.model.impl.ModelValueHistory;
import de.hybris.platform.servicelayer.model.ItemModelContextImpl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;


/**
 * Automatically clones the order's elements:
 * <ul>
 * <li>{@link AbstractOrderModel#PAYMENTINFOS},</li>
 * </ul>
 * and sets the <b>clones</b> as actual values.<br>
 * If the cloned elements are clones themselves, the interceptor removes them.
 */
public class DefaultAmwayOrderPrepareInterceptor extends DefaultOrderPrepareInterceptor
{
	private OrderPartOfMembersCloningStrategy cloningStrategy;

	/**
	 * prepare interceptor for order
	 *
	 * @param model
	 * @param ctx
	 * @throws InterceptorException
	 */
	@Override
	public void onPrepare(final Object model, final InterceptorContext ctx) throws InterceptorException
	{
		if (model instanceof OrderModel)
		{
			super.onPrepare(model, ctx);
			final OrderModel order = (OrderModel) model;
			final ItemModelContextImpl context = (ItemModelContextImpl) order.getItemModelContext();
			final ModelValueHistory history = context.getValueHistory();

			//check if payment info was changed to a non null value
			if (ctx.isModified(order, OrderModel.PAYMENTINFOS))
			{
				final Set<PaymentInfoModel> paymentInfos = new LinkedHashSet();
				if (order.getPaymentInfos() != null)
				{
					for (final PaymentInfoModel paymentInfo : order.getPaymentInfos())
					{
						//if the new payment info needs cloning, we need to clone it to be a part of this order's contract:
						if (getCloningStrategy().paymentInfoNeedsCloning(paymentInfo, order))
						{
							final PaymentInfoModel paymentInfoClone = getCloningStrategy().clonePaymentInfoForOrder(paymentInfo, order);
							paymentInfos.add(paymentInfoClone);
							//changing the info on transactions to clone version
							for (final PaymentTransactionModel paymentTransactions : order.getPaymentTransactions())
							{
								if (paymentInfo.equals(paymentTransactions.getInfo()))
								{
									paymentTransactions.setInfo(paymentInfoClone);
								}
								if (paymentTransactions.getInfo() == null)
								{
									for (final PaymentTransactionEntryModel pte : paymentTransactions.getEntries())
									{
										if (PaymentTransactionType.FRAUD_DECISION.equals(pte.getType()) && "REVIEW"
												.equals(pte.getTransactionStatus()))
										{
											order.setFraudulent(Boolean.TRUE);
										}
									}

								}
							}
							ctx.registerElement(paymentInfoClone);
						}
						else
						{
							paymentInfos.add(paymentInfo);
						}
					}
				}
				order.setPaymentInfos(paymentInfos);

				if (!ctx.isNew(order))
				{
					registerPaymentInfoForRemovalIfNeeded(ctx, order, history);
				}
			}

			if (ctx.isModified(order,
					OrderModel.PAYMENTTRANSACTIONS)) //  (AR Txns are made internally)
			{
				final Set<PaymentInfoModel> paymentInfos = order.getPaymentInfos();
				if (order.getPaymentTransactions() != null)
				{
					for (final PaymentTransactionModel paymentTransactions : order.getPaymentTransactions())
					{
						final PaymentInfoModel paymentInfo = paymentTransactions.getInfo();
						if (!paymentInfos.contains(paymentInfo))
						{
							if (getCloningStrategy().paymentInfoNeedsCloning(paymentInfo, order))
							{
								final PaymentInfoModel paymentInfoClone = getCloningStrategy()
										.clonePaymentInfoForOrder(paymentInfo, order);
								paymentTransactions.setInfo(paymentInfoClone);
								ctx.registerElement(paymentInfoClone);
							}
						}
					}
				}
			}
		}
	}

	private void registerPaymentInfoForRemovalIfNeeded(final InterceptorContext ctx, final OrderModel order,
			final ModelValueHistory history)
	{
		final Set<PaymentInfoModel> oldPaymentInfos = (Set<PaymentInfoModel>) history.getOriginalValue(OrderModel.PAYMENTINFOS);
		final Set<PaymentInfoModel> paymentInfos = order.getPaymentInfos();
		if (CollectionUtils.isNotEmpty(oldPaymentInfos))
		{
			for (final PaymentInfoModel oldPaymentInfo : oldPaymentInfos)
			{
				if (!paymentInfos.contains(oldPaymentInfo) && oldPaymentInfo.getDuplicate().booleanValue())
				{
					ctx.registerElementFor(oldPaymentInfo, PersistenceOperation.DELETE);
				}
			}
		}
	}

	/**
	 * @return cloningStrategy
	 */
	public OrderPartOfMembersCloningStrategy getCloningStrategy()
	{
		return cloningStrategy;
	}

	/**
	 * @param cloningStrategy
	 *           the cloningStrategy to set
	 */
	public void setCloningStrategy(final OrderPartOfMembersCloningStrategy cloningStrategy)
	{
		this.cloningStrategy = cloningStrategy;
	}
}
