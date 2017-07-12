package com.amway.amwayfulfillment.drop.job;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amway.amwayfulfillment.drop.AmwayDropProcessHelper;
import com.amway.amwayfulfillment.drop.services.AmwayDropSupportService;


/**
 * Job queries orders performs drop process for orders which are ready for drop.
 * Logic related to determine set of orders is encapsulated in {@link AmwayDropSupportService#getOrdersForJob(CronJobModel)}.
 */
public class AmwayDropProcessJob extends AbstractJobPerformable<CronJobModel>
{
	private static final Logger LOG = LoggerFactory.getLogger(AmwayDropProcessJob.class);
	/**
	 * Maximum number of job repetition for huge amount of orders.
	 */
	private static int MAX_REPEAT = 10;

	private AmwayDropSupportService dropSupportService;

	@Override
	public PerformResult perform(final CronJobModel jobModel)
	{
		int i = 0;
		while (doDrop(jobModel) && (i < MAX_REPEAT))
		{
			i++;
		}
		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	private boolean doDrop(final CronJobModel jobModel)
	{
		final List<OrderModel> orders = dropSupportService.getOrdersForJob(jobModel);

		boolean haveSomethingToDo = !CollectionUtils.isEmpty(orders);
		if (haveSomethingToDo)
		{
			final List<String> codes = AmwayDropProcessHelper.getOrderCodes(orders);
			LOG.info("Prepare for drop. Orders: {}", StringUtils.join(codes, ", "));
			dropSupportService.doDrop(orders, jobModel);
		}
		return haveSomethingToDo;
	}


	public AmwayDropSupportService getDropSupportService()
	{
		return dropSupportService;
	}

	public void setDropSupportService(AmwayDropSupportService dropSupportService)
	{
		this.dropSupportService = dropSupportService;
	}

}
