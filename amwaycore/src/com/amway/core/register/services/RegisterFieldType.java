/**
 *
 */
package com.amway.core.register.services;

import java.util.HashMap;
import java.util.Map;


/**
 * Returns an array containing the constants of this enum type
 */
public enum RegisterFieldType
{
	CPF_NUMBER, FIRST_NAME, LAST_NAME, ADDRESS_LINE1, ADDRESS_LINE2, CITY, REGION, ZIP_CODE, COUNTRY, UNKNOWN;
	private String typeString;
	private static final Map<String, RegisterFieldType> LOOKUPMAP;

	static
	{
		LOOKUPMAP = new HashMap();



		for (final RegisterFieldType field : values())
		{
			LOOKUPMAP.put(field.getTypeString(), field);
		}
	}






	/**
	 * @return typeString
	 */
	public String getTypeString()
	{
		return this.typeString;
	}

	/**
	 * @param typeKey
	 * @return fieldType
	 */
	public static RegisterFieldType lookup(final String typeKey)
	{
		RegisterFieldType fieldType = LOOKUPMAP.get(typeKey);
		if (fieldType == null)
		{
			fieldType = UNKNOWN;
		}
		return fieldType;
	}


}
