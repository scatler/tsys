<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ti" tagdir="/WEB-INF/tags/input" %>
<t:genericpage inner_title="Assign train to route">
    <form:form class="form-horizontal" method="POST" modelAttribute="trd" action="/train/saveTRD">
        <ti:spdd field="trainId" list="${trains}" title="Train"/>
        <ti:spdd field="routeId" list="${routes}" title="Route"/>
        <ti:datevalid field="day" title="Date"/>
        <ti:btnsubmit title="Assign!"/>
    </form:form>
 </t:genericpage>

