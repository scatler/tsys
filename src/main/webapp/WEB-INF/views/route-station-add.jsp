<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ti" tagdir="/WEB-INF/tags/input" %>
<t:genericpage inner_title="Modify route">
    <form:form action="/train/newRouteSubmisson" method="POST"  modelAttribute="routeStationForm" cssClass="form-horizontal">
        <table class="table table-striped table-bordered table-condensed text-center ">
            <thead class="thead-dark ">
            <tr>
                <th>Station</th>
                <th>Arrival time</th>
                <th>Day count from start</th>
                <th>Stop in minutes</th>
            </tr>
            </thead>
            <tbody>
            <ti:spdd field="routeId" list="${routesDtos}" title="Route"/>
            <%--<td><ti:spdd field="${routeDtos}" list="" title=""/></td>--%>
            <c:forEach varStatus="us" var="r" items="${routeStationForm.rs}" >
                <tr>
                    <td><ti:spdd field="rs[${us.index}].stationId" list="${stationDtos}" title=""/></td>
                    <%--<td><form:input type="text" path="rs[${us.index}].arrivalTime" value="00:00:00"/> ${rs.arrivalTime}</td>--%>
                    <td><ti:inputfmt field="rs[${us.index}].arrivalTime" title=""/></td>
                    <td ><ti:inputfmt field="rs[${us.index}].day" title=""/></td>
                    <td><ti:input field="rs[${us.index}].stopMin" title=""/></td>
                    <td><form:input type="hidden" path="rs[${us.index}].id"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <input type="submit" name="submit" value="addRow" class="btn-primary" />
        <input type="submit" name="submit" value="go" class="btn-danger" />
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form:form>
</t:genericpage>