<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
  
<div id ="message-center">
  <div class="container-fluid main-container">
        <h1 class="page-title">Message Center</h1>
        <div class="message-title">
            <div class="notice">15 messages</div>
            <div>Type of Messages:<div>&nbsp; All<span class="line">|</span><a>Message Type 1</a><span class="line">|</span><a>Message Type 2</a></div></div>
        </div>
      
        <div id="message-center-list" class="message-center-list">
            <div class="billing-shipping">
                <div class="panel-group accordion-billing-shipping" >
                    <div class="panel pop-style-one">
                        <div id="paymentInformationBody" class="panel-collapse">
                            <div class="panel-body">
                                <div class="account-paymentdetails account-list ">
                                    <div class="account-cards card-select">
                                        <div>
                                            <div class="payment-info-container container-fluid">
                                                <div class="row">
                                                    <div class="col-lg-6 col-md-6 col-xs-12 card-info-block">
                                                        <div class="card-info">
                                                            <div class="card-data">
                                                                <a><span class="panel-title-text">February End-of-Month Customer Service Hours</span></a>
                                                                <span class="expiration-date">mm/dd/yyyy - 12:00pm</span>
                                                            </div>
                                                        </div>

                                                    </div>
                                                    <div class="col-lg-6 col-md-6 col-xs-12 block-right">
                                                        <div class="product-stock">
                                                            <div>
                                                                <span class="stock">
                                                                    <span class="product-availability">
                                                                        <span class="green">
                                                                              <span class="icon icon-alert"></span>
                                                                            <span class="text text-uppercase">Message Type</span></span>
                                                                    </span>
                                                                </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel pop-style-two">
                        <div id="paymentInformationBody" class="panel-collapse">
                            <div class="panel-body">
                                <div class="account-paymentdetails account-list ">
                                    <div class="account-cards card-select">
                                        <div>
                                            <div class="payment-info-container container-fluid">
                                                <div class="row">
                                                    <div class="col-lg-6 col-md-6 col-xs-12 card-info-block">
                                                        <div class="card-info">
                                                            <div class="card-data">
                                                                <a><span class="panel-title-text">What’s Trending Now?</span></a>
                                                                <span class="expiration-date">mm/dd/yyyy - 12:00pm</span>
                                                            </div>
                                                        </div>

                                                    </div>
                                                    <div class="col-lg-6 col-md-6 col-xs-12 block-right">
                                                        <div class="product-stock">
                                                            <div>
                                                                <span class="stock">
                                                                    <span class="product-availability">
                                                                        <span class="green">
                                                                              <span class="icon icon-alert"></span>
                                                                            <span class="text text-uppercase">Message Type</span></span>
                                                                    </span>
                                                                </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel">
                        <div id="paymentInformationBody" class="panel-collapse">
                            <div class="panel-body">
                                <div class="account-paymentdetails account-list ">
                                    <div class="account-cards card-select">
                                        <div>
                                            <div class="payment-info-container container-fluid">
                                                <div class="row">
                                                    <div class="col-lg-6 col-md-6 col-xs-12 card-info-block">
                                                        <div class="card-info">
                                                            <div class="card-data">
                                                                <a><span class="panel-title-text">Your Credit Card Will Expire in 30 Days</span></a>
                                                                <span class="expiration-date">mm/dd/yyyy - 12:00pm</span>
                                                            </div>
                                                        </div>

                                                    </div>
                                                    <div class="col-lg-6 col-md-6 col-xs-12 block-right">
                                                        <div class="product-stock">
                                                            <div>
                                                                    <span class="stock">
                                                                        <span class="product-availability">
                                                                        <span class="red">
                                                                            <span class="icon icon-alert"></span>
                                                                            <span class="text">Message Type</span>
                                                                        </span>
                                                                        </span>
                                                                    </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel">
                        <div id="paymentInformationBody" class="panel-collapse">
                            <div class="panel-body">
                                <div class="account-paymentdetails account-list ">
                                    <div class="account-cards card-select">
                                        <div>
                                            <div class="payment-info-container container-fluid">
                                                <div class="row">
                                                    <div class="col-lg-6 col-md-6 col-xs-12 card-info-block">
                                                        <div class="card-info">
                                                            <div class="card-data">
                                                                <a><span class="panel-title-text">Limited Time Offer - Earn Extra PV/BV!</span></a>
                                                                <span class="expiration-date">mm/dd/yyyy - 12:00pm</span>
                                                            </div>
                                                        </div>

                                                    </div>
                                                    <div class="col-lg-6 col-md-6 col-xs-12 block-right">
                                                        <div class="product-stock">
                                                            <div>
                                                                    <span class="stock">
                                                                        <span class="product-availability">
                                                                        <span class="green">
                                                                            <span class="icon icon-alert"></span>
                                                                            <span class="text">Message Type</span>
                                                                        </span>
                                                                        </span>
                                                                    </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel">
                        <div id="paymentInformationBody" class="panel-collapse">
                            <div class="panel-body">
                                <div class="account-paymentdetails account-list ">
                                    <div class="account-cards card-select">
                                        <div>
                                            <div class="payment-info-container container-fluid">
                                                <div class="row">
                                                    <div class="col-lg-6 col-md-6 col-xs-12 card-info-block">
                                                        <div class="card-info">
                                                            <div class="card-data">
                                                                <a><span class="panel-title-text">Don’t Forget to Sign and Return your Silver Producer Acknowledgment</span></a>
                                                                <span class="expiration-date">mm/dd/yyyy - 12:00pm</span>
                                                            </div>
                                                        </div>

                                                    </div>
                                                    <div class="col-lg-6 col-md-6 col-xs-12 block-right">
                                                        <div class="product-stock">
                                                            <div>
                                                                    <span class="stock">
                                                                        <span class="product-availability">
                                                                        <span class="red">
                                                                            <span class="icon icon-alert"></span>
                                                                            <span class="text">Message Type</span>
                                                                        </span>
                                                                        </span>
                                                                    </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel">
                        <div id="paymentInformationBody" class="panel-collapse">
                            <div class="panel-body">
                                <div class="account-paymentdetails account-list ">
                                    <div class="account-cards card-select">
                                        <div>
                                            <div class="payment-info-container container-fluid">
                                                <div class="row">
                                                    <div class="col-lg-6 col-md-6 col-xs-12 card-info-block">
                                                        <div class="card-info">
                                                            <div class="card-data">
                                                                <a><span class="panel-title-text">Privacy Warning</span></a>
                                                                <span class="expiration-date">mm/dd/yyyy - 12:00pm</span>
                                                            </div>
                                                        </div>

                                                    </div>
                                                    <div class="col-lg-6 col-md-6 col-xs-12 block-right">
                                                        <div class="product-stock">
                                                            <div>
                                                                    <span class="stock">
                                                                        <span class="product-availability">
                                                                        <span class="red">
                                                                            <span class="icon icon-alert"></span>
                                                                            <span class="text">Message Type</span>
                                                                        </span>
                                                                        </span>
                                                                    </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel">
                        <div id="paymentInformationBody" class="panel-collapse">
                            <div class="panel-body">
                                <div class="account-paymentdetails account-list ">
                                    <div class="account-cards card-select">
                                        <div>
                                            <div class="payment-info-container container-fluid">
                                                <div class="row">
                                                    <div class="col-lg-6 col-md-6 col-xs-12 card-info-block">
                                                        <div class="card-info">
                                                            <div class="card-data">
                                                                <a><span class="panel-title-text">January End-of-Month Customer Service Hours</span></a>
                                                                <span class="expiration-date">mm/dd/yyyy - 12:00pm</span>
                                                            </div>
                                                        </div>

                                                    </div>
                                                    <div class="col-lg-6 col-md-6 col-xs-12 block-right">
                                                        <div class="product-stock">
                                                            <div>
                                                                    <span class="stock">
                                                                        <span class="product-availability">
                                                                        <span class="green">
                                                                            <span class="icon icon-alert"></span>
                                                                            <span class="text">Message Type</span>
                                                                        </span>
                                                                        </span>
                                                                    </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel">
                        <div id="paymentInformationBody" class="panel-collapse">
                            <div class="panel-body">
                                <div class="account-paymentdetails account-list ">
                                    <div class="account-cards card-select">
                                        <div>
                                            <div class="payment-info-container container-fluid">
                                                <div class="row">
                                                    <div class="col-lg-6 col-md-6 col-xs-12 card-info-block">
                                                        <div class="card-info">
                                                            <div class="card-data">
                                                                <a><span class="panel-title-text">Get Started Strong with New IBO Training Videos</span></a>
                                                                <span class="expiration-date">mm/dd/yyyy - 12:00pm</span>
                                                            </div>
                                                        </div>

                                                    </div>
                                                    <div class="col-lg-6 col-md-6 col-xs-12 block-right">
                                                        <div class="product-stock">
                                                            <div>
                                                                    <span class="stock">
                                                                        <span class="product-availability">
                                                                        <span class="green">
                                                                            <span class="icon icon-alert"></span>
                                                                            <span class="text">Message Type</span>
                                                                        </span>
                                                                        </span>
                                                                    </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel">
                        <div id="paymentInformationBody" class="panel-collapse">
                            <div class="panel-body">
                                <div class="account-paymentdetails account-list ">
                                    <div class="account-cards card-select">
                                        <div>
                                            <div class="payment-info-container container-fluid">
                                                <div class="row">
                                                    <div class="col-lg-6 col-md-6 col-xs-12 card-info-block">
                                                        <div class="card-info">
                                                            <div class="card-data">
                                                                <a><span class="panel-title-text">What’s Trending Now?</span></a>
                                                                <span class="expiration-date">mm/dd/yyyy - 12:00pm</span>
                                                            </div>
                                                        </div>

                                                    </div>
                                                    <div class="col-lg-6 col-md-6 col-xs-12 block-right">
                                                        <div class="product-stock">
                                                            <div>
                                                                    <span class="stock">
                                                                        <span class="product-availability">
                                                                        <span class="green">
                                                                            <span class="icon icon-alert"></span>
                                                                            <span class="text">Message Type</span>
                                                                        </span>
                                                                        </span>
                                                                    </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel">
                        <div id="paymentInformationBody" class="panel-collapse">
                            <div class="panel-body">
                                <div class="account-paymentdetails account-list ">
                                    <div class="account-cards card-select">
                                        <div>
                                            <div class="payment-info-container container-fluid">
                                                <div class="row">
                                                    <div class="col-lg-6 col-md-6 col-xs-12 card-info-block">
                                                        <div class="card-info">
                                                            <div class="card-data">
                                                                <a><span class="panel-title-text">Missing Information Notification</span></a>
                                                                <span class="expiration-date">mm/dd/yyyy - 12:00pm</span>
                                                            </div>
                                                        </div>

                                                    </div>
                                                    <div class="col-lg-6 col-md-6 col-xs-12 block-right">
                                                        <div class="product-stock">
                                                            <div>
                                                                    <span class="stock">
                                                                        <span class="product-availability">
                                                                        <span class="red">
                                                                            <span class="icon icon-alert"></span>
                                                                            <span class="text">Message Type</span>
                                                                        </span>
                                                                        </span>
                                                                    </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 
            <div class="account-orderhistory-pagination">
                <p class="text-center">
                    <button type="button" id="show-more" class="btn btn-link show-more-orders js-show-more-personal"><span>View More Messages</span>
                        <span class="icon icon-arrow-dropdown"></span>
                    </button>
                </p>
            </div>
             -->
        </div>
    </div>
</div>