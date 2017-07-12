/**
 *
 */
package com.amway.core.message.service;

import java.util.Locale;



/**
 * Interface dedicated to find message for the given code
 */
public interface AmwayMessageService
{
	/**
	 * To find the message for the given code.
	 *
	 * @param code
	 * @return
	 */
	String findMessage(String code);

	/**
	 * Find message for given code and locale.
	 *
	 * @param code
	 *           the code
	 * @param locale
	 *           the locale
	 * @return the string
	 */
	String findMessage(String code, Locale locale);
}
