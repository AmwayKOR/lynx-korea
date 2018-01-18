package com.amway.apac.core.account.service;

import com.amway.apac.core.enums.AccountClassificationEnum;


/**
 * Service layer for account classification.
 *
 * @author Shubham Goyal
 */
public interface AmwayApacAccountClassificationService
{
	/**
	 * Returns true if the session user has higher classification than passed reference classification
	 *
	 * @param referenceClassification
	 *           Classification level code of the entity (Like Asset or Pre launch config).
	 * @return true if session user has higher classification than reference.
	 * @throws IllegalArgumentException
	 *            if referenceClassification is null.
	 */
	boolean checkUserClassification(final AccountClassificationEnum referenceClassification);
}
