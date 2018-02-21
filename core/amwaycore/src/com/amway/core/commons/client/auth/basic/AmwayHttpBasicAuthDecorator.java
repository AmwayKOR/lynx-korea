/**
 *
 */
package com.amway.core.commons.client.auth.basic;

import com.hybris.commons.client.RestCallBuilder;
import com.hybris.commons.client.RestCallDecorator;
import org.glassfish.jersey.internal.util.Base64;
import java.io.UnsupportedEncodingException;


/**
 * Customized BasicAuthDecorator. Works only with ApacheHttpClientHandler & HTTPBasicAuthFilter
 *
 * **** removed due to corepluscommons Hybris 5.7 dependency
 */
public class AmwayHttpBasicAuthDecorator implements RestCallDecorator
{

	private final String user;
	private final String password;

	/**
	 * @param user
	 * @param password
	 */
	public AmwayHttpBasicAuthDecorator(final String user, final String password)
	{
		this.user = user;
		this.password = password;
	}

	public RestCallBuilder decorate(RestCallBuilder builder) {
		String authentication = null;
		try {
			authentication = "Basic " + new String(Base64.encode((user + ":" + password).getBytes()),"ASCII");
		} catch (UnsupportedEncodingException var4) {
			throw new RuntimeException(var4);
		}
		return builder.header("Authorization", authentication);
	}

}
