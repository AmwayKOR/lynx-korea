package com.amway.integration.cis.dms.api.model;

/**
 * DmsField POJO
 */
public class DmsField
{
	private final String fieldId;

	/**
	 * Constructor DmsField
	 */
	public DmsField()
	{
		this.fieldId = "UNKNOWN";
	}

	/**
	 * @param fieldId the fieldId to set
	 */
	public DmsField(final String fieldId)
	{
		this.fieldId = fieldId;
	}

	/**
	 * @return fieldId
	 */
	public String getFieldId()
	{
		return this.fieldId;
	}

}
