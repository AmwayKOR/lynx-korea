package com.amway.apac.storefront.forms;

/**
 * @author Ashish Sabal
 *
 */
public class AmwayApacNotificationEntryForm
{
	private String checkedMessagesCodes;
	private String messageStatus;

	/**
	 * @return the checkedMessagesCodes
	 */
	public String getCheckedMessagesCodes()
	{
		return checkedMessagesCodes;
	}

	/**
	 * @param checkedMessagesCodes
	 *           the checkedMessagesCodes to set
	 */
	public void setCheckedMessagesCodes(final String checkedMessagesCodes)
	{
		this.checkedMessagesCodes = checkedMessagesCodes;
	}

	/**
	 * @return the messageStatus
	 */
	public String getMessageStatus()
	{
		return messageStatus;
	}

	/**
	 * @param messageStatus
	 *           the messageStatus to set
	 */
	public void setMessageStatus(final String messageStatus)
	{
		this.messageStatus = messageStatus;
	}
}
