package com.amway.integration.cis.los.pojo;


import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface generated in the
 * com.amway.integration.cis.los.pojo package.
 */
@XmlRegistry
public class ObjectFactory
{
	private static final QName _TransactionGroup_QNAME = new QName("", "transactionGroup");

	/**
	 * @return TransactionGroup
	 */
	public TransactionGroup createTransactionGroup()
	{
		return new TransactionGroup();
	}

	/**
	 * @return Transaction
	 */
	public Transaction createTransaction()
	{
		return new Transaction();
	}

	/**
	 * @return TransactionList
	 */
	public TransactionList createTransactionList()
	{
		return new TransactionList();
	}

	/**
	 * Transition Group
	 *
	 * @param value
	 * @return JAXBElement
	 */
	@XmlElementDecl(namespace = "", name = "transactionGroup")
	public TransactionGroup createTransactionGroup(final TransactionGroup value)
	{
		return value;
	}
}
