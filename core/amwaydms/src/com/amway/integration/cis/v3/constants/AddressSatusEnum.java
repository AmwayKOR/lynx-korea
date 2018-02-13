package com.amway.integration.cis.v3.constants;

public enum AddressSatusEnum
{
	// @formatter:off
	VALID("Valid"),
	INVALID("Invalid"),
	NOT_VALIDATED("NotValidated"),
	DELETED("Deleted");
	// @formatter:on

	public final String code;

	AddressSatusEnum(String code)
	{
		this.code = code;
	}
}
