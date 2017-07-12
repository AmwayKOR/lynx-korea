package com.amway.integration.cis.dms.api.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;


/**
 * DmsFieldError POJO
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DmsFieldError
{

	@XmlAttribute
	private DmsField field;

	@XmlElement
	private DmsFieldErrorCode errorCode;

	public DmsFieldError()
	{
	}

	/**
	 * Constructor for DmsFieldError
	 *
	 * @param field
	 * @param errorCode
	 */
	public DmsFieldError(final DmsField field, final DmsFieldErrorCode errorCode)
	{
		this.field = (field == null) ? new DmsField() : field;
		this.errorCode = errorCode;
	}

	/**
	 * @return field
	 */
	public DmsField getField()
	{
		return this.field;
	}

	/**
	 * @param field the field to set
	 */
	public void setField(final DmsField field)
	{
		this.field = field;
	}

	/**
	 * @return errorCode
	 */
	public DmsFieldErrorCode getErrorCode()
	{
		return this.errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(final DmsFieldErrorCode errorCode)
	{
		this.errorCode = errorCode;
	}

	@Override
	public String toString()
	{
		return "FieldError [field=" + this.field + ", errorCode=" + this.errorCode + "]";
	}

}
