/**
 *
 */
package com.amway.apac.storefront.response.dto;

import java.util.List;


/**
 * @author Badrun Bandi
 *
 */
public class AmwayApacResponseDto<T>
{
	private final boolean success;
	private final List<AmwayApacResponseMessageDto> message;
	private final T data;

	public AmwayApacResponseDto(final boolean success, final List<AmwayApacResponseMessageDto> message, final T payload)
	{
		this.success = success;
		this.message = message;
		this.data = payload;
	}

	/**
	 * @return the status
	 */
	public boolean isSuccess()
	{
		return success;
	}

	/**
	 * @return the errors
	 */
	public List<AmwayApacResponseMessageDto> getMessage()
	{
		return message;
	}

	/**
	 * @return the data
	 */
	public T getData()
	{
		return data;
	}


}
