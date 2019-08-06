<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Timetable" pageEncoding="UTF-8" %>
<%@attribute name="timetable" required="true" type="java.util.List" %>


<table class="table table-striped table-bordered table-condensed">
    <tr>
        <th>Train#</th>
        <th>Time</th>
    </tr>
    <c:forEach var="tt" items="${timetable.iterator()}">
        <tr>
            <td>${tt.trainId}</td>
            <td>${tt.arrivalTime}</td>
        </tr>
    </c:forEach>
</table>