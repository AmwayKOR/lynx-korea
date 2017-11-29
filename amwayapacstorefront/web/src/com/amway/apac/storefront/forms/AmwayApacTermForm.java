package com.amway.apac.storefront.forms;

import java.util.List;


/**
 * @author Badrun Bandi
 *
 *         Register Terms Form
 */
public class AmwayApacTermForm
{
	private List<AmwayApacTerm> term;
	private Boolean verified;


	/**
	 * @return the term
	 */
	public List<AmwayApacTerm> getTerm()
	{
		return term;
	}

	/**
	 * @param term
	 *           the term to set
	 */
	public void setTerm(final List<AmwayApacTerm> term)
	{
		this.term = term;
	}

	/**
	 * @return the verified
	 */
	public Boolean getVerified()
	{
		return verified;
	}

	/**
	 * @param verified
	 *           the verified to set
	 */
	public void setVerified(final Boolean verified)
	{
		this.verified = verified;
	}



}
