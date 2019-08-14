<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/menu.css">


<body>

<div id="sidebar">
    <ul>
        <c:if test="${isAdmin == true}">
            <li><a href="/user/list">List users</a></li>
            <li><a href="/user/add">Add user</a></li>
            <li><a href="/station/add">Add station</a></li>
        </c:if>
        <li><a href="/tickets/trains">Buy tickets</a></li>
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