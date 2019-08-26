<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ti" tagdir="/WEB-INF/tags/input" %>
<t:genericpage inner_title="Tickets">
    <form:form class="form-horizontal" method="POST" modelAttribute="trainSelector" action="/tickets/trains">
        <ti:spdd field="stationFrom" list="${stations}" title="Station From"/>
        <ti:spdd field="stationTo" list="${stations}" title="Station To"/>
        <ti:datevalid field="day" title="Date"/>
        <ti:btnsubmit title="Find!"/>
    </form:form>
    <t:crudform genForm="${genForm}"/>
</t:genericpage>