/**
 *
 */
package com.amway.core.register.services;

import java.util.HashMap;
import java.util.Map;


/**
 * Returns an array containing the constants of this enum type.
 */
public enum RegisterErrorCode
{
	MISSING, INVALID, TRUNCATED, CORRECTED, UNKNOWN;
	private String errorString;
	private static final Map<String, RegisterErrorCode> LOOKUPMAP;

	static
	{
		LOOKUPMAP = new HashMap();



		for (final RegisterErrorCode err : values())
		{
			LOOKUPMAP.put(err.getErrorString(), err);
		}
	}






	/**
	 * @return errorString
	 */
	public String getErrorString()
	{
		return this.errorString;
	}

	/**
	 * @param typeKey
	 * @return errorCode
	 */
	public static RegisterErrorCode lookup(final String typeKey)
	{
		RegisterErrorCode errorCode = LOOKUPMAP.get(typeKey);
		if (errorCode == null)
		{
			errorCode = UNKNOWN;
		}
		return errorCode;
	}

}
