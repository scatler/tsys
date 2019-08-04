<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="menu.jsp" flush="true"/>
<t:genericpage>
    <jsp:attribute name="header">
      <h1>Welcome</h1>
    </jsp:attribute>
    <jsp:attribute name="inner_title">
      <h1>Station TimeTable</h1>
    </jsp:attribute>
    <jsp:attribute name="footer">
      <p id="copyright">Contacts</p>
    </jsp:attribute>
    <jsp:body>
        <t:dropdown listCategory="${stations}"/>
        <t:datepicker/>
        <t:traintable timetable="${timetable}"/>
       </jsp:body>
</t:genericpage>