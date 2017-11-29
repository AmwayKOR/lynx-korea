package com.amway.apac.storefront.forms;

/**
 * @author Badrun Bandi
 *
 *         Registration Single Term.
 */
public class AmwayApacTerm
{
	private Boolean termAccepted;
	private String termsComponentUid;

	/**
	 * @return the termAccepted
	 */
	public Boolean getTermAccepted()
	{
		return termAccepted;
	}

	/**
	 * @param termAccepted
	 *           the termAccepted to set
	 */
	public void setTermAccepted(final Boolean termAccepted)
	{
		this.termAccepted = termAccepted;
	}

	/**
	 * @return the termsComponentUid
	 */
	public String getTermsComponentUid()
	{
		return termsComponentUid;
	}

	/**
	 * @param termsComponentUid
	 *           the termsComponentUid to set
	 */
	public void setTermsComponentUid(final String termsComponentUid)
	{
		this.termsComponentUid = termsComponentUid;
	}

}
