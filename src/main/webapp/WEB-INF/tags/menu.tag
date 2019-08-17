<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/menu.css">


<body>

<div id="sidebar">
    <ul>
        <c:if test="${isAdmin == true}">
            <li><a href="/user/list">List users</a></li>
            <li><a href="/user/add">Add user</a></li>
            <li><a href="/station/add">Add station</a></li>
            <li><a href="/train/add">Add train</a></li>
            <li><a href="/routes/add">Add route</a></li>
            <li><a href="/train/addRouteStation">Modify route</a></li>
            <li><a href="/tickets/viewAllPassengers"><spring:message code="view.all.passengers"/></a></li>
            <li><a href="/train/viewAllTrains"><spring:message code="view.all.trains"/></a></li>
            <li><a href="/train/TRD"><spring:message code="assign.trains.to.route"/></a></li>
        </c:if>
        <li><a href="/tickets/start"><spring:message code="find.tickets.and.trains"/></a></li>
        <li><a href="/station/timeTable">Station timetable</a></li>
    </ul>
    <div id="sidebar-btn">
        <span></span>
        <span></span>
        <span></span>
    </div>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>

    $(document).ready(function () {
        $('#sidebar-btn').click(function () {
            $('#sidebar').toggleClass('visible');
        });
    });

</script>

</body>
</html>