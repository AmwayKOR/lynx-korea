/**
 *
 */
package com.amway.core.account.hierarchy.service;

import java.util.Collection;

import com.amway.core.facades.account.los.data.LosHierarchyData;



/**
 * Interface dedicated to find account for the given uid
 */
public interface AmwayAccountHierarchyService
{
	/**
	 * @param bonusPeriod
	 * @param aboNumber
	 * @return hierarchy of the down line along with basic info
	 */
	Collection<LosHierarchyData> getAccountHierarchy(final String bonusPeriod, final String aboNumber);
}