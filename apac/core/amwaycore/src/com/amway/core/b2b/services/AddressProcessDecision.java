/**
 *
 */
package com.amway.core.b2b.services;

import java.util.HashMap;
import java.util.Map;


/**
 * Returns an array containing the constants of this enum type, in the order they are declared.
 */
public enum AddressProcessDecision
{
	ACCEPT, REVIEW, REJECT, UNKNOWN;

	private String decisionString;
	private static final Map<String, AddressProcessDecision> LOOKUPMAP;

	static
	{
		LOOKUPMAP = new HashMap();



		for (final AddressProcessDecision dec : values())
		{
			LOOKUPMAP.put(dec.getDecisionString(), dec);
		}
	}

	/**
	 * @return the decisionstring
	 */
	public String getDecisionString()
	{
		return this.decisionString;
	}

	/**
	 * Returns an array containing the constants of this enum type, in the order they are declared.
	 *
	 * @param decisionKey
	 * @return decison
	 */
	public static AddressProcessDecision lookup(final String decisionKey)
	{
		AddressProcessDecision decision = LOOKUPMAP.get(decisionKey);
		if (decision == null)
		{
			decision = UNKNOWN;
		}
		return decision;
	}
}
