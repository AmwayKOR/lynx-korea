/**
 *
 */
package com.amway.core.account.restrictions;

import java.util.List;

/**
 */
public interface BusinessRestrictionList
{
	/**
	 * Get the list of restrictions.
	 *
	 * @return the populators.
	 */
	List<BusinessRestriction> getRestrictions();

	/**
	 * Set the list of restrictions.
	 *
	 * @param restrictions
	 *           the restrictions
	 */
	void setRestrictions(List<BusinessRestriction> restrictions);
}
