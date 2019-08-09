
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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