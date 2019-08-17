<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ti" tagdir="/WEB-INF/tags/input" %>
<t:genericpage inner_title="Modify route">
    <form:form action="/train/newRouteSubmisson" method="POST" modelAttribute="routeStationForm"
               cssClass="form-horizontal">
        <table class="table table-striped table-bordered table-condensed text-center ">
            <thead class="thead-dark ">
            <tr>
                <th>Station</th>
                <th>Arrival time</th>
                <th>Day count from start</th>
                <th>Stop time</th>
                <th>Select a row</th>
            </tr>
            </thead>
            <tbody>
            <ti:spdd field="routeId" list="${routesDtos}" title="Route"/>
            <c:forEach varStatus="us" var="r" items="${routeStationForm.rs}">
                <tr>
                    <td><ti:spddfmt field="rs[${us.index}].stationId" list="${stationDtos}" title=""/></td>
                    <td><ti:time field="rs[${us.index}].arrivalTime" title="" idtime="timer1${us.index}"/></td>
                    <td><ti:inputfmt field="rs[${us.index}].day" title=""/></td>
                    <td><ti:time field="rs[${us.index}].stopMin" title="" idtime="timer2${us.index}"/></td>
                    <td><ti:chkbox field="rs[${us.index}].isDeleted" title=""/></td>
                    <form:input type="hidden" path="rs[${us.index}].id"/>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <input type="submit" name="submit" value="addRow" class="btn btn-primary"/>
        <input type="submit" name="submit" value="go" class="btn btn-danger"/>
        <input type="submit" name="submit" value="Delete rows" class="btn btn-danger"/>
        <input type="submit" name="submit" value="Get data..." class="btn btn-danger"/>
    </form:form>
</t:genericpage>