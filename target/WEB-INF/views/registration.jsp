<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ti" tagdir="/WEB-INF/tags/input" %>
<t:genericpage inner_title="Register">
    <form:form cssClass="form-horizontal" method="POST" modelAttribute="user" action="/submitUser">
        <%--<ti:formerr/>--%>
        <ti:input field="firstName" title="Name"/>
        <ti:input field="lastName" title="Surname"/>
        <ti:input field="email" title="E-mail"/>
        <ti:input field="login" title="login"/>
        <ti:inppass field="password" title="password"/>
        <ti:inppass field="matchingPassword" title="password"/>
        <ti:btnsubmit title="Register"/>
    </form:form>
</t:genericpage>