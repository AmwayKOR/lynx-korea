<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<li class="${maxHeight ? 'simple' : ''}">
	<c:if test="${not empty title}">
		<h3 class="uppercase">
			<cms:component component="${title}" />
			<a class="show-terms">
				<i class="icon icon-exports"></i>
			</a>
		</h3>
	</c:if>
	<form:errors path="term[${radioCount}].termAccepted" />
	<div class="conditions">
		<div class="text">
			<cms:component component="${content}" />
		</div>
		<button class="text-btn view-all">
			<spring:theme code="register.terms.popup.viewall.button" />
			<i class="icon icon-arrow-downs"></i>
			<i class="icon icon-arrow-ups"></i>
		</button>
	</div>
	<div class="action amway-theme">
		<div class="radio-wrapper">
			<label class="amw-radio-wrap radio-label">
				<form:radiobutton value="true" path="term[${radioCount}].termAccepted" name="radio-${radioCount}"
					cssClass="amw-global-radio radio-${radioCount}" />
				<span class="amw-radio-overlay"></span>
				<span class="amw-label-radio-text">${radio1Description}</span>
			</label>
		</div>
		<div class="radio-wrapper">
			<label class="amw-radio-wrap radio-label">
				<form:radiobutton value="false" path="term[${radioCount}].termAccepted" name="radio-${radioCount}"
					cssClass="amw-global-radio radio-${radioCount}" />
				<span class="amw-radio-overlay"></span>
				<span class="amw-label-radio-text">${radio2Description}</span>
			</label>
		</div>
	</div>
	<input name="term[${radioCount}].termsComponentUid" type="hidden" value="${component.uid}" />
	<div class="pop-box-container">
		<div class="pop-box">
			<div class="pop-header uppercase">
				<cms:component component="${title}" />
				<i class="pop-close icon icon-x-close"></i>
			</div>
			<div class="pop-content">
				<div id="terms">
					<div class="div-table">
						<div class="col-xs-12">
							<div class="col-text">
								<cms:component component="${content}" />
							</div>
							<div class="line"></div>
						</div>
					</div>
				</div>
				<div class="action amway-theme">
					<div class="radio-wrapper">
						<label class="amw-radio-wrap radio-label">
							<input type="radio" value="true" name="radio-${radioCount}-popup" data-radio-count="radio-${radioCount}"
								class="amw-global-radio" />
							<span class="amw-radio-overlay"></span>
							<span class="amw-label-radio-text">${radio1Description}</span>
						</label>
					</div>
					<div class="radio-wrapper">
						<label class="amw-radio-wrap radio-label">
							<input type="radio" value="false" name="radio-${radioCount}-popup" data-radio-count="radio-${radioCount}"
								class="amw-global-radio" />
							<span class="amw-radio-overlay"></span>
							<span class="amw-label-radio-text">${radio2Description}</span>
						</label>
					</div>
				</div>
			</div>
			<div class="pop-footer">
				<a class="btn-blue-white" id="submit-btn">
					<spring:theme code="register.terms.popup.submit.button" />
				</a>
				<button class="btn-cancel" id="cancel-btn" data-radio-cancel="radio-${radioCount}">
					<spring:theme code="register.terms.popup.cancel.button" />
				</button>
			</div>
		</div>
	</div>
</li>
