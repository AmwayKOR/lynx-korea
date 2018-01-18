package com.amway.apac.core.comparators;

import java.util.Comparator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.constants.AmwayapacCoreConstants;
import com.amway.apac.core.enums.AccountClassificationEnum;


/**
 * Comparator to compare 2 classification levels. Returns 1 if first classification parameter is higher than second.
 *
 * @author Shubham Goyal
 */
public class AmwayApacAccountClassificationComparator implements Comparator<AccountClassificationEnum>
{
	private Map<AccountClassificationEnum, Integer> amwayAccountClassificationRankMapping;

	/**
	 * Returns 1 if classification1 is higher than classification2
	 */
	@Override
	public int compare(final AccountClassificationEnum classification1, final AccountClassificationEnum classification2)
	{
		int decision = AmwayapacCoreConstants.NEGATIVE_ONE;
		if ((amwayAccountClassificationRankMapping.containsKey(classification1))
				&& (amwayAccountClassificationRankMapping.containsKey(classification2)))
		{
			final Integer classification1Rank = getAmwayAccountClassificationRankMapping().get(classification1);
			final Integer classification2Rank = getAmwayAccountClassificationRankMapping().get(classification2);
			decision = classification1Rank.compareTo(classification2Rank);
		}
		return decision;
	}

	/**
	 * @return the amwayAccountClassificationRankMapping
	 */
	public Map<AccountClassificationEnum, Integer> getAmwayAccountClassificationRankMapping()
	{
		return amwayAccountClassificationRankMapping;
	}

	/**
	 * @param amwayAccountClassificationRankMapping
	 *           the amwayAccountClassificationRankMapping to set
	 */
	@Required
	public void setAmwayAccountClassificationRankMapping(
			final Map<AccountClassificationEnum, Integer> amwayAccountClassificationRankMapping)
	{
		this.amwayAccountClassificationRankMapping = amwayAccountClassificationRankMapping;
	}
}
