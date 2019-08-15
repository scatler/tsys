<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ti" tagdir="/WEB-INF/tags/input" %>
<t:genericpage>
    <form:form method="POST" modelAttribute="trainDTO" action="save" cssClass="form-horizontal">
        <ti:input field="name" title="Name"/>
        <ti:input field="seats" title="Seats"/>
        <ti:btnsubmit title="Save"/>
    </form:form>
</t:genericpage>