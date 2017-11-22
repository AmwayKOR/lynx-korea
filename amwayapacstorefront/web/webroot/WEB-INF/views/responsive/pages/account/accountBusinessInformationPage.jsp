<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="businessinformation" tagdir="/WEB-INF/tags/responsive/businessInformation"%>


    <div class="container-fluid my-count">
        
        <div class="row">
            <div id="business-page-accordion" role="tablist" aria-multiselectable="true">
                
                <businessinformation:businessInformation/>
                <businessinformation:coapplicants/>
                <businessinformation:upline/>
                
                
            </div>
        </div>

    </div>