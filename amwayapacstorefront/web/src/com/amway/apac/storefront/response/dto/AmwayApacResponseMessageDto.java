/**
 *
 */
package com.amway.apac.storefront.response.dto;

/**
 * @author Badrun Bandi
 *
 */
public class AmwayApacResponseMessageDto
{
	private final String id;
	private final String code;
	private final String field;
	private final String message;

	/**
	 * @param id
	 * @param code
	 * @param field
	 * @param message
	 */
	public AmwayApacResponseMessageDto(final String id, final String code, final String field, final String message)
	{
		super();
		this.id = id;
		this.code = code;
		this.field = field;
		this.message = message;
	}

	/**
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @return the code
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * @return the field
	 */
	public String getField()
	{
		return field;
	}

	/**
	 * @return the message
	 */
	public String getMessage()
	{
		return message;
	}


}
