<%@ taglib prefix="address" tagdir="/WEB-INF/tags/responsive/address"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>

<common:globalMessages/>
    <div class="primary-address-section container-fluid">
        <div class="header">
            <span class="primary-text"> Primary addresses</span>
        </div>
        <div class="main row">
        		<c:forEach items="${addressData}" var="address">
        			<c:if test="${address.defaultAddress}">
        					<div class="address-container col-xs-12 col-md-6">
                   <div class="form-container">
                           <div class="mailing-address">
                           	   <span class="primary-text mailing-address__title">Shipping address</span>
                                <span class="secondary-text mailing-address__line-one">${fn:escapeXml(address.line1)}&nbsp;</span>
                                <span class="secondary-text mailing-address__line-two"><c:if test="${not empty fn:escapeXml(address.line2)}">${fn:escapeXml(address.line2)}</c:if></span>
							    <span class="secondary-text mailing-address__line-three"><c:if test="${not empty fn:escapeXml(address.line3)}">${fn:escapeXml(address.line3)}</c:if></span>
                                <span class="secondary-text mailing-address__city">${fn:escapeXml(address.town)}</span>
                                <div>
                                    ${fn:escapeXml(address.region.name)}
                                </div>
                                <span class="secondary-text mailing-address__zip">${fn:escapeXml(address.postalCode)}</span>
                                <span class="secondary-text mailing-address__phone">${fn:escapeXml(address.phone)}</span>
                             			   <span class="secondary-text mailing-address__email">${fn:escapeXml(address.email)}</span>
                            			   
                               <button type="button"
                                       class="btn-edit btn btn-primary"
                                       data-address-id="${fn:escapeXml(address.id)}"
                                       data-address-countryISOCode="${fn:escapeXml(address.country.isocode)}"
                                       onclick="ACC.billingshipping.populateFormUponEditAddress(this);"> Edit
                               </button>
                           </div>
                           <c:set var="addressID" scope="request" value="${fn:escapeXml(address.id)}"/>
                           <c:set var="countryISO" scope="request" value="${fn:escapeXml(address.country.isocode)}"/>
                           <div class="error-mailing-address"></div>
                           <div class="mailing-address-edit">
                               <h4>SHIPPING ADDRESS</h4>
                               <div class="addressEdit">
                               		
                               </div>
                               
                           </div>
                    </div>
                   
               </div>
        			</c:if>
        		</c:forEach>
            
            
        </div>
    </div>
    <div class="alternative-address-section container-fluid">
        <div class="header">
            <span class="primary-text"> Alternate addresses</span>
        </div>
        <div class="main row">
        		<c:forEach items="${addressData}" var="address">
        			<c:if test="${not address.defaultAddress}">
        				<div class="address-container col-xs-12 col-md-6">
                			<div class="form-container">
                    				  <div class="mailing-address">
                    				  	  <span class="primary-text mailing-address__title">Shipping address</span>
                               <span class="secondary-text mailing-address__line-one">${fn:escapeXml(address.line1)}&nbsp;</span>
                               
                                <span class="secondary-text mailing-address__line-two"><c:if test="${not empty fn:escapeXml(address.line2)}">${fn:escapeXml(address.line2)}</c:if></span>
							    <span class="secondary-text mailing-address__line-three"><c:if test="${not empty fn:escapeXml(address.line3)}">${fn:escapeXml(address.line3)}</c:if></span>
                               <span class="secondary-text mailing-address__city">${fn:escapeXml(address.town)}</span>
                               <div>
                                   ${fn:escapeXml(address.region.name)}
                               </div>
                               <span class="secondary-text mailing-address__zip">${fn:escapeXml(address.postalCode)}</span>
                               <span class="secondary-text mailing-address__phone">${fn:escapeXml(address.phone)}</span>
                            			   <span class="secondary-text mailing-address__email">${fn:escapeXml(address.email)}</span>
                             	 <button type="button"
                                        class="btn-edit btn btn-primary"
                                        data-address-id="${fn:escapeXml(address.id)}"
                                        data-address-countryISOCode="${fn:escapeXml(address.country.isocode)}"
                                        onclick="ACC.billingshipping.populateFormUponEditAddress(this);"> Edit
                                </button>
                           </div>
                           
                           <c:set var="addressID" scope="request" value="${fn:escapeXml(address.id)}"/>
                           <c:set var="countryISO" scope="request" value="${fn:escapeXml(address.country.isocode)}"/>
                           <div class="error-mailing-address"></div>
                           <div class="mailing-address-edit">
                               <h4>SHIPPING ADDRESS</h4>
                               <div class="addressEdit">
                               		
                               </div>
                           </div>
                    			</div>
                    	</div>
        			</c:if>
        		</c:forEach>
        		
            
        </div>
        
    </div>