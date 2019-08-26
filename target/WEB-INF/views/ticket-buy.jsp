<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ti" tagdir="/WEB-INF/tags/input" %>
<t:genericpage>
    <form:form action="saveBuy" cssClass="form-horizontal"
               method="post" modelAttribute="ticket">
        <ti:input field="name" title="Name"/>
        <ti:input field="surname" title="Surname"/>
        <%--<ti:input field="birthday" title="Birthday"/>--%>
        <ti:btnsubmit title="Buy!"/>
    </form:form>
  </t:genericpage>
