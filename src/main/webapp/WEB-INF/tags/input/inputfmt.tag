<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@tag description="Drop-down menu"  %>
<%@attribute name="field" required="true" %>
<%@attribute name="title" %>

<div class="form-group text-center">
    <div class="m-0">
        <form:input path="${field}" cssClass="form-control"/>
        <form:errors path="${field}" cssClass="error" />
    </div>
</div>