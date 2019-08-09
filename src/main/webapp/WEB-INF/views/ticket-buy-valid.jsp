<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ti" tagdir="/WEB-INF/tags/input" %>
<t:genericpage>
    ${samePassengerError}
    <form:form method="POST" modelAttribute="ticket" action="saveBuy">
        <ti:input field="name" title="Name"/>
        <ti:input field="surname" title="Surname"/>
        <ti:datevalid field="birthday"/>
        <ti:btnsubmit title="Buy!"/>
    </form:form>
</t:genericpage>
