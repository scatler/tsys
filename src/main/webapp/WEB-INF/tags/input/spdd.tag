<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Drop-down menu" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@attribute name="field" required="true"  %>
<%@attribute name="list" required="true" type="java.util.ArrayList" %>
<%@attribute name="title" required="true" %>
<div class="form-group">
    <label for="select" class="col-sm-4 control-label">${title}</label>
    <div class="col-sm-4">
        <form:select id="select" path="${field}" cssClass="form-control">
            <form:option value="" label="--Select ${title}"/>
            <form:options items="${list}" itemValue="id" itemLabel="name"/>
        </form:select>
        <form:errors path="${field}" cssClass="error"/>
    </div>
</div>