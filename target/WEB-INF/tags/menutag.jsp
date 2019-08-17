<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>SideBar Menu</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/menu.css">

</head>

<body>

<div id="sidebar">

    <ul>
        <li><a href="/user/list">List users</a></li>
        <li><a href="/user/addUser">Add user</a></li>

    </ul>

    <div id="sidebar-btn">
        <span></span>
        <span></span>
        <span></span>
    </div>

</div>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>

    $(document).ready(function(){
        $('#sidebar-btn').click(function(){
            $('#sidebar').toggleClass('visible');
        });
    });

</script>

</body>
</html>