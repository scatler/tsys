<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>SideBar Menu</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/menu.css">

</head>

<body>
<%= request.getSession().getAttribute("currentUser") %>
<div id="sidebar">
    <ul>
        <c:if test="${isAdmin == true}">
            <li><a href="/user/list">List users</a></li>
            <li><a href="/user/addUser">Add user1</a></li>
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