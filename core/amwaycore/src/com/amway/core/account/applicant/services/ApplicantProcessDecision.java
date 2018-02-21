package com.amway.core.account.applicant.services;

import java.util.HashMap;
import java.util.Map;


/**
 * Returns an array containing the constants of this enum type, in the order they are declared.
 */
public enum ApplicantProcessDecision
{
	ACCEPT, REVIEW, REJECT, UNKNOWN;

	private String decisionString;
	private static final Map<String, ApplicantProcessDecision> LOOKUPMAP;

	static
	{
		LOOKUPMAP = new HashMap();

		for (final ApplicantProcessDecision dec : values())
		{
			LOOKUPMAP.put(dec.getDecisionString(), dec);
		}
	}

	/**
	 * @return the decisionString
	 */
	public String getDecisionString()
	{
		return this.decisionString;
	}

	/**
	 * Returns an array containing the constants of this enum type, in the order they are declared.
	 *
	 * @param decisionKey
	 * @return the decision
	 */
	public static ApplicantProcessDecision lookup(final String decisionKey)
	{
		ApplicantProcessDecision decision = LOOKUPMAP.get(decisionKey);
		if (decision == null)
		{
			decision = UNKNOWN;
		}
		return decision;
	}

}
