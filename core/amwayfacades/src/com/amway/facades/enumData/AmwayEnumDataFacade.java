/**
 *
 */
package com.amway.facades.enumData;

import de.hybris.platform.core.HybrisEnumValue;
import java.util.List;
import de.hybris.platform.cmsfacades.data.EnumData;

/**
 * Facade to get enum data.
 *
 * @author rajatgoswami
 */
public interface AmwayEnumDataFacade
{

	/**
	 * Gets enum datas by class
	 *
	 * @param cnfe
	 * @return
	 */
	List<EnumData> getEnumValuesByClass(Class cnfe);

	/**
	 * Gets enum data by values
	 *
	 * @param enumValues
	 * @return
	 */
	List<EnumData> getEnumValues(final List<? extends HybrisEnumValue> enumValues);

}
