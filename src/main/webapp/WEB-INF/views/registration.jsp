<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ti" tagdir="/WEB-INF/tags/input" %>
<t:genericpage inner_title="Register">
<%--    <jsp:attribute name="inner_title">
      <h1>Register</h1>
    </jsp:attribute>--%>
    <form:form cssClass="form-horizontal" method="POST" modelAttribute="user" action="/submitUser">
        <ti:input field="firstName" title="Name"/>
        <ti:input field="lastName" title="Surname"/>
        <ti:input field="email" title="E-mail"/>
        <ti:input field="login" title="login"/>
        <ti:input field="password" title="password"/>
        <ti:input field="matchingPassword" title="password"/>
        <ti:btnsubmit title="Register"/>
    </form:form>
<%--    <jsp:attribute name="error">--%>
      <h1>${error}</h1>
<%--    </jsp:attribute>--%>
</t:genericpage>