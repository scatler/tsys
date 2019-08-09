<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@tag description="Drop-down menu"  %>
<%@attribute name="field" required="true" %>
<%@attribute name="title" %>

<div class="form-group">
    <label for="${field}" class="col-md-3 control-label">${title}</label>
    <div class="col-md-9">
        <form:input path="${field}" cssClass="form-control"/>
    </div>
</div>