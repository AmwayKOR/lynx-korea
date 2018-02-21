<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="pagination" tagdir="/WEB-INF/tags/responsive/nav/pagination" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="account" tagdir="/WEB-INF/tags/responsive/account"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>
<%@ taglib prefix="messageCenter" tagdir="/WEB-INF/tags/responsive/messageCenter" %>

<c:url value="/message-center/messagelist-data" var="messageBodyUrl"/>

<div id ="message-center">
  <div class="container-fluid main-container" id="message-centers">
        <h1 class="page-title"><spring:theme code="text.account.messageCenter"/></h1>
        <div class="message-title">
        
        		<div v-if="pagination.totalNumberOfResults > 0" class="notice">
			  {{ pagination.totalNumberOfResults }} messages
			</div>
			<div v-else class="notice">
			  You have 0 messages
			</div>
        		
            <div>Type of Messages:<div>&nbsp; All<span class="line">|</span><a v-on:click.stop.prevent="filterByMessageType('READ')" />Message Type 1</a><span class="line">|</span><a v-on:click.stop.prevent="filterByMessageType('UNREAD')">Message Type 2</a></div></div>
        </div>
      
        <div id="message-center-list" class="message-center-list">
            <div class="billing-shipping">
                <div class="panel-group accordion-billing-shipping" >
                			<div class="account-message-center-section-content">
			                <div class="account-message-center-messages container-fluid">
			                    <%-- <c:set var="numberOfPages" value="${searchPageData.pagination.totalNumberOfResults / searchPageData.pagination.pageSize}"/> --%>
			                    <input type="hidden" class="js-message-center-number-of-pages" v-bind:value="hiddenTotalNumberPage"/>
			                    <input type="hidden" v-bind:value="hiddenMessageTypeFilter"/>
								<div class="js-message-center-search-result" >
			                     	<messageCenter:messageCenterListing/></div>	                       
		                    </div>
		                </div>
			   </div>
            </div>
            
            <br/>
			
			<div v-if="pagination.totalNumberOfResults > pagination.pageSize" class="account-message-center-pagination">
				<p class="text-center">
	                   <a id="show-more" v-show="isShow" class="js-message-center-show-more" v-on:click.stop.prevent="viewMore(pagination.currentPage)">
	                     <span>
	                       <spring:theme code="text.account.messageCenter.viewMore"/>
	                     </span>
	                     <span class="icon icon-arrow-dropdown"></span>
	                   </a>
	            </p>
			</div>
       </div>    
    </div>
</div>
<div id="colorbox" v-show="isShowPopup" class="bonus-payment-modal bonus-payment-method lynx-modal-window--opened historyBox message-center-pop message-center-pop1 display-block" >
    <div id="cboxWrapper">
        <div id="cboxContent">
            <div id="cboxLoadedContent">
                <div class="message-center-pop-content">
                            
                </div>
                <button type="button" id="cboxClose" class="cClose"><span class="modal-close-icon">x</span></button>
            </div>
        </div>
    </div>
</div>