/**
 *
 */
package com.amway.integration.cis.los.pojo;


import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * TransactionList
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transactionList", propOrder = { "transaction" })
public class TransactionList
{
	protected List<Transaction> transaction;

	/**
	 * @return transaction
	 */
	public List<Transaction> getTransaction()
	{
		if (this.transaction == null)
		{
			this.transaction = new ArrayList();
		}
		return this.transaction;
	}
}
