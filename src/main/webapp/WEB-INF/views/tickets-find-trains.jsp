<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<t:genericpage>
    <jsp:attribute name="header"/>
    <jsp:attribute name="inner_title">
        <h1><spring:message code="find.tickets.and.trains"/></h1>
    </jsp:attribute>
    <jsp:attribute name="footer"/>
    <jsp:body>
        <t:ajax updateLink="trains"/><%--Input without start and end "/"--%>
        <form class="form-inline" name="form" action="<c:url value="/tickets/trains"/>" method="post">
            <div class="form-group"><t:dropdownname listCategory="${stations}" title="From" name="stationFrom"/></div>
            <div class="form-group"><t:dropdownname listCategory="${stations}" title="To" name="stationTo"/></div>
            <div class="form-group"><t:datepicker/></div>
            <div class="form-group"><t:btnsearch/></div>
        </form>
        <t:ajaxresp/>
    </jsp:body>
</t:genericpage>