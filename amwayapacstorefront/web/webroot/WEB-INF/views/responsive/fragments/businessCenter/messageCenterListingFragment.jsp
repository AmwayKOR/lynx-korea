<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:url var="messageCenterUrl" value="message-center"/>

<div class="js-account-message-wrapper">
    <c:forEach items="${searchPageData.results}" var="message" varStatus="loop">
        <div class="account-message-center-message row">
            <div class="col-xs-9">
                <div>
                    <a class="account-message-page js-account-show-detailed-message"
                        data-message-page-number="${pageNumber * pageSize + loop.index}" data-message-title="${message.shortDescription}">${message.shortDescription}</a>
                </div>
                <div>
                   <fmt:parseDate value="${message.publishDate}" pattern="mm/dd/yyyy" var="parsedDate"/>
                   <fmt:formatDate value='${parsedDate}' pattern="mm/dd/yyyy hh:mma" var="formattedParsedDate"/>
                   <span class="expiration-date">${fn:toLowerCase(formattedParsedDate)}</span>
                </div>
            </div>
            <div class="col-xs-3 text-right">
            		<div class="product-stock">
                      <div>
                          <span class="stock">
                              <span class="product-availability">
                					<c:choose>
						        		<c:when test="${notification.status.code eq 'READ'}">
						        			<span class="green">
                                         <span class="icon icon-alert"></span>
                                       	<span class="text text-uppercase">Message Type</span>
                                       	</span>
						        		</c:when>
						        		<c:otherwise>
						        			<span class="red">
                                         <span class="icon icon-alert"></span>
                                       	<span class="text text-uppercase">Message Type</span>
                                       	</span>
						        		</c:otherwise>
					        		</c:choose>
					        		</span>
                              </span>
                          </div>
                      </div>
            </div>
        </div>
    </c:forEach>

    <div class="hidden">
        <div id="accountMessageBlockId">
            <div class="js-account-message"></div>
         </div>
    </div>
</div>