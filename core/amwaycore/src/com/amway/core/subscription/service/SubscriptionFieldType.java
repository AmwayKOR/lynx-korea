/**
 *
 */
package com.amway.core.subscription.service;

import java.util.HashMap;


/**
 * Returns an array containing the constants of this enum type, in the order they are declared.
 */
public enum SubscriptionFieldType
{
	AFF_NO, IBO_NO, UNKNOWN;
	private static HashMap LOOKUPMAP;

	static
	{
		LOOKUPMAP = new HashMap();

		for (final SubscriptionFieldType field : values())
		{
			LOOKUPMAP.put(field.getTypeString(), field);
		}
	}

	public String getTypeString()
	{
		return this.getTypeString();
	}

	/**
	 * @param typeKey
	 * @return fieldType
	 */
	public static SubscriptionFieldType lookup(final String typeKey)
	{
		SubscriptionFieldType fieldType = (SubscriptionFieldType) LOOKUPMAP.get(typeKey);
		if (fieldType == null)
		{
			fieldType = UNKNOWN;
		}
		return fieldType;
	}
}
