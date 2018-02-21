package com.amway.core.subscription.service;

import java.util.HashMap;


/**
 * Returns an array containing the constants of this enum type, in the order they are declared.
 */
public enum SubscriptionErrorCode
{
	MISSING, INVALID, UNKNOWN;
	private static HashMap LOOKUPMAP;

	static
	{
		LOOKUPMAP = new HashMap();

		for (final SubscriptionErrorCode err : values())
		{
			LOOKUPMAP.put(err.getErrorString(), err);
		}
	}


	public String getErrorString()
	{
		return this.getErrorString();
	}

	/**
	 * @param typeKey
	 * @return errorCode
	 */
	public static SubscriptionErrorCode lookup(final String typeKey)
	{
		SubscriptionErrorCode errorCode = (SubscriptionErrorCode) LOOKUPMAP.get(typeKey);
		if (errorCode == null)
		{
			errorCode = UNKNOWN;
		}
		return errorCode;
	}
}
