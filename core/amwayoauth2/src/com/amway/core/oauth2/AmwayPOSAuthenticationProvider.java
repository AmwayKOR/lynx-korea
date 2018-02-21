package com.amway.core.oauth2;

import com.amway.core.model.AmwayTerminalModel;
import com.amway.core.oauth2.constants.Amwayoauth2Constants;
import com.amway.core.pos.service.AmwayPOSService;
import de.hybris.platform.commerceservices.model.user.StoreEmployeeGroupModel;
import de.hybris.platform.core.Registry;
import de.hybris.platform.jalo.JaloConnection;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.user.LoginToken;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.jalo.user.UserManager;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.spring.security.CoreAuthenticationProvider;
import de.hybris.platform.spring.security.CoreUserDetails;
import de.hybris.platform.storelocator.model.PointOfServiceModel;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class AmwayPOSAuthenticationProvider extends CoreAuthenticationProvider
{
	private static final Logger logger = Logger.getLogger(AmwayPOSAuthenticationProvider.class.getName());

	private final UserDetailsChecker postAuthenticationChecks = new DefaultPostAuthenticationChecks();

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "amwayPOSService")
	private AmwayPOSService amwayPOSService;

	public AmwayPOSAuthenticationProvider(){}

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException
	{
		final ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

		final String terminalMacAddress = attr.getRequest().getParameter(Amwayoauth2Constants.POS.PARAM_MAC_ADDRESS);

		final String username = authentication.getPrincipal() == null ? "NONE_PROVIDED" : authentication.getName();

		final UserDetails userDetails = this.retrieveUser(username);

		AmwayTerminalModel terminalModel = null;

		if (StringUtils.isNotEmpty(terminalMacAddress))
		{
			terminalModel = amwayPOSService.getPOSTerminalByMacAddress(terminalMacAddress);
		}

		if (Registry.hasCurrentTenant() && JaloConnection.getInstance().isSystemInitialized())
		{
			try
			{
				if (StringUtils.isNotBlank(terminalMacAddress) && terminalModel != null)
				{
					final PointOfServiceModel posRequested = terminalModel.getPointOfService();
					// fetch the employee store
					final List<StoreEmployeeGroupModel> storeEmployeeGroups = userService
							.getAllUserGroupsForUser(userService.getUserForUID(username), StoreEmployeeGroupModel.class).stream()
							.filter(s -> s.getUid().equalsIgnoreCase(Amwayoauth2Constants.POS.EMPLOYEE_ROLE_GROUP))
							.collect(Collectors.toList());

					// this list should contain the cashier or supervisor roles
					// only.
					if (CollectionUtils.isNotEmpty(storeEmployeeGroups))
					{
						final List<PointOfServiceModel> posList = storeEmployeeGroups.stream().map(StoreEmployeeGroupModel::getStore)
								.collect(Collectors.toList());

						if (CollectionUtils.isNotEmpty(posList) && !posList.contains(posRequested))
						{
							throw new BadCredentialsException("Access Restricted.");
						}
					}
					else
					{
						// if this list is null,it means user is not a cashier
						// neither a supervisor,so not allowed to login.
						throw new BadCredentialsException("Access Restricted.");
					}
				}

			}
			catch (final UsernameNotFoundException var6)
			{
				throw new BadCredentialsException(
						messages.getMessage("CoreAuthenticationProvider.badCredentials", "Incorrect Username/ Password"), var6);
			}

			this.getPreAuthenticationChecks().check(userDetails);
			final User user = UserManager.getInstance().getUserByLogin(userDetails.getUsername());
			final Object credential = authentication.getCredentials();
			if (credential instanceof String)
			{
				if (!user.checkPassword((String) credential))
				{
					throw new BadCredentialsException(
							messages.getMessage("CoreAuthenticationProvider.badCredentials", "Incorrect Username/ Password"));
				}
			}
			else
			{
				if (!(credential instanceof LoginToken))
				{
					throw new BadCredentialsException(
							messages.getMessage("CoreAuthenticationProvider.badCredentials", "Bad credentials"));
				}

				if (!user.checkPassword((LoginToken) credential))
				{
					throw new BadCredentialsException(
							messages.getMessage("CoreAuthenticationProvider.badCredentials", "Bad credentials"));
				}
			}

			additionalAuthenticationChecks(userDetails, (AbstractAuthenticationToken) authentication);
			postAuthenticationChecks.check(userDetails);
			JaloSession.getCurrentSession().setUser(user);
			logger.info("User "+username +" authenticated successfully.");
			return createSuccessAuthentication(authentication, userDetails);
		}
		else
		{
			return createSuccessAuthentication(authentication, new CoreUserDetails("systemNotInitialized", "systemNotInitialized", true, false, true, true, Collections.EMPTY_LIST, (String)null));
		}
	}

	private class DefaultPostAuthenticationChecks implements UserDetailsChecker
	{
		private DefaultPostAuthenticationChecks()
		{
		}

		public void check(final UserDetails user)
		{
			if (!user.isCredentialsNonExpired())
			{
				throw new CredentialsExpiredException(AmwayPOSAuthenticationProvider.this.messages
						.getMessage("CoreAuthenticationProvider.credentialsExpired", "User credentials have expired"));
			}
		}
	}
}
