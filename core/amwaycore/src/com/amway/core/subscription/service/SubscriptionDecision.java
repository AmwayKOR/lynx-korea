package com.amway.core.subscription.service;

import java.util.HashMap;


/**
 * Returns an array containing the constants of this enum type, in the order they are declared.
 */
public enum SubscriptionDecision
{
	SUCCESS, FAILURE, UNKNOWN;
	final static HashMap LOOKUPMAP = new HashMap();

	static
	{
		for (final SubscriptionDecision dec : values())
		{
			LOOKUPMAP.put(dec.getDecisionString(), dec);
		}
	}

	/**
	 * @return SubscriptionDecision
	 */
	public SubscriptionDecision getDecisionString()
	{
		return SubscriptionDecision.SUCCESS;
	}

	/**
	 * @param decisionKey
	 * @return decision
	 */
	public static SubscriptionDecision lookup(final String decisionKey)
	{
		SubscriptionDecision decision = (SubscriptionDecision) LOOKUPMAP.get(decisionKey);
		if (decision == null)
		{
			decision = UNKNOWN;
		}
		return decision;
	}
}
