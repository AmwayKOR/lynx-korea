package com.amway.integration.cis.dms.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.hybris.cis.api.model.CisDecision;
import com.hybris.cis.api.model.CisResult;


/**
 * DmsResult POJO
 */
@XmlRootElement(name = "dmsResult")
@XmlAccessorType(XmlAccessType.FIELD)
public class DmsResult extends CisResult
{

	@XmlElementWrapper(name = "fieldErrors")
	@XmlElement(name = "field")
	private List<DmsFieldError> fieldErrors = new ArrayList();

	public DmsResult()
	{
	}

	/**
	 * DmsResult Constructor
	 *
	 * @param decision
	 */
	public DmsResult(final CisDecision decision)
	{
		super(decision);
	}

	/**
	 * @return fieldErrors
	 */
	public List<DmsFieldError> getFieldErrors()
	{
		return this.fieldErrors;
	}

	/**
	 * @param fieldErrors the fieldErrors to set
	 */
	public void setFieldErrors(final List<DmsFieldError> fieldErrors)
	{
		this.fieldErrors = fieldErrors;
	}

	@Override
	public String toString()
	{
		final StringBuilder value = new StringBuilder();
		value.append("AvsResult [id=").append(getId()).append(", decision=").append(getDecision()).append("]");
		return value.toString();
	}
}
