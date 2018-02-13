<%@ taglib prefix="address" tagdir="/WEB-INF/tags/responsive/address"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ attribute name="addressData" required="false" type="de.hybris.platform.commercefacades.user.data.AddressData" %>

<span class="primary-text mailing-address__title">Shipping address</span>
<span class="secondary-text mailing-address__line-one">${fn:escapeXml(addressData.line1)}</span>
<span class="secondary-text mailing-address__line-two"><c:if test="${not empty fn:escapeXml(addressData.line2)}">${fn:escapeXml(addressData.line2)}</c:if></span>
<span class="secondary-text mailing-address__line-three"><c:if test="${not empty fn:escapeXml(addressData.line3)}">${fn:escapeXml(addressData.line3)}</c:if></span>
<span class="secondary-text mailing-address__city">${fn:escapeXml(addressData.town)}</span>
<div>${fn:escapeXml(addressData.region.name)}</div>
<span class="secondary-text mailing-address__zip">${fn:escapeXml(addressData.postalCode)}</span>
<span class="secondary-text mailing-address__phone">${fn:escapeXml(addressData.phone)}</span>
<span class="secondary-text mailing-address__email">${fn:escapeXml(addressData.email)}</span>
<button type="button" class="btn-edit btn btn-primary" data-address-id="${fn:escapeXml(addressData.id)}"
	data-address-countryISOCode="${fn:escapeXml(addressData.country.isocode)}"
	onclick="ACC.billingshipping.populateFormUponEditAddress(this);">Edit</button>