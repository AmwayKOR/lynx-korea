/**
 * 
 */
package com.amway.core.pos.dao;

import com.amway.core.model.AmwayPrinterModel;

import java.util.List;

/**
 * Data access to {@link AmwayPrinterModel}
 */
public interface AmwayPrinterDao
{
	/**
	 * @uid uid of the printer
	 * 
	 * @return the AmwayPrinterModel
	 */
	List<AmwayPrinterModel> getPrinter(String id);
}
