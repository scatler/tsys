<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="menu.jsp" flush="true"/>
<t:genericpage>
    <jsp:attribute name="header">
      <h1>Welcome</h1>
    </jsp:attribute>
    <jsp:attribute name="footer">
      <p id="copyright">Copyright 1927, Future Bits When There Be Bits Inc.</p>
    </jsp:attribute>
    <jsp:body>
        <t:dropdown listCategory="${stations}">
            Menu
        </t:dropdown>
        <table class="table table-striped table-bordered">
            <tr>
                <th>Train#</th>
                <th>Time</th>
             </tr>
            <c:forEach var="tt" items="${timetable}">
                  <tr>
                    <td>${tt.trainId}</td>
                    <td>${tt.arrivalTime}</td>
                </tr>
            </c:forEach>
        </table>
    </jsp:body>
</t:genericpage>