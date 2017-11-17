/**
 *
 */
package com.amway.apac.storefront.forms;

import java.util.List;


/**
 * @author Badrun Bandi
 *
 */
public class AmwayApacTermForm
{
	private List<AmwayApacTerm> term;
	private Boolean verified;

	public List<AmwayApacTerm> getTerm()
	{
		return term;
	}

	public void setTerm(final List<AmwayApacTerm> term)
	{
		this.term = term;
	}

	public Boolean getVerified()
	{
		return verified;
	}

	public void setVerified(final Boolean verified)
	{
		this.verified = verified;
	}

}
