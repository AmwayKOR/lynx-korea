/**
 *
 */
package com.amway.facades.populators;

import de.hybris.platform.commercefacades.user.converters.populator.CustomerPopulator;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Required;


/**
 * @author Aliaksei_Sery
 */
public class AmwayCustomerPopulator extends CustomerPopulator
{
	private UserService userService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.commercefacades.user.converters.populator.CustomerPopulator#populate(de.hybris.platform.core
	 * .model.user.CustomerModel, de.hybris.platform.commercefacades.user.data.CustomerData)
	 */
	@Override
	public void populate(final CustomerModel source, final CustomerData target)
	{
		super.populate(source, target);

		// set list of user groups
		target.setUserGroups(groupsToList(source.getAllGroups()));
		target.setCustomerID(source.getCustomerID());
	}

	/**
	 * @param allGroups
	 * @return
	 */
	private static List<String> groupsToList(final Set<PrincipalGroupModel> allGroups)
	{
		final List<String> groups = new ArrayList();
		if (allGroups != null && !allGroups.isEmpty())
		{
			for (final Iterator<PrincipalGroupModel> it = allGroups.iterator(); it.hasNext(); )
			{
				final PrincipalGroupModel groupModel = it.next();
				groups.add(groupModel.getUid());
			}
		}

		return groups;
	}


	/**
	 * @return the userService
	 */
	public UserService getUserService()
	{
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}


}
