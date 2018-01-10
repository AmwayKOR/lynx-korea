package com.amway.integration.dam.strategy.impl;

import java.util.Collection;

import com.amway.integration.dam.data.AmwayDamRenditionData;
import com.amway.integration.dam.strategy.AmwayDamRenditionFilterStrategy;
import com.amway.core.annotations.AmwayBean;

/**
 * Dummy implementation of {@link AmwayDamRenditionFilterStrategy}.
 * Return same list of renditions as it consume.
 * If you need some filtration for renditions, override this strategy.
 */
@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamRenditionFilterStrategyImpl implements AmwayDamRenditionFilterStrategy
{
	@Override
	public Collection<AmwayDamRenditionData> getSuitableRenditions(Collection<AmwayDamRenditionData> renditions)
	{
		return renditions;
	}
}
