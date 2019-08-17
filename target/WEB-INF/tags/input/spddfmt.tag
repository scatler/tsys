<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Drop-down menu" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@attribute name="field" required="true" %>
<%@attribute name="list" required="true" type="java.util.ArrayList" %>
<%@attribute name="title" required="true" %>

<c:set var="bindError"><form:errors path="${field}"/></c:set>
<form:select id="select" path="${field}" cssClass="form-control">
    <form:option value="" label="--Select ${title}"/>
    <form:options items="${list}" itemValue="id" itemLabel="name"/>
</form:select>
<c:if test="${not empty bindError}">
    <div class="alert alert-danger" role="alert">
        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
        <span class="sr-only">Error:</span>
        <form:errors path="${field}"/>
    </div>
</c:if>
