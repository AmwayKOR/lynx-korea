/**
 *
 */
package com.amway.core.register.services;

import java.util.HashMap;
import java.util.Map;


/**
 * Returns an array containing the constants of this enum type
 */
public enum RegistrationProcessDecision
{
	ACCEPT, REVIEW, REJECT, UNKNOWN;

	private String decisionString;
	private static final Map<String, RegistrationProcessDecision> LOOKUPMAP;

	static
	{
		LOOKUPMAP = new HashMap();



		for (final RegistrationProcessDecision dec : values())
		{
			LOOKUPMAP.put(dec.getDecisionString(), dec);
		}
	}






	/**
	 * @return decisionString
	 */
	public String getDecisionString()
	{
		return this.decisionString;
	}

	/**
	 * @param decisionKey
	 * @return decision
	 */
	public static RegistrationProcessDecision lookup(final String decisionKey)
	{
		RegistrationProcessDecision decision = LOOKUPMAP.get(decisionKey);
		if (decision == null)
		{
			decision = UNKNOWN;
		}
		return decision;
	}

}
