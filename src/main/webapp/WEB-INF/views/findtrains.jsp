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
    <t:ajax/>
        <form  class="form-inline" name="getTimeTableForm" action="<c:url value="/station/getTimeTable"/>" method="post">
            <div class="form-group"><t:dropdown listCategory="${stations}" title="From"/></div>
            <div class="form-group"><t:dropdown listCategory="${stations}" title="To"/></div>
            <div class="form-group"><t:datepicker/></div>
            <div class="form-group"><t:btnsubmit/></div>
        </form>
    <t:ajaxresp/>

    </jsp:body>
</t:genericpage>