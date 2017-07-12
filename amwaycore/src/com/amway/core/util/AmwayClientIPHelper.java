/**
 *
 */
package com.amway.core.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


/**
 * gets the IP from where the request has originated,usedd in dms services
 */
public class AmwayClientIPHelper
{
	private static final Logger LOG = Logger.getLogger(AmwayClientIPHelper.class);

	/**
	 * get the ip address where the request has originated.
	 *
	 * @param request
	 * @return clientIp
	 */
	public static String getClientIpAddr(final HttpServletRequest request)
	{
		String clientIp = request.getHeader("True-Client-IP");
		LOG.debug("Captured Ip for request True-Client-IP : " + clientIp);
		if (StringUtils.isEmpty(clientIp))
		{
			clientIp = request.getHeader("X-Forwarded-For");
			LOG.debug("Captured Ip for request X-Forwarded-For : " + clientIp);
		}
		if (StringUtils.isEmpty(clientIp) || "unknown".equalsIgnoreCase(clientIp))
		{
			clientIp = request.getHeader("Proxy-Client-IP");
			LOG.debug("Captured Ip for request Proxy-Client-IP : " + clientIp);
		}
		if (StringUtils.isEmpty(clientIp) || "unknown".equalsIgnoreCase(clientIp))
		{
			clientIp = request.getHeader("WL-Proxy-Client-IP");
			LOG.debug("Captured Ip for request WL-Proxy-Client-IP : " + clientIp);
		}
		if (StringUtils.isEmpty(clientIp) || "unknown".equalsIgnoreCase(clientIp))
		{
			clientIp = request.getHeader("HTTP_CLIENT_IP");
			LOG.debug("Captured Ip for request HTTP_CLIENT_IP : " + clientIp);
		}
		if (StringUtils.isEmpty(clientIp) || "unknown".equalsIgnoreCase(clientIp))
		{
			clientIp = request.getHeader("HTTP_X_FORWARDED_FOR");
			LOG.debug("Captured Ip for request HTTP_X_FORWARDED_FOR : " + clientIp);
		}
		if (StringUtils.isEmpty(clientIp) || "unknown".equalsIgnoreCase(clientIp))
		{
			clientIp = request.getRemoteAddr();
			LOG.debug("Captured Ip for request RemoteAddr : " + clientIp);
		}

		if (StringUtils.isNotEmpty(clientIp) && StringUtils.contains(clientIp, ','))
		{
			clientIp = StringUtils.substringAfterLast(clientIp, ",");
		}
		LOG.info("Captured Ip for request is : " + clientIp);
		return clientIp;
	}
}
