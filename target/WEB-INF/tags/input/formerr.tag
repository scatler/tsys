<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@tag description="General form submission error"  %>
<c:set var="bindError"><form:errors path="user"/></c:set>
<c:if test="${not empty bindError}">
    <div class="alert alert-danger" role="alert">
        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
        <span class="sr-only">Error:</span>
        <form:errors path="user"/>
    </div>
</c:if>