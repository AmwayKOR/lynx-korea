/**
 *
 */
package com.amway.amwayapacbackoffice.actions;

import de.hybris.platform.ordersplitting.model.StockLevelModel;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.zkoss.util.resource.Labels;
import org.zkoss.zul.Messagebox;

import com.amway.apac.core.backorder.service.AmwayApacBackOrderService;
import com.hybris.cockpitng.actions.ActionContext;
import com.hybris.cockpitng.actions.ActionResult;
import com.hybris.cockpitng.actions.CockpitAction;


/**
 *
 */
public class ReleaseBackorderAction implements CockpitAction<StockLevelModel, String>
{

	private static final Logger LOG = Logger.getLogger(ReleaseBackorderAction.class);

	@Resource
	AmwayApacBackOrderService amwayApacBackOrderService;

	/*
	 * Perform method to be called upon from release backorder button in stock level from back office. Releases the
	 * backorder associated with the selected stock levels.
	 *
	 * @see com.hybris.cockpitng.actions.CockpitAction#perform(com.hybris.cockpitng.actions.ActionContext)
	 *
	 * @param ActionContext<StockLevelModel>
	 *
	 * @return ActionResult<String>
	 */
	@Override
	public ActionResult<String> perform(final ActionContext<StockLevelModel> ctx)
	{
		ActionResult<String> result = null;

		final List<StockLevelModel> stockLevelList = new ArrayList<>();
		stockLevelList.add(ctx.getData());
		try
		{
			amwayApacBackOrderService.releaseBackOrdersForStockLevels(stockLevelList);
			result = new ActionResult<>(ActionResult.SUCCESS);
			result.setResultMessage(Labels.getLabel("backOrder.released.successfully"));
		}
		catch (final Exception e)
		{
			if (LOG.isDebugEnabled())
			{
				LOG.error("Error occured while releasing backorder" + e);
				result = new ActionResult<>(ActionResult.ERROR);
				result.setResultMessage(Labels.getLabel("backOrder.release.unsuccessful"));
			}
		}
		Messagebox.show(result.getResultMessage());
		return result;
	}

}
