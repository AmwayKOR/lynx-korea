<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="address" tagdir="/WEB-INF/tags/responsive/address"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="panel panel-default">
       <div class="panel-heading" role="tab" id="headingTwo">
           <h4 class="panel-title">
               <div class="collapsed" data-toggle="collapse" data-parent="#business-page-accordion" href="#BusinessCollapseTwo" aria-expanded="false" aria-controls="BusinessCollapseTwo">
                   <span class="icon-users icon-businnes"></span>
                   <span class="text-uppercase">Co-APPLICANTS</span>
                   <span class="indicator pull-right"></span>
               </div>
           </h4>
       </div>
       <div id="BusinessCollapseTwo" class="panel-collapse applicants-panel collapse" role="tabpanel" aria-labelledby="headingTwo" style="height: 0px;">
           <div class="row">
               <div class="applicant-item col-md-6">
                   <div class="row">
                       <div class="col-xs-12 col-md-5 row-title">Primary Applicant name</div>
                       <div class="col-xs-12 col-md-7 row-description">Tony Martin</div>
                   </div>
                   <div class="row">
                       <div class="col-xs-12 col-md-5 row-title">PRIMARY phone</div>
                       <div class="col-xs-12 col-md-7 row-description">(555) 123-4567</div>
                   </div>
                   <div class="row">
                       <div class="col-xs-12 col-md-5 row-title">SECONDARY phone</div>
                       <div class="col-xs-12 col-md-7 row-description">(555) 456-7890</div>
                   </div>
                   <div class="row">
                       <div class="col-xs-12 col-md-5 row-title">EMAIL</div>
                       <div class="col-xs-12 col-md-7 row-description">tony_martin_amway@longdomainname.com</div>
                   </div>
               </div>
               <div class="applicant-item col-md-6">
                   <div class="row">
                       <div class="col-xs-12 col-md-5 row-title">Primary Applicant name</div>
                       <div class="col-xs-12 col-md-7 row-description">Tony Martin</div>
                   </div>
                   <div class="row">
                       <div class="col-xs-12 col-md-5 row-title">PRIMARY phone</div>
                       <div class="col-xs-12 col-md-7 row-description">(555) 123-4567</div>
                   </div>
                   <div class="row">
                       <div class="col-xs-12 col-md-5 row-title">SECONDARY phone</div>
                       <div class="col-xs-12 col-md-7 row-description">(555) 456-7890</div>
                   </div>
                   <div class="row">
                       <div class="col-xs-12 col-md-5 row-title">EMAIL</div>
                       <div class="col-xs-12 col-md-7 row-description">tony_martin_amway@longdomainname.com</div>
                   </div>
               </div>
               <div class="applicant-item col-md-6">
                   <div class="row">
                       <div class="col-xs-12 col-md-5 row-title">Primary Applicant name</div>
                       <div class="col-xs-12 col-md-7 row-description">Tony Martin</div>
                   </div>
                   <div class="row">
                       <div class="col-xs-12 col-md-5 row-title">PRIMARY phone</div>
                       <div class="col-xs-12 col-md-7 row-description">(555) 123-4567</div>
                   </div>
                   <div class="row">
                       <div class="col-xs-12 col-md-5 row-title">SECONDARY phone</div>
                       <div class="col-xs-12 col-md-7 row-description">(555) 456-7890</div>
                   </div>
                   <div class="row">
                       <div class="col-xs-12 col-md-5 row-title">EMAIL</div>
                       <div class="col-xs-12 col-md-7 row-description">tony_martin_amway@longdomainname.com</div>
                   </div>
               </div>
               <div class="applicant-item col-md-6">
                   <div class="row">
                       <div class="col-xs-12 col-md-5 row-title">Primary Applicant name</div>
                       <div class="col-xs-12 col-md-7 row-description">Tony Martin</div>
                   </div>
                   <div class="row">
                       <div class="col-xs-12 col-md-5 row-title">PRIMARY phone</div>
                       <div class="col-xs-12 col-md-7 row-description">(555) 123-4567</div>
                   </div>
                   <div class="row">
                       <div class="col-xs-12 col-md-5 row-title">SECONDARY phone</div>
                       <div class="col-xs-12 col-md-7 row-description">(555) 456-7890</div>
                   </div>
                   <div class="row">
                       <div class="col-xs-12 col-md-5 row-title">EMAIL</div>
                       <div class="col-xs-12 col-md-7 row-description">tony_martin_amway@longdomainname.com</div>
                   </div>
               </div>
               <div class="applicant-item col-md-6">
                   <div class="row">
                       <div class="col-xs-12 col-md-5 row-title">Primary Applicant name</div>
                       <div class="col-xs-12 col-md-7 row-description">Tony Martin</div>
                   </div>
                   <div class="row">
                       <div class="col-xs-12 col-md-5 row-title">PRIMARY phone</div>
                       <div class="col-xs-12 col-md-7 row-description">(555) 123-4567</div>
                   </div>
                   <div class="row">
                       <div class="col-xs-12 col-md-5 row-title">SECONDARY phone</div>
                       <div class="col-xs-12 col-md-7 row-description">(555) 456-7890</div>
                   </div>
                   <div class="row">
                       <div class="col-xs-12 col-md-5 row-title">EMAIL</div>
                       <div class="col-xs-12 col-md-7 row-description">tony_martin_amway@longdomainname.com</div>
                   </div>
               </div>
               <div class="applicant-item col-md-6">
                   <div class="row">
                       <div class="col-xs-12 col-md-5 row-title">Primary Applicant name</div>
                       <div class="col-xs-12 col-md-7 row-description">Tony Martin</div>
                   </div>
                   <div class="row">
                       <div class="col-xs-12 col-md-5 row-title">PRIMARY phone</div>
                       <div class="col-xs-12 col-md-7 row-description">(555) 123-4567</div>
                   </div>
                   <div class="row">
                       <div class="col-xs-12 col-md-5 row-title">SECONDARY phone</div>
                       <div class="col-xs-12 col-md-7 row-description">(555) 456-7890</div>
                   </div>
                   <div class="row">
                       <div class="col-xs-12 col-md-5 row-title">EMAIL</div>
                       <div class="col-xs-12 col-md-7 row-description">tony_martin_amway@longdomainname.com</div>
                   </div>
               </div>
           </div>
           <div class="account-orderhistory-pagination">
               <p class="text-center">
                   <a type="button" id="show-more" class="btn btn-link show-more-orders js-show-more-personal">
                       <span>View more Applicants</span>
                       <span class="icon icon-arrow-dropdown"></span>
                   </a>
               </p>
           </div>
       </div>
</div>