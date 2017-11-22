<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="address" tagdir="/WEB-INF/tags/responsive/address"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="panel panel-default">
       <div class="panel-heading" role="tab" id="headingOne">
           <h4 class="panel-title">
               <div data-toggle="collapse" data-parent="#business-page-accordion" href="#BusinessCollapseOne" aria-expanded="true" aria-controls="BusinessCollapseOne" class="collapsed">
                   <span class="icon-shop icon-businnes"></span>
                   <span class="text-uppercase">Business Information</span>
                   <span class="indicator pull-right"></span>
               </div>
           </h4>
       </div>
       <div id="BusinessCollapseOne" class="panel-collapse business-info collapse" role="tabpanel" aria-labelledby="headingOne" style="height: 40px;">
           <div class="row">
               <div class="col-xs-12 col-md-3 row-title">Business Name</div>
               <div class="col-xs-12 col-md-5 row-description">Jennifer Industries</div>
           </div>
           <div class="row">
               <div class="col-xs-12 col-md-3 row-title">Business Tax ID</div>
               <div class="col-xs-12 col-md-5 row-description">Tax ID on File</div>
           </div>
           <div class="row">
               <div class="col-xs-12 col-md-3 row-title">Mission Statement</div>
               <div id="mission-statement-text" class="col-xs-12 col-md-5 row-description mission-statement">
                   Despacito
                   Quiero respirar tu cuello despacito
                   Deja que te diga cosas al oído
                   Para que te acuerdes si no estás conmigo
                   Despacito
                   Quiero desnudarte a besos despacito
                   Firmo en las paredes de tu laberinto
                   Y hacer de tu cuerpo todo un manuscrito
               </div>
               <textarea id="mission-statement-textarea" class="col-xs-12 col-md-5 row-description mission-statement">Despacito Quiero respirar tu cuello despacito Deja que te diga cosas al oído Para que te acuerdes si no estás conmigo Despacito Quiero desnudarte a besos despacito Firmo en las paredes de tu laberinto Y hacer de tu cuerpo todo un manuscrito
                            </textarea>
           </div>
           <div class="row">
               <div class="col-xs-12 col-md-3 row-title">
                   <span>Business Photo</span>
                   <div class="photo-description">This photo will be used for the Line of Sponsorship tool</div>
               </div>
               <div class="col-xs-12 col-md-5 row-description business-photo">
                   <div><img src="${themeResourcePath}/images/no_photo.jpg"><button class="photo-edit"><span>Edit</span></button></div>
               </div>
           </div>
           <div class="row">
               <div  class="edit edit-item-link col-xs-12 col-md-3 row-title">
                   <button class="btn btn-default btn-block businees-action-btn btn-primary">
                       Edit</button>
               </div>
           </div>
       </div>
</div>