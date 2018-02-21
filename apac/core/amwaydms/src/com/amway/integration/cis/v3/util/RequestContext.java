package com.amway.integration.cis.v3.util;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.store.BaseStoreModel;

import java.io.Serializable;

import com.amway.core.data.CommonRequestFieldsData;
import com.amway.core.model.AmwayAccountModel;
import com.amway.integration.cis.v3.swagger.OrderRequest;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


/**
 * Common request context.
 */
public class RequestContext<T extends Serializable> implements Serializable
{
	@JsonTypeInfo(
			use = JsonTypeInfo.Id.NAME,
			include = JsonTypeInfo.As.PROPERTY,
			property = "type")
	@JsonSubTypes({ @JsonSubTypes.Type(value = OrderRequest.class, name = "OrderRequest") })
	private T requestData;

	private String salesPlanAff;

	private String aboNum;

	private String partyId;

	private String countryIso;

	public RequestContext()
	{
	}

	public RequestContext(AmwayAccountModel account, CustomerModel customer, BaseStoreModel store) {
		this.salesPlanAff = store.getAffiliateNumber();
		this.aboNum = account.getCode();
		this.partyId = customer.getCustomerID();
	}

	public RequestContext(String salesPlanAff, String aboNum, String partyId)
	{
		this.salesPlanAff = salesPlanAff;
		this.aboNum = aboNum;
		this.partyId = partyId;
	}

	public T getRequestData()
	{
		return requestData;
	}

	public void setRequestData(T requestData)
	{
		this.requestData = requestData;
	}

	public String getAboNum()
	{
		return aboNum;
	}

	public void setAboNum(String aboNum)
	{
		this.aboNum = aboNum;
	}

	public String getPartyId()
	{
		return partyId;
	}

	public void setPartyId(String partyId)
	{
		this.partyId = partyId;
	}

	public String getSalesPlanAff()
	{
		return salesPlanAff;
	}

	public void setSalesPlanAff(String salesPlanAff)
	{
		this.salesPlanAff = salesPlanAff;
	}

	public String getCountryIso()
	{
		return countryIso;
	}

	public void setCountryIso(String countryIso)
	{
		this.countryIso = countryIso;
	}

	public static CommonRequestFieldsData createCommonRequestFieldsData(RequestContext restoredRequest)
	{
		CommonRequestFieldsData r = new CommonRequestFieldsData();
		r.setSalesPlanAff(restoredRequest.getSalesPlanAff());
		r.setAboNum(restoredRequest.getAboNum());
		r.setPartyId(restoredRequest.getPartyId());
		return r;
	}

	public static <T extends Serializable> String addContextParams(RequestContext<T> request, String uri)
	{
		if (request.getSalesPlanAff() != null) {
			uri = uri.replaceAll("\\{" + "salesPlanAff" + "\\}", request.getSalesPlanAff());
		}
		if (request.getAboNum() != null) {
			uri = uri.replaceAll("\\{" + "aboNum" + "\\}", request.getAboNum());
		}
		if (request.getPartyId() != null) {
			uri = uri.replaceAll("\\{" + "partyId" + "\\}", request.getPartyId());
		}
		return uri;
	}
}