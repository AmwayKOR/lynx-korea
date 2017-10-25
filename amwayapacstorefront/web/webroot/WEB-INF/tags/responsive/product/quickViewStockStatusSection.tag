<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="stockLevelStatus" type="java.lang.String" %>

<div class="product-stock">
    <span class="stock">
        <span class="product-availability">
        	<c:choose>
        		<c:when test="${stockLevelStatus == 'outOfStock'}">
        			<span class="red">
		                <span class="text text-uppercase">Out Of Stock</span>
	            	</span>
        		</c:when>
        		<c:when test="${stockLevelStatus == 'inStock'}">
        			<span class="green">
		                <span class="icon icon-check-bold"></span>
		                <span class="text text-uppercase">In Stock</span>
	            	</span>
        		</c:when>
        	</c:choose>
        </span>
    </span>
</div>