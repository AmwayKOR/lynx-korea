<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:url var="messageCenterUrl" value="message-center"/>


<div class="js-account-message-wrapper" >
	<div v-for="(message,index) of results" class="account-message-center-message row">
		<div class="col-xs-9">
            <div>
                <a class="account-message-page js-account-show-detailed-message" v-on:click.stop.prevent="getDetailedDescription(message.code)"
                    v-bind:data-message-page-number="dataMessageNumber(index)" v-bind:data-message-title="message.shortDescription">{{message.shortDescription}}</a>
            </div>
            <div>
            		<span class="expiration-date">{{messageCenterPublishDate(message.publishDate)}}</span>
            </div>
        </div>
        <div class="col-xs-3 text-right">
            		<div class="product-stock">
                      <div>
                          <span class="stock">
                              <span class="product-availability">
                              	<div v-if="message.status == 'READ'">
                              		<span class="green">
                                         <span class="icon icon-alert"></span>
                                       	<span class="text text-uppercase">Message Type</span>
                                     </span>
                              	</div>
                              	<div v-else>
                              		<span class="red">
                                         <span class="icon icon-alert"></span>
                                       	<span class="text text-uppercase">Message Type</span>
                                     </span>
                              	</div>
					        		</span>
                              </span>
                          </div>
                      </div>
            </div>
	</div>
    
    <div class="hidden">
        <div id="accountMessageBlockId">
            <div class="js-account-message"></div>
         </div>
    </div>
</div>
