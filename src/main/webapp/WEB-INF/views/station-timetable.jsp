<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:genericpage>
    <jsp:attribute name="header"/>

    <jsp:attribute name="inner_title">
      <h1>Station TimeTable</h1>
    </jsp:attribute>
    <jsp:attribute name="footer"/>

    <jsp:body>
        <t:ajax updateLink="getTimeTable"/>
        <form  class="form-inline" name="form" action="<c:url value="/station/getTimeTable"/>" method="post">
            <div class="form-group"><t:dropdown listCategory="${stations}" title="Select station:"/></div>
            <div class="form-group"><t:datepicker/></div>
            <div class="form-group"><t:btnsearch/></div>
        </form>
        <t:ajaxresp/>

    </jsp:body>
</t:genericpage>