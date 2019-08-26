<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ti" tagdir="/WEB-INF/tags/input" %>
<t:genericpage inner_title="Update user">
    <form:form method="POST" modelAttribute="user" action="save" cssClass="form-horizontal">
        <form:input type="hidden" path="id"/>
        <ti:input field="firstName" title="First name"/>
        <ti:input field="lastName" title="Last name"/>
        <ti:input field="email" title="E-mail"/>
        <ti:input field="login" title="Login"/>
        <ti:inppass field="password" title="Password"/>
        <ti:inppass field="matchingPassword" title="Match Password"/>
        <ti:input field="type" title="Type"/>
        <ti:input field="Authorities" title="Authorities"/>
        <ti:btnsubmit title="Save"/>
    </form:form>
</t:genericpage>