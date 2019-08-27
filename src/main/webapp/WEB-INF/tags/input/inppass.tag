<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@tag description="Drop-down menu"  %>
<%@attribute name="field" required="true" %>
<%@attribute name="title" %>

<c:set var="bindError"><form:errors path="${field}"/></c:set>
<div class="form-group">
    <label for="${field}" class="col-sm-4 control-label">${title}</label>
    <div class="col-sm-4">
        <form:input type="password" path="${field}" cssClass="form-control" />
        <c:if test="${not empty bindError}">
            <div class="alert alert-danger" role="alert">
                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                <span class="sr-only">Error:</span>
                <form:errors path="${field}" />
            </div>
        </c:if>
    </div>
</div>